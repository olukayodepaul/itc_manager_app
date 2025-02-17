package its.dart.com.domain.repository.remote.model


data class CustomersModel(
    val status: Int,
    val message: String,
    val transDate: String,
    val data: List<AllCustomersModel>
) {
    class Builder {
        private var status: Int = 0
        private var message: String = ""
        private var transDate: String = ""
        private var data: List<AllCustomersModel> = emptyList()

        fun setStatus(status: Int) = apply { this.status = status }
        fun setMessage(message: String) = apply { this.message = message }
        fun setTransDate(transDate: String) = apply { this.transDate = transDate }
        fun setData(data: List<AllCustomersModel>) = apply { this.data = data }

        fun build() = CustomersModel(status, message, transDate, data)
    }
}

data class AllCustomersModel(
    val id: Int,
    val urno: Int,
    val latitude: String = "0.0",
    val longitude: String = "0.0",
    val posid: String = "0.0",
    val outletName: String,
    val outletAddress: String,
    val contactPhone: String,
    val repId: Int = 0,
    val option: Int = 0

) {
    class Builder {
        private var id: Int = 0
        private var urno: Int = 0
        private var latitude: String = "0.0"
        private var longitude: String = "0.0"
        private var posid: String = "0.0"
        private var outletName: String = ""
        private var outletAddress: String = ""
        private var contactPhone: String = ""
        private var repId: Int = 0
        private var option: Int = 0

        fun setId(id: Int) = apply { this.id = id }
        fun setUrno(urno: Int) = apply { this.urno = urno }
        fun setLatitude(latitude: String) = apply { this.latitude = latitude }
        fun setLongitude(longitude: String) = apply { this.longitude = longitude }
        fun setPosid(posid: String) = apply { this.posid = posid }
        fun setOutletName(outletName: String) = apply { this.outletName = outletName }
        fun setOutletAddress(outletAddress: String) = apply { this.outletAddress = outletAddress }
        fun setContactPhone(contactPhone: String) = apply { this.contactPhone = contactPhone }
        fun setRepId(repId: Int) = apply { this.repId = repId }
        fun setOption(option: Int) = apply { this.option = option }

        fun build() = AllCustomersModel(id, urno, latitude, longitude, posid, outletName, outletAddress, contactPhone, repId, option)
    }
}
