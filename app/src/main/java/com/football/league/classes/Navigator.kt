package com.football.league.classes

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity

class Navigator {
    /*loadFragment parameters >> 1-the context 2-the fragment which navigation to it 3-the id of container of fragment
   4-isStacked variable used in for back purpose(true>>the fragment still in progress and can back to it , false >> the fragment will be destroyed)*/
    companion object {
        fun loadFragment(
            activity: FragmentActivity?,
            baseFragment: Fragment?,
            containerId: Int,
            isStacked: Boolean
        ) {
            if (!isStacked) activity!!.supportFragmentManager.beginTransaction()
                .replace(containerId, baseFragment!!)
                .commitAllowingStateLoss() else activity!!.supportFragmentManager.beginTransaction()
                .replace(containerId, baseFragment!!).addToBackStack("").commit()
        }
    }
}