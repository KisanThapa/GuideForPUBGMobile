package com.np.kisanthapa.guideforpubgmobile.ui.attachment.ui.muzzle.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.np.kisanthapa.guideforpubgmobile.R
import com.np.kisanthapa.guideforpubgmobile.ui.attachment.ui.muzzle.model.MuzzleModel
import com.np.kisanthapa.guideforpubgmobile.ui.attachment.ui.scope.model.ScopeModel
import com.np.kisanthapa.guideforpubgmobile.utility.UtilityObject
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_single_muzzle.view.*

class MuzzleRecyclerAdapter(private val context: Context) :
    RecyclerView.Adapter<MuzzleRecyclerAdapter.MuzzleViewHolder>() {


    lateinit var muzzleItemClickCallback: MuzzleItemClickCallback

    interface MuzzleItemClickCallback {
        fun onItemClick(muzzleModel: MuzzleModel)
    }

    fun onSetMuzzleItemClickListener(muzzleItemClickCallback: MuzzleItemClickCallback) {
        this.muzzleItemClickCallback = muzzleItemClickCallback
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = MuzzleViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_single_muzzle, parent, false)
    )

    private var muzzleDataList = emptyList<MuzzleModel>()

    internal fun setMuzzleData(muzzleList: List<MuzzleModel>) {
        muzzleDataList = muzzleList
        notifyDataSetChanged()
    }

    override fun getItemCount() = muzzleDataList.size

    override fun onBindViewHolder(holder: MuzzleViewHolder, position: Int) {
        holder.bindView(context, muzzleDataList[position], muzzleItemClickCallback)
    }

    class MuzzleViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private var mMuzzleLayout: ConstraintLayout = view.muzzleConstraintLayout!!
        private val mMuzzleName = view.muzzleName
        private val mMuzzleImage = view.muzzleImageView

        fun bindView(
            context: Context,
            muzzleModel: MuzzleModel,
            muzzleItemClickCallback: MuzzleItemClickCallback
        ) {

            mMuzzleName.text = muzzleModel.muzzleName

            mMuzzleLayout.setOnClickListener {
                muzzleItemClickCallback.onItemClick(muzzleModel)
            }

            // Image set
            try {
                val drawableId =
                    UtilityObject
                        .getDrawableIdByName(context, muzzleModel.muzzleImage)

                Picasso.get()
                    .load(drawableId)
                    .into(mMuzzleImage)
            } catch (e: Exception) {
                mMuzzleImage.setImageResource(R.drawable.ic_image_error_placeholder)
            }

        }

    }

}