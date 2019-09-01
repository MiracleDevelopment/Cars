package com.sevenpeakssoftware.patipan.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sevenpeakssoftware.patipan.R
import com.sevenpeakssoftware.patipan.base.BaseFragment
import com.sevenpeakssoftware.patipan.shared.success
import com.sevenpeakssoftware.patipan.view.adapter.CarsListAdapter
import com.sevenpeakssoftware.patipan.view.viewmodel.CarsListScreenViewModel
import kotlinx.android.synthetic.main.cars_list_screen_fragment.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class CarsListScreenFragment : BaseFragment() {
  private val carsListScreenViewModel: CarsListScreenViewModel by viewModel()
  private val carsListAdapter: CarsListAdapter by inject { parametersOf() }

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    return inflater.inflate(R.layout.cars_list_screen_fragment, container, false)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    setUpRecyclerView()
    setObserve()
  }

  private fun setUpRecyclerView() {
    carsListScreenFragmentRecyclerView.layoutManager =
      LinearLayoutManager(context, RecyclerView.VERTICAL, false)
    carsListScreenFragmentRecyclerView.itemAnimator = DefaultItemAnimator()
    carsListScreenFragmentRecyclerView.adapter = carsListAdapter
  }

  private fun setObserve() {
    carsListScreenViewModel.observeCarsList().observe(this, Observer {
      it.success({listCars->
        carsListScreenViewModel.setItemsCarsList(listCars)
      },{error->
        Toast.makeText(context,"${error.message}",Toast.LENGTH_LONG).show()
      })
    })


    carsListScreenViewModel.observeCarsResponse().observe(this, Observer {
      carsListAdapter.addItemCarsList(it)
    })
  }

  override fun onStart() {
    super.onStart()
    carsListScreenViewModel.executeCarsList()
  }

  companion object {
    const val tagFragment: String = "carsListScreenFragment"
    fun newInstance(): CarsListScreenFragment =
      CarsListScreenFragment().apply { arguments = Bundle() }
  }
}
