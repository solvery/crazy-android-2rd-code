package org.crazyit.opengl;

import android.app.Activity;
import android.opengl.GLSurfaceView;
import android.os.Bundle;

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
public class Simple3D extends Activity
{
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		// ����һ��GLSurfaceView��������ʾOpenGL���Ƶ�ͼ��
		GLSurfaceView glView = new GLSurfaceView(this);
		// ����GLSurfaceView�����ݻ�����
		MyRenderer myRender = new MyRenderer();
		// ΪGLSurfaceView���û�����
		glView.setRenderer(myRender);
		setContentView(glView);
	}
}