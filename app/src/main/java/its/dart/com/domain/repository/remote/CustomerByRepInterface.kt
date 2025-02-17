package its.dart.com.domain.repository.remote

import its.dart.com.domain.repository.remote.model.CustomersModel

interface CustomerByRepInterface {
    suspend fun customer(userId: Int, weekDay: Int, properId: Int): CustomersModel
}