package org.crazyit.sound;

import java.io.File;

import android.app.Activity;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
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
public class RecordVideo extends Activity
	implements OnClickListener
{
	// �����е�������ť
	ImageButton record , stop;
	// ϵͳ����Ƶ�ļ�
	File videoFile ;
	MediaRecorder mRecorder;
	// ��ʾ��ƵԤ����SurfaceView
    SurfaceView sView;
    // ��¼�Ƿ����ڽ���¼��
    private boolean isRecording = false;

    @Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		// ��ȡ��������е�������ť
		record = (ImageButton) findViewById(R.id.record);
		stop = (ImageButton) findViewById(R.id.stop);
		// ��stop��ť�����á�
		stop.setEnabled(false);
		// Ϊ������ť�ĵ����¼��󶨼�����
		record.setOnClickListener(this);
		stop.setOnClickListener(this);
		// ��ȡ��������е�SurfaceView
		sView = (SurfaceView) this.findViewById(R.id.sView);
        // ����Surface����Ҫ�Լ�ά��������
        sView.getHolder().setType(SurfaceHolder
        	.SURFACE_TYPE_PUSH_BUFFERS);
        // ���÷ֱ���
        sView.getHolder().setFixedSize(320, 280);
        // ���ø��������Ļ�����Զ��ر�
        sView.getHolder().setKeepScreenOn(true);
	}

	@Override
	public void onClick(View source)
	{
		switch (source.getId())
		{
			// ����¼�ư�ť
			case R.id.record:
				if (!Environment.getExternalStorageState().equals(
					android.os.Environment.MEDIA_MOUNTED))
				{
					Toast.makeText(RecordVideo.this
						, "SD�������ڣ������SD����"
						, Toast.LENGTH_SHORT).show();
					return;
				}
				try
				{
					// ��������¼����Ƶ����Ƶ�ļ�
					videoFile = new File(Environment
						.getExternalStorageDirectory()
						.getCanonicalFile() + "/myvideo.mp4");
					// ����MediaPlayer����
					mRecorder = new MediaRecorder();
					mRecorder.reset();
					// ���ô���˷�ɼ�����
					mRecorder.setAudioSource(MediaRecorder
						.AudioSource.MIC);
					// ���ô�����ͷ�ɼ�ͼ��
					mRecorder.setVideoSource(MediaRecorder
						.VideoSource.CAMERA);
					// ������Ƶ�ļ��������ʽ
					// �������������������ʽ��ͼ������ʽ֮ǰ����
					mRecorder.setOutputFormat(MediaRecorder
						.OutputFormat.MPEG_4);
					// ������������ĸ�ʽ
					mRecorder.setAudioEncoder(MediaRecorder
						.AudioEncoder.DEFAULT);
					// ����ͼ�����ĸ�ʽ
					mRecorder.setVideoEncoder(MediaRecorder
						.VideoEncoder.MPEG_4_SP);
					mRecorder.setVideoSize(320, 280);
					// ÿ�� 4֡
					mRecorder.setVideoFrameRate(4);
					mRecorder.setOutputFile(videoFile.getAbsolutePath());
					// ָ��ʹ��SurfaceView��Ԥ����Ƶ
					mRecorder.setPreviewDisplay(sView
						.getHolder().getSurface());  //��
					mRecorder.prepare();
					// ��ʼ¼��
					mRecorder.start();
					System.out.println("---recording---");
					// ��record��ť�����á�
					record.setEnabled(false);
					// ��stop��ť���á�
					stop.setEnabled(true);
					isRecording = true;
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
				break;
			// ����ֹͣ��ť
			case R.id.stop:
				// ������ڽ���¼��
				if (isRecording)
				{
					// ֹͣ¼��
					mRecorder.stop();
					// �ͷ���Դ
					mRecorder.release();
					mRecorder = null;
					// ��record��ť���á�
					record.setEnabled(true);
					// ��stop��ť�����á�
					stop.setEnabled(false);
				}
				break;
		}
	}
}
