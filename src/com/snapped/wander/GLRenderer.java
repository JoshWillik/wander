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
	Triangle tri;
	
	public GLRenderer(Context context, GL gl){
		this.gl = gl;
		tri = new Triangle();
	}

	@Override
	public void onSurfaceCreated(GL10 lgl, javax.microedition.khronos.egl.EGLConfig p2)
	{
		GLES20.glClearColor(1.0f, 0.5f, 0.5f, 1.0f);
		lgl.glEnable(GL10.GL_CULL_FACE);
		lgl.glCullFace(GL10.GL_BACK);
	}

	@Override
	public void onSurfaceChanged(GL10 lgl, int w, int h)
	{
		lgl.glViewport(0, 0, w, h);
		float ratio = (float) w/h;
		
		lgl.glMatrixMode(GL10.GL_PROJECTION);
		lgl.glLoadIdentity();
		lgl.glFrustumf(-ratio, ratio, -1, 1, 1, 10);
	}

	@Override
	public void onDrawFrame(GL10 p1)
	{
		GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT | GLES20.GL_DEPTH_BUFFER_BIT);
		tri.draw();
		//game drawing logic here
	}
	
	public static int loadShader(int type, String shaderCode){	
		int shader = 0;
		GLES20.glCreateShader(type);
		GLES20.glShaderSource(shader, shaderCode);
		GLES20.glCompileShader(shader);
		return shader;
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
	
	private final int vertexCount = triangleCoords.length;
	private final int vertexStride = vertexCount * 4;
	
	private final String vertexShaderCode = 
	"uniform mat4 uMVPMatrix; \n" 
	+ "attribute vec4 vPosition; \n"
	+"void main(){"
	+	"gl_position = uMVPMatrix * vPosition; \n:"
	+"}";
	
	private final String fragmentShaderCode =
	"precision mediump float;" +
	"uniform vec4 vColor;" +
	"void main() {" +
	"  gl_FragColor = vColor;" +
	"}";
	
	private int
		mProgram
		, mPositionHandle
		, mColorHandle
		;
	
	public Triangle(){
		ByteBuffer bb = ByteBuffer.allocateDirect(triangleCoords.length * 4);
		bb.order(ByteOrder.nativeOrder());
		vertexBuffer = bb.asFloatBuffer();
		vertexBuffer.put(triangleCoords);
		vertexBuffer.position(0);		
		
		int vertexShader = GLRenderer.loadShader(GLES20.GL_VERTEX_SHADER, vertexShaderCode);
		int fragmentShader = GLRenderer.loadShader(GLES20.GL_FRAGMENT_SHADER, fragmentShaderCode);
		
		mProgram = GLES20.glCreateProgram();
		GLES20.glAttachShader(mProgram, vertexShader);
		GLES20.glAttachShader(mProgram, fragmentShader);
		GLES20.glLinkProgram(mProgram);
	}
	
	void draw(){
		GLES20.glUseProgram(mProgram); 
		mPositionHandle = GLES20.glGetAttribLocation(mProgram, "vPosition");
		GLES20.glEnableVertexAttribArray(mPositionHandle);
		GLES20.glVertexAttribPointer(
			mPositionHandle, 
			COORDS_PER_VERTEX,
			GLES20.GL_FLOAT,
			false,
			vertexStride,
			vertexBuffer
		);
		mColorHandle = GLES20.glGetUniformLocation(mProgram, "vColor");
		GLES20.glUniform4fv(mColorHandle, 1, triangleColor, 0);
		GLES20.glDrawArrays(GLES20.GL_TRIANGLES, 0, vertexCount);
		GLES20.glDisableVertexAttribArray(mPositionHandle);
	}
}
