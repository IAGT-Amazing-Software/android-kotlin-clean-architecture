package es.iagt.android.utils

import es.iagt.android.constants.AppConstants
import java.util.*


@Suppress("UNCHECKED_CAST")
class SharedPreferencesManager(private val preferencesUtils: PreferencesUtils) {

    private val session = TreeMap<String, Any?>()

    fun <T> getData(key: String, clazz: Class<T>): T? {
        var data: T? = session[key] as T
        if (data == null) {
            try {
                data = preferencesUtils.getPreferences(key, clazz)!!
            } catch (e: KotlinNullPointerException) {
                data = null
            }
        }
        return data
    }


    fun setData(key: String, data: Any?) {
        session[key] = data
    }

    fun setDataPersist(key: String, data: Any?) {
        setData(key, data)
        preferencesUtils.setPreferences(key, data)
    }

    fun clearData(key: String) {
        session.remove(key)
        preferencesUtils.removePreference(key)
    }

    fun getLong(key: String): Long? {
        return getData(key, Long::class.java)
    }

    fun getBoolean(key: String): Boolean? {
        return getData(key, Boolean::class.java)
    }

    fun getString(key: String): String? {
        return getData(key, String::class.java)
    }

    fun getInteger(key: String): Int? {
        return getData(key, Int::class.java)
    }

    fun isFirstInstall(): Boolean? {
        return getBoolean(AppConstants.FIRST_INSTALLATION)
    }

    fun setFirstInstall() {
        setDataPersist(AppConstants.FIRST_INSTALLATION, true)
    }


}
