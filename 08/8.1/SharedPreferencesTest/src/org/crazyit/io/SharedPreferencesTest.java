package org.crazyit.io;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
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
public class SharedPreferencesTest extends Activity
{
	SharedPreferences preferences;
	SharedPreferences.Editor editor;

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		// ��ȡֻ�ܱ���Ӧ�ó������д��SharedPreferences����
		preferences = getSharedPreferences("crazyit", MODE_WORLD_READABLE);
		editor = preferences.edit();
		Button read = (Button) findViewById(R.id.read);
		Button write = (Button) findViewById(R.id.write);
		read.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View arg0)
			{
				// ��ȡ�ַ�������
				String time = preferences.getString("time", null);
				// ��ȡint���͵�����
				int randNum = preferences.getInt("random", 0);
				String result = time == null ? "����ʱ��δд������" : "д��ʱ��Ϊ��" + time
						+ "\n�ϴ����ɵ������Ϊ��" + randNum;
				// ʹ��Toast��ʾ��Ϣ
				Toast.makeText(SharedPreferencesTest.this, result, 5000).show();
			}
		});
		write.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View arg0)
			{
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy��MM��dd�� "
						+ "hh:mm:ss");
				// ���뵱ǰʱ��
				editor.putString("time", sdf.format(new Date()));
				// ����һ�������
				editor.putInt("random", (int) (Math.random() * 100));
				// �ύ���д��������
				editor.commit();
			}
		});
	}
}