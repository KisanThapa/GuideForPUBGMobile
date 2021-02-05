package com.np.kisanthapa.guideforpubgmobile.ui.attachment.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.np.kisanthapa.guideforpubgmobile.R
import com.np.kisanthapa.guideforpubgmobile.ui.attachment.model.AttachmentModelClass
import com.np.kisanthapa.guideforpubgmobile.utility.UtilityObject
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_single_attachment.view.*

class AttachmentRecyclerAdapter(private val context: Context) :
    RecyclerView.Adapter<AttachmentRecyclerAdapter.AttachmentViewHolder>() {

    // attachment data list
    private var attachmentDataList: List<AttachmentModelClass> = emptyList()

    //Click listener
    interface AttachmentItemClickCallback {
        fun onAttachmentItemClick(attachmentData: AttachmentModelClass)
    }

    private lateinit var attachmentItemClickCallback: AttachmentItemClickCallback
    fun setOnAttachmentItemClickListener(attachmentItemClickCallback: AttachmentItemClickCallback) {
        this.attachmentItemClickCallback = attachmentItemClickCallback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = AttachmentViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_single_attachment, parent, false)
    )

    internal fun setAttachmentData(data: List<AttachmentModelClass>) {
        attachmentDataList = data
        notifyDataSetChanged()
    }

    override fun getItemCount() = attachmentDataList.size

    override fun onBindViewHolder(holder: AttachmentViewHolder, position: Int) {

        holder.bindView(context, attachmentDataList[position], attachmentItemClickCallback)
    }


    class AttachmentViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val attachmentConstraintLayout = view.attachmentConstraintLayout
        private val attachmentImageView = view.attachmentImageView
        private val attachmentName = view.attachmentName
        private val attachmentInfo = view.attachmentInfo


        fun bindView(
            context: Context,
            attachmentData: AttachmentModelClass,
            itemClickCallback: AttachmentItemClickCallback
        ) {

            attachmentName.text = attachmentData.attachName
            attachmentInfo.text = attachmentData.attachInfo

            // Set images
            Picasso.get()
                .load(
                    UtilityObject.getDrawableIdByName(context, attachmentData.attachImage)
                )
                .into(attachmentImageView)

            attachmentConstraintLayout.setOnClickListener {
                itemClickCallback.onAttachmentItemClick(attachmentData)
            }

        }
    }
}