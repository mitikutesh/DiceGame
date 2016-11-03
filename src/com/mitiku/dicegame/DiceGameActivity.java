package com.mitiku.dicegame;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Window;

public class DiceGameActivity extends Activity {
    /** Called when the activity is first created. */
	MediaPlayer introMusic;
	
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        
        setContentView(R.layout.intro);
        
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        
        introMusic = MediaPlayer.create(this, R.raw.intropro);
        introMusic.start();
        Thread logoTimer = new Thread(){
        	public void run()
        	{
        		try{

        			int logoTimer = 0;
        			while(logoTimer < 5100)
        			{
        				sleep(100);
        				logoTimer = logoTimer+100;
        			}
        			
        			startActivity(new Intent("com.mitiku.dicegame.CLEARSCREEN"));
        		} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        		finally{
        			finish();
        		}
        	}
        };
        logoTimer.start();
    }
    

    @Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		introMusic.release();
	}
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		introMusic.pause();
	}
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		introMusic.start();
	}

}