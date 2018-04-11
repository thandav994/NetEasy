package com.neteasy.test;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class Splash1 extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash1);
		Thread timer = new Thread() {
			public void run() {
				try {
					sleep(3000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				} finally {
					Intent splash2=new Intent("com.neteasy.test.SPLASH2");
					startActivity(splash2);
					finish();
				}}
			};
			timer.start();
	}}