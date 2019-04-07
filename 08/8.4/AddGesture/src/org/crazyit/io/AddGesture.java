package org.crazyit.io;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.gesture.Gesture;
import android.gesture.GestureLibraries;
import android.gesture.GestureLibrary;
import android.gesture.GestureOverlayView;
import android.gesture.GestureOverlayView.OnGesturePerformedListener;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

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
public class AddGesture extends Activity
{
	EditText editText;
	GestureOverlayView gestureView;

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		// ��ȡ�ı��༭��
		editText = (EditText) findViewById(R.id.gesture_name);
		// ��ȡ���Ʊ༭��ͼ
		gestureView = (GestureOverlayView) 
			findViewById(R.id.gesture);
		// �������ƵĻ�����ɫ
		gestureView.setGestureColor(Color.RED);
		// �������ƵĻ��ƿ��
		gestureView.setGestureStrokeWidth(4);
		// Ϊgesture����������¼����¼�������
		gestureView.addOnGesturePerformedListener(
			new OnGesturePerformedListener()
			{
				@Override
				public void onGesturePerformed(GestureOverlayView overlay,
					final Gesture gesture)
				{
					// ����save.xml���沼�ִ������ͼ
					View saveDialog = getLayoutInflater().inflate(
						R.layout.save, null);
					// ��ȡsaveDialog���show���
					ImageView imageView = (ImageView) saveDialog
						.findViewById(R.id.show);
					// ��ȡsaveDialog���gesture_name���
					final EditText gestureName = (EditText) saveDialog
						.findViewById(R.id.gesture_name);
					// ����Gesture���������ƴ���һ��λͼ
					Bitmap bitmap = gesture.toBitmap(128, 
						128, 10, 0xffff0000);
					imageView.setImageBitmap(bitmap);
					// ʹ�öԻ�����ʾsaveDialog���
					new AlertDialog.Builder(AddGesture.this)
					.setView(saveDialog)
					.setPositiveButton("����", new OnClickListener()
					{
						@Override
						public void onClick(DialogInterface dialog,
							int which)
						{
							// ��ȡָ���ļ���Ӧ�����ƿ�
							GestureLibrary gestureLib = GestureLibraries
								.fromFile("/mnt/sdcard/mygestures");
							// �������
							gestureLib.addGesture(gestureName.getText()
								.toString(), gesture);
							// �������ƿ�
							gestureLib.save();
						}
					}).setNegativeButton("ȡ��", null).show();
				}
			});
	}
}