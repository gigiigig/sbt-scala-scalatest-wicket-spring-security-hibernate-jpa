package org.gg.template.web

import org.junit.runner.RunWith
import org.junit.Ignore
import org.scalatest.matchers.ShouldMatchers

import org.scalatest.junit.JUnitRunner
import org.scalatest._
import org.apache.wicket.util.tester.WicketTester

import org.slf4j._

class BasePageSpec extends WicketSpec {

  var wicketTester: WicketTester = null

  before {
    wicketTester = userWicketTester
    wicketTester.startPage(classOf[BasePage])
  }

  after {
    wicketTester.destroy()
  }

  describe("All pages ") {

    it("should be rendered correct") {
      wicketTester.assertRenderedPage(classOf[BasePage])
    }

    describe("Styles") {

      it("should have boostrap javascript and css") {
        wicketTester.assertContains("/js/bootstrap-ver-")
        wicketTester.assertContains("/css/bootstrap-ver-")
      }

    }

  }

}
