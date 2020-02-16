package zrock.application.engine.app.plugin;

import zrock.application.R;
import zrock.application.superuser.excution.Shell;
import zrock.application.superuser.excution.Shell.SU;

import android.annotation.SuppressLint;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.preference.PreferenceManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.os.Environment;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class AssetManager {

    public static final String LOGTAG = "InstLib-AssetMgr";
	public static SharedPreferences sharedPreferences;
    public static void setFolder(Context mContext)
	{

		new File(FolderMe.FILES_PATH).mkdirs();
		new File(FolderMe.HOME_PATH).mkdirs();
		new File(FolderMe.USER_PATH).mkdirs();
		FolderMe.ExtractToAppCache(mContext,"zrock" ,".nomedia");
		FolderMe.ExtractToHome(mContext,"zrock",".nomedia");
		FolderMe.ExtractToHome_Path(mContext,"/zrock", "zrock",".nomedia");
		FolderMe.ExtractToUser(mContext,"zrock" ,".nomedia");
		FolderMe.ExtractToUser_Path(mContext,"/bin","zrock" ,".nomedia");

		FolderMe.RawToStorage(mContext,FolderMe.APK_PATH,"kingoroot.apk" ,R.raw.rooted);
		FolderMe.RawToStorage(mContext,FolderMe.IMAGE_PATH,".nomedia" ,R.raw.runme_install);
		FolderMe.RawToStorage(mContext,FolderMe.EBOOK_PATH,".nomedia" ,R.raw.runme_install);
		FolderMe.RawToStorage(mContext,FolderMe.AUDIO_PATH,".nomedia" ,R.raw.runme_install);
		FolderMe.RawToStorage(mContext,FolderMe.VIDEO_PATH,".nomedia" ,R.raw.runme_install);
		FolderMe.RawToStorage(mContext,FolderMe.ZIP_PATH,".nomedia" ,R.raw.runme_install);

	}

	public static void setFolder_Home(Context mContext)
	{
		sharedPreferences = PreferenceManager.getDefaultSharedPreferences(mContext.getApplicationContext());
        SharedPreferences.Editor editor = sharedPreferences.edit();
        String defValue = mContext.getDir("HOME", mContext.MODE_PRIVATE).getAbsolutePath();
        String homePath = sharedPreferences.getString("home_path", defValue);
        editor.putString("home_path", homePath);
        editor.commit();
	}
    
	public static File prepareApk(Context mContext,String assetName) {
        // Copy the given asset out into a file so that it can be installed.
        // Returns the path to the file.
        byte[] buffer = new byte[8192];
        InputStream is = null;
        FileOutputStream fout = null;
        try {
            is = mContext.getAssets().open(assetName);
            fout = mContext
			.openFileOutput("tmp.apk", Context.MODE_WORLD_READABLE);
            int n;
            while ((n=is.read(buffer)) >= 0) {
                fout.write(buffer, 0, n);
            }
        } catch (IOException e) {
            Log.i("InstallApk", "Failed transferring", e);
        } finally {
            try {
                if (is != null) {
                    is.close();
                }
            } catch (IOException e) {
            }
            try {
                if (fout != null) {
                    fout.close();
                }
            } catch (IOException e) {
            }
        }

        return mContext.getFileStreamPath("tmp.apk");
    }
}
