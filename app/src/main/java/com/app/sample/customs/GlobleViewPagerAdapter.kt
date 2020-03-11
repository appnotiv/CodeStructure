package com.app.sample.customs;

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter


class GlobleViewPagerAdapter(fragmentManager: FragmentManager) :
    FragmentPagerAdapter(
        fragmentManager,
        BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
    ) {


    var mFragmentList: MutableList<Fragment> = ArrayList()
    var mTabTitleList: MutableList<String> = ArrayList()


    override fun getItem(position: Int): Fragment {
        return mFragmentList[position]
    }


    override fun getPageTitle(position: Int): CharSequence? {
        return mTabTitleList[position]
    }

    override fun getCount(): Int {
        return mFragmentList.size
    }

    fun addFragmentTabDetail(fragment: Fragment, title: String) {
        mFragmentList.add(fragment)
        mTabTitleList.add(title)
    }

}