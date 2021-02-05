package com.np.kisanthapa.guideforpubgmobile.ui.attachment.ui.scope.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.np.kisanthapa.guideforpubgmobile.R
import com.np.kisanthapa.guideforpubgmobile.ui.attachment.ui.scope.model.ScopeModel
import com.np.kisanthapa.guideforpubgmobile.utility.UtilityObject
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_single_scope.view.*

class ScopeRecyclerAdapter(val context: Context) :
    RecyclerView.Adapter<ScopeRecyclerAdapter.ScopeViewHolder>() {

    lateinit var scopeItemClickCallback: ScopeItemClickCallback

    interface ScopeItemClickCallback {
        fun inItemClick(scopeModel: ScopeModel)
    }

    fun onSetScopeItemClickListener(scopeItemClickCallback: ScopeItemClickCallback) {
        this.scopeItemClickCallback = scopeItemClickCallback
    }

    private var scopeDataList = emptyList<ScopeModel>()

    internal fun setScopeData(scopeList: List<ScopeModel>) {
        scopeDataList = scopeList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ScopeViewHolder(
        LayoutInflater.from(parent.context)
            .inflate(
                R.layout.item_single_scope,
                parent,
                false
            )
    )

    override fun getItemCount() = scopeDataList.size

    override fun onBindViewHolder(holder: ScopeViewHolder, position: Int) {
        holder.bindView(context, scopeDataList[position], scopeItemClickCallback)
    }


    class ScopeViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private var mScopeLayout: ConstraintLayout = view.scopeConstraintLayout!!
        private val mScopeName = view.scopeName
        private val mScopeImage = view.scopeImageView

        fun bindView(
            context: Context,
            scopeModel: ScopeModel,
            scopeItemClickCallback: ScopeItemClickCallback
        ) {
            mScopeName.text = scopeModel.scopeName

            mScopeLayout.setOnClickListener {
                scopeItemClickCallback.inItemClick(scopeModel)
            }

            val drawableId =
                UtilityObject
                    .getDrawableIdByName(context, scopeModel.scopeImage)

            try {
                Picasso.get()
                    .load(drawableId)
                    .into(mScopeImage)
            } catch (e: Exception) {
                mScopeImage.setImageResource(R.drawable.ic_image_error_placeholder)
            }

        }
    }
}