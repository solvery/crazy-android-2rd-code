/**
 *
 */
package org.crazyit.opengl;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.opengl.GLSurfaceView.Renderer;

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
public class MyRenderer implements Renderer
{
	float[] triangleData = new float[] {
		0.1f, 0.6f , 0.0f , // �϶���
		-0.3f, 0.0f , 0.0f , // �󶥵�
		0.3f, 0.1f , 0.0f  // �Ҷ���
	};
	int[] triangleColor = new int[] {
		65535, 0, 0, 0, // �϶����ɫ
		0, 65535, 0, 0, // �󶥵���ɫ
		0, 0, 65535, 0 // �Ҷ�����ɫ
	};
	float[] rectData = new float[] {
		0.4f, 0.4f , 0.0f, // ���϶���
		0.4f, -0.4f , 0.0f, // ���¶���
		-0.4f, 0.4f , 0.0f, // ���϶���
		-0.4f, -0.4f , 0.0f // ���¶���
	};
	int[] rectColor = new int[] {
		0, 65535, 0, 0, // ���϶�����ɫ
		0, 0, 65535, 0, // ���¶�����ɫ
		65535, 0, 0, 0, // ���϶����ɫ
		65535, 65535, 0, 0 // ���¶����ɫ
	};
	// ��Ȼ�������ε��ĸ����㣬ֻ��˳�򽻻���һ��
	float[] rectData2 = new float[] {
		-0.4f, 0.4f , 0.0f, // ���϶���
		0.4f, 0.4f , 0.0f, // ���϶���
		0.4f, -0.4f , 0.0f, // ���¶���
		-0.4f, -0.4f , 0.0f // ���¶���
	};
	float[] pentacle = new float[]{
		0.4f , 0.4f , 0.0f,
		-0.2f , 0.3f , 0.0f,
		0.5f , 0.0f , 0f,
		-0.4f , 0.0f , 0f,
		-0.1f, -0.3f , 0f
	};
	FloatBuffer triangleDataBuffer;
	IntBuffer triangleColorBuffer;
	FloatBuffer rectDataBuffer;
	IntBuffer rectColorBuffer;
	FloatBuffer rectDataBuffer2;
	FloatBuffer pentacleBuffer;
	public MyRenderer()
	{
		// ������λ����������ת����FloatBuffer;
		triangleDataBuffer = floatBufferUtil(triangleData);
		rectDataBuffer = floatBufferUtil(rectData);
		rectDataBuffer2 = floatBufferUtil(rectData2);
		pentacleBuffer = floatBufferUtil(pentacle);
		// ��������ɫ��������ת����IntBuffer;
		triangleColorBuffer = intBufferUtil(triangleColor);
		rectColorBuffer = intBufferUtil(rectColor);
	}
	@Override
	public void onSurfaceCreated(GL10 gl, EGLConfig config)
	{
		// �رտ�����
		gl.glDisable(GL10.GL_DITHER);
		// ����ϵͳ��͸�ӽ�������
		gl.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT
			, GL10.GL_FASTEST);
		gl.glClearColor(0, 0, 0, 0);
		// ������Ӱƽ��ģʽ
		gl.glShadeModel(GL10.GL_SMOOTH);
		// ������Ȳ���
		gl.glEnable(GL10.GL_DEPTH_TEST);
		// ������Ȳ��Ե�����
		gl.glDepthFunc(GL10.GL_LEQUAL);
	}
	@Override
	public void onSurfaceChanged(GL10 gl, int width, int height)
	{
		// ����3D�Ӵ��Ĵ�С��λ��
		gl.glViewport(0, 0, width, height);
		// ����ǰ����ģʽ��ΪͶӰ����
		gl.glMatrixMode(GL10.GL_PROJECTION);
		// ��ʼ����λ����
		gl.glLoadIdentity();
		// ����͸���Ӵ��Ŀ�ȡ��߶ȱ�
		float ratio = (float) width / height;
		// ���ô˷�������͸���Ӵ��Ŀռ��С��
		gl.glFrustumf(-ratio, ratio, -1, 1, 1, 10);
	}
	// ����ͼ�εķ���
	@Override
	public void onDrawFrame(GL10 gl)
	{
		// �����Ļ�������Ȼ���
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
		// ���ö�����������
		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
		// ���ö�����ɫ����
		gl.glEnableClientState(GL10.GL_COLOR_ARRAY);
		// ���õ�ǰ�����ջΪģ�Ͷ�ջ��
		gl.glMatrixMode(GL10.GL_MODELVIEW);
		// --------------------���Ƶ�һ��ͼ��---------------------
		// ���õ�ǰ��ģ����ͼ����
		gl.glLoadIdentity();
		gl.glTranslatef(-0.32f, 0.35f, -1f);  //��
		// ���ö����λ������
		gl.glVertexPointer(3, GL10.GL_FLOAT, 0, triangleDataBuffer);
		// ���ö������ɫ����
		gl.glColorPointer(4, GL10.GL_FIXED, 0, triangleColorBuffer);
		// ���ݶ������ݻ���ƽ��ͼ��
		gl.glDrawArrays(GL10.GL_TRIANGLES, 0, 3);
		// --------------------���Ƶڶ���ͼ��---------------------
		// ���õ�ǰ��ģ����ͼ����
		gl.glLoadIdentity();
		gl.glTranslatef(0.6f, 0.8f, -1.5f);
		// ���ö����λ������
		gl.glVertexPointer(3, GL10.GL_FLOAT, 0, rectDataBuffer);
		// ���ö������ɫ����
		gl.glColorPointer(4, GL10.GL_FIXED, 0, rectColorBuffer);
		// ���ݶ������ݻ���ƽ��ͼ��
		gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 0, 4);
		// --------------------���Ƶ�3��ͼ��---------------------
		// ���õ�ǰ��ģ����ͼ����
		gl.glLoadIdentity();
		gl.glTranslatef(-0.4f, -0.5f, -1.5f);
		// ���ö����λ�����ݣ���Ȼʹ��֮ǰ�Ķ�����ɫ��
		gl.glVertexPointer(3, GL10.GL_FLOAT, 0, rectDataBuffer2);
		// ���ݶ������ݻ���ƽ��ͼ��
		gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 0, 4);
		// --------------------���Ƶ�4��ͼ��---------------------
		// ���õ�ǰ��ģ����ͼ����
		gl.glLoadIdentity();
		gl.glTranslatef(0.4f, -0.5f, -1.5f);
		// ����ʹ�ô�ɫ���
		gl.glColor4f(1.0f, 0.2f, 0.2f, 0.0f);  //��
		gl.glDisableClientState(GL10.GL_COLOR_ARRAY);
		// ���ö����λ������
		gl.glVertexPointer(3, GL10.GL_FLOAT, 0, pentacleBuffer);
		// ���ݶ������ݻ���ƽ��ͼ��
		gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 0, 5);
		// ���ƽ���
		gl.glFinish();
		gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
	}
	// ����һ�����߷�������int[]����ת��ΪOpenGL ES�����IntBuffer
	private IntBuffer intBufferUtil(int[] arr)
	{
		IntBuffer mBuffer;
		// ��ʼ��ByteBuffer������Ϊarr����ĳ���*4����Ϊһ��intռ4���ֽ�
		ByteBuffer qbb = ByteBuffer.allocateDirect(arr.length * 4);
		// ����������nativeOrder
		qbb.order(ByteOrder.nativeOrder());
		mBuffer = qbb.asIntBuffer();
		mBuffer.put(arr);
		mBuffer.position(0);
		return mBuffer;
	}
	// ����һ�����߷�������float[]����ת��ΪOpenGL ES�����FloatBuffer
	private FloatBuffer floatBufferUtil(float[] arr)
	{
		FloatBuffer mBuffer;
		// ��ʼ��ByteBuffer������Ϊarr����ĳ���*4����Ϊһ��intռ4���ֽ�
		ByteBuffer qbb = ByteBuffer.allocateDirect(arr.length * 4);
		// ����������nativeOrder
		qbb.order(ByteOrder.nativeOrder());
		mBuffer = qbb.asFloatBuffer();
		mBuffer.put(arr);
		mBuffer.position(0);
		return mBuffer;
	}
}

