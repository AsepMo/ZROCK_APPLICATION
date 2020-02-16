package zrock.application;

import zrock.application.engine.app.plugin.AssetManager;
import zrock.application.engine.app.plugin.PluginManager;

import android.app.Application;
import android.content.Context;

public class ApplicationMain extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
		PluginManager.init(getApplicationContext());
		AssetManager.setFolder(getApplicationContext());
    }
	
}
