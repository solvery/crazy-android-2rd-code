package org.crazyit.desktop;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;
import android.widget.Toast;

/**
 * Description:
 * <br/>��վ: <a href="http://www.crazyit.org">���Java����</a>
 * <br/>Copyright (C), 2001-2014, Yeeku.H.Lee
 * <br/>This program is protected by copyright laws.
 * <br/>Program Name:
 * <br/>Date:
 * @author Yeeku.H.Lee kongyeeku@163.com
 * @version 1.0
 */
public class StackWidgetProvider extends AppWidgetProvider
{
	public static final String TOAST_ACTION
		= "org.crazyit.desktop.TOAST_ACTION";
	public static final String EXTRA_ITEM 
		= "org.crazyit.desktop.EXTRA_ITEM";

	@Override
	public void onUpdate(Context context,
		AppWidgetManager appWidgetManager, int[] appWidgetIds)
	{
		// ����RemoteViews���󣬼���/res/layoutĿ¼�µ�widget_layout.xml�ļ�
		RemoteViews rv = new RemoteViews(context.getPackageName(),
			R.layout.widget_layout);
		Intent intent = new Intent(context, StackWidgetService.class);
		// ʹ��intent����rv��stack_view�����StackView��
		rv.setRemoteAdapter(R.id.stack_view, intent);  //��
		// ���õ�StackWidgetService�ṩ���б���Ϊ��ʱ��ֱ����ʾempty_view���
		rv.setEmptyView(R.id.stack_view, R.id.empty_view);
		// ��������StackWidgetProvider�������ΪBroadcastReceiver����Intent
		Intent toastIntent = new Intent(context,
			StackWidgetProvider.class);
		// Ϊ��Intent����Action����
		toastIntent.setAction(StackWidgetProvider.TOAST_ACTION);
		// ��Intent��װ��PendingIntent
		PendingIntent toastPendingIntent = PendingIntent
			.getBroadcast(context, 0, toastIntent,
				PendingIntent.FLAG_UPDATE_CURRENT);
		// ��PendingIntent��stack_view���й���
		rv.setPendingIntentTemplate(R.id.stack_view,
			toastPendingIntent);
		// ʹ��AppWidgetManagerͨ��RemteViews����AppWidgetProvider
		appWidgetManager.updateAppWidget(
			new ComponentName(context, StackWidgetProvider.class), rv); //��
		super.onUpdate(context, appWidgetManager, appWidgetIds);
	}
	@Override
	public void onDeleted(Context context, int[] appWidgetIds)
	{
		super.onDeleted(context, appWidgetIds);
	}

	@Override
	public void onDisabled(Context context)
	{
		super.onDisabled(context);
	}

	@Override
	public void onEnabled(Context context)
	{
		super.onEnabled(context);
	}
	// ��д�÷����������������BroadcastReceiverʹ��
	@Override
	public void onReceive(Context context, Intent intent)
	{
		if (intent.getAction().equals(TOAST_ACTION))
		{
			// ��ȡIntent�е�����
			int viewIndex = intent.getIntExtra(EXTRA_ITEM, 0);
			// ��ʾToast��ʾ
			Toast.makeText(context, "����ڡ�" + viewIndex + "�����б���",
				Toast.LENGTH_SHORT).show();
		}
		super.onReceive(context, intent);
	}	
}
