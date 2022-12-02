package com.arabin.albertsonsacronymstest.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavOptions
import androidx.navigation.Navigation
import com.arabin.albertsonsacronymstest.R
import com.arabin.albertsonsacronymstest.databinding.EntermacroFragmentBinding
import com.arabin.albertsonsacronymstest.retrofit.RestAPIStatus
import com.arabin.albertsonsacronymstest.retrofit.viewmodel.ResponseViewModel
import com.arabin.albertsonsacronymstest.retrofit.viewmodel.ShareDataViewModel


/**
 * An example full-screen fragment that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
class EnterMacroFragment : Fragment() {

    private lateinit var responseViewModel: ResponseViewModel
    private lateinit var shareDataViewModel: ShareDataViewModel

    private var enterMacroFragmentBinding: EntermacroFragmentBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        enterMacroFragmentBinding =
            DataBindingUtil.inflate(inflater, R.layout.entermacro_fragment, container, false)
        return enterMacroFragmentBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        responseViewModel = requireActivity().run {
            ViewModelProvider(
                requireActivity(),
                defaultViewModelProviderFactory
            )[ResponseViewModel::class.java]
        }

        enterMacroFragmentBinding?.responseViewModel = responseViewModel
        enterMacroFragmentBinding?.lifecycleOwner = viewLifecycleOwner

        shareDataViewModel = requireActivity().run {
            ViewModelProvider(
                requireActivity(),
                defaultViewModelProviderFactory
            )[ShareDataViewModel::class.java]
        }
    }

    override fun onResume() {
        super.onResume()
        responseViewModel._data?.observe(viewLifecycleOwner) {
            when (it?.status) {
                RestAPIStatus.SUCCESS -> {
                    val data = it.data
                    if (data?.size!! >0){
                        data.let { it1 -> shareDataViewModel.setResponse(it1) }
                        val navController = Navigation.findNavController(
                            requireActivity(),
                            R.id.nav_host_fragment
                        )
                        navController.navigate(
                            R.id.details_fragment,
                            null,
                            getNavigationOptions()
                        )
                    }else
                        Toast.makeText(requireContext(), "No data found", Toast.LENGTH_SHORT).show()
                }
                RestAPIStatus.ERROR -> {
                    Toast.makeText(requireContext(), "NetworkError", Toast.LENGTH_SHORT)
                        .show()
                }
                RestAPIStatus.LOADING -> {
                    Toast.makeText(
                        requireContext(),
                        it.message,
                        Toast.LENGTH_SHORT
                    ).show()
                }
                else -> {}
            }
        }

        responseViewModel.emptyString?.observe(viewLifecycleOwner) {
            if (!it.isNullOrBlank())
                Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        responseViewModel.clearViewModel()
    }

    companion object {
        fun getNavigationOptions(): NavOptions? {
            return NavOptions.Builder()
                .setLaunchSingleTop(true) // Used to prevent multiple copies of the same destination
                .setEnterAnim(androidx.navigation.ui.R.anim.nav_default_enter_anim)
                .setExitAnim(androidx.navigation.ui.R.anim.nav_default_exit_anim)
                .setPopEnterAnim(androidx.navigation.ui.R.anim.nav_default_pop_enter_anim)
                .setPopExitAnim(androidx.navigation.ui.R.anim.nav_default_pop_exit_anim)
                .build()
        }
    }

}