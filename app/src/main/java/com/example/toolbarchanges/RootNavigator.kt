package com.example.toolbarchanges

import androidx.fragment.app.FragmentManager
import com.example.toolbarchanges.fragments.caseA.FragmentA
import com.example.toolbarchanges.fragments.caseB.FragmentB
import com.example.toolbarchanges.fragments.caseC.FragmentC

class RootNavigator(
    private val fragmentManager: FragmentManager,
    private val rootLayoutResId: Int //TODO Add ResId annotation
) {

    fun navigateInitialFragmentA() {
        fragmentManager.beginTransaction()
            .replace(rootLayoutResId, FragmentA())
            .commit()
    }

    fun navigateFragmentA(exitPosition: IntArray) {
        fragmentManager.beginTransaction()
            .add(rootLayoutResId, FragmentA.newInstance(exitPosition))
            //.addToBackStack(null)
            .commit()
    }

    fun navigateFragmentB(exitPosition: IntArray) {
        fragmentManager.beginTransaction()
            .add(rootLayoutResId, FragmentB.newInstance(exitPosition))
            //.addToBackStack(null)
            .commit()
    }

    fun navigateFragmentC(exitPosition: IntArray) {
        fragmentManager.beginTransaction()
            .add(rootLayoutResId, FragmentC.newInstance(exitPosition))
            //.addToBackStack(null)
            .commit()
    }
}