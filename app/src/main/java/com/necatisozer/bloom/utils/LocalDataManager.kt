package com.necatisozer.bloom.utils

import android.content.Context
import android.content.SharedPreferences
import androidx.compose.runtime.ProvidableCompositionLocal

class LocalDataManager {

    fun setSharedPreferenceString(context: Context, key: String?, value: String?) {
        val sharedPref: SharedPreferences = context.getSharedPreferences(context.packageName, Context.MODE_PRIVATE)
        val edit = sharedPref.edit()
        edit.putString(key, value)
        edit.apply()
    }

    fun setSharedPreferenceBoolean(context: Context, key: String?, value: Boolean?) {
        val sharedPref: SharedPreferences = context.getSharedPreferences(context.packageName, Context.MODE_PRIVATE)
        val edit = sharedPref.edit()
        edit.putBoolean(key, value!!)
        edit.apply()
    }

    fun getSharedPreferenceString(context: Context, key: String?, defaultValue: String?): String {
        return context.getSharedPreferences(context.packageName, Context.MODE_PRIVATE).getString(key, defaultValue)!!
    }

    fun getSharedPreferenceBoolean(context: Context, key: String?, defaultValue: Boolean?): Boolean {
        return context.getSharedPreferences(context.packageName, Context.MODE_PRIVATE).getBoolean(key, defaultValue!!)
    }

    companion object {
        val instance = LocalDataManager()
    }

}