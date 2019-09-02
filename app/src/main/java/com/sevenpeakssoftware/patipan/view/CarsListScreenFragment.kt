package com.sevenpeakssoftware.patipan.view

import android.net.Network
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.sevenpeakssoftware.patipan.R
import com.sevenpeakssoftware.patipan.base.BaseAppCompatActivity
import com.sevenpeakssoftware.patipan.base.BaseFragment
import com.sevenpeakssoftware.patipan.shared.success
import com.sevenpeakssoftware.patipan.utils.NetworkUtils
import com.sevenpeakssoftware.patipan.view.adapter.CarsListAdapter
import com.sevenpeakssoftware.patipan.view.adapter.OnCarsListenerItem
import com.sevenpeakssoftware.patipan.view.viewmodel.CarsListScreenViewModel
import kotlinx.android.synthetic.main.cars_list_screen_fragment.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf
import java.io.IOException

class CarsListScreenFragment : BaseFragment() {
  private val carsListScreenViewModel: CarsListScreenViewModel by viewModel()
  private val carsListAdapter: CarsListAdapter by inject { parametersOf() }

  private val onNetworkCallback = object : BaseAppCompatActivity.OnNetWorkCallBack {
    override fun onAvailable(network: Network?) {
      carsListScreenViewModel.executeCarsList(network != null)
      showSnackBar(view ?: return, getString(R.string.error_message_internet_connection))
    }

    override fun onLost(network: Network?) {
      carsListScreenViewModel.executeCarsList(network == null)
      showSnackBar(view ?: return, getString(R.string.error_message_internet_loss))
    }
  }

  private val onCarsListenerItem = object : OnCarsListenerItem{
    override fun onRetryAgain() {
      showSnackBar(view,getString(R.string.message_loading))
      carsListScreenViewModel.executeCarsList(NetworkUtils.checkNetworkUtils(context))
    }
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    savedInstanceState ?: run {
      showSnackBar(view,getString(R.string.message_loading))
      carsListScreenViewModel.executeCarsList(NetworkUtils.checkNetworkUtils(context))
    }

  }

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    return inflater.inflate(R.layout.cars_list_screen_fragment, container, false)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    setNetWorkCallBack()
    setUpRecyclerView()
    setObserve()
  }

  private fun setNetWorkCallBack() {
    activity?.let {
      (it as? CarsListScreenActivity)?.setOnNetWorkCallBackFromFragment(onNetworkCallback)
    }
  }

  private fun setUpRecyclerView() {
    carsListScreenFragmentRecyclerView.layoutManager =
      LinearLayoutManager(context, RecyclerView.VERTICAL, false)
    carsListScreenFragmentRecyclerView.itemAnimator = DefaultItemAnimator()
    carsListScreenFragmentRecyclerView.adapter = carsListAdapter
    carsListAdapter.setOnCarsListenerItem(onCarsListenerItem)
  }

  private fun setObserve() {
    carsListScreenViewModel.observeCarsList().observe(this, Observer {
      it.success({ listCars ->
        carsListScreenViewModel.setItemsCarsList(listCars)
      }, { error ->
        when (error) {
          is IOException -> {
            showSnackBar(view, getString(R.string.error_message_internet_please_connection))
          }

          else -> {
            showSnackBar(view, error.message.toString())
          }
        }

      })
    })

    carsListScreenViewModel.observeCarsResponse().observe(this, Observer {
      carsListAdapter.addItemCarsList(it)
    })
  }

  private fun showSnackBar(view: View?, message: String) {
    Snackbar.make(view ?: return, message, Snackbar.LENGTH_LONG).show()
  }


  companion object {
    const val tagFragment: String = "carsListScreenFragment"
    fun newInstance(): CarsListScreenFragment =
      CarsListScreenFragment().apply { arguments = Bundle() }
  }
}
