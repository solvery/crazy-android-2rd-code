package org.crazyit.gps;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.location.Criteria;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.crazyit.gps.R;


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
public class FreeProvidersTest extends Activity
{
	ListView providers;
	LocationManager lm;
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		providers = (ListView) findViewById(R.id.providers);
		// ��ȡϵͳ��LocationManager����
		lm = (LocationManager)getSystemService(
			Context.LOCATION_SERVICE);
		// ����һ��LocationProvider�Ĺ�������
		Criteria cri = new Criteria();
		// ����Ҫ��LocationProvider��������ѵġ�
		cri.setCostAllowed(false);
		// ����Ҫ��LocationProvider���ṩ�߶���Ϣ
		cri.setAltitudeRequired(true);
		// ����Ҫ��LocationProvider���ṩ������Ϣ
		cri.setBearingRequired(true);
		// ��ȡϵͳ���и���������LocationProvider������
		List<String> providerNames = lm.getProviders(cri , false);
		System.out.println(providerNames.size());
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(
			this,
			android.R.layout.simple_list_item_1,
			providerNames);
		// ʹ��ListView����ʾ���п��õ�LocationProvider
		providers.setAdapter(adapter);
	}
}