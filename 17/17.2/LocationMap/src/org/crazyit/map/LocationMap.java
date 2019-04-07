package org.crazyit.map;

import java.util.List;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;
import android.widget.RadioGroup.OnCheckedChangeListener;

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
// ����̳�MapActivity
public class LocationMap extends MapActivity
{
	// ��������ϵĵĿ��ӻ��ؼ�
	Button locBn;
	RadioGroup mapType;
	MapView mv;
	EditText etLng , etLat;
	// ����MapController����
	MapController controller;
	Bitmap posBitmap;
	@Override
	protected void onCreate(Bundle status)
	{
		super.onCreate(status);
		setContentView(R.layout.main);
		posBitmap = BitmapFactory.decodeResource(getResources(),
			R.drawable.pos);
		// ��ý�����MapView����
		mv = (MapView) findViewById(R.id.mv);
		// ��ȡ�����������ı���
		etLng = (EditText) findViewById(R.id.lng);
		etLat = (EditText) findViewById(R.id.lat);
		// ������ʾ�Ŵ���С�Ŀ��ư�ť
		mv.setBuiltInZoomControls(true);
		// ����MapController����
		controller = mv.getController();  //��
		 // ���Button����
		locBn = (Button) findViewById(R.id.loc);
		locBn.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View source)
			{
				// ��ȡ�û�����ľ��ȡ�γ��ֵ
				String lng = etLng.getEditableText().toString().trim();
				String lat = etLat.getEditableText().toString().trim();
				if (lng.equals("") || lat.equals(""))
				{
					Toast.makeText(LocationMap.this, "��������Ч�ľ��ȡ�γ��!",
						Toast.LENGTH_LONG).show();
				}
				else
				{
					double dLong = Double.parseDouble(lng);
					double dLat = Double.parseDouble(lat);
					// ���÷�������MapView
					updateMapView(dLong , dLat);
				}
			}
		});
		// ������ť�ĵ����¼�
		locBn.performClick();
		// ���RadioGroup����
		mapType = (RadioGroup) findViewById(R.id.rg);
		// ΪRadioGroup��ѡ��״̬�ı���Ӽ�����
		mapType.setOnCheckedChangeListener(new OnCheckedChangeListener()
		{
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId)
			{
				switch(checkedId)
				{
					// �����ѡ����"������ͼ"�ĵ�ѡ��
					case R.id.normal:
						mv.setSatellite(false);
						break;
					// �����ѡ����"������ͼ"�ĵ�ѡ��
					case R.id.satellite:
						mv.setSatellite(true);
						break;
				}
			}
		});
	}

	@Override
	protected boolean isRouteDisplayed()
	{
		return true;
	}

	// ���ݾ��ȡ�γ�Ƚ�MapView��λ��ָ���ص�ķ���
	private void updateMapView(double lng, double lat)
	{
		// ����γ����Ϣ��װ��GeoPoint����
		GeoPoint gp = new GeoPoint((int) (lat * 1E6)
			, (int) (lng * 1E6));  //��
		// ������ʾ�Ŵ���С��ť
		mv.displayZoomControls(true);
		// ����ͼ�ƶ���ָ���ĵ���λ��
		controller.animateTo(gp);  //��
		// ���MapView��ԭ�е�Overlay����
		List<Overlay> ol = mv.getOverlays();
		// ���ԭ�е�Overlay����
		ol.clear();
		// ���һ���µ�OverLay����
		ol.add(new PosOverLay(gp, posBitmap));  //��
	}
}