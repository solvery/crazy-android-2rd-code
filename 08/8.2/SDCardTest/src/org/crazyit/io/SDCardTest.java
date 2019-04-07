package org.crazyit.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
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
public class SDCardTest extends Activity
{
	final String FILE_NAME = "/crazyit.bin";

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
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
			// ����ֻ�������SD��������Ӧ�ó�����з���SD��Ȩ��
			if (Environment.getExternalStorageState().equals(
					Environment.MEDIA_MOUNTED))
			{
				// ��ȡSD����Ӧ�Ĵ洢Ŀ¼
				File sdCardDir = Environment.getExternalStorageDirectory();
				// ��ȡָ���ļ���Ӧ��������
				FileInputStream fis = new FileInputStream(
					sdCardDir.getCanonicalPath() + FILE_NAME);
				// ��ָ����������װ��BufferedReader
				BufferedReader br = new BufferedReader(new 
					InputStreamReader(fis));
				StringBuilder sb = new StringBuilder("");
				String line = null;
				// ѭ����ȡ�ļ�����				
				while ((line = br.readLine()) != null)
				{
					sb.append(line);
				}
				// �ر���Դ
				br.close();
				return sb.toString();
			}
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
			// ����ֻ�������SD��������Ӧ�ó�����з���SD��Ȩ��
			if (Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED))
			{
				// ��ȡSD����Ŀ¼
				File sdCardDir = Environment.getExternalStorageDirectory();
				File targetFile = new File(sdCardDir
					.getCanonicalPath() + FILE_NAME);
				// ��ָ���ļ����� RandomAccessFile����
				RandomAccessFile raf = new RandomAccessFile(
					targetFile, "rw");
				// ���ļ���¼ָ���ƶ������
				raf.seek(targetFile.length());
				// ����ļ�����
				raf.write(content.getBytes());
				// �ر�RandomAccessFile				
				raf.close();
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}