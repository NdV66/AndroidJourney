package com.ndv.designlibrary

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
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
    }

    //PRIVATE
    private fun prepareViewPager() :ViewPager2{
        val pager = findViewById<ViewPager2>(R.id.pager)
        pager.adapter = ScreenSlidePagerAdapter(this)
        return pager;
    }

    private fun setupCards(viewPager: ViewPager2) {
        val tabLayout = findViewById<TabLayout>(R.id.tabs)
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = CARD_TITLES[position]
        }.attach()
    }

    //INNER
    private inner class ScreenSlidePagerAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {
        override fun getItemCount(): Int = SCREENS.size
        override fun createFragment(position: Int): Fragment = SCREENS[position]!!
    }
}