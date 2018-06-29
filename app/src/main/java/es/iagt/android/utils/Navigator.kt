package es.iagt.android.utils

import android.content.Intent
import es.iagt.android.R
import es.iagt.android.constants.AppConstants
import es.iagt.android.view.base.BaseActivity
import es.iagt.android.view.base.BaseFragment
import es.iagt.android.view.main.activities.MainActivity
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class Navigator
@Inject
constructor() {
    companion object {
        const val ADD = "ADD"
        const val REPLACE = "REPLACE"
    }

    lateinit var context: MainActivity

    fun setScreenOnMain(tag: String, fragment: BaseFragment, type: String){

        if (context.supportFragmentManager.findFragmentById(R.id.main_content) != null){
            val currentTag = context.supportFragmentManager.findFragmentById(R.id.main_content).tag
            if (currentTag != tag) {
                if(type == ADD){
                    context.addFragment(R.id.main_content, fragment, tag)
                }
                if(type == REPLACE){
                    context.replaceFragment(R.id.main_content, fragment, tag)
                }
            }
        }else{
            if(type == ADD){
                context.addFragment(R.id.main_content, fragment, tag)
            }
            if(type == REPLACE){
                context.replaceFragment(R.id.main_content, fragment, tag)
            }
        }
    }


    fun goToMain(activity: BaseActivity) {
        activity.startActivity(Intent(activity, MainActivity::class.java))
        activity.finish()
    }


}
