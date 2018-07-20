package es.iagt.android.view.slider.activities

import android.os.Bundle
import android.support.v4.view.ViewPager
import android.view.View
import es.iagt.android.R
import es.iagt.android.view.base.BaseActivity
import es.iagt.android.view.slider.adapters.SliderAdapter
import kotlinx.android.synthetic.main.activity_slider.*

class SliderActivity : BaseActivity() {

    internal var currentPosition: Int? = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



        if (sharedPreferencesManager.isFirstInstall()!!) {
            navigator.goToSplash(this)
        } else {
            setContentView(R.layout.activity_slider)
            vp_slider!!.adapter = SliderAdapter(supportFragmentManager)
            ci_slider!!.setViewPager(vp_slider)

            vp_slider!!.addOnPageChangeListener(object : ViewPager.SimpleOnPageChangeListener() {

                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    currentPosition = position
                    if (position == vp_slider!!.adapter!!.count - 1) {
                        b_slider_jump!!.visibility = View.GONE
                        b_slider_next!!.text = getString(R.string.begin)
                    } else {
                        b_slider_jump!!.visibility = View.VISIBLE
                        b_slider_next!!.text = getString(R.string.next)
                    }
                }
            })

            b_slider_next.setOnClickListener {
                if (currentPosition!! < vp_slider!!.adapter!!.count - 1) {
                    vp_slider!!.currentItem = vp_slider!!.currentItem + 1
                } else {
                    navigator.goToSplash(this)
                    sharedPreferencesManager.setFirstInstall()
                }
            }

            b_slider_jump.setOnClickListener {
                navigator.goToSplash(this)
                sharedPreferencesManager.setFirstInstall()
            }
        }

    }


}
