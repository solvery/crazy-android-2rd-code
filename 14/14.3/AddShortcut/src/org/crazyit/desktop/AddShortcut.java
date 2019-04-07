package org.crazyit.desktop;

import java.util.Timer;
import java.util.TimerTask;

import org.crazyit.desktop.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

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
public class AddShortcut extends Activity
{
	ImageView flower;
	// �������ݶ�����Դ
	Animation anim, reverse;
	final Handler handler = new Handler()
	{
		@Override
		public void handleMessage(Message msg)
		{
			if (msg.what == 0x123)
			{
				flower.startAnimation(reverse);
			}
		}
	};
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		flower = (ImageView) findViewById(R.id.flower);
		// ���ص�һ�ݶ�����Դ
		anim = AnimationUtils.loadAnimation(this, R.anim.anim);
		// ���ö���������������״̬
		anim.setFillAfter(true);
		// ���صڶ��ݶ�����Դ
		reverse = AnimationUtils.loadAnimation(this, R.anim.reverse);
		// ���ö���������������״̬
		reverse.setFillAfter(true);
		Button bn = (Button) findViewById(R.id.bn);
		// Ϊ��ť�ĵ����¼���Ӽ�����
		bn.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View source)
			{
				// ������ӿ�ݷ�ʽ��Intent
				Intent addIntent = new Intent(
					"com.android.launcher.action.INSTALL_SHORTCUT"); //��
				String title = getResources().getString(R.string.title);
				// ���ؿ�ݷ�ʽ��ͼ��
				Parcelable icon = Intent.ShortcutIconResource.fromContext(
					AddShortcut.this, R.drawable.ic_launcher);
				// ���������ݷ�ʽ�����Intent,�ô�����������Ŀ�ݷ�ʽ���ٴ������ó���
				Intent myIntent = new Intent(AddShortcut.this,
					AddShortcut.class);
				// ���ÿ�ݷ�ʽ�ı���
				addIntent.putExtra(Intent.EXTRA_SHORTCUT_NAME, title); //��
				// ���ÿ�ݷ�ʽ��ͼ��
				addIntent.putExtra(Intent.EXTRA_SHORTCUT_ICON_RESOURCE
					, icon); //��
				// ���ÿ�ݷ�ʽ��Ӧ��Intent
				addIntent.putExtra(Intent.EXTRA_SHORTCUT_INTENT
					, myIntent); //��
				// ���͹㲥��ӿ�ݷ�ʽ
				sendBroadcast(addIntent); //��
			}
		});
	}

	@Override
	public void onResume()
	{
		super.onResume();
		// ��ʼִ�ж���
		flower.startAnimation(anim);
		// ����3.5��������ڶ�������
		new Timer().schedule(new TimerTask()
		{
			@Override
			public void run()
			{
				handler.sendEmptyMessage(0x123);
			}
		}, 3500);
	}
}