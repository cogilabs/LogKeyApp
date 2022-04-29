package fr.logkey.logkeyapp.services

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import fr.logkey.logkeyapp.MenuHambActivity
import fr.logkey.logkeyapp.R
import fr.logkey.logkeyapp.accueil.AccueilActivity
import fr.logkey.logkeyapp.accueil.MaReservationActivity
import fr.logkey.logkeyapp.accueil.MonCompteActivity
import fr.logkey.logkeyapp.accueil.NotificationsActivity

class MenuTaxiActivity : AppCompatActivity() {
    lateinit var bellButton : ImageView
    lateinit var bagButton : ImageView
    lateinit var homeButton : ImageView
    lateinit var userButton : ImageView
    lateinit var menuButton : ImageView
    lateinit var boutonTelephoneUrgent: ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_taxi)

        boutonTelephoneUrgent = findViewById(R.id.icone_telephone)

        boutonTelephoneUrgent.setOnClickListener {
            call(boutonTelephoneUrgent)
        }
        bellButton = findViewById(R.id.bell)
        bagButton = findViewById(R.id.bag)
        homeButton = findViewById(R.id.house)
        userButton = findViewById(R.id.user)
        menuButton = findViewById(R.id.menu)

        val intent1 = Intent(this, NotificationsActivity::class.java)
        bellButton.setOnClickListener {
            startActivity(intent1)
        }


        val intent2 = Intent(this, MaReservationActivity::class.java)
        bagButton.setOnClickListener {
            startActivity(intent2)
        }

        val intent3 = Intent(this, AccueilActivity::class.java)
        homeButton.setOnClickListener {
            startActivity(intent3)
        }


        val intent4 = Intent(this, MonCompteActivity::class.java)
        userButton.setOnClickListener {
            startActivity(intent4)
        }

        val intent5 = Intent(this, MenuHambActivity::class.java)
        menuButton.setOnClickListener {
            startActivity(intent5)
        }
    }
    fun call(view: View) {
        val telephoneIntent = Intent(Intent.ACTION_DIAL)
        telephoneIntent.data = Uri.parse("tel:" + "33672240680")
        startActivity(telephoneIntent)
    }

}