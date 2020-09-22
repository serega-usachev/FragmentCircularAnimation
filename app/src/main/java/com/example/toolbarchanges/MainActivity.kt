package com.example.toolbarchanges

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.example.toolbarchanges.animation.exitCircularReveal
import com.example.toolbarchanges.animation.findLocationOfCenterOnTheScreen
import com.example.toolbarchanges.animation.makeStatusBarTransparent
import com.google.android.material.navigation.NavigationView
import timber.log.Timber

interface ExitWithAnimation {
    var posX: Int?
    var posY: Int?

    fun isToBeExitedWithAnimation(): Boolean
}

const val ALL_FRAGMENTS_TAG = "AllFragments"

class MainActivity : AppCompatActivity() {

    lateinit var rootNavigator: RootNavigator

    private lateinit var drawerLayout: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        makeStatusBarTransparent()

        initNavigator()
        drawerLayout = findViewById(R.id.root_drawer)

        findViewById<NavigationView>(R.id.root_nav_view).setNavigationItemSelectedListener {
            drawerLayout.closeDrawer(GravityCompat.START)
            when (it.itemId) {
                R.id.case_red -> {
                    val centerPosition = window.decorView.findLocationOfCenterOnTheScreen()
                    rootNavigator.navigateFragmentRed(centerPosition)
                }
                R.id.case_green -> {
                    val centerPosition = window.decorView.findLocationOfCenterOnTheScreen()
                    rootNavigator.navigateFragmentGreen(centerPosition)
                }
                R.id.case_blue -> {
                    val centerPosition = window.decorView.findLocationOfCenterOnTheScreen()
                    rootNavigator.navigateFragmentBlue(centerPosition)
                }
            }
            true
        }

        if (savedInstanceState == null) {
            rootNavigator.navigateInitialFragmentRed()
        }
    }

    fun openDrawer() {
        drawerLayout.openDrawer(GravityCompat.START)
    }

    private fun initNavigator() {
        rootNavigator = RootNavigator(supportFragmentManager, R.id.root_nav_container)
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
}