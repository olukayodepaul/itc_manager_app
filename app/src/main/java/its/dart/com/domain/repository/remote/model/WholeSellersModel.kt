package its.dart.com.domain.repository.remote.model

//builder patter from the design pattern
data class WholeSellersModel(
    val status: String,
    val message: String,
    val data: List<WholeSellerModel>

) {
    class Builder {

        private var status: String = ""
        private var message: String = ""
        private var data: MutableList<WholeSellerModel> = mutableListOf()

        fun status(status: String) = apply { this.status = status }
        fun message(message: String) = apply {this.message = message}
        fun data(data: List<WholeSellerModel>) = apply {this.data = data.toMutableList()}

        fun build() : WholeSellersModel{
            if (data == null) {
                throw IllegalStateException("WholeSeller Model must be provided.")
            }
            return WholeSellersModel(status, message, data)
        }
    }
}

data class WholeSellerModel(
    val id: Int,
    val usersId: Int,
    val name: String,
    val address: String,
    val latitude: String,
    val longitude: String,
    val contactPhone: String,
){

    class Builder {

        private var id: Int = 0
        private var  usersId : Int = 0
        private var name: String = ""
        private var address : String = ""
        private var latitude: String = ""
        private var longitude : String = ""
        private var contactPhone: String = ""

        fun id(id : Int) = apply { this.id = id }
        fun usersId(userId: String) = apply { this.usersId = usersId}
        fun name(name: String) = apply { this.name = name }
        fun address(address: String) = apply { this.address =  address }
        fun latitude(latitude: String) = apply { this.latitude = latitude }
        fun longitude(longitude: String) = apply { this.longitude = longitude }
        fun contactPhone(contactPhone: String) = apply { this.contactPhone = contactPhone }

        //this is no exception here to be handle by the application.
        fun build() : WholeSellerModel{
            return WholeSellerModel(id, usersId, name, address, latitude, longitude, contactPhone)
        }
    }
}
