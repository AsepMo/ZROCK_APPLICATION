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

import java.util.HashSet;
import java.util.Set;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.IOException;

public class FolderMe
{
	public static final String LOGTAG = "InstLib-AssetMgr";
    /** Note that this is a symlink on the Android M preview. */
    @SuppressLint("SdCardPath")
    public static final String FILES_PATH = "/data/data/zrock.application/files";
    public static final String USER_PATH = FILES_PATH + "/user";
    public static final String HOME_PATH = FILES_PATH + "/home";
    public static final String FILES_INTERNAL_PATH = "/ZRock";
	public static final String APK_PATH = FILES_INTERNAL_PATH + "/Apk";
	public static final String IMAGE_PATH = FILES_INTERNAL_PATH + "/Image";
	public static final String EBOOK_PATH = FILES_INTERNAL_PATH + "/Ebook";
	public static final String AUDIO_PATH = FILES_INTERNAL_PATH + "/Audio";
	public static final String VIDEO_PATH = FILES_INTERNAL_PATH + "/Video";
	public static final String ZIP_PATH = FILES_INTERNAL_PATH + "/Zip";
	public static final String SCRIPTME_PATH = FILES_INTERNAL_PATH + "/files/ScriptMe";
	
	public static boolean ExtractToHome(Context context, String fileAsset, String output)
	{
       
		Log.i(LOGTAG, "Starting extraction of asset file \"" + fileAsset + "\"...");
        try
		{
            File folder = new File(HOME_PATH);
			if (!folder.exists())
			{
				folder.mkdir();
			}
			Log.i(LOGTAG, "The file will be extracted as \"" + folder + "/" + output + "\"");	
			InputStream in = context.getAssets().open(fileAsset);
			OutputStream out = new FileOutputStream(new File(folder + "/" + output));
			
            try
			{
                copyFile(in, out);
                in.close();
                out.flush();
                out.close();
                Log.i(LOGTAG, "Successfully extracted asset file!");
                return true;
            }
			catch (IOException e)
			{
                Log.e(LOGTAG, "Failed to extract asset file!");
                return false;
            }
        }
		catch (IOException e2)
		{
            Log.e(LOGTAG, "Failed to extract asset file!");
            return false;
        }
	}
	
	public static boolean ExtractToHome_Path(Context context,String directory, String fileAsset, String output)
	{

		Log.i(LOGTAG, "Starting extraction of asset file \"" + fileAsset + "\"...");
        try
		{
            File folder = new File(HOME_PATH + directory);
			if (!folder.exists())
			{
				folder.mkdir();
			}
			Log.i(LOGTAG, "The file will be extracted as \"" + folder + "/" + output + "\"");	
			InputStream in = context.getAssets().open(fileAsset);
			OutputStream out = new FileOutputStream(new File(folder + "/" + output));

            try
			{
                copyFile(in, out);
                in.close();
                out.flush();
                out.close();
                Log.i(LOGTAG, "Successfully extracted asset file!");
                return true;
            }
			catch (IOException e)
			{
                Log.e(LOGTAG, "Failed to extract asset file!");
                return false;
            }
        }
		catch (IOException e2)
		{
            Log.e(LOGTAG, "Failed to extract asset file!");
            return false;
        }
	}
	
	public static boolean ExtractToUser(Context context, String fileAsset, String output)
	{

		Log.i(LOGTAG, "Starting extraction of asset file \"" + fileAsset + "\"...");
        try
		{
            File folder = new File(USER_PATH);
			if (!folder.exists())
			{
				folder.mkdir();
			}
			Log.i(LOGTAG, "The file will be extracted as \"" + folder + "/" + output + "\"");	
			InputStream in = context.getAssets().open(fileAsset);
			OutputStream out = new FileOutputStream(new File(folder + "/" + output));

            try
			{
                copyFile(in, out);
                in.close();
                out.flush();
                out.close();
                Log.i(LOGTAG, "Successfully extracted asset file!");
                return true;
            }
			catch (IOException e)
			{
                Log.e(LOGTAG, "Failed to extract asset file!");
                return false;
            }
        }
		catch (IOException e2)
		{
            Log.e(LOGTAG, "Failed to extract asset file!");
            return false;
        }
	}
	
	public static boolean ExtractToUser_Path(Context context, String directory, String fileAsset, String output)
	{

		Log.i(LOGTAG, "Starting extraction of asset file \"" + fileAsset + "\"...");
        try
		{
            File folder = new File(USER_PATH + directory);
			if (!folder.exists())
			{
				folder.mkdir();
			}
			Log.i(LOGTAG, "The file will be extracted as \"" + folder + "/" + output + "\"");	
			InputStream in = context.getAssets().open(fileAsset);
			OutputStream out = new FileOutputStream(new File(folder + "/" + output));

            try
			{
                copyFile(in, out);
                in.close();
                out.flush();
                out.close();
                Log.i(LOGTAG, "Successfully extracted asset file!");
                return true;
            }
			catch (IOException e)
			{
                Log.e(LOGTAG, "Failed to extract asset file!");
                return false;
            }
        }
		catch (IOException e2)
		{
            Log.e(LOGTAG, "Failed to extract asset file!");
            return false;
        }
	}
	
    public static boolean ExtractToAppCache(Context context, String fileAsset, String output)
	{
        Log.i(LOGTAG, "Starting extraction of asset file \"" + fileAsset + "\"...");
        Log.i(LOGTAG, "The file will be extracted as \"" + context.getCacheDir() + "/" + output + "\"");
        try
		{
            InputStream in = context.getAssets().open(fileAsset);
            OutputStream out = new FileOutputStream(new File(context.getCacheDir() + "/" + output));
            try
			{
                copyFile(in, out);
                in.close();
                out.flush();
                out.close();
                Log.i(LOGTAG, "Successfully extracted asset file!");
                return true;
            }
			catch (IOException e)
			{
                Log.e(LOGTAG, "Failed to extract asset file!");
                return false;
            }
        }
		catch (IOException e2)
		{
            Log.e(LOGTAG, "Failed to extract asset file!");
            return false;
        }
    }

    public static boolean OpenRecoveryScript(Context context, String fileAsset)
	{
        Log.i(LOGTAG, "Loading OpenRecoveryScript from \"" + fileAsset + "\"...");
        try
		{
            InputStream in = context.getAssets().open(fileAsset);
            OutputStream out = new FileOutputStream(new File(context.getCacheDir() + "/openrecoveryscript"));
            try
			{
                copyFile(in, out);
                in.close();
                out.flush();
                out.close();
                Log.i(LOGTAG, "Successfully extracted OpenRecoveryScript!");
                Log.i(LOGTAG, "Loading OpenRecoveryScript into System...");
                SU.run("cp " + context.getCacheDir() + "/openrecoveryscript /cache/recovery/openrecoveryscript");
                Log.i(LOGTAG, "Loaded OpenRecoveryScript into system!");
                return true;
            }
			catch (IOException e)
			{
                Log.e(LOGTAG, "Failed to load OpenRecoveryScript into system!");
                return false;
            }
        }
		catch (IOException e2)
		{
            Log.e(LOGTAG, "Failed to load OpenRecoveryScript into system!");
            return false;
        }
    }
	
	public static boolean AssetsToStorage(Context mContext ,String Folder,String fileAsset ,String output) {
        Log.i(LOGTAG, "Starting extraction of asset file \"" + fileAsset + "\"...");
        Log.i(LOGTAG, "The file will be extracted as \"" + Folder + "/" + output + "\"");
    
		
        try {
			File path = new File(Environment.getExternalStorageDirectory() + File.separator + Folder);
			File file = new File(path + "/" + output);
			
            // Make sure the Pictures directory exists.
            path.mkdirs();

            InputStream in = mContext.getAssets().open(fileAsset);
            OutputStream out = new FileOutputStream(file);
            try
			{
                copyFile(in, out);
                in.close();
                out.flush();
                out.close();
                Log.i(LOGTAG, "Successfully extracted asset file!");
                return true;
            }
			catch (IOException e)
			{
                Log.e(LOGTAG, "Failed to extract asset file!");
                return false;
            }
        }
		catch (IOException e2)
		{
            Log.e(LOGTAG, "Failed to extract asset file!");
            return false;
        }    
    }
	
	public static void RawToStorage(Context mContext ,String Folder,String ZFile ,int Resources) {
        File path = new File(Environment.getExternalStorageDirectory() + File.separator + Folder);
        File file = new File(path, ZFile);

        try {
            // Make sure the Pictures directory exists.
            path.mkdirs();

            InputStream is = mContext.getResources().openRawResource(Resources);
            OutputStream os = new FileOutputStream(file);
            byte[] data = new byte[is.available()];
            is.read(data);
            os.write(data);
            is.close();
            os.close();

            // Tell the media scanner about the new file so that it is
            // immediately available to the user.
            MediaScannerConnection.scanFile(mContext,
				new String[] { file.toString() }, null,
				new MediaScannerConnection.OnScanCompletedListener() {
					public void onScanCompleted(String path, Uri uri) {
						Log.i("ExternalStorage", "Scanned " + path + ":");
						Log.i("ExternalStorage", "-> uri=" + uri);
					}
				});
        } catch (IOException e) {
            // Unable to create file, likely because external storage is
            // not currently mounted.
            Log.w("ExternalStorage", "Error writing " + file, e);
        }
    }

    public static void deleteDirectory_Apk(String apk) {
		File path = new File(Environment.getExternalStorageDirectory() + File.separator + FolderMe.APK_PATH);
        File file = new File(path, apk);
		file.delete();
    }

    public static boolean hasDirectory_Apk(String apk) {
        File path = new File(Environment.getExternalStorageDirectory() + File.separator + FolderMe.APK_PATH);
		File file = new File(path, apk);
        return file.exists();
    }

    private static void copyFile(InputStream in, OutputStream out) throws IOException
	{
        byte[] buffer = new byte[AccessibilityNodeInfoCompat.ACTION_NEXT_HTML_ELEMENT];
        while (true)
		{
            int read = in.read(buffer);
            if (read != -1)
			{
                out.write(buffer, 0, read);
            }
			else
			{
                return;
            }
        }
    }
}
