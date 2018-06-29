package es.iagt.data.net.user.request

import com.google.gson.annotations.SerializedName


open class RegisterRequestDTO(
        @SerializedName("name") var name: String? = null,
        @SerializedName("email") var email: String? = null,
        @SerializedName("password") var password: String? = null)
