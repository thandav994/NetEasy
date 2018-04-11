package com.neteasy.test;

	import android.annotation.SuppressLint;
import android.app.Fragment;
	import android.os.Bundle;
	import android.view.LayoutInflater;
	import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
	 
	public class MyProfile extends Fragment {
		EditText user, pass, name, age, email,Intersted;
		String Suser, Spass, Sname, Sage, Semail,Scountry,SInterested;
	
		public MyProfile(String suser, String sname, String sage, String semail, String scountry, String sInterested){
	    	Suser=suser;
	    	Sname=sname;
	    	Sage=sage;
	    	Semail=semail;
	    	Scountry=scountry;
	    	Suser=suser;
	    	
	    }
	    public void Myprofile(){}
	     
	    @Override
	    public View onCreateView(LayoutInflater inflater, ViewGroup container,
	            Bundle savedInstanceState) {
	        View rootView = inflater.inflate(R.layout.myprofile, container, false);
	        
	        return rootView;
	    }
	}
