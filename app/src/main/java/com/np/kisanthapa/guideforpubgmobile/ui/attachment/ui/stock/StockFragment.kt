package com.np.kisanthapa.guideforpubgmobile.ui.attachment.ui.stock

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.np.kisanthapa.guideforpubgmobile.R
import com.np.kisanthapa.guideforpubgmobile.ui.attachment.ui.stock.adapter.StockRecyclerAdapter
import com.np.kisanthapa.guideforpubgmobile.ui.attachment.ui.stock.model.StockModel
import com.np.kisanthapa.guideforpubgmobile.ui.attachment.ui.stock.model.StockModelParcelable

class StockFragment : Fragment() {

    companion object {
        fun newInstance() = StockFragment()
    }

    private lateinit var viewModel: StockViewModel

    // Recycler view
    private lateinit var mStockRecyclerView: RecyclerView

    // Adapter
    private lateinit var mStockRecyclerAdapter: StockRecyclerAdapter

    // Nav Controller
    private lateinit var mNavController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.stock_fragment, container, false)

        viewModel = ViewModelProvider(this).get(StockViewModel::class.java)

        mStockRecyclerView = root.findViewById(R.id.mStockRecyclerView)

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mNavController = Navigation.findNavController(view)

        initRecyclerView()

        viewModel.stockList.observe(viewLifecycleOwner, Observer {
            it?.let {
                mStockRecyclerAdapter.setStockData(it)
            }
        })
    }

    private fun initRecyclerView() {

        mStockRecyclerAdapter = StockRecyclerAdapter(requireContext())

        val viewManager = GridLayoutManager(requireContext(), 2)

        mStockRecyclerView.apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = mStockRecyclerAdapter
        }

        // Click handling
        mStockRecyclerAdapter.onSetStockItemClickListener(object :
            StockRecyclerAdapter.StockItemClickCallback {
            override fun onItemClick(stockModel: StockModel) {

                val stockModelParcelable = StockModelParcelable(
                    stockModel.stockId,
                    stockModel.stockName,
                    stockModel.stockSummary,
                    stockModel.stockFeature,
                    stockModel.stockSupportedWeaponName,
                    stockModel.stockSupportedWeaponImage,
                    stockModel.stockImage
                )

                mNavController.navigate(
                    StockFragmentDirections.actionStockFragmentToStockDetailsFragment(
                        stockModelParcelable,
                        stockModel.stockName
                    )
                )

            }
        })

    }

}