package org.gg.template.web

import org.gg.template.web.WicketApplication
import org.junit.runner.RunWith
import org.scalatest.matchers.ShouldMatchers
import org.scalatest._
import org.scalatest.junit.JUnitRunner
import org.apache.wicket.util.tester.WicketTester
import org.apache.wicket.protocol.http.WebApplication
import org.apache.wicket.markup.WicketTag
import org.springframework.test.context.web.WebAppConfiguration
import org.springframework.test.context.ContextConfiguration
import org.gg.template.web.security.AnonWebSession

class WicketApplicationSpec extends WicketSpec {

  describe("WebApplication") {

    it("should have ApplicationContext injected") {
      assert(applicationContext != null)
    }

    it("should start with HomePage") {
      val app = new WicketApplication()
      wicketApplication(classOf[AnonWebSession]).getHomePage() should not be null
    }

  }

}

