package es.iagt.data.dao

import android.arch.persistence.room.*
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import es.iagt.data.entity.UserEntity


@Dao
interface UserDAO {
    @Query("SELECT * FROM user") fun getAll(): List<UserEntity>
    @Query("SELECT * FROM user WHERE id LIKE :id LIMIT 1") fun findById(id: Int): UserEntity
    @Update (onConflict = REPLACE) fun updateUser(user: UserEntity)
    @Insert fun insertAll(vararg users: UserEntity)
    @Delete fun deleteUser(user: UserEntity)
    @Query("DELETE FROM user") fun deleteAll()
}
