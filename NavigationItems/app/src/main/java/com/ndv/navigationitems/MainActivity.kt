package com.ndv.navigationitems

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupToolbar()
        showFirstFragment()
    }

    private fun showFirstFragment() {
        val fragment = ValarFragment()
        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.contentFrame, fragment)
        transaction.commit()
    }

    private fun setupToolbar() {
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
    }
}
