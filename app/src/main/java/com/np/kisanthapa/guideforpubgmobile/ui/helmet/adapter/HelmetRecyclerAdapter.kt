package com.np.kisanthapa.guideforpubgmobile.ui.helmet.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.np.kisanthapa.guideforpubgmobile.R
import com.np.kisanthapa.guideforpubgmobile.ui.helmet.model.HelmetModelClass
import com.np.kisanthapa.guideforpubgmobile.utility.UtilityObject
import com.squareup.picasso.Picasso
import com.stfalcon.imageviewer.StfalconImageViewer
import kotlinx.android.synthetic.main.item_single_helmet.view.*

class HelmetRecyclerAdapter(val context: Context) :
    RecyclerView.Adapter<HelmetRecyclerAdapter.HelmetViewHolder>() {

    //Interface for listening click
    private var helmetItemClickCallback: HelmetItemClickCallback? = null

    interface HelmetItemClickCallback {
        fun onItemClick(helmetModelClass: HelmetModelClass)
    }

    fun setOnHelmetItemClickListener(helmetItemClickCallback: HelmetItemClickCallback) {
        this.helmetItemClickCallback = helmetItemClickCallback
    }

    private var helmetDataList = emptyList<HelmetModelClass>()

    internal fun setHelmetData(helmetData: List<HelmetModelClass>) {
        helmetDataList = helmetData
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HelmetViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(
                R.layout.item_single_helmet,
                parent,
                false
            )
        return HelmetViewHolder(view)

    }

    override fun getItemCount() = helmetDataList.size

    override fun onBindViewHolder(holder: HelmetViewHolder, position: Int) {
        holder.bindView(
            context,
            helmetDataList[position],
            helmetItemClickCallback!!
        )
    }

    class HelmetViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private var mSingleHelmetLayout: ConstraintLayout = view.helmetConstraintLayout!!

        private val mHelmetName = view.helmetName
        private val mHelmetLevel = view.helmetLevel
        private val mHelmetDurability = view.helmetDurability
        private val mHelmetImage = view.helmetImageView

        fun bindView(
            context: Context,
            singleHelmetData: HelmetModelClass,
            helmetItemClickCallback: HelmetItemClickCallback
        ) {

            mHelmetName.text = singleHelmetData.helmetName
            mHelmetLevel.text = singleHelmetData.helmetLevel
            mHelmetDurability.text = "Durability: ${singleHelmetData.helmetDurability}"

            val drawableId =
                UtilityObject.getDrawableIdByName(context, singleHelmetData.helmetImageName)

            Picasso.get().load(
                drawableId
            ).error(R.drawable.ic_image_error_placeholder)
                .into(mHelmetImage)

            mSingleHelmetLayout.setOnClickListener {
                helmetItemClickCallback.onItemClick(singleHelmetData)
            }

        }
    }

}