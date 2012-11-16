package com.example.petetimer;

import android.app.Activity;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends Activity {
	private TextView text;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        text = (TextView) findViewById(R.id.textView1);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
    public void startTimer(View view){
    	
    	 new CountDownTimer(30000, 1000) {

    	     public void onTick(long millisUntilFinished) {
    	        
				text.setText("seconds remaining: " + millisUntilFinished / 1000);
    	     }

    	     public void onFinish() {
    	         text.setText("done!");
    	     }
    	     
    	  }.start();
    	
    }
}
