package com.happy.home;

import com.happy.home.R;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

public class BaseActivity  extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getActionBar().hide();  
		setContentView(R.layout.activity_main);
	}
}
