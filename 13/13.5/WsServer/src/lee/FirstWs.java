package lee;

import java.util.*;

import javax.xml.bind.annotation.adapters.*;
import javax.jws.*;

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
//��@WebService Annotation��ע�������ýӿڽ���Ӧһ��Web Services
@WebService
public interface FirstWs
{
	//����һ���������÷���������¶��һ��Web Services����
	List<User> getUserList(String base);
}