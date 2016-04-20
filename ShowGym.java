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
import android.text.InputFilter.LengthFilter;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class ShowGym extends Activity {
	String city="";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_show_gym);
	
		Button bt=(Button)findViewById(R.id.button1);
		bt.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				EditText e=(EditText)findViewById(R.id.editText1);
				city=e.getText().toString();
				new Gym().execute();
			}
		});
	
	}

	ProgressDialog pDialog;
	String url;
	String name[], phone[],charges[],description[];
	private class Gym extends AsyncTask<Void, Void, Void> {
	   	 
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            pDialog = new ProgressDialog(ShowGym.this);
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();
 
        }
 
        @Override
        protected Void doInBackground(Void... arg0) {
            // Creating service handler class instance
        	try {
                
				url="http://"+Constants.ip+":3000/api/viewGyms";
				ServiceHandler sh = new ServiceHandler();
				
				List<NameValuePair> params = new ArrayList<NameValuePair>();
				params.add(new BasicNameValuePair("city", "delhi"));
				
			
				String jsonStr = sh.makeServiceCall(url, ServiceHandler.POST, params);

				Log.d("Response: ", "> " + jsonStr);
            	if (jsonStr != null) {
                    JSONObject jsonObj = new JSONObject("{ data: "+jsonStr+"}");
                    
                    
                    JSONArray ar=jsonObj.getJSONArray("data");
                    name=new String[ar.length()];
                    charges = new String[ar.length()];
                    phone=new String[ar.length()];
                    description=new String[ar.length()];
                    
                    for(int i=0;i<ar.length();i++){
                    	JSONObject ob=ar.getJSONObject(i);
                    	Log.d("hello","hello");
                    	
                    	name[i]=ob.getString("name");
                    	charges[i]=ob.getString("charges");
                    	description[i]=ob.getString("description");
                    	phone[i]=ob.getString("phoneNo");
                    }
            
                
            } else {
                Log.e("ServiceHandler", "Couldn't get any data from the url");
            }
		 } catch (Exception ex) {
				//Toast.makeText(getApplicationContext(), ex.toString(),Toast.LENGTH_LONG).show();
			 Log.d(ex.toString(),"hello");
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
            /*for(int i=0;i<name.length;i++){
            	Toast.makeText(ShowGym.this, name[i], Toast.LENGTH_LONG).show();
            }*/
            Log.d("after","after");
            	CustomList ad=new CustomList(ShowGym.this, name,charges);
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
		getMenuInflater().inflate(R.menu.show_gym, menu);
		
		
		
				return true;
	}

}
