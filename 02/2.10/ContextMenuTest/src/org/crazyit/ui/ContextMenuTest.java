package org.crazyit.ui;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

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
public class ContextMenuTest extends Activity
{
	// Ϊÿ���˵�����һ����ʶ
	final int MENU1 = 0x111;
	final int MENU2 = 0x112;
	final int MENU3 = 0x113;
	private TextView txt;
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		txt = (TextView) findViewById(R.id.txt);
		// Ϊ�ı���ע�������Ĳ˵�
		registerForContextMenu(txt); //��
	}

	// ���������Ĳ˵�ʱ�����÷���
	@Override
	public void onCreateContextMenu(ContextMenu menu, View source,
		ContextMenu.ContextMenuInfo menuInfo)
	{
		menu.add(0, MENU1, 0, "��ɫ");
		menu.add(0, MENU2, 0, "��ɫ");
		menu.add(0, MENU3, 0, "��ɫ");
		// �������˵�����Ϊ��ѡ�˵���
		menu.setGroupCheckable(0, true, true);
		//���������Ĳ˵��ı��⡢ͼ��
		menu.setHeaderIcon(R.drawable.tools);
		menu.setHeaderTitle("ѡ�񱳾�ɫ");
	}

	// ���²˵��Ĳ˵������ʱ�����÷�����
	@Override
	public boolean onContextItemSelected(MenuItem mi)
	{
		switch (mi.getItemId())
		{
			case MENU1:
				mi.setChecked(true);
				txt.setBackgroundColor(Color.RED);
				break;
			case MENU2:
				mi.setChecked(true);
				txt.setBackgroundColor(Color.GREEN);
				break;
			case MENU3:
				mi.setChecked(true);
				txt.setBackgroundColor(Color.BLUE);
				break;
		}
		return true;
	}
}