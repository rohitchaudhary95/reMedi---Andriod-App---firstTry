package com.example.project;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class Welcome extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_welcome);
		
		String opt[]={"Gyms","Doctor","Yoga","Blood bank"};
		
		ListView lv=(ListView)findViewById(R.id.left_drawer);
		ArrayAdapter<String> ad=new ArrayAdapter<String>(this,  android.R.layout.simple_list_item_1, opt);
		lv.setAdapter(ad);
		
		lv.setOnItemClickListener(new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				if(position == 0){
					Intent intent = new Intent(Welcome.this,ShowGym.class);
					//intent.putExtra("aa", "jkjkjk");
					Welcome.this.startActivity(intent);
					Toast.makeText(getApplicationContext(), "Yaha code change karlo", Toast.LENGTH_LONG).show();
				}
				if(position == 1){
					//Intent intent = new Intent(Welcome.this,ShowDoctor.class);
					//intent.putExtra("aa", "jkjkjk");
					//Welcome.this.startActivity(intent);
					//Toast.makeText(getApplicationContext(), "Yaha code change karlo", Toast.LENGTH_LONG).show();
				}
				if(position == 2){
					//Intent intent = new Intent(Welcome.this,ShowYoga.class);
					//intent.putExtra("aa", "jkjkjk");
					//Welcome.this.startActivity(intent);
					//Toast.makeText(getApplicationContext(), "Yaha code change karlo", Toast.LENGTH_LONG).show();
				}
				if(position == 3){
					Intent intent = new Intent(Welcome.this,Bloodbank.class);
					//intent.putExtra("aa", "jkjkjk");
					Welcome.this.startActivity(intent);
					Toast.makeText(getApplicationContext(), "Yaha code change karlo", Toast.LENGTH_LONG).show();
				}
			}
			
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.welcome, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
