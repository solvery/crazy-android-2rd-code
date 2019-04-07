package org.crazyit.image;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;
import android.widget.ListView;

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
public class ListViewTween extends Activity
{
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		// ��ȡListView���
		ListView list = (ListView) findViewById(R.id.list);
		WindowManager windowManager = (WindowManager)
			getSystemService(WINDOW_SERVICE);
		Display display = windowManager.getDefaultDisplay();
		DisplayMetrics metrice = new DisplayMetrics();
		// ��ȡ��Ļ�Ŀ�͸�
		display.getMetrics(metrice);
		// ���ö�ListView���Ӧ�ö���
		list.setAnimation(new MyAnimation(metrice.xdpi / 2
			, metrice.ydpi / 2, 3500));
	}
}