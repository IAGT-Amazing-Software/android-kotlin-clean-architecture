package es.iagt.android.view.login

import android.os.Bundle
import es.iagt.android.R
import es.iagt.android.view.base.BaseActivity


open class LoginActivity: BaseActivity(), LoginView{

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        applicationComponent.inject(this)
    }
}