package org.crazyit.app;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Description:
 * <br/>网站: <a href="http://www.crazyit.org">疯狂Java联盟</a>
 * <br/>Copyright (C), 2001-2014, Yeeku.H.Lee
 * <br/>This program is protected by copyright laws.
 * <br/>Program Name:
 * <br/>Date:
 * @author Yeeku.H.Lee kongyeeku@163.com
 * @version 1.0
 */
public class SecondFragment extends Fragment
{
	@Override
	public View onCreateView(LayoutInflater inflater
		, ViewGroup container, Bundle data)
	{
		TextView tv = new TextView(getActivity());
		tv.setGravity(Gravity.CENTER_HORIZONTAL);
		tv.setText("第二个Fragment");
		tv.setTextSize(40);
		return tv;
	}
}
