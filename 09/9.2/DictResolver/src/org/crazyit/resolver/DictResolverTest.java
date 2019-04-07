package org.crazyit.resolver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.crazyit.content.Words;
import org.crazyit.resolver.R;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
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
public class DictResolverTest extends Activity
{
	ContentResolver contentResolver;
	Button insert = null;
	Button search = null;

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		// ��ȡϵͳ��ContentResolver����
		contentResolver = getContentResolver();
		insert = (Button) findViewById(R.id.insert);
		search = (Button) findViewById(R.id.search);
		// Ϊinsert��ť�ĵ����¼����¼�������
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
				ContentValues values = new ContentValues();
				values.put(Words.Word.WORD, word);
				values.put(Words.Word.DETAIL, detail);
				contentResolver.insert(
					Words.Word.DICT_CONTENT_URI, values);
				// ��ʾ��ʾ��Ϣ
				Toast.makeText(DictResolverTest.this, "������ʳɹ���"
					, Toast.LENGTH_LONG).show();
			}
		});
		// Ϊsearch��ť�ĵ����¼����¼�������
		search.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View source)
			{
				// ��ȡ�û�����
				String key = ((EditText) findViewById(R.id.key))
					.getText().toString();
				// ִ�в�ѯ
				Cursor cursor = contentResolver.query(
					Words.Word.DICT_CONTENT_URI, null,
					"word like ? or detail like ?", new String[] {
						"%" + key + "%", "%" + key + "%" }, null);
				// ����һ��Bundle����
				Bundle data = new Bundle();
				data.putSerializable("data", converCursorToList(cursor));
				// ����һ��Intent
				Intent intent = new Intent(DictResolverTest.this,
					ResultActivity.class);
				intent.putExtras(data);
				// ����Activity
				startActivity(intent);
			}
		});
	}

	private ArrayList<Map<String, String>> converCursorToList(Cursor cursor)
	{
		ArrayList<Map<String, String>> result 
			= new ArrayList<Map<String, String>>();
		// ����Cursor�����
		while (cursor.moveToNext())
		{
			// ��������е����ݴ���ArrayList��
			Map<String, String> map = new HashMap<String, String>();
			// ȡ����ѯ��¼�е�2�С���3�е�ֵ
			map.put(Words.Word.WORD, cursor.getString(1));
			map.put(Words.Word.DETAIL, cursor.getString(2));
			result.add(map);
		}
		return result;
	}
}