package es.iagt.android.view.slider.fragments

import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import java.util.ArrayList
import es.iagt.android.R
import es.iagt.android.utils.ScreenUtils
import es.iagt.android.view.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_slide.*
import kotlinx.android.synthetic.main.loading_view.*

class SliderFragment : BaseFragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_slide, container, false) as ViewGroup
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val position = arguments!!.getInt("position")
        tv_text!!.text = "Texto correspondiente para la imagen del slider número y posición " + position
        val images = ArrayList<String>()
        images.add("https://cdn2.techadvisor.co.uk/cmsdata/features/3614881/Android_thumb800.jpg")
        images.add("https://cdn1.cnet.com/img/Gat2Cyj-OXukqJaugh6G4FNHRPg=/1600x900/2017/04/24/07ed121e-8cc9-403b-b2db-78be57830ac8/mejores-apps-android-google-play-awards.png")
        images.add("http://programaenlinea.net/wp-content/uploads/2018/02/android-2.jpg")
        images.add("https://tr2.cbsistatic.com/hub/i/r/2017/01/31/7e355c52-c68f-4389-825f-392f2dd2ac19/resize/770x/d19d6c021f770122da649e2a77bd1404/androiddatahero.jpg")
        images.add("https://www.ayudacelular.com/wp-content/uploads/2018/01/Trucos-para-Android.jpg")

        loading_view!!.visibility = View.VISIBLE

        Picasso.with(context).load(images[position])
                //resize image to 40% of height of screen
                .resize(((ScreenUtils.getHeightScreen(activity!!) * 0.4).toInt()), ((ScreenUtils.getHeightScreen(activity!!) * 0.4).toInt()))
                .centerCrop()
                .into(iv_image!!, object : Callback {
                    override fun onSuccess() {
                        //set round image
                        val imageBitmap = (iv_image.drawable as BitmapDrawable).bitmap
                        val imageDrawable = RoundedBitmapDrawableFactory.create(resources, imageBitmap)
                        imageDrawable.isCircular = true
                        imageDrawable.cornerRadius = Math.max(imageBitmap.width, imageBitmap.height) / 2.0f
                        iv_image!!.setImageDrawable(imageDrawable)

                        loading_view!!.visibility = View.GONE
                    }

                    override fun onError() {
                        //                        iv_image.setImageResource(R.drawable.default_image);
                        loading_view!!.visibility = View.GONE
                    }
                })

    }

}
