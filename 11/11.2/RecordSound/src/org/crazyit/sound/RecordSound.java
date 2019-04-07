package org.crazyit.sound;

import java.io.File;

import android.app.Activity;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
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
public class RecordSound extends Activity
	implements OnClickListener
{
	// ��������ϵ�������ť
	ImageButton record, stop;
	// ϵͳ����Ƶ�ļ�
	File soundFile;
	MediaRecorder mRecorder;

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		// ��ȡ��������е�������ť
		record = (ImageButton) findViewById(R.id.record);
		stop = (ImageButton) findViewById(R.id.stop);
		// Ϊ������ť�ĵ����¼��󶨼�����
		record.setOnClickListener(this);
		stop.setOnClickListener(this);
	}

	@Override
	public void onDestroy()
	{
		if (soundFile != null && soundFile.exists())
		{
			// ֹͣ¼��
			mRecorder.stop();
			// �ͷ���Դ
			mRecorder.release();
			mRecorder = null;
		}
		super.onDestroy();
	}

	@Override
	public void onClick(View source)
	{
		switch (source.getId())
		{
		// ����¼����ť
			case R.id.record:
				if (!Environment.getExternalStorageState().equals(
					android.os.Environment.MEDIA_MOUNTED))
				{
					Toast.makeText(RecordSound.this, "SD�������ڣ������SD����",
						Toast.LENGTH_SHORT).show();
					return;
				}
				try
				{
					// ��������¼������Ƶ�ļ�
					soundFile = new File(Environment
						.getExternalStorageDirectory().getCanonicalFile()
						+ "/sound.amr");
					mRecorder = new MediaRecorder();
					// ����¼����������Դ
					mRecorder.setAudioSource(MediaRecorder
						.AudioSource.MIC);
					// ����¼�Ƶ������������ʽ���������������������ʽ֮ǰ���ã�
					mRecorder.setOutputFormat(MediaRecorder
						.OutputFormat.THREE_GPP);
					// ������������ĸ�ʽ
					mRecorder.setAudioEncoder(MediaRecorder
						.AudioEncoder.AMR_NB);
					mRecorder.setOutputFile(soundFile.getAbsolutePath());
					mRecorder.prepare();
					// ��ʼ¼��
					mRecorder.start();  //��
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
				break;
			// ����ֹͣ��ť
			case R.id.stop:
				if (soundFile != null && soundFile.exists())
				{
					// ֹͣ¼��
					mRecorder.stop();  //��
					// �ͷ���Դ
					mRecorder.release();  //��
					mRecorder = null;
				}
				break;
		}
	}
}