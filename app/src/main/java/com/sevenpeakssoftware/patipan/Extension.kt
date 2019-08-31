package com.sevenpeakssoftware.patipan

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment


fun <T : Fragment> AppCompatActivity.replaceFragment(container  : Int, fragment : T,tagFragment : String,isBackStack : Boolean){
  val supportFragmentManager = supportFragmentManager.beginTransaction()

  if (isBackStack){
    supportFragmentManager.replace(container, fragment,tagFragment).commit()
  }else{
    supportFragmentManager.replace(container,fragment,tagFragment).addToBackStack(null).commit()
  }

}