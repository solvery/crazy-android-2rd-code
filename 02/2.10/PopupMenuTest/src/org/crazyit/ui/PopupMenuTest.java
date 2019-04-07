package org.crazyit.ui;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;
import android.widget.Toast;
import android.app.Activity;

/**
 * Description:
 * <br/>website: <a href="http://www.crazyit.org">crazyit.org</a>
 * <br/>Copyright (C), 2001-2014, Yeeku.H.Lee
 * <br/>This program is protected by copyright laws.
 * <br/>Program Name:
 * <br/>Date:
 * @author Yeeku.H.Lee kongyeeku@163.com
 * @version 1.0
 */
public class PopupMenuTest extends Activity
{
	PopupMenu popup = null;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
	}

	public void onPopupButtonClick(View button)
	{
		// ����PopupMenu����
		popup = new PopupMenu(this, button);
		// ��R.menu.popup_menu�˵���Դ���ص�popup�˵���
		getMenuInflater().inflate(R.menu.popup_menu, popup.getMenu());
		// Ϊpopup�˵��Ĳ˵�����¼����¼�������
		popup.setOnMenuItemClickListener(
			new PopupMenu.OnMenuItemClickListener()
		{
			@Override
			public boolean onMenuItemClick(MenuItem item)
			{
				switch (item.getItemId())
				{
					case R.id.exit:
						// ���ظöԻ���
						popup.dismiss();
						break;
					default:
						// ʹ��Toast��ʾ�û�����Ĳ˵���
						Toast.makeText(PopupMenuTest.this,
							"�������ˡ�" + item.getTitle() + "���˵���"
							, Toast.LENGTH_SHORT).show();
				}
				return true;
			}
		});
		popup.show();
	}
}

