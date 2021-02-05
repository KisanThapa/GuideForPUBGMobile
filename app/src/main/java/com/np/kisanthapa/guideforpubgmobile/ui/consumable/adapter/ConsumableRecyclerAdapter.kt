package com.np.kisanthapa.guideforpubgmobile.ui.consumable.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.np.kisanthapa.guideforpubgmobile.R
import com.np.kisanthapa.guideforpubgmobile.ui.consumable.model.ConsumableModel
import com.np.kisanthapa.guideforpubgmobile.utility.UtilityObject
import com.squareup.picasso.Picasso
import com.stfalcon.imageviewer.StfalconImageViewer
import kotlinx.android.synthetic.main.item_single_boost_graph.view.*
import kotlinx.android.synthetic.main.item_single_consumable.view.*


class ConsumableRecyclerAdapter(
    val context: Context
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val TYPE_BOOST_GRAPH_ITEM = 4
    val TYPE_OTHER_ITEM = 5

    private var consumableDataList = emptyList<ConsumableModel>()

    //Listeners for listening click events
    private lateinit var consumableItemClickCallback: ConsumableItemClickCallback

    interface ConsumableItemClickCallback {
        fun onItemClick(consumableData: ConsumableModel)
    }

    fun setOnConsumableItemClickListener(consumableItemClickCallback: ConsumableItemClickCallback) {
        this.consumableItemClickCallback = consumableItemClickCallback
    }


    internal fun setConsumableData(consumableDataList: List<ConsumableModel>) {
        this.consumableDataList = consumableDataList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return if (viewType == TYPE_BOOST_GRAPH_ITEM) {
            BoostGraphViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_single_boost_graph, parent, false)
            )
        } else {
            ConsumableViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_single_consumable, parent, false)
            )
        }
    }

    override fun getItemCount() = consumableDataList.size + 1

    override fun getItemViewType(position: Int): Int {
        return if (UtilityObject.isRecyclerViewHeader(position - 1))
            TYPE_BOOST_GRAPH_ITEM
        else
            TYPE_OTHER_ITEM
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (UtilityObject.isRecyclerViewHeader(position - 1)) {
            (holder as BoostGraphViewHolder).bindView(context)
        } else {
            (holder as ConsumableViewHolder).bindView(
                context,
                consumableDataList[position - 1],
                consumableItemClickCallback
            )
        }
    }


    class ConsumableViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val mConsumableImageView = view.consumableImageView
        private val mConsumableName = view.consumableName
        private val nConsumableLayout = view.consumableConstraintLayout

        private val mConsumableItemType = view.consumableItemType

        fun bindView(
            context: Context,
            consumableData: ConsumableModel,
            consumableItemClickCallback: ConsumableItemClickCallback
        ) {

            mConsumableName.text = consumableData.consumableName
            mConsumableItemType.text = consumableData.consumableType

            try {
                Picasso.get().load(
                    UtilityObject.getDrawableIdByName(
                        context,
                        consumableData.consumableImageName
                    )
                ).into(mConsumableImageView)
            } catch (e: Exception) {
                mConsumableImageView.setImageResource(R.drawable.ic_image_error_placeholder)
            }

            nConsumableLayout.setOnClickListener {
                consumableItemClickCallback.onItemClick(consumableData)
            }
        }
    }

    class BoostGraphViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val boostImageView: ImageView = view.mBoostGraphImageView
        fun bindView(context: Context) {

            Picasso.get().load(R.drawable.boost_graph_pic).into(boostImageView)

            boostImageView.setOnClickListener {

                StfalconImageViewer.Builder(
                    context,
                    listOf(R.drawable.boost_graph_pic)
                ) { view, image ->
                    Picasso.get().load(image).into(view)
                }
                    .withTransitionFrom(boostImageView)
                    .withHiddenStatusBar(true)
                    .build()
                    .show(true)

            }
        }
    }
}