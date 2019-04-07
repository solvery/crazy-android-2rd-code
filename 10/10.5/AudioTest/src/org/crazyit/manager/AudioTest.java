package org.crazyit.manager;

import android.app.Activity;
import android.app.Service;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ToggleButton;

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
public class AudioTest extends Activity
{
	Button play, up, down;
	ToggleButton mute;
	AudioManager aManager;

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		// ��ȡϵͳ����Ƶ����
		aManager = (AudioManager) getSystemService(
			Service.AUDIO_SERVICE);
		// ��ȡ������3����ť��һ��ToggleButton�ؼ�
		play = (Button) findViewById(R.id.play);
		up = (Button) findViewById(R.id.up);
		down = (Button) findViewById(R.id.down);
		mute = (ToggleButton) findViewById(R.id.mute);
		// Ϊplay��ť�ĵ����¼��󶨼�����
		play.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View source)
			{
				// ��ʼ��MediaPlayer����׼����������
				MediaPlayer mPlayer = MediaPlayer.create(
					AudioTest.this, R.raw.earth);
				// ����ѭ������
				mPlayer.setLooping(true);
				// ��ʼ����
				mPlayer.start();
			}
		});

		up.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View source)
			{
				// ָ���������ֵ���Ƶ������������������ʾ����ͼ��ʾ��
				aManager.adjustStreamVolume(AudioManager.STREAM_MUSIC,
					AudioManager.ADJUST_RAISE, AudioManager.FLAG_SHOW_UI);
			}
		});
		down.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View source)
			{
				// ָ���������ֵ���Ƶ������������������ʾ����ͼ��ʾ��
				aManager.adjustStreamVolume(AudioManager.STREAM_MUSIC,
					AudioManager.ADJUST_LOWER, AudioManager.FLAG_SHOW_UI);
			}
		});
		mute.setOnCheckedChangeListener(new OnCheckedChangeListener()
		{
			@Override
			public void onCheckedChanged(CompoundButton source,
				boolean isChecked)
			{
				// ָ���������ֵ���Ƶ������isCheckedȷ���Ƿ���Ҫ����
				aManager.setStreamMute(AudioManager.STREAM_MUSIC,
					isChecked);
			}
		});
	}
}