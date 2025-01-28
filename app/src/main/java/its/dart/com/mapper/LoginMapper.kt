package its.dart.com.mapper



import its.dart.com.data.repository.remote.dto.LoginDto
import its.dart.com.domain.repository.remote.model.LoginModel
import its.dart.com.domain.repository.remote.model.UserDataModel
import its.dart.com.domain.repository.remote.model.UserModel


fun LoginDto.mapToLoginModel(): LoginModel {
    val userDto = this.data.users
    val userModel = UserModel.Builder()
        .fullName(userDto.fullName)
        .id(userDto.id)
        .depotId(userDto.depotId)
        .systemCategoryId(userDto.systemCategoryId)
        .depotLat(userDto.depotLat)
        .depotLng(userDto.depotLng)
        .build()

    val userDataModel = UserDataModel.Builder()
        .users(userModel)
        .build()

    return LoginModel.Builder()
        .status(this.status)
        .message(this.message)
        .transDate(this.transDate)
        .data(userDataModel)
        .build()
}
