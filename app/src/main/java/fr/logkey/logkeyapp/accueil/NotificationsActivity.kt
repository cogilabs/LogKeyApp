package fr.logkey.logkeyapp.accueil

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging
import fr.logkey.logkeyapp.*
import fr.logkey.logkeyapp.databinding.ActivityNotificationsBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

const val TOPIC = "/topics/myTopic2"

class NotificationsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNotificationsBinding
  //  private lateinit var items: ArrayList<String>

    lateinit var bellButton : ImageView
    lateinit var bagButton : ImageView
    lateinit var homeButton : ImageView
    lateinit var userButton : ImageView
    lateinit var menuButton : ImageView
    private lateinit var etToken: EditText
    private lateinit var etMessage: EditText
    private lateinit var etTitle: EditText
    private lateinit var buttonEnvoieNotif: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notifications)

        bellButton = findViewById(R.id.bell)
        bagButton = findViewById(R.id.bag)
        homeButton = findViewById(R.id.house)
        userButton = findViewById(R.id.user)
        menuButton = findViewById(R.id.menu)

        binding = ActivityNotificationsBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val categorieName = intent.getStringExtra("category")
       // binding.itemsMessage.text = categorieName


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


        FirebaseService.sharedPref = getSharedPreferences("sharedPref", MODE_PRIVATE)

        FirebaseMessaging.getInstance().token.addOnCompleteListener(
            OnCompleteListener { task ->

                if (!task.isSuccessful) {
                    Log.w("???", "Fetching FCM token failed", task.exception)
                    return@OnCompleteListener
                }
                val token = task.result

                Log.d("???", "token is $token")
            })
        FirebaseMessaging.getInstance().subscribeToTopic(TOPIC)

      /* buttonEnvoieNotif.setOnClickListener {
            val title = etTitle.text.toString()
            val message = etMessage.text.toString()
            val recipientToken = etToken.text.toString()
            if(title.isNotEmpty() && message.isNotEmpty() && recipientToken.isNotEmpty()) {
                PushNotification(
                    NotificationData(title, message),
                    recipientToken
                ).also {
                    sendNotification(it)
                }
            }
        }*/
    }
    private fun sendNotification(notification: PushNotification) = CoroutineScope(Dispatchers.IO).launch {
        try {
            val response = RetrofitInstance.api.postNotification(notification)
            if(response.isSuccessful) {
                //Log.d(TAG, "Response: ${Gson().toJson(response)}")
            } else {
                Log.e(ContentValues.TAG, response.errorBody().toString())
            }
        } catch(e: Exception) {
            Log.e(ContentValues.TAG, e.toString())
        }
    }
}

