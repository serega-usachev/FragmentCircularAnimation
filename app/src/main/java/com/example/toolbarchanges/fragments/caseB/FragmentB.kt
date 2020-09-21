package com.example.toolbarchanges.fragments.caseB

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.toolbarchanges.ExitWithAnimation
import com.example.toolbarchanges.MainActivity
import com.example.toolbarchanges.R
import com.example.toolbarchanges.RootNavigator
import com.example.toolbarchanges.animation.startCircularReveal
import timber.log.Timber

class FragmentB : Fragment(R.layout.fragment_b_layout), ExitWithAnimation {

    override var posX: Int? = null
    override var posY: Int? = null
    override fun isToBeExitedWithAnimation() = true

    override fun onCreate(savedInstanceState: Bundle?) {
        Timber.d("onCreate")
        super.onCreate(savedInstanceState)
    }

    private lateinit var rootNavigator: RootNavigator
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        Timber.d("onActivityCreated")
        super.onActivityCreated(savedInstanceState)
        rootNavigator = (requireActivity() as MainActivity).rootNavigator
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Timber.d("onViewCreated")
        super.onViewCreated(view, savedInstanceState)
        view.startCircularReveal(true) {
            rootNavigator.removePreviousIfExist()
        }
    }

    override fun onDestroyView() {
        Timber.d("onDestroyView")
        super.onDestroyView()
    }

    override fun onDestroy() {
        Timber.d("onDestroy")
        super.onDestroy()
    }

    companion object {
        fun newInstance(exitPosition: IntArray) = FragmentB().apply {
            posX = exitPosition[0]
            posY = exitPosition[1]
        }
    }

    override fun toString(): String = this.javaClass.simpleName
}