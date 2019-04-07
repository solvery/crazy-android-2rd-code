/**
 *
 */
package org.crazyit.broadcast;

import java.io.IOException;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.IBinder;

/**
 * Description:
 * <br/>��վ: <a href="http://www.crazyit.org">���Java����</a>
 * <br/>Copyright (C), 2001-2014, Yeeku.H.Lee
 * <br/>This program is protected by copyright laws.
 * <br/>Program Name:
 * <br/>Date:
 * @author  Yeeku.H.Lee kongyeeku@163.com
 * @version  1.0
 */
public class MusicService extends Service
{
	MyReceiver serviceReceiver;
	AssetManager am;
	String[] musics = new String[] { "wish.mp3", "promise.mp3",
		"beautiful.mp3" };
	MediaPlayer mPlayer;
	// ��ǰ��״̬,0x11 ����û�в��� ��0x12���� ���ڲ��ţ�0x13������ͣ
	int status = 0x11;
	// ��¼��ǰ���ڲ��ŵ�����
	int current = 0;

	@Override
	public IBinder onBind(Intent intent)
	{
		return null;
	}

	@Override
	public void onCreate()
	{
		am = getAssets();
		// ����BroadcastReceiver
		serviceReceiver = new MyReceiver();
		// ����IntentFilter
		IntentFilter filter = new IntentFilter();
		filter.addAction(MusicBox.CTL_ACTION);
		registerReceiver(serviceReceiver, filter);
		// ����MediaPlayer
		mPlayer = new MediaPlayer();
		// ΪMediaPlayer��������¼��󶨼�����
		mPlayer.setOnCompletionListener(new OnCompletionListener() //��
		{
			@Override
			public void onCompletion(MediaPlayer mp)
			{
				current++;
				if (current >= 3)
				{
					current = 0;
				}
				// ���͹㲥֪ͨActivity�����ı���
				Intent sendIntent = new Intent(MusicBox.UPDATE_ACTION);
				sendIntent.putExtra("current", current);
				// ���͹㲥 ������Activity����е�BroadcastReceiver���յ�
				sendBroadcast(sendIntent);
				// ׼��������������
				prepareAndPlay(musics[current]);
			}
		});
		super.onCreate();
	}

	public class MyReceiver extends BroadcastReceiver
	{
		@Override
		public void onReceive(final Context context, Intent intent)
		{
			int control = intent.getIntExtra("control", -1);
			switch (control)
			{
				// ���Ż���ͣ
				case 1:
					// ԭ������û�в���״̬
					if (status == 0x11)
					{
						// ׼��������������
						prepareAndPlay(musics[current]);
						status = 0x12;
					}
					// ԭ�����ڲ���״̬
					else if (status == 0x12)
					{
						// ��ͣ
						mPlayer.pause();
						// �ı�Ϊ��ͣ״̬
						status = 0x13;
					}
					// ԭ��������ͣ״̬
					else if (status == 0x13)
					{
						// ����
						mPlayer.start();
						// �ı�״̬
						status = 0x12;
					}
					break;
				// ֹͣ����
				case 2:
					// ���ԭ�����ڲ��Ż���ͣ
					if (status == 0x12 || status == 0x13)
					{
						// ֹͣ����
						mPlayer.stop();
						status = 0x11;
					}
			}
			// ���͹㲥֪ͨActivity����ͼ�ꡢ�ı���
			Intent sendIntent = new Intent(MusicBox.UPDATE_ACTION);
			sendIntent.putExtra("update", status);
			sendIntent.putExtra("current", current);
			// ���͹㲥 ������Activity����е�BroadcastReceiver���յ�
			sendBroadcast(sendIntent);
		}
	}

	private void prepareAndPlay(String music)
	{
		try
		{
			// ��ָ�������ļ�
			AssetFileDescriptor afd = am.openFd(music);
			mPlayer.reset();
			// ʹ��MediaPlayer����ָ���������ļ���
			mPlayer.setDataSource(afd.getFileDescriptor(),
				afd.getStartOffset(), afd.getLength());
			// ׼������
			mPlayer.prepare();
			// ����
			mPlayer.start();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}
