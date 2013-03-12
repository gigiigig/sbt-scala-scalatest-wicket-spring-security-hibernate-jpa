package org.gg.template.web

class LoginPageSpec extends WicketSpec {

	val wicketTester = anonWicketTester
	
	describe("LoginPage") {

		it ("should render correct") {

			wicketTester.startPage(classOf[LoginPage])
			wicketTester.assertRenderedPage(classOf[LoginPage])

		}

		it("should contains LoginForm") {
			
		}

	}

}