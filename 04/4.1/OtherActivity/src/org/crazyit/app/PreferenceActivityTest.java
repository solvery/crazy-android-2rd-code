/**
 *
 */
package org.crazyit.app;

import java.util.List;

import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceFragment;
import android.widget.Button;
import android.widget.Toast;

/**
 * Description:
 * <br/>网站: <a href="http://www.crazyit.org">疯狂Java联盟</a>
 * <br/>Copyright (C), 2001-2014, Yeeku.H.Lee
 * <br/>This program is protected by copyright laws.
 * <br/>Program Name:
 * <br/>Date:
 * @author  Yeeku.H.Lee kongyeeku@163.com
 * @version  1.0
 */
public class PreferenceActivityTest extends PreferenceActivity
{
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		// 该方法用于为该界面设置一个标题按钮
		if (hasHeaders())
		{
			Button button = new Button(this);
			button.setText("设置操作");
			// 将该按钮添加到该界面上
			setListFooter(button);
		}
	}
	// 重写该该方法，负责加载页面布局文件
	@Override
	public void onBuildHeaders(List<Header> target)
	{
		// 加载选项设置列表的布局文件
		loadHeadersFromResource(R.xml.preference_headers, target);
	}

	public static class Prefs1Fragment extends PreferenceFragment
	{
		@Override
		public void onCreate(Bundle savedInstanceState)
		{
			super.onCreate(savedInstanceState);
			addPreferencesFromResource(R.xml.preferences);
		}
	}
	public static class Prefs2Fragment extends PreferenceFragment
	{
		@Override
		public void onCreate(Bundle savedInstanceState)
		{
			super.onCreate(savedInstanceState);
			addPreferencesFromResource(R.xml.display_prefs);
			// 获取传入该Fragment的参数
			String website = getArguments().getString("website");
			Toast.makeText(getActivity()
				, "网站域名是：" + website , Toast.LENGTH_LONG).show();
		}
	}	
}
