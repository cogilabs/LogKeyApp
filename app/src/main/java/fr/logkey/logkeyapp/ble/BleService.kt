package fr.logkey.logkeyapp.ble

import android.bluetooth.BluetoothGattCharacteristic
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup

class BleService (val name: String, val characteristics: MutableList<BluetoothGattCharacteristic> ):
    ExpandableGroup<BluetoothGattCharacteristic>(name, characteristics){

}