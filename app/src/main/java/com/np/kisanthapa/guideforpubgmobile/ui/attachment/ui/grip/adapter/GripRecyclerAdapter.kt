package com.np.kisanthapa.guideforpubgmobile.ui.attachment.ui.grip.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.np.kisanthapa.guideforpubgmobile.R
import com.np.kisanthapa.guideforpubgmobile.ui.attachment.ui.grip.model.GripModel
import com.np.kisanthapa.guideforpubgmobile.utility.UtilityObject
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_single_grip.view.*


class GripRecyclerAdapter(val context: Context) :
    RecyclerView.Adapter<GripRecyclerAdapter.GripViewHolder>() {

    lateinit var gripItemClickCallback: GripItemClickCallback

    interface GripItemClickCallback {
        fun onItemClick(gripModel: GripModel)
    }

    fun onSetGripItemClickListener(gripItemClickCallback: GripItemClickCallback) {
        this.gripItemClickCallback = gripItemClickCallback
    }

    private var gripDataList = emptyList<GripModel>()

    internal fun setGripData(gripList: List<GripModel>) {
        gripDataList = gripList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = GripViewHolder(
        LayoutInflater.from(parent.context)
            .inflate(
                R.layout.item_single_grip,
                parent,
                false
            )
    )

    override fun getItemCount() = gripDataList.size

    override fun onBindViewHolder(holder: GripViewHolder, position: Int) {
        holder.bindView(context, gripDataList[position], gripItemClickCallback)
    }


    class GripViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private var mGripLayout: ConstraintLayout = view.gripConstraintLayout!!
        private val mGripName = view.gripName
        private val mGripImage = view.gripImageView

        fun bindView(
            context: Context,
            gripModel: GripModel,
            gripItemClickCallback: GripItemClickCallback
        ) {
            mGripName.text = gripModel.gripName

            mGripLayout.setOnClickListener {
                gripItemClickCallback.onItemClick(gripModel)
            }

            try {
                val drawableId =
                    UtilityObject
                        .getDrawableIdByName(context, gripModel.gripImage)

                Picasso.get()
                    .load(drawableId)
                    .into(mGripImage)
            } catch (e: Exception) {
                mGripImage.setImageResource(R.drawable.ic_image_error_placeholder)
            }

        }
    }
}