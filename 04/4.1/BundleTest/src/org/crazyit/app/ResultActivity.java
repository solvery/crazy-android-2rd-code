/**
 *
 */
package org.crazyit.app;

import org.crazyt.model.Person;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

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
		setContentView(R.layout.result);
		TextView name = (TextView) findViewById(R.id.name);
		TextView passwd = (TextView) findViewById(R.id.passwd);
		TextView gender = (TextView) findViewById(R.id.gender);
		// ��ȡ������Result��Intent
		Intent intent = getIntent();
		// ֱ��ͨ��Intentȡ������Я����Bundle���ݰ��е�����
		Person p = (Person) intent.getSerializableExtra("person");
		name.setText("�����û���Ϊ��" + p.getName());
		passwd.setText("��������Ϊ��" + p.getPass());
		gender.setText("�����Ա�Ϊ��" + p.getGender());
	}
}
