package com.mitiku.dicegame;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class UserPage extends Activity
{
	Button cancelBtn;
	Button okBtn;
	EditText nameTxt;
	MediaPlayer btnMusic;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.userpage);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

		cancelBtn = (Button)findViewById(R.id.DataCancelbtn);
		okBtn     = (Button)findViewById(R.id.DataOkbtn);
		nameTxt   = (EditText)findViewById(R.id.EnterName);
		btnMusic = MediaPlayer.create(this, R.raw.btnclick);
		
		
		
		cancelBtn.setOnClickListener(new View.OnClickListener() 
		{			
			@Override
			public void onClick(View v) 
			{	
				btnMusic.start();
				finish();
			}
		});

		okBtn.setOnClickListener(new View.OnClickListener() 
		{
			@Override
			public void onClick(View v) 
			{
				btnMusic.start();
				Intent data = new Intent();
				data.setData(Uri.parse((nameTxt.getText().toString())));
					
				setResult(RESULT_OK,data);
								
				finish();
			}
		});
	}
	
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		
		btnMusic.pause();

		
		super.onPause();
	}


	@Override
	protected void onRestart() {
		// TODO Auto-generated method stub
		btnMusic.start();
		super.onRestart();
	}


	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		
		btnMusic.stop();

		super.onDestroy();
	}
	
	

}
