package org.crazyit.desktop;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.widget.RemoteViews;

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
public class DesktopApp extends AppWidgetProvider
{
	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager,
		int[] appWidgetIds)
	{
		// ����ָ�����沼���ļ�������RemoteViews����
		RemoteViews remoteViews = new RemoteViews(
			context.getPackageName(),
			R.layout.main);  //��
		// Ϊshow ImageView����ͼƬ
		remoteViews.setImageViewResource(R.id.show
			, R.drawable.logo);   //��
		// ��AppWidgetProvider����ʵ����װ��ComponentName����
		ComponentName componentName = new ComponentName(
			context, DesktopApp.class);  //��
		// ����AppWidgetManager��remoteViews��ӵ�ComponentName��
		appWidgetManager.updateAppWidget(componentName
			, remoteViews);  //��
	}
}