package org.crazyit.helloworld;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class HelloWorld extends Activity
{
	Button ok;
	TextView tv;
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        // �������ID����ȡĿ�����
        ok = (Button) findViewById(R.id.ok);
		// ��ȡUI������IDΪR.id.show���ı���
		tv = (TextView) findViewById(R.id.show);
        ok.setOnClickListener(new OnClickListener()
		{
			public void onClick(View v)
			{
				// �ı��ı�����ı�����
				tv.setText("Hello Android-" + new java.util.Date());				
			}
		});
    }
}
