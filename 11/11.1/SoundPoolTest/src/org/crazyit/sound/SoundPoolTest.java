package org.crazyit.sound;

import java.util.HashMap;

import android.app.Activity;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

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
public class SoundPoolTest extends Activity implements OnClickListener
{
	Button bomb, shot, arrow;
	// ����һ��SoundPool
	SoundPool soundPool;
	HashMap<Integer, Integer> soundMap =
		new HashMap<Integer, Integer>();
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		bomb = (Button) findViewById(R.id.bomb);
		shot = (Button) findViewById(R.id.shot);
		arrow = (Button) findViewById(R.id.arrow);
		// ������������10����Ƶ������Ƶ��Ʒ��Ϊ5
		soundPool = new SoundPool(10
			, AudioManager.STREAM_SYSTEM, 5);  //��
		// load��������ָ����Ƶ�ļ��������������ص���ƵID��
		// �˴�ʹ��HashMap��������Щ��Ƶ��
		soundMap.put(1, soundPool.load(this, R.raw.bomb, 1));  //��
		soundMap.put(2, soundPool.load(this, R.raw.shot, 1));
		soundMap.put(3, soundPool.load(this, R.raw.arrow, 1));
		bomb.setOnClickListener(this);
		shot.setOnClickListener(this);
		arrow.setOnClickListener(this);
	}

	// ��дOnClickListener�������ӿڵķ���
	@Override
	public void onClick(View source)
	{
		// �ж��ĸ���ť������
		switch (source.getId())
		{
			case R.id.bomb:
				soundPool.play(soundMap.get(1), 1, 1, 0, 0, 1);  //��
				break;
			case R.id.shot:
				soundPool.play(soundMap.get(2), 1, 1, 0, 0, 1);
				break;
			case R.id.arrow:
				soundPool.play(soundMap.get(3), 1, 1, 0, 0, 1);
				break;
		}
	}
}