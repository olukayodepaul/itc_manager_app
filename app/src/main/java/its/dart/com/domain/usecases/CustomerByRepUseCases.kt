package its.dart.com.domain.usecases

import its.dart.com.domain.repository.remote.CustomerByRepInterface
import its.dart.com.domain.repository.remote.model.CustomersModel
import javax.inject.Inject

class CustomerByRepUseCases @Inject constructor(private val repo: CustomerByRepInterface) {

    suspend fun invokeCustomer(userId: Int, weekDay: Int, properId: Int): Result<CustomersModel> {
        return try {
            val getLoginResponse = repo.customer(userId, weekDay, properId)
            Result.success(getLoginResponse)
        }catch(e: Exception) {
            Result.failure(e)
        }
    }
}