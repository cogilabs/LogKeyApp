package fr.logkey.logkeyapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    lateinit var boutonVersRestauration : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        boutonVersRestauration = findViewById(R.id.btn_vers_restauration)


        //envoi intent
        val intentVersRestauration : Intent =  Intent(this,MainActivity::class.java)

        //clic bouton
        boutonVersRestauration.setOnClickListener {
            startActivity(intentVersRestauration)
        }


    }

}