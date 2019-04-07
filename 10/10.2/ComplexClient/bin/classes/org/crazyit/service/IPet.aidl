package org.crazyit.service;

import org.crazyit.service.Pet;
import org.crazyit.service.Person;

/**
 * Description:
 * <br/>��վ: <a href="http://www.crazyit.org">���Java����</a> 
 * <br/>Copyright (C), 2001-2012, Yeeku.H.Lee
 * <br/>This program is protected by copyright laws.
 * <br/>Program Name:
 * <br/>Date:
 * @author  Yeeku.H.Lee kongyeeku@163.com
 * @version  1.0
 */
interface IPet
{
	// ����һ��Person������Ϊ�������
	List<Pet> getPets(in Person owner);
}