package org.crazyit.db;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
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
public class Dict extends Activity
{
	MyDatabaseHelper dbHelper;
	Button insert = null;
	Button search = null;

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		// ����MyDatabaseHelper����ָ�����ݿ�汾Ϊ1���˴�ʹ�����·�����ɣ�
		// ���ݿ��ļ��Զ��ᱣ���ڳ���������ļ��е�databasesĿ¼�¡�
		dbHelper = new MyDatabaseHelper(this, "myDict.db3", 1);
		insert = (Button) findViewById(R.id.insert);
		search = (Button) findViewById(R.id.search);
		insert.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View source)
			{
				// ��ȡ�û�����
				String word = ((EditText) findViewById(R.id.word))
					.getText().toString();
				String detail = ((EditText) findViewById(R.id.detail))
					.getText().toString();
				// �������ʼ�¼
				insertData(dbHelper.getReadableDatabase(), word, detail);
				// ��ʾ��ʾ��Ϣ
				Toast.makeText(Dict.this, "������ʳɹ���", 8000).show();
			}
		});

		search.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View source)
			{
				// ��ȡ�û�����
				String key = ((EditText) findViewById(R.id.key)).getText()
					.toString();
				// ִ�в�ѯ
				Cursor cursor = dbHelper.getReadableDatabase().rawQuery(
					"select * from dict where word like ? or detail like ?",
					new String[] { "%" + key + "%", "%" + key + "%" });

				// ����һ��Bundle����
				Bundle data = new Bundle();
				data.putSerializable("data", converCursorToList(cursor));
				// ����һ��Intent
				Intent intent = new Intent(Dict.this
					, ResultActivity.class);
				intent.putExtras(data);
				// ����Activity
				startActivity(intent);
			}
		});
	}

	protected ArrayList<Map<String, String>>
		converCursorToList(Cursor cursor)
	{
		ArrayList<Map<String, String>> result = 
			new ArrayList<Map<String, String>>();
		// ����Cursor�����
		while (cursor.moveToNext())
		{
			// ��������е����ݴ���ArrayList��
			Map<String, String> map = new HashMap<String, String>();
			// ȡ����ѯ��¼�е�2�С���3�е�ֵ
			map.put("word", cursor.getString(1));
			map.put("detail", cursor.getString(2));
			result.add(map);
		}
		return result;
	}

	private void insertData(SQLiteDatabase db, String word
		, String detail)
	{
		// ִ�в������
		db.execSQL("insert into dict values(null , ? , ?)"
			, new String[] {word, detail });
	}

	@Override
	public void onDestroy()
	{
		super.onDestroy();
		// �˳�����ʱ�ر�MyDatabaseHelper���SQLiteDatabase
		if (dbHelper != null)
		{
			dbHelper.close();
		}
	}
}