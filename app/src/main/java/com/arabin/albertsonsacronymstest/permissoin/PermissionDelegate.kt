package com.arabin.albertsonsacronymstest.permissoin

import android.Manifest
import android.app.Activity
import android.bluetooth.BluetoothAdapter
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.core.app.ActivityCompat

const val requestCode = "requestCode"


class PermissionDelegate(private val aPermissionDlgInterface: PermissionDelegateInterface,
                        private var launcher: ActivityResultLauncher<Intent>,
                        private val context: Context) {


    fun checkPermission(aPermissionType: String) {
        when (aPermissionType) {
            PERMISSIONS.REQUEST_BLUETOOTH_ENABLE.aPermissionType -> {
                handleBluetoothEnablePermission()
            }
            PERMISSIONS.REQUEST_BLUETOOTH_CONNECT.aPermissionType -> {
                handleBlueToothConnectPermission()
            }
        }
    }

    private fun handleBlueToothConnectPermission() {
/*        val pairedDevices = if (ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.BLUETOOTH_CONNECT
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                checkPermission(Manifest.permission.BLUETOOTH_CONNECT)
            } else {

            }
        } else {

        }
        pairAdapterAndManager.second?.bondedDevices*/
    }

    private fun handleBluetoothEnablePermission() {
        val intent = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
        intent.putExtra(requestCode, PERMISSIONS.REQUEST_BLUETOOTH_ENABLE)
        launcher.launch(intent)
    }

    fun handlePermissionResult(result: ActivityResult){
        val extras = result.data?.extras
        if (result.resultCode == Activity.RESULT_OK) {
            if (extras?.getString(requestCode)
                    ?.equals(PERMISSIONS.REQUEST_BLUETOOTH_ENABLE.aPermissionType) == true
            ) {
                aPermissionDlgInterface.onPermissionAccepted(
                    PERMISSIONS
                        .REQUEST_BLUETOOTH_ENABLE.aPermissionType)
            }
        }else{
            extras?.getString(requestCode)
                ?.let {aPermissionDlgInterface.onPermissionDenied(it) }
        }
    }

    enum class PERMISSIONS(val aPermissionType: String) {
        REQUEST_BLUETOOTH_ENABLE(aPermissionType = BluetoothAdapter.ACTION_REQUEST_ENABLE),
        REQUEST_BLUETOOTH_CONNECT(aPermissionType = Manifest.permission.BLUETOOTH_CONNECT)
    }
}