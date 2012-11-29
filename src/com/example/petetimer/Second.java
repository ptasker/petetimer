package com.example.petetimer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Second extends Activity {
	private TextView text;
	private Integer worktime;
	private Integer rest_timer;
	private Integer interval;
	public Integer n;
	public long s1;
	public long s2;
	public String stage;

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
				stage = "work";
				s1 = millisUntilFinished;

				text.setText(formatTime(millisUntilFinished));
			}

			public void onFinish() {

				new CountDownTimer(rest_timer, 1000) {

					public void onTick(long millisUntilFinished) {

						stage = "rest";
						s2 = millisUntilFinished;

						text.setText(formatTime(millisUntilFinished));
					}

					public void onFinish() {

						n++;
						// Log.v(TAG, "meesage" + n);
						if (n <= interval) {

							startWorkTimer(worktime);

						} else {
							text.setText("done!");
						}
					}

				}.start();

			}

		}.start();

	}

	public void btnClick(View view) {

		Button btn = (Button) findViewById(R.id.timeraction);

		
		
	}

	public String formatTime(long millis) {
		String output = "00:00:00";
		long seconds = millis / 1000;
		long minutes = seconds / 60;
		long hours = minutes / 60;

		seconds = seconds % 60;
		minutes = minutes % 60;
		hours = hours % 60;

		String secondsD = String.valueOf(seconds);
		String minutesD = String.valueOf(minutes);
		String hoursD = String.valueOf(hours);

		if (seconds < 10)
			secondsD = "0" + seconds;
		if (minutes < 10)
			minutesD = "0" + minutes;
		if (hours < 10)
			hoursD = "0" + hours;

		output = hoursD + " : " + minutesD + " : " + secondsD;
		return output;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_second, menu);
		return true;
	}

}
