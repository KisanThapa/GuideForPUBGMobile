package com.np.kisanthapa.guideforpubgmobile.ui.attachment.ui.stock

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.np.kisanthapa.guideforpubgmobile.R
import com.np.kisanthapa.guideforpubgmobile.adapter.SupportedWeaponAdapter
import com.np.kisanthapa.guideforpubgmobile.adapter.SupportedWeaponModel
import com.np.kisanthapa.guideforpubgmobile.ui.attachment.ui.stock.model.StockModelParcelable
import com.np.kisanthapa.guideforpubgmobile.utility.UtilityObject
import com.squareup.picasso.Picasso

class StockDetailsFragment : Fragment() {

    private lateinit var mStockImageCardView: CardView
    private lateinit var mStockImageView: ImageView
    private lateinit var mStockName: TextView
    private lateinit var mStockFeature: TextView
    private lateinit var mStockSummary: TextView

    // For Supported Weapons List
    private lateinit var mStockSupportedWeaponRecyclerView: RecyclerView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_stock_details, container, false)


        // init views here
        mStockImageCardView = root.findViewById(R.id.mStockImageCardView)
        mStockImageView = root.findViewById(R.id.mStockImageView)
        mStockName = root.findViewById(R.id.mStockName)
        mStockFeature = root.findViewById(R.id.mStockFeature)
        mStockSummary = root.findViewById(R.id.mStockSummary)


        //Init recycler view here
        mStockSupportedWeaponRecyclerView =
            root.findViewById(R.id.mStockSupportedWeaponRecyclerView)


        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val stockData = StockDetailsFragmentArgs.fromBundle(requireArguments()).stockDataArgs

        setDataInViews(stockData)

    }

    private fun setDataInViews(stockData: StockModelParcelable) {
        mStockName.text = stockData.stockName
        mStockSummary.text = stockData.stockSummary
        mStockFeature.text = stockData.stockFeature

        try {
            Picasso.get().load(
                UtilityObject.getDrawableIdByName(
                    requireContext(),
                    stockData.stockImage
                )
            ).into(mStockImageView)

        } catch (e: Exception) {
            mStockImageView.setImageResource(R.drawable.ic_image_error_placeholder)
        }

        // Prepare Supported weapon data here
        val weaponNameList = stockData.stockSupportedWeaponName.split("\n")
        val weaponImageList = stockData.stockSupportedWeaponImage.split("\n")

        val supportedWeaponDataList = mutableListOf<SupportedWeaponModel>()

        for ((index, value) in weaponNameList.withIndex()) {
            val weaponData =
                SupportedWeaponModel(
                    index + 1,
                    value,
                    weaponImageList[index]
                )
            supportedWeaponDataList.add(weaponData)
        }
        // Init Recycler View here
        initRecyclerView(supportedWeaponDataList)

    }

    private fun initRecyclerView(supportedWeaponDataList: MutableList<SupportedWeaponModel>) {

        val supportedWeaponAdapter =
            SupportedWeaponAdapter(
                requireContext()
            )
        supportedWeaponAdapter.setSupportedWeaponData(supportedWeaponDataList)

        val viewManager = LinearLayoutManager(requireContext())
        viewManager.orientation = LinearLayoutManager.VERTICAL

        mStockSupportedWeaponRecyclerView.apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = supportedWeaponAdapter
        }

    }

}