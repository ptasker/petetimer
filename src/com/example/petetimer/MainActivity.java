package com.example.petetimer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {

	public final static String TIMER_WORK = "com.example.mainactivity.WORK";
	public final static String TIMER_REST = "com.example.mainactivity.REST";
	public final static String TIMER_INTERVAL = "com.example.mainactivity.INTERVAL";
	
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
    
    }


    public void startTimer(View view){
    	
    	Intent intent = new Intent(this, Second.class);
    	
        EditText work = (EditText) findViewById(R.id.work);
        String work_msg = work.getText().toString();
        
        EditText rest = (EditText) findViewById(R.id.rest);
        String rest_msg = rest.getText().toString();
        
        EditText interval = (EditText) findViewById(R.id.repeat);
        String interval_msg = interval.getText().toString();
        
        
        intent.putExtra(TIMER_WORK, work_msg);
        intent.putExtra(TIMER_REST, rest_msg);
        intent.putExtra(TIMER_INTERVAL, interval_msg);
        
        startActivity(intent);
    	
    }
    
    
//    public void startTimer(View view){
//    	
//    	 new CountDownTimer(30000, 1000) {
//
//    	     public void onTick(long millisUntilFinished) {
//    	        
//				text.setText("seconds remaining: " + millisUntilFinished / 1000);
//    	     }
//
//    	     public void onFinish() {
//    	         text.setText("done!");
//    	     }
//    	     
//    	  }.start();
//    	
//    }
}
