package org.crazyit.gps;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.EditText;

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
public class LocationTest extends Activity
{
	// ����LocationManager����
	LocationManager locManager;
	// �����������е�EditText���
	EditText show;
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		// ��ȡ��������ϵ�EditText���
		show = (EditText) findViewById(R.id.show);
		// ����LocationManager����
		locManager = (LocationManager) getSystemService
			(Context.LOCATION_SERVICE);
		// ��GPS��ȡ���������Ķ�λ��Ϣ
		Location location = locManager.getLastKnownLocation(
			LocationManager.GPS_PROVIDER);
		// ʹ��location����EditText����ʾ
		updateView(location);
		// ����ÿ3���ȡһ��GPS�Ķ�λ��Ϣ
		locManager.requestLocationUpdates(LocationManager.GPS_PROVIDER
			, 3000, 8, new LocationListener()  //��
		{
			@Override
			public void onLocationChanged(Location location)
			{
				// ��GPS��λ��Ϣ�����ı�ʱ������λ��
				updateView(location);
			}

			@Override
			public void onProviderDisabled(String provider)
			{
				updateView(null);
			}

			@Override
			public void onProviderEnabled(String provider)
			{
				// ��GPS LocationProvider����ʱ������λ��
				updateView(locManager
					.getLastKnownLocation(provider));
			}

			@Override
			public void onStatusChanged(String provider, int status,
				Bundle extras)
			{
			}
		});
	}

	// ����EditText����ʾ������
	public void updateView(Location newLocation)
	{
		if (newLocation != null)
		{
			StringBuilder sb = new StringBuilder();
			sb.append("ʵʱ��λ����Ϣ��\n");
			sb.append("���ȣ�");
			sb.append(newLocation.getLongitude());
			sb.append("\nγ�ȣ�");
			sb.append(newLocation.getLatitude());
			sb.append("\n�߶ȣ�");
			sb.append(newLocation.getAltitude());
			sb.append("\n�ٶȣ�");
			sb.append(newLocation.getSpeed());
			sb.append("\n����");
			sb.append(newLocation.getBearing());
			show.setText(sb.toString());
		}
		else
		{
			// ��������Location����Ϊ�������EditText
			show.setText("");
		}
	}
}