package org.crazyit.map;					//���������

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Point;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.Projection;
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