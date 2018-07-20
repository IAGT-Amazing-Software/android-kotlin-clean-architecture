package es.iagt.android.view.base

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import es.iagt.android.app.AndroidApplication
import es.iagt.android.di.components.ApplicationComponent
import es.iagt.android.utils.Navigator
import es.iagt.android.utils.SharedPreferencesManager
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject


open class BaseFragment : Fragment() {

    @Inject
    lateinit var navigator: Navigator
    @Inject
    lateinit var sharedPreferencesManager: SharedPreferencesManager

    protected val applicationComponent = AndroidApplication.Companion.applicationComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        applicationComponent.inject(this)
    }

    fun closeFragment(fragment: BaseFragment) {
        val fragmentTransaction = fragmentManager!!.beginTransaction()
        fragmentTransaction.remove(fragment)
        fragmentTransaction.commit()
    }

    fun hideStatusBar() {
        activity!!.window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
    }

    fun showStatusBar() {
        activity!!.window.clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
    }

    fun hideKeyboard(activity: BaseActivity) {
        val imm = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(activity.rootView.windowToken, 0)
    }

    fun showKeyboard(activity: BaseActivity) {
        val imm = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0)
    }

}
