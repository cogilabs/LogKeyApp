package fr.logkey.logkeyapp.ble

import android.annotation.SuppressLint
import android.bluetooth.*
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import fr.logkey.logkeyapp.CheckInActivity
import fr.logkey.logkeyapp.R
import fr.logkey.logkeyapp.accueil.AccueilActivity
import fr.logkey.logkeyapp.databinding.ActivityDeviceDetailBinding
import java.util.*


@SuppressLint("MissingPermission")
class DeviceDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDeviceDetailBinding
    private var bluetoothGatt: BluetoothGatt? = null
    private var timer: Timer? = null
    lateinit var checkInBtn : Button



    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityDeviceDetailBinding.inflate(layoutInflater)
        setContentView((binding.root))

        val device = intent.getParcelableExtra<BluetoothDevice?>(BleScanActivity.DEVICE_KEY)
        Toast.makeText(this, device?.address, Toast.LENGTH_SHORT).show()
        binding.deviceName.text = device?.name ?: "Nom inconnu"
        binding.deviceStatus.text = getString(R.string.ble_device_disconnected)
        binding.checkInBtn.isEnabled =false

        connectToDevice(device)

        checkInBtn = findViewById(R.id.checkInBtn)
        val checkIntent : Intent = Intent(this, CheckInActivity::class.java)
        checkInBtn.setOnClickListener {
            startActivity(checkIntent)
            finish()
        }
    }

    override fun onStop() {
        super.onStop()
        closeBluetoothGatt()
    }

    private fun closeBluetoothGatt() {
        bluetoothGatt?.close()
        bluetoothGatt = null
    }


    private fun connectToDevice(device: BluetoothDevice?) {
        this.bluetoothGatt = device?.connectGatt(this, true, gattCallback)
        this.bluetoothGatt?.connect()
    }

    private val gattCallback = object : BluetoothGattCallback() {
        override fun onConnectionStateChange(gatt: BluetoothGatt?, status: Int, newState: Int) {
            super.onConnectionStateChange(gatt, status, newState)
            when (newState) {
                BluetoothGatt.STATE_CONNECTED -> {
                    gatt?.discoverServices()
                    runOnUiThread { binding.deviceStatus.text = "Connected"
                        checkInBtn.isEnabled = true

                    }

                }
                BluetoothGatt.STATE_CONNECTING -> {
                    runOnUiThread { binding.deviceStatus.text = "Connection..."
                        checkInBtn.isEnabled =false }
                }
                else -> {
                    runOnUiThread { binding.deviceStatus.text = "No connection"
                        checkInBtn.isEnabled =false }
                }
            }
        }


        override fun onServicesDiscovered(gatt: BluetoothGatt?, status: Int) {
            super.onServicesDiscovered(gatt, status)



            Log.d("BluetoothLeService", "onServicesDiscovered()")
            val gattServices: List<BluetoothGattService> = gatt!!.services
            Log.d("onServicesDiscovered", "Count service: " + gattServices.size)
            for (gattService in gattServices) {
                val serviceUUID = gattService.uuid.toString()
                Log.d("onServicesDiscovered", "UUID service $serviceUUID")

                val gattChara: List<BluetoothGattCharacteristic> = gattService.characteristics
                for (gattC in gattChara) {
                    val charaUUID = gattC.uuid.toString()
                    Log.d("onServicesDiscovered", "        UUID character $charaUUID")
                }
            }
        }

        override fun onCharacteristicRead(
            gatt: BluetoothGatt?, characteristic: BluetoothGattCharacteristic?, status: Int
        ) {
            super.onCharacteristicRead(gatt, characteristic, status)
            runOnUiThread {

            }
        }

        override fun onCharacteristicWrite(
            gatt: BluetoothGatt?, characteristic: BluetoothGattCharacteristic?, status: Int
        ) {
            super.onCharacteristicWrite(gatt, characteristic, status)
        }

        override fun onCharacteristicChanged(
            gatt: BluetoothGatt?, characteristic: BluetoothGattCharacteristic?
        ) {
            super.onCharacteristicChanged(gatt, characteristic)
        }

        private fun toggleNotificationOnCharacteristic(
            gatt: BluetoothGatt,
            characteristic: BluetoothGattCharacteristic,
            enable: Boolean
        ) {
            characteristic.descriptors.forEach {
                it.value =
                    if (enable) BluetoothGattDescriptor.ENABLE_NOTIFICATION_VALUE else BluetoothGattDescriptor.DISABLE_NOTIFICATION_VALUE
                gatt.writeDescriptor(it)
            }
            gatt.setCharacteristicNotification(characteristic, enable)
            timer = Timer()
            timer?.schedule(object : TimerTask() {
                override fun run() {
                    gatt.readCharacteristic(characteristic)
                }
            }, 0, 1000)
        }


        private fun writeIntoCharacteristic(
            gatt: BluetoothGatt,
            characteristic: BluetoothGattCharacteristic
        ) {
            runOnUiThread {
                val input = EditText(this@DeviceDetailActivity)
                input.inputType = InputType.TYPE_CLASS_TEXT
                val params = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                )
                params.setMargins(16, 0, 16, 0)
                input.layoutParams = params

                AlertDialog.Builder(this@DeviceDetailActivity)
                    .setTitle("Write value")
                    .setView(input)
                    .setPositiveButton("Validate") { _, _ ->
                        characteristic.value = input.text.toString().toByteArray()
                        gatt.writeCharacteristic(characteristic)
                        gatt.readCharacteristic(characteristic)
                    }
                    .setNegativeButton("Cancel") { dialog, _ -> dialog.cancel() }
                    .create()
                    .show()
            }
        }
    }
}




