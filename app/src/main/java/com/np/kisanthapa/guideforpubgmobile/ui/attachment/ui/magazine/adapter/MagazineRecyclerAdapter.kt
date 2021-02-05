package com.np.kisanthapa.guideforpubgmobile.ui.attachment.ui.magazine.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.np.kisanthapa.guideforpubgmobile.R
import com.np.kisanthapa.guideforpubgmobile.ui.attachment.ui.magazine.model.MagazineModel
import com.np.kisanthapa.guideforpubgmobile.ui.attachment.ui.scope.model.ScopeModel
import com.np.kisanthapa.guideforpubgmobile.utility.UtilityObject
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_single_magazine.view.*
import kotlinx.android.synthetic.main.item_single_scope.view.*


class MagazineRecyclerAdapter(val context: Context) :
    RecyclerView.Adapter<MagazineRecyclerAdapter.MagazineViewHolder>() {

    lateinit var magazineItemClickCallback: MagazineItemClickCallback

    interface MagazineItemClickCallback {
        fun onItemClick(magazineModel: MagazineModel)
    }

    fun onSetMagazineItemClickListener(magazineItemClickCallback: MagazineItemClickCallback) {
        this.magazineItemClickCallback = magazineItemClickCallback
    }

    private var magazineDataList = emptyList<MagazineModel>()

    internal fun setMagazineData(magazineList: List<MagazineModel>) {
        magazineDataList = magazineList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = MagazineViewHolder(
        LayoutInflater.from(parent.context)
            .inflate(
                R.layout.item_single_magazine,
                parent,
                false
            )
    )

    override fun getItemCount() = magazineDataList.size

    override fun onBindViewHolder(holder: MagazineViewHolder, position: Int) {
        holder.bindView(context, magazineDataList[position], magazineItemClickCallback)
    }


    class MagazineViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private var mMagazineLayout: ConstraintLayout = view.magazineConstraintLayout!!
        private val mMagazineName = view.magazineName
        private val mMagazineImage = view.magazineImageView

        fun bindView(
            context: Context,
            magazineModel: MagazineModel,
            magazineItemClickCallback: MagazineItemClickCallback
        ) {
            mMagazineName.text = magazineModel.magName

            mMagazineLayout.setOnClickListener {
                magazineItemClickCallback.onItemClick(magazineModel)
            }

            // Image
            val drawableId =
                UtilityObject
                    .getDrawableIdByName(context, magazineModel.magImage)
            try {
                Picasso.get()
                    .load(drawableId)
                    .into(mMagazineImage)
            } catch (e: Exception) {
                mMagazineImage.setImageResource(R.drawable.ic_image_error_placeholder)
            }

        }

    }

}
