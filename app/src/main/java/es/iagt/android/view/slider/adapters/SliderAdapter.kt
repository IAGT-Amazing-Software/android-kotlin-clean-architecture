package es.iagt.android.view.slider.adapters


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import es.iagt.android.view.slider.fragments.SliderFragment

class SliderAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {

    private val NUM_PAG = 5

    override fun getItem(position: Int): Fragment {
        val bundle = Bundle()
        bundle.putInt("position", position)
        val fragment = SliderFragment()
        fragment.arguments = bundle
        return fragment
    }

    override fun getCount(): Int {
        return NUM_PAG
    }
}
