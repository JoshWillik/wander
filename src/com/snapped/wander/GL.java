package com.snapped.wander;

import android.app.*;
import android.opengl.*;
import android.os.*;
import android.content.*;

public class GL extends GLSurfaceView
{	
	private GLRenderer renderer;
	public GL(Context context){
		super(context);
		renderer = new GLRenderer(context, this);
		setEGLContextClientVersion(2);
		setRenderer(renderer);
		setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);
	}
}
