package org.crazyit.io;

import org.crazyit.io.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ViewFlipper;

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
public class GestureFlip extends Activity 
	implements OnGestureListener
{
	// ViewFlipperʵ��
	ViewFlipper flipper;
	// �������Ƽ����ʵ��
	GestureDetector detector;
	// ����һ���������飬����ΪViewFlipperָ���л�����Ч��
	Animation[] animations = new Animation[4];
	// �������ƶ�������֮�����С����
	final int FLIP_DISTANCE = 50;

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		// �������Ƽ����
		detector = new GestureDetector(this, this);
		// ���ViewFlipperʵ��
		flipper = (ViewFlipper) this.findViewById(R.id.flipper);
		// ΪViewFlipper���5��ImageView���
		flipper.addView(addImageView(R.drawable.java));
		flipper.addView(addImageView(R.drawable.ee));
		flipper.addView(addImageView(R.drawable.ajax));
		flipper.addView(addImageView(R.drawable.xml));
		flipper.addView(addImageView(R.drawable.classic));
		// ��ʼ��Animation����
		animations[0] = AnimationUtils.loadAnimation(
			this, R.anim.left_in);
		animations[1] = AnimationUtils.loadAnimation(
			this, R.anim.left_out);
		animations[2] = AnimationUtils.loadAnimation(
			this, R.anim.right_in);
		animations[3] = AnimationUtils.loadAnimation(
			this, R.anim.right_out);
	}

	// �������ImageView�Ĺ��߷���
	private View addImageView(int resId)
	{
		ImageView imageView = new ImageView(this);
		imageView.setImageResource(resId);
		imageView.setScaleType(ImageView.ScaleType.CENTER);
		return imageView;
	}

	@Override
	public boolean onFling(MotionEvent event1, MotionEvent event2,
		float velocityX, float velocityY)
	{
		// �����һ�������¼���X������ڵڶ��������¼���X���곬��FLIP_DISTANCE
		// Ҳ�������ƴ������󻬡�
		if (event1.getX() - event2.getX() > FLIP_DISTANCE)
		{
			// Ϊflipper�����л��ĵĶ���Ч��
			flipper.setInAnimation(animations[0]);
			flipper.setOutAnimation(animations[1]);
			flipper.showPrevious();
			return true;
		}
		// ����ڶ��������¼���X������ڵ�һ�������¼���X���곬��FLIP_DISTANCE
		// Ҳ�������ƴ������󻬡�
		else if (event2.getX() - event1.getX() > FLIP_DISTANCE)
		{
			// Ϊflipper�����л��ĵĶ���Ч��
			flipper.setInAnimation(animations[2]);
			flipper.setOutAnimation(animations[3]);
			flipper.showNext();
			return true;
		}
		return false;
	}

	@Override
	public boolean onTouchEvent(MotionEvent event)
	{
		// ����Activity�ϵĴ����¼�����GestureDetector����
		return detector.onTouchEvent(event);
	}

	@Override
	public boolean onDown(MotionEvent arg0)
	{
		return false;
	}

	@Override
	public void onLongPress(MotionEvent event)
	{
	}

	@Override
	public boolean onScroll(MotionEvent event1
		, MotionEvent event2, float arg2, float arg3)
	{
		return false;
	}

	@Override
	public void onShowPress(MotionEvent event)
	{
	}

	@Override
	public boolean onSingleTapUp(MotionEvent event)
	{
		return false;
	}
}