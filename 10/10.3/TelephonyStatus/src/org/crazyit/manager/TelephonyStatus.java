package org.crazyit.manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.widget.ListView;
import android.widget.SimpleAdapter;

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
public class TelephonyStatus extends Activity
{
	ListView showView;
	// ��������״̬��������
	String[] statusNames;
	// ���������ֻ�״̬�ļ���
	ArrayList<String> statusValues = new ArrayList<String>();

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		// ��ȡϵͳ��TelephonyManager����
		TelephonyManager tManager = (TelephonyManager)
			getSystemService(Context.TELEPHONY_SERVICE);
		// ��ȡ����״̬���Ƶ�����
		statusNames = getResources().getStringArray(R.array.statusNames);
		// ��ȡ����SIM��״̬������
		String[] simState = getResources()
			.getStringArray(R.array.simState);
		// ��ȡ����绰�������͵�����
		String[] phoneType = getResources().getStringArray(
			R.array.phoneType);
		// ��ȡ�豸���
		statusValues.add(tManager.getDeviceId());
		// ��ȡϵͳƽ̨�İ汾
		statusValues.add(tManager.getDeviceSoftwareVersion()
			!= null ? tManager.getDeviceSoftwareVersion() : "δ֪");
		// ��ȡ������Ӫ�̴���
		statusValues.add(tManager.getNetworkOperator());
		// ��ȡ������Ӫ������
		statusValues.add(tManager.getNetworkOperatorName());
		// ��ȡ�ֻ���������
		statusValues.add(phoneType[tManager.getPhoneType()]);
		// ��ȡ�豸����λ��
		statusValues.add(tManager.getCellLocation() != null ? tManager
			.getCellLocation().toString() : "δ֪λ��");
		// ��ȡSIM���Ĺ���
		statusValues.add(tManager.getSimCountryIso());
		// ��ȡSIM�����к�
		statusValues.add(tManager.getSimSerialNumber());
		// ��ȡSIM��״̬
		statusValues.add(simState[tManager.getSimState()]);
		// ���ListView����
		showView = (ListView) findViewById(R.id.show);
		ArrayList<Map<String, String>> status = 
			new ArrayList<Map<String, String>>();
		// ����statusValues���ϣ���statusNames��statusValues
		// �����ݷ�װ��List<Map<String , String>>������
		for (int i = 0; i < statusValues.size(); i++)
		{
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("name", statusNames[i]);
			map.put("value", statusValues.get(i));
			status.add(map);
		}
		// ʹ��SimpleAdapter��װList����
		SimpleAdapter adapter = new SimpleAdapter(this, status,
			R.layout.line, new String[] { "name", "value" }
			, new int[] { R.id.name, R.id.value });
		// ΪListView����Adapter
		showView.setAdapter(adapter);
	}
}