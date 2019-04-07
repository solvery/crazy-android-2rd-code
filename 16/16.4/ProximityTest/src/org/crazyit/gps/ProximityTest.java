package org.crazyit.gps;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.os.Bundle;

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
public class ProximityTest extends Activity
{
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		// ��λ������
		String locService = Context.LOCATION_SERVICE;
		// ��λ���������ʵ��
		LocationManager locationManager;
		// ͨ��getSystemService�������LocationManagerʵ��
		locationManager = (LocationManager) getSystemService(locService);
		// ���������ӵĴ��¾��ȡ�γ��
		double longitude = 113.39;
		double latitude = 23.13;
		// ����뾶��5���
		float radius = 5000;
		// ����Intent
		Intent intent = new Intent(this, ProximityAlertReciever.class);
		// ��Intent��װ��PendingIntent
		PendingIntent pi = PendingIntent.getBroadcast(this, -1, intent, 0);
		// ����ٽ�����
		locationManager.addProximityAlert(latitude, longitude, radius, -1, pi);
	}
}