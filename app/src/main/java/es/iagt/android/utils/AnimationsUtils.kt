package es.iagt.android.utils

import android.view.View
import android.view.ViewAnimationUtils
import android.view.animation.DecelerateInterpolator


object AnimationsUtils{

    private const val TRANSLATION_FLOAT = 2000f
    private const val DURATION = 300L
    private const val INITIAL_TRANSLATION = 0f
    private const val MIN_ALPHA = 0f
    private const val MAX_ALPHA = 1f

    fun fadeOutIn(view: View, duration: Long, runnable: Runnable){
        this.fadeOut(view, duration, runnable)
    }

    fun fadeIn(view: View, duration: Long){
        view.animate()
                .alpha(MAX_ALPHA)
                .setDuration(duration)
                .setInterpolator(DecelerateInterpolator())
                .start()
    }

    fun fadeOut(view: View, duration: Long, runnable: Runnable){
        view.animate()
                .alpha(MIN_ALPHA)
                .setDuration(duration)
                .setInterpolator(DecelerateInterpolator())
                .withEndAction(runnable)
                .start()
    }

    fun fadeOut(view: View, duration: Long){
        view.animate()
                .alpha(MIN_ALPHA)
                .setDuration(duration)
                .setInterpolator(DecelerateInterpolator())
                .start()
    }

    fun circleReveal(view: View){
        view.post(Runnable {
            val cx = (view.width / 2)
            val cy = (view.height / 2)
            val radius = Math.hypot(cx.toDouble(), cy.toDouble()).toFloat()
            val anim = ViewAnimationUtils.createCircularReveal(view, cx, cy, 0F, radius)
            anim.start()
        })
    }

    fun viewFromDown(view: View) {
        view.visibility = View.INVISIBLE
        view.translationY = TRANSLATION_FLOAT
        view.visibility = View.VISIBLE
        view.animate().translationY(INITIAL_TRANSLATION).setDuration(DURATION).setInterpolator(DecelerateInterpolator()).start()
    }

    fun viewFromUp(view: View) {
        view.visibility = View.INVISIBLE
        view.translationY = -TRANSLATION_FLOAT
        view.visibility = View.VISIBLE
        view.animate().translationY(INITIAL_TRANSLATION).setDuration(DURATION).setInterpolator(DecelerateInterpolator()).start()
    }

    fun viewFromRight(view: View) {
        view.visibility = View.INVISIBLE
        view.translationX = TRANSLATION_FLOAT
        view.visibility = View.VISIBLE
        view.animate().translationX(INITIAL_TRANSLATION).setDuration(DURATION).setInterpolator(DecelerateInterpolator()).start()
    }
}
