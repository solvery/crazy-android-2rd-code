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
public class ChooseItemFragment extends Fragment
{
	public static final int ADD_BID = 0x1009;
	Button bnHome;
	ListView succList;
	TextView viewTitle;
	Callbacks mCallbacks;	
	// ��д�÷������÷������ص�View����ΪFragment��ʾ�����
	@Override
	public View onCreateView(LayoutInflater inflater
		, ViewGroup container, Bundle savedInstanceState)
	{
		View rootView = inflater.inflate(R.layout.view_item
			, container , false);
		// ��ȡ�����еķ��ذ�ť
		bnHome = (Button)rootView.findViewById(R.id.bn_home);
		succList = (ListView)rootView.findViewById(R.id.succList);
		viewTitle = (TextView)rootView.findViewById(R.id.view_titile);
		// Ϊ���ذ�ť�ĵ����¼����¼�������
		bnHome.setOnClickListener(new HomeListener(getActivity()));
		long kindId = getArguments().getLong("kindId");
		// ���巢�������URL
		String url = HttpUtil.BASE_URL + "itemList.jsp?kindId=" + kindId;
		try
		{
			// ��������ID��ȡ�������Ӧ��������Ʒ
			JSONArray jsonArray = new JSONArray(HttpUtil.getRequest(url));
			JSONArrayAdapter adapter = new JSONArrayAdapter(
				getActivity() , jsonArray , "name" , true);
			// ʹ��ListView��ʾ��ǰ�����������Ʒ
			succList.setAdapter(adapter);
		}
		catch (Exception e1)
		{
			DialogUtil.showDialog(getActivity() , "��������Ӧ�쳣�����Ժ����ԣ�" , false);
			e1.printStackTrace();
		}
		// �޸ı���
		viewTitle.setText(R.string.item_list);
		succList.setOnItemClickListener(new OnItemClickListener()
		{
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
				int position, long id)
			{
				JSONObject jsonObj = (JSONObject) succList
					.getAdapter().getItem(position);
				Bundle bundle = new Bundle();
				try
				{
					bundle.putInt("itemId" , jsonObj.getInt("id"));
				}
				catch (JSONException e)
				{
					e.printStackTrace();
				}
				mCallbacks.onItemSelected(ADD_BID, bundle);
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
}