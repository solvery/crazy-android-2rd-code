package org.crazyit.ui;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;

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
public class ProgressDialogTest extends Activity
{
	final static int MAX_PROGRESS = 100;
	// �ó���ģ����䳤��Ϊ100������
	private int[] data = new int[50];
	// ��¼���ȶԻ������ɰٷֱ�
	int progressStatus = 0;
	int hasData = 0;
	ProgressDialog pd1,pd2;
	// ����һ��������µĽ��ȵ�Handler
	Handler handler = new Handler()
	{
		@Override
		public void handleMessage(Message msg)
		{
			// ������Ϣ���ɸó����͵ġ�
			if (msg.what == 0x123)
			{
				pd2.setProgress(progressStatus);
			}
		}
	};

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
	}

	public void showSpinner(View source)
	{
		// ���þ�̬������ʾ���ν�����
		ProgressDialog.show(this, "����ִ����"
			, "����ִ���У���ȴ�", false, true); //��
	}

	public void showIndeterminate(View source)
	{
		pd1 = new ProgressDialog(ProgressDialogTest.this);
		// ���öԻ���ı���
		pd1.setTitle("��������ִ����");
		// ���öԻ�����ʾ������
		pd1.setMessage("��������ִ���У�����ȴ�...");
		// ���öԻ������á�ȡ������ť�ر�
		pd1.setCancelable(true);
		// ���öԻ���Ľ��������
		pd1.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
		// ���öԻ���Ľ������Ƿ���ʾ����
		pd1.setIndeterminate(true);
		pd1.show(); //��
	}

	public void showProgress(View source)
	{
		// ������������ɽ�������Ϊ0
		progressStatus = 0;
		// ���¿�ʼ������顣
		hasData = 0;
		pd2 = new ProgressDialog(ProgressDialogTest.this);
		pd2.setMax(MAX_PROGRESS);
		// ���öԻ���ı���
		pd2.setTitle("������ɰٷֱ�");
		// ���öԻ��� ��ʾ������
		pd2.setMessage("��ʱ�������ɰٷֱ�");
		// ���öԻ������á�ȡ������ť�ر�
		pd2.setCancelable(false);
		// ���öԻ���Ľ��������
		pd2.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
		// ���öԻ���Ľ������Ƿ���ʾ����
		pd2.setIndeterminate(false);
		pd2.show(); //��

		new Thread()
		{
			public void run()
			{
				while (progressStatus < MAX_PROGRESS)
				{
					// ��ȡ��ʱ��������ɰٷֱ�
					progressStatus = MAX_PROGRESS
						* doWork() / data.length;
					// ���Ϳ���Ϣ��Handler
					handler.sendEmptyMessage(0x123);
				}
				// ��������Ѿ����
				if (progressStatus >= MAX_PROGRESS)
				{
					// �رնԻ���
					pd2.dismiss();
				}
			}
		}.start();
	}

	// ģ��һ����ʱ�Ĳ�����
	public int doWork()
	{
		// Ϊ����Ԫ�ظ�ֵ
		data[hasData++] = (int) (Math.random() * 100);
		try
		{
			Thread.sleep(100);
		}
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}
		return hasData;
	}
}