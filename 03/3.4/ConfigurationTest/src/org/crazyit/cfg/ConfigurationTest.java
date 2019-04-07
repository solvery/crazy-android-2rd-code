package org.crazyit.cfg;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
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
public class ConfigurationTest extends Activity
{
	EditText ori;
	EditText navigation;
	EditText touch;
	EditText mnc;
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		// ��ȡӦ�ý����еĽ������
		ori = (EditText)findViewById(R.id.ori);
		navigation = (EditText)findViewById(R.id.navigation);
		touch = (EditText)findViewById(R.id.touch);
		mnc = (EditText)findViewById(R.id.mnc);
		Button bn = (Button)findViewById(R.id.bn);
		bn.setOnClickListener(new OnClickListener()
		{
			// Ϊ��ť���¼�������
			@Override
			public void onClick(View source)
			{
				// ��ȡϵͳ��Configuration����
				Configuration cfg = getResources().getConfiguration();
				String screen = cfg.orientation ==
					Configuration.ORIENTATION_LANDSCAPE 
					? "������Ļ": "������Ļ";
				String mncCode = cfg.mnc + "";
				String naviName = cfg.orientation ==
					Configuration.NAVIGATION_NONAV
					? "û�з������" :
					cfg.orientation == Configuration.NAVIGATION_WHEEL
					? "���ֿ��Ʒ���" :
					cfg.orientation == Configuration.NAVIGATION_DPAD
					? "��������Ʒ���" : "�켣����Ʒ���";
				navigation.setText(naviName);
				String touchName = cfg.touchscreen == 
					Configuration.TOUCHSCREEN_NOTOUCH
					? "�޴�����" : "֧�ִ�����";
				ori.setText(screen);
				mnc.setText(mncCode);
				touch.setText(touchName);
			}
		});
	}
}