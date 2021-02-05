package com.np.kisanthapa.guideforpubgmobile.ui.throwable

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
import com.np.kisanthapa.guideforpubgmobile.ui.throwable.adapter.ThrowableRecyclerAdapter
import com.np.kisanthapa.guideforpubgmobile.ui.throwable.model.ThrowableModelClass
import com.np.kisanthapa.guideforpubgmobile.ui.throwable.model.ThrowableModelClassParcelable

class ThrowableFragment : Fragment() {

    companion object {
        fun newInstance() = ThrowableFragment()
    }

    //Declare View Model
    private lateinit var throwableViewModel: ThrowableViewModel

    private lateinit var mThrowableRecyclerAdapter: ThrowableRecyclerAdapter

    //Recycler view for Armor list
    private lateinit var mThrowableRecyclerView: RecyclerView

    // Navigation Controller for further Navigation
    // and passing arguments to further
    private lateinit var mNavController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.throwable_fragment, container, false)

        throwableViewModel = ViewModelProvider(this).get(ThrowableViewModel::class.java)


        //Init recycler view here
        mThrowableRecyclerView = root.findViewById(R.id.mThrowableRecyclerView)

        //Initialize recycler view and its design here
        initRecyclerView()

        //Observe armorList in View Model
        throwableViewModel.throwableList.observe(viewLifecycleOwner, Observer {
            it?.let {
                mThrowableRecyclerAdapter.setThrowableData(it)
            }
        })


        return root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mNavController = Navigation.findNavController(view)

    }


    private fun initRecyclerView() {

        // Define view layout of recycler view here
        val viewManager = GridLayoutManager(requireContext(), 2)

        //Init Adapter here
        mThrowableRecyclerAdapter = ThrowableRecyclerAdapter(requireContext())

        //Set other parameters of recycler view here
        mThrowableRecyclerView.apply {
            setHasFixedSize(true)
            // use a grid layout manager which is defined above
            layoutManager = viewManager
            // specify an viewAdapter
            adapter = mThrowableRecyclerAdapter
        }

        // Handle actions after user clicked the single item here
        mThrowableRecyclerAdapter.setOnThrowableItemClickListener(object :
            ThrowableRecyclerAdapter.ThrowableItemClickCallback {
            override fun onItemClick(throwableModelClass: ThrowableModelClass) {

                val throwableParcelable = ThrowableModelClassParcelable(

                    throwableModelClass.throwableId,
                    throwableModelClass.throwableName,
                    throwableModelClass.throwableFullName,
                    throwableModelClass.throwableDescription,
                    throwableModelClass.throwableUsageInstruction,
                    throwableModelClass.throwableCapacity,
                    throwableModelClass.throwablePickupDelay,
                    throwableModelClass.throwableReadyDelay,
                    throwableModelClass.throwableImageName
                )

                val action = ThrowableFragmentDirections.actionNavThrowableToDetailsThrowableFragment(
                    throwableParcelable,
                    throwableModelClass.throwableName
                )

                mNavController.navigate(action)
            }

        })
    }


}
