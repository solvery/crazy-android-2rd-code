package org.crazyit.auction.client;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;

/**
 * Description:
 * <br/>website: <a href="http://www.crazyit.org">crazyit.org</a>
 * <br/>Copyright (C), 2001-2014, Yeeku.H.Lee
 * <br/>This program is protected by copyright laws.
 * <br/>Program Name:
 * <br/>Date:
 * @author Yeeku.H.Lee kongyeeku@163.com
 * @version 1.0
 */
public class AuctionClientActivity extends Activity
	implements Callbacks
{
	// ����һ����꣬���ڱ�ʶ��Ӧ���Ƿ�֧�ִ���Ļ
	private boolean mTwoPane;
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		// ָ������R.layout.activity_main��Ӧ�Ľ��沼���ļ�
		// ��ʵ���ϸ�Ӧ�û������Ļ�ֱ��ʼ��ڲ�ͬ�Ľ��沼���ļ�
		setContentView(R.layout.activity_main);
		// ������صĽ��沼���ļ��а���IDΪbook_detail_container�����
		if (findViewById(R.id.auction_detail_container) != null)
		{
			mTwoPane = true;
			((AuctionListFragment) getFragmentManager()
				.findFragmentById(R.id.auction_list))
				.setActivateOnItemClick(true);
		}
	}
	@Override
	public void onItemSelected(Integer id , Bundle bundle)
	{
		if (mTwoPane)
		{
			Fragment fragment = null;
			switch ((int) id)
			{
				// �鿴������Ʒ
				case 0:
					// ����ViewItemFragment
					fragment = new ViewItemFragment();
					// ����Bundle��׼����Fragment�������
					Bundle arguments = new Bundle();
					arguments.putString("action", "viewSucc.jsp");
					// ��Fragment�������
					fragment.setArguments(arguments);
					break;
				// ���������Ʒ
				case 1:
					// ����ViewItemFragment
					fragment = new ViewItemFragment();
					// ����Bundle��׼����Fragment�������
					Bundle arguments2 = new Bundle();
					arguments2.putString("action", "viewFail.jsp");
					// ��Fragment�������
					fragment.setArguments(arguments2);
					break;
				// ������Ʒ����
				case 2:
					// ����ManageKindFragment
					fragment = new ManageKindFragment();
					break;
				// ������Ʒ
				case 3:
					// ����ManageItemFragment
					fragment = new ManageItemFragment();
					break;
				// ���������Ʒ��ѡ����Ʒ���ࣩ
				case 4:
					// ����ChooseKindFragment
					fragment = new ChooseKindFragment();
					break;
				// �鿴�Լ��ľ���
				case 5:
					// ����ViewBidFragment
					fragment = new ViewBidFragment();
					break;
				case ManageItemFragment.ADD_ITEM:
					fragment = new AddItemFragment();
					break;
				case ManageKindFragment.ADD_KIND:
					fragment = new AddKindFragment();
					break;
				case ChooseKindFragment.CHOOSE_ITEM:
					fragment = new ChooseItemFragment();
					Bundle args = new Bundle();
					args.putLong("kindId", bundle.getLong("kindId"));
					fragment.setArguments(args);
					break;
				case ChooseItemFragment.ADD_BID:
					fragment = new AddBidFragment();
					Bundle args2 = new Bundle();
					args2.putInt("itemId", bundle.getInt("itemId"));
					fragment.setArguments(args2);
					break;
			}			
			// ʹ��fragment�滻auction_detail_container������ǰ��ʾ��Fragment
			getFragmentManager().beginTransaction()
				.replace(R.id.auction_detail_container, fragment)
				.addToBackStack(null).commit();
		}
		else
		{
			Intent intent = null;
			switch ((int) id)
			{
				// �鿴������Ʒ
				case 0:
					// ����ViewItem Activity
					intent = new Intent(this, ViewItem.class);
					// action����Ϊ�����Servlet��ַ��
					intent.putExtra("action", "viewSucc.jsp");
					startActivity(intent);
					break;
				// ���������Ʒ
				case 1:
					// ����ViewItem Activity
					intent = new Intent(this, ViewItem.class);
					// action����Ϊ�����Servlet��URL��
					intent.putExtra("action", "viewFail.jsp");
					startActivity(intent);
					break;
				// ������Ʒ����
				case 2:
					// ����ManageKind Activity
					intent = new Intent(this, ManageKind.class);
					startActivity(intent);
					break;
				// ������Ʒ
				case 3:
					// ����ManageItem Activity
					intent = new Intent(this, ManageItem.class);
					startActivity(intent);
					break;
				// ���������Ʒ��ѡ����Ʒ���ࣩ
				case 4:
					// ����ChooseKind Activity
					intent = new Intent(this, ChooseKind.class);
					startActivity(intent);
					break;
				// �鿴�Լ��ľ���
				case 5:
					// ����ViewBid Activity
					intent = new Intent(this, ViewBid.class);
					startActivity(intent);
					break;
			}
		}
	}
}
