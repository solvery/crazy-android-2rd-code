package org.crazyit.manager;

import android.app.Activity;
import android.app.Service;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.MotionEvent;

import android.widget.Toast;

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
public class VibratorTest extends Activity
{
	Vibrator vibrator;

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		// ��ȡϵͳ��Vibrator����
		vibrator = (Vibrator) getSystemService(
			Service.VIBRATOR_SERVICE);
	}

	// ��дonTouchEvent���������û�����������ʱ�����÷���
	@Override
	public boolean onTouchEvent(MotionEvent event)
	{
		Toast.makeText(this, "�ֻ���"
			, Toast.LENGTH_LONG).show();
		// �����ֻ���2��
		vibrator.vibrate(2000);
		return super.onTouchEvent(event);
	}
}