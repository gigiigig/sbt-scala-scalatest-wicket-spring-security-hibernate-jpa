package org.gg.template.web

import org.apache.wicket.util.tester.WicketTester
import org.scalatest._
import org.scalatest.junit.JUnitRunner
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.web.WebAppConfiguration

class HomePageSpec extends WicketSpec {

  describe("HomePage") {

    describe("with not logged in user") {

      it("should show LoginPage") {

        val wicketTester = anonWicketTester
        wicketTester.startPage(classOf[HomePage])
        wicketTester.assertRenderedPage(classOf[LoginPage])
        wicketTester.destroy()

      }

    }

    describe("with logged in user") {

      def userWicketTesterWithHomePage = {

        val wicketTester = userWicketTester
        wicketTester.startPage(classOf[HomePage])
        wicketTester

      }

      it("should show HomePage") {

        val wicketTester = userWicketTesterWithHomePage
        wicketTester.assertRenderedPage(classOf[HomePage])
        wicketTester.destroy()

      }

      it("should see welcome message with his name") {

        val wicketTester = userWicketTesterWithHomePage
        wicketTester.assertLabel("userName" , "hello logged in user!")

      }

    }

  }

}



