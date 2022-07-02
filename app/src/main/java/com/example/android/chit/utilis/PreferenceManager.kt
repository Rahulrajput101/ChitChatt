package com.example.android.chit.utilis

import android.content.Context
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException


class PreferenceManager(val context: Context) {

    private object PreferenceKeys {
        val KEY_AUTH = stringPreferencesKey("key_auth")

    }


    val Context.dataStore: DataStore<androidx.datastore.preferences.core.Preferences>
            by preferencesDataStore(name = Constants.PREFERENCE_NAME)


    suspend fun saveToDataStore(name: String) {
        context.dataStore.edit { preference ->
            preference[PreferenceKeys.KEY_AUTH] = name

        }
    }


    val readFromDataStore: Flow<String> = context.dataStore.data
        .catch { exception ->
            if (exception is IOException) {
                Log.d("DataStore", exception.message.toString())
                emit(emptyPreferences())
            } else {
                throw exception
            }

        }.map { prefernces ->
            val value = prefernces[PreferenceKeys.KEY_AUTH] ?: " none"
            value

        }

}