package com.sevenpeakssoftware.patipan.view

import android.os.Bundle
import com.sevenpeakssoftware.patipan.R
import com.sevenpeakssoftware.patipan.base.BaseAppCompatActivity
import com.sevenpeakssoftware.patipan.replaceFragment
import kotlinx.android.synthetic.main.cars_list_screen_activity.*

class CarsListScreenActivity : BaseAppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.cars_list_screen_activity)

    setSupportToolbar()
    setFragment(savedInstanceState)
  }

  private fun setSupportToolbar() {
    setSupportActionBar(carsListScreenToolbar)
  }

  private fun setFragment(savedInstanceState: Bundle?) {
    savedInstanceState ?: run {
      replaceFragment(
        frameLayoutCarsListScreenFragment.id,
        CarsListScreenFragment.newInstance(),
        CarsListScreenFragment.tagFragment,
        false
      )
    }
  }

}
