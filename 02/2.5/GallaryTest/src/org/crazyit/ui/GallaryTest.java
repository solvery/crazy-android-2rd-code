package org.crazyit.ui;

import android.app.Activity;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.Gallery.LayoutParams;

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
public class GallaryTest extends Activity
{
	int[] imageIds = new int[]
	{
		R.drawable.shuangzi, R.drawable.shuangyu,
		R.drawable.chunv, R.drawable.tiancheng, R.drawable.tianxie,
		R.drawable.sheshou, R.drawable.juxie, R.drawable.shuiping,
		R.drawable.shizi, R.drawable.baiyang, R.drawable.jinniu,
		R.drawable.mojie };
	Gallery gallery;
	ImageView imageView;
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		gallery = (Gallery) findViewById(R.id.gallery);
		// 获取显示图片的ImageView对象
		imageView = (ImageView) findViewById(R.id.imageView);
		// 创建一个BaseAdapter对象，该对象负责提供Gallery所显示的列表项
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

			// 该方法的返回的View就是代表了每个列表项
			@Override
			public View getView(int position, View convertView, ViewGroup parent)
			{
				// 创建一个ImageView
				ImageView imageView = new ImageView(GallaryTest.this);
				imageView.setImageResource(imageIds[position]);
				// 设置ImageView的缩放类型
				imageView.setScaleType(ImageView.ScaleType.FIT_XY);
				// 为imageView设置布局参数
				imageView.setLayoutParams(new Gallery.LayoutParams(75, 100));
				TypedArray typedArray = obtainStyledAttributes(
						R.styleable.Gallery);
				imageView.setBackgroundResource(typedArray.getResourceId(
						R.styleable.Gallery_android_galleryItemBackground, 0));
				return imageView;
			}
		};
		gallery.setAdapter(adapter);
		gallery.setOnItemSelectedListener(new OnItemSelectedListener()
		{
			// 当Gallery选中项发生改变时触发该方法
			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id)
			{
				imageView.setImageResource(imageIds[position]);
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent)
			{
			}
		});
	}
}