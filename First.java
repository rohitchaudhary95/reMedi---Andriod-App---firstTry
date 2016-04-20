package com.example.project;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.MediaController;  
import android.widget.VideoView;  
import android.net.Uri;

public class First extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_first);
	
		VideoView videoView =(VideoView)findViewById(R.id.videoView1);  
        
        //Creating MediaController  
MediaController mediaController= new MediaController(this);  
    mediaController.setAnchorView(videoView);          
 
      //specify the location of media file  
   Uri uri=Uri.parse(Environment.getExternalStorageDirectory().getPath()+"/Download/first.mp4");          
        
      //Setting MediaController and URI, then starting the videoView  
   videoView.setMediaController(mediaController);  
   videoView.setVideoURI(uri);          
   videoView.requestFocus();  
   videoView.start();  
	
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.first, menu);
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
