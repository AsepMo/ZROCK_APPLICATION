package zrock.application.engine.app;

import zrock.application.R;

import android.support.v4.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Build;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.ImageView;


public class MessageFragment extends Fragment
{

	public View rootView;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		// TODO: Implement this method
		rootView = inflater.inflate(R.layout.fragment_application_messages ,container ,false);

		return rootView;
	}

}
