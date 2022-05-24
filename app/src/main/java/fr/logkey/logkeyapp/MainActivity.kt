package fr.logkey.logkeyapp

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging
import fr.logkey.logkeyapp.ui.login.EmailPasswordActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        goToLogin("")


    }
    private  fun goToLogin(login: String){
        val intent = Intent(this, EmailPasswordActivity::class.java)
        intent.putExtra("login", login)
        startActivity(intent)
        finishAffinity()
    }

}
