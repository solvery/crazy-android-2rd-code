/**
 *
 */
package org.crazyit.intent;

import android.app.Activity;
import android.content.ComponentName;
import android.os.Bundle;
import android.widget.EditText;

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
public class SecondActivity extends Activity
{
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.second);
		EditText show = (EditText) findViewById(R.id.show);
		// ��ȡ��Activity��Ӧ��Intent��Component����
		ComponentName comp = getIntent().getComponent();
		// ��ʾ��ComponentName����İ���������
		show.setText("�������Ϊ��" + comp.getPackageName()
			+ "\n�������Ϊ��" + comp.getClassName());
	}
}
