package com.np.kisanthapa.guideforpubgmobile.ui.throwable.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.np.kisanthapa.guideforpubgmobile.R
import com.np.kisanthapa.guideforpubgmobile.ui.throwable.model.ThrowableModelClass
import com.np.kisanthapa.guideforpubgmobile.utility.UtilityObject
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_single_throwable.view.*


class ThrowableRecyclerAdapter(val context: Context) :
    RecyclerView.Adapter<ThrowableRecyclerAdapter.ThrowableViewHolder>() {

    //Interface for listening click
    private var throwableItemClickCallback: ThrowableItemClickCallback? = null

    interface ThrowableItemClickCallback {
        fun onItemClick(throwableModelClass: ThrowableModelClass)
    }

    fun setOnThrowableItemClickListener(throwableItemClickCallback: ThrowableItemClickCallback) {
        this.throwableItemClickCallback = throwableItemClickCallback
    }

    private var throwableDataList = emptyList<ThrowableModelClass>()
    internal fun setThrowableData(throwableData: List<ThrowableModelClass>) {
        throwableDataList = throwableData
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ThrowableViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(
                R.layout.item_single_throwable,
                parent,
                false
            )
        return ThrowableViewHolder(view)

    }

    override fun getItemCount() = throwableDataList.size

    override fun onBindViewHolder(holder: ThrowableViewHolder, position: Int) {
        holder.bindView(
            context,
            throwableDataList[position],
            throwableItemClickCallback!!
        )
    }

    class ThrowableViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private var mSingleThrowableLayout: ConstraintLayout = view.throwableConstraintLayout!!

        private val mThrowableName = view.throwableName

        //private val mThrowableLevel = view.throwableLevel
        private val mThrowableImage = view.throwableImageView

        fun bindView(
            context: Context,
            singleThrowableData: ThrowableModelClass,
            throwableItemClickCallback: ThrowableItemClickCallback
        ) {

            mThrowableName.text = singleThrowableData.throwableName

            val drawableId =
                UtilityObject.getDrawableIdByName(context, singleThrowableData.throwableImageName)

            Picasso.get().load(
                drawableId
            )
                .error(R.drawable.ic_image_error_placeholder)
                .into(mThrowableImage)

            mSingleThrowableLayout.setOnClickListener {
                throwableItemClickCallback.onItemClick(singleThrowableData)
            }

        }
    }


}