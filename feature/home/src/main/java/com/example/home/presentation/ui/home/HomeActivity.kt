package com.example.home.presentation.ui.home

import android.content.Context
import android.content.Intent
import androidx.fragment.app.Fragment
import com.example.core.base.BaseActivity
import com.example.home.R
import com.example.home.databinding.ActivityHomeBinding
import com.example.home.presentation.ui.homefeeds.HomeFeedsFragment
import com.example.home.presentation.ui.watchlist.WatchListFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeActivity: BaseActivity<ActivityHomeBinding, HomeViewModel>(ActivityHomeBinding::inflate) {

    private val homeFeedsFragment = HomeFeedsFragment()
    private val watchListFragment = WatchListFragment()
    private var activeFragment: Fragment = homeFeedsFragment

    companion object {
        fun createIntent(context: Context): Intent {
            return Intent(context, HomeActivity::class.java)
        }
    }

    override val viewModel: HomeViewModel by viewModel()

    override fun initView() {
        setupFragment()
    }

    private fun setupFragment() {
        // delete all fragment in fragment manager first
        for (fragment in supportFragmentManager.fragments) {
            supportFragmentManager.beginTransaction().remove(fragment).commit()
        }
        // add fragment to fragment manager
        supportFragmentManager.beginTransaction().apply {
            add(binding.container.id, homeFeedsFragment)
            add(binding.container.id, watchListFragment)
            hide(watchListFragment)
        }.commit()
        // set click menu for changing fragment
        binding.bottomNavView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.home -> {
                    showFragment(homeFeedsFragment)
                    true
                }
                else -> {
                    showFragment(watchListFragment)
                    true
                }
            }
        }
    }

    private fun showFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .hide(activeFragment)
            .show(fragment)
            .commit()
        activeFragment = fragment
    }
}