package com.example.ageindays

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {
    private var myDatePickerBtn : Button? = null
    private var tvSelectedDate : TextView? = null
    private var tvAgeInDays : TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        myDatePickerBtn = findViewById(R.id.datePickerBtn)
        tvSelectedDate = findViewById(R.id.tvSelectedDate)
        tvAgeInDays = findViewById(R.id.tvAgeInDays)
        myDatePickerBtn?.setOnClickListener{
            ageInDaysFun()
        }

    }

    private fun ageInDaysFun() {
        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val dayOfMonth = myCalendar.get(Calendar.DAY_OF_MONTH)
        val dpd = DatePickerDialog(this, {
                _, selectedYear, selectedMonth, selectedDayOfMonth ->
            val selectedDate = "$selectedDayOfMonth/${selectedMonth+1}/$selectedYear"
            tvSelectedDate?.text = selectedDate
            val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
            val selectedDateInDateFormat = sdf.parse(selectedDate)
            val currDate = sdf.parse("$dayOfMonth/${month+1}/$year")
            currDate?.let {
                selectedDateInDateFormat?.let {
                    val diff = currDate.time - selectedDateInDateFormat.time
                    tvAgeInDays?.text = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS).toString()
                    Toast.makeText(this, "Date Picker Works", Toast.LENGTH_LONG).show()
                }
            }
        },
        year,
        month,
        dayOfMonth)
        dpd.datePicker.maxDate = System.currentTimeMillis() - 86400000
        dpd.show()
    }
}