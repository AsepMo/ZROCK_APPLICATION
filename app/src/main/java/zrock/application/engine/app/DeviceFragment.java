package zrock.application.engine.app;

import zrock.application.R;

import android.support.v4.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Build;
import android.os.Handler;
import android.util.Log;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.ImageView;


public class DeviceFragment extends Fragment
{
	private String ModelNumber, Board, Brand, Display, FingerPrint, ID, TAGS, Type, 
	AndroidVersion, APILevel, CodeName, INCREMENTAL;

	TextView text;
	public View rootView;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		// TODO: Implement this method
		rootView = inflater.inflate(R.layout.fragment_application_engine_device ,container ,false);
		ModelNumber = android.os.Build.MODEL;
        Board = android.os.Build.BOARD;
        Brand = android.os.Build.BRAND;
        Display = android.os.Build.DISPLAY;
        FingerPrint = android.os.Build.FINGERPRINT;
        ID = android.os.Build.ID;
        TAGS = android.os.Build.TAGS;
        Type = android.os.Build.TYPE;

        AndroidVersion = android.os.Build.VERSION.RELEASE;
        APILevel = android.os.Build.VERSION.SDK;
        CodeName = android.os.Build.VERSION.CODENAME;
        INCREMENTAL = android.os.Build.VERSION.INCREMENTAL;

        text = (TextView) rootView.findViewById(R.id.device_info);

        text.setText(Html.fromHtml("Phone Type" +
								   "<br/><br/><font color = 'red';>Model Number : </font></font>" + ModelNumber 
								   + "<br/><font color = 'red';>Board : </font>" + Board
								   + "<br/><font color = 'red';>Brand : </font>" + Brand
								   + "<br/><font color = 'red';>Display : </font>" + Display
								   + "<br/><font color = 'red';>FingerPrint : </font>" + FingerPrint
								   + "<br/><font color = 'red';>ID : </font>" + ID
								   + "<br/><font color = 'red';>TAGS : </font>" + TAGS
								   + "<br/><font color = 'red';>Type : </font>" + Type        		

								   + "<br/>"

								   + "<br/><font color = 'red';>Android Version : </font>" + AndroidVersion
								   + "<br/><font color = 'red';>API Level : </font>" + APILevel
								   + "<br/><font color = 'red';>CodeName : </font>" + CodeName
								   + "<br/><font color = 'red';>INCREMENTAL : </font>" + INCREMENTAL));
		
		return rootView;
	}

}
