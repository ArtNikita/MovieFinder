package ru.nikitaartamonov.moviefinder.util

import android.app.IntentService
import android.content.Context
import android.content.Intent
import android.util.Log
import java.text.SimpleDateFormat
import java.util.*

object MyAnalytics {
    fun logEvent(context: Context, event: String) {
        val intent = Intent(context, MyAnalyticsService::class.java).apply {
            putExtra(EVENT_KEY, event)
        }
        context.startService(intent)
    }
}

class MyAnalyticsService : IntentService(SERVICE_NAME) {
    override fun onHandleIntent(intent: Intent?) {
        intent?.apply {
            getStringExtra(EVENT_KEY)?.let { event ->
                val msg = "${
                    SimpleDateFormat("dd/M/yyyy hh:mm:ss", Locale.getDefault()).format(Date())
                }: $event"
                Log.i(MY_ANALYTICS_TAG, msg)
            }
        }
    }
}

const val MY_ANALYTICS_TAG = "MY_ANALYTICS_TAG"
private const val SERVICE_NAME = "MyAnalyticsService"
private const val EVENT_KEY = "EVENT_KEY"