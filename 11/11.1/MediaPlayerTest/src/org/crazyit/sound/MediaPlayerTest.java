package org.crazyit.sound;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.audiofx.BassBoost;
import android.media.audiofx.Equalizer;
import android.media.audiofx.PresetReverb;
import android.media.audiofx.Visualizer;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

public class MediaPlayerTest extends Activity
{
	// ���岥��������MediaPlayer
	private MediaPlayer mPlayer;
	// ����ϵͳ��ʾ����
	private Visualizer mVisualizer;	
	// ����ϵͳ�ľ�����
	private Equalizer mEqualizer;
	// ����ϵͳ���ص���������
	private BassBoost mBass;
	// ����ϵͳ��Ԥ������������
	private PresetReverb mPresetReverb;
	private LinearLayout layout;
	private List<Short> reverbNames = new ArrayList<Short>();
	private List<String> reverbVals = new ArrayList<String>();

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		// ���ÿ�����������
		setVolumeControlStream(AudioManager.STREAM_MUSIC);
		layout = new LinearLayout(this);
		layout.setOrientation(LinearLayout.VERTICAL);
		setContentView(layout);
		// ����MediaPlayer����
		mPlayer = MediaPlayer.create(this, R.raw.beautiful);
		// ��ʼ��ʾ����
		setupVisualizer();
		// ��ʼ�����������
		setupEqualizer();
		// ��ʼ���ص���������
		setupBassBoost();
		// ��ʼ��Ԥ������������
		setupPresetReverb();
		// ������������
		mPlayer.start();
	}
	private void setupVisualizer()
	{
		// ����MyVisualizerView�����������ʾ����ͼ
		final MyVisualizerView mVisualizerView =
			new MyVisualizerView(this);
		mVisualizerView.setLayoutParams(new ViewGroup.LayoutParams(
			ViewGroup.LayoutParams.MATCH_PARENT,
			(int) (120f * getResources().getDisplayMetrics().density)));
		// ��MyVisualizerView�����ӵ�layout������
		layout.addView(mVisualizerView);
		// ��MediaPlayer��AudioSessionId����Visualizer
		// �൱������Visualizer������ʾ��MediaPlayer����Ƶ����
		mVisualizer = new Visualizer(mPlayer.getAudioSessionId());
		mVisualizer.setCaptureSize(Visualizer.getCaptureSizeRange()[1]);
		// ΪmVisualizer���ü�����
		mVisualizer.setDataCaptureListener(
			new Visualizer.OnDataCaptureListener()
			{
				@Override
				public void onFftDataCapture(Visualizer visualizer,
					byte[] fft, int samplingRate)
				{
				}
				@Override
				public void onWaveFormDataCapture(Visualizer visualizer,
					byte[] waveform, int samplingRate)
				{
					// ��waveform�������ݸ���mVisualizerView���
					mVisualizerView.updateVisualizer(waveform);
				}
			}, Visualizer.getMaxCaptureRate() / 2, true, false);
		mVisualizer.setEnabled(true);
	}
	
	// ��ʼ������������ķ���
	private void setupEqualizer()
	{
		// ��MediaPlayer��AudioSessionId����Equalizer
		// �൱������Equalizer������Ƹ�MediaPlayer
		mEqualizer = new Equalizer(0, mPlayer.getAudioSessionId());
		// ���þ������Ч��
		mEqualizer.setEnabled(true);
		TextView eqTitle = new TextView(this);
		eqTitle.setText("��������");
		layout.addView(eqTitle);
		// ��ȡ���������֧����Сֵ�����ֵ
		final short minEQLevel = mEqualizer.getBandLevelRange()[0];
		short maxEQLevel = mEqualizer.getBandLevelRange()[1];
		// ��ȡ���������֧�ֵ�����Ƶ��
		short brands = mEqualizer.getNumberOfBands();
		for (short i = 0; i < brands; i++)
		{
			TextView eqTextView = new TextView(this);
			// ����һ��TextView��������ʾƵ��
			eqTextView.setLayoutParams(new ViewGroup.LayoutParams(
				ViewGroup.LayoutParams.MATCH_PARENT,
				ViewGroup.LayoutParams.WRAP_CONTENT));
			eqTextView.setGravity(Gravity.CENTER_HORIZONTAL);
			// ���øþ����������Ƶ��
			eqTextView.setText((mEqualizer.getCenterFreq(i) / 1000)
				+ " Hz");
			layout.addView(eqTextView);
			// ����һ��ˮƽ���������LinearLayout
			LinearLayout tmpLayout = new LinearLayout(this);
			tmpLayout.setOrientation(LinearLayout.HORIZONTAL);
			// ������ʾ�����������Сֵ��TextView
			TextView minDbTextView = new TextView(this);
			minDbTextView.setLayoutParams(new ViewGroup.LayoutParams(
				ViewGroup.LayoutParams.WRAP_CONTENT,
				ViewGroup.LayoutParams.WRAP_CONTENT));
			// ��ʾ�������������Сֵ
			minDbTextView.setText((minEQLevel / 100) + " dB");
			// ������ʾ������������ֵ��TextView
			TextView maxDbTextView = new TextView(this);
			maxDbTextView.setLayoutParams(new ViewGroup.LayoutParams(
				ViewGroup.LayoutParams.WRAP_CONTENT,
				ViewGroup.LayoutParams.WRAP_CONTENT));
			// ��ʾ��������������ֵ			
			maxDbTextView.setText((maxEQLevel / 100) + " dB");
			LinearLayout.LayoutParams layoutParams = new 
				LinearLayout.LayoutParams(
				ViewGroup.LayoutParams.MATCH_PARENT,
				ViewGroup.LayoutParams.WRAP_CONTENT);
			layoutParams.weight = 1;
			// ����SeekBar��Ϊ��������
			SeekBar bar = new SeekBar(this);
			bar.setLayoutParams(layoutParams);
			bar.setMax(maxEQLevel - minEQLevel);
			bar.setProgress(mEqualizer.getBandLevel(i));
			final short brand = i;
			// ΪSeekBar���϶��¼������¼�������
			bar.setOnSeekBarChangeListener(new SeekBar
				.OnSeekBarChangeListener()
			{
				@Override
				public void onProgressChanged(SeekBar seekBar,
					int progress, boolean fromUser)
				{
					// ���ø�Ƶ�ʵľ���ֵ
					mEqualizer.setBandLevel(brand,
						(short) (progress + minEQLevel));
				}
				@Override
				public void onStartTrackingTouch(SeekBar seekBar)
				{
				}
				@Override
				public void onStopTrackingTouch(SeekBar seekBar)
				{
				}
			});
			// ʹ��ˮƽ���������LinearLayout��ʢװ��3�����
			tmpLayout.addView(minDbTextView);
			tmpLayout.addView(bar);
			tmpLayout.addView(maxDbTextView);
			// ��ˮƽ���������LinearLayout��ӵ�myLayout������
			layout.addView(tmpLayout);
		}
	}

	// ��ʼ���ص���������
	private void setupBassBoost()
	{
		// ��MediaPlayer��AudioSessionId����BassBoost
		// �൱������BassBoost������Ƹ�MediaPlayer
		mBass = new BassBoost(0, mPlayer.getAudioSessionId());
		// ���������ص���Ч��
		mBass.setEnabled(true);
		TextView bbTitle = new TextView(this);
		bbTitle.setText("�ص�����");
		layout.addView(bbTitle);
		// ʹ��SeekBar��Ϊ�ص����ĵ������� 
		SeekBar bar = new SeekBar(this);
		// �ص����ķ�ΧΪ0��1000
		bar.setMax(1000);
		bar.setProgress(0);
		// ΪSeekBar���϶��¼������¼�������
		bar.setOnSeekBarChangeListener(new SeekBar
			.OnSeekBarChangeListener()
		{
			@Override
			public void onProgressChanged(SeekBar seekBar
				, int progress, boolean fromUser)
			{
				// �����ص�����ǿ��
				mBass.setStrength((short) progress);
			}
			@Override
			public void onStartTrackingTouch(SeekBar seekBar)
			{
			}
			@Override
			public void onStopTrackingTouch(SeekBar seekBar)
			{
			}
		});
		layout.addView(bar);
	}

	// ��ʼ��Ԥ������������
	private void setupPresetReverb()
	{
		// ��MediaPlayer��AudioSessionId����PresetReverb
		// �൱������PresetReverb������Ƹ�MediaPlayer
		mPresetReverb = new PresetReverb(0,
			mPlayer.getAudioSessionId());
		// ��������Ԥ����������
		mPresetReverb.setEnabled(true);
		TextView prTitle = new TextView(this);
		prTitle.setText("����");
		layout.addView(prTitle);
		// ��ȡϵͳ֧�ֵ�����Ԥ������
		for (short i = 0; i < mEqualizer.getNumberOfPresets(); i++)
		{
			reverbNames.add(i);
			reverbVals.add(mEqualizer.getPresetName(i));
		}
		// ʹ��Spinner��Ϊ����ѡ�񹤾�
		Spinner sp = new Spinner(this);
		sp.setAdapter(new ArrayAdapter<String>(MediaPlayerTest.this,
			android.R.layout.simple_spinner_item, reverbVals));
		// ΪSpinner���б���ѡ���¼����ü�����
		sp.setOnItemSelectedListener(new Spinner
			.OnItemSelectedListener()
		{
			@Override
			public void onItemSelected(AdapterView<?> arg0
				, View arg1, int arg2, long arg3)
			{
				// �趨����
				mPresetReverb.setPreset(reverbNames.get(arg2));
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0)
			{
			}
		});
		layout.addView(sp);
	}

	@Override
	protected void onPause()
	{
		super.onPause();
		if (isFinishing() && mPlayer != null)
		{
			// �ͷ����ж���
			mVisualizer.release();
			mEqualizer.release();
			mPresetReverb.release();
			mBass.release();
			mPlayer.release();
			mPlayer = null;
		}
	}

	private static class MyVisualizerView extends View
	{
		// bytes���鱣���˲��γ������ֵ
		private byte[] bytes;
		private float[] points;
		private Paint paint = new Paint();
		private Rect rect = new Rect();
		private byte type = 0;
		public MyVisualizerView(Context context)
		{
			super(context);
			bytes = null;
			// ���û��ʵ�����
			paint.setStrokeWidth(1f);
			paint.setAntiAlias(true);
			paint.setColor(Color.GREEN);
			paint.setStyle(Style.FILL);
		}

		public void updateVisualizer(byte[] ftt)
		{
			bytes = ftt;
			// ֪ͨ������ػ��Լ���
			invalidate();
		}
		
		@Override
		public boolean onTouchEvent(MotionEvent me)
		{
			// ���û����������ʱ���л���������
			if(me.getAction() != MotionEvent.ACTION_DOWN)
			{
				return false;
			}
			type ++;
			if(type >= 3)
			{
				type = 0;
			}
			return true;
		}

		@Override
		protected void onDraw(Canvas canvas)
		{
			super.onDraw(canvas);
			if (bytes == null)
			{
				return;
			}
			// ���ư�ɫ��������ҪΪ��ӡˢʱ�ÿ���
			canvas.drawColor(Color.WHITE);			
			// ʹ��rect�����¼������Ŀ�Ⱥ͸߶�
			rect.set(0,0,getWidth(),getHeight());
			switch(type)
			{
				// -------���ƿ�״�Ĳ���ͼ-------
				case 0:	
					for (int i = 0; i < bytes.length - 1; i++)
					{
						float left = getWidth() * i / (bytes.length - 1);
						// ���ݲ���ֵ����þ��εĸ߶�		
						float top = rect.height()-(byte)(bytes[i+1]+128)
							* rect.height() / 128;
						float right = left + 1;
						float bottom = rect.height();
						canvas.drawRect(left, top, right, bottom, paint);
					}
					break;
				// -------������״�Ĳ���ͼ��ÿ��18�����������һ�����Σ�-------
				case 1:
					for (int i = 0; i < bytes.length - 1; i += 18)
					{
						float left = rect.width()*i/(bytes.length - 1);
						// ���ݲ���ֵ����þ��εĸ߶�
						float top = rect.height()-(byte)(bytes[i+1]+128)
							* rect.height() / 128;
						float right = left + 6;
						float bottom = rect.height();
						canvas.drawRect(left, top, right, bottom, paint);
					}
					break;
				// -------�������߲���ͼ-------
				case 2:
					// ���point���黹δ��ʼ��
					if (points == null || points.length < bytes.length * 4)
					{
						points = new float[bytes.length * 4];
					}
					for (int i = 0; i < bytes.length - 1; i++)
					{
						// �����i�����x����
						points[i * 4] = rect.width()*i/(bytes.length - 1);
						// ����bytes[i]��ֵ�����ε��ֵ�������i�����y����
						points[i * 4 + 1] = (rect.height() / 2)
							+ ((byte) (bytes[i] + 128)) * 128
							/ (rect.height() / 2);
						// �����i+1�����x����
						points[i * 4 + 2] = rect.width() * (i + 1)
							/ (bytes.length - 1);
						// ����bytes[i+1]��ֵ�����ε��ֵ�������i+1�����y����
						points[i * 4 + 3] = (rect.height() / 2)
							+ ((byte) (bytes[i + 1] + 128)) * 128
							/ (rect.height() / 2);
					}
					// ���Ʋ�������
					canvas.drawLines(points, paint);
					break;
			}
		}
	}	
}