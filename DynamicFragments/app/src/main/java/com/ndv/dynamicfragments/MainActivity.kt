package com.ndv.dynamicfragments

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            setupFragment()
        }
    }

    private fun setupFragment() {
        val randomPersonFragment = RandomPersonFragment()
        val transaction = supportFragmentManager.beginTransaction()

        transaction.add(R.id.randomPersonsFragmentContainer, randomPersonFragment)
//        transaction.addToBackStack(null)
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
        transaction.commit()
    }
}