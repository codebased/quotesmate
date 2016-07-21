package helpers;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.imcodebased.quotesmate.R;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class FileLoaderTask extends AsyncTask<String, Integer, String> {

    public final static String TAG = "FILELOADTASK";
    private final FileLoaderTaskCallback callback;
    private final Context context;

    public interface FileLoaderTaskCallback {
        void onProgress(int on, int total);

        void onSuccess(String result);

        void onException(Exception ex);

        void onCancel(String reason);

        void onPreExecute();
    }

    public FileLoaderTask(Context context, FileLoaderTaskCallback callback) {
        this.callback = callback;
        this.context = context;
    }

    @Override
    protected String doInBackground(String... params) {

        Log.i(TAG, String.format("doInBackground is called by %s", Thread.currentThread().getId()));
        // executed under background thread.
//        File file = new File(params.toString());
        InputStreamReader inputStream = null;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int count = 0;
        try {
            inputStream = new InputStreamReader(context.getResources().openRawResource(R.raw.quotes));
            int character = inputStream.read();
            while (character != -1) {
                byteArrayOutputStream.write(character);
                character = inputStream.read();
                count++;
                publishProgress(count, 100);
            }
        } catch (Exception e) {
            if (this.callback != null) {
                this.callback.onException(e);
            }
        } finally {

            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }

        return "";
    }


    @Override
    protected void onProgressUpdate(Integer... values) {
        // executed under main thread.
        super.onProgressUpdate(values);
//        Log.i(TAG, String.format("onProgressUpdate is called by Thread ID %s and value is %s", Thread.currentThread().getId(), values));

        if (callback != null) {
            callback.onProgress(values[0], values[1]);
        }
    }

    @Override
    protected void onPreExecute() {
        Log.i(TAG, String.format("onPreExecute is called by Thread ID %s", Thread.currentThread().getId()));
        if (callback != null) {
            callback.onPreExecute();
        }
    }

    @Override
    protected void onPostExecute(String result) {
        // executed under main thread.
        Log.i(TAG, String.format("onPostExecute is called by Thread ID %d", Thread.currentThread().getId()));
        if (callback != null) {
            callback.onSuccess(result);
        }
    }

    @Override
    protected void onCancelled() {
        onCancelled("No reason has been specified");
    }
}