package org.crazyit.ui;

import android.app.Fragment;
import android.os.Bundle;
//import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Description:
 * <br/>��վ: <a href="http://www.crazyit.org">���Java����</a>
 * <br/>Copyright (C), 2001-2014, Yeeku.H.Lee
 * <br/>This program is protected by copyright laws.
 * <br/>Program Name:
 * <br/>Date:
 * @author Yeeku.H.Lee kongyeeku@163.com
 * @version 1.0
 */
public class DummyFragment extends Fragment
{
	public static final String ARG_SECTION_NUMBER = "section_number";
	// �÷����ķ���ֵ���Ǹ�Fragment��ʾ��View���
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState)
	{
		TextView textView = new TextView(getActivity());
		textView.setGravity(Gravity.CENTER_HORIZONTAL);
		// ��ȡ������Fragmentʱ����Ĳ���Bundle
		Bundle args = getArguments();
		// ����TextView��ʾ���ı�
		textView.setText(args.getInt(ARG_SECTION_NUMBER) + "");
		textView.setTextSize(30);
		// ���ظ�TextView
		return textView;
	}
}
