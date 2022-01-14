package com.mohitsingh.ageinminuteskotlin

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Change title text of main activity
        supportActionBar?.title = "Age In Minutes"

        btnDatePicker.setOnClickListener {
            showDatePicker()
        }

    }

    private fun showDatePicker() {
        // Create instance of calendar to get current year, month and date for date picker dialog
        val currentDate = Calendar.getInstance()
        val year = currentDate.get(Calendar.YEAR)
        val month = currentDate.get(Calendar.MONTH)
        val day = currentDate.get(Calendar.DATE)

        // Create date picker dialog
        val datePickerDialog = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { datePicker, selectedYear, selectedMonth, selectedDay ->
            // Set text of selected date and set it's visibility to visible
            selectedDateTV.text = "$selectedDay/${selectedMonth + 1}/$selectedYear"
            selectedDateTV.visibility = View.VISIBLE

            // Create instance of simple date format to parse selected date
            val simpleDateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)

            // parse selected date and calculated minutes from it
            val selectedDate = simpleDateFormat.parse(selectedDateTV.text.toString())
            val selectedDateInMinutes = selectedDate!!.time / 60000

            // parse current date and calculated minutes from it
            val currentDate = simpleDateFormat.parse(simpleDateFormat.format(System.currentTimeMillis()))
            val currentDateInMinutes = currentDate!!.time / 60000

            // find difference and set it in minutes text view and set it's visibility to visible.
            val differenceInMinutes = currentDateInMinutes - selectedDateInMinutes
            minutesTV.text = differenceInMinutes.toString()
            minutesTV.visibility = View.VISIBLE
        }, year, month, day)

        // set max date to today's date in date picker dialog and show it.
        datePickerDialog.datePicker.maxDate = Date().time - 86400000
        datePickerDialog.show()
    }

}
