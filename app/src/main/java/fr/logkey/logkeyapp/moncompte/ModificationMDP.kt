package fr.logkey.logkeyapp.moncompte

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import fr.logkey.logkeyapp.R
import fr.logkey.logkeyapp.ui.login.EmailPasswordActivity

class ModificationMDP : AppCompatActivity() {

    private val TAG = "ModificationPasswordActivity"

    // Les composants de l'interfaces
    private var eMailAddress: EditText? = null
    private var btnEnvoieMDP: Button? = null

    //Les références Firebase
    private var auth: FirebaseAuth? = null

    private fun initialisation() {
        eMailAddress = findViewById<View>(R.id.editTexteEmailChangementMDP) as EditText
        btnEnvoieMDP = findViewById<View>(R.id.btnEnvoyerMDP) as Button
        auth = FirebaseAuth.getInstance()
        btnEnvoieMDP!!.setOnClickListener { reInitialisationPassword() }
    }

    private fun reInitialisationPassword() {
        val email = eMailAddress?.text.toString()
        if (!TextUtils.isEmpty(email)) {
            auth!!
                .sendPasswordResetEmail(email)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val message = "Email envoyé. Merci de vérifier vos spams."
                        Log.d(TAG, message)
                        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
                        MAJInfoUser()
                    } else {
                        task.exception!!.message?.let { Log.w(TAG, it) }
                        Toast.makeText(this, "Aucun utilisateur trouvé avec cette adresse email.", Toast.LENGTH_SHORT).show()
                    }
                }
        } else {
            Toast.makeText(this, "Entrez votre Email", Toast.LENGTH_SHORT).show()
        }
    }

    private fun MAJInfoUser() {
        val intent = Intent(this@ModificationMDP, EmailPasswordActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_modification_mdp)
        initialisation()
    }
}