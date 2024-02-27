package com.example.agora_appoftheday

//import android.R
import android.os.Bundle
import android.provider.SyncStateContract.Constants
import android.util.Log
import android.view.ViewGroup
import android.widget.Button
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import io.agora.agorauikit_android.*
import io.agora.rtc2.IRtcEngineEventHandler.ClientRole.CLIENT_ROLE_BROADCASTER


class MainActivity : AppCompatActivity() {


    // Object of AgoraVideoVIewer class
    private var agView: AgoraVideoViewer? = null

    // Fill the App ID of your project generated on Agora Console.
    private val appId = "61e0ea9ad397418db35c11da6d458ee9"

    // Fill the channel name.
    private val channelName = "harrypotter"

    // Fill the temp token generated on Agora Console.
    private val token =
        "007eJxTYDDfUy4Z9TnDrbbE8rl2al4866HEO0LX9nGdaVbJ413pf1yBwcww1SA10TIxxdjS3MTQIiXJ2DTZ0DAl0SzFxNQiNdWyadHh1IZARgbZp8FMjAwQCOJzM2QkFhVVFuSXlKQWMTAAANTSIZ4="


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initializeAndJoinChannel()
    }

    fun joinChannel() {
        agView?.join(channelName, token, CLIENT_ROLE_BROADCASTER, 0)
    }

    private fun initializeAndJoinChannel() {
        // Create AgoraVideoViewer instance
        try {
            agView = AgoraVideoViewer(
                this,
                AgoraConnectionData(appId, token),
                AgoraVideoViewer.Style.FLOATING,
                AgoraSettings(),
                null
            )
        } catch (e: Exception) {
            Log.e(
                "AgoraVideoViewer",
                "Could not initialize AgoraVideoViewer. Check that your app Id is valid."
            )
            Log.e("Exception", e.toString())
            return
        }

        // Add the AgoraVideoViewer to the Activity layout
        addContentView(
            agView, FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.MATCH_PARENT,
                FrameLayout.LayoutParams.MATCH_PARENT
            )
        )

        // Check permission and join a channel
        //if (DevicePermissionsKt.requestPermissions(AgoraVideoViewer.Companion, this)) {
        joinChannel()
        //   } else {
        val joinButton = Button(this)
        joinButton.text = "Allow camera and microphone access, then click here"
        joinButton.setOnClickListener {
            //            if (DevicePermissionsKt.requestPermissions(
            //                  AgoraVideoViewer.Companion,
            //                 applicationContext
            //            )
            //      ) {
            (joinButton.parent as ViewGroup).removeView(joinButton)
            joinChannel()
            //        }
        }
        addContentView(
            joinButton,
            FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, 200)
        )
    }
}


