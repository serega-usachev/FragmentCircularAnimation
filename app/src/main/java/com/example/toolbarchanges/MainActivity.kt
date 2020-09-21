package com.example.toolbarchanges

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.toolbarchanges.animation.exitCircularReveal
import com.example.toolbarchanges.animation.findLocationOfCenterOnTheScreen
import com.google.android.material.navigation.NavigationView
import timber.log.Timber

interface ExitWithAnimation {
    var posX: Int?
    var posY: Int?

    fun isToBeExitedWithAnimation(): Boolean
}

class MainActivity : AppCompatActivity() {

    private lateinit var rootNavigator: RootNavigator

    private lateinit var drawerLayout: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        enableLogFragmentManager()
        initNavigator()

        drawerLayout = findViewById(R.id.root_drawer)

        findViewById<NavigationView>(R.id.root_nav_view).setNavigationItemSelectedListener {
            drawerLayout.closeDrawer(GravityCompat.START)
            when (it.itemId) {
                R.id.case1 -> {
                    val centerPosition = window.decorView.findLocationOfCenterOnTheScreen()
                    rootNavigator.navigateFragmentA(centerPosition)
                }
                R.id.case2 -> {
                    val centerPosition = window.decorView.findLocationOfCenterOnTheScreen()
                    rootNavigator.navigateFragmentB(centerPosition)
                }
                R.id.case3 -> {
                    val centerPosition = window.decorView.findLocationOfCenterOnTheScreen()
                    rootNavigator.navigateFragmentC(centerPosition)
                }
            }
            true
        }
    }

    private fun initNavigator() {
        rootNavigator = RootNavigator(supportFragmentManager, R.id.root_nav_container)
    }

    private fun enableLogFragmentManager() {
        supportFragmentManager.addOnBackStackChangedListener {
            Timber.d("BS entries: ${supportFragmentManager.backStackEntryCount}")
        }
        supportFragmentManager.registerFragmentLifecycleCallbacks(fragmentLifecycleCallbacks, true)
    }

    private fun animateExitingFragment(block: () -> Unit) {
        Timber.d("F in cont: ${supportFragmentManager.findFragmentById(R.id.root_nav_container)}")
        with(supportFragmentManager.findFragmentById(R.id.root_nav_container)) {
            if ((this as? ExitWithAnimation)?.isToBeExitedWithAnimation() == true) {
                if (this.posX == null || this.posY == null) {
                    Timber.d("F: ${this.javaClass.simpleName}, posX: ${this.posX}, posY: ${this.posY}")
                    block()
                } else {
                    this.view?.exitCircularReveal(this.posX!!, this.posY!!) {
                        block()
                    }
                }
            } else block()
        }
    }


    private val fragmentLifecycleCallbacks = object : FragmentManager.FragmentLifecycleCallbacks(){
        override fun onFragmentPreAttached(fm: FragmentManager, f: Fragment, context: Context) {
            super.onFragmentPreAttached(fm, f, context)
        }

        override fun onFragmentAttached(fm: FragmentManager, f: Fragment, context: Context) {
            super.onFragmentAttached(fm, f, context)
        }

        override fun onFragmentPreCreated(
            fm: FragmentManager,
            f: Fragment,
            savedInstanceState: Bundle?
        ) {
            super.onFragmentPreCreated(fm, f, savedInstanceState)
        }

        override fun onFragmentCreated(
            fm: FragmentManager,
            f: Fragment,
            savedInstanceState: Bundle?
        ) {
            super.onFragmentCreated(fm, f, savedInstanceState)
        }

        override fun onFragmentActivityCreated(
            fm: FragmentManager,
            f: Fragment,
            savedInstanceState: Bundle?
        ) {
            super.onFragmentActivityCreated(fm, f, savedInstanceState)
        }

        override fun onFragmentViewCreated(
            fm: FragmentManager,
            f: Fragment,
            v: View,
            savedInstanceState: Bundle?
        ) {
            super.onFragmentViewCreated(fm, f, v, savedInstanceState)
        }

        override fun onFragmentStarted(fm: FragmentManager, f: Fragment) {
            super.onFragmentStarted(fm, f)
        }

        override fun onFragmentResumed(fm: FragmentManager, f: Fragment) {
            super.onFragmentResumed(fm, f)
        }

        override fun onFragmentPaused(fm: FragmentManager, f: Fragment) {
            super.onFragmentPaused(fm, f)
        }

        override fun onFragmentStopped(fm: FragmentManager, f: Fragment) {
            super.onFragmentStopped(fm, f)
        }

        override fun onFragmentSaveInstanceState(
            fm: FragmentManager,
            f: Fragment,
            outState: Bundle
        ) {
            super.onFragmentSaveInstanceState(fm, f, outState)
        }

        override fun onFragmentViewDestroyed(fm: FragmentManager, f: Fragment) {
            super.onFragmentViewDestroyed(fm, f)
        }

        override fun onFragmentDestroyed(fm: FragmentManager, f: Fragment) {
            super.onFragmentDestroyed(fm, f)
        }

        override fun onFragmentDetached(fm: FragmentManager, f: Fragment) {
            super.onFragmentDetached(fm, f)
        }
    }
}