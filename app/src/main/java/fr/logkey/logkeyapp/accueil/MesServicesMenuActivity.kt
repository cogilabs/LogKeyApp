package fr.logkey.logkeyapp.accueil

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import fr.logkey.logkeyapp.services.MenuPressingActivity
import fr.logkey.logkeyapp.services.MenuRoomServiceActivity
import fr.logkey.logkeyapp.R
import fr.logkey.logkeyapp.services.MenuTaxiActivity


class MesServicesMenuActivity : AppCompatActivity() {

    lateinit var boutonVersRoomService: Button
    lateinit var boutonVersPressing: Button
    lateinit var boutonVersTaxi: Button
    lateinit var bellButton : ImageView
    lateinit var bagButton : ImageView
    lateinit var homeButton : ImageView
    lateinit var userButton : ImageView
    lateinit var menuButton : ImageView

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mes_services_menu)
        //initialisation
        boutonVersRoomService = findViewById(R.id.icone_roomService)
        boutonVersPressing = findViewById(R.id.icone_pressing)
        boutonVersTaxi = findViewById(R.id.icone_taxi)


        //envoi intent
        val intentVersRoomService : Intent =  Intent(this, MenuRoomServiceActivity::class.java)
        val intentVersPressing : Intent =  Intent(this, MenuPressingActivity::class.java)
        val intentVersTaxi : Intent =  Intent(this, MenuTaxiActivity::class.java)


        //clic bouton
        boutonVersRoomService.setOnClickListener {
            startActivity(intentVersRoomService)
        }
        boutonVersPressing.setOnClickListener {
            startActivity(intentVersPressing)
        }
        boutonVersTaxi.setOnClickListener {
            startActivity(intentVersTaxi)
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

        val intent5 = Intent(this, NotificationsActivity::class.java)
        menuButton.setOnClickListener {
            startActivity(intent5)
        }
    }
}