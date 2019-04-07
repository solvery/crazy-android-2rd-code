package org.crazyit.map;					//���������

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Point;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.Projection;

public class PosOverLay extends Overlay
{
	// �����PosOverLay�����Ƶ�λͼ
	Bitmap posBitmap;
	// �����PosOverLay����λͼ��λ��
	GeoPoint gp;

	public PosOverLay(GeoPoint gp, Bitmap posBitmap)
	{
		super();
		this.gp = gp;
		this.posBitmap = posBitmap;
	}

	@Override
	public void draw(Canvas canvas, MapView mapView
		, boolean shadow)
	{
		if (!shadow)
		{
			// ��ȡMapView��Projection����
			Projection proj = mapView.getProjection();
			Point p = new Point();
			// ����ʵ�ĵ�������ת��Ϊ��Ļ�ϵ�����
			proj.toPixels(gp, p);
			// ��ָ��λ�û���ͼƬ
			canvas.drawBitmap(posBitmap, p.x - posBitmap.getWidth() / 2
				, p.y - posBitmap.getHeight(), null);
		}
	}
}