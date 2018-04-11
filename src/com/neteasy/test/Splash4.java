package com.neteasy.test;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class Splash4 extends Activity{
Button next;
			@Override
			protected void onCreate(Bundle savedInstanceState) {
				// TODO Auto-generated method stub
				super.onCreate(savedInstanceState);
				setContentView(R.layout.splash4);
				next=(Button)findViewById(R.id.next4);
		next.setOnClickListener(new View.OnClickListener() {
					
					@Override
					public void onClick(View v) {
						Intent openStartingPoint = new Intent(
								"com.neteasy.test.MAINACTIVITY");
						startActivity(openStartingPoint);
						finish();
						
					}
				});
			}
}