package org.crazyit.event;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnKeyListener;
import android.widget.Button;

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
public class Propagation extends Activity
{
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		Button bn = (Button) findViewById(R.id.bn);
		// Ϊbn���¼�������
		bn.setOnKeyListener(new OnKeyListener()
		{
			@Override
			public boolean onKey(View source
					, int keyCode, KeyEvent event)
			{
				// ֻ�����¼����¼�
				if (event.getAction() == KeyEvent.ACTION_DOWN)
				{
					Log.v("-Listener-", "the onKeyDown in Listener");
				}
				// ����false���������¼������⴫��
				return true; // ��
			}
		});
	}

	// ��дonKeyDown�������÷����ɼ���������������������İ����������¼�
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event)
	{
		super.onKeyDown(keyCode , event);
		Log.v("-Activity-" , "the onKeyDown in Activity");
		//����false��������δ��ȫ������¼������¼���Ȼ������ɢ
		return false;
	}
}