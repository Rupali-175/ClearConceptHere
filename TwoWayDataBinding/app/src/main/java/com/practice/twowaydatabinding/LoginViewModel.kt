package com.practice.twowaydatabinding

import android.content.Context
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.practice.twowaydatabinding.security.StorageManager
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class LoginViewModel : ViewModel() {
    val timeStamp = MutableLiveData<Long>()
    private var _user = MutableLiveData<User>()
    private var _userName = MutableLiveData<String>()

    val user: LiveData<User> get() = _user
    val edtUserName get() = _userName

    fun updateUser() {
        _user.value = User(
            edtUserName.value.toString(),
            "Updated pwd", true, timeStamp.value!!
        )
    }

    fun saveUser(context: Context) {
        StorageManager.saveCredentials(
            context,
            StorageManager.USERNAME_KEY,
            edtUserName.value.toString()
        )
    }

    fun getUser(context: Context) : String? {
        return StorageManager.getCredentials(context, StorageManager.USERNAME_KEY)
    }
}

//  use either in util file or outside of viewmodel
@BindingAdapter("formattedDate")
fun formatDate(txtDate: TextView, timeStamp: Long?) {
    if (timeStamp != null) {
        val formatter = SimpleDateFormat(
            "yyyy-MM-dd",
            Locale.getDefault()
        ) // Customize the date format as needed
        val formattedDate = formatter.format(Date(timeStamp))
        txtDate.text = formattedDate
    } else {
        txtDate.text = "N/A" // Placeholder text if the timestamp is null
    }

}