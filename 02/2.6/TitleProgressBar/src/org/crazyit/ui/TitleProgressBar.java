package org.crazyit.ui;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
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
public class TitleProgressBar extends Activity
{
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
//		//���ô���������������ʾ���ȵĽ�����
//		requestWindowFeature(Window.FEATURE_PROGRESS);  //��
		//���ô������������ò���ʾ���ȵĽ�����
		requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS); //��
		setContentView(R.layout.main);
		Button bn1 = (Button)findViewById(R.id.bn1);
		Button bn2 = (Button)findViewById(R.id.bn2);
		bn1.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View source)
			{
				//��ʾ�������ȵĽ�������
				setProgressBarIndeterminateVisibility(true);
				//��ʾ�����ȵĽ�������
				setProgressBarVisibility(true);
				//���ý������Ľ���
				setProgress(4500);

			}
		});
		bn2.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View source)
			{
				//���ز������ȵĽ�������
				setProgressBarIndeterminateVisibility(false);
				//���ش����ȵĽ�������
				setProgressBarVisibility(false);
			}
		});
	}
}