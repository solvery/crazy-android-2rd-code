package org.crazyit.sensor;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.EditText;

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
public class AccelerometerTest extends Activity
	implements SensorEventListener
{
	// ����ϵͳ��Sensor������
	SensorManager sensorManager;
	EditText etTxt1;

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		// ��ȡ��������ϵ��ı������
		etTxt1 = (EditText) findViewById(R.id.txt1);
		// ��ȡϵͳ�Ĵ������������
		sensorManager = (SensorManager) getSystemService(
			Context.SENSOR_SERVICE);  //��
	}

	@Override
	protected void onResume()
	{
		super.onResume();
		// Ϊϵͳ�ļ��ٶȴ�����ע�������
		sensorManager.registerListener(this,
			sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
			SensorManager.SENSOR_DELAY_GAME);  //��
	}

	@Override
	protected void onStop()
	{
		// ȡ��ע��
		sensorManager.unregisterListener(this);
		super.onStop();
	}

	// ������ʵ��SensorEventListener�ӿڱ���ʵ�ֵķ���
	// ����������ֵ�����ı�ʱ�ص��÷���
	@Override
	public void onSensorChanged(SensorEvent event)
	{
		float[] values = event.values;
		StringBuilder sb = new StringBuilder();
		sb.append("X�����ϵļ��ٶȣ�");
		sb.append(values[0]);
		sb.append("\nY�����ϵļ��ٶȣ�");
		sb.append(values[1]);
		sb.append("\nZ�����ϵļ��ٶȣ�");
		sb.append(values[2]);
		etTxt1.setText(sb.toString());
	}

	// �����������ȸı�ʱ�ص��÷�����
	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy)
	{
	}
}