package org.crazyit.ui;

import android.os.Bundle;
import android.widget.NumberPicker;
import android.widget.NumberPicker.OnValueChangeListener;
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
public class NumberPickerTest extends Activity
{
	NumberPicker np1 , np2;
	// ������ͼ۸���߼۸�ĳ�ʼֵ
	int minPrice = 25, maxPrice = 75;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		np1 = (NumberPicker) findViewById(R.id.np1);
		// ����np1����Сֵ�����ֵ
		np1.setMinValue(10);
		np1.setMaxValue(50);
		// ����np1�ĵ�ǰֵ
		np1.setValue(minPrice);
		np1.setOnValueChangedListener(new OnValueChangeListener()
		{
			// ��NumberPicker��ֵ�����ı�ʱ�����ἤ���÷���
			@Override
			public void onValueChange(NumberPicker picker, int oldVal,
					int newVal)
			{
				minPrice = newVal;
				showSelectedPrice();
			}
		});
		np2 = (NumberPicker) findViewById(R.id.np2);
		// ����np2����Сֵ�����ֵ
		np2.setMinValue(60);
		np2.setMaxValue(100);
		// ����np2�ĵ�ǰֵ
		np2.setValue(maxPrice);	
		np2.setOnValueChangedListener(new OnValueChangeListener()
		{
			// ��NumberPicker��ֵ�����ı�ʱ�����ἤ���÷���
			@Override
			public void onValueChange(NumberPicker picker, int oldVal,
					int newVal)
			{
				maxPrice = newVal;
				showSelectedPrice();
			}
		});
	}
	private void showSelectedPrice()
	{
		Toast.makeText(this, "��ѡ����ͼ۸�Ϊ��" + minPrice
			+ ",��߼۸�Ϊ��" + maxPrice, Toast.LENGTH_SHORT)
			.show();
	}
}

