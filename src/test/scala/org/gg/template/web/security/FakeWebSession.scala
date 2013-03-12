package org.gg.template.web.security

import org.apache.wicket.authroles.authentication.AuthenticatedWebSession
import org.apache.wicket.request.Request
import org.apache.wicket.authroles.authorization.strategies.role.Roles

class FakeWebSession(request: Request) extends AuthenticatedWebSession(request) {

  val roles: Array[String] = Array[String]()

  override def authenticate(username: String, password: String): Boolean = true
  override def getRoles() = new Roles(roles)

}

class AdminWebSession(request: Request) extends FakeWebSession(request) {
  override val roles = Array("ROLE_USER", "ROLE_ADMIN")
}

class UserWebSession(request: Request) extends FakeWebSession(request) {
  override val roles = Array("ROLE_USER")
}

class AnonWebSession(request: Request) extends FakeWebSession(request) {
  override def authenticate(username: String, password:String) : Boolean = false
}



