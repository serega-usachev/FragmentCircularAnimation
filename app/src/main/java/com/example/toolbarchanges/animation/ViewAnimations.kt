package com.example.toolbarchanges.animation

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.view.View
import android.view.ViewAnimationUtils
import android.view.WindowInsets
import android.view.animation.DecelerateInterpolator
import kotlin.math.hypot

/**
 * Starts circular reveal animation
 * [fromLeft] if `true` then start animation from the bottom left of the [View] else start from the bottom right
 */
fun View.startCircularReveal(fromLeft: Boolean, block: () -> Unit) {
    addOnLayoutChangeListener(object : View.OnLayoutChangeListener {
        override fun onLayoutChange(
            v: View,
            left: Int,
            top: Int,
            right: Int,
            bottom: Int,
            oldLeft: Int,
            oldTop: Int,
            oldRight: Int,
            oldBottom: Int
        ) {
            v.removeOnLayoutChangeListener(this)
            val cx = if (fromLeft) v.left else v.right
            val cy = v.top
            val radius = hypot(right.toDouble(), bottom.toDouble()).toInt()

            ViewAnimationUtils.createCircularReveal(v, cx, cy, 0f, radius.toFloat()).apply {
                interpolator = DecelerateInterpolator(2f)
                duration = 1500
                addListener(object : AnimatorListenerAdapter() {
                    override fun onAnimationEnd(animation: Animator?) {
                        block()
                        super.onAnimationEnd(animation)
                    }
                })
                start()
            }
        }
    })
}

/**
 * Animate fragment exit using given parameters as animation end point. Runs the given block of code
 * after animation completion.
 *
 * @param exitX: Animation end point X coordinate.
 * @param exitY: Animation end point Y coordinate.
 * @param block: Block of code to be executed on animation completion.
 */
fun View.exitCircularReveal(exitX: Int, exitY: Int, block: () -> Unit) {
    val startRadius = hypot(this.width.toDouble(), this.height.toDouble())
    ViewAnimationUtils.createCircularReveal(this, exitX, exitY, startRadius.toFloat(), 0f).apply {
        duration = 1000
        interpolator = DecelerateInterpolator(1f)
        addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator?) {
                block()
                super.onAnimationEnd(animation)
            }
        })
        start()
    }
}

/**
 * @return the position of the current [View]'s center in the screen
 */
fun View.findLocationOfCenterOnTheScreen(): IntArray {
    val positions = intArrayOf(0, 0)
    getLocationInWindow(positions)
    // Get the center of the view
    positions[0] = positions[0] + width / 2
    positions[1] = positions[1] + height / 2
    return positions
}

data class InitialPadding(val left: Int, val top: Int,
                          val right: Int, val bottom: Int)

private fun recordInitialPaddingForView(view: View) = InitialPadding(
    view.paddingLeft, view.paddingTop, view.paddingRight, view.paddingBottom)

fun View.doOnApplyWindowInsets(f: (View, WindowInsets, InitialPadding) -> Unit) {
    // Create a snapshot of the view's padding state
    val initialPadding = recordInitialPaddingForView(this)
    // Set an actual OnApplyWindowInsetsListener which proxies to the given
    // lambda, also passing in the original padding state
    setOnApplyWindowInsetsListener { v, insets ->
        f(v, insets, initialPadding)
        // Always return the insets, so that children can also use them
        insets
    }
    // request some insets
    requestApplyInsetsWhenAttached()
}

fun View.requestApplyInsetsWhenAttached() {
    if (isAttachedToWindow) {
        // We're already attached, just request as normal
        requestApplyInsets()
    } else {
        // We're not attached to the hierarchy, add a listener to
        // request when we are
        addOnAttachStateChangeListener(object : View.OnAttachStateChangeListener {
            override fun onViewAttachedToWindow(v: View) {
                v.removeOnAttachStateChangeListener(this)
                v.requestApplyInsets()
            }

            override fun onViewDetachedFromWindow(v: View) = Unit
        })
    }
}

