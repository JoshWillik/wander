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
		setEGLContextClientVersion(2);
		renderer = new GLRenderer(context, this);
		setRenderer(renderer);
		setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);
	}
}
