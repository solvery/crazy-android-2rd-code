package org.crazyit.image;

import android.animation.*;
import android.animation.Animator.AnimatorListener;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RadialGradient;
import android.graphics.Shader;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.LinearLayout;

import java.util.ArrayList;

/**
 * Description:
 * <br/>site: <a href="http://www.crazyit.org">crazyit.org</a>
 * <br/>Copyright (C), 2001-2014, Yeeku.H.Lee
 * <br/>This program is protected by copyright laws.
 * <br/>Program Name:
 * <br/>Date:
 * @author  Yeeku.H.Lee kongyeeku@163.com
 * @version  1.0
 */
public class AnimatorTest extends Activity
{
	// ����С��Ĵ�С�ĳ���
	static final float BALL_SIZE = 50F;
	// ����С�����Ļ�Ϸ������䵽��Ļ�׶˵���ʱ��
	static final float FULL_TIME = 1000;
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		LinearLayout container = (LinearLayout)
			findViewById(R.id.container);
		// ���øô�����ʾMyAnimationView���
		container.addView(new MyAnimationView(this));
	}

	public class MyAnimationView extends View
		implements AnimatorUpdateListener
	{
		public final ArrayList<ShapeHolder> balls 
			= new ArrayList<ShapeHolder>();
		public MyAnimationView(Context context)
		{
			super(context);
			setBackgroundColor(Color.WHITE);
		}
		@Override
		public boolean onTouchEvent(MotionEvent event)
		{
			// ��������¼����ǰ��¡��ƶ��¼�
			if (event.getAction() != MotionEvent.ACTION_DOWN
				&& event.getAction() != MotionEvent.ACTION_MOVE)
			{
				return false;
			}
			//  ���¼����������һ��С����һ��Բ�δ���
			ShapeHolder newBall = addBall(event.getX(), event.getY());
			// ����С�����䶯����ʼʱ��y����
			float startY = newBall.getY();
			// ����С�����䶯������ʱ��y���꣨�䵽��Ļ���·���������Ļ�߶ȼ�ȥС��߶ȣ�
			float endY = getHeight() - BALL_SIZE;
			// ��ȡ��Ļ�߶�
			float h = (float) getHeight();
			float eventY = event.getY();
			// ���㶯���ĳ���ʱ��
			int duration = (int) (FULL_TIME * ((h - eventY) / h));
			// ����С�����¡��Ķ�������newBall�����y���Դ��¼�������仯����Ļ���·�
			ValueAnimator fallAnim = ObjectAnimator.ofFloat(
				newBall, "y", startY, endY);
			// ����fallAnim�����ĳ���ʱ��
			fallAnim.setDuration(duration);
			// ����fallAnim�����Ĳ�ֵ��ʽ�����ٲ�ֵ
			fallAnim.setInterpolator(new AccelerateInterpolator());
			// ΪfallAnim������Ӽ�������
			// ��ValueAnimator������ֵ�����ı�ʱ�����ἤ���ü��������¼���������
			fallAnim.addUpdateListener(this);
			// �����newBall�����alpha����ִ�д�1��0�Ķ����������彥��������
			ObjectAnimator fadeAnim = ObjectAnimator.ofFloat(newBall
				, "alpha", 1f, 0f);
			// ���ö�������ʱ��
			fadeAnim.setDuration(250);
			// ΪfadeAnim������Ӽ�����
			fadeAnim.addListener(new AnimatorListenerAdapter()
			{
				// ����������ʱ
				@Override
				public void onAnimationEnd(Animator animation)
				{
					// ��������ʱ���ö���������ShapeHolderɾ��
					balls.remove(((ObjectAnimator) animation).getTarget());
				}
			});
			// ΪfadeAnim������Ӽ�������
			// ��ValueAnimator������ֵ�����ı�ʱ�����ἤ���ü��������¼���������
			fadeAnim.addUpdateListener(this);		
			// ����һ��AnimatorSet����϶���
			AnimatorSet animatorSet = new AnimatorSet();
			// ָ���ڲ���fadeAnim֮ǰ���Ȳ���fallAnim����
			animatorSet.play(fallAnim).before(fadeAnim);
			// �������Ŷ���
			animatorSet.start();
			return true;
		}

		private ShapeHolder addBall(float x, float y)
		{
			// ����һ��Բ
			OvalShape circle = new OvalShape();
			// ���ø���Բ�Ŀ���
			circle.resize(BALL_SIZE, BALL_SIZE);
			// ��Բ��װ��Drawable����
			ShapeDrawable drawable = new ShapeDrawable(circle);
			// ����һ��ShapeHolder����
			ShapeHolder shapeHolder = new ShapeHolder(drawable);
			// ����ShapeHolder��x��y����
			shapeHolder.setX(x - BALL_SIZE / 2);
			shapeHolder.setY(y - BALL_SIZE / 2);
			int red = (int) (Math.random() * 255);
			int green = (int) (Math.random() * 255);
			int blue = (int) (Math.random() * 255);
			// ��red��green��blue3���������ϳ�ARGB��ɫ
			int color = 0xff000000 + red << 16 | green << 8 | blue;
			// ��ȡdrawable�Ϲ����Ļ���
			Paint paint = drawable.getPaint();
			// ��red��green��blue3�����������4�õ���ֵ��ϳ�ARGB��ɫ
			int darkColor = 0xff000000 | red / 4 << 16 
				| green / 4 << 8 | blue / 4;
			// ����Բ�ν���
			RadialGradient gradient = new RadialGradient(
				37.5f, 12.5f, BALL_SIZE, color, darkColor
				, Shader.TileMode.CLAMP);
			paint.setShader(gradient);
			// ΪshapeHolder����paint����
			shapeHolder.setPaint(paint);
			balls.add(shapeHolder);
			return shapeHolder;
		}
		@Override
		protected void onDraw(Canvas canvas)
		{
			// ����balls�����е�ÿ��ShapeHolder����
			for (ShapeHolder shapeHolder : balls)
			{
				// ����canvas�ĵ�ǰ����ϵͳ
				canvas.save();
				// ����任������������ϵͳƽ�Ƶ�shapeHolder��X��Y���괦��
				canvas.translate(shapeHolder.getX()
					, shapeHolder.getY());
				// ��shapeHolder���е�Բ�λ�����Canvas��
				shapeHolder.getShape().draw(canvas);
				// �ָ�Canvas����ϵͳ
				canvas.restore();
			}
		}
		@Override
		public void onAnimationUpdate(ValueAnimator animation)
		{
			// ָ���ػ�ý��档
			this.invalidate(); //��
		}	
	}
}