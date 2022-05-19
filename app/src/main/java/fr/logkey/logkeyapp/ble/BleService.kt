package fr.logkey.logkeyapp.ble

import android.bluetooth.BluetoothGattCharacteristic
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup

class BleService (val name: String, val characteristics: MutableList<BluetoothGattCharacteristic>):
    ExpandableGroup<BluetoothGattCharacteristic>(name, characteristics){

}