package org.gg.template.web;

import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.markup.html.basic.Label;

@AuthorizeInstantiation("ROLE_USER")
public class HomePage extends BaseLoggedPage {

	public static final long serialVersionUID = 1l;

	@Override
	protected void onInitialize() {
		super.onInitialize();
		
		add(new Label("userName", "hello logged in user!"));
		helper.addClass("buttonDashboard", "active");

	}
}
