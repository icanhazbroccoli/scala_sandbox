package io.whitebox.scala.sandbox.composition_example

object CompositionExample {

  case class HttpRequest(
      headers: Map[String, String],
      payload: String,
      principal: Option[String] = None
    )

  def checkAuthorization(request: HttpRequest) = {
    val authHeader = request.headers.get("Authorization")
    val mockPrincipal = authHeader match {
      case Some(headerValue) => Some("AUser")
      case _ => None
    }
    request.copy(principal = mockPrincipal)
  }

  def logFingerprint(request: HttpRequest) = {
    val fingerprint = request.headers.getOrElse("X-RequestFingerprint", "")
    print("FINGERPRINT=" + fingerprint)
    request
  }

  def composeFilters(filters: Seq[Function[HttpRequest, HttpRequest]]) =
    filters.reduce {
      (allFilters, currentFilter) => allFilters compose currentFilter
    }

  val filters = Vector(checkAuthorization, logFingerprint)
  val filterChain = composeFilters(filters)
  val requestHeaders = Map(
      "Authorization" -> "Auth", "X-RequestFingerprint" -> "fingerprint"
    )
  val request = HttpRequest(requestHeaders, "body")
  filterChain(request)
}
