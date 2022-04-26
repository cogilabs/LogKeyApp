package fr.logkey.logkeyapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

lateinit var paramButton : TextView
lateinit var contactButton : TextView
lateinit var mentionButton : TextView
lateinit var deconnexionButton : TextView

class MenuHambActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_hamb)


        //   paramButton = findViewById(R.id.parametres)
        //  contactButton = findViewById(R.id.contact)
        //   mentionButton = findViewById(R.id.mentionsLeg)
        //   deconnexionButton = findViewById(R.id.deconnexion)

        val paramIntent = Intent(this,BluetoothActivity::class.java)
        val contactIntent = Intent(this,BluetoothActivity::class.java)
        val mentionIntent = Intent(this,BluetoothActivity::class.java)
        val deconnexionIntent = Intent(this,BluetoothActivity::class.java)

        /*     paramButton.setOnClickListener {
                 startActivity(paramIntent)
             }

             contactButton.setOnClickListener {
                  startActivity(contactIntent)
             }

             mentionButton.setOnClickListener {
                 startActivity(mentionIntent)
             }

             deconnexionButton.setOnClickListener {
                 startActivity(deconnexionIntent)
             }*/
    }
}