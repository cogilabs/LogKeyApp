package fr.logkey.logkeyapp.accueil

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import fr.logkey.logkeyapp.*
import fr.logkey.logkeyapp.moncompte.*

class MonCompteActivity : AppCompatActivity() {
    lateinit var boutonVersInfosPersonnelles: TextView
    lateinit var boutonVersDocumentsDeVoyage: TextView
    lateinit var boutonVersChangementDeMotDePasse: TextView
    lateinit var boutonVersMesMoyensDePaiement: TextView
    lateinit var boutonVersMaFacture: TextView
    lateinit var boutonVersSuivreMesCommandesEnCours: TextView
    lateinit var boutonVersVoirMesCommandesTerminees: TextView
    lateinit var bellButton : ImageView
    lateinit var bagButton : ImageView
    lateinit var homeButton : ImageView
    lateinit var userButton : ImageView
    lateinit var menuButton : ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mon_compte)
        boutonVersInfosPersonnelles = findViewById(R.id.informationsPersonnelles)
        boutonVersDocumentsDeVoyage = findViewById(R.id.documentsVoyage)
        boutonVersMesMoyensDePaiement = findViewById(R.id.mesMoyensDePaiement)
        boutonVersChangementDeMotDePasse= findViewById(R.id.changementMDP)
        boutonVersMaFacture= findViewById(R.id.maFacture)
        boutonVersSuivreMesCommandesEnCours= findViewById(R.id.suivreMesCommandes)
        boutonVersVoirMesCommandesTerminees= findViewById(R.id.commandesTerminees)



        val intentVersInfosPersonnelles: Intent = Intent(this, InformationsPersonnellesActivity::class.java)
        val intentVersDocumentsDeVoyage: Intent = Intent(this, DocumentsDeVoyageActivity::class.java)
        val intentVersMesMoyensDePaiement: Intent = Intent(this, MesMoyensDePaiementActivity::class.java)
        val intentVersMaFacture: Intent = Intent(this, MaFactureActivity::class.java)
        val intentVersMesCommandesEnCours: Intent = Intent(this, MesCommandesEnCoursActivity::class.java)
        val intentVersCommandesTerminees: Intent = Intent(this, MesCommandesTermineesActivity::class.java)

        boutonVersInfosPersonnelles.setOnClickListener {
            startActivity(intentVersInfosPersonnelles)
        }
        boutonVersDocumentsDeVoyage.setOnClickListener {
            startActivity(intentVersDocumentsDeVoyage)
        }
        boutonVersMesMoyensDePaiement.setOnClickListener {
            startActivity(intentVersMesMoyensDePaiement)
        }
        boutonVersChangementDeMotDePasse.setOnClickListener {
            Toast.makeText(applicationContext,"Option indisponible pour le moment.", Toast.LENGTH_SHORT).show()
        }
        boutonVersMaFacture.setOnClickListener {
            startActivity(intentVersMaFacture)
        }
        boutonVersSuivreMesCommandesEnCours.setOnClickListener {
            startActivity(intentVersMesCommandesEnCours)
        }
        boutonVersVoirMesCommandesTerminees.setOnClickListener {
            startActivity(intentVersCommandesTerminees)
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