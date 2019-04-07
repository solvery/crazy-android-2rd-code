package org.crazyit.auction.client;

import org.crazyit.auction.client.util.DialogUtil;
import org.crazyit.auction.client.util.HttpUtil;
import org.json.JSONArray;

import android.app.Activity;
import android.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

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
public class ManageKindFragment extends Fragment
{
	public static final int ADD_KIND = 0x1007;
	Button bnHome , bnAdd;
	ListView kindList;
	Callbacks mCallbacks;	
	@Override
	public View onCreateView(LayoutInflater inflater
		, ViewGroup container, Bundle savedInstanceState)
	{
		View rootView = inflater.inflate(R.layout.manage_kind
			, container , false);
		// ��ȡ���沼���ϵ�������ť
		bnHome = (Button) rootView.findViewById(R.id.bn_home);
		bnAdd = (Button) rootView.findViewById(R.id.bnAdd);
		kindList = (ListView) rootView.findViewById(R.id.kindList);
		// Ϊ���ذ�ť�ĵ����¼����¼�������
		bnHome.setOnClickListener(new HomeListener(getActivity()));
		// Ϊ��Ӱ�ť�ĵ����¼����¼�������
		bnAdd.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View source)
			{
				// ����Ӱ�ť������ʱ�����ø�Fragment����Activity��onItemSelected����
				mCallbacks.onItemSelected(ADD_KIND , null);
			}
		});
		// ���巢�������URL
		String url = HttpUtil.BASE_URL + "viewKind.jsp";
		try
		{
			// ��ָ��URL�������󣬲�����Ӧ��װ��JSONArray����
			final JSONArray jsonArray = new JSONArray(
				HttpUtil.getRequest(url));
			// ��JSONArray�����װ��Adapter
			kindList.setAdapter(new KindArrayAdapter(jsonArray
				, getActivity()));
		}
		catch (Exception e)
		{
			DialogUtil.showDialog(getActivity()
				, "��������Ӧ�쳣�����Ժ����ԣ�" ,false);
			e.printStackTrace();
		}
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