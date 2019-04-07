package org.crazyit.res;

import java.io.IOException;

import android.app.Activity;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

/**
 * Description: <br/>
 * site: <a href="http://www.crazyit.org">crazyit.org</a> <br/>
 * Copyright (C), 2001-2012, Yeeku.H.Lee <br/>
 * This program is protected by copyright laws. <br/>
 * Program Name: <br/>
 * Date:
 * 
 * @author Yeeku.H.Lee kongyeeku@163.com
 * @version 1.0
 */
public class RawResTest extends Activity
{
	MediaPlayer mediaPlayer1 = null;
	MediaPlayer mediaPlayer2 = null;

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		// ֱ�Ӹ��������ļ���ID������MediaPlayer��
		mediaPlayer1 = MediaPlayer.create(this, R.raw.bomb);
		// ��ȡ��Ӧ�õ�AssetManager
		AssetManager am = getAssets();
		try
		{
			// ��ȡָ���ļ���Ӧ��AssetFileDescriptor��
			AssetFileDescriptor afd = am.openFd("shot.mp3");
			mediaPlayer2 = new MediaPlayer();
			// ʹ��MediaPlayer����ָ���������ļ���
			mediaPlayer2.setDataSource(afd.getFileDescriptor());
			mediaPlayer2.prepare();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		// ��ȡ��һ����ť����Ϊ�����¼�������
		Button playRaw = (Button) findViewById(R.id.playRaw);
		playRaw.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View arg0)
			{
				// ��������
				mediaPlayer1.start();
			}
		});
		// ��ȡ�ڶ�����ť����Ϊ�����¼�������
		Button playAsset = (Button) findViewById(R.id.playAsset);
		playAsset.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View arg0)
			{
				// ��������
				mediaPlayer2.start();
			}
		});
	}
}