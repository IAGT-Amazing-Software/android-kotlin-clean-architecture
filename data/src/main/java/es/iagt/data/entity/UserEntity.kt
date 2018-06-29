package es.iagt.data.entity

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "user")
data class UserEntity(
        @PrimaryKey(autoGenerate = true) var id: Int? = null,
        @SerializedName("name") var name: String? = null,
        @SerializedName("email") var email: String? = null

)

//@SerializedName("client_data") @Embedded var client_data: ClientDataEntity?,
//@SerializedName("client_info") @Embedded var client_info: ClientInfoEntity?
