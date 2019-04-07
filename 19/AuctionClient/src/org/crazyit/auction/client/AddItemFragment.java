package org.crazyit.auction.client;

import java.util.HashMap;
import java.util.Map;

import org.crazyit.auction.client.util.DialogUtil;
import org.crazyit.auction.client.util.HttpUtil;
import org.json.JSONArray;
import org.json.JSONObject;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

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
public class AddItemFragment extends Fragment
{
	// ����������ı���
	EditText itemName, itemDesc,itemRemark,initPrice;
	Spinner itemKind , availTime;
	// ���������������ť
	Button bnAdd, bnCancel;
	@Override
	public View onCreateView(LayoutInflater inflater
		, ViewGroup container, Bundle savedInstanceState)
	{
		View rootView = inflater.inflate(R.layout.add_item
			, container , false);
		// ��ȡ�����е��ı���
		itemName = (EditText) rootView.findViewById(R.id.itemName);
		itemDesc = (EditText) rootView.findViewById(R.id.itemDesc);
		itemRemark = (EditText) rootView.findViewById(R.id.itemRemark);
		initPrice = (EditText) rootView.findViewById(R.id.initPrice);
		itemKind = (Spinner) rootView.findViewById(R.id.itemKind);
		availTime = (Spinner) rootView.findViewById(R.id.availTime);
		// ���巢������ĵ�ַ
		String url = HttpUtil.BASE_URL + "viewKind.jsp";
		JSONArray jsonArray = null;
		try
		{
			// ��ȡϵͳ�����е���Ʒ����
			// ��ִ��URL�������󣬲��ѷ�������Ӧ��װ��JSONArray
			jsonArray = new JSONArray(HttpUtil.getRequest(url));  //��
		}
		catch (Exception e1)
		{
			e1.printStackTrace();
		}
		// ��JSONArray��װ��Adapter
		JSONArrayAdapter adapter = new JSONArrayAdapter(
			getActivity() , jsonArray , "kindName" , false);
		// ��ʾ��Ʒ�����б�
		itemKind.setAdapter(adapter);
		// ��ȡ�����е�������ť
		bnAdd = (Button) rootView.findViewById(R.id.bnAdd);
		bnCancel = (Button) rootView.findViewById(R.id.bnCancel);
		// Ϊȡ����ť�ĵ����¼����¼�������
		bnCancel.setOnClickListener(new HomeListener(getActivity()));
		bnAdd.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				// ִ������У��
				if (validate())
				{
					// ��ȡ�û��������Ʒ������Ʒ��������Ϣ
					String name = itemName.getText().toString();
					String desc = itemDesc.getText().toString();
					String remark = itemRemark.getText().toString();
					String price = initPrice.getText().toString();
					JSONObject kind = (JSONObject) itemKind.getSelectedItem();
					int avail = availTime.getSelectedItemPosition();
					//�����û�ѡ����Чʱ��ѡ�ָ��ʵ�ʵ���Чʱ��
					switch(avail)
					{
						case 5 :
							avail = 7;
							break;
						case 6 :
							avail = 30;
							break;
						default :
							avail += 1;
							break;
					}
					try
					{
						// �����Ʒ
						String result = addItem(name, desc
							, remark , price , kind.getInt("id") , avail);
						// ��ʾ�Ի���
						DialogUtil.showDialog(getActivity()
							, result , true);
					}
					catch (Exception e)
					{
						DialogUtil.showDialog(getActivity()
							, "��������Ӧ�쳣�����Ժ����ԣ�" , false);
						e.printStackTrace();
					}
				}
			}
		});
		return rootView;
	}

	// ���û��������Ʒ�������ļ۸����У��
	private boolean validate()
	{
		String name = itemName.getText().toString().trim();
		if (name.equals(""))
		{
			DialogUtil.showDialog(getActivity() , "��Ʒ�����Ǳ����" , false);
			return false;
		}
		String price = initPrice.getText().toString().trim();
		if (price.equals(""))
		{
			DialogUtil.showDialog(getActivity() , "���ļ۸��Ǳ����" , false);
			return false;
		}
		try
		{
			// ���԰����ļ۸�ת��Ϊ������
			Double.parseDouble(price);
		}
		catch(NumberFormatException e)
		{
			DialogUtil.showDialog(getActivity() , "���ļ۸��������ֵ��" , false);
			return false;
		}
		return true;
	}

	private String addItem(String name, String desc
		, String remark , String initPrice , int kindId , int availTime)
		throws Exception
	{
		// ʹ��Map��װ�������
		Map<String , String> map = new HashMap<String, String>();
		map.put("itemName" , name);
		map.put("itemDesc" , desc);
		map.put("itemRemark" , remark);
		map.put("initPrice" , initPrice);
		map.put("kindId" , kindId + "");
		map.put("availTime" , availTime + "");
		// ���巢�������URL
		String url = HttpUtil.BASE_URL + "addItem.jsp";
		// ��������
		return HttpUtil.postRequest(url , map);
	}
}