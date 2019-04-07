package org.crazyit.app;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
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
public class LifecycleFragment extends Fragment
{
	final String TAG = "--CrazyIt--";

	@Override
	public void onAttach(Activity activity)
	{
		super.onAttach(activity);
		// �����־
		Log.d(TAG, "-------onAttach------");
	}

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		// �����־
		Log.d(TAG, "-------onCreate------");
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle data)
	{
		// �����־
		Log.d(TAG, "-------onCreateView------");		
		TextView tv = new TextView(getActivity());
		tv.setGravity(Gravity.CENTER_HORIZONTAL);
		tv.setText("����Fragment");
		tv.setTextSize(40);
		return tv;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState)
	{
		super.onActivityCreated(savedInstanceState);
		// �����־
		Log.d(TAG, "-------onActivityCreated------");
	}

	@Override
	public void onStart()
	{
		super.onStart();
		// �����־
		Log.d(TAG, "-------onStart------");
	}

	@Override
	public void onResume()
	{
		super.onResume();
		// �����־
		Log.d(TAG, "-------onResume------");
	}

	@Override
	public void onPause()
	{
		super.onPause();
		// �����־
		Log.d(TAG, "-------onPause------");
	}

	@Override
	public void onStop()
	{
		super.onStop();
		// �����־
		Log.d(TAG, "-------onStop------");
	}

	@Override
	public void onDestroyView()
	{
		super.onDestroyView();
		// �����־
		Log.d(TAG, "-------onDestroyView------");
	}

	@Override
	public void onDestroy()
	{
		super.onDestroy();
		// �����־
		Log.d(TAG, "-------onDestroy------");
	}

	@Override
	public void onDetach()
	{
		super.onDetach();
		// �����־
		Log.d(TAG, "-------onDetach------");
	}
}
