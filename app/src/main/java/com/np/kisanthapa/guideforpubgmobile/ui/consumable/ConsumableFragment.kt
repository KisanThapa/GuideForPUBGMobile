package com.np.kisanthapa.guideforpubgmobile.ui.consumable

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.np.kisanthapa.guideforpubgmobile.R
import com.np.kisanthapa.guideforpubgmobile.ui.consumable.adapter.ConsumableRecyclerAdapter
import com.np.kisanthapa.guideforpubgmobile.ui.consumable.model.ConsumableModel
import com.np.kisanthapa.guideforpubgmobile.ui.consumable.model.ConsumableModelClassParcelable
import com.np.kisanthapa.guideforpubgmobile.utility.UtilityObject.isRecyclerViewHeader


class ConsumableFragment : Fragment() {

    companion object {
        fun newInstance() = ConsumableFragment()
    }

    private lateinit var consumableViewModel: ConsumableViewModel

    //Recycler View
    private lateinit var mConsumableRecyclerView: RecyclerView

    private lateinit var mConsumableRecyclerAdapter: ConsumableRecyclerAdapter

    // Navigation Controller for further Navigation
    // and passing arguments to further
    private lateinit var mNavController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.consumable_fragment, container, false)

        mConsumableRecyclerView = root.findViewById(R.id.mConsumableRecyclerView)

        //Initialize recycler view and its design here
        initRecyclerView()

        //Init View Model instance
        consumableViewModel = ViewModelProvider(this).get(ConsumableViewModel::class.java)
        //Observe data change in View Model class
        consumableViewModel.consumableList.observe(viewLifecycleOwner, Observer { bagList ->
            bagList?.let { mConsumableRecyclerAdapter.setConsumableData(it) }
        })


        return root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialization of NavController for passing into another fragment
        mNavController = Navigation.findNavController(view)

    }

    private fun initRecyclerView() {

        //Init Adapter here
        mConsumableRecyclerAdapter = ConsumableRecyclerAdapter(requireContext())

        val viewManager = GridLayoutManager(context, 2)
        viewManager.spanSizeLookup =
            object : GridLayoutManager.SpanSizeLookup() {
                override fun getSpanSize(position: Int): Int {
                    return if (isRecyclerViewHeader(position - 1)) 2
                    else 1
                }
            }

        //Set other parameters of recycler view here
        mConsumableRecyclerView.apply {
            setHasFixedSize(true)
            // use a grid layout manager
            layoutManager = viewManager
            // specify an viewAdapter
            adapter = mConsumableRecyclerAdapter
        }


        // Handle actions after user clicked the single item here
        mConsumableRecyclerAdapter.setOnConsumableItemClickListener(object :
            ConsumableRecyclerAdapter.ConsumableItemClickCallback {
            override fun onItemClick(modelClass: ConsumableModel) {

                val consumableParcelable =
                    ConsumableModelClassParcelable(
                        modelClass.consumableId,
                        modelClass.consumableName,
                        modelClass.consumableType,
                        modelClass.consumableDescription,
                        modelClass.consumableFeature,
                        modelClass.consumableImageName
                    )

                val action =
                    ConsumableFragmentDirections.actionNavConsumableToDetailsConsumableFragment(
                        consumableParcelable,
                        modelClass.consumableName
                    )

                mNavController.navigate(action)
            }
        })
    }
}
