package com.neteasy.test;

	import android.app.Fragment;
	import android.os.Bundle;
	import android.view.LayoutInflater;
	import android.view.View;
	import android.view.ViewGroup;
	 
	public class EditProfile extends Fragment {
	     
	    public EditProfile(){}
	     
	    @Override
	    public View onCreateView(LayoutInflater inflater, ViewGroup container,
	            Bundle savedInstanceState) {
	  
	        View rootView = inflater.inflate(R.layout.inbox, container, false);
	          return rootView;
	    }
	}
