package its.dart.com.mapper


import its.dart.com.data.repository.local.entity.AllCustomersEntity
import its.dart.com.data.repository.local.entity.ProductEntity
import its.dart.com.data.repository.local.entity.SalesRepsEntity
import its.dart.com.data.repository.local.entity.TasksEntity
import its.dart.com.data.repository.remote.dto.AllCustomersDto
import its.dart.com.data.repository.remote.dto.CustomersDto
import its.dart.com.data.repository.remote.dto.LoginDto
import its.dart.com.data.repository.remote.dto.TaskDto
import its.dart.com.data.repository.remote.dto.TaskRequestDto
import its.dart.com.domain.repository.remote.model.AllCustomersModel
import its.dart.com.domain.repository.remote.model.ProductModel
import its.dart.com.domain.repository.remote.model.CustomersModel
import its.dart.com.domain.repository.remote.model.Errors
import its.dart.com.domain.repository.remote.model.LoginModel
import its.dart.com.domain.repository.remote.model.RepsModel
import its.dart.com.domain.repository.remote.model.TaskModel
import its.dart.com.domain.repository.remote.model.TaskRequestModel
import its.dart.com.domain.repository.remote.model.TasksDTO
import its.dart.com.domain.repository.remote.model.TasksModel
import its.dart.com.domain.repository.remote.model.UserDataModel
import its.dart.com.domain.repository.remote.model.UserModel


fun LoginDto.mapToLoginModel(): LoginModel {
    val userModel = this.data?.users?.let { userDto ->
        UserModel.Builder()
            .fullName(userDto.fullName)
            .id(userDto.id)
            .depotId(userDto.depotId)
            .systemCategoryId(userDto.systemCategoryId)
            .depotLat(userDto.depotLat)
            .depotLng(userDto.depotLng)
            .build()
    }

    val repsModelList = this.data?.rep?.map { repDto ->
        RepsModel.Builder()
            .id(repDto.id)
            .routeId(repDto.routeId)
            .fullName(repDto.fullName)
            .staffCode(repDto.staffCode)
            .phoneNo(repDto.phoneNo)
            .routeName(repDto.routeName)
            .build()
    }

    val customerModelList = this.products?.map { productDto ->
        ProductModel.Builder()
            .id(productDto.id)
            .item(productDto.item)
            .code(productDto.code)
            .qty(productDto.qty)
            .build()
    }

    val userDataModel = UserDataModel.Builder()
        .users(userModel?: UserModel.Builder().build())
        .rep(repsModelList?: emptyList())
        .build()

    val errorDataModel = this.error?.let { error ->
        Errors.Builder()
            .errorCode(error.errorCode)
            .errorMessage(error.errorMessage)
            .build()
    }

    return LoginModel.Builder()
        .status(this.status)
        .message(this.message)
        .transDate(this.transDate)
        .data(userDataModel)
        .customer(customerModelList?: emptyList())
        .error(errorDataModel?: Errors.Builder().build())
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

fun List<ProductModel>.toEntityList(): List<ProductEntity> {
    return this.map { productModel ->
        ProductEntity(
            id = productModel.id,
            item = productModel.item,
            code = productModel.code,
            qty = productModel.qty
        )
    }
}

fun List<ProductEntity>.toModelList(): List<ProductModel> {
    return this.map { productEntity ->
        ProductModel.Builder()
            .id(productEntity.id)
            .item(productEntity.item)
            .code(productEntity.code)
            .qty(productEntity.qty)
            .build()
    }
}

fun List<AllCustomersEntity>.toAllCustomersModel(): List<AllCustomersModel> {
    return this.map { entity ->
        AllCustomersModel.Builder()
            .setId(entity.id)
            .setUrno(entity.urno)
            .setLatitude(entity.latitude ?: "0.0")
            .setLongitude(entity.longitude ?: "0.0")
            .setPosid(entity.posid ?: "0.0")
            .setOutletName(entity.outlet_name)
            .setOutletAddress(entity.outlet_address)
            .setContactPhone(entity.contact_phone)
            .build()
    }
}

fun List<AllCustomersModel>.toAllCustomersEntity(): List<AllCustomersEntity> {
    return this.map { model ->
        AllCustomersEntity(
            id = model.id,
            urno = model.urno,
            latitude = model.latitude,
            longitude = model.longitude,
            posid = model.posid,
            outlet_name = model.outletName,
            outlet_address = model.outletAddress,
            contact_phone = model.contactPhone
        )
    }
}

fun List<TasksEntity>.toTasksDTOList(): List<TasksDTO> {
    return this.map { task ->
        TasksDTO.Builder()
            .id(task.id)
            .taskId(task.task_id)
            .latitude(task.latitude)
            .longitude(task.longitude)
            .userId(task.user_id)
            .timeAgo(task.time_ago)
            .build()
    }
}

fun List<TasksDTO>.toTasksList(): List<TasksEntity> {
    return this.map { dto ->
        TasksEntity(
            id = dto.id,
            task_id = dto.taskId,
            latitude = dto.latitude,
            longitude = dto.longitude,
            user_id = dto.userId,
            time_ago = dto.timeAgo
        )
    }
}

fun TaskDto.mapToTaskModel(): TaskModel {
    return TaskModel.Builder()
        .status(this.status)
        .message(this.message)
        .time(this.time)
        .taskId(this.taskid)
        .build()
}

fun TaskModel.mapToTaskDto(): TaskDto {
    return TaskDto(
        status = this.status,
        message = this.message,
        time = this.time,
        taskid = this.taskId
    )
}

fun TaskRequestModel.toTaskRequestDto(): TaskRequestDto {
    return TaskRequestDto(
        user_id = this.userId,
        task_id = this.taskId,
        lat = this.lat,
        lng = this.lng,
        taskname = this.taskName,
        user_type = this.userType
    )
}

//fun TasksEntity.toTasksModel (): TasksModel {
//    return TasksModel(
//        id = id,
//        taskId = task_id,
//        latitude = latitude,
//        longitude = longitude,
//        userId = user_id,
//        timeAgo = time_ago
//    )
//}

fun TasksModel.toTasksEntity(): TasksEntity {
    return TasksEntity(
        id = id,
        task_id = taskId,
        latitude = latitude,
        longitude = longitude,
        user_id = userId.toString(),
        time_ago = timeAgo
    )
}