/**
 *
 */
package org.crazyit.manager;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.media.MediaPlayer;
import android.os.Bundle;

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
public class AlarmActivity extends Activity
{
	MediaPlayer alarmMusic;

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		// ����ָ�����֣���Ϊ֮����MediaPlayer����
		alarmMusic = MediaPlayer.create(this, R.raw.alarm);
		alarmMusic.setLooping(true);
		// ��������
		alarmMusic.start();
		// ����һ���Ի���
		new AlertDialog.Builder(AlarmActivity.this).setTitle("����")
			.setMessage("��������,Go��Go��Go��")
			.setPositiveButton("ȷ��", new OnClickListener()
			{
				@Override
				public void onClick(DialogInterface dialog, int which)
				{
					// ֹͣ����
					alarmMusic.stop();
					// ������Activity
					AlarmActivity.this.finish();
				}
			}).show();
	}
}
