package com.np.kisanthapa.guideforpubgmobile.ui.armor.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.np.kisanthapa.guideforpubgmobile.R
import com.np.kisanthapa.guideforpubgmobile.ui.armor.model.ArmorModelClass
import com.np.kisanthapa.guideforpubgmobile.utility.UtilityObject
import com.squareup.picasso.Picasso
import com.stfalcon.imageviewer.StfalconImageViewer
import kotlinx.android.synthetic.main.item_single_armor.view.*


class ArmorRecyclerAdapter(val context: Context) :
    RecyclerView.Adapter<ArmorRecyclerAdapter.ArmorViewHolder>() {

    //Interface for listening click
    private var armorItemClickCallback: ArmorItemClickCallback? = null

    interface ArmorItemClickCallback {
        fun onItemClick(armorModelClass: ArmorModelClass)
    }

    fun setOnArmorItemClickListener(armorItemClickCallback: ArmorItemClickCallback) {
        this.armorItemClickCallback = armorItemClickCallback
    }

    private var armorDataList = emptyList<ArmorModelClass>()
    internal fun setArmorData(armorData: List<ArmorModelClass>) {
        armorDataList = armorData
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ArmorViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(
                R.layout.item_single_armor,
                parent,
                false
            )
        return ArmorViewHolder(view)

    }

    override fun getItemCount() = armorDataList.size

    override fun onBindViewHolder(holder: ArmorViewHolder, position: Int) {
        holder.bindView(
            context,
            armorDataList[position],
            armorItemClickCallback!!
        )
    }

    class ArmorViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private var mSingleArmorLayout: ConstraintLayout = view.armorConstraintLayout!!

        private val mArmorName = view.armorName
        private val mArmorLevel = view.armorLevel
        private val mArmorDurability = view.armorDurability
        private val mArmorImage = view.armorImageView

        fun bindView(
            context: Context,
            singleArmorData: ArmorModelClass,
            armorItemClickCallback: ArmorItemClickCallback
        ) {

            mArmorName.text = singleArmorData.armorName
            mArmorLevel.text = singleArmorData.armorLevel
            mArmorDurability.text = "Durability: ${singleArmorData.armorDurability}"

            val drawableId =
                UtilityObject.getDrawableIdByName(context, singleArmorData.armorImageName)

            Picasso.get().load(
                drawableId
            )
                .error(R.drawable.ic_image_error_placeholder)
                .into(mArmorImage)

            mSingleArmorLayout.setOnClickListener {
                armorItemClickCallback.onItemClick(singleArmorData)
            }
        }
    }


}