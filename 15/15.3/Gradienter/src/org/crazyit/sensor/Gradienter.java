package org.crazyit.sensor;


import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;

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
public class Gradienter extends Activity 
	implements SensorEventListener
{
	// ����ˮƽ�ǵ��Ǳ���
	MyView show;
	// ����ˮƽ���ܴ���������б�ǣ������ýǶȣ����ݽ�ֱ����λ�ڱ߽硣
	int MAX_ANGLE = 30;
	// ����Sensor������
	SensorManager mSensorManager;
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		// ��ȡˮƽ�ǵ������
		show = (MyView) findViewById(R.id.show);
		// ��ȡ�������������
		mSensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
	}

	@Override
	public void onResume()
	{
		super.onResume();
		// Ϊϵͳ�ķ��򴫸���ע�������
		mSensorManager.registerListener(this,
			mSensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION),
			SensorManager.SENSOR_DELAY_GAME);
	}

	@Override
	protected void onPause()
	{
		// ȡ��ע��
		mSensorManager.unregisterListener(this);
		super.onPause();
	}

	@Override
	protected void onStop()
	{
		// ȡ��ע��
		mSensorManager.unregisterListener(this);
		super.onStop();
	}

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy)
	{
	}

	@Override
	public void onSensorChanged(SensorEvent event)
	{
		float[] values = event.values;
		// ��ȡ����event�Ĵ���������
		int sensorType = event.sensor.getType();
		switch (sensorType)
		{
			case Sensor.TYPE_ORIENTATION:
				// ��ȡ��Y��ļн�
				float yAngle = values[1];
				// ��ȡ��Z��ļн�
				float zAngle = values[2];
				// ����λ���м�ʱ��ˮƽ����ȫˮƽ�������ݵ�X��Y����
				int x = (show.back.getWidth() - show.bubble.getWidth()) / 2;
				int y = (show.back.getHeight() - show.bubble.getHeight()) / 2;
				// �����Z�����б�ǻ������Ƕ�֮��
				if (Math.abs(zAngle) <= MAX_ANGLE)
				{
					// ������Z�����б�Ƕȼ���X����ı仯ֵ����б�Ƕ�Խ��X����仯Խ��
					int deltaX = (int) ((show.back.getWidth() - show.bubble
						.getWidth()) / 2 * zAngle / MAX_ANGLE);
					x += deltaX;
				}
				// �����Z�����б���Ѿ�����MAX_ANGLE������Ӧ�������
				else if (zAngle > MAX_ANGLE)
				{
					x = 0;
				}
				// �����Z�����б���Ѿ�С�ڸ���MAX_ANGLE������Ӧ�����ұ�
				else
				{
					x = show.back.getWidth() - show.bubble.getWidth();
				}
				// �����Y�����б�ǻ������Ƕ�֮��
				if (Math.abs(yAngle) <= MAX_ANGLE)
				{
					// ������Y�����б�Ƕȼ���Y����ı仯ֵ����б�Ƕ�Խ��Y����仯Խ��
					int deltaY = (int) ((show.back.getHeight() - show.bubble
						.getHeight()) / 2 * yAngle / MAX_ANGLE);
					y += deltaY;
				}
				// �����Y�����б���Ѿ�����MAX_ANGLE������Ӧ�����±�
				else if (yAngle > MAX_ANGLE)
				{
					y = show.back.getHeight() - show.bubble.getHeight();
				}
				// �����Y�����б���Ѿ�С�ڸ���MAX_ANGLE������Ӧ�����ұ�
				else
				{
					y = 0;
				}
				// ������������X��Y���껹λ��ˮƽ�ǵ��Ǳ����ڣ�����ˮƽ�ǵ���������
				if (isContain(x, y))
				{
					show.bubbleX = x;
					show.bubbleY = y;
				}
				// ֪ͨϵͳ�ػ�MyView���
				show.postInvalidate();
				break;
		}
	}

	// ����x��y��������Ƿ���ˮƽ�ǵ��Ǳ�����
	private boolean isContain(int x, int y)
	{
		// �������ݵ�Բ������X��Y
		int bubbleCx = x + show.bubble.getWidth() / 2;
		int bubbleCy = y + show.bubble.getWidth() / 2;
		// ����ˮƽ���Ǳ��̵�Բ������X��Y
		int backCx = show.back.getWidth() / 2;
		int backCy = show.back.getWidth() / 2;
		// �������ݵ�Բ����ˮƽ���Ǳ��̵�Բ��֮��ľ��롣
		double distance = Math.sqrt((bubbleCx - backCx) * (bubbleCx - backCx)
			+ (bubbleCy - backCy) * (bubbleCy - backCy));
		// ������Բ�ĵľ���С�����ǵİ뾶�������Ϊ���ڸõ��������Ȼλ���Ǳ�����
		if (distance < (show.back.getWidth() - show.bubble.getWidth()) / 2)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
}