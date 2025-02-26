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

@Serializable
data class PackPlacementDTO(
    val urno: Int = 0,
    @SerialName("supervisor_category_id") val supervisorCategoryId: Int,
    @SerialName("supervisor_id") val supervisorId: Int,
    @SerialName("customer_id") val customerId: Int = 0,
    val skuHandler: String = "",
    @SerialName("free_pack_placement_tgt_super")  val freePackPlacementTGTSuper : String= "",
    @SerialName("free_pack_placement_tgt_mlt")  val freePackPlacementTGTMLT : String= "",
    @SerialName("free_pack_placement_exec")  val freePackPlacementExec : String= "",
    @SerialName("qty_bought")  val qtyBought : String= "",
    @SerialName("bike_sales")  val bikeSales : String= "",
    @SerialName("sales_man_id")  val salesManID : String= "",
)


@Serializable
data class DailyActivityViewDTO(
    @SerialName("tgt_super_stock_out") val tTGTSuperStockOut: String,
    @SerialName("tgt_mlt_stock_out") val tTGTMLTStockOut: String,
    @SerialName("exec_ks_stock_out") val execKSStockOut: String,
    @SerialName("exec_ck_stock_out") val execCKStockOut: String,
    @SerialName("tgt_super_sales_made_uom") val tTGTSuperSalesMadeUOM: String,
    @SerialName("tgt_super_sales_made") val tTGTSuperSalesMade: String,
    @SerialName("tgt_mlt_sales_made_uom") val tTGTMLTSalesMadeUOM: String,
    @SerialName("tgt_mlt_sales_made") val tTGTMLTSalesMade: String,
    @SerialName("exec_ks_sales_made_uom") val execKSSalesMadeUOM: String,
    @SerialName("exec_ks_sales_made") val execKSSalesMade: String,
    @SerialName("exec_ck_sales_made_uom") val execCKSalesMadeUOM: String,
    @SerialName("exec_ck_sales_made") val execCKSalesMade: String,
    @SerialName("itc_sales_man") val itcSalesMan: String,
    @SerialName("tgt_super_reward_uom") val tTGTSuperRewardUOM: String,
    @SerialName("tgt_super_reward") val tTGTSuperReward: String,
    @SerialName("tgt_mlt_reward_uom") val tTGTMLTRewardUOM: String,
    @SerialName("tgt_mlt_reward") val tTGTMLTReward: String,
    @SerialName("exec_ks_reward_uom") val execKSRewardUOM: String,
    @SerialName("exec_ks_reward") val execKSReward: String,
    @SerialName("exec_ck_reward_uom") val execCKRewardUOM: String,
    @SerialName("exec_ck_reward") val execCKReward: String,
    @SerialName("exec_ks_sampling") val execKSSampling: String,
    @SerialName("exec_ck_sampling") val execCKSampling: String
)


