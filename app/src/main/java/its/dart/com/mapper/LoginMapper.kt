package its.dart.com.mapper


import its.dart.com.data.repository.local.entity.AllCustomersEntity
import its.dart.com.data.repository.local.entity.ProductEntity
import its.dart.com.data.repository.local.entity.SalesRepsEntity
import its.dart.com.data.repository.remote.dto.AllCustomersDto
import its.dart.com.data.repository.remote.dto.CustomersDto
import its.dart.com.data.repository.remote.dto.LoginDto
import its.dart.com.domain.repository.remote.model.AllCustomersModel
import its.dart.com.domain.repository.remote.model.ProductModel
import its.dart.com.domain.repository.remote.model.CustomersModel
import its.dart.com.domain.repository.remote.model.LoginModel
import its.dart.com.domain.repository.remote.model.RepsModel
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

    val customerModelList = this.products.map { customerDto ->
        ProductModel.Builder()
            .id(customerDto.id)
            .item(customerDto.item)
            .code(customerDto.code)
            .qty(customerDto.qty)
            .build()
    }

    val userDataModel = UserDataModel.Builder()
        .users(userModel)
        .rep(repsModelList)
        .build()

    return LoginModel.Builder()
        .status(this.status)
        .message(this.message)
        .transDate(this.transDate)
        .data(userDataModel)
        .customer(customerModelList)
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