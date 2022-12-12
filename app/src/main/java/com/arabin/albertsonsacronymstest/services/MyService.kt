package com.arabin.albertsonsacronymstest.services

import android.app.Service
import android.content.Intent
import android.os.Handler
import android.os.HandlerThread
import android.os.IBinder
import android.os.Looper
import android.os.Message
import android.widget.Toast

class MyService : Service() {

    private var serviceLooper: Looper? = null
    private var serviceHandler: ServiceHandler? = null

    private inner class ServiceHandler(
        looper: Looper,
        private val aFunction: (string: String) -> Unit
    ) : Handler(looper) {

        override fun handleMessage(msg: Message) {
            try {
                aFunction.invoke("Pass Message")
                Thread.sleep(5000)
            } catch (e: InterruptedException) {
                Thread.currentThread().interrupt()
            }
            stopSelf(msg.arg1)
        }
    }


    override fun onCreate() {
        HandlerThread("ServiceArguments", android.os.Process.THREAD_PRIORITY_BACKGROUND).apply {
            start()
            serviceLooper = looper
            serviceHandler = ServiceHandler(looper, ::onSetMessage)
        }
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Toast.makeText(this, "Services Started", Toast.LENGTH_SHORT).show()
        serviceHandler?.obtainMessage()?.also { msg ->
            msg.arg1 = startId
        }
        return START_STICKY
    }

    override fun onDestroy() {
        Toast.makeText(this, "service done", Toast.LENGTH_SHORT).show()
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    fun onSetMessage(string: String) {
        Toast.makeText(this, string, Toast.LENGTH_SHORT).show()
    }


}