package org.crazyit.ui;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
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
public class ToastTest extends Activity
{
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		Button simple = (Button) findViewById(R.id.simple);
		// 为按钮的单击事件绑定事件监听器
		simple.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View source)
			{
				// 创建一个Toast提示信息
				Toast toast = Toast.makeText(ToastTest.this
					, "简单的提示信息"
				// 设置该Toast提示信息的持续时间
						, Toast.LENGTH_SHORT);
				toast.show();
			}
		});
		Button bn = (Button) findViewById(R.id.bn);
		// 为按钮的单击事件绑定事件监听器
		bn.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View source)
			{
				// 创建一个Toast提示信息
				Toast toast = new Toast(ToastTest.this);
				// 设置Toast的显示位置
				toast.setGravity(Gravity.CENTER, 0, 0);
				// 创建一个ImageView
				ImageView image = new ImageView(ToastTest.this);
				image.setImageResource(R.drawable.tools);
				// 创建一个LinearLayout容器
				LinearLayout ll = new LinearLayout(ToastTest.this);
				// 向LinearLayout中添加图片、原有的View
				ll.addView(image);
				// 创建一个ImageView
				TextView textView = new TextView(ToastTest.this);
				textView.setText("带图片的提示信");
				// 设置文本框内字体的大小和颜色
				textView.setTextSize(30);
				textView.setTextColor(Color.MAGENTA);
				ll.addView(textView);
				// 设置Toast显示自定义View				
				toast.setView(ll);
				// 设置Toast的显示时间
				toast.setDuration(Toast.LENGTH_LONG);
				toast.show();
			}
		});
	}
}