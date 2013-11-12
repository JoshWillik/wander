package com.snapped.wander;
import android.opengl.*;
import android.opengl.EGLConfig;
import javax.microedition.khronos.opengles.*;
import javax.microedition.khronos.egl.*;
import android.content.Context;
public class GLRenderer implements GLSurfaceView.Renderer
{	
	private GL gl;
	public GLRenderer(Context context, GL gl){
		this.gl = gl;
		
	}

	public void onSurfaceCreated(GL10 p1, javax.microedition.khronos.egl.EGLConfig p2)
	{
		// TODO: Implement this method
	}
	

	public void onSurfaceCreated(GL10 p1, EGLConfig p2)
	{
		// TODO: Implement this method
	}

	public void onSurfaceChanged(GL10 p1, int p2, int p3)
	{
		// TODO: Implement this method
	}

	public void onDrawFrame(GL10 p1)
	{
		GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT | GLES20.GL_DEPTH_BUFFER_BIT);
		//game drawing logic here
	}
}
