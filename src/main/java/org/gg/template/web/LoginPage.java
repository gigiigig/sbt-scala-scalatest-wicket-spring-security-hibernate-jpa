package org.gg.template.web;

import org.gg.template.web.component.LoginForm;
import org.gg.template.web.component.MyFeedbackPanel;

public class LoginPage extends BasePage {

	private static final long serialVersionUID = 1L;

  public LoginPage(){
    super();
  }

	@Override 
	protected void onInitialize()	{
		super.onInitialize();
		add(new LoginForm("loginform"));
    MyFeedbackPanel feedback = new MyFeedbackPanel("feedback");
    feedback.setErrorMessage();
		add(feedback);
	} 

}
