package org.crazyit.auction.client;

import org.crazyit.auction.client.util.DialogUtil;
import org.crazyit.auction.client.util.HttpUtil;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
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
public class ManageItemFragment extends Fragment
{
	public static final int ADD_ITEM = 0x1006;;
	Button bnHome, bnAdd;
	ListView itemList;
	Callbacks mCallbacks;

	@Override
	public View onCreateView(LayoutInflater inflater
		, ViewGroup container, Bundle savedInstanceState)
	{
		View rootView = inflater.inflate(R.layout.manage_item
			, container , false);
		bnHome = (Button) rootView.findViewById(R.id.bn_home);
		bnAdd = (Button) rootView.findViewById(R.id.bnAdd);
		itemList = (ListView) rootView.findViewById(R.id.itemList);
		// Ϊ���ذ�ť�ĵ����¼����¼�������
		bnHome.setOnClickListener(new HomeListener(getActivity()));
		bnAdd.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View source)
			{
				
				mCallbacks.onItemSelected(ADD_ITEM , null);
			}
		});
		// ���巢�������URL
		String url = HttpUtil.BASE_URL + "viewOwnerItem.jsp";
		try
		{
			// ��ָ��URL��������
			JSONArray jsonArray = new JSONArray(HttpUtil.getRequest(url));
			// ����������Ӧ��װ��Adapter
			JSONArrayAdapter adapter = new JSONArrayAdapter(getActivity()
				, jsonArray, "name", true);
			itemList.setAdapter(adapter);
		}
		catch (Exception e)
		{
			DialogUtil.showDialog(getActivity()
				, "��������Ӧ�쳣�����Ժ����ԣ�", false);
			e.printStackTrace();
		}
		itemList.setOnItemClickListener(new OnItemClickListener()
		{
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
				int position, long id)
			{
				viewItemInBid(position);  //��
			}
		});
		return rootView;
	}
	// ����Fragment����ӡ���ʾ��Activityʱ���ص��÷���
	@Override
	public void onAttach(Activity activity)
	{
		super.onAttach(activity);
		// ���Activityû��ʵ��Callbacks�ӿڣ��׳��쳣
		if (!(activity instanceof Callbacks))
		{
			throw new IllegalStateException(
				"ManagerItemFragment���ڵ�Activity����ʵ��Callbacks�ӿ�!");
		}
		// �Ѹ�Activity����Callbacks����
		mCallbacks = (Callbacks) activity;
	}
	// ����Fragment����������Activity�б�ɾ��ʱ�ص��÷���
	@Override
	public void onDetach()
	{
		super.onDetach();
		// ��mCallbacks��Ϊnull��
		mCallbacks = null;
	}	

	private void viewItemInBid(int position)
	{
		// ����detail_in_bid.xml���沼�ִ������ͼ
		View detailView = getActivity().getLayoutInflater()
			.inflate(R.layout.detail_in_bid, null);
		// ��ȡdetail_in_bid.xml�����е��ı���
		TextView itemName = (TextView) detailView
			.findViewById(R.id.itemName);
		TextView itemKind = (TextView) detailView
			.findViewById(R.id.itemKind);
		TextView maxPrice = (TextView) detailView
			.findViewById(R.id.maxPrice);
		TextView initPrice = (TextView) detailView
			.findViewById(R.id.initPrice);
		TextView endTime = (TextView) detailView
			.findViewById(R.id.endTime);
		TextView itemRemark = (TextView) detailView
			.findViewById(R.id.itemRemark);
		// ��ȡ�������б�������װ��JSONObject
		JSONObject jsonObj = (JSONObject) itemList.getAdapter().getItem(
			position);
		try
		{
			// ͨ���ı�����ʾ��Ʒ����
			itemName.setText(jsonObj.getString("name"));
			itemKind.setText(jsonObj.getString("kind"));
			maxPrice.setText(jsonObj.getString("maxPrice"));
			itemRemark.setText(jsonObj.getString("desc"));
			initPrice.setText(jsonObj.getString("initPrice"));
			endTime.setText(jsonObj.getString("endTime"));
		}
		catch (JSONException e)
		{
			e.printStackTrace();
		}
		DialogUtil.showDialog(getActivity(), detailView);
	}
}