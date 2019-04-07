package org.crazyit.intent;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import android.app.Activity;
import android.content.Intent;

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
public class DataTypeOverride extends Activity
{
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
	}

	public void overrideType(View source)
	{
		Intent intent = new Intent();
		// 先为Intent设置Type属性
		intent.setType("abc/xyz");
		// 再为Intent设置Data属性，覆盖Type属性
		intent.setData(Uri.parse("lee://www.fkjava.org:8888/test"));
		Toast.makeText(this, intent.toString(), Toast.LENGTH_LONG).show();
	}

	public void overrideData(View source)
	{
		Intent intent = new Intent();
		// 先为Intent设置Data属性
		intent.setData(Uri.parse("lee://www.fkjava.org:8888/mypath"));
		// 再为Intent设置Type属性，覆盖Data属性
		intent.setType("abc/xyz");
		Toast.makeText(this, intent.toString(), Toast.LENGTH_LONG).show();
	}

	public void dataAndType(View source)
	{
		Intent intent = new Intent();
		// 同时设置Intent的Data、Type属性
		intent.setDataAndType(Uri.parse("lee://www.fkjava.org:8888/mypath"),
				"abc/xyz");
		Toast.makeText(this, intent.toString(), Toast.LENGTH_LONG).show();
	}
}
