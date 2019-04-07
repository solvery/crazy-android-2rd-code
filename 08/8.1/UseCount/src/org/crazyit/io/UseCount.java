package org.crazyit.io;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
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
public class UseCount extends Activity
{
	SharedPreferences preferences;

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		preferences = getSharedPreferences("count"
			, MODE_WORLD_READABLE);
		// ��ȡSharedPreferences���count����
		int count = preferences.getInt("count", 0);
		// ��ʾ������ǰʹ�õĴ���
		Toast.makeText(this, "������ǰ��ʹ����" + count + "�Ρ�"
			, Toast.LENGTH_LONG).show();
		Editor editor = preferences.edit();
		// ��������
		editor.putInt("count", ++count);
		// �ύ�޸�
		editor.commit();
	}
}