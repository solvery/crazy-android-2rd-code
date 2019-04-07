/**
 *
 */
package org.crazyit.event;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.Button;

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
public class MyButton extends Button
{
	public MyButton(Context context, AttributeSet set)
	{
		super(context, set);
	}
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event)
	{
		super.onKeyDown(keyCode, event);
		Log.v("-crazyit.org-", "the onKeyDown in MyButton");
		// ����true���������¼�����������ɢ
		return true;
	}
}
