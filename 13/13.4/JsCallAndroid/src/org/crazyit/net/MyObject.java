package org.crazyit.net;

import android.app.AlertDialog;
import android.content.Context;
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
public class MyObject
{
	Context mContext;

	MyObject(Context c)
	{
		mContext = c;
	}
	// �÷������ᱩ¶��JavaScript�ű�����
	public void showToast(String name)
	{
		Toast.makeText(mContext, name + "�����ã�"
			, Toast.LENGTH_LONG).show();
	}
	// �÷������ᱩ¶��JavaScript�ű�����
	public void showList()
	{
		// ��ʾһ����ͨ���б�Ի���
		new AlertDialog.Builder(mContext)
			.setTitle("ͼ���б�")
			.setIcon(R.drawable.ic_launcher)
			.setItems(new String[]{"���Java����"
			, "���Android����" , "������Java EE��ҵӦ��ʵս"} , null)
			.setPositiveButton("ȷ��", null)
			.create()
			.show();
	}
}
