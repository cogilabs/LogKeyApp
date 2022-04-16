package fr.logkey.logkeyapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import fr.logkey.logkeyapp.ui.login.LoginActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        goToLogin("")

    }
    private  fun goToLogin(login: String){
        val intent = Intent(this, LoginActivity::class.java)
        intent.putExtra("login", login)
        startActivity(intent)
    }
}
