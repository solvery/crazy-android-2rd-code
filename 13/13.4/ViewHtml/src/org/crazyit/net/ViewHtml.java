package org.crazyit.net;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;

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
public class ViewHtml extends Activity
{
	WebView show;
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		// ��ȡ�����е�WebView���
		show = (WebView) findViewById(R.id.show);
		StringBuilder sb = new StringBuilder();
		// ƴ��һ��HTML����
		sb.append("<html>");
		sb.append("<head>");
		sb.append("<title> ��ӭ�� </title>");
		sb.append("</head>");
		sb.append("<body>");
		sb.append("<h2> ��ӭ������<a href=\"http://www.crazyit.org\">"
			+ "���Java����</a></h2>");
		sb.append("</body>");
		sb.append("</html>");
		// ʹ�ü򵥵�loadData�����ᵼ�����룬������Android API��Bug
		// show.loadData(sb.toString() , "text/html" , "utf-8");
		// ���ء�����ʾHTML����
		show.loadDataWithBaseURL(null, sb.toString()
			, "text/html" , "utf-8", null);
	}
}