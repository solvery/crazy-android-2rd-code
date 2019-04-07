package org.crazyit.manager;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.telephony.SmsManager;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

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
public class GroupSend extends Activity
{
	EditText numbers, content;
	Button select, send;
	SmsManager sManager;
	// ��¼��ҪȺ���ĺ����б�
	ArrayList<String> sendList = new ArrayList<String>();

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		sManager = SmsManager.getDefault();
		// ��ȡ�����ϵ��ı��򡢰�ť���
		numbers = (EditText) findViewById(R.id.numbers);
		content = (EditText) findViewById(R.id.content);
		select = (Button) findViewById(R.id.select);
		send = (Button) findViewById(R.id.send);
		// Ϊsend��ť�ĵ����¼��󶨼�����
		send.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				for (String number : sendList)
				{
					// ����һ��PendingIntent����
					PendingIntent pi = PendingIntent.getActivity(
						GroupSend.this, 0, new Intent(), 0);
					// ���Ͷ���
					sManager.sendTextMessage(number, null, content
						.getText().toString(), pi, null);
				}
				// ��ʾ����Ⱥ�����
				Toast.makeText(GroupSend.this, "����Ⱥ�����"
					, Toast.LENGTH_SHORT).show();
			}
		});

		// Ϊselect��ť�ĵ����¼��󶨼�����
		select.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				// ��ѯ��ϵ�˵ĵ绰����
				final Cursor cursor = getContentResolver().query(
					ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
					null, null, null, null);
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
					public View getView(int position, View convertView,
						ViewGroup parent)
					{
						cursor.moveToPosition(position);
						CheckBox rb = new CheckBox(GroupSend.this);
						// ��ȡ��ϵ�˵ĵ绰���룬��ȥ���м���л��ߡ��ո�
						String number = cursor
							.getString(cursor.getColumnIndex(ContactsContract
							.CommonDataKinds.Phone.NUMBER))
							.replace("-", "")
							.replace(" " , "");
						rb.setText(number);
						// ����ú����Ѿ������뷢����������Ĭ�Ϲ�ѡ�ú���
						if (isChecked(number))
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
				new AlertDialog.Builder(GroupSend.this)
					.setView(selectView)
					.setPositiveButton("ȷ��",
					new DialogInterface.OnClickListener()
					{
						@Override
						public void onClick(DialogInterface dialog,
							int which)
						{
							// ���sendList����
							sendList.clear();
							// ����listView�����ÿ���б���
							for (int i = 0; i < listView.getCount(); i++)
							{
								CheckBox checkBox = (CheckBox) listView
									.getChildAt(i);
								// ������б����ѡ
								if (checkBox.isChecked())
								{
									// ��Ӹ��б���ĵ绰����
									sendList.add(checkBox.getText()
										.toString());
								}
							}
							numbers.setText(sendList.toString());
						}
					}).show();
			}
		});
	}

	// �ж�ĳ���绰�����Ƿ�����Ⱥ����Χ��
	public boolean isChecked(String phone)
	{
		for (String s1 : sendList)
		{
			if (s1.equals(phone))
			{
				return true;
			}
		}
		return false;
	}
}