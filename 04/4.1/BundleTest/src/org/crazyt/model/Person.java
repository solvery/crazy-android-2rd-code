/**
 *
 */
package org.crazyt.model;

import java.io.Serializable;

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
public class Person implements Serializable
{
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;
	private String name;
	private String pass;
	private String gender;

	public Person()
	{
	}

	/**
	 * @param name
	 * @param pass
	 * @param gender
	 */
	public Person(String name, String pass, String gender)
	{
		this.name = name;
		this.pass = pass;
		this.gender = gender;
	}
	public Integer getId()
	{
		return id;
	}
	public void setId(Integer id)
	{
		this.id = id;
	}
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public String getPass()
	{
		return pass;
	}
	public void setPass(String pass)
	{
		this.pass = pass;
	}
	public String getGender()
	{
		return gender;
	}
	public void setGender(String gender)
	{
		this.gender = gender;
	}

}
