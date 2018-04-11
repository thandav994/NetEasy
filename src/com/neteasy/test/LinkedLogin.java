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
import org.json.JSONArray;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LinkedLogin extends Activity {
	EditText user, pass,Intersted;
	String Suser, Spass, Sname, Sage, Semail,Scountry,SInterested;
	AutoCompleteTextView country;
	String[] Countries;
	ArrayAdapter<String> adapter;
	Button reg;

	@SuppressWarnings("unchecked")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.linkedlogin);
		Bundle gotbasket=getIntent().getExtras();
		user = (EditText) findViewById(R.id.etLinkedUserName);
		pass = (EditText) findViewById(R.id.etLinkedPassword);
		Intersted=(EditText)findViewById(R.id.etLinkedInterested);
		reg = (Button) findViewById(R.id.bRegister);
		Suser=user.getText().toString();
		Spass=pass.getText().toString();
	//	Sage=gotbasket.getString("age");
		Semail=gotbasket.getString("email");
		Scountry=gotbasket.getString("country");
		SInterested=Intersted.getText().toString();
		reg.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Log.i("YEAH","THIS CLASS GOT OPENED");
//				 TODO Auto-generated method stub
//				if (Suser.contentEquals("")||Spass==null||Sage==null||Semail==null||Scountry==null||SInterested==null) {
//					Toast.makeText(Join.this,"Please fill all the details", Toast.LENGTH_SHORT).show();
//				}
//				else{
//					Suser=user.getText().toString();
//					Spass=pass.getText().toString();
//					Sage=age.getText().toString();
//					Semail=email.getText().toString();
//					Scountry=country.getText().toString();
//					SInterested=Intersted.getText().toString();
					Bundle basket=new Bundle();
					basket.putString("user",Suser);
					basket.putString("age", Sage);
					basket.putString("email",Semail );
					basket.putString("country", Scountry);
					basket.putString("age", Sage);
					basket.putString("Interested In",SInterested);
					Intent i=new Intent("com.neteasy.test.ProfileMenu");
					i.putExtras(basket);
					startActivity(i);
					
				}
			//}
		});
	}
//
//	class Connection extends AsyncTask<String, Void, String> {
//
//		@Override
//		protected String doInBackground(String... arg0) {
//			// TODO Auto-generated method stub
//
//			String result = "";
//			InputStream isr = null;
//
//			try {
//				HttpClient httpclient = new DefaultHttpClient();
//				HttpPost httppost = new HttpPost(
//						"http://andytest.comuf.com/enter.php");
//				ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
//				nameValuePairs.add(new BasicNameValuePair("username", Suser));
//				nameValuePairs.add(new BasicNameValuePair("pass", Spass));
//				nameValuePairs.add(new BasicNameValuePair("age", Sage));
//				nameValuePairs.add(new BasicNameValuePair("email", Semail));
//				nameValuePairs.add(new BasicNameValuePair("country", Scountry));
//				nameValuePairs.add(new BasicNameValuePair("interests", SInterested));
//				httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
//				HttpResponse response = httpclient.execute(httppost);
//				HttpEntity entity = response.getEntity();
//				isr = entity.getContent();
//				Log.i("output",isr.toString());
//			} catch (Exception e) {
//				Log.e("log_tag", "Error at httpost " + e.toString());
//			}
//			try {
//				BufferedReader reader = new BufferedReader(
//						new InputStreamReader(isr, "iso-8859-1"), 8);
//				StringBuilder sb = new StringBuilder();
//				String line = null;
//				while ((line = reader.readLine()) != null) {
//					sb.append(line + "\n");
//				}
//				isr.close();
//				result = sb.toString();
//			} catch (Exception e) {
//				Log.e("log_tag", "Error converting " + e.toString());
//			}
//			return result;
//		}
//
//		@Override
//		protected void onPostExecute(String result) {
//
//			super.onPostExecute(result);
//			int s = 0;
//			try {
//				JSONArray jArray = new JSONArray(result);
//				// String type = null;
//				for (int i = 0; i < jArray.length(); i++) {
//					JSONObject json = jArray.getJSONObject(i);
//					s=json.getInt("output");
//					// type = json.getString("flag");
//				}
//				if (s==3) {
//					// Intent intent = new
//					// Intent(MainActivity.this,ProfileMenu.class);
//					// intent.putExtra(USER_NAME, username);
//					// // intent.putExtra(TYPE, type);
//					// startActivity(intent);
//					Log.i("JOINED", "JOIN DONE!!");
//				} else {
//					Toast.makeText(getBaseContext(), "Invalid Login Details",
//							Toast.LENGTH_SHORT).show();
//				}
//
//			} catch (Exception e) {
//				Log.e("log_tag", "Error parsing data " + e.toString());
//				Toast.makeText(getBaseContext(), "Error In Connection",
//						Toast.LENGTH_SHORT).show();
//			}
//		}
//	}

}
