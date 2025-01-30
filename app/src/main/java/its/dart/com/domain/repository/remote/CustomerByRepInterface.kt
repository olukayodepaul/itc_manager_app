package its.dart.com.domain.repository.remote

import its.dart.com.domain.repository.remote.model.CustomersModel

interface CustomerByRepInterface {
    suspend fun customer(Int: String): CustomersModel
}