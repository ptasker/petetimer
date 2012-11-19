package com.example.petetimer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class Second extends Activity {
	private TextView text;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_second);

		// Get the message from the intent
		Intent intent = getIntent();
		String message = intent.getStringExtra(MainActivity.TIMER_WORK);
		text = (TextView) findViewById(R.id.textView1);
		
		Integer worktime = Integer.parseInt(message) * 1000;
		
		this.startWorkTimer(worktime);
		
		
		// // Create the text view
		// TextView textView = new TextView(this);
		// textView.setTextSize(40);
		// textView.setText(message);
		//
		// // Set the text view as the activity layout
		// setContentView(textView);

	}

	public void startWorkTimer(Integer length) {

		new CountDownTimer(length, 1000) {

			public void onTick(long millisUntilFinished) {

				text.setText("seconds remaining: " + millisUntilFinished / 1000);
			}

			public void onFinish() {
				text.setText("done!");
			}

		}.start();

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_second, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}
