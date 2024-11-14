package com.practice.twowaydatabinding.security

import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys

object StorageManager {
    private const val FILE_NAME = "secure_prefs"
    const val USERNAME_KEY = "username"
    const val PASSWORD_KEY = "password"

    private fun getEncryptedSharedPreferences(context: Context): SharedPreferences {

        val masterKeyAlias = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC)

        return EncryptedSharedPreferences.create(
            FILE_NAME, // fileName
            masterKeyAlias, // masterKeyAlias
            context, // context
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV, // prefKeyEncryptionScheme
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM // prefvalueEncryptionScheme
        )
    }

    fun saveCredentials(context: Context, key: String, value: String) {
        val encryptedPrefs = getEncryptedSharedPreferences(context)
        encryptedPrefs.edit()
            .putString(key, value)
            .apply()
    }

    fun getCredentials(context: Context, key: String): String? {
        val encryptedPrefs = getEncryptedSharedPreferences(context)
        return encryptedPrefs.getString(key, null)
    }

    fun clearCredentials(context: Context) {
        val encryptedPrefs = getEncryptedSharedPreferences(context)
        encryptedPrefs.edit()
            .remove(USERNAME_KEY)
            .remove(PASSWORD_KEY)
            .apply()
    }
}