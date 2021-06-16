package com.fdhasna21.multimedia

import android.annotation.SuppressLint
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.widget.SeekBar
import kotlinx.android.synthetic.main.activity_5.*

class Activity5 : AppCompatActivity() {
    companion object{
        private lateinit var mediaPlayer : MediaPlayer
        private var songDuration : Int = 0
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_5)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.title = "Mediaplayer"

        mediaPlayer = MediaPlayer.create(this, R.raw.banana_chacha)
        mediaPlayer.isLooping = true
        mediaPlayer.setVolume(0.5f, 0.5f)
        songDuration = mediaPlayer.duration

        //Current Position
        activity5_current_position.max = songDuration
        activity5_current_position.setOnSeekBarChangeListener(
            object : SeekBar.OnSeekBarChangeListener{
                override fun onProgressChanged(
                    seekBar: SeekBar?,
                    progress: Int,
                    fromUser: Boolean
                ) {
                    if(fromUser){
                        mediaPlayer.seekTo(progress)
                    }
                }

                override fun onStartTrackingTouch(seekBar: SeekBar?) {}

                override fun onStopTrackingTouch(seekBar: SeekBar?) {}
            }
        )

        //Play Button
        activity5_play_button.setOnClickListener {
            playButtonClick()
        }

        Thread(Runnable {
            while (mediaPlayer != null){
                try {
                    val msg = Message()
                    msg.what = mediaPlayer.currentPosition
                    handler.sendMessage(msg)
                    Thread.sleep(1000)
                }
                catch (e:InterruptedException){}
            }
        }).start()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private var handler = @SuppressLint("HandlerLeak")
    object :Handler(){
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)

            val currentPosition = msg.what
            activity5_current_position.progress = currentPosition

            val songDuration = songTimeLabel(songDuration)
            activity5_song_duration_txt.text = songDuration

            val songCurrentPosition = songTimeLabel(currentPosition)
            activity5_song_position_txt.text = songCurrentPosition
        }
    }

    fun songTimeLabel(value:Int) :String{
        var output = ""
        val minutes = value / 1000 / 60
        val seconds = value / 1000 % 60

        output = "$minutes:"
        output+= if(seconds<10){"0$seconds"} else{seconds}
        return output
    }

    fun playButtonClick(){
        if(mediaPlayer.isPlaying){
            mediaPlayer.pause()
            activity5_play_button.setImageResource(R.drawable.ic_play)
        }
        else{
            mediaPlayer.start()
            activity5_play_button.setImageResource(R.drawable.ic_pause)
        }
    }
}