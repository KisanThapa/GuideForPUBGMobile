package com.np.kisanthapa.guideforpubgmobile.ui.backpack

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
import com.np.kisanthapa.guideforpubgmobile.ui.backpack.adapter.BackPackRecyclerAdapter
import com.np.kisanthapa.guideforpubgmobile.ui.backpack.model.BackPackModelClass
import com.np.kisanthapa.guideforpubgmobile.ui.backpack.model.BackPackModelClassParcelable

class BagPackFragment : Fragment() {

    companion object {
        fun newInstance() = BagPackFragment()
    }

    private lateinit var backpackViewModel: BackPackViewModel

    //BagPack RecyclerView
    private lateinit var mBackPackRecyclerAdapter: BackPackRecyclerAdapter
    private lateinit var mBagPackRecyclerView: RecyclerView
    private lateinit var viewManager: RecyclerView.LayoutManager

    // Navigation Controller for further Navigation
    // and passing arguments to further
    private lateinit var mNavController: NavController


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.backpack_fragment, container, false)

        //Init Recycler View
        mBagPackRecyclerView = root.findViewById<RecyclerView>(R.id.mBagPackRecyclerView)

        //Initialize recycler view and its design here
        initRecyclerView()

        //Init View Model instance
        backpackViewModel = ViewModelProvider(this).get(BackPackViewModel::class.java)
        //Observe data change in View Model class
        backpackViewModel.backList.observe(viewLifecycleOwner, Observer { bagList ->
            bagList?.let { mBackPackRecyclerAdapter.setBagPackData(it) }
        })

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialization of NavController for passing into another fragment
        mNavController = Navigation.findNavController(view)

    }

    private fun initRecyclerView() {
        //Type of view for recycler view
//        viewManager = LinearLayoutManager(context)
        viewManager = GridLayoutManager(context, 2)

        //Init Adapter here
        mBackPackRecyclerAdapter = BackPackRecyclerAdapter(requireContext())

        //Set other parameters of recycler view here
        mBagPackRecyclerView.apply {
            setHasFixedSize(true)
            // use a grid layout manager
            layoutManager = viewManager
            // specify an viewAdapter
            adapter = mBackPackRecyclerAdapter
        }

        // Handle actions after user clicked the single item here
        mBackPackRecyclerAdapter.onSetBackPackItemClickListener(object :
            BackPackRecyclerAdapter.BackPackItemClickCallback {
            override fun inItemClick(backPackModel: BackPackModelClass) {

                val backPackParcelable: BackPackModelClassParcelable =
                    BackPackModelClassParcelable(
                        backPackModel.backPackId,
                        backPackModel.backPackName,
                        backPackModel.backPackFullName,
                        backPackModel.backPackLevel,
                        backPackModel.backPackDescription,
                        backPackModel.backPackCapacity,
                        backPackModel.backPackImageName,
                        backPackModel.backPackVariants
                    )
                val action =
                    BagPackFragmentDirections.actionNavBackpackToDetailsBackPackFragment(
                        backPackParcelable,
                        backPackModel.backPackName
                    )

                mNavController.navigate(action)
            }

        })


    }

}
