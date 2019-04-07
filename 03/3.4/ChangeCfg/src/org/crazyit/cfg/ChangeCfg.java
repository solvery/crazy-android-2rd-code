package org.crazyit.cfg;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
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
public class ChangeCfg extends Activity
{
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		Button bn = (Button) findViewById(R.id.bn);
		// Ϊ��ť���¼�������
		bn.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View source)
			{
				Configuration config = getResources().getConfiguration();
				// �����ǰ�Ǻ���
				if (config.orientation == Configuration.ORIENTATION_LANDSCAPE)
				{
					// ��Ϊ����
					ChangeCfg.this.setRequestedOrientation(
						ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
				}
				// �����ǰ������
				if (config.orientation == Configuration.ORIENTATION_PORTRAIT)
				{
					// ��Ϊ����
					ChangeCfg.this.setRequestedOrientation(
						ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
				}
			}
		});
	}

	// ��д�÷��������ڼ���ϵͳ���õĸ��ģ���Ҫ�Ǽ����Ļ����ĸ���
	@Override
	public void onConfigurationChanged(Configuration newConfig)
	{
		super.onConfigurationChanged(newConfig);
		String screen = newConfig.orientation ==
			Configuration.ORIENTATION_LANDSCAPE ? "������Ļ" : "������Ļ";
		Toast.makeText(this, "ϵͳ����Ļ�������ı�" + "\n�޸ĺ����Ļ����Ϊ��"
			+ screen, Toast.LENGTH_LONG).show();
	}
}