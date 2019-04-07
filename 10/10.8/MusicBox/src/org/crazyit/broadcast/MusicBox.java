package org.crazyit.broadcast;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.TextView;

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
public class MusicBox extends Activity implements OnClickListener
{
	// ��ȡ��������ʾ�������⡢�����ı���
	TextView title, author;
	// ����/��ͣ��ֹͣ��ť
	ImageButton play, stop;
	ActivityReceiver activityReceiver;
	public static final String CTL_ACTION = 
		"org.crazyit.action.CTL_ACTION";
	public static final String UPDATE_ACTION =
		"org.crazyit.action.UPDATE_ACTION";
	// �������ֵĲ���״̬��0x11����û�в��ţ�0x12�������ڲ��ţ�0x13������ͣ
	int status = 0x11;
	String[] titleStrs = new String[] { "��Ը", "Լ��", "����������" };
	String[] authorStrs = new String[] { "δ֪������", "��ޥ", "���" };

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		// ��ȡ�����������е�������ť
		play = (ImageButton) this.findViewById(R.id.play);
		stop = (ImageButton) this.findViewById(R.id.stop);
		title = (TextView) findViewById(R.id.title);
		author = (TextView) findViewById(R.id.author);
		// Ϊ������ť�ĵ����¼���Ӽ�����
		play.setOnClickListener(this);
		stop.setOnClickListener(this);
		activityReceiver = new ActivityReceiver();
		// ����IntentFilter
		IntentFilter filter = new IntentFilter();
		// ָ��BroadcastReceiver������Action
		filter.addAction(UPDATE_ACTION);
		// ע��BroadcastReceiver
		registerReceiver(activityReceiver, filter);
		Intent intent = new Intent(this, MusicService.class);
		// ������̨Service
		startService(intent);
	}

	// �Զ����BroadcastReceiver�����������Service�������Ĺ㲥
	public class ActivityReceiver extends BroadcastReceiver
	{
		@Override
		public void onReceive(Context context, Intent intent)
		{
			// ��ȡIntent�е�update��Ϣ��update������״̬
			int update = intent.getIntExtra("update", -1);
			// ��ȡIntent�е�current��Ϣ��current����ǰ���ڲ��ŵĸ���
			int current = intent.getIntExtra("current", -1);
			if (current >= 0)
			{
				title.setText(titleStrs[current]);
				author.setText(authorStrs[current]);
			}
			switch (update)
			{
				case 0x11:
					play.setImageResource(R.drawable.play);
					status = 0x11;
					break;
				// ����ϵͳ���벥��״̬
				case 0x12:
					// ����״̬������ʹ����ͣͼ��
					play.setImageResource(R.drawable.pause);
					// ���õ�ǰ״̬
					status = 0x12;
					break;
				// ����ϵͳ������ͣ״̬
				case 0x13:
					// ��ͣ״̬������ʹ�ò���ͼ��
					play.setImageResource(R.drawable.play);
					// ���õ�ǰ״̬
					status = 0x13;
					break;
			}
		}
	}

	@Override
	public void onClick(View source)
	{
		// ����Intent
		Intent intent = new Intent("org.crazyit.action.CTL_ACTION");
		switch (source.getId())
		{
			// ���²���/��ͣ��ť
			case R.id.play:
				intent.putExtra("control", 1);
				break;
			// ����ֹͣ��ť
			case R.id.stop:
				intent.putExtra("control", 2);
				break;
		}
		// ���͹㲥������Service����е�BroadcastReceiver���յ�
		sendBroadcast(intent);
	}
}