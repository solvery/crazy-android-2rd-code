package org.crazyit.net;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;

/**
 * Description:
 * <br/>Web Site: <a href="http://www.crazyit.org">CrazyIt.org</a> 
 * <br/>Copyright (C), 2001-2012, Yeeku.H.Lee
 * <br/>This program is protected by copyright laws.
 * <br/>Program Name:
 * <br/>Date:
 * @author  Yeeku.H.Lee kongyeeku@163.com
 * @version  1.0
 */
public class JsCallAndroid extends Activity 
{
	WebView myWebView;
	@Override
	public void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		myWebView = (WebView) findViewById(R.id.webview);
		// �˴�Ϊ�˼򻯱�̣�ʹ��fileЭ����ر���assetsĿ¼�µ�HTMLҳ��
		// �������Ҫ��Ҳ��ʹ��httpЭ�����Զ����վ��HTMLҳ�档
		myWebView.loadUrl("file:///android_asset/test.html");
		// ��ȡWebView�����ö���
		WebSettings webSettings = myWebView.getSettings();
		// ����JavaScript����
		webSettings.setJavaScriptEnabled(true);
		// ��MyObject����¶��JavaScript�ű�
		// ����test.htmlҳ���е�JavaScript����ͨ��myObj������MyObject�ķ���
		myWebView.addJavascriptInterface(new MyObject(this), "myObj");
	}
}