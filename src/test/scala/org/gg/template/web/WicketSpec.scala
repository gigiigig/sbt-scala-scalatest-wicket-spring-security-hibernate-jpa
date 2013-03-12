package org.gg.template.web

import org.apache.wicket.authroles.authentication.AbstractAuthenticatedWebSession
import org.apache.wicket.authroles.authentication.AuthenticatedWebSession
import org.apache.wicket.util.tester.WicketTester
import org.gg.template.web.WicketApplication
import org.junit.Test
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitSuite
import org.scalatest.junit.ShouldMatchersForJUnit
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.web.WebAppConfiguration
import org.springframework.web.context.WebApplicationContext
import org.scalatest.junit.JUnitRunner
import org.scalatest.FunSpec
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner
import org.scalatest.matchers.ShouldMatchers
import org.scalatest.BeforeAndAfter
import org.scalatest.BeforeAndAfterEach
import org.scalatest.junit.JUnitRunner
import org.springframework.test.context.TestContextManager
import org.apache.wicket.spring.injection.annot.SpringComponentInjector
import org.apache.wicket.request.Request
import org.gg.template.web.security.UserWebSession
import org.gg.template.web.security.AdminWebSession
import org.gg.template.web.security.AnonWebSession

@ContextConfiguration(locations = Array(
  "file:src/main/webapp/WEB-INF/spring/spring-context.xml",
  "file:src/main/webapp/WEB-INF/spring/jpa-context.xml",
  "file:src/main/webapp/WEB-INF/spring/security-context.xml",
  "file:src/main/webapp/WEB-INF/spring/mvc-context.xml"))
@WebAppConfiguration
class WicketSpec extends FunSpec with ShouldMatchers with BeforeAndAfter with BeforeAndAfterEach{

  val logger = LoggerFactory.getLogger(this.getClass())

  @Autowired
  val applicationContext: WebApplicationContext = null
  
  // This force Spring load @Autowired elements
  // It is needed because org.springframework.test.context.junit4.
  // does'n work in ScalaTest because ScalaTest to be runned as JUnitTest
  // need  @RunWith(classOf[JUnitRunner])
  // and Spring to inject components need  @RunWith(classOf[SpringJUnit4ClassRunner])
  // but we can't use both this annotation together
  new TestContextManager(this.getClass()).prepareTestInstance(this)
  
  def wicketApplication(className: Class[_ <: AbstractAuthenticatedWebSession]) = {
    val app = new WicketApplication() {
      override def  getWebSessionClass(): Class[_ <: AbstractAuthenticatedWebSession] = {
        className
      }
    }
    app.setApplicationContext(applicationContext)
    app
  }

  def adminWicketTester = new WicketTester(wicketApplication(classOf[AdminWebSession]))
  def userWicketTester = new WicketTester(wicketApplication(classOf[UserWebSession]))
  def anonWicketTester = new WicketTester(wicketApplication(classOf[AnonWebSession]))

}

