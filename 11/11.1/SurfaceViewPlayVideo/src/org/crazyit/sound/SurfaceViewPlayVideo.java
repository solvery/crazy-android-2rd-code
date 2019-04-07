package org.crazyit.sound;

import java.io.IOException;

import android.app.Activity;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.ImageButton;

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
public class SurfaceViewPlayVideo extends Activity
	implements OnClickListener
{
	SurfaceView surfaceView;
	ImageButton play, pause, stop;
	MediaPlayer mPlayer;
	// ��¼��ǰ��Ƶ�Ĳ���λ��
	int position;

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		// ��ȡ�����е�3����ť
		play = (ImageButton) findViewById(R.id.play);
		pause = (ImageButton) findViewById(R.id.pause);
		stop = (ImageButton) findViewById(R.id.stop);
		// Ϊ3����ť�ĵ����¼����¼�������
		play.setOnClickListener(this);
		pause.setOnClickListener(this);
		stop.setOnClickListener(this);
		// ����MediaPlayer
		mPlayer = new MediaPlayer();
		surfaceView = (SurfaceView) this.findViewById(R.id.surfaceView);
		// ���ò���ʱ����Ļ
		surfaceView.getHolder().setKeepScreenOn(true);
		surfaceView.getHolder().addCallback(new SurfaceListener());
	}

	@Override
	public void onClick(View source)
	{
		try
		{
			switch (source.getId())
			{
				// ���Ű�ť������
				case R.id.play:
					play();
					break;
				// ��ͣ��ť������
				case R.id.pause:
					if (mPlayer.isPlaying())
					{
						mPlayer.pause();
					}
					else
					{
						mPlayer.start();
					}
					break;
				// ֹͣ��ť������
				case R.id.stop:
					if (mPlayer.isPlaying()) mPlayer.stop();
					break;
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	private void play() throws IOException
	{
		mPlayer.reset();
		mPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
		// ������Ҫ���ŵ���Ƶ
		mPlayer.setDataSource("/mnt/sdcard/movie.3gp");
		// ����Ƶ���������SurfaceView
		mPlayer.setDisplay(surfaceView.getHolder());  //��
		mPlayer.prepare();
		// ��ȡ���ڹ�����
		WindowManager wManager = getWindowManager();
		DisplayMetrics metrics = new DisplayMetrics();
		// ��ȡ��Ļ��С
		wManager.getDefaultDisplay().getMetrics(metrics);
		// ������Ƶ�����ݺ�����ŵ�ռ��������Ļ
		surfaceView.setLayoutParams(new LayoutParams(metrics.widthPixels
			, mPlayer.getVideoHeight() * metrics.widthPixels
			/ mPlayer.getVideoWidth()));
		mPlayer.start();
	}

	private class SurfaceListener implements SurfaceHolder.Callback
	{
		@Override
		public void surfaceChanged(SurfaceHolder holder, int format,
			int width, int height)
		{
		}

		@Override
		public void surfaceCreated(SurfaceHolder holder)
		{
			if (position > 0)
			{
				try
				{
					// ��ʼ����
					play();
					// ��ֱ�Ӵ�ָ��λ�ÿ�ʼ����
					mPlayer.seekTo(position);
					position = 0;
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		}

		@Override
		public void surfaceDestroyed(SurfaceHolder holder)
		{
		}
	}

	// ������Activity���򿪣���ͣ����
	@Override
	protected void onPause()
	{
		if (mPlayer.isPlaying())
		{
			// ���浱ǰ�Ĳ���λ��
			position = mPlayer.getCurrentPosition();
			mPlayer.stop();
		}
		super.onPause();
	}

	@Override
	protected void onDestroy()
	{
		// ֹͣ����
		if (mPlayer.isPlaying()) mPlayer.stop();
		// �ͷ���Դ
		mPlayer.release();
		super.onDestroy();
	}
}