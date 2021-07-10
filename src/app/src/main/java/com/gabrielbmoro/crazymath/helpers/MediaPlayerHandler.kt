package com.gabrielbmoro.crazymath.helpers

import android.content.Context
import android.media.MediaPlayer
import androidx.annotation.RawRes

class MediaPlayerHandler() {

    fun createMediaPlayer(context: Context, @RawRes rawRes: Int): MediaPlayer {
        return MediaPlayer.create(context, rawRes)
    }

    fun startThePlayer(mediaPlayer: MediaPlayer?) {
        if (mediaPlayer?.isPlaying == false) {
            mediaPlayer.start()
        }
    }

    fun stopAndReleaseThePlayer(mediaPlayer: MediaPlayer?): MediaPlayer? {
        mediaPlayer?.stop()
        mediaPlayer?.release()
        return null
    }
}