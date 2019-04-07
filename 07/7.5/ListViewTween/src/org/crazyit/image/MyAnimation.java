package org.crazyit.image;

import android.graphics.Camera;
import android.graphics.Matrix;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.Transformation;

public class MyAnimation extends Animation
{
	private float centerX;
	private float centerY;
	// ���嶯���ĳ����¼�
	private int duration;
	private Camera camera = new Camera();

	public MyAnimation(float x, float y, int duration)
	{
		this.centerX = x;
		this.centerY = y;
		this.duration = duration;
	}

	@Override
	public void initialize(int width, int height
		, int parentWidth, int parentHeight)
	{
		super.initialize(width, height, parentWidth, parentHeight);
		// ���ö����ĳ���ʱ��
		setDuration(duration);
		// ���ö���������Ч������
		setFillAfter(true);
		setInterpolator(new LinearInterpolator());
	}

	/*
	 * �÷�����interpolatedTime�����˳���Ķ�������ʱ�䣬���ܶ���ʵ�ʳ���ʱ��೤��
	 * interpolatedTime�������Ǵ�0��������ʼʱ����1����������ʱ�� 
	 * Transformation���������˶�Ŀ����������ı�.
	 */
	@Override
	protected void applyTransformation(float interpolatedTime
		, Transformation t)
	{
		camera.save();
		// ����interpolatedTimeʱ��������X��Y��Z�ϵ�ƫ��
		camera.translate(100.0f - 100.0f * interpolatedTime,
				150.0f * interpolatedTime - 150,
				80.0f - 80.0f * interpolatedTime);
		// ���ø���interpolatedTimeʱ����Y������ת��ͬ�Ƕȡ�
		camera.rotateY(360 * (interpolatedTime));
		// ���ø���interpolatedTimeʱ����X������ת��ͬ�Ƕ�
		camera.rotateX((360 * interpolatedTime));
		// ��ȡTransformation������Matrix����
		Matrix matrix = t.getMatrix();
		camera.getMatrix(matrix);
		matrix.preTranslate(-centerX, -centerY);
		matrix.postTranslate(centerX, centerY);
		camera.restore();
	}
}