package com.np.kisanthapa.guideforpubgmobile.ui.backpack

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import com.np.kisanthapa.guideforpubgmobile.R
import com.np.kisanthapa.guideforpubgmobile.utility.UtilityObject
import com.ramijemli.percentagechartview.PercentageChartView
import com.ramijemli.percentagechartview.callback.ProgressTextFormatter
import com.squareup.picasso.Picasso
import com.stfalcon.imageviewer.StfalconImageViewer


class DetailsBackPackFragment : Fragment() {

    //Declare view which are parts fo this fragment
    private lateinit var mBackPackCapacityProgressBar: PercentageChartView
    private lateinit var mBackPackImageView: ImageView
    private lateinit var mBackPackFullName: TextView
    private lateinit var mBackPackDescription: TextView
    private lateinit var mBackPackCapacityDetails: TextView
    private lateinit var mBackPackVariants: TextView

    private lateinit var mBackPackImageCardView: CardView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.fragment_details_back_pack, container, false)

        //Init View here
        mBackPackCapacityProgressBar = root.findViewById(R.id.mBackPackCapacityProgressBar)
        mBackPackImageView = root.findViewById(R.id.mBackPackImageView)
        mBackPackFullName = root.findViewById(R.id.mBackPackFullName)
        mBackPackDescription = root.findViewById(R.id.mBackPackDescription)
        mBackPackCapacityDetails = root.findViewById(R.id.mBackPackCapacityDetails)
        mBackPackVariants = root.findViewById(R.id.mBackPackVariants)

        mBackPackImageCardView = root.findViewById(R.id.mBackPackImageCardView)

        return root
    }

    private var backPackCapacity: Int? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // getting arguments which are passed from the previous fragment
        val args = DetailsBackPackFragmentArgs.fromBundle(requireArguments())

        val backPackData = args.singleBackPackDetail


        //Set Data in Views

        //Getting backpack capacity
        backPackCapacity = backPackData.backPackCapacity

        val progressData = (backPackCapacity!! * 100 / 270)

        mBackPackCapacityProgressBar.setTextFormatter(ProgressTextFormatter {
            "$backPackCapacity"
        })
        mBackPackCapacityProgressBar.setProgress(progressData.toFloat(), true)


        //set images
        val drawableId = UtilityObject.getDrawableIdByName(
            requireContext(),
            backPackData.backPackImageName
        )
        Picasso.get()
            .load(drawableId)
            .error(R.drawable.ic_image_error_placeholder)
            .into(mBackPackImageView)

        mBackPackFullName.text = backPackData.backPackFullName
        mBackPackDescription.text = backPackData.backPackDescription
        mBackPackCapacityDetails.text =
            "\u2022  ${backPackData.backPackName} only = $backPackCapacity hold capacity."
        mBackPackVariants.text = backPackData.backPackVariants


        // Image Preview
        mBackPackImageCardView.setOnClickListener {
            StfalconImageViewer.Builder(
                context,
                listOf(drawableId)
            ) { view, image ->
                Picasso.get().load(image).into(view)
            }.withTransitionFrom(mBackPackImageView)
                .withHiddenStatusBar(true)
                .build()
                .show(true)
        }

    }

    override fun onResume() {
        super.onResume()
        mBackPackCapacityProgressBar.setTextFormatter(ProgressTextFormatter {
            "$backPackCapacity"
        })

    }

}
