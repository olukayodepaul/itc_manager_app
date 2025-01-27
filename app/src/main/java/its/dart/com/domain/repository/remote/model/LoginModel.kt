package its.dart.com.domain.repository.remote.model


data class LoginModel(
    val status: Int? = null,
    val message: String? = null,
    val data: DataModel?,
    val error: ErrorModel,
    val transDate: String
) {
    class Builder {

        private var status: Int? = null
        private var message: String? = null
        private var data: DataModel? = null
        private var error: ErrorModel? = null
        private var transDate: String = ""

        fun setStatus(status: Int?) = apply { this.status = status }
        fun setMessage(message: String?) = apply { this.message = message }
        fun setData(data: DataModel?) = apply { this.data = data }
        fun setError(error: ErrorModel) = apply { this.error = error }
        fun setTransDate(transDate: String) = apply { this.transDate = transDate }

        fun build(): LoginModel {
            return LoginModel(
                status = status,
                message = message,
                data = data,
                error = error ?: ErrorModel(),  // Default error model if null
                transDate = transDate
            )
        }
    }
}

data class DataModel(
    val user: UserModel,
    val allReps: List<AllRepsModel> = emptyList(),
    val toke: String,
    val expiresIn: String
) {
    class Builder {
        private lateinit var user: UserModel
        private var allReps: List<AllRepsModel> = emptyList()
        private lateinit var toke: String
        private lateinit var expiresIn: String

        fun setUser(user: UserModel) = apply { this.user = user }
        fun setAllReps(allReps: List<AllRepsModel>) = apply { this.allReps = allReps }
        fun setToke(toke: String) = apply { this.toke = toke }
        fun setExpiresIn(expiresIn: String) = apply { this.expiresIn = expiresIn }

        fun build(): DataModel {
            return DataModel(user, allReps, toke, expiresIn)
        }
    }
}

data class UserModel(
    val uis: Int,
    val userName: String? = null,
    val role: String? = null,
    val deportId: Int? = null
) {
    class Builder {
        private var uis: Int = 0
        private var userName: String? = null
        private var role: String? = null
        private var deportId: Int? = null

        fun setUis(uis: Int) = apply { this.uis = uis }
        fun setUserName(userName: String?) = apply { this.userName = userName }
        fun setRole(role: String?) = apply { this.role = role }
        fun setDeportId(deportId: Int?) = apply { this.deportId = deportId }

        fun build(): UserModel {
            return UserModel(uis, userName, role, deportId)
        }
    }
}

data class AllRepsModel(
    val id: Int? = null,
    val routeId: Int? = null,
    val repName: String,
    val repContact: String
) {
    class Builder {
        private var id: Int? = null
        private var routeId: Int? = null
        private lateinit var repName: String
        private lateinit var repContact: String

        fun setId(id: Int?) = apply { this.id = id }
        fun setRouteId(routeId: Int?) = apply { this.routeId = routeId }
        fun setRepName(repName: String) = apply { this.repName = repName }
        fun setRepContact(repContact: String) = apply { this.repContact = repContact }

        fun build(): AllRepsModel {
            return AllRepsModel(id, routeId, repName, repContact)
        }
    }
}

data class ErrorModel(
    val errorCode: String? = null,
    val description: String? = null
) {
    class Builder {
        private var errorCode: String? = null
        private var description: String? = null

        fun setErrorCode(errorCode: String?) = apply { this.errorCode = errorCode }
        fun setDescription(description: String?) = apply { this.description = description }

        fun build(): ErrorModel {
            return ErrorModel(errorCode, description)
        }
    }
}
