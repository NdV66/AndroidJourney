package com.ndv.sharedviewmodel

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction

class StepperActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stepper)

        if (savedInstanceState == null) {
            val fragment = fragmentFactory(FRAGMENTS.NAME)
            setFragment(fragment, false)
        }
    }

    private fun fragmentFactory(fragment: FRAGMENTS): Fragment {
        val nameFragment = NameFragment()
        val metalFragment = MetalFragment()
        val summaryFragment = SummaryFragment()

        metalFragment.listener = IStepperFragmentListener {
            setFragment(summaryFragment)
        }

        nameFragment
            .listener = IStepperFragmentListener {
            setFragment(metalFragment)
        }

        summaryFragment.listener = IStepperFragmentListener {
            println(">>>>>>>>>> SUMMARY")
        }

        return when (fragment) {
            FRAGMENTS.METAl -> metalFragment
            FRAGMENTS.NAME -> nameFragment
            FRAGMENTS.SUMMARY -> summaryFragment
        }
    }

    private fun setFragment(fragment: Fragment, addToBackStack: Boolean = true) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.stepperScreenContainer, fragment)

        if (addToBackStack) {
            transaction.addToBackStack(null)
        }

        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
        transaction.commit()
    }
}