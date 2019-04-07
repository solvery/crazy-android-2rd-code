package org.crazyit.image;

import android.animation.*;
import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
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
public class BouncingBalls extends Activity
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
	{
		public final ArrayList<ShapeHolder> balls
			= new ArrayList<ShapeHolder>();

		public MyAnimationView(Context context)
		{
			super(context);
			// ���ض�����Դ
			ObjectAnimator colorAnim = (ObjectAnimator) AnimatorInflater
				.loadAnimator(BouncingBalls.this, R.animator.color_anim);
			colorAnim.setEvaluator(new ArgbEvaluator());
			// �Ը�View����Ӧ�����Զ���
			colorAnim.setTarget(this);
			// ��ʼָ������
			colorAnim.start();
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
			// ����С��ѹ�⡱�Ķ������ö�������С���x���ꡰ�����ơ������
			ValueAnimator squashAnim1 = ObjectAnimator.ofFloat(newBall
				, "x", newBall.getX(), newBall.getX() - BALL_SIZE / 2);
			// ����squashAnim1��������ʱ��
			squashAnim1.setDuration(duration / 4);
			// ����squashAnim1�������ظ�1��
			squashAnim1.setRepeatCount(1);
			// ����squashAnim1�������ظ���ʽ
			squashAnim1.setRepeatMode(ValueAnimator.REVERSE);
			// ����squashAnim1�����Ĳ�ֵ��ʽ�����ٲ�ֵ
			squashAnim1.setInterpolator(new DecelerateInterpolator());
			// ����С��ѹ�⡱�Ķ������ö�������С��Ŀ�ȼӱ�
			ValueAnimator squashAnim2 = ObjectAnimator.ofFloat(newBall,
				"width", newBall.getWidth()
				, newBall.getWidth() + BALL_SIZE);
			// ����squashAnim2��������ʱ��
			squashAnim2.setDuration(duration / 4);
			// ����squashAnim2�������ظ�1��
			squashAnim2.setRepeatCount(1);
			// ����squashAnim2�������ظ���ʽ
			squashAnim2.setRepeatMode(ValueAnimator.REVERSE);
			// ����squashAnim2�����Ĳ�ֵ��ʽ�����ٲ�ֵ
			squashAnim2.setInterpolator(new DecelerateInterpolator());
			// ����С�����족�Ķ������ö�������С���y���ꡰ�����ơ������
			ObjectAnimator stretchAnim1 = ObjectAnimator.ofFloat(newBall
				, "y", endY, endY + BALL_SIZE / 2);
			// ����stretchAnim1��������ʱ��
			stretchAnim1.setDuration(duration / 4);
			// ����stretchAnim1�����ظ�1��
			stretchAnim1.setRepeatCount(1);
			// ����stretchAnim1�������ظ���ʽ
			stretchAnim1.setRepeatMode(ValueAnimator.REVERSE);
			// ����stretchAnim1�����Ĳ�ֵ��ʽ�����ٲ�ֵ
			stretchAnim1.setInterpolator(new DecelerateInterpolator());
			// ����С�����족�Ķ������ö�������С��ĸ߶ȼ���
			ValueAnimator stretchAnim2 = ObjectAnimator.ofFloat(newBall,
				"height", newBall.getHeight()
				, newBall.getHeight() - BALL_SIZE / 2);
			// ����stretchAnim2��������ʱ��
			stretchAnim2.setDuration(duration / 4);
			// ����squashAnim2�������ظ�1��
			stretchAnim2.setRepeatCount(1);
			// ����squashAnim2�������ظ���ʽ
			stretchAnim2.setRepeatMode(ValueAnimator.REVERSE);
			// ����squashAnim2�����Ĳ�ֵ��ʽ�����ٲ�ֵ
			stretchAnim2.setInterpolator(new DecelerateInterpolator());
			// ����С�򡰵��𡱵Ķ���
			ObjectAnimator bounceBackAnim = ObjectAnimator.ofFloat(
				newBall , "y", endY, startY);
			// ���ó���ʱ��
			bounceBackAnim.setDuration(duration);
			// ���ö����Ĳ�ֵ��ʽ�����ٲ�ֵ
			bounceBackAnim.setInterpolator(new DecelerateInterpolator());
			// ʹ��AnimatorSet��˳�򲥷š�����/ѹ��&����/���𶯻�
			AnimatorSet bouncer = new AnimatorSet();
			// ������squashAnim1����֮ǰ����fallAnim���䶯��
			bouncer.play(fallAnim).before(squashAnim1);
			// ������ΪС���ڡ���Ļ���·�����ʱ��С��Ҫ����ѹ��
			// ������ȼӱ���x�������ư���򣬸߶ȼ��롢y�������ư����
			// ��˴˴�ָ������squashAnim1��ͬʱ��
			// ������squashAnim2��stretchAnim1��stretchAnim2
			bouncer.play(squashAnim1).with(squashAnim2);
			bouncer.play(squashAnim1).with(stretchAnim1);
			bouncer.play(squashAnim1).with(stretchAnim2);
			// ָ������stretchAnim2����֮�󣬲���bounceBackAnim���𶯻�
			bouncer.play(bounceBackAnim).after(stretchAnim2);
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
			// �ٴζ���һ��AnimatorSet����϶���
			AnimatorSet animatorSet = new AnimatorSet();
			// ָ���ڲ���fadeAnim֮ǰ���Ȳ���bouncer����
			animatorSet.play(bouncer).before(fadeAnim);
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
	}
}