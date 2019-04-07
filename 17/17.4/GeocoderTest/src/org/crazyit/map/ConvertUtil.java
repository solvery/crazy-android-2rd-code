/**
 *
 */
package org.crazyit.map;

import java.io.InputStreamReader;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

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
public class ConvertUtil
{
	// ���ݵ�ַ��ȡ��Ӧ�ľ�γ��
	public static double[] getLocationInfo(final String address)
	{
		FutureTask<double[]> task = new FutureTask<double[]>(
			new Callable<double[]>()
		{
			public double[] call() throws Exception
			{
				// ����һ��HttpClient��������ָ����ַ��������
				HttpClient client = new DefaultHttpClient();
				// ��ָ����ַ����GET����
				HttpGet httpGet = new HttpGet("http://maps.google.com/maps/"
					+ "api/geocode/json?address=" + address
					+ "ka&sensor=false");
				// ����ģ�����������������ڼ������Ļ�������֤���ص���ӦΪ�������ĵ�ַ
				httpGet.addHeader("Accept-Charset" , "GBK;q=0.7,*;q=0.3");
				httpGet.addHeader("Accept-Language" , "zh-CN,zh;q=0.8");
				StringBuilder sb = new StringBuilder();
				// ��ȡ����������Ӧ
				HttpResponse response = client.execute(httpGet);
				HttpEntity entity = response.getEntity();
				// ��ȡ��������Ӧ���ַ���
				InputStreamReader br = new InputStreamReader(
					entity.getContent() , "utf-8");
				int b;
				while ((b = br.read()) != -1)
				{
					sb.append((char) b);
				}
				// �����������ص��ַ���ת��ΪJSONObject����
				JSONObject jsonObject = new JSONObject(sb.toString());
				// ��JSONObject������ȡ������λ�õ�location����
				JSONObject location = jsonObject.getJSONArray("results")
					.getJSONObject(0)
					.getJSONObject("geometry").getJSONObject("location");
				// ��ȡ������Ϣ
				double longitude = location.getDouble("lng");
				// ��ȡγ����Ϣ
				double latitude = location.getDouble("lat");
				// �����ȡ�γ����Ϣ���double[]����
				return new double[]{longitude , latitude};
			}
		});
		new Thread(task).start();
		try
		{
			return task.get();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}
	// ���ݾ�γ�Ȼ�ȡ��Ӧ�ĵ�ַ
	public static String getAddress(final double longitude
		, final double latitude)
	{
		FutureTask<String> task = new FutureTask<String>(
			new Callable<String>()
		{
			public String call() throws Exception
			{
				// ����һ��HttpClient��������ָ����ַ��������
				HttpClient client = new DefaultHttpClient();
				// ��ָ����ַ����GET����
				HttpGet httpGet = new HttpGet("http://maps.google.com/maps/"
					+ "api/geocode/json?latlng="
					+ latitude + "," + longitude
					+ "&sensor=false");
				// ����ģ�����������������ڼ������Ļ�������֤���ص���ӦΪ�������ĵ�ַ
				httpGet.addHeader("Accept-Charset" , "GBK;q=0.7,*;q=0.3");
				httpGet.addHeader("Accept-Language" , "zh-CN,zh;q=0.8");
				StringBuilder sb = new StringBuilder();
				// ִ������
				HttpResponse response = client.execute(httpGet);
				HttpEntity entity = response.getEntity();
				// ��ȡ��������Ӧ���ַ���
				InputStreamReader br = new InputStreamReader(
					entity.getContent() , "utf-8");
				int b;
				while ((b = br.read()) != -1)
				{
					sb.append((char) b);
				}
				// �ѷ�������Ӧ���ַ���ת��ΪJSONObject
				JSONObject jsonObj = new JSONObject(sb.toString());
				// ��������Ӧ����еĵ�ַ����
				return jsonObj.getJSONArray("results").getJSONObject(0)
					.getString("formatted_address");
			}
		});
		new Thread(task).start();
		try
		{
			return task.get();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}
}
