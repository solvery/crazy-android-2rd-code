/**
 *
 */
package org.crazyit.content;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;

/**
 * Description:
 * <br/>��վ: <a href="http://www.crazyit.org">���Java����</a>
 * <br/>Copyright (C), 2001-2014, Yeeku.H.Lee
 * <br/>This program is protected by copyright laws.
 * <br/>Program Name:
 * <br/>Date:
 * @author  Yeeku.H.Lee kongyeeku@163.com
 * @version  1.0
 */
public class FirstProvider extends ContentProvider
{
	// ��һ�δ�����ContentProviderʱ���ø÷���
	@Override
	public boolean onCreate()
	{
		System.out.println("===onCreate����������===");
		return true;
	}

	// �÷����ķ���ֵ�����˸�ContentProvider���ṩ���ݵ�MIME����
	@Override
	public String getType(Uri uri)
	{
		System.out.println("~~getType����������~~");
		return null;
	}

	// ʵ�ֲ�ѯ�������÷���Ӧ�÷��ز�ѯ�õ���Cursor
	@Override
	public Cursor query(Uri uri, String[] projection, String where,
		String[] whereArgs, String sortOrder)
	{
		System.out.println(uri + "===query����������===");
		System.out.println("where����Ϊ��" + where);
		return null;
	}

	// ʵ�ֲ���ķ������÷���Ӧ���²���ļ�¼��Uri
	@Override
	public Uri insert(Uri uri, ContentValues values)
	{
		System.out.println(uri + "===insert����������===");
		System.out.println("values����Ϊ��" + values);
		return null;
	}

	// ʵ��ɾ���������÷���Ӧ�÷��ر�ɾ���ļ�¼����
	@Override
	public int delete(Uri uri, String where, String[] whereArgs)
	{
		System.out.println(uri + "===delete����������===");
		System.out.println("where����Ϊ��" + where);
		return 0;
	}

	// ʵ��ɾ���������÷���Ӧ�÷��ر����µļ�¼����
	@Override
	public int update(Uri uri, ContentValues values, String where,
		String[] whereArgs)
	{
		System.out.println(uri + "===update����������===");
		System.out.println("where����Ϊ��"
			+ where + ",values����Ϊ��" + values);
		return 0;
	}
}