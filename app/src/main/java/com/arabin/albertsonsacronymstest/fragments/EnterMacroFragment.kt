package com.arabin.albertsonsacronymstest.fragments

import android.Manifest
import android.app.Activity
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothManager
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavOptions
import androidx.navigation.Navigation
import com.arabin.albertsonsacronymstest.R
import com.arabin.albertsonsacronymstest.databinding.EntermacroFragmentBinding
import com.arabin.albertsonsacronymstest.permissoin.PermissionDelegate
import com.arabin.albertsonsacronymstest.permissoin.PermissionDelegateInterface
import com.arabin.albertsonsacronymstest.permissoin.requestCode
import com.arabin.albertsonsacronymstest.services.MyService
import com.arabin.albertsonsacronymstest.viewmodel.ResponseViewModel
import com.arabin.albertsonsacronymstest.viewmodel.ShareDataViewModel
import com.arabin.retrofitmodule.retrofit.RestApiHelper.RestAPIStatus


/**
 * An example full-screen fragment that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
class EnterMacroFragment : Fragment(), View.OnClickListener, PermissionDelegateInterface {

    private lateinit var responseViewModel: ResponseViewModel
    private lateinit var shareDataViewModel: ShareDataViewModel
    private var enterMacroFragmentBinding: EntermacroFragmentBinding? = null
    private lateinit var mPermissionDelegate: PermissionDelegate


    private var resultLauncher = registerForActivityResult(
        ActivityResultContracts
            .StartActivityForResult()
    ) { result -> mPermissionDelegate.handlePermissionResult(result) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mPermissionDelegate = PermissionDelegate(this, resultLauncher, requireContext())
    }

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
        responseViewModel.init(requireContext().applicationContext)

        enterMacroFragmentBinding?.responseViewModel = responseViewModel
        enterMacroFragmentBinding?.lifecycleOwner = viewLifecycleOwner

        shareDataViewModel = requireActivity().run {
            ViewModelProvider(
                requireActivity(),
                defaultViewModelProviderFactory
            )[ShareDataViewModel::class.java]
        }

        val pairAdapterAndManager = getBluetoothMangerAndAdapter()

        setUpBluetooth(pairAdapterAndManager)
        showDevices(pairAdapterAndManager)
        enterMacroFragmentBinding?.service?.setOnClickListener(this)

    }

    private fun getBluetoothMangerAndAdapter(): Pair<BluetoothManager?, BluetoothAdapter?> {
        val bluetoothManager = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requireContext().getSystemService(BluetoothManager::class.java)
        } else {
            requireContext().getSystemService(Context.BLUETOOTH_SERVICE) as BluetoothManager
        }

        val bluetoothAdapter: BluetoothAdapter? = bluetoothManager.adapter
        if (bluetoothAdapter == null)
            Toast.makeText(context, "No BlueTooth Device", Toast.LENGTH_SHORT).show()
        return Pair(bluetoothManager, bluetoothAdapter)
    }

    private fun showDevices(pairAdapterAndManager: Pair<BluetoothManager?, BluetoothAdapter?>) {
        enterMacroFragmentBinding?.bluetoothDevices?.setOnClickListener(this)
    }


    private fun setUpBluetooth(pairAdapterAndManager: Pair<BluetoothManager?, BluetoothAdapter?>) {
        enterMacroFragmentBinding?.bluetooth?.tag = pairAdapterAndManager
        enterMacroFragmentBinding?.bluetooth?.setOnClickListener(this)
    }


    override fun onResume() {
        super.onResume()
        responseViewModel.data?.observe(viewLifecycleOwner) {
            when (it?.status) {
                RestAPIStatus.SUCCESS -> {
                    val data = it.data
                    if (data?.size!! > 0) {
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
                    } else
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

        showMacroFromDb()

    }

    private fun showMacroFromDb() {
        responseViewModel.liveMacroList?.observe(viewLifecycleOwner) {apiState->
            when(apiState.status){
                RestAPIStatus.SUCCESS->{
                    val data = apiState.data
                    Toast.makeText(requireContext(), data?.size.toString(), Toast.LENGTH_SHORT)
                        .show()
                }
                RestAPIStatus.ERROR->{
                    Toast.makeText(requireContext(), "NetworkError", Toast.LENGTH_SHORT)
                        .show()
                }
                RestAPIStatus.LOADING->{
                    Toast.makeText(
                        requireContext(),
                        apiState.message,
                        Toast.LENGTH_SHORT
                    ).show()
                }else->{}
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        responseViewModel.clearViewModel()
        val intent = Intent(requireActivity(), MyService::class.java)
        requireActivity().stopService(intent)
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

    override fun onClick(v: View?) {
        when (v) {
            enterMacroFragmentBinding?.bluetooth -> {
                val pairAdapterAndManager = v?.tag as Pair<BluetoothManager?, BluetoothAdapter?>
                if (pairAdapterAndManager.second?.isEnabled == false) {
                    mPermissionDelegate.checkPermission(PermissionDelegate.PERMISSIONS.REQUEST_BLUETOOTH_ENABLE.aPermissionType)
                }
            }
            enterMacroFragmentBinding?.bluetoothDevices -> {
                mPermissionDelegate.checkPermission(Manifest.permission.BLUETOOTH_CONNECT)
            }
            enterMacroFragmentBinding?.service ->{
                val intent = Intent(requireActivity(), MyService::class.java)
                requireActivity().startService(intent)
            }
        }
    }

    override fun onPermissionAccepted(aPermissionCode: String) {
        when (aPermissionCode) {
            PermissionDelegate.PERMISSIONS.REQUEST_BLUETOOTH_CONNECT.aPermissionType -> {

            }
            PermissionDelegate.PERMISSIONS.REQUEST_BLUETOOTH_ENABLE.aPermissionType -> {
                val pairedDevices = if (ActivityCompat.checkSelfPermission(
                        requireContext(),
                        Manifest.permission.BLUETOOTH_CONNECT
                    ) != PackageManager.PERMISSION_GRANTED
                ) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        mPermissionDelegate.checkPermission(Manifest.permission.BLUETOOTH_CONNECT)
                    } else {

                    }
                } else {

                }
//                pairAdapterAndManager.second?.bondedDevices
            }
        }
    }

    override fun onPermissionDenied(aPermissionCode: String) {

    }

}