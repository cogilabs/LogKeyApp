package fr.logkey.logkeyapp.accueil


import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.os.Bundle
import android.widget.DatePicker
import android.widget.ImageView
import android.widget.TimePicker
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import fr.logkey.logkeyapp.R
import kotlinx.android.synthetic.main.activity_ma_chambre.*
import java.util.*


class MaChambreActivity : AppCompatActivity(), DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {

    var day = 0
    var month =0
    var year = 0
    var hour = 0
    var minute = 0

    var savedDay = 0
    var savedMonth = 0
    var savedYear =0
    var savedHour =0
    var savedMinute = 0

    lateinit var bellButton : ImageView
    lateinit var bagButton : ImageView
    lateinit var homeButton : ImageView
    lateinit var userButton : ImageView
    lateinit var menuButton : ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ma_chambre)
        bellButton = findViewById(R.id.bell)
        bagButton = findViewById(R.id.bag)
        homeButton = findViewById(R.id.house)
        userButton = findViewById(R.id.user)
        menuButton = findViewById(R.id.menu)

        val intent1 = Intent(this,NotificationsActivity::class.java)
        bellButton.setOnClickListener {
            startActivity(intent1)
        }


        val intent2 = Intent(this,MaReservationActivity::class.java)
        bagButton.setOnClickListener {
            startActivity(intent2)
        }

        val intent3 = Intent(this,AccueilActivity::class.java)
        homeButton.setOnClickListener {
            startActivity(intent3)
        }


        val intent4 = Intent(this,MonCompteActivity::class.java)
        userButton.setOnClickListener {
            startActivity(intent4)
        }
        pickDate()
        switch1.setOnCheckedChangeListener{_, isChecked ->
            if(isChecked) {
                switch1.text = "Chambre occupée"
                Toast.makeText(this, "chambre occupée", Toast.LENGTH_LONG).show()

            }
            else {
                switch1.text = "Chambre non occupée"
                Toast.makeText(this, "chambre non occupée", Toast.LENGTH_LONG).show()
            }

        }
    }
    private fun getDateTimeCalendar(){
        val cal = Calendar.getInstance()
        day = cal.get(Calendar.DAY_OF_MONTH)
        month = cal.get(Calendar.MONTH)
        year = cal.get(Calendar.YEAR)
        hour = cal.get(Calendar.HOUR)
        minute = cal.get(Calendar.MINUTE)
    }

    private fun pickDate(){
        btnTimePicker.setOnClickListener {
            getDateTimeCalendar()
            DatePickerDialog(this, R.style.MyDatePickerStyle, this,year,month,day).show()

        }
    }
    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayofMonth: Int) {

        savedDay = dayofMonth
        savedMonth = month +1
        savedYear = year

        getDateTimeCalendar()

        TimePickerDialog (this, R.style.MyTimePickerStyle, this,hour,minute,true).show()
    }

    @SuppressLint("SetTextI18n")
    override fun onTimeSet(view: TimePicker?, hourofDay: Int, minute: Int) {

        savedHour = hourofDay
        savedMinute = minute

        texteSelectionDate.text = "$savedDay-$savedMonth-$savedYear\n " +
                " $savedHour : $savedMinute"

    }
}