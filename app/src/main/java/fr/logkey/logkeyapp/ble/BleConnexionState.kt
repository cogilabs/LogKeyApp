package fr.logkey.logkeyapp.ble

import android.bluetooth.BluetoothProfile
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.StringRes
import fr.logkey.logkeyapp.R

enum class BleConnexionState (val state: Int,@StringRes val text: Int) {
    STATE_CONNECTING(BluetoothProfile.STATE_CONNECTING, R.string.ble_device_status_connecting),
    STATE_CONNECTED(BluetoothProfile.STATE_CONNECTED, R.string.ble_device_connected),
    STATE_DISCONNECTED(BluetoothProfile.STATE_DISCONNECTED, R.string.ble_device_disconnected),
    STATE_DISCONNECTING(BluetoothProfile.STATE_DISCONNECTING, R.string.ble_device_status_disconnecting);

    companion object {
        fun getBLEConnexionStateFromState(state: Int) = values().firstOrNull {
            it.state == state
        }
    }
}