package org.crazyit.sound;

import java.io.File;

import android.app.Activity;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

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
public class VedioViewTest extends Activity
{
	VideoView videoView;
	MediaController mController;
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		getWindow().setFormat(PixelFormat.TRANSLUCENT);
		setContentView(R.layout.main);
		// ��ȡ������VideoView���
		videoView = (VideoView) findViewById(R.id.video);
		// ����MediaController����
		mController = new MediaController(this);
		File video = new File("/mnt/sdcard/movie.mp4");
		if(video.exists())
		{
			videoView.setVideoPath(video.getAbsolutePath());  //��
			// ����videoView��mController��������
			videoView.setMediaController(mController);  //��
			// ����mController��videoView��������
			mController.setMediaPlayer(videoView);  //��
			// ��VideoView��ȡ����
			videoView.requestFocus();
		}
	}
}