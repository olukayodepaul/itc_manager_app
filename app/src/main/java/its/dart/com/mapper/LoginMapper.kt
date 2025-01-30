package its.dart.com.mapper


import its.dart.com.data.repository.local.entity.SalesRepsEntity
import its.dart.com.data.repository.remote.dto.AllCustomersDto
import its.dart.com.data.repository.remote.dto.CustomersDto
import its.dart.com.data.repository.remote.dto.LoginDto
import its.dart.com.domain.repository.remote.model.AllCustomersModel
import its.dart.com.domain.repository.remote.model.CustomersModel
import its.dart.com.domain.repository.remote.model.LoginModel
import its.dart.com.domain.repository.remote.model.RepsModel
import its.dart.com.domain.repository.remote.model.UserDataModel
import its.dart.com.domain.repository.remote.model.UserModel

fun LoginDto.mapToLoginModel(): LoginModel {
    // Map UserDto to UserModel
    val userDto = this.data.users
    val userModel = UserModel.Builder()
        .fullName(userDto.fullName)
        .id(userDto.id)
        .depotId(userDto.depotId)
        .systemCategoryId(userDto.systemCategoryId)
        .depotLat(userDto.depotLat)
        .depotLng(userDto.depotLng)
        .build()

    // Map List of RepsDto to List of RepsModel
    val repsModelList = this.data.rep.map { repDto ->
        RepsModel.Builder()
            .id(repDto.id)
            .routeId(repDto.routeId)
            .fullName(repDto.fullName)
            .staffCode(repDto.staffCode)
            .phoneNo(repDto.phoneNo)
            .routeName(repDto.routeName)
            .build()
    }

    // Map UserDataDto to UserDataModel
    val userDataModel = UserDataModel.Builder()
        .users(userModel)
        .rep(repsModelList) // Add the list of RepsModel
        .build()

    // Map LoginDto to LoginModelx
    return LoginModel.Builder()
        .status(this.status)
        .message(this.message)
        .transDate(this.transDate)
        .data(userDataModel)
        .build()
}

fun List<SalesRepsEntity>.toRepsModelList(): List<RepsModel> {
    return this.map { salesRep ->
        RepsModel(
            id = salesRep.id,
            routeId = salesRep.route_id,
            fullName = salesRep.full_name,
            staffCode = salesRep.staff_code,
            phoneNo = salesRep.phone_no ?: "",
            routeName = salesRep.route_name,
            state = salesRep.state,
            time = salesRep.time
        )
    }
}

fun List<RepsModel>.toSalesRepsList(): List<SalesRepsEntity> {
    return this.map { repsModel ->
        SalesRepsEntity(
            id = repsModel.id,
            route_id = repsModel.routeId,
            full_name = repsModel.fullName,
            staff_code = repsModel.staffCode,
            phone_no = repsModel.phoneNo ?: "",
            route_name = repsModel.routeName ?: "",
            state = repsModel.state,
            time = repsModel.time?:""
        )
    }
}

fun CustomersDto.toCustomersModel(): CustomersModel {
    return CustomersModel(
        status = this.status,
        message = this.message,
        transDate = this.transDate,
        data = this.data.toAllCustomersModelList()
    )
}

fun List<AllCustomersDto>.toAllCustomersModelList(): List<AllCustomersModel> {
    return this.map { customer ->
        AllCustomersModel(
            id = customer.id,
            urno = customer.urno,
            latitude = customer.latitude ?: "0.0",
            longitude = customer.longitude ?: "0.0",
            posid = customer.posid ?: "0.0",
            outletName = customer.outletName,
            outletAddress = customer.outletAddress,
            contactPhone = customer.contactPhone
        )
    }
}

