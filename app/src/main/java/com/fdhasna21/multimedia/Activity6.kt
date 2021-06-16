package com.fdhasna21.multimedia

import android.os.Bundle
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayerView

class Activity6 : YouTubeBaseActivity(), YouTubePlayer.OnInitializedListener{
    val YOUTUBE_VIDEO_ID = "lHTqUBIr5Gk"
    private lateinit var toolbar: MaterialToolbar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val layout = layoutInflater.inflate(R.layout.activity_6, null) as LinearLayout
        setContentView(layout)

        val youtubeView = YouTubePlayerView(this)
        youtubeView.layoutParams = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
        layout.addView(youtubeView)
        youtubeView.initialize(getString(R.string.youtube_api_key), this)

        toolbar = layout.findViewById(R.id.activity6_topbar_menu)
        toolbar.title = "Youtube Player"
        toolbar.setNavigationIcon(R.drawable.ic_arrow)
        toolbar.setNavigationOnClickListener {
            onBackPressed()
        }
    }

    override fun onInitializationSuccess(
        provider: YouTubePlayer.Provider?,
        youtubePlayer: YouTubePlayer?,
        wasRestored: Boolean
    ) {
        youtubePlayer?.setPlayerStateChangeListener(object : YouTubePlayer.PlayerStateChangeListener{
            override fun onLoading() {
//                Toast.makeText(this@Activity6, "onLoading", Toast.LENGTH_SHORT).show()
            }

            override fun onLoaded(p0: String?) {
//                Toast.makeText(this@Activity6, "onLoaded $p0", Toast.LENGTH_SHORT).show()
            }

            override fun onAdStarted() {
//                Toast.makeText(this@Activity6, "onAdStarted", Toast.LENGTH_SHORT).show()
            }

            override fun onVideoStarted() {
//                Toast.makeText(this@Activity6, "onVideoStarted", Toast.LENGTH_SHORT).show()
            }

            override fun onVideoEnded() {
//                Toast.makeText(this@Activity6, "onVideoEnded", Toast.LENGTH_SHORT).show()
            }

            override fun onError(p0: YouTubePlayer.ErrorReason?) {
//                Toast.makeText(this@Activity6, "onError $p0", Toast.LENGTH_SHORT).show()
            }
        })

        youtubePlayer?.setPlaybackEventListener(object : YouTubePlayer.PlaybackEventListener{
            override fun onPlaying() {
//                Toast.makeText(this@Activity6, "onPlaying", Toast.LENGTH_SHORT).show()
            }

            override fun onPaused() {
//                Toast.makeText(this@Activity6, "onPaused", Toast.LENGTH_SHORT).show()
            }

            override fun onStopped() {
//                Toast.makeText(this@Activity6, "onStopped", Toast.LENGTH_SHORT).show()
            }

            override fun onBuffering(p0: Boolean) {
//                Toast.makeText(this@Activity6, "onBuffering $p0", Toast.LENGTH_SHORT).show()
            }

            override fun onSeekTo(p0: Int) {
//                Toast.makeText(this@Activity6, "onSeekTo $p0", Toast.LENGTH_SHORT).show()
            }
        })

        if(!wasRestored){
            youtubePlayer?.cueVideo(YOUTUBE_VIDEO_ID)
        }
    }

    override fun onInitializationFailure(
        provider : YouTubePlayer.Provider?,
        youtubeInitializationResult: YouTubeInitializationResult?
    ) {
        when(youtubeInitializationResult?.isUserRecoverableError){
            true ->{
                youtubeInitializationResult.getErrorDialog(this, 0).show()
            }
            false ->{
                Toast.makeText(this, "There was an error initializing the YoutubePlayer $youtubeInitializationResult", Toast.LENGTH_LONG).show()
            }
        }
    }
}