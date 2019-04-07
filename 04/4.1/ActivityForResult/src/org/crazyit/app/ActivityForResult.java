package org.crazyit.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

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
public class ActivityForResult extends Activity
{
	Button bn;
	EditText city;

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		// ��ȡ�����ϵ����
		bn = (Button) findViewById(R.id.bn);
		city = (EditText) findViewById(R.id.city);
		// Ϊ��ť���¼�������
		bn.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View source)
			{
				// ������Ҫ��Ӧ��Ŀ��Activity��Intent
				Intent intent = new Intent(ActivityForResult.this,
						SelectCityActivity.class);
				// ����ָ��Activity���ȴ����صĽ��������0�������룬���ڱ�ʶ������
				startActivityForResult(intent, 0);
			}
		});
	}

	// ��д�÷������÷����Իص��ķ�ʽ����ȡָ��Activity���صĽ��
	@Override
	public void onActivityResult(int requestCode
		, int resultCode, Intent intent)
	{
		// ��requestCode��resultCodeͬʱΪ0��Ҳ���Ǵ����ض��Ľ��
		if (requestCode == 0 && resultCode == 0)
		{
			// ȡ��Intent���Extras����
			Bundle data = intent.getExtras();
			// ȡ��Bundle�е�����
			String resultCity = data.getString("city");
			// �޸�city�ı��������
			city.setText(resultCity);
		}
	}
}