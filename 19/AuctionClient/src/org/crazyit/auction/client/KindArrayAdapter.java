/**
 *
 */
package org.crazyit.auction.client;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

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
public class KindArrayAdapter extends BaseAdapter
{
	// ��Ҫ��װ��JSONArray
	private JSONArray kindArray;
	private Context ctx;
	public KindArrayAdapter(JSONArray kindArray
		,Context ctx)
	{
		this.kindArray = kindArray;
		this.ctx = ctx;
	}
	@Override
	public int getCount()
	{
		// ����ListView�������б��������
		return kindArray.length();
	}

	@Override
	public Object getItem(int position)
	{
		// ��ȡָ���б�������װ��JSONObject
		return kindArray.optJSONObject(position);
	}

	@Override
	public long getItemId(int position)
	{
		try
		{
			return ((JSONObject) getItem(position)).getInt("id");
		}
		catch (JSONException e)
		{
			e.printStackTrace();
		}
		return -1;
	}

	@Override
	public View getView(int position, View convertView,
		ViewGroup parent)
	{
		// ����һ�����Բ��ֹ�����
		LinearLayout container = new LinearLayout(ctx);
		// ����Ϊˮƽ�����Բ��ֹ�����
		container.setOrientation(1);
		// ����һ�����Բ��ֹ�����
		LinearLayout linear = new LinearLayout(ctx);
		// ����Ϊˮƽ�����Բ��ֹ�����
		linear.setOrientation(0);
		// ����һ��ImageView
		ImageView iv = new ImageView(ctx);
		iv.setPadding(10, 0, 20, 0);
		iv.setImageResource(R.drawable.item);
		// ��ͼƬ��ӵ�LinearLayout��
		linear.addView(iv);
		// ����һ��TextView
		TextView tv = new TextView(ctx);
		try
		{
			// ��ȡJSONArray����Ԫ�ص�kindName����
			String kindName = ((JSONObject)getItem(position))
				.getString("kindName");
			// ����TextView����ʾ������
			tv.setText(kindName);
		}
		catch (JSONException e)
		{
			e.printStackTrace();
		}
		tv.setTextSize(20);
		// ��TextView��ӵ�LinearLayout��
		linear.addView(tv);
		container.addView(linear);
		// ����һ���ı�������ʾ��������
		TextView descView = new TextView(ctx);
		descView.setPadding(30, 0, 0, 0);
		try
		{
			// ��ȡJSONArray����Ԫ�ص�kindDesc����
			String kindDesc = ((JSONObject)getItem(position))
				.getString("kindDesc");
			descView.setText(kindDesc);
		}
		catch (JSONException e)
		{
			e.printStackTrace();
		}
		descView.setTextSize(16);
		container.addView(descView);
		return container;
	}
}
