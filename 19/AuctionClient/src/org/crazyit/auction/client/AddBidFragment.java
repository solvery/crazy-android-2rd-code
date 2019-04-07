package org.crazyit.auction.client;

import java.util.HashMap;
import java.util.Map;

import org.crazyit.auction.client.util.DialogUtil;
import org.crazyit.auction.client.util.HttpUtil;
import org.json.JSONObject;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

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
public class AddBidFragment extends Fragment
{
	// ����������ı���
	TextView itemName, itemDesc,itemRemark,itemKind
		,initPrice , maxPrice ,endTime;
	EditText bidPrice;
	// ���������������ť
	Button bnAdd, bnCancel;
	// ���嵱ǰ������������Ʒ
	JSONObject jsonObj;
	@Override
	public View onCreateView(LayoutInflater inflater
		, ViewGroup container, Bundle savedInstanceState)
	{
		View rootView = inflater.inflate(R.layout.add_bid
			, container , false);
		// ��ȡ�����б༭��
		itemName = (TextView)rootView.findViewById(R.id.itemName);
		itemDesc = (TextView)rootView.findViewById(R.id.itemDesc);
		itemRemark = (TextView)rootView.findViewById(R.id.itemRemark);
		itemKind = (TextView)rootView.findViewById(R.id.itemKind);
		initPrice = (TextView)rootView.findViewById(R.id.initPrice);
		maxPrice = (TextView)rootView.findViewById(R.id.maxPrice);
		endTime = (TextView)rootView.findViewById(R.id.endTime);
		bidPrice = (EditText)rootView.findViewById(R.id.bidPrice);
		// ��ȡ�����е�������ť
		bnAdd = (Button)rootView.findViewById(R.id.bnAdd);
		bnCancel = (Button)rootView.findViewById(R.id.bnCancel);
		// Ϊȡ����ť�ĵ����¼����¼�������
		bnCancel.setOnClickListener(new HomeListener(getActivity()));
		// ���巢�������URL
		String url = HttpUtil.BASE_URL + "getItem.jsp?itemId="
			+ getArguments().getInt("itemId");
		try
		{
			// ��ȡָ����������Ʒ
			jsonObj = new JSONObject(HttpUtil.getRequest(url));
			// ʹ���ı�������ʾ������Ʒ������
			itemName.setText(jsonObj.getString("name"));
			itemDesc.setText(jsonObj.getString("desc"));
			itemRemark.setText(jsonObj.getString("remark"));
			itemKind.setText(jsonObj.getString("kind"));
			initPrice.setText(jsonObj.getString("initPrice"));
			maxPrice.setText(jsonObj.getString("maxPrice"));
			endTime.setText(jsonObj.getString("endTime"));
		}
		catch (Exception e1)
		{
			DialogUtil.showDialog(getActivity(), "��������Ӧ�����쳣��", false);
			e1.printStackTrace();
		}
		bnAdd.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				try
				{
					// ִ������ת��
					double curPrice = Double.parseDouble(
						bidPrice.getText().toString());
					// ִ������У��
					if( curPrice <  jsonObj.getDouble("maxPrice"))  //��
					{
						DialogUtil.showDialog(getActivity(),
							"������ľ��۱�����ڵ�ǰ����", false);
					}
					else
					{
						// ��Ӿ���
						String result = addBid(jsonObj.getString("id")
							, curPrice + "");  //��
						// ��ʾ�Ի���
						DialogUtil.showDialog(getActivity()
							, result , true);
					}
				}
				catch(NumberFormatException ne)
				{
					DialogUtil.showDialog(getActivity() 
						, "������ľ��۱�������ֵ", false);
				}
				catch(Exception e)
				{
					e.printStackTrace();
					DialogUtil.showDialog(getActivity() 
						, "��������Ӧ�����쳣�������ԣ�", false);
				}
			}
		});
		return rootView;
	}

	private String addBid(String itemId , String bidPrice)
		throws Exception
	{
		// ʹ��Map��װ�������
		Map<String , String> map = new HashMap<String, String>();
		map.put("itemId" , itemId);
		map.put("bidPrice" , bidPrice);
		// �������󽫻ᷢ�͵�addKind.jspҳ��
		String url = HttpUtil.BASE_URL + "addBid.jsp";
		// ��������
		return HttpUtil.postRequest(url , map);
	}
}