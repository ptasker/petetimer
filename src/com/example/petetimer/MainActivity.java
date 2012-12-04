package com.example.petetimer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {

	public final static String TIMER_WORK = "com.example.mainactivity.WORK";
	public final static String TIMER_REST = "com.example.mainactivity.REST";
	public final static String TIMER_INTERVAL = "com.example.mainactivity.INTERVAL";
	public final static String WORK_MSG = "com.example.mainactivity.WORK_MSG";
	public final static String REST_MSG = "com.example.mainactivity.REST_MSG";
	public EditText rest;
	public EditText work;
	public EditText work_msg_field;
	public EditText rest_msg_field;
	public EditText interval;
	public Button button;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		work = (EditText) findViewById(R.id.work);
		rest = (EditText) findViewById(R.id.rest);

		work_msg_field = (EditText) findViewById(R.id.EditText01);
		rest_msg_field = (EditText) findViewById(R.id.EditText02);

		interval = (EditText) findViewById(R.id.repeat);
		button = (Button) findViewById(R.id.button1);

		TextWatcher watcher = new LocalTextWatcher();

		work.addTextChangedListener(watcher);
		rest.addTextChangedListener(watcher);
		interval.addTextChangedListener(watcher);
		work_msg_field.addTextChangedListener(watcher);
		rest_msg_field.addTextChangedListener(watcher);

		// button.setEnabled(false);

		updateButtonState();
	}

	public void startTimer(View view) {

		Intent intent = new Intent(this, Second.class);

		String work_msg = work.getText().toString();
		String rest_msg = rest.getText().toString();
		String interval_msg = interval.getText().toString();
		String work_msg_intent = work_msg_field.getText().toString();
		String rest_msg_intent = rest_msg_field.getText().toString();

		intent.putExtra(TIMER_WORK, work_msg);
		intent.putExtra(TIMER_REST, rest_msg);
		intent.putExtra(TIMER_INTERVAL, interval_msg);
		intent.putExtra(WORK_MSG, work_msg_intent);
		intent.putExtra(REST_MSG, rest_msg_intent);

		startActivity(intent);

	}

	void updateButtonState() {
		boolean enabled = checkEditText(work) && checkEditText(rest)
				&& checkEditText(interval) && checkEditText(work_msg_field)
				&& checkEditText(rest_msg_field);
		button.setEnabled(enabled);
	}

	private boolean checkEditText(EditText edit) {
		return ((edit.getText().toString()).length() > 0);
	}

	private class LocalTextWatcher implements TextWatcher {
		public void afterTextChanged(Editable s) {
			updateButtonState();
		}

		public void beforeTextChanged(CharSequence s, int start, int count,
				int after) {
		}

		public void onTextChanged(CharSequence s, int start, int before,
				int count) {
		}
	}
}
