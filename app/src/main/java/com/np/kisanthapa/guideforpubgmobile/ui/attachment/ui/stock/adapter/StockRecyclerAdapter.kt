package com.np.kisanthapa.guideforpubgmobile.ui.attachment.ui.stock.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.np.kisanthapa.guideforpubgmobile.R
import com.np.kisanthapa.guideforpubgmobile.ui.attachment.ui.stock.model.StockModel
import com.np.kisanthapa.guideforpubgmobile.utility.UtilityObject
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_single_stock.view.*


class StockRecyclerAdapter(val context: Context) :
    RecyclerView.Adapter<StockRecyclerAdapter.StockViewHolder>() {

    lateinit var stockItemClickCallback: StockItemClickCallback

    interface StockItemClickCallback {
        fun onItemClick(stockModel: StockModel)
    }

    fun onSetStockItemClickListener(stockItemClickCallback: StockItemClickCallback) {
        this.stockItemClickCallback = stockItemClickCallback
    }

    private var stockDataList = emptyList<StockModel>()

    internal fun setStockData(stockList: List<StockModel>) {
        stockDataList = stockList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = StockViewHolder(
        LayoutInflater.from(parent.context)
            .inflate(
                R.layout.item_single_stock,
                parent,
                false
            )
    )

    override fun getItemCount() = stockDataList.size

    override fun onBindViewHolder(holder: StockViewHolder, position: Int) {
        holder.bindView(context, stockDataList[position], stockItemClickCallback)
    }

    class StockViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private var mStockLayout: ConstraintLayout = view.stockConstraintLayout!!
        private val mStockName = view.stockName
        private val mStockImage = view.stockImageView

        fun bindView(
            context: Context,
            stockModel: StockModel,
            stockItemClickCallback: StockItemClickCallback
        ) {
            mStockName.text = stockModel.stockName

            mStockLayout.setOnClickListener {
                stockItemClickCallback.onItemClick(stockModel)
            }

            // Set Image
            try {
                val drawableId =
                    UtilityObject
                        .getDrawableIdByName(context, stockModel.stockImage)
                Picasso.get()
                    .load(drawableId)
                    .into(mStockImage)
            } catch (e: Exception) {
                mStockImage.setImageResource(R.drawable.ic_image_error_placeholder)
            }

        }
    }
}