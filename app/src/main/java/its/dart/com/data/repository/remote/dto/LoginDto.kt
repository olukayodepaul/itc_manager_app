package its.dart.com.data.repository.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LoginDto(
    val status: Int,
    val message: String,
    @SerialName("trans_date") val transDate: String,
    val data: UserDataDto? = null,
    val products: List<ProductDto>? = null,
    val error: ErrorDto? = null
)

@Serializable
data class UserDataDto(
    val users: UserDto,
    val rep: List<RepsDto>
)

@Serializable
data class UserDto (
    @SerialName("full_name") val fullName: String,
    val id: Int,
    @SerialName("depot_id") val depotId: Int,
    @SerialName("system_category_id") val systemCategoryId: Int,
    @SerialName("depot_lat") val depotLat: String,
    @SerialName("depot_lng") val depotLng: String
)

@Serializable
data class RepsDto(
    val id: Int,
    @SerialName("route_id") val routeId: Int,
    @SerialName("full_name") val fullName: String,
    @SerialName("staff_code") val staffCode: String,
    @SerialName("phone_no") val phoneNo: String? = null,
    @SerialName("route_name") val routeName: String? = null,
    val state: Int,
    val time: String? = null
)

@Serializable
data class ProductDto(
    val id: Int,
    val item: String,
    val code: String,
    val qty: String? = null,
)

@Serializable
data class ErrorDto(
    @SerialName("error_message") val errorMessage: String,
    @SerialName("error_code") val errorCode: Int
)

@Serializable
data class AddCustomerReqDTO(
    @SerialName("outlet_name") val outletName: String,
    @SerialName("contact_pension") val contactPension: String,
    @SerialName("phone_number") val phoneNumber: String,
    @SerialName("language_id") val languageId: Int,
    @SerialName("outlet_type_id") val outletTypeId: Int,
    @SerialName("outlet_class_id") val outletClassId: Int,
    @SerialName("address") val address: String,
    @SerialName("lat") val lat: Double,
    @SerialName("lng") val lng: Double,
    @SerialName("user_id") val userId: Int,
    @SerialName("user_type") val userType: String,
    @SerialName("rep_id") val repId: Int,
)

@Serializable
data class AddCustomerResDTO(
    @SerialName("status") val status: Int,
    @SerialName("message") val message: String,
    @SerialName("time") val time: String,
    val id: Int
)

@Serializable
data class SurveyStateDTO(
    @SerialName("sales_rep_visit") val salesRepVisit: String = "",
    @SerialName("sales_rep_visit_date") val salesRepVisitDate: String = "",
    @SerialName("sales_rep_visit_sequence") val salesRepVisitSequence: String = "",
    @SerialName("sales_rep_visit_proactive") val salesRepVisitProactive: String = "",
    @SerialName("how_satisfy") val howSatisfy: String = "",
    @SerialName("regular_visit") val regularVisit: String = "",
    @SerialName("un_resolve_issue") val unResolveIssue: String = "",
    @SerialName("sales_rating") val salesRating: String = "",
    @SerialName("sales_rep_visit_responsiveness") val salesRepVisitResponsiveness: String = "",
    @SerialName("sales_performance_product_purchase") val salesPerformanceProductPurchase: String = "",
    @SerialName("target_super_price") val targetSuperPrice: String = "",
    @SerialName("target_super_uom") val targetSuperUOM: String = "",
    @SerialName("target_menthol_price") val targetMentholPrice: String = "",
    @SerialName("target_menthol_uom") val targetMentholUOM: String = "",
    @SerialName("exec_price") val execPrice: String = "",
    @SerialName("exec_uom") val execUOM: String = "",
    @SerialName("exec_click_price") val execClickPrice: String = "",
    @SerialName("exec_click_uom") val execClickUOM: String = "",
    @SerialName("target_super_purchase") val targetSuperPurchase: String = "",
    @SerialName("target_menthol_purchase") val targetMentholPurchase: String = "",
    @SerialName("executive_purchase") val executivePurchase: String = "",
    @SerialName("executive_click_purchase") val executiveClickPurchase: String = "",
    @SerialName("what_can_we_to_improve_service") val whatCanWeToImproveService: String = "",
    @SerialName("what_wud_you_recommend") val whatWudYouRecommend: String = "",
    @SerialName("comment") val comment: String = "",
    @SerialName("product_availability_super") val productAvailabilitySuper: String = "",
    @SerialName("product_availability_menthol") val productAvailabilityMenthol: String = "",
    @SerialName("product_availability_exec") val productAvailabilityExec: String = "",
    @SerialName("product_availability_exec_click") val productAvailabilityExecClick: String = "",
    val urno: String = "",
    @SerialName("supervisor_category_id") val supervisorCategoryId: String = "",
    @SerialName("supervisor_id") val supervisorId: String = "",
    @SerialName("rep_id") val repId: Int = 0
)

//save Response
@Serializable
data class SurveyStateResDTO(
    @SerialName("status") val status: Int,
    @SerialName("message") val message: String,
    val id: Int
)

@Serializable
data class OrderDTO(
    val urno: Int = 0,
    @SerialName("supervisor_category_id") val supervisorCategoryId: String,
    @SerialName("supervisor_id") val supervisorId: String,
    @SerialName("rep_id") val repId: Int = 0,
    val body : List<OrderBodyDTO>
)

@Serializable
data class OrderBodyDTO(
    val id: Int,
    val item: String,
    val code: String,
    val qty: String,
    val inv: String,
    val uom: String
)

//{
//  sales_rep_visit: 'Weekly',
//  sales_rep_visit_date: '2022',
//  sales_rep_visit_sequence: 'Yes',
//  sales_rep_visit_proactive: 'No',
//  how_satisfy: 'Very Dissatisfied',
//  un_resolve_issue: 'Yes',
//  sales_rating: 'Poor',
//  sales_rep_visit_responsiveness: 'Neutral',
//  sales_performance_product_purchase: 'EXECUTIVE',
//  target_super_price: '0',
//  target_super_uom: 'Pack',
//  target_menthol_price: '4',
//  target_menthol_uom: 'Pack',
//  exec_price: '4',
//  exec_uom: 'Roll',
//  exec_click_price: '3',
//  exec_click_uom: 'Roll',
//  target_super_purchase: 'TARGET SUPER RV',
//  target_menthol_purchase: 'TARGET MENTHOL RV',
//  executive_purchase: 'EXECUTIVE',
//  executive_click_purchase: 'EXECUTIVE CLICK',
//  what_can_we_to_improve_service: '0030303',
//  what_wud_you_recommend: 'No',
//  comment: '737ee',
//  product_availability_super: 'TARGET SUPER RV',
//  product_availability_menthol: 'TARGET MENTHOL RV',
//  product_availability_exec: 'EXECUTIVE',
//  product_availability_exec_click: 'EXECUTIVE CLICK',
//  urno: '2999',
//add the userId
//  user_id: '6' this is system
//}