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
	// ��������׵��4������
	float[] taperVertices = new float[] {
		0.0f, 0.5f, 0.0f,
		-0.5f, -0.5f, -0.2f,
		0.5f, -0.5f, -0.2f,
		0.0f, -0.2f, 0.2f
	};
	// ��������׵��4���������ɫ
	int[] taperColors = new int[]{
		65535, 0, 0, 0,  // ��ɫ
		0, 65535, 0, 0,	 // ��ɫ
		0, 0, 65535, 0,  // ��ɫ
		65535, 65535, 0, 0 //��ɫ
	};
	// ��������׵��4��������
	private byte[] taperFacets = new byte[]{
		0, 1, 2, //0��1��2�����������һ����
		0, 1, 3, //0��1��3�����������һ����
		1, 2, 3, //1��2��3�����������һ����
		0, 2, 3  //0��2��3�����������һ����
	};
	// �����������8������
	float[] cubeVertices = new float[] {
		// �϶��������ε��ĸ�����
		0.5f, 0.5f, 0.5f,
		0.5f, -0.5f, 0.5f,
		-0.5f, -0.5f, 0.5f,
		-0.5f, 0.5f, 0.5f,
		// �µ��������ε��ĸ�����
		0.5f, 0.5f, -0.5f,
		0.5f, -0.5f, -0.5f,
		-0.5f, -0.5f, -0.5f,
		-0.5f, 0.5f, -0.5f
	};
	// ��������������Ҫ��6���棨һ����12������������Ķ��㣩
	private byte[] cubeFacets = new byte[]{
		0, 1, 2,
		0, 2, 3,
		2, 3, 7,
		2, 6, 7,
		0, 3, 7,
		0, 4, 7,
		4, 5, 6,
		4, 6, 7,
		0, 1, 4,
		1, 4, 5,
		1, 2, 6,
		1, 5, 6
	};
	// ����Open GL ES��������Ҫ��Buffer����
	FloatBuffer taperVerticesBuffer;
	IntBuffer taperColorsBuffer;
	ByteBuffer taperFacetsBuffer;
	FloatBuffer cubeVerticesBuffer;
	ByteBuffer cubeFacetsBuffer;
	// ������ת�ĽǶ�
	private float rotate;
	public MyRenderer()
	{
		// ������׵�Ķ���λ�����������װ��FloatBuffer;
		taperVerticesBuffer = floatBufferUtil(taperVertices);
		// ������׵���ĸ���������װ��ByteBuffer
		taperFacetsBuffer = ByteBuffer.wrap(taperFacets);
		// ������׵���ĸ��������ɫ�����װ��IntBuffer
		taperColorsBuffer = intBufferUtil(taperColors);
		// ��������Ķ���λ�����������װ��FloatBuffer;
		cubeVerticesBuffer = floatBufferUtil(cubeVertices);
		// ���������6���棨12�������Σ��������װ��ByteBuffer
		cubeFacetsBuffer = ByteBuffer.wrap(cubeFacets);
	}

	@Override
	public void onSurfaceCreated(GL10 gl, EGLConfig config)
	{
		// �رտ�����
		gl.glDisable(GL10.GL_DITHER);
		// ����ϵͳ��͸�ӽ�������
		gl.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT, GL10.GL_FASTEST);
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
		// ���õ�ǰ����ģʽΪģ����ͼ��
		gl.glMatrixMode(GL10.GL_MODELVIEW);
		// --------------------���Ƶ�һ��ͼ��---------------------
		// ���õ�ǰ��ģ����ͼ����
		gl.glLoadIdentity();
		gl.glTranslatef(-0.6f, 0.0f, -1.5f);
		// ����Y����ת
		gl.glRotatef(rotate, 0f, 0.2f, 0f);
		// ���ö����λ������
		gl.glVertexPointer(3, GL10.GL_FLOAT, 0, taperVerticesBuffer);
		// ���ö������ɫ����
		gl.glColorPointer(4, GL10.GL_FIXED, 0, taperColorsBuffer);
		// ��taperFacetsBufferָ���������������
		gl.glDrawElements(GL10.GL_TRIANGLE_STRIP
			, taperFacetsBuffer.remaining(),
			GL10.GL_UNSIGNED_BYTE, taperFacetsBuffer);
		// --------------------���Ƶڶ���ͼ��---------------------
		// ���õ�ǰ��ģ����ͼ����
		gl.glLoadIdentity();
		gl.glTranslatef(0.7f, 0.0f, -2.2f);
		// ����Y����ת
		gl.glRotatef(rotate, 0f, 0.2f, 0f);
		// ����X����ת
		gl.glRotatef(rotate, 1f, 0f, 0f);
		// ���ö����λ������
		gl.glVertexPointer(3, GL10.GL_FLOAT, 0, cubeVerticesBuffer);
		// �����ö������ɫ���ݣ�������ǰ����ɫ����
		// ��cubeFacetsBufferָ���������������
		gl.glDrawElements(GL10.GL_TRIANGLE_STRIP
			, cubeFacetsBuffer.remaining(),
			GL10.GL_UNSIGNED_BYTE, cubeFacetsBuffer);

		// ���ƽ���
		gl.glFinish();
		gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
		// ��ת�Ƕ�����1
		rotate+=1;
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
