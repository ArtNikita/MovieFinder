package ru.nikitaartamonov.moviefinder.ui.main

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.widget.Toast
import ru.nikitaartamonov.moviefinder.R


class NetworkBroadcastReceiver : BroadcastReceiver() {
    private var wasOnline = true

    override fun onReceive(context: Context, intent: Intent) {
        val isOnline = isOnline(context)
        if (isOnline != wasOnline) {
            wasOnline = isOnline
            showMessage(
                    context,
                    if (isOnline) context.getString(R.string.network_connected_broadcast_receiver_message)
                    else context.getString(R.string.network_disconnected_broadcast_receiver_message)
            )
        }
    }

    private fun isOnline(context: Context): Boolean {
        val connectivityManager =
                context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val netInfo = connectivityManager.activeNetworkInfo
        return netInfo != null && netInfo.isConnected
    }

    private fun showMessage(context: Context, msg: String) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
    }
}