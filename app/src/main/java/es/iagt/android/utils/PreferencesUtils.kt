package es.iagt.android.utils

import android.content.SharedPreferences
import android.util.Log
import com.google.gson.GsonBuilder
import com.google.gson.JsonDeserializer
import com.google.gson.JsonPrimitive
import com.google.gson.JsonSerializer
import java.util.*
import javax.inject.Inject


@Suppress("UNCHECKED_CAST")
class PreferencesUtils
@Inject
constructor(private var mSharedPreferences: SharedPreferences) {

    /**
     * Storage the pair key-object in preference
     */
    @Suppress("ComplexMethod")
    fun setPreferences(key: String?, o: Any?) {

        if (key != null) {

            val ed = mSharedPreferences.edit()
            if (o is String) {
                ed.putString(key, o as String?).apply()
            } else if (o is Boolean) {
                ed.putBoolean(key, (o as Boolean?)!!).apply()
            } else if (o is Int) {
                ed.putInt(key, o).apply()
            } else if (o is Long) {
                ed.putLong(key, (o as Long?)!!).apply()
            } else if (o is Float) {
                ed.putFloat(key, (o as Float?)!!).apply()
            } else {
                var json: String? = null
                if (o != null) {
                    json = gsonBuilder().create().toJson(o)
                }
                ed.putString(key, json).apply()
            }

        }
    }

    // Get the stored object associate with the key string
    @Suppress("ComplexMethod", "TooGenericExceptionCaught")
    fun <T> getPreferences(key: String?, clazz: Class<T>?): T? {

        if (key != null && clazz != null) {

            try {
                if (clazz == String::class.java) {
                    return mSharedPreferences.getString(key, null) as T
                } else if (clazz == Boolean::class.java) {
                    return mSharedPreferences.getBoolean(key, false) as T
                } else if (clazz == Int::class.java) {
                    return mSharedPreferences.getInt(key, 0) as T
                } else if (clazz == Long::class.java) {
                    return mSharedPreferences.getLong(key, 0) as T
                } else if (clazz == Float::class.java) {
                    return mSharedPreferences.getFloat(key, 0f) as T
                } else {
                    val json = mSharedPreferences.getString(key, null)
                    return gsonBuilder().create().fromJson(json, clazz) as T
                }
            } catch (e: Exception) {
                Log.e("getPreferences exceptio", e.message)
            }

        }
        return null
    }

    /**
     * Remove stored object that it is associated with key param
     */
    fun removePreference(key: String) {

        setPreferences(key, null)
    }

    fun removeAll(){
        mSharedPreferences.edit().clear().apply()
    }

    fun gsonBuilder(): GsonBuilder {
        val builder = GsonBuilder()

        builder.registerTypeAdapter(Date::class.java, JsonDeserializer<Date> { json, _, _ ->
            if (json.asString != null && "".compareTo(json.asString) != 0) {
                val calendar = Calendar.getInstance()
                calendar.timeInMillis = java.lang.Long.parseLong(json.asString)
                calendar.time
            } else {
                null
            }
        })

        builder.registerTypeAdapter(Date::class.java, JsonSerializer<Date> { arg0, _, _ -> JsonPrimitive(arg0.time) })

        return builder
    }

}
