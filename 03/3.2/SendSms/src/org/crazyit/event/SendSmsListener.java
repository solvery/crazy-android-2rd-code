/**
 *
 */
package org.crazyit.event;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.telephony.SmsManager;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Description:
 * <br/>��վ: <a href="http://www.crazyit.org">���Java����</a>
 * <br/>Copyright (C), 2001-2014, Yeeku.H.Lee
 * <br/>This program is protected by copyright laws.
 * <br/>Program Name:
 * <br/>Date:
 * @author  Yeeku.H.Lee kongyeeku@163.com
 * @version  1.0
 */
public class SendSmsListener implements OnLongClickListener
{
	private Activity act;
	private EditText address;
	private EditText content;

	public SendSmsListener(Activity act, EditText address
		, EditText content)
	{
		this.act = act;
		this.address = address;
		this.content = content;
	}

	@Override
	public boolean onLongClick(View source)
	{
		String addressStr = address.getText().toString();
		String contentStr = content.getText().toString();
		// ��ȡ���Ź�����
		SmsManager smsManager = SmsManager.getDefault();
		// �������Ͷ��ŵ�PendingIntent
		PendingIntent sentIntent = PendingIntent.getBroadcast(act
			, 0, new Intent(), 0);
		// �����ı�����
		smsManager.sendTextMessage(addressStr, null, contentStr
			, sentIntent, null);
		Toast.makeText(act, "���ŷ������", Toast.LENGTH_LONG).show();
		return false;
	}
}
