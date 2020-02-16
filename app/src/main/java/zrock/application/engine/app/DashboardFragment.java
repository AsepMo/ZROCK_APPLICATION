package zrock.application.engine.app;

import zrock.application.R;
import zrock.application.engine.*;
import zrock.application.engine.widget.BannerLayout;
import zrock.application.engine.widget.layout.adapter.WebBannerAdapter;

import zrock.application.superuser.excution.Shell;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Build;
import android.os.Handler;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.util.Collection;
import java.util.List;
import java.util.ArrayList;


public class DashboardFragment extends Fragment
{

	static final int[] NarrowImage = {
		R.drawable.menu_engine,
		R.drawable.menu_root,
		R.drawable.menu_busybox,
		R.drawable.menu_superuser,
		R.drawable.menu_scriptme,
		R.drawable.menu_connection,
		R.drawable.menu_cloud,
    };
	
    public static ArrayList<Integer> getNarrowImage(){
        ArrayList<Integer> arrayList = new ArrayList<>();
        for (int i = 0; i < NarrowImage.length; i++) {
            arrayList.add(NarrowImage[i]);
        }
        return arrayList;
    }
	public static final String TAG  = DashboardFragment.class.getSimpleName();
	public View rootView;
	private BannerLayout mBannerLayout;
	private WebBannerAdapter mWebBannerAdapter;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		// TODO: Implement this method
		rootView = inflater.inflate(R.layout.fragment_application_dashboard ,container ,false);
		       
		ImageView icon = (ImageView)rootView.findViewById(R.id.zrock_icon);
		icon.setImageResource(R.mipmap.ic_launcher_zrock);

		// output
		StringBuilder sb = (new StringBuilder()).append("Device   :").append("   ").append(Build.DEVICE);
		TextView device = (TextView)rootView.findViewById(R.id.zrock_pkgname);
		device.setText(sb.toString());
		StringBuilder sb1 = (new StringBuilder()).append("Version :").append("   ").append("ZROCK ENGINE");
		TextView version = (TextView)rootView.findViewById(R.id.zrock_vname);
		version.setText(sb1.toString());
		StringBuilder sb2 = (new StringBuilder()).append("Version (internal) :").append("   ").append("v3.6");
		TextView internal = (TextView)rootView.findViewById(R.id.zrock_vc);
		internal.setText(sb2.toString());
		StringBuilder sb3 = (new StringBuilder()).append("Package   :").append("   ").append("zrock.application");
		TextView pack = (TextView)rootView.findViewById(R.id.zrock_firsttime);
		pack.setText(sb3.toString());
		StringBuilder sb4 = (new StringBuilder()).append("CopyRight©2020").append(" ").append("- AndroWeb");
		TextView powered = (TextView)rootView.findViewById(R.id.zrock_lastupdated);
		powered.setText(sb4.toString());
		
		// Let's do some background stuff
		mBannerLayout =  rootView.findViewById(R.id.recycler_ver);
        mWebBannerAdapter =new WebBannerAdapter(getActivity(),getNarrowImage());
        mWebBannerAdapter.setOnBannerItemClickListener(new BannerLayout.OnBannerItemClickListener() {
				@Override
				public void onItemClick(int position) {
					Toast.makeText(getActivity(), "You Clicked On: " + position, Toast.LENGTH_SHORT).show();
				}
			});
			
		(new Startup()).setContext(getActivity()).execute();
		return rootView;
	}
	
	public class Startup extends AsyncTask<Void, Void, Void>
	{
        private ProgressDialog dialog = null;
        private Context context = null;
        public Startup setContext(Context context)
		{
            this.context = context;
            return this;
        }

        @Override
        protected void onPreExecute()
		{
			// We're creating a progress dialog here because we want the user to wait.
            // If in your app your user can just continue on with clicking other things,
            // don't do the dialog thing.

            dialog = new ProgressDialog(context);
            dialog.setTitle("ZROCK PROGRESS");
            dialog.setMessage("Please Wait ...");
            dialog.setIndeterminate(true);
            dialog.setCancelable(false);
            dialog.show();
        }

        @Override
        protected Void doInBackground(Void... params)
		{
			Log.i(TAG, "Load Banner List...");
			
			// This is just so you see we had a progress dialog, 
            // don't do this in production code
            try
			{ Thread.sleep(5000); }
			catch (Exception e)
			{ }

            return null;
        }

        @Override
        protected void onPostExecute(Void result)
		{
			dialog.dismiss();
			mBannerLayout.setAdapter(mWebBannerAdapter);
            setContext(null);
        }
    }
	
}
