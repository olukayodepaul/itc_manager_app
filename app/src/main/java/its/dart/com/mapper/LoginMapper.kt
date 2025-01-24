package its.dart.com.mapper


import its.dart.com.data.repository.remote.dto.AllRepsDto
import its.dart.com.data.repository.remote.dto.LoginDto
import its.dart.com.domain.repository.remote.model.AllRepsModel
import its.dart.com.domain.repository.remote.model.LoginModel

fun LoginDto.toLoginModel(): LoginModel {
   return LoginModel.Builder()
       .build()

}

fun AllRepsDto.toAllRepModel(): AllRepsModel {
    return AllRepsModel.Builder()
        .build()
}