package org.crazyit.ui;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.MultiAutoCompleteTextView;

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
public class AutoCompleteTextViewTest extends Activity
{
	AutoCompleteTextView actv;
	MultiAutoCompleteTextView mauto;
	// �����ַ������飬��Ϊ��ʾ���ı�
	String[] books = new String[]{
		"���Java����",
		"���Ajax����",
		"���XML����",
		"���Workflow����"
	};
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		// ����һ��ArrayAdapter����װ����
		ArrayAdapter<String> aa = new ArrayAdapter<String>(this,
			android.R.layout.simple_dropdown_item_1line, books);
		actv = (AutoCompleteTextView)findViewById(R.id.auto);
		// ����Adapter
		actv.setAdapter(aa);
		mauto = (MultiAutoCompleteTextView)findViewById(R.id.mauto);
		// ����Adapter
		mauto.setAdapter(aa);
		// ΪMultiAutoCompleteTextView���÷ָ���
		mauto.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());
	}
}