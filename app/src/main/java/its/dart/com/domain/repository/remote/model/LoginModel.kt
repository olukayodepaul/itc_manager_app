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
                throw IllegalStateException("UserDataModel must be provided.")
            }
            return LoginModel(status, message, transDate, data!!)
        }
    }
}

data class UserDataModel(
    val users: UserModel,
    val rep: List<RepsModel>
) {
    // Builder class for UserDataModel
    class Builder {
        private var users: UserModel? = null
        private var rep: List<RepsModel> = emptyList() // Default to an empty list

        // Setters for each property
        fun users(users: UserModel) = apply { this.users = users }
        fun rep(rep: List<RepsModel>) = apply { this.rep = rep }

        // Build method to create UserDataModel object
        fun build(): UserDataModel {
            if (users == null) {
                throw IllegalStateException("UserModel must be provided.")
            }
            return UserDataModel(users!!, rep)
        }
    }
}

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


data class RepsModel(
    val id: Int,
    val routeId: Int,
    val fullName: String,
    val staffCode: String,
    val phoneNo: String? = null,
    val routeName: String? = null,
    val state: Int,
    val time: String? = null
) {
    // Builder class for RepsModel
    class Builder {
        private var id: Int = 0
        private var routeId: Int = 0
        private var fullName: String = ""
        private var staffCode: String = ""
        private var phoneNo: String? = null
        private var routeName: String? = null
        private var state: Int = 0
        private var time: String? = null

        // Setters for each property
        fun id(id: Int) = apply { this.id = id }
        fun routeId(routeId: Int) = apply { this.routeId = routeId }
        fun fullName(fullName: String) = apply { this.fullName = fullName }
        fun staffCode(staffCode: String) = apply { this.staffCode = staffCode }
        fun phoneNo(phoneNo: String?) = apply { this.phoneNo = phoneNo }
        fun routeName(routeName: String?) = apply { this.routeName = routeName }
        fun state(state: Int) = apply { this.state = state }  // Fixed duplicate function name
        fun time(time: String?) = apply { this.time = time }  // Added setter for time

        // Build method to create RepsModel object
        fun build(): RepsModel {
            return RepsModel(id, routeId, fullName, staffCode, phoneNo, routeName, state, time)
        }
    }
}
