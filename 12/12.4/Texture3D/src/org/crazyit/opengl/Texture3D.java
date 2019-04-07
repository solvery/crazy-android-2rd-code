package org.crazyit.opengl;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLSurfaceView;
import android.opengl.GLUtils;
import android.opengl.GLSurfaceView.Renderer;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;

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
public class Texture3D extends Activity
	implements OnGestureListener
{
	// ������ת�Ƕ�
	private float anglex = 0f;
	private float angley = 0f;
	static final float ROTATE_FACTOR = 60;
	// �������Ƽ����ʵ��
	GestureDetector detector;

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		// ����һ��GLSurfaceView��������ʾOpenGL���Ƶ�ͼ��
		GLSurfaceView glView = new GLSurfaceView(this);
		// ����GLSurfaceView�����ݻ�����
		MyRenderer myRender = new MyRenderer(this);
		// ΪGLSurfaceView���û�����
		glView.setRenderer(myRender);
		setContentView(glView);
		// �������Ƽ����
		detector = new GestureDetector(this , this);
	}

	@Override
	public boolean onTouchEvent(MotionEvent me)
	{
		// ����Activity�ϵĴ����¼�����GestureDetector����
		return detector.onTouchEvent(me);
	}

	@Override
	public boolean onFling(MotionEvent event1, MotionEvent event2,
		float velocityX, float velocityY)
	{
		velocityX = velocityX > 2000 ? 2000 : velocityX;
		velocityX = velocityX < -2000 ? -2000 : velocityX;
		velocityY = velocityY > 2000 ? 2000 : velocityY;
		velocityY = velocityY < -2000 ? -2000 : velocityY;
		// ���ݺ����ϵ��ٶȼ�����Y����ת�ĽǶ�
		angley += velocityX * ROTATE_FACTOR / 4000;
		// ���������ϵ��ٶȼ�����X����ת�ĽǶ�
		anglex += velocityY * ROTATE_FACTOR / 4000;
		return true;
	}

	@Override
	public boolean onDown(MotionEvent arg0)
	{
		return false;
	}

	@Override
	public void onLongPress(MotionEvent event)
	{
	}

	@Override
	public boolean onScroll(MotionEvent event1, MotionEvent event2,
		float distanceX, float distanceY)
	{
		return false;
	}

	@Override
	public void onShowPress(MotionEvent event)
	{
	}

	@Override
	public boolean onSingleTapUp(MotionEvent event)
	{
		return false;
	}

	public class MyRenderer implements Renderer
	{
		// ������Ķ������꣨һ����36�����㣬���12�������Σ�
		private float[] cubeVertices = { -0.6f, -0.6f, -0.6f, -0.6f, 0.6f,
			-0.6f, 0.6f, 0.6f, -0.6f, 0.6f, 0.6f, -0.6f, 0.6f, -0.6f, -0.6f,
			-0.6f, -0.6f, -0.6f, -0.6f, -0.6f, 0.6f, 0.6f, -0.6f, 0.6f, 0.6f,
			0.6f, 0.6f, 0.6f, 0.6f, 0.6f, -0.6f, 0.6f, 0.6f, -0.6f, -0.6f,
			0.6f, -0.6f, -0.6f, -0.6f, 0.6f, -0.6f, -0.6f, 0.6f, -0.6f, 0.6f,
			0.6f, -0.6f, 0.6f, -0.6f, -0.6f, 0.6f, -0.6f, -0.6f, -0.6f, 0.6f,
			-0.6f, -0.6f, 0.6f, 0.6f, -0.6f, 0.6f, 0.6f, 0.6f, 0.6f, 0.6f,
			0.6f, 0.6f, -0.6f, 0.6f, 0.6f, -0.6f, -0.6f, 0.6f, 0.6f, -0.6f,
			-0.6f, 0.6f, -0.6f, -0.6f, 0.6f, 0.6f, -0.6f, 0.6f, 0.6f, 0.6f,
			0.6f, 0.6f, 0.6f, 0.6f, -0.6f, -0.6f, 0.6f, -0.6f, -0.6f, -0.6f,
			-0.6f, -0.6f, -0.6f, 0.6f, -0.6f, -0.6f, 0.6f, -0.6f, 0.6f, 0.6f,
			-0.6f, 0.6f, -0.6f, };
		// ��������������Ҫ��6���棨һ����12������������Ķ��㣩
		private byte[] cubeFacets = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12,
			13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29,
			30, 31, 32, 33, 34, 35, };
		// ����������ͼ����������
		private float[] cubeTextures = { 1.0000f, 1.0000f, 1.0000f, 0.0000f,
			0.0000f, 0.0000f, 0.0000f, 0.0000f, 0.0000f, 1.0000f, 1.0000f,
			1.0000f, 0.0000f, 1.0000f, 1.0000f, 1.0000f, 1.0000f, 0.0000f,
			1.0000f, 0.0000f, 0.0000f, 0.0000f, 0.0000f, 1.0000f, 0.0000f,
			1.0000f, 1.0000f, 1.0000f, 1.0000f, 0.0000f, 1.0000f, 0.0000f,
			0.0000f, 0.0000f, 0.0000f, 1.0000f, 0.0000f, 1.0000f, 1.0000f,
			1.0000f, 1.0000f, 0.0000f, 1.0000f, 0.0000f, 0.0000f, 0.0000f,
			0.0000f, 1.0000f, 0.0000f, 1.0000f, 1.0000f, 1.0000f, 1.0000f,
			0.0000f, 1.0000f, 0.0000f, 0.0000f, 0.0000f, 0.0000f, 1.0000f,
			0.0000f, 1.0000f, 1.0000f, 1.0000f, 1.0000f, 0.0000f, 1.0000f,
			0.0000f, 0.0000f, 0.0000f, 0.0000f, 1.0000f };

		private Context context;
		private FloatBuffer cubeVerticesBuffer;
		private ByteBuffer cubeFacetsBuffer;
		private FloatBuffer cubeTexturesBuffer;
		// ���屾������ʹ�õ�����
		private int texture;

		public MyRenderer(Context main)
		{
			this.context = main;
			// ��������Ķ���λ�����������װ��FloatBuffer;
			cubeVerticesBuffer = floatBufferUtil(cubeVertices);
			// ���������6���棨12�������Σ��������װ��ByteBuffer
			cubeFacetsBuffer = ByteBuffer.wrap(cubeFacets);
			// ���������������ͼ���������ݰ�װ��FloatBuffer
			cubeTexturesBuffer = floatBufferUtil(cubeTextures);
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
			// ����2D������ͼ
			gl.glEnable(GL10.GL_TEXTURE_2D);
			// װ������
			loadTexture(gl);
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

		public void onDrawFrame(GL10 gl)
		{
			// �����Ļ�������Ȼ���
			gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
			// ���ö�����������
			gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
			// ������ͼ������������
			gl.glEnableClientState(GL10.GL_TEXTURE_COORD_ARRAY);  //��
			// ���õ�ǰ����ģʽΪģ����ͼ��
			gl.glMatrixMode(GL10.GL_MODELVIEW);
			gl.glLoadIdentity();
			// �ѻ�ͼ����������Ļ2����λ
			gl.glTranslatef(0f, 0.0f, -2.0f);
			// ��תͼ��
			gl.glRotatef(angley, 0, 1, 0);
			gl.glRotatef(anglex, 1, 0, 0);
			// ���ö����λ������
			gl.glVertexPointer(3, GL10.GL_FLOAT, 0, cubeVerticesBuffer);
			// ������ͼ����������
			gl.glTexCoordPointer(2, GL10.GL_FLOAT, 0, cubeTexturesBuffer);//��
			// ִ��������ͼ
			gl.glBindTexture(GL10.GL_TEXTURE_2D, texture);  //��
			// ��cubeFacetsBufferָ���������������
			gl.glDrawElements(GL10.GL_TRIANGLES, cubeFacetsBuffer.remaining(),
				GL10.GL_UNSIGNED_BYTE, cubeFacetsBuffer);
			// ���ƽ���
			gl.glFinish();
			// ���ö��㡢������������
			gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
			gl.glDisableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
			// �����Ƕ�ֵ�Ա�ÿ���Բ�ͬ�ǶȻ���
		}

		private void loadTexture(GL10 gl)
		{
			Bitmap bitmap = null;
			try
			{
				// ����λͼ
				bitmap = BitmapFactory.decodeResource(context.getResources(),
					R.drawable.sand);
				int[] textures = new int[1];
				// ָ������N��������һ������ָ������1��������
				// textures���齫����洢��������Ĵ��š�
				gl.glGenTextures(1, textures, 0);
				// ��ȡtextures���������еĵ�һ������
				texture = textures[0];
				// ֪ͨOpenGL��texture����󶨵�GL10.GL_TEXTURE_2DĿ����
				gl.glBindTexture(GL10.GL_TEXTURE_2D, texture);
				// ����������С�������ӵ��Զʱ����С��ʱ����˲���ʽ
				gl.glTexParameterf(GL10.GL_TEXTURE_2D,
					GL10.GL_TEXTURE_MIN_FILTER, GL10.GL_NEAREST);
				// ���������Ŵ󣨾����ӵ�ܽ�ʱ��������ʱ����˲���ʽ
				gl.glTexParameterf(GL10.GL_TEXTURE_2D,
					GL10.GL_TEXTURE_MAG_FILTER, GL10.GL_LINEAR);
				// �����ں��������϶���ƽ������
				gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_WRAP_S,
					GL10.GL_REPEAT);
				gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_WRAP_T,
					GL10.GL_REPEAT);
				// ����λͼ��������
				GLUtils.texImage2D(GL10.GL_TEXTURE_2D, 0, bitmap, 0);
			}
			finally
			{
				// ��������֮�󣬻���λͼ
				if (bitmap != null)
					bitmap.recycle();
			}
		}
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