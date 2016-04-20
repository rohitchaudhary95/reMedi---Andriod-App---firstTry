package com.example.project;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.hardware.Camera.Parameters;
import android.opengl.Visibility;
import android.sax.StartElementListener;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.Layout;
import android.text.TextUtils.TruncateAt;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;



public class CustomList extends ArrayAdapter<String>{

	private final Activity context;
	private final String[] name;
	private final String[] phone;


	public CustomList(Activity context,String[] name, String[] phone){
		super(context,R.layout.listview_item, name);
		this.context=context;
		this.name=name;
		this.phone=phone;
	}

	@Override
	public View getView(final int position, View view, ViewGroup parent) {
		// TODO Auto-generated method stub

         LayoutInflater inflater=context.getLayoutInflater();
         View rowView=view;
         if (view==null)
        	 rowView=inflater.inflate(R.layout.listview_item,null,true);	

         TextView tv1=(TextView)rowView.findViewById(R.id.dtextView1);
		 tv1.setText(name[position]);
		
		 TextView tv2=(TextView)rowView.findViewById(R.id.dtextView2);
		 tv2.setText(phone[position]);
		
		 return rowView;
	}
	
}
