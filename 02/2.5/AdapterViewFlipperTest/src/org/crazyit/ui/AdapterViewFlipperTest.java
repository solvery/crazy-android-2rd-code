package org.crazyit.ui;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterViewFlipper;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.app.Activity;

/**
 * Description:
 * <br/>website: <a href="http://www.crazyit.org">crazyit.org</a>
 * <br/>Copyright (C), 2001-2014, Yeeku.H.Lee
 * <br/>This program is protected by copyright laws.
 * <br/>Program Name:
 * <br/>Date:
 * @author Yeeku.H.Lee kongyeeku@163.com
 * @version 1.0
 */
public class AdapterViewFlipperTest extends Activity
{
	int[] imageIds = new int[]
	{
		R.drawable.shuangzi, R.drawable.shuangyu,
		R.drawable.chunv, R.drawable.tiancheng, R.drawable.tianxie,
		R.drawable.sheshou, R.drawable.juxie, R.drawable.shuiping,
		R.drawable.shizi, R.drawable.baiyang, R.drawable.jinniu,
		R.drawable.mojie };
	AdapterViewFlipper flipper;

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		flipper = (AdapterViewFlipper) findViewById(R.id.flipper);
		// ����һ��BaseAdapter���󣬸ö������ṩGallery����ʾ���б���
		BaseAdapter adapter = new BaseAdapter()
		{
			@Override
			public int getCount()
			{
				return imageIds.length;
			}

			@Override
			public Object getItem(int position)
			{
				return position;
			}

			@Override
			public long getItemId(int position)
			{
				return position;
			}

			// �÷����ķ��ص�View���Ǵ�����ÿ���б���
			@Override
			public View getView(int position, View convertView, ViewGroup parent)
			{
				// ����һ��ImageView
				ImageView imageView = new ImageView(AdapterViewFlipperTest.this);
				imageView.setImageResource(imageIds[position]);
				// ����ImageView����������
				imageView.setScaleType(ImageView.ScaleType.FIT_XY);
				// ΪimageView���ò��ֲ���
				imageView.setLayoutParams(new LayoutParams(
						LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
				return imageView;
			}
		};
		flipper.setAdapter(adapter);
	}

	public void prev(View source)
	{
		// ��ʾ��һ�����
		flipper.showPrevious();
		// ֹͣ�Զ�����
		flipper.stopFlipping();
	}

	public void next(View source)
	{
		// ��ʾ��һ�������
		flipper.showNext();
		// ֹͣ�Զ�����
		flipper.stopFlipping();
	}

	public void auto(View source)
	{
		// ��ʼ�Զ�����
		flipper.startFlipping();
	}
}
