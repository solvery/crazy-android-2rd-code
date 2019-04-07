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
 * <br/>网站: <a href="http://www.crazyit.org">疯狂Java联盟</a>
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
		// 获取启动该Result的Intent
		Intent intent = getIntent();
		// 直接通过Intent取出它所携带的Bundle数据包中的数据
		Person p = (Person) intent.getSerializableExtra("person");
		name.setText("您的用户名为：" + p.getName());
		passwd.setText("您的密码为：" + p.getPass());
		gender.setText("您的性别为：" + p.getGender());
	}
}
