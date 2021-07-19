package com.example.mywaiterapp

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_new_advert.*
import java.util.*

class NewAdvert : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_advert)

        val spinnerItems = arrayOf("Банкет")

        val adapter = ArrayAdapter(this, R.layout.spinner_dropdown_item, spinnerItems)
        spinner.adapter = adapter

        val calendar = Calendar.getInstance()
        val hour = calendar.get(Calendar.HOUR_OF_DAY) + 1
        val minutes = calendar.get(Calendar.MINUTE)
        val timePicker = TimePickerDialog(this, TimePickerDialog.OnTimeSetListener { _, h, m ->
            val time = h.toString().padStart(2, '0') + ':' + m.toString().padStart(2, '0')
            naETTime.text = time
        }, hour, minutes, true)
        naETTime.setOnClickListener {
            timePicker.show()
        }
        val timePicker2 = TimePickerDialog(this, TimePickerDialog.OnTimeSetListener { _, h, m ->
            val time = h.toString().padStart(2, '0') + ':' + m.toString().padStart(2, '0')
            naETTime2.text = time
        }, hour, minutes, true)
        naETTime2.setOnClickListener {
            timePicker2.show()
        }

        val day = calendar.get(Calendar.DAY_OF_MONTH)
        val month = calendar.get(Calendar.MONTH)
        val year = calendar.get(Calendar.YEAR)
        val dayPicker = DatePickerDialog(this, DatePickerDialog.OnDateSetListener{ _, y, m, d ->
            val date = d.toString().padStart(2, '0') + '.' + (m + 1).toString().padStart(2, '0') + '.' + y.toString().padStart(2, '0')
            naETDate.text = date
        }, year, month, day)
        naETDate.setOnClickListener{
            dayPicker.show()
        }

        naBtn.setOnClickListener {

            val newAdvertData = mapOf<String, String>(
                    "date" to naETDate.text.toString(),
                    "desc" to naETDesc.text.toString(),
                    "loc" to naETLocation.text.toString(),
                    "name" to naETName.text.toString(),
                    "pay" to naETPay.text.toString(),
                    "time" to naETTime.text.toString() + " - " + naETTime2.text.toString(),
                    "type" to spinner.selectedItem.toString(),
                    "userID" to "Андрюха емае"
            )

            // здесь есть один прекол который надо пофиксить как-нибудь, но мне лень
            // если у челика нет интернета и он кучу раз нажмет добавить, а потом включит интернет, то добавится дофигища
            FirebaseFirestore.getInstance().collection("adverts").document().set(newAdvertData)
                    .addOnSuccessListener {
                        Toast.makeText(this, "Объявление успешно добавлено", Toast.LENGTH_SHORT).show()
                    }
                    .addOnFailureListener {
                        Toast.makeText(this, "Ошибка добавления объявления", Toast.LENGTH_SHORT).show()
                    }
        }
    }
}