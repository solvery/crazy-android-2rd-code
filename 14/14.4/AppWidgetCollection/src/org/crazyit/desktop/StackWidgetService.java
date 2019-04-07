package org.crazyit.desktop;

import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

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
public class StackWidgetService extends RemoteViewsService
{
	// ��д�÷������÷�������һ��RemoteViewsFactory����
	// RemoteViewsFactory����ĵ�����������Adapter��
	// ������ΪRemoteView��ָ������ṩ����б��
	@Override
	public RemoteViewsFactory onGetViewFactory(Intent intent)
	{
		return new StackRemoteViewsFactory(this.getApplicationContext(),
			intent);  //��
	}
	class StackRemoteViewsFactory implements
	RemoteViewsService.RemoteViewsFactory
	{
		// ����һ�������������������ɵĶ���б���
		private int[] items = null;
		private Context mContext;
		public StackRemoteViewsFactory(Context context, Intent intent)
		{
			mContext = context;
		}
		@Override
		public void onCreate()
		{
			// ��ʼ��items����
			items = new int[] { R.drawable.bomb5, R.drawable.bomb6,
				R.drawable.bomb7, R.drawable.bomb8, R.drawable.bomb9,
				R.drawable.bomb10, R.drawable.bomb11, R.drawable.bomb12,
				R.drawable.bomb13, R.drawable.bomb14, R.drawable.bomb15,
				R.drawable.bomb16
			};
		}
		@Override
		public void onDestroy()
		{
			items = null;
		}
		// �÷����ķ���ֵ���Ƹö���������ٸ��б���
		@Override
		public int getCount()
		{
			return items.length;
		}
		// �÷����ķ���ֵ���Ƹ�λ������ʾ��RemoteViews
		@Override
		public RemoteViews getViewAt(int position)
		{
			// ����RemoteViews���󣬼���/res/layoutĿ¼��widget_item.xml�ļ�
			RemoteViews rv = new RemoteViews(mContext.getPackageName(),
				R.layout.widget_item);
			// ����widget_item.xml�����ļ��е�widget_item���
			rv.setImageViewResource(R.id.widget_item,
				items[position]);
			// ����Intent�����ڴ�������
			Intent fillInIntent = new Intent();
			fillInIntent.putExtra(StackWidgetProvider.EXTRA_ITEM, position);
			// ���õ�������RemoteViewsʱ����fillInIntent����������
			rv.setOnClickFillInIntent(R.id.widget_item, fillInIntent);
			// �˴�ʹ�����߳���ͣ0.5����ģ����ظ����
			try
			{
				System.out.println("���ء�" + position + "��λ�õ����");
				Thread.sleep(500);
			}
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}
			return rv;
		}
		@Override
		public RemoteViews getLoadingView()
		{
			return null;
		}
		@Override
		public int getViewTypeCount()
		{
			return 1;
		}
		@Override
		public long getItemId(int position)
		{
			return position;
		}
		@Override
		public boolean hasStableIds()
		{
			return true;
		}
		@Override
		public void onDataSetChanged()
		{
		}
	}	
}

