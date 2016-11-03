package com.mitiku.dicegame;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

public class WelcomePage extends Activity
{
	MediaPlayer backgrdMusic;
	MediaPlayer btnMusic;
	private Button ContBtn;
	private Button CancBtn;
	private TextView bgText;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		
		setContentView(R.layout.welcompage);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
       
        ContBtn = (Button)findViewById(R.id.welcomeOkbtn);
		CancBtn = (Button)findViewById(R.id.welcomeCancelBtn);
		bgText  = (TextView)findViewById(R.id.bgText);
		
		backgrdMusic = MediaPlayer.create(this, R.raw.mainsong);
		btnMusic= MediaPlayer.create(this, R.raw.btnclick);
		
		backgrdMusic.start();
		backgrdMusic.setLooping(true);
		
		bgText.setText("\n\t\t\tWELCOME TO MX DICE GAME!\t\t\t");
		
		CancBtn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) 
			{
				btnMusic.start();
				finish();
				
			}
		});

		ContBtn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) 
			{
				btnMusic.start();
				startActivity(new Intent("com.mitiku.dicegame.INFOSCREEN"));				
			}
		});
	}
	

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		backgrdMusic.pause();
		btnMusic.pause();

		
		super.onPause();
	}


	@Override
	protected void onRestart() {
		// TODO Auto-generated method stub
		backgrdMusic.start();
		super.onRestart();
	}


	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		backgrdMusic.stop();
		btnMusic.stop();

		super.onDestroy();
	}

}
