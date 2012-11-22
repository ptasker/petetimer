package com.example.petetimer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.NavUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class Second extends Activity {
	private TextView text;
	private Integer worktime;
	private Integer rest_timer;
	private Integer interval;
	public Integer n;
	private static final String TAG = "MyActivity";
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_second);

		// Get the message from the intent
		Intent intent = getIntent();
		String work_timer = intent.getStringExtra(MainActivity.TIMER_WORK);

		rest_timer = Integer.parseInt(intent
				.getStringExtra(MainActivity.TIMER_REST)) * 1000;

		interval = Integer.parseInt(intent
				.getStringExtra(MainActivity.TIMER_INTERVAL));

		text = (TextView) findViewById(R.id.textView1);

		worktime = Integer.parseInt(work_timer) * 1000;
		n = 1;
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

					text.setText("Work 00: " + millisUntilFinished / 1000);
				}

				public void onFinish() {

					new CountDownTimer(rest_timer, 1000) {

						public void onTick(long millisUntilFinished) {
							text.setText("Rest 00: " + millisUntilFinished
									/ 1000);
						}

						public void onFinish() {
							
							n++;
							//Log.v(TAG, "meesage" + n);
							
							if( n <= interval ){
								startWorkTimer(worktime);
							}else{
								text.setText("done!");
							}
						}

					}.start();

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
