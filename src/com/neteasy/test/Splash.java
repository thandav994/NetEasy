package com.neteasy.test;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Adapter;
import android.widget.Button;

public class Splash extends Activity {

	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash);
		Thread timer = new Thread() {
			public void run() {
				try {
					sleep(3000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				} finally {

					
					Intent openStartingPoint = new Intent(
							"com.neteasy.test.MAINACTIVITY");
					startActivity(openStartingPoint);
					SharedPreferences settings = getSharedPreferences("first",
							0);
					if (settings.getBoolean("my_first_time", true)) {
						// First time!
						Intent splash1 = new Intent("com.neteasy.test.SPLASH1");
						startActivity(splash1); // Instructions to be given when
												// app is launched the first
												// time.
						settings.edit().putBoolean("my_first_time", false).commit();
					}

				}

			}

		};
		timer.start();
	}
	
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		finish();
	}

}
