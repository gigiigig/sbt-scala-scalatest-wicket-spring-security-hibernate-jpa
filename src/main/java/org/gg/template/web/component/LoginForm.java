package org.gg.template.web.component;

import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.gg.template.web.ComponentHelper;
import org.gg.template.web.LoginPage;


public class LoginForm extends Form<LoginPage> {

	private static final long serialVersionUID = 1L;

	private String username;
	private String password;

	public LoginForm(String id) {
		super(id);
		setModel(new CompoundPropertyModel(this));
		add(new RequiredTextField<String>("username"));
		add(new PasswordTextField("password"));
	}

	@Override
	protected void onSubmit() {
		AuthenticatedWebSession session = AuthenticatedWebSession.get();
		if (session.signIn(username, password)) {
			setDefaultResponsePageIfNecessary();
		} else {
			getPage().error(getString("message.login.failed"));
		}
	}

	private void setDefaultResponsePageIfNecessary() {
		// if(!continueToOriginalDestination()) {
		setResponsePage(getApplication().getHomePage());
		// }
	}

}
