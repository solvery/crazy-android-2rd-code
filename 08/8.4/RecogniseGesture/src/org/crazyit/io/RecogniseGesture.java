package org.crazyit.io;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.gesture.Gesture;
import android.gesture.GestureLibraries;
import android.gesture.GestureLibrary;
import android.gesture.GestureOverlayView;
import android.gesture.Prediction;
import android.gesture.GestureOverlayView.OnGesturePerformedListener;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Toast;

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
public class RecogniseGesture extends Activity
{
	// �������Ʊ༭���
	GestureOverlayView gestureView;
	// ��¼�ֻ������е����ƿ�
	GestureLibrary gestureLibrary;

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		// ��ȡ��һ�����������������ƿ�
		gestureLibrary = GestureLibraries
			.fromFile("/mnt/sdcard/mygestures");
		if (gestureLibrary.load())
		{
			Toast.makeText(RecogniseGesture.this, "�����ļ�װ�سɹ���",
				Toast.LENGTH_LONG).show();
		}
		else
		{
			Toast.makeText(RecogniseGesture.this, "�����ļ�װ��ʧ�ܣ�",
				Toast.LENGTH_LONG).show();
		}
		// ��ȡ���Ʊ༭���
		gestureView = (GestureOverlayView) findViewById(R.id.gesture);
		// Ϊ���Ʊ༭������¼�������
		gestureView.addOnGesturePerformedListener(
			new OnGesturePerformedListener()
			{
				@Override
				public void onGesturePerformed(GestureOverlayView
					overlay, Gesture gesture)
				{
					// ʶ���û��ո������Ƶ�����
					ArrayList<Prediction> predictions = gestureLibrary
						.recognize(gesture);
					ArrayList<String> result = new ArrayList<String>();
					// ���������ҵ���Prediction����
					for (Prediction pred : predictions)
					{
						// ֻ�����ƶȴ���2.0�����ƲŻᱻ���
						if (pred.score > 2.0)
						{
							result.add("�����ơ�" + pred.name + "�����ƶ�Ϊ"
								+ pred.score);
						}
					}
					if (result.size() > 0)
					{
						ArrayAdapter<Object> adapter = new 
							ArrayAdapter<Object>(RecogniseGesture.this,
							android.R.layout.simple_dropdown_item_1line
							, result.toArray());
						// ʹ��һ����List�ĶԻ�������ʾ����ƥ�������
						new AlertDialog.Builder(RecogniseGesture.this)
							.setAdapter(adapter, null)
							.setPositiveButton("ȷ��", null).show();
					}
					else
					{
						Toast.makeText(RecogniseGesture.this
							, "�޷��ҵ���ƥ������ƣ�",
							Toast.LENGTH_LONG).show();
					}
				}
			});
	}
}