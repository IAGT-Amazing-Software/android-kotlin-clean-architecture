package es.iagt.data.util

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import javax.inject.Inject


open class ConnectionUtils
@Inject
constructor(private val application: Application){
    @SuppressLint("MissingPermission")
    fun isConnected(): Boolean {
        val connectivityManager = application.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val netInfo = connectivityManager.activeNetworkInfo
        return netInfo != null && netInfo.isConnected
    }
}
