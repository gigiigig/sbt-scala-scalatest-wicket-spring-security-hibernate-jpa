package org.gg.template.web

import org.apache.wicket.util.tester.WicketTester

class BaseLoggedPageSpec extends WicketSpec {

  var wicketTester : WicketTester = null

  before {
    wicketTester = userWicketTester
    wicketTester.startPage(classOf[BaseLoggedPage])
  }

  after {
    wicketTester.destroy()
  }

  describe("BaseLoggedPage"){

    describe("navbar buttons") {

      it("should have Dashboard button wich go to dashboard") {
        wicketTester.clickLink("buttonDashboard")
        wicketTester.assertRenderedPage(classOf[HomePage])
      }

      describe("When user have ROLE_ADMIN authority") {


      }
    }

  }

}
