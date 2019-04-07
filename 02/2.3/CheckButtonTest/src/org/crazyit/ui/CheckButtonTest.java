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
		// 获取界面上rg、show两个组件
		rg = (RadioGroup) findViewById(R.id.rg);
		show = (TextView) findViewById(R.id.show);
		// 为RadioGroup组件的OnCheck事件绑定事件监听器
		rg.setOnCheckedChangeListener(new OnCheckedChangeListener()
		{
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId)
			{
				// 根据用户勾选的单选按钮来动态改变tip字符串的值
				String tip = checkedId == R.id.male ?
						"您的性别是男人": "您的性别是女人";
				// 修改show组件中的文本。
				show.setText(tip);
			}
		});
	}
}