/**
 *
 */
package org.crazyit.manager;

import android.app.Service;
import android.app.WallpaperManager;
import android.content.Intent;
import android.os.IBinder;

/**
 * Description:
 * <br/>��վ: <a href="http://www.crazyit.org">���Java����</a>
 * <br/>Copyright (C), 2001-2014, Yeeku.H.Lee
 * <br/>This program is protected by copyright laws.
 * <br/>Program Name:
 * <br/>Date:
 * @author  Yeeku.H.Lee kongyeeku@163.com
 * @version  1.0
 */
public class ChangeService extends Service
{
	// ���嶨ʱ�����ı�ֽ��Դ
	int[] wallpapers = new int[]{
		R.drawable.shuangta,
		R.drawable.lijiang,
		R.drawable.qiao,
		R.drawable.shui
	};
	// ����ϵͳ�ı�ֽ�������
	WallpaperManager wManager;
	// ���嵱ǰ����ʾ�ı�ֽ
	int current = 0;
	@Override
	public int onStartCommand(Intent intent, int flags, int startId)
	{
		// ����������һ�ţ�ϵͳ���¿�ʼ
		if(current >= 4)
			current = 0;
		try
		{
			// �ı��ֽ
			wManager.setResource(wallpapers[current++]);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return START_STICKY;
	}

	@Override
	public void onCreate()
	{
		super.onCreate();
		// ��ʼ��WallpaperManager
		wManager = WallpaperManager.getInstance(this);
	}

	@Override
	public IBinder onBind(Intent intent)
	{
		return null;
	}
}
