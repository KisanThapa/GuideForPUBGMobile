package com.np.kisanthapa.guideforpubgmobile.ui.helmet

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView

import com.np.kisanthapa.guideforpubgmobile.R
import com.np.kisanthapa.guideforpubgmobile.ui.helmet.model.HelmetModelClass
import com.np.kisanthapa.guideforpubgmobile.ui.helmet.model.HelmetModelClassParcelable
import com.np.kisanthapa.guideforpubgmobile.utility.UtilityObject
import com.ramijemli.percentagechartview.PercentageChartView
import com.ramijemli.percentagechartview.callback.ProgressTextFormatter
import com.squareup.picasso.Picasso
import com.stfalcon.imageviewer.StfalconImageViewer
import ir.androidexception.datatable.DataTable
import ir.androidexception.datatable.model.DataTableHeader
import ir.androidexception.datatable.model.DataTableRow


class DetailsHelmetFragment : Fragment() {


    //Declare view which are parts fo this fragment
    private lateinit var mHelmetDamageReductionProgressBar: PercentageChartView
    private lateinit var mHelmetImageView: ImageView
    private lateinit var mHelmetFullName: TextView
    private lateinit var mHelmetDescription: TextView
    private lateinit var mHelmetStatTable: DataTable

    private lateinit var mHelmetImageCardView: CardView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.fragment_details_helmet, container, false)

        //Init View here
        mHelmetDamageReductionProgressBar =
            root.findViewById(R.id.mHelmetDamageReductionProgressBar)
        mHelmetImageView = root.findViewById(R.id.mHelmetImageView)
        mHelmetFullName = root.findViewById(R.id.mHelmetFullName)
        mHelmetDescription = root.findViewById(R.id.mHelmetDescription)
        mHelmetStatTable = root.findViewById(R.id.mHelmetStatTable)

        mHelmetImageCardView = root.findViewById(R.id.mHelmetImageCardView)


        return root
    }

    private var helmetDamageReduction: Int? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // getting arguments which are passed from the previous fragment
        val args = DetailsHelmetFragmentArgs.fromBundle(requireArguments())

        val helmetData = args.helmetDataArgs


        //Set Data in Views

        //Get Helmet Damage Reduction
        helmetDamageReduction = helmetData.helmetDamageReduction

        val progressData = helmetDamageReduction!!

        mHelmetDamageReductionProgressBar.setTextFormatter(
            ProgressTextFormatter {
                "$helmetDamageReduction %"
            }
        )

        mHelmetDamageReductionProgressBar.setProgress(progressData.toFloat(), true)

        // Set image
        val drawableId = UtilityObject.getDrawableIdByName(
            requireContext(),
            helmetData.helmetImageName
        )
        Picasso.get()
            .load(drawableId)
            .error(R.drawable.ic_image_error_placeholder)
            .into(mHelmetImageView)

        mHelmetFullName.text = helmetData.helmetFullName
        mHelmetDescription.text = helmetData.helmetDescription

        //Stat Data table builder
        setStatsInDataTable(helmetData)


        // Image Preview
        mHelmetImageCardView.setOnClickListener {
            StfalconImageViewer.Builder(
                context,
                listOf(drawableId)
            ) { view, image ->
                Picasso.get().load(image).into(view)
            }.withTransitionFrom(mHelmetImageView)
                .withHiddenStatusBar(true)
                .build()
                .show(true)

        }

    }

    override fun onResume() {
        super.onResume()
        mHelmetDamageReductionProgressBar.setTextFormatter(ProgressTextFormatter {
            "$helmetDamageReduction %"
        })

    }

    private fun setStatsInDataTable(helmetData: HelmetModelClassParcelable) {
        val tableHeader: DataTableHeader = DataTableHeader.Builder()
            .item("Type", 8)
            .item("Damage Reduction", 8)
            .item("Durability", 8)
            .build()

        val row1 = DataTableRow.Builder()
            .value("Helmet")
            .value("${helmetData.helmetDamageReduction}%")
            .value(helmetData.helmetDurability.toString())
            .build()

        val dataRow = arrayListOf<DataTableRow>()
        dataRow.add(row1)

        mHelmetStatTable.header = tableHeader
        mHelmetStatTable.rows = dataRow
        mHelmetStatTable.inflate(requireContext())
    }

}
