package zrock.application.scriptme.utils;

import zrock.application.R;

import android.app.AlertDialog;
import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.content.DialogInterface;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Environment;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.concurrent.CountDownLatch;
import android.widget.*;

public class RunScript
{
	public static class Wrapper
	{
        public String info;
    }

	public static class start extends AsyncTask<String, Void, Wrapper>
	{

        private Context mContext;
        private String rootView = null;

        public start(Context context, String rootView)
		{
            this.mContext = context;
            this.rootView = rootView;
        }

        @Override
        protected void onProgressUpdate(Void... values)
		{
            super.onProgressUpdate(values);
        }

        @Override
        public Wrapper doInBackground(String... args)
		{
            final Wrapper w1 = new Wrapper();

            publishProgress(new Void[]{});

            final CountDownLatch latch3 = new CountDownLatch(1);

            try
			{
                Process su = Runtime.getRuntime().exec("su");
                DataOutputStream outputStream = new DataOutputStream(su.getOutputStream());
                outputStream.writeBytes(rootView);
                outputStream.writeBytes("exit\n");
                outputStream.flush();
                BufferedReader reader = new BufferedReader(
					new InputStreamReader(su.getInputStream()));
                int read;
                char[] buffer = new char[4096];
                StringBuffer output = new StringBuffer();
                while ((read = reader.read(buffer)) > 0)
				{
                    output.append(buffer, 0, read);
                }
                reader.close();

                w1.info = output.toString().trim();

                su.waitFor();

                latch3.countDown();

            }
			catch (IOException e)
			{
                throw new RuntimeException(e);
            }
			catch (InterruptedException e)
			{
                throw new RuntimeException(e);
            }

            return w1;
        }


        @Override
        public void onPostExecute(final Wrapper w1)
		{

        }
    }

	public static boolean pack = true;
	public static void openPackage(Context mContext , String packMe)
	{
		try
		{
			Intent intent = mContext.getPackageManager().getLaunchIntentForPackage(packMe); 
			intent.addCategory(Intent.CATEGORY_LAUNCHER);
			mContext.startActivity(intent);
			pack = true;
		}
		catch (Exception e)
		{
			//e.printStackTrace();
			Intent intent = new Intent(Intent.ACTION_VIEW); 
			intent.setData(Uri.parse("https://play.google.com/store?hl=en")); 
			mContext.startActivity(intent); 
			pack = false;
		}
	}

	public static void openTerminal(Context mContext)
	{
		try
		{
			Intent intent = mContext.getPackageManager().getLaunchIntentForPackage("jackpal.androidterm"); 
			intent.addCategory(Intent.CATEGORY_LAUNCHER);
			mContext.startActivity(intent);
			pack = true;
		}
		catch (Exception e)
		{
			//e.printStackTrace();
			Intent intent = new Intent(Intent.ACTION_VIEW); 
			intent.setData(Uri.parse("https://play.google.com/store?hl=en")); 
			mContext.startActivity(intent); 
			pack = false;
		}
	}

	public static void openTermux(Context mContext)
	{
		try
		{
			Intent intent = mContext.getPackageManager().getLaunchIntentForPackage("com.termux"); 
			intent.addCategory(Intent.CATEGORY_LAUNCHER);
			mContext.startActivity(intent);
			pack = true;
		}
		catch (Exception e)
		{
			//e.printStackTrace();
			Intent intent = new Intent(Intent.ACTION_VIEW); 
			intent.setData(Uri.parse("https://play.google.com/store?hl=en")); 
			mContext.startActivity(intent); 
			pack = false;
		}
	}

	public static void openScript(Context mContext ,String Command)
	{
		try
		{
			Intent intent = new Intent("jackpal.androidterm.RUN_SCRIPT");
			intent.addCategory(Intent.CATEGORY_DEFAULT);
			intent.putExtra("jackpal.androidterm.iInitialCommand", Command);	
			mContext.startActivity(intent);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			Toast.makeText(mContext, "Script Error" , Toast.LENGTH_SHORT).show();
		}
	}
}
