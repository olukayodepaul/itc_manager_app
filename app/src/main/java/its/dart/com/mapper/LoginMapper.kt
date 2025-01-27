package its.dart.com.mapper

import its.dart.com.data.repository.remote.dto.AllRepsDto
import its.dart.com.data.repository.remote.dto.DataDto
import its.dart.com.data.repository.remote.dto.ErrorDto
import its.dart.com.data.repository.remote.dto.LoginDto
import its.dart.com.data.repository.remote.dto.UsersDto
import its.dart.com.domain.repository.remote.model.AllRepsModel
import its.dart.com.domain.repository.remote.model.DataModel
import its.dart.com.domain.repository.remote.model.ErrorModel
import its.dart.com.domain.repository.remote.model.LoginModel
import its.dart.com.domain.repository.remote.model.UserModel



fun LoginDto.toLoginModel(): LoginModel {
    return LoginModel.Builder()
        .setStatus(this.status)
        .setMessage(this.message)
        .setData(this.data?.toDataModel())
        .setError(this.error.toErrorModel())
        .setTransDate(this.transDate)
        .build()
}

// DataDto to DataModel
fun DataDto.toDataModel(): DataModel {
    return DataModel.Builder()
        .setUser(this.user.toUserModel())
        .setAllReps(this.allReps.map { it.toAllRepModel() })
        .setToke(this.toke)
        .setExpiresIn(this.expiresIn)
        .build()
}

// UsersDto to UserModel
fun UsersDto.toUserModel(): UserModel {
    return UserModel.Builder()
        .setUis(this.uis)
        .setUserName(this.userName)
        .setRole(this.role)
        .setDeportId(this.deportId)
        .build()
}

// AllRepsDto to AllRepsModel
fun AllRepsDto.toAllRepModel(): AllRepsModel {
    return AllRepsModel.Builder()
        .setId(this.id)
        .setRouteId(this.routeId)
        .setRepName(this.repName)
        .setRepContact(this.repContact)
        .build()
}

// ErrorDto to ErrorModel
fun ErrorDto.toErrorModel(): ErrorModel {
    return ErrorModel.Builder()
        .setErrorCode(this.errorCode)
        .setDescription(this.description)
        .build()
}