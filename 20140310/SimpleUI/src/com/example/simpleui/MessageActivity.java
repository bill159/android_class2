package com.example.simpleui;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;

public class MessageActivity extends Activity {

	private TextView textView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_message);

		String text = getIntent().getStringExtra("text");
		writeFile(text);

		textView = (TextView) findViewById(R.id.message);
		textView.setText(readFile());
	}

	private void writeFile(String text) {
		try {
			text += "\n";
			FileOutputStream fos = openFileOutput("history.txt",
					Context.MODE_APPEND);
			fos.write(text.getBytes());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private String readFile() {
		try {
			FileInputStream fis = openFileInput("history.txt");
			byte[] buffer = new byte[1024];
			fis.read(buffer);
			return new String(buffer);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
