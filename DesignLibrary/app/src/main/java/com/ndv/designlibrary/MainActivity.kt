package com.ndv.designlibrary

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

val SCREENS = mapOf(
    0 to StartFragment(),
    1 to MaiaFragment(),
    2 to ValarFragment()
)

val CARD_TITLES = mapOf(
    0 to "Start",
    1 to "Maia",
    2 to "Valar"
)

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val viewPager = prepareViewPager()
        setupCards(viewPager)
        setupFab()
    }

    //PRIVATE
    private fun prepareViewPager(): ViewPager2 {
        val pager = findViewById<ViewPager2>(R.id.pager)
        pager.adapter = ScreenSlidePagerAdapter(this)
        return pager
    }

    private fun setupCards(viewPager: ViewPager2) {
        val tabLayout = findViewById<TabLayout>(R.id.tabs)
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = CARD_TITLES[position]
        }.attach()
    }

    private fun setupFab() {
        val fab = findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener {
            showSnackBar()
        }
    }

    private fun showSnackBar() {
        val containerView = findViewById<CoordinatorLayout>(R.id.coordinator)
        val snackBar = Snackbar.make(containerView, R.string.hello, Snackbar.LENGTH_LONG)

        val listener = View.OnClickListener {
            val toast = Toast.makeText(baseContext, R.string.action, Toast.LENGTH_LONG)
            toast.show()
        }

        snackBar.setAction(R.string.action, listener)
        snackBar.show()
    }

    //INNER
    private inner class ScreenSlidePagerAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {
        override fun getItemCount(): Int = SCREENS.size
        override fun createFragment(position: Int): Fragment = SCREENS[position]!!
    }
}
