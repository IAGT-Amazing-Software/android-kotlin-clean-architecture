package es.iagt.android.view.splash

import android.os.Bundle
import android.os.Handler
import es.iagt.android.R
import es.iagt.android.view.base.BaseActivity

class SplashActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        Handler().postDelayed({ navigator.goToMain(this) }, 2000)
    }

}
