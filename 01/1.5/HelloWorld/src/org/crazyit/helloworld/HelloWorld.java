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
        // 根据组件ID来获取目标组件
        ok = (Button) findViewById(R.id.ok);
		// 获取UI界面中ID为R.id.show的文本框
		tv = (TextView) findViewById(R.id.show);
        ok.setOnClickListener(new OnClickListener()
		{
			public void onClick(View v)
			{
				// 改变文本框的文本内容
				tv.setText("Hello Android-" + new java.util.Date());				
			}
		});
    }
}
