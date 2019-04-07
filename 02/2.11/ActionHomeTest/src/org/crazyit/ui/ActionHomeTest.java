package org.crazyit.ui;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
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
public class ActionHomeTest extends Activity
{
	private TextView txt;
	ActionBar actionBar;
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		txt = (TextView) findViewById(R.id.txt);
		actionBar = getActionBar();
		// �����Ƿ���ʾӦ�ó���ͼ��
		actionBar.setDisplayShowHomeEnabled(true);
		// ��Ӧ�ó���ͼ������Ϊ�ɵ���İ�ť
		actionBar.setHomeButtonEnabled(true);
		// ��Ӧ�ó���ͼ������Ϊ�ɵ���İ�ť������ͼ������������ͷ
//		actionBar.setDisplayHomeAsUpEnabled(true);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		MenuInflater inflator = new MenuInflater(this);
		// ״̬R.menu.context��Ӧ�Ĳ˵�������ӵ�menu��
		inflator.inflate(R.menu.my_menu, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	// ѡ��˵��Ĳ˵��������Ļص�����
	public boolean onOptionsItemSelected(MenuItem mi)
	{
		if(mi.isCheckable())
		{
			mi.setChecked(true);
		}
		// �жϵ��������ĸ��˵��������Ե�������Ӧ��
		switch (mi.getItemId())
		{
			case android.R.id.home:
				// ��������FirstActivity��Intent
				Intent intent = new Intent(this, FirstActivity.class);
				// ��Ӷ����Flag����Activityջ�д���FirstActivity֮�ϵ�Activity����
				intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				// ����intent��Ӧ��Activity
				startActivity(intent);
				break;
			case R.id.font_10:
				txt.setTextSize(10 * 2);
				break;
			case R.id.font_12:
				txt.setTextSize(12 * 2);
				break;
			case R.id.font_14:
				txt.setTextSize(14 * 2);
				break;
			case R.id.font_16:
				txt.setTextSize(16 * 2);
				break;
			case R.id.font_18:
				txt.setTextSize(18 * 2);
				break;
			case R.id.red_font:
				txt.setTextColor(Color.RED);
				mi.setChecked(true);
				break;
			case R.id.green_font:
				txt.setTextColor(Color.GREEN);
				mi.setChecked(true);
				break;
			case R.id.blue_font:
				txt.setTextColor(Color.BLUE);
				mi.setChecked(true);
				break;
			case R.id.plain_item:
				Toast toast = Toast.makeText(ActionHomeTest.this, "����������ͨ�˵���",
						Toast.LENGTH_SHORT);
				toast.show();
				break;
		}
		return true;
	}
}