package com.np.kisanthapa.guideforpubgmobile.ui.throwable

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView

import com.np.kisanthapa.guideforpubgmobile.R
import com.np.kisanthapa.guideforpubgmobile.ui.throwable.model.ThrowableModelClassParcelable
import com.np.kisanthapa.guideforpubgmobile.utility.UtilityObject
import com.ramijemli.percentagechartview.PercentageChartView
import com.squareup.picasso.Picasso
import com.stfalcon.imageviewer.StfalconImageViewer
import ir.androidexception.datatable.DataTable
import ir.androidexception.datatable.model.DataTableHeader
import ir.androidexception.datatable.model.DataTableRow
import kotlinx.android.synthetic.main.fragment_details_helmet.*

class DetailsThrowableFragment : Fragment() {


    //Declare view which are parts fo this fragment
    private lateinit var mThrowableImageView: ImageView
    private lateinit var mThrowableFullName: TextView
    private lateinit var mThrowableDescription: TextView
    private lateinit var mThrowableUsageInstruction: TextView
    private lateinit var mThrowableStatTable: DataTable

    private lateinit var mThrowableImageCardView: CardView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.fragment_details_throwable, container, false)

        //Init View here
        mThrowableImageView = root.findViewById(R.id.mThrowableImageView)
        mThrowableFullName = root.findViewById(R.id.mThrowableFullName)
        mThrowableDescription = root.findViewById(R.id.mThrowableDescription)
        mThrowableUsageInstruction = root.findViewById(R.id.mThrowableUsageInstruction)
        mThrowableStatTable = root.findViewById(R.id.mThrowableStatTable)

        mThrowableImageCardView = root.findViewById(R.id.mThrowableImageCardView)


        return root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val throwableData =
            DetailsThrowableFragmentArgs
                .fromBundle(requireArguments())
                .throwableDataAgrs


        //Set Data in Views
        val drawableId = UtilityObject.getDrawableIdByName(
            requireContext(),
            throwableData.throwableImageName
        )
        Picasso.get()
            .load(drawableId)
            .error(R.drawable.ic_image_error_placeholder)
            .into(mThrowableImageView)

        mThrowableFullName.text = throwableData.throwableFullName
        mThrowableDescription.text = throwableData.throwableDescription
        mThrowableUsageInstruction.text = throwableData.throwableUsageInstruction

        //Stat Data table builder
        setStatsInDataTable(throwableData)


        // Image Preview
        mThrowableImageCardView.setOnClickListener {
            StfalconImageViewer.Builder(
                context,
                listOf(drawableId)
            ) { view, image ->
                Picasso.get().load(image).into(view)
            }.withTransitionFrom(mThrowableImageView)
                .withHiddenStatusBar(true)
                .build()
                .show(true)
        }

    }

    private fun setStatsInDataTable(throwableData: ThrowableModelClassParcelable) {
        // Set table header here
        val tableHeader: DataTableHeader = DataTableHeader.Builder()
            .item("Capacity", 8)
            .item("Pickup Delay", 8)
            .item("Ready Delay", 8)
            .build()

        val row1 = DataTableRow.Builder()
            .value(throwableData.throwableCapacity.toString())
            .value("${throwableData.throwablePickupDelay} ms")
            .value("${throwableData.throwableReadyDelay} ms")
            .build()

        val dataRow = arrayListOf<DataTableRow>()
        dataRow.add(row1)

        mThrowableStatTable.header = tableHeader
        mThrowableStatTable.rows = dataRow
        mThrowableStatTable.inflate(requireContext())
    }

}
