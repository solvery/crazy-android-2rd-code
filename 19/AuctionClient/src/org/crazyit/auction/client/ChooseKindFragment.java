package org.crazyit.auction.client;

import org.crazyit.auction.client.util.DialogUtil;
import org.crazyit.auction.client.util.HttpUtil;
import org.json.JSONArray;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

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
public class ChooseKindFragment extends Fragment
{
	public static final int CHOOSE_ITEM = 0x1008;
	Callbacks mCallbacks;
	Button bnHome;
	ListView kindList;
	@Override
	public View onCreateView(LayoutInflater inflater
		, ViewGroup container, Bundle savedInstanceState)
	{
		View rootView = inflater.inflate(R.layout.choose_kind
			, container , false);
		bnHome = (Button) rootView.findViewById(R.id.bn_home);
		kindList = (ListView) rootView.findViewById(R.id.kindList);
		// Ϊ���ذ�ť�ĵ����¼����¼�������
		bnHome.setOnClickListener(new HomeListener(getActivity()));
		// ���巢�������URL
		String url = HttpUtil.BASE_URL + "viewKind.jsp";
		try
		{
			// ��ָ��URL�������󣬲�����������Ӧ��װ��JSONArray����
			JSONArray jsonArray = new JSONArray(
				HttpUtil.getRequest(url));  //��
			// ʹ��ListView��ʾ������Ʒ׼����
			kindList.setAdapter(new KindArrayAdapter(jsonArray
				, getActivity()));
		}
		catch (Exception e)
		{
			DialogUtil.showDialog(getActivity() 
				, "��������Ӧ�쳣�����Ժ����ԣ�" , false);
			e.printStackTrace();
		}
		kindList.setOnItemClickListener(new OnItemClickListener()
		{
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
				int position, long id)
			{
				Bundle bundle = new Bundle();
				bundle.putLong("kindId", id);
				mCallbacks.onItemSelected(CHOOSE_ITEM , bundle);
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
				"ManageKindFragment���ڵ�Activity����ʵ��Callbacks�ӿ�!");
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