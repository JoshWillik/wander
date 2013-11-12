package com.snapped.wander;

import android.app.*;
import android.opengl.*;
import android.os.*;

public class GL extends Activity
{
	private GLSurfaceView glView;
	
	@Override
	public void onCreate(Bundle saved){
		super.onCreate(saved);
		
		glView = new GLSurfaceView(this);
		
		setContentView(glView);
	}
}
