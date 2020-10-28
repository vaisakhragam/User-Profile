package com.example.userprofile.ui.main

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.content.Context
import android.os.Build
import android.widget.DatePicker
import androidx.annotation.RequiresApi
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.Period
import java.util.*

class MainViewModel : ViewModel() {
    // TODO: Implement the ViewModel

    var cal = Calendar.getInstance()
    val firstname: MutableLiveData<String> = MutableLiveData()
    val lastname: MutableLiveData<String> = MutableLiveData()

    val birthdate: MutableLiveData<String> = MutableLiveData()
    val birthdayFormat: MutableLiveData<String> = MutableLiveData()
    fun showDatePickerDialog(context: Context) {
        // create an OnDateSetListener
        val dateSetListener = object : DatePickerDialog.OnDateSetListener {
            @RequiresApi(Build.VERSION_CODES.O)
            override fun onDateSet(
                    view: DatePicker, year: Int, monthOfYear: Int,
                    dayOfMonth: Int
            ) {
                cal.set(Calendar.YEAR, year)
                cal.set(Calendar.MONTH, monthOfYear)
                cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                val myFormat = "dd/MM/yyyy" // mention the format you need
                val sdf = SimpleDateFormat(myFormat, Locale.US)
                birthdate.value = sdf.format(cal.time)


                val selected = LocalDate.of(year, monthOfYear, dayOfMonth)
                val now = LocalDate.now()
                val diff = Period.between(selected, now)

                //println("${diff.years} years , ${diff.months} months, ${diff.days} days.")
                birthdayFormat.value = "${diff.years} years , ${diff.months} months, ${diff.days} days."
            }
        }

        // when you click on the button, show DatePickerDialog that is set with OnDateSetListener

        DatePickerDialog(
                context!!,
                AlertDialog.THEME_HOLO_LIGHT,
                dateSetListener,
                // set DatePickerDialog to point to today's date when it loads up
                cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH),
                cal.get(Calendar.DAY_OF_MONTH)
        ).show()


    }
}