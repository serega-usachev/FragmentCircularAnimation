package com.example.toolbarchanges.fragments.caseA

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.ViewCompat
import androidx.fragment.app.Fragment
import com.example.toolbarchanges.*
import com.example.toolbarchanges.animation.doOnApplyWindowInsets
import com.example.toolbarchanges.animation.startCircularReveal
import kotlinx.android.synthetic.main.fragment_blue_layout.view.*
import kotlinx.android.synthetic.main.fragment_blue_layout.view.status_view
import kotlinx.android.synthetic.main.fragment_red_layout.*
import kotlinx.android.synthetic.main.fragment_red_layout.view.*
import timber.log.Timber

class FragmentRed : Fragment(R.layout.fragment_red_layout), ExitWithAnimation {

    override var posX: Int? = null
    override var posY: Int? = null
    override fun isToBeExitedWithAnimation() = true

    private lateinit var rootNavigator: RootNavigator

    override fun onAttach(context: Context) {
        Timber.tag(ALL_FRAGMENTS_TAG).d("${this.javaClass.simpleName} onAttach")
        super.onAttach(context)
        rootNavigator = (requireActivity() as MainActivity).rootNavigator
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        Timber.tag(ALL_FRAGMENTS_TAG).d("${this.javaClass.simpleName} onCreate")
        super.onCreate(savedInstanceState)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        Timber.tag(ALL_FRAGMENTS_TAG).d("${this.javaClass.simpleName} onActivityCreated")
        super.onActivityCreated(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Timber.tag(ALL_FRAGMENTS_TAG).d("${this.javaClass.simpleName} onViewCreated")
        super.onViewCreated(view, savedInstanceState)

        view.doOnApplyWindowInsets { v, windowInsets, initialPadding ->
            val params = view.status_view.layoutParams as LinearLayout.LayoutParams
            params.height = windowInsets.systemWindowInsetTop
            view.status_view.layoutParams = params
        }

        view.startCircularReveal(true) {
            rootNavigator.removePreviousIfExist()
        }

        toolbar.setNavigationOnClickListener {
            (requireActivity() as MainActivity).openDrawer()
        }
    }

    override fun onDestroyView() {
        Timber.tag(ALL_FRAGMENTS_TAG).d("${this.javaClass.simpleName} onDestroyView")
        super.onDestroyView()
    }

    override fun onDestroy() {
        Timber.tag(ALL_FRAGMENTS_TAG).d("${this.javaClass.simpleName} onDestroy")
        super.onDestroy()
    }

    override fun onDetach() {
        Timber.tag(ALL_FRAGMENTS_TAG).d("${this.javaClass.simpleName} onDetach")
        super.onDetach()
    }

    companion object {
        fun newInstance(exitPosition: IntArray) = FragmentRed().apply {
            posX = exitPosition[0]
            posY = exitPosition[1]
        }
    }

    override fun toString(): String = this.javaClass.simpleName
}