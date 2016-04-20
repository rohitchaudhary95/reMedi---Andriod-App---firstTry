package com.example.project;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;



public class Showdonor extends Activity {
	String city="",ss="";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_showdonor);
	
		Intent intent = getIntent();
		ss = intent.getStringExtra("bloodGroup");

		Button bt=(Button)findViewById(R.id.button1);
		bt.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				EditText e=(EditText)findViewById(R.id.editText1);
				city=e.getText().toString();
				new Donor().execute();
			}
		});
	}
	
	ProgressDialog pDialog;
	String url;
	String name[], phone[];
	private class Donor extends AsyncTask<Void, Void, Void> {
	   	 
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            pDialog = new ProgressDialog(Showdonor.this);
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();
 
        }
 
        @Override
        protected Void doInBackground(Void... arg0) {
            // Creating service handler class instance
        	try {
                
				url="http://"+Constants.ip+":3000/api/finddonor";//?email="+email+"&password="+pw;
				ServiceHandler sh = new ServiceHandler();
				
				List<NameValuePair> params = new ArrayList<NameValuePair>();
				params.add(new BasicNameValuePair("bloodGroup",ss ));
				params.add(new BasicNameValuePair("city", city));
				
				
				String jsonStr = sh.makeServiceCall(url, ServiceHandler.POST, params);

				Log.d("Response: ", "> " + jsonStr);
            	if (jsonStr != null) {
                    JSONObject jsonObj = new JSONObject("{ data: "+jsonStr+"}");
                    
                    JSONArray ar=jsonObj.getJSONArray("data");
                    name=new String[ar.length()];
                    phone=new String[ar.length()];
                    
                    for(int i=0;i<ar.length();i++){
                    	JSONObject ob=ar.getJSONObject(i);
                    	name[i]=ob.getString("name");
                    	phone[i]=ob.getString("mobNo");
                    }
                    
                
            } else {
                Log.e("ServiceHandler", "Couldn't get any data from the url");
            }
		 } catch (Exception ex) {
				Toast.makeText(getApplicationContext(), ex.toString(),Toast.LENGTH_LONG).show();
           }
			
            return null;
        }
 
        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            // Dismiss the progress dialog
            if (pDialog.isShowing())
                pDialog.dismiss();
            /**
             * Updating parsed JSON data into ListView
             * */
               
            	/*SharedPreferences pref=getSharedPreferences("Login",MODE_PRIVATE);;
            	SharedPreferences.Editor editor = pref.edit();
        		editor.putString("email", email);
                editor.commit();*/
            for(int i=0;i<name.length;i++){
            	Toast.makeText(Showdonor.this, name[i], Toast.LENGTH_LONG).show();
            }
            	
            	CustomList ad=new CustomList(Showdonor.this, name, phone);
            	ListView lv=(ListView)findViewById(R.id.list);
            	lv.setAdapter(ad);
            	
            	lv.setOnItemClickListener(new OnItemClickListener() {

					@Override
					public void onItemClick(AdapterView<?> arg0, View arg1,
							int pos, long arg3) {
						// TODO Auto-generated method stub
						Intent callIntent = new Intent(Intent.ACTION_CALL);
					    callIntent.setData(Uri.parse("tel:"+phone[pos]));
					    startActivity(callIntent);
						
					}
				});
            
       }
 
    }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.showdonor, menu);
		return true;
	}

}
