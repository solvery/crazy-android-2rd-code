package org.crazyit.sound;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.graphics.ImageFormat;
import android.hardware.Camera;
import android.hardware.Camera.AutoFocusCallback;
import android.hardware.Camera.PictureCallback;
import android.hardware.Camera.ShutterCallback;
import android.os.Bundle;
import android.os.Environment;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;

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
public class CaptureImage extends Activity
{
	SurfaceView sView;
	SurfaceHolder surfaceHolder;
	int screenWidth, screenHeight;
	// ����ϵͳ���õ������
	Camera camera;
	// �Ƿ���Ԥ����
	boolean isPreview = false;

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		// ����ȫ��
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
			WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.main);
		// ��ȡ���ڹ�����
		WindowManager wm = getWindowManager();
		Display display = wm.getDefaultDisplay();
		DisplayMetrics metrics = new DisplayMetrics();
		// ��ȡ��Ļ�Ŀ�͸�
		display.getMetrics(metrics);
		screenWidth = metrics.widthPixels;
		screenHeight = metrics.heightPixels;
		// ��ȡ������SurfaceView���
		sView = (SurfaceView) findViewById(R.id.sView);
		// ���ø�Surface����Ҫ�Լ�ά��������
		sView.getHolder().setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
		// ���SurfaceView��SurfaceHolder
		surfaceHolder = sView.getHolder();
		// ΪsurfaceHolder���һ���ص�������
		surfaceHolder.addCallback(new Callback()
		{
			@Override
			public void surfaceChanged(SurfaceHolder holder, int format,
				int width, int height)
			{
			}

			@Override
			public void surfaceCreated(SurfaceHolder holder)
			{
				// ������ͷ
				initCamera();
			}

			@Override
			public void surfaceDestroyed(SurfaceHolder holder)
			{
				// ���camera��Ϊnull ,�ͷ�����ͷ
				if (camera != null)
				{
					if (isPreview) camera.stopPreview();
					camera.release();
					camera = null;
				}
			}
		});
	}

	private void initCamera()
	{
		if (!isPreview)
		{
			// �˴�Ĭ�ϴ򿪺�������ͷ��
			// ͨ������������Դ�ǰ������ͷ
			camera = Camera.open(0);  //��
			camera.setDisplayOrientation(90);
		}
		if (camera != null && !isPreview)
		{
			try
			{
				Camera.Parameters parameters = camera.getParameters();
				// ����Ԥ����Ƭ�Ĵ�С
				parameters.setPreviewSize(screenWidth, screenHeight);
				// ����Ԥ����Ƭʱÿ����ʾ����֡����Сֵ�����ֵ
				parameters.setPreviewFpsRange(4, 10);
				// ����ͼƬ��ʽ
				parameters.setPictureFormat(ImageFormat.JPEG);
				// ����JPG��Ƭ������
				parameters.set("jpeg-quality", 85);
				// ������Ƭ�Ĵ�С
				parameters.setPictureSize(screenWidth, screenHeight);
				// ͨ��SurfaceView��ʾȡ������
				camera.setPreviewDisplay(surfaceHolder);  //��
				// ��ʼԤ��
				camera.startPreview();  //��
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
			isPreview = true;
		}
	}

	public void capture(View source)
	{
		if (camera != null)
		{
			// ��������ͷ�Զ��Խ��������
			camera.autoFocus(autoFocusCallback);  //��
		}
	}

	AutoFocusCallback autoFocusCallback = new AutoFocusCallback()
	{
		// ���Զ��Խ�ʱ�����÷���
		@Override
		public void onAutoFocus(boolean success, Camera camera)
		{
			if (success)
			{
				// takePicture()������Ҫ����3������������
				// ��1�������������û����¿���ʱ�����ü�����
				// ��2�����������������ȡԭʼ��Ƭʱ�����ü�����
				// ��3�����������������ȡJPG��Ƭʱ�����ü�����
				camera.takePicture(new ShutterCallback()
				{
					public void onShutter()
					{
						// ���¿���˲���ִ�д˴�����
					}
				}, new PictureCallback()
				{
					public void onPictureTaken(byte[] data, Camera c)
					{
						// �˴�������Ծ����Ƿ���Ҫ����ԭʼ��Ƭ��Ϣ
					}
				}, myJpegCallback);  //��
			}
		}
	};

	PictureCallback myJpegCallback = new PictureCallback()
	{
		@Override
		public void onPictureTaken(byte[] data, Camera camera)
		{
			// �����������õ����ݴ���λͼ
			final Bitmap bm = BitmapFactory.decodeByteArray(data, 0,
				data.length);
			// ����/layout/save.xml�ļ���Ӧ�Ĳ�����Դ
			View saveDialog = getLayoutInflater().inflate(R.layout.save,
				null);
			final EditText photoName = (EditText) saveDialog
				.findViewById(R.id.phone_name);
			// ��ȡsaveDialog�Ի����ϵ�ImageView���
			ImageView show = (ImageView) saveDialog
				.findViewById(R.id.show);
			// ��ʾ�ո��ĵõ���Ƭ
			show.setImageBitmap(bm);
			// ʹ�öԻ�����ʾsaveDialog���
			new AlertDialog.Builder(CaptureImage.this).setView(saveDialog)
				.setPositiveButton("����", new OnClickListener()
				{
					@Override
					public void onClick(DialogInterface dialog, int which)
					{
						// ����һ��λ��SD���ϵ��ļ�
						File file = new File(Environment
							.getExternalStorageDirectory(), photoName
							.getText().toString() + ".jpg");
						FileOutputStream outStream = null;
						try
						{
							// ��ָ���ļ���Ӧ�������
							outStream = new FileOutputStream(file);
							// ��λͼ�����ָ���ļ���
							bm.compress(CompressFormat.JPEG, 100,
								outStream);
							outStream.close();
						}
						catch (IOException e)
						{
							e.printStackTrace();
						}
					}
				}).setNegativeButton("ȡ��", null).show();
			// �������
			camera.stopPreview();
			camera.startPreview();
			isPreview = true;
		}
	};
}

