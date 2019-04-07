package org.crazyit.ui;

import org.crazyit.ui.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;

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
public class ActivityMenu extends Activity
{
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// -------------��menu������Ӳ˵�-------------
		SubMenu prog = menu.addSubMenu("��������");
		// ���ò˵�ͷ��ͼ��
		prog.setHeaderIcon(R.drawable.tools);
		// ���ò˵�ͷ�ı���
		prog.setHeaderTitle("ѡ����Ҫ�����ĳ���");
		// ��Ӳ˵���
		MenuItem item = prog.add("�鿴����Java EE");
		//Ϊ�˵������ù�����Activity
		item.setIntent(new Intent(this , OtherActivity.class));
		return super.onCreateOptionsMenu(menu);
	}
}