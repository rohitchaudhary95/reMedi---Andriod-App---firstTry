package com.example.project;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class Bloodbank extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_bloodbank);
		
		ImageView pro=(ImageView)findViewById(R.id.imageView1);
		pro.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(Bloodbank.this,Showdonor.class);
				intent.putExtra("bloodGroup", "O+");
				startActivity(intent);
				//Toast.makeText(getApplicationContext(), "Yaha code change karlo", Toast.LENGTH_LONG).show();
			
			}
		});
		
		
	
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.bloodbank, menu);
		return true;
	}

	
}

