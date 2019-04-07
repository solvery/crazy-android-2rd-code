/**
 *
 */
package org.crazyit.auction.client;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONException;

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
public class JSONArrayAdapter extends BaseAdapter
{
	private Context ctx;
	// ������Ҫ��װ��JSONArray����
	private JSONArray jsonArray;
	// �����б�����ʾJSONObject������ĸ�����
	private String property;
	private boolean hasIcon;
	public JSONArrayAdapter(Context ctx
		, JSONArray jsonArray, String property
		, boolean hasIcon)
	{
		this.ctx = ctx;
		this.jsonArray = jsonArray;
		this.property = property;
		this.hasIcon = hasIcon;
	}

	@Override
	public int getCount()
	{
		return jsonArray.length();
	}

	@Override
	public Object getItem(int position)
	{
		return jsonArray.optJSONObject(position);
	}

	@Override
	public long getItemId(int position)
	{
		try
		{
			// ������Ʒ��ID
			return ((JSONObject)getItem(position)).getInt("id");
		}
		catch (JSONException e)
		{
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{
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
			// ��ȡJSONArray����Ԫ�ص�property����
			String itemName = ((JSONObject)getItem(position))
				.getString(property);
			// ����TextView����ʾ������
			tv.setText(itemName);
		}
		catch (JSONException e)
		{
			e.printStackTrace();
		}

		tv.setTextSize(20);
		if (hasIcon)
		{
			// ��TextView��ӵ�LinearLayout��
			linear.addView(tv);
			return linear;
		}
		else
		{
			return tv;
		}
	}
}