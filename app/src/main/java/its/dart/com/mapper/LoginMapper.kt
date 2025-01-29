package its.dart.com.mapper


import its.dart.com.data.repository.local.entity.SalesRepsEntity
import its.dart.com.data.repository.remote.dto.LoginDto
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
            id = salesRep.id, // No transformation needed
            routeId = salesRep.route_id, // No transformation needed
            fullName = salesRep.full_name, // No transformation needed
            staffCode = salesRep.staff_code, // No transformation needed
            phoneNo = salesRep.phone_no ?: "", // Handle nullable phone_no
            routeName = salesRep.route_name // No transformation needed
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
            route_name = repsModel.routeName ?: ""
        )
    }
}
