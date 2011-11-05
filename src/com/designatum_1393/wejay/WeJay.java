package com.designatum_1393.wejay;

import android.app.Activity;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;

// for toast
import android.widget.Toast;
import android.content.Context;

public class WeJay extends Activity
{
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tom_jones);
    }

	public void sendData(View view)
	{
		Context ctx = getApplicationContext();
		Toast tst = Toast.makeText(ctx, "WOOOOAHHH WOAH WOOOOOAHHH WOAH", Toast.LENGTH_SHORT);
		tst.show();
	}
}
