package com.ndv.navigationitems

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.google.android.material.navigation.NavigationView


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar = setupToolbar()
        showFirstFragment()
        setupDrawerToggle(toolbar)
        setupOnClickNavigationItem()
    }

    override fun onBackPressed() {
        val drawer = findViewById<DrawerLayout>(R.id.drawerLayout)

        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    private fun showFirstFragment() {
        val fragment = ValarFragment()
        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.contentFrame, fragment)
        transaction.commit()
    }

    private fun setupToolbar(): Toolbar {
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        return toolbar
    }

    private fun setupDrawerToggle(toolbar: Toolbar) {
        val drawer = findViewById<DrawerLayout>(R.id.drawerLayout)
        val toggle = ActionBarDrawerToggle(this, drawer, toolbar, R.string.open, R.string.close)
        drawer.addDrawerListener(toggle)
        toggle.syncState()
    }

    private fun closeAppDrawer() {
        val drawer = findViewById<DrawerLayout>(R.id.drawerLayout)
        drawer.closeDrawer(GravityCompat.START)
    }

    private fun replaceFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.contentFrame, fragment)
        transaction.commit()
    }

    private fun getOnClickListener(
        fragments: Map<Int, Fragment>,
        intents: Map<Int, Intent>,
    ): NavigationView.OnNavigationItemSelectedListener {
        return NavigationView.OnNavigationItemSelectedListener {
            val fragment = fragments[it.itemId]
            val intent = intents[it.itemId]

            if (fragment != null) {
                replaceFragment(fragment)
            } else if (intent != null) {
                startActivity(intent)
            }

            closeAppDrawer()
            true
        }
    }

    private fun setupOnClickNavigationItem() {
        val fragments = mapOf(
            R.id.maia to MaiaFragment(),
            R.id.valar to ValarFragment(),
        )

        val intents = mapOf(
            R.id.help to Intent(this, HelpActivity::class.java)
        )

        val listener = getOnClickListener(fragments, intents)
        val navigationView = findViewById<NavigationView>(R.id.navView)
        navigationView.setNavigationItemSelectedListener(listener)
    }
}
