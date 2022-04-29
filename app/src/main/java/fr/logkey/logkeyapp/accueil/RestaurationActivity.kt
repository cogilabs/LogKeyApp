package fr.logkey.logkeyapp.accueil

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import fr.logkey.logkeyapp.MenuHambActivity
import fr.logkey.logkeyapp.R
import fr.logkey.logkeyapp.restaurant.MenuBarActivity
import fr.logkey.logkeyapp.restaurant.MenuPetitDejActivity
import fr.logkey.logkeyapp.restaurant.MenuRestaurantActivity

class RestaurationActivity : AppCompatActivity() {
    // Déclaration de bouton
    lateinit var boutonVersMenuRestaurant: Button
    lateinit var boutonVersMenuPetitDej: Button
    lateinit var boutonVersMenuBar: Button
    lateinit var bellButton: ImageView
    lateinit var bagButton: ImageView
    lateinit var homeButton: ImageView
    lateinit var userButton: ImageView
    lateinit var menuButton: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_restauration)
        //initialisation
        boutonVersMenuRestaurant = findViewById(R.id.icone_menuRestaurant)
        boutonVersMenuPetitDej = findViewById(R.id.icone_menuPetitDej)
        boutonVersMenuBar = findViewById(R.id.icone_menuBar)

        //envoi intent
        val intentVersMenuRestaurant: Intent = Intent(this, MenuRestaurantActivity::class.java)
        val intentVersMenuPetitDej: Intent = Intent(this, MenuPetitDejActivity::class.java)
        val intentVersMenuBar: Intent = Intent(this, MenuBarActivity::class.java)

        //clic bouton
        boutonVersMenuRestaurant.setOnClickListener {
            startActivity(intentVersMenuRestaurant)
        }
        boutonVersMenuPetitDej.setOnClickListener {
            startActivity(intentVersMenuPetitDej)
        }

        boutonVersMenuBar.setOnClickListener {
            startActivity(intentVersMenuBar)
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
}