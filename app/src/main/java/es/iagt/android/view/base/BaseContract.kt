package es.iagt.android.view.base

import android.content.Context


interface BaseContract {
    fun getActivity(): Context?
    fun showLoading()
    fun hideLoading()
    fun showRetry()
    fun hideRetry()
    fun retry()
}
