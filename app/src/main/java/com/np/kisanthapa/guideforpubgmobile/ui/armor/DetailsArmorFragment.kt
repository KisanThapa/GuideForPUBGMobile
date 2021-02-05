package com.np.kisanthapa.guideforpubgmobile.ui.armor

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
import com.np.kisanthapa.guideforpubgmobile.ui.armor.model.ArmorModelClassParcelable
import com.np.kisanthapa.guideforpubgmobile.utility.UtilityObject
import com.ramijemli.percentagechartview.PercentageChartView
import com.ramijemli.percentagechartview.callback.ProgressTextFormatter
import com.squareup.picasso.Picasso
import com.stfalcon.imageviewer.StfalconImageViewer
import ir.androidexception.datatable.DataTable
import ir.androidexception.datatable.model.DataTableHeader
import ir.androidexception.datatable.model.DataTableRow

class DetailsArmorFragment : Fragment() {


    //Declare view which are parts fo this fragment
    private lateinit var mArmorDamageReductionProgressBar: PercentageChartView
    private lateinit var mArmorImageView: ImageView
    private lateinit var mArmorFullName: TextView
    private lateinit var mArmorDescription: TextView
    private lateinit var mArmorCapacityDescription: TextView
    private lateinit var mArmorStatTable: DataTable

    private lateinit var mArmorImageCardView: CardView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.fragment_details_armor, container, false)

        //Init View here
        mArmorDamageReductionProgressBar =
            root.findViewById(R.id.mArmorDamageReductionProgressBar)
        mArmorImageView = root.findViewById(R.id.mArmorImageView)
        mArmorFullName = root.findViewById(R.id.mArmorFullName)
        mArmorDescription = root.findViewById(R.id.mArmorDescription)
        mArmorCapacityDescription = root.findViewById(R.id.mArmorCapacityDescription)
        mArmorStatTable = root.findViewById(R.id.mArmorStatTable)

        mArmorImageCardView = root.findViewById(R.id.mArmorImageCardView)

        return root

    }

    private var armorDamageReduction: Int? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // getting arguments which are passed from the previous fragment
        val args = DetailsArmorFragmentArgs.fromBundle(requireArguments())

        val armorData = args.armorDataAgrs

        //Set Data in Views

        //Get Helmet Damage Reduction
        armorDamageReduction = armorData.armorDamageReduction

        val progressData = armorDamageReduction!!

        mArmorDamageReductionProgressBar.setTextFormatter(
            ProgressTextFormatter {
                "$armorDamageReduction %"
            }
        )

        mArmorDamageReductionProgressBar.setProgress(progressData.toFloat(), true)


        //Set Image
        val drawableId = UtilityObject.getDrawableIdByName(
            requireContext(),
            armorData.armorImageName
        )
        Picasso.get()
            .load(
                drawableId
            )
            .error(R.drawable.ic_image_error_placeholder)
            .into(mArmorImageView)

        mArmorFullName.text = armorData.armorFullName
        mArmorDescription.text = armorData.armorDescription
        mArmorCapacityDescription.text = armorData.armorCapacityDescription

        //Stat Data table builder
        setStatsInDataTable(armorData)


        // Image Preview
        mArmorImageCardView.setOnClickListener {
            StfalconImageViewer.Builder(
                context,
                listOf(drawableId)
            ) { view, image ->
                Picasso.get().load(image).into(view)
            }.withTransitionFrom(mArmorImageView)
                .withHiddenStatusBar(true)
                .build()
                .show(true)

        }


    }

    private fun setStatsInDataTable(armorData: ArmorModelClassParcelable) {
        val tableHeader: DataTableHeader = DataTableHeader.Builder()
            .item("Capacity Extension", 8)
            .item("Damage Reduction", 8)
            .item("Durability", 8)
            .build()

        val row1 = DataTableRow.Builder()
            .value("+${armorData.armorCapacityExtension}")
            .value("${armorData.armorDamageReduction}%")
            .value(armorData.armorDurability.toString())
            .build()

        val dataRow = arrayListOf<DataTableRow>()
        dataRow.add(row1)

        mArmorStatTable.header = tableHeader
        mArmorStatTable.rows = dataRow
        mArmorStatTable.inflate(requireContext())

    }

    override fun onResume() {
        super.onResume()
        mArmorDamageReductionProgressBar.setTextFormatter(ProgressTextFormatter {
            "$armorDamageReduction %"
        })

    }

}
