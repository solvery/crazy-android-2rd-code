package org.crazyit.app.base;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.widget.LinearLayout;

/**
 * Description:
 * <br/>利嫋: <a href="http://www.crazyit.org">決髄Java選男</a>
 * <br/>Copyright (C), 2001-2014, Yeeku.H.Lee
 * <br/>This program is protected by copyright laws.
 * <br/>Program Name:
 * <br/>Date:
 * @author Yeeku.H.Lee kongyeeku@163.com
 * @version 1.0
 */
public abstract class FragmentActivity extends Activity
{
	private static final int ROOT_CONTAINER_ID = 0x90001;
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		LinearLayout layout = new LinearLayout(this);
		setContentView(layout);
		layout.setId(ROOT_CONTAINER_ID);
		getFragmentManager().beginTransaction()
			.replace(ROOT_CONTAINER_ID , getFragment())
			.commit();
	}
	protected abstract Fragment getFragment();
}
