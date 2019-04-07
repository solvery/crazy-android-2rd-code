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
	// 定义最低价格、最高价格的初始值
	int minPrice = 25, maxPrice = 75;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		np1 = (NumberPicker) findViewById(R.id.np1);
		// 设置np1的最小值和最大值
		np1.setMinValue(10);
		np1.setMaxValue(50);
		// 设置np1的当前值
		np1.setValue(minPrice);
		np1.setOnValueChangedListener(new OnValueChangeListener()
		{
			// 当NumberPicker的值发生改变时，将会激发该方法
			@Override
			public void onValueChange(NumberPicker picker, int oldVal,
					int newVal)
			{
				minPrice = newVal;
				showSelectedPrice();
			}
		});
		np2 = (NumberPicker) findViewById(R.id.np2);
		// 设置np2的最小值和最大值
		np2.setMinValue(60);
		np2.setMaxValue(100);
		// 设置np2的当前值
		np2.setValue(maxPrice);	
		np2.setOnValueChangedListener(new OnValueChangeListener()
		{
			// 当NumberPicker的值发生改变时，将会激发该方法
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
		Toast.makeText(this, "您选择最低价格为：" + minPrice
			+ ",最高价格为：" + maxPrice, Toast.LENGTH_SHORT)
			.show();
	}
}

