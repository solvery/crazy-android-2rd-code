/**
 *
 */
package org.crazyit.content;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
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
public class DictProvider extends ContentProvider
{
	private static UriMatcher matcher 
		= new UriMatcher(UriMatcher.NO_MATCH);
	private static final int WORDS = 1;
	private static final int WORD = 2;
	private MyDatabaseHelper dbOpenHelper;
	static
	{
		// ΪUriMatcherע������Uri
		matcher.addURI(Words.AUTHORITY, "words", WORDS);
		matcher.addURI(Words.AUTHORITY, "word/#", WORD);
	}

	// ��һ�ε��ø�DictProviderʱ��ϵͳ�ȴ���DictProvider���󣬲��ص��÷���
	@Override
	public boolean onCreate()
	{
		dbOpenHelper = new MyDatabaseHelper(this.getContext(),
			"myDict.db3", 1);
		return true;
	}
	
	// ����ָ��Uri������Ӧ�����ݵ�MIME����
	@Override
	public String getType(Uri uri)
	{
		switch (matcher.match(uri))
		{
			// ��������������Ƕ����¼
			case WORDS:
				return "vnd.android.cursor.dir/org.crazyit.dict";
			// ��������������ǵ����¼
			case WORD:
				return "vnd.android.cursor.item/org.crazyit.dict";
			default:
				throw new IllegalArgumentException("δ֪Uri:" + uri);
		}
	}

	// ��ѯ���ݵķ���
	@Override
	public Cursor query(Uri uri, String[] projection, String where,
		String[] whereArgs, String sortOrder)
	{
		SQLiteDatabase db = dbOpenHelper.getReadableDatabase();
		switch (matcher.match(uri))
		{
			// ���Uri�����������ȫ��������
			case WORDS:
				// ִ�в�ѯ
				return db.query("dict", projection, where,
					whereArgs, null, null, sortOrder);
			// ���Uri�����������ָ��������
			case WORD:
				// ���������ѯ�ļ�¼ID
				long id = ContentUris.parseId(uri);
				String whereClause = Words.Word._ID + "=" + id;
				// ���ԭ����where�Ӿ���ڣ�ƴ��where�Ӿ�
				if (where != null && !"".equals(where))
				{
					whereClause = whereClause + " and " + where;
				}
				return db.query("dict", projection, whereClause, whereArgs,
					null, null, sortOrder);
			default:
				throw new IllegalArgumentException("δ֪Uri:" + uri);
		}
	}
	
	// �������ݷ���
	@Override
	public Uri insert(Uri uri, ContentValues values)
	{
		// ������ݿ�ʵ��
		SQLiteDatabase db = dbOpenHelper.getReadableDatabase();
		switch (matcher.match(uri))
		{
			// ���Uri�����������ȫ��������
			case WORDS:
				// �������ݣ����ز����¼��ID
				long rowId = db.insert("dict", Words.Word._ID, values);
				// �������ɹ�����uri
				if (rowId > 0)
				{
					// �����е� Uri�ĺ���׷��ID
					Uri wordUri = ContentUris.withAppendedId(uri, rowId);
					// ֪ͨ�����Ѿ��ı�
					getContext().getContentResolver().notifyChange(wordUri, null);
					return wordUri;
				}
				break;
			default :
				throw new IllegalArgumentException("δ֪Uri:" + uri);
		}
		return null;
	}
	
	// �޸����ݵķ���
	@Override
	public int update(Uri uri, ContentValues values, String where,
		String[] whereArgs)
	{
		SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
		// ��¼���޸ĵļ�¼��
		int num = 0;
		switch (matcher.match(uri))
		{
			// ���Uri�����������ȫ��������
			case WORDS:
				num = db.update("dict", values, where, whereArgs);
				break;
			// ���Uri�����������ָ��������	
			case WORD:
				// ���������޸ĵļ�¼ID
				long id = ContentUris.parseId(uri);
				String whereClause = Words.Word._ID + "=" + id;
				// ���ԭ����where�Ӿ���ڣ�ƴ��where�Ӿ�
				if (where != null && !where.equals(""))
				{
					whereClause = whereClause + " and " + where;
				}
				num = db.update("dict", values, whereClause, whereArgs);
				break;
			default:
				throw new IllegalArgumentException("δ֪Uri:" + uri);
		}
		// ֪ͨ�����Ѿ��ı�
		getContext().getContentResolver().notifyChange(uri, null);
		return num;
	}
	
	// ɾ�����ݵķ���
	@Override
	public int delete(Uri uri, String where, String[] whereArgs)
	{
		SQLiteDatabase db = dbOpenHelper.getReadableDatabase();
		// ��¼��ɾ���ļ�¼��
		int num = 0;
		// ����uri����ƥ�䡣
		switch (matcher.match(uri))
		{
			// ���Uri�����������ȫ��������
			case WORDS:
				num = db.delete("dict", where, whereArgs);
				break;
			// ���Uri�����������ָ��������	
			case WORD:
				// ����������Ҫɾ���ļ�¼ID
				long id = ContentUris.parseId(uri);
				String whereClause = Words.Word._ID + "=" + id;
				// ���ԭ����where�Ӿ���ڣ�ƴ��where�Ӿ�
				if (where != null && !where.equals(""))
				{
					whereClause = whereClause + " and " + where;
				}
				num = db.delete("dict", whereClause, whereArgs);
				break;
			default:
				throw new IllegalArgumentException("δ֪Uri:" + uri);
		}
		// ֪ͨ�����Ѿ��ı�
		getContext().getContentResolver().notifyChange(uri, null);
		return num;
	}
}