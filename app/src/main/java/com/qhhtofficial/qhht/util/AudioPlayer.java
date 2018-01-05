package com.qhhtofficial.qhht.util;

import android.media.MediaPlayer;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.qhhtofficial.qhht.R;

import java.io.IOException;

/**
 * Created by Simon on 2018/1/3.
 */

public class AudioPlayer {
    private static final String TAG = AudioPlayer.class.getSimpleName();

    private static AudioPlayer mAudioPlayer;
    private MediaPlayer mMediaPlayer;

    public static AudioPlayer getInstance(){
        if(mAudioPlayer ==null){
            mAudioPlayer = new AudioPlayer();
        }
        return mAudioPlayer;
    }

    public void play(String filePath) {
        if(TextUtils.isEmpty(filePath))return;

        if(mMediaPlayer==null){
            mMediaPlayer = new MediaPlayer();
        }

        try {
            mMediaPlayer.setDataSource(filePath);
            mMediaPlayer.prepare();
            mMediaPlayer.start();
        } catch (IOException e) {
            Log.e(TAG, "IOException: "+e.getMessage() );
            Toast.makeText(Utility.getAppContext(), Utility.getAppContext().getString(R.string.error_media), Toast.LENGTH_SHORT).show();
        } catch (IllegalStateException e){
            Log.e(TAG, "IllegalStateException: "+e.getMessage() );
            Toast.makeText(Utility.getAppContext(), Utility.getAppContext().getString(R.string.error_player), Toast.LENGTH_SHORT).show();
        }
    }

    public void forcePlay(String filePath){
        if(mMediaPlayer!=null){
            mMediaPlayer.reset();
        }
        play(filePath);
    }

    public void release(){
        if(mMediaPlayer!=null){
            mMediaPlayer.release();
            mMediaPlayer = null;
        }
    }
}
