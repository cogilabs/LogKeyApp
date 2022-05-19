package fr.logkey.logkeyapp.ble

import android.bluetooth.BluetoothDevice
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import fr.logkey.logkeyapp.R
import fr.logkey.logkeyapp.ble.BleScanActivity.Companion.DEVICE_KEY

class TransitionCheckInActivity : AppCompatActivity() {
    private lateinit var transitionButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transition_check_in)

        transitionButton = findViewById(R.id.buttonTransition)
        val device = intent.getParcelableExtra<BluetoothDevice?>(BleScanActivity.DEVICE_KEY)

        val intent = Intent(this, DeviceDetailActivity::class.java)
        intent.putExtra(DEVICE_KEY, device)
        transitionButton.setOnClickListener {
            startActivity(intent)
        }
    }
}