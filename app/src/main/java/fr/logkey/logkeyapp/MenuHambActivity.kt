package fr.logkey.logkeyapp


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import fr.logkey.logkeyapp.ui.login.EmailPasswordActivity

lateinit var contactButton : TextView
lateinit var mentionButton : TextView
lateinit var deconnexionButton : TextView

class MenuHambActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_hamb)
        auth = Firebase.auth


        contactButton = findViewById(R.id.contact)
        mentionButton = findViewById(R.id.mentionsLeg)
        deconnexionButton = findViewById(R.id.deconnexion)


        val contactIntent = Intent(this,ContactActivity::class.java)
        val mentionIntent = Intent(this,MentionsLegalesActivity::class.java)




        contactButton.setOnClickListener {
            startActivity(contactIntent)
        }

        mentionButton.setOnClickListener {
            startActivity(mentionIntent)
        }

        deconnexionButton.setOnClickListener {

            auth.signOut()
            val logoutIntent = Intent(this, EmailPasswordActivity::class.java)
            logoutIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(logoutIntent)

            }

        }
    }
