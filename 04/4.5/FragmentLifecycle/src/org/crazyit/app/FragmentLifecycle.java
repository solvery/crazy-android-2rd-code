package org.crazyit.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

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
public class FragmentLifecycle extends Activity
{
	Button startActivity, addFragment, backFragment, replaceFragment ,finish;
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		startActivity = (Button) findViewById(R.id.startActivity);
		addFragment = (Button) findViewById(R.id.addFragment);
		backFragment = (Button) findViewById(R.id.backFragment);
		replaceFragment = (Button) findViewById(R.id.replaceFragment);
		finish = (Button) findViewById(R.id.finish);
		// 为startActivity按钮绑定事件监听器
		startActivity.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View source)
			{
				Intent intent = new Intent(FragmentLifecycle.this
						, SecondActivity.class);
				startActivity(intent);
			}
		});
		// 为addFragment按钮绑定事件监听器
		addFragment.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View source)
			{
				LifecycleFragment fragment = new LifecycleFragment();
				getFragmentManager().beginTransaction()
					.replace(R.id.container, fragment)
					.commit();
			}
		});
		// 为backFragment按钮绑定事件监听器
		backFragment.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View source)
			{
				SecondFragment fragment = new SecondFragment();
				getFragmentManager().beginTransaction()
					.replace(R.id.container, fragment)
					.addToBackStack("aa")// 将替换前的Fragment添加到Back栈
					.commit();
			}
		});		
		// 为replaceFragment按钮绑定事件监听器
		replaceFragment.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				SecondFragment fragment = new SecondFragment();
				getFragmentManager().beginTransaction()
					.replace(R.id.container, fragment)
					.commit();
			}
		});
		// 为finish按钮绑定事件监听器
		finish.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View source)
			{
				// 结束该Activity
				FragmentLifecycle.this.finish();
			}
		});		
	}
}