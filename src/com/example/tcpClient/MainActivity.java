package com.example.tcpClient;

import com.example.tcpClient.R;
import android.os.Bundle;
import android.widget.TextView;
import android.app.Activity;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends Activity {
	private Socket socket;

	private static final int SERVERPORT = 5001;
	private static final String SERVER_IP = "192.168.1.106";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_main);
		
		new Thread(new ClientThread()).start();
	}

		
	@Override
	protected void onStart() {
		super.onStart();
		
	}
	
	public void onClick(View view) {
		try {
			EditText et = (EditText) findViewById(R.id.EditText01);
			String str = et.getText().toString();
			PrintWriter out = new PrintWriter(new BufferedWriter(
					new OutputStreamWriter(socket.getOutputStream())),
					true);
			out.println(str);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	protected void onStop() {
		super.onStop();
		
	}
	
	class ClientThread implements Runnable {

		@Override
		public void run() {

			try {
				InetAddress serverAddr = InetAddress.getByName(SERVER_IP);

				socket = new Socket(serverAddr, SERVERPORT);

			} catch (UnknownHostException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			}

		}

	}

}
