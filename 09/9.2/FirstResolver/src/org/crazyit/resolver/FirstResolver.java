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
		// ��ȡϵͳ��ContentResolver����
		contentResolver = getContentResolver();
	}

	public void query(View source)
	{
		// ����ContentResolver��query()������
		// ʵ�ʷ��ص��Ǹ�Uri��Ӧ��ContentProvider��query()�ķ���ֵ
		Cursor c = contentResolver.query(uri, null
			, "query_where", null, null);
		Toast.makeText(this, "Զ��ContentProvide���ص�CursorΪ��" + c,
			Toast.LENGTH_LONG).show();
	}

	public void insert(View source)
	{
		ContentValues values = new ContentValues();
		values.put("name", "fkjava");
		// ����ContentResolver��insert()������
		// ʵ�ʷ��ص��Ǹ�Uri��Ӧ��ContentProvider��insert()�ķ���ֵ
		Uri newUri = contentResolver.insert(uri, values);
		Toast.makeText(this, "Զ��ContentProvide�²����¼��UriΪ��"
			+ newUri, Toast.LENGTH_LONG).show();
	}

	public void update(View source)
	{
		ContentValues values = new ContentValues();
		values.put("name", "fkjava");
		// ����ContentResolver��update()������
		// ʵ�ʷ��ص��Ǹ�Uri��Ӧ��ContentProvider��update()�ķ���ֵ
		int count = contentResolver.update(uri, values
			, "update_where", null);
		Toast.makeText(this, "Զ��ContentProvide���¼�¼��Ϊ��"
			+ count, Toast.LENGTH_LONG).show();
	}

	public void delete(View source)
	{
		// ����ContentResolver��delete()������
		// ʵ�ʷ��ص��Ǹ�Uri��Ӧ��ContentProvider��delete()�ķ���ֵ
		int count = contentResolver.delete(uri
			, "delete_where", null);
		Toast.makeText(this, "Զ��ContentProvideɾ����¼��Ϊ��"
			+ count, Toast.LENGTH_LONG).show();
	}
}