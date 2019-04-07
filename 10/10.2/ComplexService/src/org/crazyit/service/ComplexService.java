/**
 *
 */
package org.crazyit.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.crazyit.service.IPet.Stub;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

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
public class ComplexService extends Service
{
	private PetBinder petBinder;
	private static Map<Person , List<Pet>> pets
		= new HashMap<Person , List<Pet>>();
	static
	{
		// ��ʼ��pets Map����
		ArrayList<Pet> list1 = new ArrayList<Pet>();
		list1.add(new Pet("����" , 4.3));
		list1.add(new Pet("����" , 5.1));
		pets.put(new Person(1, "sun" , "sun") , list1);
		ArrayList<Pet> list2 = new ArrayList<Pet>();
		list2.add(new Pet("kitty" , 2.3));
		list2.add(new Pet("garfield" , 3.1));
		pets.put(new Person(2, "bai" , "bai") , list2);
	}
	// �̳�Stub��Ҳ����ʵ�ֶ�IPet�ӿڣ���ʵ����IBinder�ӿ�
	public class PetBinder extends Stub
	{
		@Override
		public List<Pet> getPets(Person owner) throws RemoteException
		{
			// ����Service�ڲ�������
			return pets.get(owner);
		}
	}
	@Override
	public void onCreate()
	{
		super.onCreate();
		petBinder = new PetBinder();
	}
	@Override
	public IBinder onBind(Intent arg0)
	{
		/* ����catBinder����
		 * �ڰ󶨱���Service������£���catBinder�����ֱ��
		 * �����ͻ��˵�ServiceConnection����
		 * ��onServiceConnected�����ĵڶ���������
		 * �ڰ�Զ��Service������£�ֻ��catBinder����Ĵ���
		 * �����ͻ��˵�ServiceConnection����
		 * ��onServiceConnected�����ĵڶ���������
		 */
		return petBinder; //��
	}
	@Override
	public void onDestroy()
	{
	}
}
