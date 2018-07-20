package es.iagt.android.view.base

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.WindowManager
import butterknife.ButterKnife
import es.iagt.android.R
import es.iagt.android.app.AndroidApplication
import es.iagt.android.di.components.ApplicationComponent
import es.iagt.android.utils.Navigator
import es.iagt.android.utils.SharedPreferencesManager
import javax.inject.Inject


abstract class BaseActivity : AppCompatActivity() {

    @Inject
    lateinit var navigator: Navigator
    @Inject
    lateinit var sharedPreferencesManager: SharedPreferencesManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        applicationComponent.inject(this)
    }

    protected val applicationComponent = AndroidApplication.Companion.applicationComponent

    open fun replaceFragment(containerViewId: Int, fragment: Fragment) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(containerViewId, fragment)
        fragmentTransaction.commit()
    }
    fun replaceFragment(containerViewId: Int, fragment: Fragment, tag: String) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(containerViewId, fragment, tag)
        fragmentTransaction.commit()
    }
    fun addFragment(containerViewId: Int, fragment: Fragment, tag: String) {
        val fragmentTransaction = this.supportFragmentManager.beginTransaction()
        fragmentTransaction.add(containerViewId, fragment, tag)
        fragmentTransaction.commit()
    }
    fun addFragment(containerViewId: Int, fragment: Fragment) {
        val fragmentTransaction = this.supportFragmentManager.beginTransaction()
        fragmentTransaction.add(containerViewId, fragment)
        fragmentTransaction.commit()
    }

    fun transparentStatusBar(){
        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
    }

    fun getCurrentFragmentTag(): String? {
        return getCurrentFragment().tag
    }

    fun getCurrentFragmentTag(res: Int): String? {
        return getCurrentFragment(res).tag
    }

    fun getCurrentFragment(): BaseFragment {
        var fragment = BaseFragment()
        if (supportFragmentManager.findFragmentById(R.id.main_content) != null){
            fragment = supportFragmentManager.findFragmentById(R.id.main_content) as BaseFragment
        }
        return fragment
    }

    fun getCurrentFragment(res: Int): BaseFragment{
        var fragment = BaseFragment()
        if (supportFragmentManager.findFragmentById(res) != null){
            fragment = supportFragmentManager.findFragmentById(res) as BaseFragment
        }
        return fragment
    }

    fun closeCurrentFragment(){
        supportFragmentManager.beginTransaction().remove(getCurrentFragment()).commit()
    }

    fun closeCurrentFragment(res: Int){
        supportFragmentManager.beginTransaction().remove(getCurrentFragment(res)).commit()
    }

    fun closeFragment(fragment: BaseFragment){
        supportFragmentManager.beginTransaction().remove(fragment).commit()
    }

    fun hideStatusBar() {
        this.window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
    }

    fun showStatusBar() {
        this.window.clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
    }



}
