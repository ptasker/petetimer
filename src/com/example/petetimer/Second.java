package com.example.petetimer;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
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
	public CountDownTimer mastertimer;
	public CountDownTimer resttimer;
	public String status;
	public TextView WorkingStatus;

	public String work_msg_str;
	public String rest_msg_str;

	// Layout
	public RelativeLayout ll;

	// bool on whether or not to run rest timer
	public boolean do_rest;

	private static final String TAG = "MyActivity";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_second);

		// Count variable - incremented in onFinish() method of rest portion
		n = 1;

		// Whether or not to run the rest period. Only used with pause/resume
		// functionality
		do_rest = true;

		ll = (RelativeLayout) findViewById(R.id.TimerLayout);

		// Get the message from the intent
		Intent intent = getIntent();

		String work_timer = intent.getStringExtra(MainActivity.TIMER_WORK);

		rest_timer = Integer.parseInt(intent
				.getStringExtra(MainActivity.TIMER_REST)) * 1000;

		interval = Integer.parseInt(intent
				.getStringExtra(MainActivity.TIMER_INTERVAL));

		text = (TextView) findViewById(R.id.textView1);

		work_msg_str = intent.getStringExtra(MainActivity.WORK_MSG);
		rest_msg_str = intent.getStringExtra(MainActivity.REST_MSG);

		WorkingStatus = (TextView) findViewById(R.id.textView2);
		WorkingStatus.setText(work_msg_str);

		worktime = Integer.parseInt(work_timer) * 1000;

		this.startWorkTimer(worktime);

		/**
		 * 
		 * PAUSE/RESUME BUTTON
		 * 
		 */
		Button button = (Button) findViewById(R.id.timeraction);

		button.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Button b1 = (Button) findViewById(R.id.timeraction);

				// /Log.v(TAG, "meesage " + b1.getText());

				if (status == "running") {

					if (stage == "work") {

						mastertimer.cancel();

					} else if (stage == "rest") {

						resttimer.cancel();
					}

					status = "stopped";

					b1.setText("Resume");

				} else if (status == "stopped") {

					long time = worktime;

					if (stage == "work") {
						time = s1;
						Integer inttime = (int) time;
						startWorkTimer(inttime);
					} else if (stage == "rest") {
						// Flip the do_rest variable to false so that we don't
						// run the rest period twice
						do_rest = false;

						time = s2;
						Integer inttime = (int) time;
						startWorkTimer(inttime);
					}

					b1.setText("Pause");
				}

			}
		});

		/**
		 * 
		 * RESET BUTTON
		 * 
		 */

		Button button2 = (Button) findViewById(R.id.button1);

		button2.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Button b1 = (Button) findViewById(R.id.timeraction);
				WorkingStatus.setText(work_msg_str);

				// Reset counter
				n = 1;
				if (stage == "work") {

					b1.setText("Resume");

					mastertimer.cancel();
					startWorkTimer(worktime);

				} else if (stage == "rest") {

					b1.setText("Pause");

					resttimer.cancel();
					startWorkTimer(worktime);
				}

			}
		});
	}

	public void startRestTimer(Integer length) {

		ll.setBackgroundColor(Color.RED);

		WorkingStatus.setText(rest_msg_str);

		resttimer = new CountDownTimer(rest_timer, 1000) {

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
					ll.setBackgroundColor(Color.BLUE);
					WorkingStatus.setText("");
					text.setText("done!");
				}

			}

		}.start();

	}

	public void startWorkTimer(Integer length) {

		status = "running";
		WorkingStatus.setText(work_msg_str);
		ll.setBackgroundColor(Color.GREEN);

		mastertimer = new CountDownTimer(length, 1000) {

			public void onTick(long millisUntilFinished) {

				stage = "work";
				s1 = millisUntilFinished;

				text.setText(formatTime(millisUntilFinished));
			}

			public void onFinish() {
				startRestTimer(rest_timer);

			}

		}.start();

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
