package com.neteasy.test;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Splash2 extends Activity{
	Button skip, next;
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash2);
		skip=(Button)findViewById(R.id.skip2);
		next=(Button)findViewById(R.id.next2);
		skip.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent openStartingPoint = new Intent(
						"com.neteasy.test.MAINACTIVITY");
				startActivity(openStartingPoint);	
				
			}
		});
next.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent openStartingPoint = new Intent(
						"com.neteasy.test.SPLASH3");
				startActivity(openStartingPoint);
				finish();
				
			}
		});
	}}