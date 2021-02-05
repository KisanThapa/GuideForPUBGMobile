package com.np.kisanthapa.guideforpubgmobile.ui.map.mapdetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.np.kisanthapa.guideforpubgmobile.R
import com.np.kisanthapa.guideforpubgmobile.ui.map.mapdetails.viewmodel.MapInformationViewModel
import com.np.kisanthapa.guideforpubgmobile.ui.map.mapdetails.viewmodel.MapInformationViewModelFactory
import com.np.kisanthapa.guideforpubgmobile.ui.map.model.MapInfoModelClass

class MapInformationFragment : Fragment() {

    companion object {
        fun newInstance() = MapInformationFragment()
    }

    // View Model
    private lateinit var viewModel: MapInformationViewModel

    // Views
    private lateinit var mMapInfoTextView: TextView
    private lateinit var mMapGeographyTextView: TextView
    private lateinit var mMapLootingTextView: TextView
    private lateinit var mMapTransportTextView: TextView
    private lateinit var mMapSummaryTextView: TextView
    private lateinit var mMapBackgroundTextView: TextView
    private lateinit var mMapDescriptionTextView: TextView
    private lateinit var mMapBehindStoryTextView: TextView
    private lateinit var mMapStrategyTextView: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.map_information_fragment, container, false)

        mMapInfoTextView = root.findViewById(R.id.mapInfoText)
        mMapGeographyTextView = root.findViewById(R.id.mapGeographyText)
        mMapLootingTextView = root.findViewById(R.id.mapLootingText)
        mMapTransportTextView = root.findViewById(R.id.mapTransportText)
        mMapSummaryTextView = root.findViewById(R.id.mapSummaryText)
        mMapBackgroundTextView = root.findViewById(R.id.mapBackgroundText)
        mMapDescriptionTextView = root.findViewById(R.id.mapDescriptionText)
        mMapBehindStoryTextView = root.findViewById(R.id.mapStoryText)
        mMapStrategyTextView = root.findViewById(R.id.mapStrategyText)


        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        //Getting arguments passed
        val mapId = MapInformationFragmentArgs.fromBundle(requireArguments()).mapIdArgs


        //Instantiate view model with factory
        viewModel =
            ViewModelProvider(
                this,
                MapInformationViewModelFactory(
                    requireActivity().application,
                    mapId
                )
            ).get(MapInformationViewModel::class.java)


        // Observe data here
        viewModel.mapInfo.observe(viewLifecycleOwner, Observer {
            it?.let {
                setView(it[0])
            }

        })

    }

    private fun setView(mapInfo: MapInfoModelClass) {

        mMapInfoTextView.text = mapInfo.mapGeneralInfo
        mMapGeographyTextView.text = mapInfo.mapGeography
        mMapLootingTextView.text = mapInfo.mapLooting
        mMapTransportTextView.text = mapInfo.mapTransport

        mMapSummaryTextView.text = mapInfo.mapSummary
        mMapBackgroundTextView.text = mapInfo.mapBackground
        mMapDescriptionTextView.text = mapInfo.mapDescription
        mMapBehindStoryTextView.text = mapInfo.mapBehindStory
        mMapStrategyTextView.text = mapInfo.mapStrategy

    }

}
