package org.crazyit.app;

import android.app.Activity;
import android.os.Bundle;

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
public class SelectBookActivity extends Activity implements
		BookListFragment.Callbacks
{
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		// ����/res/layoutĿ¼�µ�activity_book_twopane.xml�����ļ�
		setContentView(R.layout.activity_book_twopane);
	}
	// ʵ��Callbacks�ӿڱ���ʵ�ֵķ���
	@Override
	public void onItemSelected(Integer id)
	{
		// ����Bundle��׼����Fragment�������
		Bundle arguments = new Bundle();
		arguments.putInt(BookDetailFragment.ITEM_ID, id);
		// ����BookDetailFragment����
		BookDetailFragment fragment = new BookDetailFragment();
		// ��Fragment�������
		fragment.setArguments(arguments);
		// ʹ��fragment�滻book_detail_container������ǰ��ʾ��Fragment
		getFragmentManager().beginTransaction()
			.replace(R.id.book_detail_container, fragment)
			.commit();  //��
	}
}
