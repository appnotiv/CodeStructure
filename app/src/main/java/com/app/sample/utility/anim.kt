package com.app.sample.utility

import android.view.View
import android.view.animation.Animation
import android.view.animation.TranslateAnimation

// slide the view from below itself to the current position
fun slideUp(view: View, duration: Long) {
    val animate = TranslateAnimation(
        0f, // fromXDelta
        0f, // toXDelta
        view.height.toFloat(), // fromYDelta
        0f
    )                // toYDelta
    animate.duration = duration
    animate.fillAfter = true
    view.startAnimation(animate)
    animate.setAnimationListener(object : Animation.AnimationListener {
        override fun onAnimationRepeat(animation: Animation?) {

        }

        override fun onAnimationEnd(animation: Animation?) {

        }

        override fun onAnimationStart(animation: Animation?) {
            view.visibility = View.VISIBLE
        }
    })
}

// slide the view from its current position to below itself
fun slideDown(view: View, duration: Long, animationEnd: AnimationEnd) {
    val animate = TranslateAnimation(
        0f, // fromXDelta
        0f, // toXDelta
        0f, // fromYDelta
        view.height.toFloat()
    ) // toYDelta
    animate.duration = duration
    animate.fillAfter = true
    view.startAnimation(animate)
    animate.setAnimationListener(object : Animation.AnimationListener {
        override fun onAnimationRepeat(animation: Animation?) {

        }

        override fun onAnimationEnd(animation: Animation?) {
            view.visibility = View.GONE
            view.clearAnimation()
            animationEnd.onAnimationEnd()
        }

        override fun onAnimationStart(animation: Animation?) {

        }
    })
}

interface AnimationEnd {
    fun onAnimationEnd()
}