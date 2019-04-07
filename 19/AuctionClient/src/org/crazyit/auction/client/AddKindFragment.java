package org.crazyit.auction.client;

import java.util.HashMap;
import java.util.Map;

import org.crazyit.auction.client.util.DialogUtil;
import org.crazyit.auction.client.util.HttpUtil;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

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
public class AddKindFragment extends Fragment
{
	// ��������������ı���
	EditText kindName, kindDesc;
	// ���������������ť
	Button bnAdd, bnCancel;
	@Override
	public View onCreateView(LayoutInflater inflater
		, ViewGroup container, Bundle savedInstanceState)
	{
		View rootView = inflater.inflate(R.layout.add_kind
			, container , false);
		// ��ȡ�����������༭��
		kindName = (EditText)rootView.findViewById(R.id.kindName);
		kindDesc = (EditText)rootView.findViewById(R.id.kindDesc);
		// ��ȡ�����е�������ť
		bnAdd = (Button)rootView.findViewById(R.id.bnAdd);
		bnCancel = (Button)rootView.findViewById(R.id.bnCancel);
		// Ϊȡ����ť�ĵ����¼����¼�������
		bnCancel.setOnClickListener(new HomeListener(getActivity()));
		bnAdd.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				// ����У��
				if (validate())
				{
					// ��ȡ�û����������������������
					String name = kindName.getText().toString();
					String desc = kindDesc.getText().toString();
					try
					{
						// �����Ʒ����
						String result =  addKind(name, desc);
						// ʹ�öԻ�������ʾ��ӽ��
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

	// ���û�������������ƽ���У��
	private boolean validate()
	{
		String name = kindName.getText().toString().trim();
		if (name.equals(""))
		{
			DialogUtil.showDialog(getActivity() , "���������Ǳ����" , false);
			return false;
		}
		return true;
	}

	private String addKind(String name, String desc)
		throws Exception
	{
		// ʹ��Map��װ�������
		Map<String , String> map = new HashMap<String, String>();
		map.put("kindName" , name);
		map.put("kindDesc" , desc);
		// ���巢�������URL
		String url = HttpUtil.BASE_URL + "addKind.jsp";
		// ��������
		return HttpUtil.postRequest(url , map);
	}
}