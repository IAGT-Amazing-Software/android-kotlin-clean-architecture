package es.iagt.android.view.main.activities

import android.content.Context
import android.os.Bundle
import android.view.View
import es.iagt.android.R
import es.iagt.android.view.base.BaseActivity
import es.iagt.android.view.main.contracts.MainContract
import es.iagt.android.view.main.listeners.MainListener
import es.iagt.android.view.main.presenters.MainPresenter
import kotlinx.android.synthetic.main.loading_view.*
import kotlinx.android.synthetic.main.retry_view.*
import javax.inject.Inject


class MainActivity : BaseActivity(), MainContract, MainListener {

    @Inject
    lateinit var presenter: MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        applicationComponent.inject(this)
        presenter.view = this
        navigator.context = this
    }


    override fun onBackPressed() {
        super.onBackPressed()
    }

    override fun showLoading() {
        loading_view.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        loading_view.visibility = View.GONE
    }

    override fun showRetry() {
        retry_view.visibility = View.VISIBLE
    }

    override fun hideRetry() {
        retry_view.visibility = View.GONE
    }

    override fun retry() {}

    override fun getActivity(): Context? {
        return this
    }


}
