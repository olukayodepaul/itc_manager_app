package its.dart.com.domain.repository.remote.model



data class LoginModel(
    val status: Int,
    val message: String,
    val transDate: String,
    val data: UserDataModel,
    val products: List<ProductModel>,
    val error: Errors,
) {
    // Builder class for LoginModel
    class Builder {
        private var status: Int = 0
        private var message: String = ""
        private var transDate: String = ""
        private var data: UserDataModel? = null
        private var customer: MutableList<ProductModel> = mutableListOf()
        private var error: Errors? = null

        // Setters for each property
        fun status(status: Int) = apply { this.status = status }
        fun message(message: String) = apply { this.message = message }
        fun transDate(transDate: String) = apply { this.transDate = transDate }
        fun data(data: UserDataModel) = apply { this.data = data }
        fun customer(customer: List<ProductModel>) = apply { this.customer = customer.toMutableList() }
        fun addCustomer(customerModel: ProductModel) = apply { this.customer.add(customerModel) }
        fun error(error: Errors) = apply { this.error = error }

        // Build method to create LoginModel object
        fun build(): LoginModel {
            if (data == null) {
                throw IllegalStateException("UserDataModel must be provided.")
            }
            return LoginModel(status, message, transDate, data!!, customer, error!!)
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

data class ProductModel(
    val id: Int,
    val item: String,
    val code: String,
    val qty: String? = null
) {
    class Builder {
        private var id: Int = 0
        private var item: String = ""
        private var code: String = ""
        private var qty: String? = null

        fun id(id: Int) = apply { this.id = id }
        fun item(item: String) = apply { this.item = item }
        fun code(code: String) = apply { this.code = code }
        fun qty(qty: String?) = apply { this.qty = qty }

        fun build(): ProductModel {
            return ProductModel(id, item, code, qty)
        }
    }
}

data class Errors(
    val errorMessage: String,
    val errorCode: Int,
) {
    companion object {
        fun builder() = Builder()
    }

    class Builder {
        private var errorMessage: String = ""
        private var errorCode: Int = 0

        fun errorMessage(errorMessage: String) = apply { this.errorMessage = errorMessage }
        fun errorCode(errorCode: Int) = apply { this.errorCode = errorCode }

        fun build(): Errors {
            return Errors(errorMessage, errorCode)
        }
    }
}

data class TasksDTO(
    val id: Int,
    val taskId: Int? = 0,
    val latitude: String? = "0.0",
    val longitude: String? = "0.0",
    val userId: String? = "0.0",
    val timeAgo: String? = "0.0"
) {

    companion object {
        fun builder() = Builder()
    }

    class Builder {
        private var id: Int = 0
        private var taskId: Int? = 0
        private var latitude: String? = "0.0"
        private var longitude: String? = "0.0"
        private var userId: String? = "0.0"
        private var timeAgo: String? = "0.0"

        fun id(id: Int) = apply { this.id = id }
        fun taskId(taskId: Int?) = apply { this.taskId = taskId }
        fun latitude(latitude: String?) = apply { this.latitude = latitude }
        fun longitude(longitude: String?) = apply { this.longitude = longitude }
        fun userId(userId: String?) = apply { this.userId = userId }
        fun timeAgo(timeAgo: String?) = apply { this.timeAgo = timeAgo }

        fun build(): TasksDTO {
            return TasksDTO(id, taskId, latitude, longitude, userId, timeAgo)
        }
    }
}


data class TaskRequestModel (
    val userId: Int,
    val taskId: Int,
    val lat: String,
    val lng: String,
    val taskName: String,
    val userType: Int
) {

    companion object {
        fun builder() = Builder()
    }

    class Builder {
        private var userId: Int = 0
        private var taskId: Int = 0
        private var lat: String = ""
        private var lng: String = ""
        private var taskName: String = ""
        private var userType: Int = 0

        fun userId(userId: Int) = apply { this.userId = userId }
        fun taskId(taskId: Int) = apply { this.taskId = taskId }
        fun lat(lat: String) = apply { this.lat = lat }
        fun lng(lng: String) = apply { this.lng = lng }
        fun taskName(taskName: String) = apply { this.taskName = taskName }
        fun userType(userType: Int) = apply { this.userType = userType }

        fun build() = TaskRequestModel(userId, taskId, lat, lng, taskName, userType)
    }
}

class TaskModel(
    val status: Int,
    val message: String,
    val time: String,
    val taskId: String
) {

    companion object {
        fun builder() = Builder()
    }

    class Builder {
        private var status: Int = 0
        private var message: String = ""
        private var time: String = ""
        private var taskId: String = ""

        fun status(status: Int) = apply { this.status = status }
        fun message(message: String) = apply { this.message = message }
        fun time(time: String) = apply { this.time = time }
        fun taskId(taskId: String) = apply { this.taskId = taskId }

        fun build() = TaskModel(status, message, time, taskId)
    }
}

data class TasksModel(
    val id: Int = 0,
    val taskId: Int? = 0,
    val latitude: String? = "0.0",
    val longitude: String? = "0.0",
    val userId: Int? = 0,
    val timeAgo: String? = "0.0"
) {

    companion object {
        fun builder() = Builder()
    }

    class Builder {
        var id: Int = 0
        var taskId: Int? = 0
        var latitude: String? = "0.0"
        var longitude: String? = "0.0"
        var userId: Int? = 0
        var timeAgo: String? = "0.0"

        fun setId(id: Int) = apply { this.id = id }
        fun setTaskId(taskId: Int?) = apply { this.taskId = taskId }
        fun setLatitude(latitude: String?) = apply { this.latitude = latitude }
        fun setLongitude(longitude: String?) = apply { this.longitude = longitude }
        fun setUserId(userId: Int?) = apply { this.userId = userId }
        fun setTimeAgo(timeAgo: String?) = apply { this.timeAgo = timeAgo }

        fun build() = TasksModel(
                id = id,
                taskId = taskId,
                latitude = latitude,
                longitude = longitude,
                userId = userId,
                timeAgo = timeAgo
            )

    }
}

