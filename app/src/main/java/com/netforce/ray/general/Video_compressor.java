package com.netforce.ray.general;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Environment;
import android.os.PowerManager;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.netcompss.ffmpeg4android.GeneralUtils;
import com.netcompss.ffmpeg4android.Prefs;
import com.netcompss.loader.LoadJNI;
import com.netforce.ray.sell.SellActivity;

/**
 * Created by abcd on 10/21/2016.
 */
public class Video_compressor extends AsyncTask<String, Integer, Integer>{

    String workFolder = null;
    String demoVideoFolder = null;
    String demoVideoPath = null;
    String vkLogPath = null;
    String source_video_folder;


        ProgressDialog progressDialog;
        Activity _act;

        public Video_compressor (Activity act) {
            _act = act;
            workFolder =_act. getApplicationContext().getFilesDir().getAbsolutePath() + "/seek&sell/";
            source_video_folder = Environment.getExternalStorageDirectory().getAbsolutePath() + "/seek&sell/";
        }



        @Override
        protected void onPreExecute() {
            progressDialog = new ProgressDialog(_act);
            progressDialog.setMessage("FFmpeg4Android Transcoding in progress...");
            progressDialog.show();

        }

        protected Integer doInBackground(String... paths) {
            Log.i(Prefs.TAG, "doInBackground started...");

            // delete previous log
            GeneralUtils.deleteFileUtil(workFolder + "/vk.log");

            PowerManager powerManager = (PowerManager)_act.getSystemService(Activity.POWER_SERVICE);
            PowerManager.WakeLock wakeLock = powerManager.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "VK_LOCK");
            Log.d(Prefs.TAG, "Acquire wake lock");
            wakeLock.acquire();

//            EditText commandText = (EditText)findViewById(R.id.CommandText);
//            String commandStr = commandText.getText().toString();
            String timestamp= String.valueOf(System.currentTimeMillis());

            ///////////// Set Command using code (overriding the UI EditText) /////
            //String commandStr = "ffmpeg -y -i /sdcard/videokit/in.mp4 -strict experimental -s 320x240 -r 30 -aspect 3:4 -ab 48000 -ac 2 -ar 22050 -vcodec mpeg4 -b 2097152 /sdcard/videokit/out.mp4";
            String[] complexCommand = {"ffmpeg", "-y" ,"-i", SellActivity.videopath,"-strict","experimental","-s", "160x120","-r","25", "-vcodec", "mpeg4", "-b", "150k", "-ab","48000", "-ac", "2", "-ar", "22050", "/sdcard/seek&sell/"+timestamp+"seek&sell.mp4"};
            ///////////////////////////////////////////////////////////////////////


            LoadJNI vk = new LoadJNI();
            try {

                vk.run(complexCommand, workFolder, _act);
               // vk.run(GeneralUtils.utilConvertToComplex(commandStr), workFolder, _act);

                // copying vk.log (internal native log) to the videokit folder
               // GeneralUtils.copyFileToFolder(vkLogPath, source_video_folder);

            } catch (Throwable e) {
                Log.e(Prefs.TAG, "vk run exeption.", e);
            }
            finally {
                if (wakeLock.isHeld())
                    wakeLock.release();
                else{
                    Log.i(Prefs.TAG, "Wake lock is already released, doing nothing");
                }
            }
            Log.i(Prefs.TAG, "doInBackground finished");
            return Integer.valueOf(0);
        }

        protected void onProgressUpdate(Integer... progress) {
        }

        @Override
        protected void onCancelled() {
            Log.i(Prefs.TAG, "onCancelled");
            //progressDialog.dismiss();
            super.onCancelled();
        }


        @Override
        protected void onPostExecute(Integer result) {
            Log.i(Prefs.TAG, "onPostExecute");
            progressDialog.dismiss();
            super.onPostExecute(result);
            final String status = GeneralUtils.getReturnCodeFromLog(workFolder + "/vk.log");

            _act.runOnUiThread(new Runnable() {
                public void run() {
                    Toast.makeText(_act, status, Toast.LENGTH_LONG).show();
                    if (status.equals("Transcoding Status: Failed")) {
                        Toast.makeText(_act, "Check: " + workFolder + "/vk.log" + " for more information.", Toast.LENGTH_LONG).show();
                    }

                    // copying vk.log (internal native log) to the sdcard folder
                   // GeneralUtils.copyFileToFolder(vkLogPath, source_video_folder);
                }
            });

        }




    }





