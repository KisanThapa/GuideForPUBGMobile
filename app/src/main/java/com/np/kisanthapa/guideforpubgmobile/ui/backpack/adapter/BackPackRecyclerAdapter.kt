package com.np.kisanthapa.guideforpubgmobile.ui.backpack.adapter

import android.content.Context
import android.content.res.Resources
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.np.kisanthapa.guideforpubgmobile.R
import com.np.kisanthapa.guideforpubgmobile.ui.backpack.model.BackPackModelClass
import com.np.kisanthapa.guideforpubgmobile.ui.backpack.model.BackPackModelClassParcelable
import com.np.kisanthapa.guideforpubgmobile.utility.UtilityObject
import com.squareup.picasso.Picasso
import com.stfalcon.imageviewer.StfalconImageViewer
import kotlinx.android.synthetic.main.item_single_backpack.view.*


class BackPackRecyclerAdapter(val context: Context) :
    RecyclerView.Adapter<BackPackRecyclerAdapter.BagPackViewHolder>() {

    lateinit var backPackItemClickCallback: BackPackItemClickCallback

    interface BackPackItemClickCallback {
        fun inItemClick(backPackModel: BackPackModelClass)
    }

    fun onSetBackPackItemClickListener(backPackItemClickCallback: BackPackItemClickCallback) {
        this.backPackItemClickCallback = backPackItemClickCallback
    }

    private var bagPackList = emptyList<BackPackModelClass>()

    internal fun setBagPackData(backPack: List<BackPackModelClass>) {
        bagPackList = backPack
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = BagPackViewHolder(
        LayoutInflater.from(parent.context)
            .inflate(
                R.layout.item_single_backpack,
                parent,
                false
            )
    )

    override fun getItemCount() = bagPackList.size

    override fun onBindViewHolder(holder: BagPackViewHolder, position: Int) {
        holder.bindView(context, bagPackList[position], backPackItemClickCallback)
    }

    class BagPackViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private var mSingleBackPackLayout: ConstraintLayout = view.backPackConstraintLayout!!


        private val mBackPackName = view.backPackName
        private val mBackPackLevel = view.backPackLevel
        private val mBackPackCapacity = view.backPackCapacity
        private val mBackPackImage = view.backPackImageView

        fun bindView(
            context: Context,
            backPack: BackPackModelClass,
            backPackItemClickCallback: BackPackItemClickCallback
        ) {

            mBackPackName.text = backPack.backPackName
            mBackPackLevel.text = backPack.backPackLevel
            mBackPackCapacity.text = "Capacity: ${backPack.backPackCapacity}"

            mSingleBackPackLayout.setOnClickListener {
                backPackItemClickCallback.inItemClick(backPack)
            }

            val drawableId = UtilityObject.getDrawableIdByName(context, backPack.backPackImageName)

            Picasso.get()
                .load(drawableId)
                .error(R.drawable.ic_image_error_placeholder)
                .into(mBackPackImage)
        }

    }
}