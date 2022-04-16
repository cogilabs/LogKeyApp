package fr.logkey.logkeyapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class BluetoothActivity : AppCompatActivity() {
    lateinit var boutonGoBlePage : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bluetooth)

        boutonGoBlePage = findViewById(R.id.checkIn)
        val intent = Intent(this, CheckInActivity::class.java)

        boutonGoBlePage.setOnClickListener{
            startActivity(intent)
        }
    }
}