package com.snapped.wander;

import android.app.*;
import android.os.*;
import android.content.Intent;

public class MainActivity extends Activity
{
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
	{
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
		Intent f= new Intent(this, GL.class);
		startActivity(f);
    }
}
