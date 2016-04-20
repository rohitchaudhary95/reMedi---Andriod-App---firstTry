package com.example.project;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import android.support.v7.app.ActionBarActivity;
import android.app.ActionBar;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class UserLogin extends ActionBarActivity {

	String url;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_login);
		
	  /*SharedPreferences preferences = getSharedPreferences("Login",MODE_PRIVATE);
	    String em=preferences.getString("email","");
	    if(!em.equals("")){
	    	Intent i=new Intent(getApplicationContext(),Subscriptions.class);
        	i.putExtra("email", em);
        	finish();
        	startActivity(i);
	    }
	        
		
		String fontPath1 = "fonts/Caviar_Dreams_Bold.ttf";
	    Typeface tf1 = Typeface.createFromAsset(getAssets(), fontPath1);
	    String fontPath2 = "fonts/CaviarDreams.ttf";
	    Typeface tf2 = Typeface.createFromAsset(getAssets(), fontPath2);
	     TextView t1=(TextView)findViewById(R.id.h1);
	    TextView t2=(TextView)findViewById(R.id.h2);
	    TextView t3=(TextView)findViewById(R.id.h5);
	    
	    t1.setTypeface(tf1);
	    t2.setTypeface(tf2);
	    t3.setTypeface(tf2);
        */
		
		final LinearLayout ll=(LinearLayout)findViewById(R.id.ll);
		ll.setVisibility(View.GONE);
		
	   
		Button bt=(Button)findViewById(R.id.h5);
		bt.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				EditText e=(EditText)findViewById(R.id.h3);
				email=e.getText().toString();
				EditText p=(EditText)findViewById(R.id.h4);
				pw=p.getText().toString();
				
				CheckBox c1=(CheckBox)findViewById(R.id.checkBox1);
				if(c1.isChecked()){
					type=c1.getText().toString();
				}
				CheckBox c2=(CheckBox)findViewById(R.id.checkBox2);
				if(c2.isChecked()){
					type=c2.getText().toString();
				}
				CheckBox c3=(CheckBox)findViewById(R.id.checkBox3);
				if(c3.isChecked()){
					type=c3.getText().toString();
				}
				
				new Login().execute();
				
			}
		});
		
		Button pro=(Button)findViewById(R.id.h7);
		pro.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ll.setVisibility(View.VISIBLE);
			}
		});
		
	}
	
	String type="user";
	String email,pw,nm;
	Boolean st;
	ProgressDialog pDialog;
	private class Login extends AsyncTask<Void, Void, Void> {
	   	 
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            pDialog = new ProgressDialog(UserLogin.this);
            pDialog.setMessage("Loggin in...");
            pDialog.setCancelable(false);
            pDialog.show();
 
        }
 
        @Override
        protected Void doInBackground(Void... arg0) {
            // Creating service handler class instance
        	try {
                
				url="http://"+Constants.ip+":3000/api/login";//?email="+email+"&password="+pw;
				ServiceHandler sh = new ServiceHandler();
				
				List<NameValuePair> params = new ArrayList<NameValuePair>();
				params.add(new BasicNameValuePair("username", email));
				params.add(new BasicNameValuePair("password", pw));
				params.add(new BasicNameValuePair("types", type));
				
				
				String jsonStr = sh.makeServiceCall(url, ServiceHandler.POST, params);

				Log.d("Response: ", "> " + jsonStr);
            	if (jsonStr != null) {
                    JSONObject jsonObj = new JSONObject(jsonStr);
                    
                    st=jsonObj.getBoolean("success");
                    
                    if(st){
                    	nm= jsonObj.getString("name");
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
            if(st){
            	Toast.makeText(getApplicationContext(), "Successfully Logged in as "+nm,Toast.LENGTH_LONG).show();
            	
            	/*SharedPreferences pref=getSharedPreferences("Login",MODE_PRIVATE);;
            	SharedPreferences.Editor editor = pref.edit();
        		editor.putString("email", email);
                editor.commit();*/
            	
            	Intent i=new Intent(getApplicationContext(),Welcome.class);
            	i.putExtra("email", email);
            	finish();
            	startActivity(i);
            }
            else{
            	Toast.makeText(getApplicationContext(), "Some error occurred! Recheck username and password",Toast.LENGTH_LONG).show();
            }
       }
 
    }

	
}
