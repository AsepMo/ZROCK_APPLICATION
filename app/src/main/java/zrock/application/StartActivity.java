package zrock.application;

import zrock.application.engine.widget.CircularProgressBar;
import zrock.application.root.checker.RootChecker;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Build;
import android.graphics.Color;
import android.content.Intent;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ImageView;

public class StartActivity extends AppCompatActivity 
{
	public static final String TAG  = StartActivity.class.getSimpleName();
	public static final int ALERT_DIALOG = 0;
    private static int SPLASH_TIME_OUT = 2500;
	private Handler mHandler = new Handler();
	private TextView mVersion;
	private ImageView mIconLoading;
	private CircularProgressBar mProgress;
	private TextView mPleaseWite;
	@Override
    protected Dialog onCreateDialog(int id) {

        Dialog dialog;
        AlertDialog.Builder builder;
        switch (id) {
            case ALERT_DIALOG:
                builder = new AlertDialog.Builder(StartActivity.this);
				builder.setTitle(R.string.app_name);
                builder.setMessage(R.string.root)
					.setCancelable(false)
					.setNegativeButton(R.string.exit, new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int id) {
							Intent intent = new Intent(Intent.ACTION_MAIN);
							intent.addCategory(Intent.CATEGORY_HOME);
							intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
							startActivity(intent);
							StartActivity.this.finish();
							System.exit(0);
						}
					});

                dialog = builder.create();
                break;

            default:
                dialog = null;
        }
        return dialog;
    }
	
	@Override
    protected void onCreate(Bundle savedInstanceState)
	{
        setTheme(R.style.Theme_About);
		super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_application_start);
		
	    mIconLoading = (ImageView)findViewById(R.id.splash_text_1);
		mIconLoading.setImageResource(R.drawable.icon_zrock);
		mVersion = (TextView)findViewById(R.id.splash_text_2);
		mVersion.setText("v3.6");
		mPleaseWite = (TextView)findViewById(R.id.loading);
	    mProgress = (CircularProgressBar) findViewById(R.id.progressBar_1);
		loadMainActivity();
	}

	public void loadMainActivity()
	{
		mHandler.postDelayed(new Runnable(){
				@Override
				public void run()
				{
					mProgress.setProgress(30f);
					if (RootChecker.isDeviceRooted())
					{
						// device is rooted
						mIconLoading.setImageResource(R.drawable.device_rooted);	
						mPleaseWite.setVisibility(View.GONE);
						mProgress.setVisibility(View.GONE);
						mHandler.postDelayed(new Runnable(){
								@Override
								public void run()
								{
									ApplicationActivity.start(StartActivity.this);
									StartActivity.this.finish();
								}
							}, SPLASH_TIME_OUT);
					}else{
						//ApplicationActivity.start(StartActivity.this);
						//StartActivity.this.finish();
						showDialog(ALERT_DIALOG);
					}
				}

			}, 2500);
	}

}
