package org.crazyit.resolver;

import org.crazyit.resolver.R;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
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
public class FirstResolver extends Activity
{
	ContentResolver contentResolver;
	Uri uri = Uri.parse("content://org.crazyit.providers.firstprovider/");

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		// 获取系统的ContentResolver对象
		contentResolver = getContentResolver();
	}

	public void query(View source)
	{
		// 调用ContentResolver的query()方法。
		// 实际返回的是该Uri对应的ContentProvider的query()的返回值
		Cursor c = contentResolver.query(uri, null
			, "query_where", null, null);
		Toast.makeText(this, "远程ContentProvide返回的Cursor为：" + c,
			Toast.LENGTH_LONG).show();
	}

	public void insert(View source)
	{
		ContentValues values = new ContentValues();
		values.put("name", "fkjava");
		// 调用ContentResolver的insert()方法。
		// 实际返回的是该Uri对应的ContentProvider的insert()的返回值
		Uri newUri = contentResolver.insert(uri, values);
		Toast.makeText(this, "远程ContentProvide新插入记录的Uri为："
			+ newUri, Toast.LENGTH_LONG).show();
	}

	public void update(View source)
	{
		ContentValues values = new ContentValues();
		values.put("name", "fkjava");
		// 调用ContentResolver的update()方法。
		// 实际返回的是该Uri对应的ContentProvider的update()的返回值
		int count = contentResolver.update(uri, values
			, "update_where", null);
		Toast.makeText(this, "远程ContentProvide更新记录数为："
			+ count, Toast.LENGTH_LONG).show();
	}

	public void delete(View source)
	{
		// 调用ContentResolver的delete()方法。
		// 实际返回的是该Uri对应的ContentProvider的delete()的返回值
		int count = contentResolver.delete(uri
			, "delete_where", null);
		Toast.makeText(this, "远程ContentProvide删除记录数为："
			+ count, Toast.LENGTH_LONG).show();
	}
}