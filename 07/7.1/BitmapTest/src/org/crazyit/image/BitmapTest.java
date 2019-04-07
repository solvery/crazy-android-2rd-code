package org.crazyit.image;

import java.io.IOException;
import java.io.InputStream;

import android.app.Activity;
import android.content.res.AssetManager;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

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
public class BitmapTest extends Activity
{
	String[] images = null;
	AssetManager assets = null;
	int currentImg = 0;
	ImageView image;

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		image = (ImageView) findViewById(R.id.image);
		try
		{
			assets = getAssets();
			// ��ȡ/assets/Ŀ¼�������ļ�
			images = assets.list("");
			System.out.println(images.length);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		// ��ȡbn��ť
		final Button next = (Button) findViewById(R.id.next);
		// Ϊbn��ť���¼����������ü���������鿴��һ��ͼƬ
		next.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View sources)
			{
				// �����������Խ��
				if (currentImg >= images.length)
				{
					currentImg = 0;
				}
				// �ҵ���һ��ͼƬ�ļ�
				while (!images[currentImg].endsWith(".png")
						&& !images[currentImg].endsWith(".jpg")
						&& !images[currentImg].endsWith(".gif"))
				{
					currentImg++;
					// ����ѷ�������Խ��
					if (currentImg >= images.length)
					{
						currentImg = 0;
					}
				}
				InputStream assetFile = null;
				try
				{
					// ��ָ����Դ��Ӧ��������
					assetFile = assets.open(images[currentImg++]);
				}
				catch (IOException e)
				{
					e.printStackTrace();
				}
				BitmapDrawable bitmapDrawable = (BitmapDrawable) image
						.getDrawable();
				// ���ͼƬ��δ���գ���ǿ�ƻ��ո�ͼƬ
				if (bitmapDrawable != null
					&& !bitmapDrawable.getBitmap().isRecycled()) //��
				{
					bitmapDrawable.getBitmap().recycle();
				}
				// �ı�ImageView��ʾ��ͼƬ
				image.setImageBitmap(BitmapFactory
					.decodeStream(assetFile)); //��
			}
		});
	}
}