package org.crazyit.io;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.PrintStream;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

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
public class FileTest extends Activity
{
	final String FILE_NAME = "crazyit.bin";

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		System.out.println(new StringBuilder("a").append("b").append("c")
			.toString());
		// ��ȡ������ť
		Button read = (Button) findViewById(R.id.read);
		Button write = (Button) findViewById(R.id.write);
		// ��ȡ�����ı���
		final EditText edit1 = (EditText) findViewById(R.id.edit1);
		final EditText edit2 = (EditText) findViewById(R.id.edit2);
		// Ϊwrite��ť���¼�������
		write.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View source)
			{
				// ��edit1�е�����д���ļ���
				write(edit1.getText().toString());
				edit1.setText("");
			}
		});

		read.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				// ��ȡָ���ļ��е����ݣ�����ʾ����
				edit2.setText(read());
			}
		});
	}

	private String read()
	{
		try
		{
			// ���ļ�������
			FileInputStream fis = openFileInput(FILE_NAME);
			byte[] buff = new byte[1024];
			int hasRead = 0;
			StringBuilder sb = new StringBuilder("");
			// ��ȡ�ļ�����
			while ((hasRead = fis.read(buff)) > 0)
			{
				sb.append(new String(buff, 0, hasRead));
			}
			// �ر��ļ�������
			fis.close();
			return sb.toString();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}

	private void write(String content)
	{
		try
		{
			// ��׷��ģʽ���ļ������
			FileOutputStream fos = openFileOutput(FILE_NAME, MODE_APPEND);
			// ��FileOutputStream��װ��PrintStream
			PrintStream ps = new PrintStream(fos);
			// ����ļ�����
			ps.println(content);
			// �ر��ļ������
			ps.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}