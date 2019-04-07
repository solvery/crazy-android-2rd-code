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
		// 加载/res/layout目录下的activity_book_twopane.xml布局文件
		setContentView(R.layout.activity_book_twopane);
	}
	// 实现Callbacks接口必须实现的方法
	@Override
	public void onItemSelected(Integer id)
	{
		// 创建Bundle，准备向Fragment传入参数
		Bundle arguments = new Bundle();
		arguments.putInt(BookDetailFragment.ITEM_ID, id);
		// 创建BookDetailFragment对象
		BookDetailFragment fragment = new BookDetailFragment();
		// 向Fragment传入参数
		fragment.setArguments(arguments);
		// 使用fragment替换book_detail_container容器当前显示的Fragment
		getFragmentManager().beginTransaction()
			.replace(R.id.book_detail_container, fragment)
			.commit();  //①
	}
}
