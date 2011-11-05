package com.designatum_1393.wejay;

import android.app.Activity;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;

// for toast
import android.widget.Toast;
import android.content.Context;

// for socket
import java.net.Socket;
import java.net.InetAddress;
import android.util.Log;
import java.io.PrintWriter;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;

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

		/** create socket **/
		Socket skt;
		try {
			skt = new Socket( "10.0.0.9", 51393 );

			// Write to socket source: http://thinkandroid.wordpress.com/2010/03/27/incorporating-socket-programming-into-your-applications/
			PrintWriter out;
			try {
				out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(skt.getOutputStream())), true);
				out.println("I <3 Tom Jones.");
			} catch (Exception e) {
				Log.e("WeJay", "Could not write to socket!");
			}
		}
		catch (IOException e) {
			Log.e("WeJay", "Could not create socket!");
			Log.e("WeJay", ""+e);
		}

		tst.show();
	}
}
