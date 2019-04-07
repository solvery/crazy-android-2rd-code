package org.crazyit.map;

import java.util.List;

import org.crazyit.map.PosOverLay;
import org.crazyit.map.R;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

/**
 * Description: <br/>
 * site: <a href="http://www.crazyit.org">crazyit.org</a> <br/>
 * Copyright (C), 2001-2012, Yeeku.H.Lee <br/>
 * This program is protected by copyright laws. <br/>
 * Program Name: <br/>
 * Date:
 * 
 * @author Yeeku.H.Lee kongyeeku@163.com
 * @version 1.0
 */
public class Navigation extends MapActivity
{
	MapView mv;
	MapController controller;
	Bitmap posBitmap;
	LocationManager locManager;

	@Override
	protected void onCreate(Bundle status)
	{
		super.onCreate(status);
		setContentView(R.layout.main);
		posBitmap = BitmapFactory.decodeResource(getResources(),
			R.drawable.pos);
		// ��ý�����MapView����
		mv = (MapView) findViewById(R.id.mv);
		// ������ʾ�Ŵ���С�İ�ť
		mv.setBuiltInZoomControls(true);
		// ����MapController����
		controller = mv.getController();
		// ��ȡLocationManager����
		locManager = (LocationManager) getSystemService(
			Context.LOCATION_SERVICE);
		// ����ÿ30���ȡһ��GPS�Ķ�λ��Ϣ
		locManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
			30000, 10, new LocationListener() //��
			{
				@Override
				public void onLocationChanged(Location location)
				{
					// ��GPS��λ��Ϣ�����ı�ʱ������λ��
					updateMapView(location); //��
				}

				@Override
				public void onProviderDisabled(String provider)
				{
				}

				@Override
				public void onProviderEnabled(String provider)
				{
					// ��GPS LocationProvider����ʱ������λ��
					updateMapView(locManager
						.getLastKnownLocation(provider));
				}

				@Override
				public void onStatusChanged(String provider, int status,
					Bundle extras)
				{
				}
			});
	}

	@Override
	protected boolean isRouteDisplayed()
	{
		return true;
	}

	// ����Location������MapView
	private void updateMapView(Location location)
	{
		// ��Location�����еľ���γ����Ϣ��װ��GeoPoint����
		GeoPoint gp = new GeoPoint((int) (location.getLatitude() * 1E6),
			(int) (location.getLongitude() * 1E6));
		// ������ʾ�Ŵ���С��ť
		mv.displayZoomControls(true);
		// ����ͼ�ƶ���ָ���ĵ���λ��
		controller.animateTo(gp);
		// ���MapView��ԭ�е�Overlay����
		List<Overlay> ol = mv.getOverlays();
		// ���ԭ�е�Overlay����
		ol.clear();
		// ���һ���µ�OverLay����
		ol.add(new PosOverLay(gp, posBitmap));
	}
}
