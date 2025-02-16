package its.dart.com.domain.repository.remote

import its.dart.com.domain.repository.remote.model.AddCustomerReqModel
import its.dart.com.domain.repository.remote.model.AddCustomerResModel

interface AddCustomer  {
    suspend fun postCustomer(customer: AddCustomerReqModel): AddCustomerResModel
}