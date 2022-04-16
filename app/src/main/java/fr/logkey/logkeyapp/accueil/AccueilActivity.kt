package fr.logkey.logkeyapp.accueil

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import fr.logkey.logkeyapp.R

class AccueilActivity : AppCompatActivity() {
    lateinit var clickMaChambre : TextView
    lateinit var clickMaReservation : TextView
    lateinit var clickRestauration : TextView
    lateinit var clickMonHotel : TextView
    lateinit var clickMesServices : TextView
    lateinit var clickMonCompte : TextView
    lateinit var clickFaq : TextView
    lateinit var clickNotifications : TextView
    lateinit var bellButton : ImageView
    lateinit var bagButton : ImageView
    lateinit var homeButton : ImageView
    lateinit var userButton : ImageView
    lateinit var menuButton : ImageView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_accueil)

        clickMaChambre = findViewById(R.id.buttonChambre)
        clickMaReservation=findViewById(R.id.buttonReservation)
        clickRestauration = findViewById(R.id.buttonRestau)
        clickMonHotel = findViewById(R.id.buttonHotel)
        clickMesServices = findViewById(R.id.buttonServices)
        clickMonCompte = findViewById(R.id.buttonCompte)
        clickFaq = findViewById(R.id.buttonFaq)
        clickNotifications = findViewById(R.id.buttonNotifications)
        bellButton = findViewById(R.id.bell)
        bagButton = findViewById(R.id.bag)
        homeButton = findViewById(R.id.house)
        userButton = findViewById(R.id.user)
        menuButton = findViewById(R.id.menu)


        val chambreIntent : Intent =  Intent(this, MaChambreActivity::class.java)
        clickMaChambre.setOnClickListener {
            startActivity(chambreIntent)
        }
        val reservationIntent : Intent =  Intent(this, MaReservationActivity::class.java)
        clickMaReservation.setOnClickListener { startActivity(reservationIntent) }
        val restauIntent : Intent =  Intent(this, RestaurationActivity::class.java)
        clickRestauration.setOnClickListener {
            startActivity(restauIntent)
        }
        val hotelIntent : Intent =  Intent(this, MapsActivity::class.java)
        clickMonHotel.setOnClickListener {
            startActivity(hotelIntent)
        }

        val servicesIntent : Intent =  Intent(this, MesServicesMenuActivity::class.java)
        clickMesServices.setOnClickListener {
            startActivity(servicesIntent)
        }
        val compteIntent : Intent =  Intent(this, MonCompteActivity::class.java)
        clickMonCompte.setOnClickListener {
            startActivity(compteIntent)
        }
        val faqIntent : Intent =  Intent(this, FaqActivity::class.java)
        clickFaq.setOnClickListener {
            startActivity(faqIntent)
        }
        val notificationsIntent : Intent =  Intent(this, NotificationsActivity::class.java)
        clickNotifications.setOnClickListener {
            startActivity(notificationsIntent)
        }


        val intent1 = Intent(this,NotificationsActivity::class.java)
        bellButton.setOnClickListener {
            startActivity(intent1)
        }


        val intent2 = Intent(this,MaReservationActivity::class.java)
        bagButton.setOnClickListener {
            startActivity(intent2)
        }

        val intent3 = Intent(this, AccueilActivity::class.java)
        homeButton.setOnClickListener {
            startActivity(intent3)
        }


        val intent4 = Intent(this,MonCompteActivity::class.java)
        userButton.setOnClickListener {
            startActivity(intent4)
        }

        val intent5 = Intent(this,NotificationsActivity::class.java)
        menuButton.setOnClickListener {
            startActivity(intent5)
        }

    }

}