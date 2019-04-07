/**
 *
 */
package org.crazyit.db;

import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

/**
 * Description:
 * <br/>��վ: <a href="http://www.crazyit.org">���Java����</a>
 * <br/>Copyright (C), 2001-2014, Yeeku.H.Lee
 * <br/>This program is protected by copyright laws.
 * <br/>Program Name:
 * <br/>Date:
 * @author  Yeeku.H.Lee kongyeeku@163.com
 * @version  1.0
 */
public class ResultActivity extends Activity
{
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.popup);
		ListView listView = (ListView) findViewById(R.id.show);
		Intent intent = getIntent();
		// ��ȡ��intent��Я��������
		Bundle data = intent.getExtras();
		// ��Bundle���ݰ���ȡ������
		@SuppressWarnings("unchecked")
		List<Map<String, String>> list = (List<Map<String, String>>)
			data.getSerializable("data");
		// ��List��װ��SimpleAdapter
		SimpleAdapter adapter = new SimpleAdapter(ResultActivity.this
			, list,
			R.layout.line, new String[] { "word", "detail" }
			, new int[] {R.id.word, R.id.detail });
		// ���ListView
		listView.setAdapter(adapter);
	}
}
