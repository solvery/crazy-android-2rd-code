package org.crazyit.ui;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
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
public class MenuTest extends Activity
{
	// ���������С�˵���ı�ʶ
	final int FONT_10 = 0x111;
	final int FONT_12 = 0x112;
	final int FONT_14 = 0x113;
	final int FONT_16 = 0x114;
	final int FONT_18 = 0x115;
	// ������ͨ�˵���ı�ʶ
	final int PLAIN_ITEM = 0x11b;
	// ����������ɫ�˵���ı�ʶ
	final int FONT_RED = 0x116;
	final int FONT_BLUE = 0x117;
	final int FONT_GREEN = 0x118;

	private EditText edit;

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		edit = (EditText) findViewById(R.id.txt);
	}
	// ���û�����MENU��ʱ�����÷���	
	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// -------------��menu����������С���Ӳ˵�-------------
		SubMenu fontMenu = menu.addSubMenu("�����С");
		// ���ò˵���ͼ��
		fontMenu.setIcon(R.drawable.font);
		// ���ò˵�ͷ��ͼ��
		fontMenu.setHeaderIcon(R.drawable.font);
		// ���ò˵�ͷ�ı���
		fontMenu.setHeaderTitle("ѡ�������С");
		fontMenu.add(0, FONT_10, 0, "10������");
		fontMenu.add(0, FONT_12, 0, "12������");
		fontMenu.add(0, FONT_14, 0, "14������");
		fontMenu.add(0, FONT_16, 0, "16������");
		fontMenu.add(0, FONT_18, 0, "18������");
		// -------------��menu�������ͨ�˵���-------------
		menu.add(0, PLAIN_ITEM, 0, "��ͨ�˵���");
		// -------------��menu�����������ɫ���Ӳ˵�-------------
		SubMenu colorMenu = menu.addSubMenu("������ɫ");
		colorMenu.setIcon(R.drawable.color);
		// ���ò˵�ͷ��ͼ��
		colorMenu.setHeaderIcon(R.drawable.color);
		// ���ò˵�ͷ�ı���
		colorMenu.setHeaderTitle("ѡ��������ɫ");
		colorMenu.add(0, FONT_RED, 0, "��ɫ");
		colorMenu.add(0, FONT_GREEN, 0, "��ɫ");
		colorMenu.add(0, FONT_BLUE, 0, "��ɫ");
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	// ѡ��˵��Ĳ˵��������Ļص�����
	public boolean onOptionsItemSelected(MenuItem mi)
	{
		//�жϵ��������ĸ��˵��������Ե�������Ӧ��
		switch (mi.getItemId())
		{
			case FONT_10:
				edit.setTextSize(10 * 2);
				break;
			case FONT_12:
				edit.setTextSize(12 * 2);
				break;
			case FONT_14:
				edit.setTextSize(14 * 2);
				break;
			case FONT_16:
				edit.setTextSize(16 * 2);
				break;
			case FONT_18:
				edit.setTextSize(18 * 2);
				break;
			case FONT_RED:
				edit.setTextColor(Color.RED);
				break;
			case FONT_GREEN:
				edit.setTextColor(Color.GREEN);
				break;
			case FONT_BLUE:
				edit.setTextColor(Color.BLUE);
				break;
			case PLAIN_ITEM:
				Toast toast = Toast.makeText(MenuTest.this
					, "����������ͨ�˵���" , Toast.LENGTH_SHORT);
				toast.show();
				break;
		}
		return true;
	}
}