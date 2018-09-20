package com.fantasy1022.constraintvector

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.constraint.ConstraintSet
import android.support.v4.view.animation.LinearOutSlowInInterpolator
import android.transition.ChangeBounds
import android.transition.TransitionManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    private val STATE_SET_PLAY = intArrayOf(R.attr.state_play, -R.attr.state_pause)
    private val STATE_SET_PAUSE = intArrayOf(-R.attr.state_play, R.attr.state_pause)
    var isPlay: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        playPauseButton.setOnClickListener {
            if (!isPlay) {
                isPlay = true
                updateConstraints(R.layout.activity_main_playing)
                playPauseButton.setImageState(STATE_SET_PAUSE, true)
            } else {
                isPlay = false
                updateConstraints(R.layout.activity_main)
                playPauseButton.setImageState(STATE_SET_PLAY, true)
            }
        }
    }

    private fun updateConstraints(@LayoutRes id: Int) {
        val newConstraintSet = ConstraintSet()
        newConstraintSet.clone(this, id)
        newConstraintSet.applyTo(rootLayout)
        val transition = ChangeBounds()
        transition.duration = resources.getInteger(R.integer.long_duration).toLong()
        transition.interpolator = LinearOutSlowInInterpolator()
        TransitionManager.beginDelayedTransition(rootLayout, transition)
    }
}
