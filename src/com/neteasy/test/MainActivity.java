package com.neteasy.test;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.brickred.socialauth.Profile;
import org.brickred.socialauth.android.DialogListener;
//import org.brickred.socialauth.android.ShareButtonAdapter;
import org.brickred.socialauth.android.SocialAuthAdapter;
import org.brickred.socialauth.android.SocialAuthAdapter.Provider;
import org.brickred.socialauth.android.SocialAuthError;
import org.brickred.socialauth.android.SocialAuthListener;
import org.json.JSONArray;
import org.json.JSONObject;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.app.Activity;
//import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
//import android.content.DialogInterface;
//import android.content.DialogInterface.OnClickListener;
//import android.content.Intent;
//import android.content.pm.FeatureInfo;
import android.util.Log;
import android.view.Menu;
//import android.view.View;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {
	Button share, SignUp, Linkedlogin, login;
	SocialAuthAdapter adapter, loginAdapter;
	WakeLock w;
	public static int loggedIn=0;
	EditText etUser, etPass;
	String username, pass;
//	public static String USER_NAME = "com.neteasy.test.USER_NAME";
//	public static String TYPE = "com.neteasy.test.TYPE";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		PowerManager pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
		w = pm.newWakeLock(PowerManager.FULL_WAKE_LOCK, "wakelock");
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		super.onCreate(savedInstanceState);
		w.acquire();
		setContentView(R.layout.activity_main);
		share = (Button) findViewById(R.id.share);
		Linkedlogin = (Button) findViewById(R.id.bLinked);
		SignUp = (Button) findViewById(R.id.bJoin);
		login = (Button) findViewById(R.id.bLogin);
		etUser = (EditText) findViewById(R.id.etuser);
		etPass = (EditText) findViewById(R.id.etPassword);
		adapter = new SocialAuthAdapter(new ResponseListener());
		// Add providers
		adapter.addProvider(Provider.FACEBOOK, R.drawable.facebook);
		adapter.addProvider(Provider.TWITTER, R.drawable.twitter);
		adapter.addProvider(Provider.LINKEDIN, R.drawable.linkedin);

		boolean x = isOnline();
		if (!x)
			Toast.makeText(MainActivity.this, "Internet is not available",
					Toast.LENGTH_LONG).show();
		else {
			login.setOnClickListener(new View.OnClickListener() {
				public void onClick(View v) {
					// TODO Auto-generated method stub
					username = etUser.getText().toString();
					pass = etPass.getText().toString();
					new Connection().execute();
				}
			});
			loginAdapter = new SocialAuthAdapter(new loginlistener());
			if (loggedIn!=0)
			{
				loginAdapter.getCurrentProvider().logout();
				loggedIn=0;
			}
				Linkedlogin.setOnClickListener(new View.OnClickListener() {
				public void onClick(View v) {
					// TODO Auto-generated method stub
					loginAdapter.authorize(MainActivity.this, Provider.LINKEDIN);
				}
			});
//			SharedPreferences settings = getSharedPreferences("first", 0);
//			if (settings.getBoolean("logged_in", false))
//				loginAdapter.signOut(MainActivity.this, "LinkedIn");
			SignUp = (Button) findViewById(R.id.bJoin);
			SignUp.setOnClickListener(new View.OnClickListener() {
				public void onClick(View v) {
					Intent i = new Intent(MainActivity.this,
							com.neteasy.test.Join.class);
					startActivity(i);
				}
			});
			// Add it to Library
			adapter.enable(share);
		}
	}

	public boolean isOnline() {
		ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo netInfo = cm.getActiveNetworkInfo();
		if (netInfo != null && netInfo.isConnectedOrConnecting()) {
			return true;
		}
		return false;
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		w.release();
		finish();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	private final class ResponseListener implements DialogListener {
		public void onComplete(Bundle values) {
			Toast.makeText(
					MainActivity.this,
					values.getString(SocialAuthAdapter.PROVIDER) + " connected",
					Toast.LENGTH_LONG).show();
			adapter.updateStatus(
					"This is a great app. Please download NetEasy right now !!",
					new MessageListener(), true);
			Log.v("Updated", "The message got updated!");
		}

		public void onCancel() {
			Log.d("ShareButton", "Cancelled");
		}

		@Override
		public void onBack() {
			// TODO Auto-generated method stub
			Log.d("ShareButton", "Back");
		}

		@Override
		public void onError(SocialAuthError e) {
			Log.d("ShareButton", e.getMessage());
		}
	}

	// To get status of message after authentication
	private final class MessageListener implements SocialAuthListener<Integer> {

		public void onExecute(String provider, Integer t) {
			Log.i("Toast", "The method got called");
			Integer status = t;
			Log.i("Value of status:", "" + status.intValue());
			if (status.intValue() == 200 || status.intValue() == 201
					|| status.intValue() == 204 || status.intValue() == 500)
				Toast.makeText(MainActivity.this,
						"Message posted on " + provider, Toast.LENGTH_LONG)
						.show();
			else
				Toast.makeText(MainActivity.this,
						"Message not posted " + provider, Toast.LENGTH_LONG)
						.show();

		}

		public void onError(SocialAuthError e) {
			Log.d("ShareButton", "Error");

		}

	}

	public class loginlistener implements DialogListener {

		@Override
		public void onBack() {
			// TODO Auto-generated method stub
			Log.d("loginButton", "Stopped coz back button was pressed.");
		}

		@Override
		public void onCancel() {
			// TODO Auto-generated method stub
			Log.d("loginButton", "login Cancelled");
		}

		@Override
		public void onComplete(Bundle arg0) {
			// TODO Auto-generated method stub
			Log.i("Completed", "YEahh this is called");
			Log.i("Name: ", loginAdapter.getUserProfile().getFirstName());
			Profile user = loginAdapter.getUserProfile();
			Bundle lbasket = new Bundle();
			lbasket.putString("name", user.getFullName());
			lbasket.putString("email", user.getEmail());
			lbasket.putString("country", user.getCountry());
			//lbasket.putString("age", String.valueOf(2014-user.getDob().getYear()));
			Intent i = new Intent(MainActivity.this,com.neteasy.test.LinkedLogin.class);
			i.putExtras(lbasket);
			startActivity(i);
			// loginAdapter.getUserProfileAsync(new ProfileListener());
		}

		@Override
		public void onError(SocialAuthError arg0) {
			// TODO Auto-generated method stub

		}

	}

	// public class ProfileListener implements SocialAuthListener<Profile> {
	//
	// @Override
	// public void onError(SocialAuthError e) {
	// // TODO Auto-generated method stub
	// Log.e("Erroe",e.getMessage() );
	// }
	//
	// @Override
	// public void onExecute(String provider, Profile pr) {
	// Profile user=pr;
	// Log.d("Username:", user.getDisplayName().toString());
	// }
	//
	// }

	class Connection extends AsyncTask<String, Void, String> {

		@Override
		protected String doInBackground(String... arg0) {
			// TODO Auto-generated method stub

			String result = "";
			InputStream isr = null;

			try {
				HttpClient httpclient = new DefaultHttpClient();
				HttpPost httppost = new HttpPost(
						"http://andytest.comuf.com/login.php");
				ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
				nameValuePairs
						.add(new BasicNameValuePair("username", username));
				httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
				HttpResponse response = httpclient.execute(httppost);
				HttpEntity entity = response.getEntity();
				isr = entity.getContent();
			} catch (Exception e) {
				Log.e("log_tag", "Error at httpost " + e.toString());
			}
			try {
				BufferedReader reader = new BufferedReader(
						new InputStreamReader(isr, "iso-8859-1"), 8);
				StringBuilder sb = new StringBuilder();
				String line = null;
				while ((line = reader.readLine()) != null) {
					sb.append(line + "\n");
				}
				isr.close();
				result = sb.toString();
			} catch (Exception e) {
				Log.e("log_tag", "Error converting " + e.toString());
			}
			return result;
		}

		@Override
		protected void onPostExecute(String result) {

			super.onPostExecute(result);
			String s = null;
			String Suser=null,Sname = null, Spass=null,Sage=null, Semail=null,Scountry=null,SInterested=null;
			try {
				JSONArray jArray = new JSONArray(result);
				// String type = null;
				for (int i = 0; i < jArray.length(); i++) {
					JSONObject json = jArray.getJSONObject(i);
					s = json.getString("password");
					Suser=json.getString("username");
					Sname=json.getString("name");
					Sage=json.getString("age");
					Semail=json.getString("email");
					Scountry=json.getString("country");
					SInterested=json.getString("interested");
					// type = json.getString("flag");
				}
				if (s.equals(pass)) {
					Intent intent = new Intent(MainActivity.this,
							ProfileMenu.class);
					Bundle loginbasket=new Bundle();
					loginbasket.putString("name",Sname);
					loginbasket.putString("user",Suser);
					loginbasket.putString("age", Sage);
					loginbasket.putString("email",Semail );
					loginbasket.putString("country", Scountry);
					loginbasket.putString("Interested In",SInterested);
					startActivity(intent);
					Log.i("LOGIN DETAILS", "Login details correct!!");
				} else {
					Toast.makeText(getBaseContext(), "Invalid Login Details",
							Toast.LENGTH_SHORT).show();
				}

			} catch (Exception e) {
				Log.e("log_tag", "Error parsing data " + e.toString());
				Toast.makeText(getBaseContext(), "Error In Connection",
						Toast.LENGTH_SHORT).show();
			}
		}

	}
}
