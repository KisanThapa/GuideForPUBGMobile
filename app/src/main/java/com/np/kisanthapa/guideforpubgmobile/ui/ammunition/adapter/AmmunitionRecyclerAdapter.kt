package com.np.kisanthapa.guideforpubgmobile.ui.ammunition.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.np.kisanthapa.guideforpubgmobile.R
import com.np.kisanthapa.guideforpubgmobile.ui.ammunition.model.AmmunitionModel
import com.np.kisanthapa.guideforpubgmobile.utility.UtilityObject
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_single_ammunition.view.*

class AmmunitionRecyclerAdapter(private val context: Context) :
    RecyclerView.Adapter<AmmunitionRecyclerAdapter.AmmunitionViewHolder>() {

    // Interface for listening click
    private var ammunitionItemClickCallback: AmmunitionItemClickCallback? = null

    interface AmmunitionItemClickCallback {
        fun onItemClick(ammunitionModel: AmmunitionModel)
    }

    fun setOnAmmunitionItemClickListener(ammunitionItemClickCallback: AmmunitionItemClickCallback) {
        this.ammunitionItemClickCallback = ammunitionItemClickCallback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = AmmunitionViewHolder(
        LayoutInflater.from(parent.context)
            .inflate(
                R.layout.item_single_ammunition,
                parent,
                false)
    )

    private var ammunitionDataList = emptyList<AmmunitionModel>()

    internal fun setAmmunitionData(data: List<AmmunitionModel>) {
        ammunitionDataList = data
        notifyDataSetChanged()
    }

    override fun getItemCount() = ammunitionDataList.size

    override fun onBindViewHolder(holder: AmmunitionViewHolder, position: Int) {
        holder.bindView(
            context,
            ammunitionDataList[position],
            ammunitionItemClickCallback!!)
    }

    class AmmunitionViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val ammunitionItemLayout = view.ammunitionConstraintLayout
        private val ammunitionName = view.ammunitionName
        private val ammunitionInfo = view.ammunitionInfo
        private val ammunitionImage = view.ammunitionImageView

        fun bindView(
            context: Context,
            ammunitionModel: AmmunitionModel,
            amItemClickCallback: AmmunitionItemClickCallback
        ) {

            ammunitionName.text = ammunitionModel.amName
            //ammunitionInfo.text = getWeaponInComma(ammunitionModel.amSupportedWeaponName)

            Picasso.get().load(
                UtilityObject.getDrawableIdByName(context, ammunitionModel.amImageName)
            ).into(ammunitionImage)

            ammunitionItemLayout.setOnClickListener {
                amItemClickCallback.onItemClick(ammunitionModel)
            }

        }

//        private fun getWeaponInComma(weapons: String): String {
//
//
//        }

    }

}