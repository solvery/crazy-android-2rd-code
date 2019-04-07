package org.crazyit.ui;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.RatingBar.OnRatingBarChangeListener;

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
public class RatingBarTest extends Activity
{
	ImageView image;
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		image = (ImageView) findViewById(R.id.image);
		RatingBar ratingBar = (RatingBar) findViewById(R.id.rating);

		ratingBar.setOnRatingBarChangeListener(new OnRatingBarChangeListener()
		{
			// ���϶����Ļ���λ�÷����ı�ʱ�����÷���
			@Override
			public void onRatingChanged(RatingBar arg0, float rating,
					boolean fromUser)
			{
				// ��̬�ı�ͼƬ��͸���ȣ�����255���Ǽ������������ֵ
				// 5�����Ǿʹ������ֵ255
				image.setAlpha((int) (rating * 255 / 5));
			}
		});
	}
}