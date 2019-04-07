package org.crazyit.res;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

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
public class I18NTest extends Activity
{
	TextView tvShow;
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		tvShow = (TextView) findViewById(R.id.show);
		// 设置文本框所显示的文本
		tvShow.setText(R.string.msg);
	}
}