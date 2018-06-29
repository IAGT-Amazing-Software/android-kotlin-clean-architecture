package es.iagt.data.cache

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import es.iagt.data.dao.UserDAO
import es.iagt.data.entity.UserEntity

@SuppressWarnings("MagicNumber")
@Database(entities = [UserEntity::class], version = 0, exportSchema = false)
//@TypeConverters(TypeConverter::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDao(): UserDAO

}
