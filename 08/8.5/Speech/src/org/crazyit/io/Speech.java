package org.crazyit.io;

import java.util.Locale;

import android.app.Activity;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnInitListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
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
public class Speech extends Activity
{
	TextToSpeech tts;
	EditText editText;
	Button speech;
	Button record;

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		// ��ʼ��TextToSpeech����
		tts = new TextToSpeech(this, new OnInitListener()
		{
			@Override
			public void onInit(int status)
			{
				// ���װ��TTS����ɹ�
				if (status == TextToSpeech.SUCCESS)
				{
					// ����ʹ����ʽӢ���ʶ�
					int result = tts.setLanguage(Locale.US);
					// �����֧�������õ�����
					if (result != TextToSpeech.LANG_COUNTRY_AVAILABLE
						&& result != TextToSpeech.LANG_AVAILABLE)
					{
						Toast.makeText(Speech.this
							, "TTS��ʱ��֧���������Ե��ʶ���", Toast.LENGTH_LONG)
							.show();
					}
				}
			}

		});
		editText = (EditText) findViewById(R.id.txt);
		speech = (Button) findViewById(R.id.speech);
		record = (Button) findViewById(R.id.record);
		speech.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View arg0)
			{
				// ִ���ʶ�
				tts.speak(editText.getText().toString(),
					TextToSpeech.QUEUE_ADD, null);
			}
		});
		record.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View arg0)
			{
				// ���ʶ��ı�����Ƶ��¼��ָ���ļ�
				tts.synthesizeToFile(editText.getText().toString()
					, null, "/mnt/sdcard/sound.wav");
				Toast.makeText(Speech.this, "������¼�ɹ���"
					, Toast.LENGTH_LONG).show();
			}
		});
	}

	@Override
	public void onDestroy()
	{
		// �ر�TextToSpeech����
		if (tts != null)
		{
			tts.shutdown();
		}
	}
}