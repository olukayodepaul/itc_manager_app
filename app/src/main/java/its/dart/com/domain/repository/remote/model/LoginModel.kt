package its.dart.com.domain.repository.remote.model


data class LoginModel(
    val status: Int,
    val message: String,
    val transDate: String,
    val data: UserDataModel
) {
    // Builder class for LoginModel
    class Builder {
        private var status: Int = 0
        private var message: String = ""
        private var transDate: String = ""
        private var data: UserDataModel? = null

        // Setters for each property
        fun status(status: Int) = apply { this.status = status }
        fun message(message: String) = apply { this.message = message }
        fun transDate(transDate: String) = apply { this.transDate = transDate }
        fun data(data: UserDataModel) = apply { this.data = data }

        // Build method to create LoginModel object
        fun build(): LoginModel {
            if (data == null) {
                throw IllegalStateException("User data must be provided.")
            }
            return LoginModel(status, message, transDate, data!!)
        }
    }
}

// UserDataModel with Builder pattern
data class UserDataModel(
    val users: UserModel
) {
    // Builder class for UserDataModel
    class Builder {
        private var users: UserModel? = null

        // Setter for user
        fun users(users: UserModel) = apply { this.users = users }

        // Build method to create UserDataModel object
        fun build(): UserDataModel {
            if (users == null) {
                throw IllegalStateException("UserModel must be provided.")
            }
            return UserDataModel(users!!)
        }
    }
}

// UserModel with Builder pattern
data class UserModel(
    val fullName: String,
    val id: Int,
    val depotId: Int,
    val systemCategoryId: Int,
    val depotLat: String,
    val depotLng: String
) {
    // Builder class for UserModel
    class Builder {
        private var fullName: String = ""
        private var id: Int = 0
        private var depotId: Int = 0
        private var systemCategoryId: Int = 0
        private var depotLat: String = ""
        private var depotLng: String = ""

        // Setters for each property
        fun fullName(fullName: String) = apply { this.fullName = fullName }
        fun id(id: Int) = apply { this.id = id }
        fun depotId(depotId: Int) = apply { this.depotId = depotId }
        fun systemCategoryId(systemCategoryId: Int) = apply { this.systemCategoryId = systemCategoryId }
        fun depotLat(depotLat: String) = apply { this.depotLat = depotLat }
        fun depotLng(depotLng: String) = apply { this.depotLng = depotLng }

        // Build method to create UserModel object
        fun build(): UserModel {
            return UserModel(fullName, id, depotId, systemCategoryId, depotLat, depotLng)
        }
    }
}
