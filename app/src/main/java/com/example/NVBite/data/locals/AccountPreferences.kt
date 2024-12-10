package com.example.NVBite.data.locals

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.NVBite.data.models.GetProfileResponse
import kotlinx.coroutines.flow.map

class AccountPreferences private constructor(private val dataStore: DataStore<Preferences>) {
    fun getToken() = dataStore.data.map { it[TOKEN_PREFERENCES] ?: preferencesDefaultValue }

    fun getProfilePic() =
        dataStore.data.map { it[PROFILE_PIC_PREFERENCES] ?: preferencesDefaultValue }

    fun getName() = dataStore.data.map { it[NAME_PREFERENCES] ?: preferencesDefaultValue }
    fun getEmail() = dataStore.data.map { it[EMAIL_PREFERENCES] ?: preferencesDefaultValue }
    fun getPassword() = dataStore.data.map { it[PASSWORD_PREFERENCES] ?: preferencesDefaultValue }
    fun getPhone() = dataStore.data.map { it[PHONE_PREFERENCES] ?: preferencesDefaultValue }

    suspend fun saveToken(
        token: String
    ) {
        dataStore.edit { prefs ->
            prefs[TOKEN_PREFERENCES] = token
        }
    }

    suspend fun saveImageProfile(
        imageProfile: String
    ) {
        dataStore.edit { prefs ->
            prefs[PROFILE_PIC_PREFERENCES] = imageProfile
        }
    }

    suspend fun saveProfileInfo(
        profileInfo: GetProfileResponse
    ) {
        dataStore.edit { prefs ->
            prefs[PROFILE_PIC_PREFERENCES] =
                if (profileInfo.photoUrl?.lowercase() == "not yet provided") preferencesDefaultValue else profileInfo.photoUrl
                    ?: preferencesDefaultValue
            prefs[EMAIL_PREFERENCES] = profileInfo.email ?: preferencesDefaultValue
            prefs[NAME_PREFERENCES] = profileInfo.name ?: preferencesDefaultValue
            prefs[PHONE_PREFERENCES] = profileInfo.phoneNumber ?: preferencesDefaultValue
            prefs[PASSWORD_PREFERENCES] = profileInfo.password ?: preferencesDefaultValue
        }
    }


    suspend fun clearPreferences() {
        dataStore.edit { prefs ->
            prefs.clear()
        }
    }

    companion object {
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "account_preferences")

        private val TOKEN_PREFERENCES = stringPreferencesKey("token_preferences")
        private val PROFILE_PIC_PREFERENCES = stringPreferencesKey("profile_pic_preferences")
        private val NAME_PREFERENCES = stringPreferencesKey("name_preferences")
        private val EMAIL_PREFERENCES = stringPreferencesKey("email_preferences")
        private val PASSWORD_PREFERENCES = stringPreferencesKey("password_preferences")
        private val PHONE_PREFERENCES = stringPreferencesKey("phone_preferences")

        const val preferencesDefaultValue: String = "preferences_default_value"

        @Volatile
        private var INSTANCE: AccountPreferences? = null

        fun getInstance(context: Context) = INSTANCE ?: synchronized(this) {
            val instance = AccountPreferences(context.dataStore)
            INSTANCE = instance
            instance
        }
    }
}