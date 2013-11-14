package com.snapped.wander;
import android.opengl.*;
import android.opengl.EGLConfig;
import javax.microedition.khronos.opengles.*;
import javax.microedition.khronos.egl.*;
import android.content.Context;
import java.nio.*;

public class GLRenderer implements GLSurfaceView.Renderer
{	
	private GL gl;
	private final String vertexShaderCode = 
	"uniform mat4 uMVPMatrix; \n" 
	+ "attribute vec4 vPosition; \n"
	+"void main(){"
	+	"gl_position = uMVPMatrix * vPosition; \n:"
	+"}";
	
	public GLRenderer(Context context, GL gl){
		this.gl = gl;
		
	}

	public void onSurfaceCreated(GL10 lgl, javax.microedition.khronos.egl.EGLConfig p2)
	{
		GLES20.glClearColor(1.0f, 0.5f, 0.5f, 0.8f);
		lgl.glEnable(GL10.GL_CULL_FACE);
		lgl.glCullFace(GL10.GL_BACK);
	}

	public void onSurfaceChanged(GL10 lgl, int w, int h)
	{
		lgl.glViewport(0, 0, w, h);
		float ratio = (float) w/h;
		
		lgl.glMatrixMode(GL10.GL_PROJECTION);
		lgl.glLoadIdentity();
		lgl.glFrustumf(-ratio, ratio, -1, 1, 1, 10);
	}

	public void onDrawFrame(GL10 p1)
	{
		GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT | GLES20.GL_DEPTH_BUFFER_BIT);
		//game drawing logic here
	}
}

class Triangle{
	private FloatBuffer vertexBuffer;
	static final int COORDS_PER_VERTEX = 3;
	static float[] triangleCoords = {
		0.0f, 0.5f, 0.0f
		, 0.5f, -0.5f, 0.0f
		, -0.5f, -0.5f, 0.0f
	};

	static float[] triangleColor = {
		0.3f, 0.5f, 0.6f, 0.7f
	};
	
	public Triangle(){
		ByteBuffer bb = ByteBuffer.allocateDirect(triangleCoords.length * 4);
		bb.order(ByteOrder.nativeOrder());
		vertexBuffer = bb.asFloatBuffer();
		vertexBuffer.put(triangleCoords);
		vertexBuffer.position(0);		
	}
}
