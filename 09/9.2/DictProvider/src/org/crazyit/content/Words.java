/**
 *
 */
package org.crazyit.content;

import android.net.Uri;
import android.provider.BaseColumns;

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
public final class Words
{
	// �����ContentProvider��Authority
	public static final String AUTHORITY 
		= "org.crazyit.providers.dictprovider";

	// ����һ����̬�ڲ��࣬�����ContentProvider�����������е�����
	public static final class Word implements BaseColumns
	{
		// ����Content�����������3��������
		public final static String _ID = "_id";
		public final static String WORD = "word";
		public final static String DETAIL = "detail";
		// �����Content�ṩ���������Uri
		public final static Uri DICT_CONTENT_URI = Uri
			.parse("content://" + AUTHORITY + "/words");
		public final static Uri WORD_CONTENT_URI = Uri
			.parse("content://"	+ AUTHORITY + "/word");
	}
}
