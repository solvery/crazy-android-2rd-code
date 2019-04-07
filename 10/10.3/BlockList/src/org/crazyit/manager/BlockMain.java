/**
 * 
 */
package org.crazyit.manager;

import java.lang.reflect.Method;
import java.util.ArrayList;

import com.android.internal.telephony.ITelephony;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.os.IBinder;
import android.provider.ContactsContract;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ListView;

/**
 * Description: <br/>
 * ��վ: <a href="http://www.crazyit.org">���Java����</a> <br/>
 * Copyright (C), 2001-2012, Yeeku.H.Lee <br/>
 * This program is protected by copyright laws. <br/>
 * Program Name: <br/>
 * Date:
 * 
 * @author Yeeku.H.Lee kongyeeku@163.com
 * @version 1.0
 */
public class BlockMain extends Activity
{
	// ��¼��������List
	ArrayList<String> blockList = new ArrayList<String>();
	TelephonyManager tManager;
	// ����ͨ��״̬�ļ�����
	CustomPhoneCallListener cpListener;

	public class CustomPhoneCallListener extends PhoneStateListener
	{
		@Override
		public void onCallStateChanged(int state, String number)
		{
			switch (state)
			{
				case TelephonyManager.CALL_STATE_IDLE:
					break;
				case TelephonyManager.CALL_STATE_OFFHOOK:
					break;
				// ���绰����ʱ
				case TelephonyManager.CALL_STATE_RINGING:
					// ����ú������ں�����
					if (isBlock(number))
					{
						System.out.println("�������Ҷϵ绰������");
						try
						{
							Method method = Class.forName(
								"android.os.ServiceManager")
								.getMethod("getService"
								, String.class);
							// ��ȡԶ��TELEPHONY_SERVICE��IBinder����Ĵ���
							IBinder binder = (IBinder) method.invoke(null,
								new Object[] { TELEPHONY_SERVICE });
							// ��IBinder����Ĵ���ת��ΪITelephony����
							ITelephony telephony = ITelephony.Stub
								.asInterface(binder);
							// �Ҷϵ绰
							telephony.endCall();
						}
						catch (Exception e)
						{
							e.printStackTrace();
						}
					}
					break;
			}
			super.onCallStateChanged(state, number);
		}
	}

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		// ��ȡϵͳ��TelephonyManager������
		tManager = (TelephonyManager) 
			getSystemService(TELEPHONY_SERVICE);
		cpListener = new CustomPhoneCallListener();
		// ͨ��TelephonyManager����ͨ��״̬�ĸı�
		tManager.listen(cpListener
			, PhoneStateListener.LISTEN_CALL_STATE);
		// ��ȡ����İ�ť����Ϊ���ĵ����¼��󶨼�����
		findViewById(R.id.managerBlock).setOnClickListener(
			new OnClickListener()
			{
				@Override
				public void onClick(View v)
				{
					// ��ѯ��ϵ�˵ĵ绰����
					final Cursor cursor = getContentResolver()
						.query(ContactsContract.CommonDataKinds
						.Phone.CONTENT_URI,	null, null, null, null);
					BaseAdapter adapter = new BaseAdapter()
					{
						@Override
						public int getCount()
						{
							return cursor.getCount();
						}

						@Override
						public Object getItem(int position)
						{
							return position;
						}

						@Override
						public long getItemId(int position)
						{
							return position;
						}

						@Override
						public View getView(int position,
							View convertView, ViewGroup parent)
						{
							cursor.moveToPosition(position);
							CheckBox rb = new CheckBox(BlockMain.this);
							// ��ȡ��ϵ�˵ĵ绰���룬��ȥ���м���л��ߡ��ո�
							String number = cursor
								.getString(cursor.getColumnIndex(
								ContactsContract.CommonDataKinds
								.Phone.NUMBER))
								.replace("-", "")
								.replace(" ", "");
							rb.setText(number);
							// ����ú����Ѿ��������������Ĭ�Ϲ�ѡ�ú���
							if (isBlock(number))
							{
								rb.setChecked(true);
							}
							return rb;
						}
					};
					// ����list.xml�����ļ���Ӧ��View
					View selectView = getLayoutInflater().inflate(
						R.layout.list, null);
					// ��ȡselectView�е���Ϊlist��ListView���
					final ListView listView = (ListView) selectView
						.findViewById(R.id.list);
					listView.setAdapter(adapter);
					new AlertDialog.Builder(BlockMain.this)
						.setView(selectView)
						.setPositiveButton("ȷ��",
						new DialogInterface.OnClickListener()
						{
							@Override
							public void onClick(
								DialogInterface dialog, int which)
							{
								// ���blockList����
								blockList.clear();
								// ����listView�����ÿ���б���
								for (int i = 0; i < listView
									.getCount(); i++)
								{
									CheckBox checkBox = (CheckBox)
										listView.getChildAt(i);
									// ������б����ѡ
									if (checkBox.isChecked())
									{
										// ��Ӹ��б���ĵ绰����
										blockList.add(checkBox
											.getText().toString());
									}
								}
								System.out.println(blockList);
							}
						}).show();
				}
			});
	}

	// �ж�ĳ���绰�����Ƿ��ں�����֮��
	public boolean isBlock(String phone)
	{
		System.out.println("������룺" + phone);
		System.out.println("--------" + blockList);
		
		for (String s1 : blockList)
		{
			if (s1.equals(phone))
			{
				return true;
			}
		}
		return false;
	}
}
