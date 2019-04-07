package org.crazyit.auction.client;

import org.crazyit.auction.client.util.DialogUtil;
import org.crazyit.auction.client.util.HttpUtil;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
public class ViewItemFragment extends Fragment
{
	Button bnHome;
	ListView succList;
	TextView viewTitle;
	@Override
	public View onCreateView(LayoutInflater inflater
		, ViewGroup container, Bundle savedInstanceState)
	{
		View rootView = inflater.inflate(R.layout.view_item
			, container , false);
		// ��ȡ�����ϵķ��ذ�ť
		bnHome = (Button) rootView.findViewById(R.id.bn_home);
		succList = (ListView) rootView.findViewById(R.id.succList);
		viewTitle = (TextView) rootView.findViewById(R.id.view_titile);
		// Ϊ���ذ�ť�ĵ����¼����¼�������
		bnHome.setOnClickListener(new HomeListener(getActivity()));
		String action = getArguments().getString("action");
		// ���巢�������URL
		String url = HttpUtil.BASE_URL + action;
		// ����ǲ鿴������Ʒ���޸ı���
		if (action.equals("viewFail.jsp"))
		{
			viewTitle.setText(R.string.view_fail);
		}
		try
		{
			// ��ָ��URL�������󣬲��ѷ�������Ӧת����JSONArray����
			JSONArray jsonArray = new JSONArray(HttpUtil
				.getRequest(url));  //��
			// ��JSONArray��װ��Adapter
			JSONArrayAdapter adapter = new JSONArrayAdapter(getActivity()
				, jsonArray, "name", true);  //��
			succList.setAdapter(adapter);
		}
		catch (Exception e)
		{
			DialogUtil.showDialog(getActivity(), "��������Ӧ�쳣�����Ժ����ԣ�", false);
			e.printStackTrace();
		}
		succList.setOnItemClickListener(new OnItemClickListener()
		{
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
				int position, long id)
			{
				// �鿴ָ����Ʒ����ϸ�����
				viewItemDetail(position);  //��
			}
		});
		return rootView;
	}
	private void viewItemDetail(int position)
	{
		// ����detail.xml���沼�ִ������ͼ
		View detailView = getActivity().getLayoutInflater()
			.inflate(R.layout.detail, null);
		// ��ȡdetail.xml���沼���е��ı���
		TextView itemName = (TextView) detailView
			.findViewById(R.id.itemName);
		TextView itemKind = (TextView) detailView
			.findViewById(R.id.itemKind);
		TextView maxPrice = (TextView) detailView
			.findViewById(R.id.maxPrice);
		TextView itemRemark = (TextView) detailView
			.findViewById(R.id.itemRemark);
		// ��ȡ���������б���
		JSONObject jsonObj = (JSONObject) succList.getAdapter().getItem(
			position);
		try
		{
			// ͨ���ı�����ʾ��Ʒ����
			itemName.setText(jsonObj.getString("name"));
			itemKind.setText(jsonObj.getString("kind"));
			maxPrice.setText(jsonObj.getString("maxPrice"));
			itemRemark.setText(jsonObj.getString("desc"));
		}
		catch (JSONException e)
		{
			e.printStackTrace();
		}
		DialogUtil.showDialog(getActivity(), detailView);
	}
}