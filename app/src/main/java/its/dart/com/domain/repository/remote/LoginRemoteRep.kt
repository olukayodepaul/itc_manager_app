package its.dart.com.domain.repository.remote


interface LoginRemoteRep {
    fun login(username: String, password: String)
}