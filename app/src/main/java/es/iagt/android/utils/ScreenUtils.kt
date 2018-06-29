package es.iagt.android.utils


import android.app.Activity
import android.graphics.Point
import android.util.DisplayMetrics
import android.util.TypedValue
import android.view.inputmethod.InputMethodManager
import es.iagt.android.view.base.BaseFragment

object ScreenUtils {

    fun getStatusBarHeight(activity: Activity): Int {
        var result = 0
        val resourceId = activity.resources.getIdentifier("status_bar_height", "dimen", "android")
        if (resourceId > 0) {
            result = activity.resources.getDimensionPixelSize(resourceId)
        }
        return result
    }

    fun getActionBarHeight(activity: Activity): Int {
        var actionBarHeight = 0
        val tv = TypedValue()
        if (activity.theme.resolveAttribute(android.R.attr.actionBarSize, tv,
                        true))
            actionBarHeight = TypedValue.complexToDimensionPixelSize(
                    tv.data, activity.resources.displayMetrics)
        return actionBarHeight
    }

    fun getHeightScreen(activity: Activity): Int {
        val display = activity.windowManager.defaultDisplay
        val size = Point()
        display.getSize(size)
        return size.y
    }

    fun getWidthScreen(activity: Activity): Int {
        val display = activity.windowManager.defaultDisplay
        val size = Point()
        display.getSize(size)
        return size.x
    }

    fun getCalculatedHeight(activity: Activity): Int {
        val displayMetrics = DisplayMetrics()
        activity.windowManager.defaultDisplay.getMetrics(displayMetrics)
        return displayMetrics.heightPixels - getStatusBarHeight(activity) - getActionBarHeight(activity)
    }

    fun getCalculatedWidth(activity: Activity): Int {
        val displayMetrics = DisplayMetrics()
        activity.windowManager.defaultDisplay.getMetrics(displayMetrics)
        return displayMetrics.widthPixels
    }

    fun hideKeyboard(fragment: BaseFragment) {
        val inputMethodManager = fragment.activity!!.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(fragment.view?.windowToken, 0)
    }
}
