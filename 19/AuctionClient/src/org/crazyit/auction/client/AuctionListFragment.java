package org.crazyit.auction.client;

import android.app.Activity;
import android.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
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
public class AuctionListFragment extends Fragment
{	
	ListView auctionList;
	private Callbacks mCallbacks;	
	// ��д�÷������÷������ص�View����ΪFragment��ʾ�����
	@Override
	public View onCreateView(LayoutInflater inflater
		, ViewGroup container, Bundle savedInstanceState)
	{
		// ����/res/layout/Ŀ¼�µ�function_list.xml�����ļ�
		View rootView = inflater.inflate(R.layout.auction_list,
			container, false);
		auctionList = (ListView) rootView.findViewById(
			R.id.auction_list);
		// ΪListView���б���ĵ����¼����¼�������
		auctionList.setOnItemClickListener(new OnItemClickListener()
		{

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
				int position, long id)
			{
				mCallbacks.onItemSelected(position , null);
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
				"AuctionListFragment���ڵ�Activity����ʵ��Callbacks�ӿ�!");
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

	public void setActivateOnItemClick(boolean activateOnItemClick)
	{
		auctionList.setChoiceMode(activateOnItemClick 
			? ListView.CHOICE_MODE_SINGLE
			: ListView.CHOICE_MODE_NONE);
	}
}