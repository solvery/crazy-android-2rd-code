package org.crazyit.ui;

import android.app.Activity;
import android.os.Bundle;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
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
public class CheckButtonTest extends Activity
{
	RadioGroup rg;
	TextView show;
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		// ��ȡ������rg��show�������
		rg = (RadioGroup) findViewById(R.id.rg);
		show = (TextView) findViewById(R.id.show);
		// ΪRadioGroup�����OnCheck�¼����¼�������
		rg.setOnCheckedChangeListener(new OnCheckedChangeListener()
		{
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId)
			{
				// �����û���ѡ�ĵ�ѡ��ť����̬�ı�tip�ַ�����ֵ
				String tip = checkedId == R.id.male ?
						"�����Ա�������": "�����Ա���Ů��";
				// �޸�show����е��ı���
				show.setText(tip);
			}
		});
	}
}