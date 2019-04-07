package org.crazyit.intent;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
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
public class DataTypeAttr extends Activity
{
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
	}
	public void scheme(View source)
	{
		Intent intent = new Intent();
		// ֻ����Intent��Data����
		intent.setData(Uri.parse("lee://www.crazyit.org:1234/test"));
		startActivity(intent);
	}
	public void schemeHostPort(View source)
	{
		Intent intent = new Intent();
		// ֻ����Intent��Data����
		intent.setData(Uri.parse("lee://www.fkjava.org:8888/test"));
		startActivity(intent);	
	}
	public void schemeHostPath(View source)
	{
		Intent intent = new Intent();
		// ֻ����Intent��Data����
		intent.setData(Uri.parse("lee://www.fkjava.org:1234/mypath"));
		startActivity(intent);	
	}
	public void schemeHostPortPath(View source)
	{
		Intent intent = new Intent();
		// ֻ����Intent��Data����
		intent.setData(Uri.parse("lee://www.fkjava.org:8888/mypath"));
		startActivity(intent);
	}
	public void schemeHostPortPathType(View source)
	{
		Intent intent = new Intent();
		// ͬʱ����Intent��Data��Type����
		intent.setDataAndType(Uri.parse("lee://www.fkjava.org:8888/mypath")
			, "abc/xyz");
		startActivity(intent);
	}
}
