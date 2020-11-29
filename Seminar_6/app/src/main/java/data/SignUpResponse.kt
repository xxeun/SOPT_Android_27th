package data

data class SignUpResponse(
    val `data`: Data,
    val message: String,
    val status: Int,
    val success: Boolean
) {
    data class Data(
        val email: String,
        val userName: String,
        val password: String
    )
}