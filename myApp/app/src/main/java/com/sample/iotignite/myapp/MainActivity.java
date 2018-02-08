package com.sample.iotignite.myapp;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Future;

import com.ardic.android.iotignite.callbacks.ConnectionCallback;
import com.ardic.android.iotignite.exceptions.UnsupportedVersionException;
import com.ardic.android.iotignite.nodes.IotIgniteManager;

public class MainActivity extends Activity {
    private static final String TAG ="Sample IoT-Ignite App";
    private IotIgniteHandler mIotIgniteHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /**
         * Set IgniteHandler and start.
         */
        mIotIgniteHandler = IotIgniteHandler.getInstance(getApplicationContext());
        mIotIgniteHandler.start();

        TextView textViewConnection = (TextView) findViewById(R.id.textConnection);
        textViewConnection.setText("Connected!");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if(mIotIgniteHandler != null){
            mIotIgniteHandler.shutdown();
        }
    }
/*
    private static final String TAG ="Sample IoT-Ignite App";
    private IotIgniteManager mIotIgniteManager;

    private boolean isConnected = false;
    private boolean versionError = false;

    private Handler handler = new Handler();

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            connect();
            if(isConnected)
                handler.removeCallbacks(this);
            else
                handler.postDelayed(this, 100);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        handler.postDelayed(runnable, 100);

        Log.i(TAG,"main thread...");

    }

    private void connect() {
        Log.i(TAG,"Trying to connect...");
        try {
            mIotIgniteManager = new IotIgniteManager.Builder()

                    .setContext(getApplicationContext())
                    .setConnectionListener(new ConnectionCallback() {
                        TextView textViewConnection = (TextView) findViewById(R.id.textConnection);
                        @Override
                        public void onConnected() {
                            Log.i(TAG,"Ignite connected");
                            isConnected = true;
                            textViewConnection.setText("Connected!");
                        }

                        @Override
                        public void onDisconnected() {
                            Log.i(TAG,"Ignite disconnected");
                            textViewConnection.setText("Disconnected!");
                        }
                    })

                    .build();
        } catch (UnsupportedVersionException e) {
            versionError = true;
            Log.e(TAG,"UnsupportedVersionException : " + e.getMessage());
        }
    }
*/
    /*public class ConnectionTimerTask extends TimerTask {

        @Override
        public void run() {
            connect();
           // if(!isConnected) timer.schedule(connecTimerTask, 1000, 1000);
        }

        private void connect() {
            Log.i(TAG,"Trying to connect...");
            try {
                mIotIgniteManager = new IotIgniteManager.Builder()

                        .setContext(getApplicationContext())
                        .setConnectionListener(new ConnectionCallback() {
                            TextView textViewConnection = (TextView) findViewById(R.id.textConnection);
                            @Override
                            public void onConnected() {
                                Log.i(TAG,"Ignite connected");
                                isConnected = true;
                                textViewConnection.setText("Connected!");
                            }

                            @Override
                            public void onDisconnected() {
                                Log.i(TAG,"Ignite disconnected");
                                textViewConnection.setText("Disconnected!");
                            }
                        })

                        .build();
            } catch (UnsupportedVersionException e) {
                versionError = true;
                Log.e(TAG,"UnsupportedVersionException : " + e.getMessage());
            }
        }
    }
    */
}


/*
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.ardic.android.iotignite.callbacks.ConnectionCallback;
import com.ardic.android.iotignite.exceptions.UnsupportedVersionException;
import com.ardic.android.iotignite.nodes.IotIgniteManager;

public class MainActivity extends Activity {

    private static final String TAG ="Sample IoT-Ignite App";
    private IotIgniteManager mIotIgniteManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        try {
            mIotIgniteManager = new IotIgniteManager.Builder()

                    .setContext(getApplicationContext())

                    .setConnectionListener(new ConnectionCallback() {
                        TextView textViewConnection = (TextView) findViewById(R.id.textConnection);
                        @Override
                        public void onConnected() {
                            Log.i(TAG,"Ignite connected");
                            textViewConnection.setText("Connected!");
                        }

                        @Override
                        public void onDisconnected() {
                            Log.i(TAG,"Ignite disconnected");
                            textViewConnection.setText("Connected!");
                        }
                    })

                    .build();
        } catch (UnsupportedVersionException e) {
            Log.e(TAG,"UnsupportedVersionException : " + e.getMessage());
        }
    }
}
*/