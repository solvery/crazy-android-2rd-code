package org.crazyit.ui;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;

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
public class MixView extends Activity
{
	// ����һ������ͼƬ������
	int[] images = new int[] { 
			R.drawable.java,
			R.drawable.ee,
			R.drawable.classic,
			R.drawable.ajax, 
			R.drawable.xml, };
	int currentImg = 0;

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		// ��ȡLinearLayout��������
		LinearLayout main = (LinearLayout) findViewById(R.id.root);
		// ���򴴽�ImageView���
		final ImageView image = new ImageView(this);
		// ��ImageView�����ӵ�LinearLayout����������
		main.addView(image);
		// ��ʼ��ʱ��ʾ��һ��ͼƬ
		image.setImageResource(images[0]);
		image.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				// �ı�ImageView����ʾ��ͼƬ
				image.setImageResource(images[++currentImg % images.length]);
			}
		});
	}
}