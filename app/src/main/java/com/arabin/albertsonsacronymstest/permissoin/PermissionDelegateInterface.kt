package com.arabin.albertsonsacronymstest.permissoin

import android.Manifest
import android.bluetooth.BluetoothAdapter

interface PermissionDelegateInterface {
    fun onPermissionAccepted(aPermissionCode: String)
    fun onPermissionDenied(aPermissionCode: String)
}