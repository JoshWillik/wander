package com.snapped.wander;

import android.app.*;
import android.os.*;
import android.content.Intent;
import android.view.*;
import android.text.*;

public class MainActivity extends Activity
{
	private GL gl;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
	{
        super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(
			WindowManager.LayoutParams.FLAG_FULLSCREEN
			, WindowManager.LayoutParams.FLAG_FULLSCREEN
			);
		
		gl = new GL(this);
		setContentView(gl);
    }
	
	@Override
	public void onPause(){
		super.onResume();
		gl.onPause();
	}
	
	@Override
	public void onResume(){
		super.onResume();
		gl.onResume();
	}
}
