package org.crazyit.event;

import android.app.Activity;
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
// ʵ���¼��������ӿ�
public class ActivityListener extends Activity
	implements OnClickListener
{
	EditText show;
	Button bn;

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		show = (EditText) findViewById(R.id.show);
		bn = (Button) findViewById(R.id.bn);
		// ֱ��ʹ��Activity��Ϊ�¼�������
		bn.setOnClickListener(this);
	}

	// ʵ���¼�������
	@Override
	public void onClick(View v)
	{
		show.setText("bn��ť�������ˣ�");
	}
}