package com.mitiku.dicegame;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ProgressBar;
import android.widget.TextView;


public class LoaderPage extends Activity

{
	final int VISIBLE	= 0;
	final int INVISIBLE = 4;
	final int GONE		= 8;
	private static int progress;
	private int progressStatus = 0;
	private ProgressBar progressBar;
	private Handler handler = new Handler();
	TextView	loadingText;
	String 		name = "";
	String 		lengTxt = null;
	int			REQUEST_CODE = 1;
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.loader_layout);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

		initialize();
		
		new Thread(new Runnable() 
		{
			
			@Override
			public void run() 
			{
				while(progressStatus < 100)
				{
					progressStatus = doSomework();
					handler.post(new Runnable() 
					{						
						@Override
						public void run() 
						{							
							progressBar.setProgress(progressStatus);
						}
					});
				}
				
				handler.post(new Runnable() 
				{					
					@Override
					public void run() 
					{
						progressBar.setVisibility(GONE);
						loadingText.setVisibility(GONE);
						
						gameActivity();
					}
					
					private void gameActivity()
					{
						Intent intent = new Intent("com.mitiku.dicegame.GAMESCREEN");
						Bundle bnd = new Bundle();
						bnd.putString("PlayerName", name);
						bnd.putString("length", lengTxt);
						intent.putExtras(bnd);
						startActivityForResult(intent, REQUEST_CODE);
					}
										
				});
			}
			
			private int doSomework()
			{
						
				for(int i = 0; i< 50000; i++){}
				
				return ++progress;
			}
		}).start();
		
	}
	
	
	private void initialize()
	{
		Bundle extras = getIntent().getExtras();
		if(extras != null)
		{
			name = extras.getString("Name");
			lengTxt = extras.getString("Length");
			
		}
		
		progress = 0;
		progressBar = (ProgressBar)findViewById(R.id.progressBar1);
		progressBar.setMax(100);
		
		loadingText		= (TextView)findViewById(R.id.loaderTxt);
		
		String l = name + " Remember you will play " + "\t\t\t"+lengTxt + " times\n" +
				"\n\t\t\tloading.....";
		loadingText.setText(l);
	}

}
