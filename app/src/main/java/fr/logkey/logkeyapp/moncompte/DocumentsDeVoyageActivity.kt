package fr.logkey.logkeyapp.moncompte

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.Nullable
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.EventListener
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import fr.logkey.logkeyapp.MenuHambActivity
import fr.logkey.logkeyapp.R
import fr.logkey.logkeyapp.accueil.AccueilActivity
import fr.logkey.logkeyapp.accueil.MaReservationActivity
import fr.logkey.logkeyapp.accueil.MonCompteActivity
import fr.logkey.logkeyapp.accueil.NotificationsActivity

class DocumentsDeVoyageActivity : AppCompatActivity() {

    lateinit var bellButton : ImageView
    lateinit var bagButton : ImageView
    lateinit var homeButton : ImageView
    lateinit var userButton : ImageView
    lateinit var menuButton : ImageView

    lateinit var userIDcard : TextView

    //Initialisation variable pour firebase firestore
    var db: FirebaseFirestore? = FirebaseFirestore.getInstance()
    private lateinit var auth: FirebaseAuth
    private lateinit var uid : String

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_documents_de_voyage)

        bellButton = findViewById(R.id.bell)
        bagButton = findViewById(R.id.bag)
        homeButton = findViewById(R.id.house)
        userButton = findViewById(R.id.user)
        menuButton = findViewById(R.id.menu)
        userIDcard = findViewById(R.id.numeroIdentite)

        auth = FirebaseAuth.getInstance()
        uid = auth.currentUser?.uid.toString()

        //Création de variable pour la référence du doc dans la bdd
        val documentReference = db!!.collection("Users").document(uid)


        // adding snapshot listener to our document reference.
        documentReference.addSnapshotListener(object : EventListener<DocumentSnapshot?> {
            override fun onEvent(
                @Nullable value: DocumentSnapshot?,
                @Nullable error: FirebaseFirestoreException?
            ) {
                // inside the on event method.
                if (error != null) {
                    // this method is called when error is not null
                    // and we gt any error
                    // in this cas we are displaying an error message.
                    Toast.makeText(this@DocumentsDeVoyageActivity, "Error found is $error", Toast.LENGTH_SHORT)
                        .show()
                    return
                }
                if (value != null && value.exists()) {
                    // if th value from firestore is not null then we are getting
                    // our data and setting that data to our text view.
                    userIDcard.text = value.data!!["num_IDcard"].toString()

                }
            }
        })

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