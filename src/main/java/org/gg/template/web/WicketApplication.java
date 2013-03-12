package org.gg.template.web;

import org.apache.wicket.authroles.authentication.AbstractAuthenticatedWebSession;
import org.apache.wicket.authroles.authentication.AuthenticatedWebApplication;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;
import org.gg.template.model.User;
import org.gg.template.model.dao.UserDao;
import org.gg.template.web.security.MyAuthenticatedWebSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Component;

import de.agilecoders.wicket.Bootstrap;
import de.agilecoders.wicket.settings.BootstrapSettings;

@Component
public class WicketApplication extends AuthenticatedWebApplication implements
    ApplicationContextAware {

	Logger logger = LoggerFactory.getLogger(WicketApplication.class);

	@Autowired
	ApplicationContext applicationContext;

	@SpringBean
	UserDao userDao;

	String[] langs;

	@Override
	public Class<HomePage> getHomePage() {
		return HomePage.class;
	}

	@Override
	public void init() {

		super.init();

		getApplicationSettings().setPageExpiredErrorPage(LoginPage.class);

		BootstrapSettings settings = new BootstrapSettings();
		Bootstrap.install(this, settings);

		SpringComponentInjector springComponentInjector = null;

		// this is for test
		if (applicationContext != null) {
			springComponentInjector = new SpringComponentInjector(this,
			    applicationContext, true);
		} else {
			springComponentInjector = new SpringComponentInjector(this);
		}

		springComponentInjector.inject(this);
		getComponentInstantiationListeners().add(springComponentInjector);

		langs = new String[] { "it", "en" };

		mountPackage("admin", org.gg.template.web.BasePage.class);
		mountPage("admin/dashboard", org.gg.template.web.HomePage.class);
		
		loadSampleDataIfNoExists();

	}

	private void loadSampleDataIfNoExists() {

		String email = "user@test.it";
		User user = userDao.findByEmail(email);

		if (user == null) {
			User luigi = new User(email, new Md5PasswordEncoder().encodePassword(
			    "test", null), "ROLE_USER", "ROLE_ADMIN");
			luigi.setFirstname("Gigi");
			luigi.setLastname("igiG");
			userDao.save(luigi);
		}

	}

	@Override
	protected Class<? extends WebPage> getSignInPageClass() {
		return LoginPage.class;
	}

	@Override
	protected Class<? extends AbstractAuthenticatedWebSession> getWebSessionClass() {
		return MyAuthenticatedWebSession.class;
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
	    throws BeansException {
		this.applicationContext = applicationContext;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
	public String[] getLangs() {
	  return langs;
  }

}
