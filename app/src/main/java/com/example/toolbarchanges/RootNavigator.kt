package com.example.toolbarchanges

import androidx.fragment.app.FragmentManager
import com.example.toolbarchanges.fragments.caseA.FragmentRed
import com.example.toolbarchanges.fragments.caseB.FragmentGreen
import com.example.toolbarchanges.fragments.caseC.FragmentBlue

class RootNavigator(
    private val fragmentManager: FragmentManager,
    private val rootLayoutResId: Int //TODO Add ResId annotation
) {

    fun removePreviousIfExist() {
        val removeIndex = fragmentManager.fragments.size - 2
        val prevFragment = fragmentManager.fragments.getOrNull(removeIndex)
        prevFragment?.let {
            fragmentManager.beginTransaction()
                .remove(prevFragment)
                .commitAllowingStateLoss()
        }
    }

    fun navigateInitialFragmentA() {
        fragmentManager.beginTransaction()
            .replace(rootLayoutResId, FragmentRed())
            .commit()
    }

    fun navigateFragmentRed(exitPosition: IntArray) {
        fragmentManager.beginTransaction()
            .add(rootLayoutResId, FragmentRed.newInstance(exitPosition))
            //.addToBackStack(null)
            .commit()
    }

    fun navigateFragmentGreen(exitPosition: IntArray) {
        fragmentManager.beginTransaction()
            .add(rootLayoutResId, FragmentGreen.newInstance(exitPosition))
            //.addToBackStack(null)
            .commit()
    }

    fun navigateFragmentBlue(exitPosition: IntArray) {
        fragmentManager.beginTransaction()
            .add(rootLayoutResId, FragmentBlue.newInstance(exitPosition))
            //.addToBackStack(null)
            .commit()
    }
}