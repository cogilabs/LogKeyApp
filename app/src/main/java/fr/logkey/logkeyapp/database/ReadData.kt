package fr.logkey.logkeyapp.database

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import fr.logkey.logkeyapp.databinding.ActivityInformationsPersonnellesBinding


class ReadData : AppCompatActivity() {

    private lateinit var binding: ActivityInformationsPersonnellesBinding
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInformationsPersonnellesBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }


}

