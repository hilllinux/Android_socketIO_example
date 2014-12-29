package com.example.wsq.helloworld;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;


import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.github.nkzawa.emitter.Emitter;
import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;

import java.net.URISyntaxException;


public class MainActivity extends ActionBarActivity {
    private  Socket mSocket;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("SOCKET.IO","before connect");
        connect();
        Log.d("SOCKET.IO","after connect");

    }

    private  Socket mSocket;

    private void connect () {
        try {
            mSocket = IO.socket("http://106.187.51.112:3000");
            mSocket.on(Socket.EVENT_CONNECT, new Emitter.Listener() {
                @Override
                public void call(Object... args) {
                }
            }).on("foo", new Emitter.Listener() {
                @Override
                public void call(Object... args) {
                    Log.d("SOCKET.IO","received message");
                }
            }).on(Socket.EVENT_DISCONNECT, new Emitter.Listener() {
                @Override
                public void call(Object... args) {}
            });

            mSocket.connect();
            mSocket.emit("reg", "112");

        } catch (Exception e) {
            Log.d("SOCKET.IO", "This shit did not work!!!");
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
