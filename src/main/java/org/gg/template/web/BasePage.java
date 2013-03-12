package org.gg.template.web;

import org.apache.wicket.markup.head.CssReferenceHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptReferenceHeaderItem;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.request.Url;
import org.apache.wicket.request.resource.UrlResourceReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.agilecoders.wicket.settings.BootstrapSettings;

public class BasePage extends WebPage {

	Logger logger = LoggerFactory.getLogger(this.getClass());
	public static final long serialVersionUID = 1l;

	protected ComponentHelper helper;
	
	public BasePage() {
		helper = new ComponentHelper(this);
	}

	@Override
	public void renderHead(IHeaderResponse response) {

		response.render(JavaScriptReferenceHeaderItem
				.forReference(new BootstrapSettings().getJsResourceReference()));

		response.render(CssReferenceHeaderItem
				.forReference(new BootstrapSettings().getCssResourceReference()));

		response.render(CssReferenceHeaderItem
				.forReference(new BootstrapSettings()
						.getResponsiveCssResourceReference()));

		addCss(response, "/style.css");
		// addCss(response,"font-awesome.css");
		// addCss(response,"base-admin.css");
		// addCss(response,"base-admin-responsive.css");
		// addCss(response,"dashboard.css");
	}

	private void addCss(IHeaderResponse response, String css) {
		Url url = Url.parse(css);
		response.render(CssReferenceHeaderItem
				.forReference(new UrlResourceReference(url)));
	}

  public ComponentHelper getHelper(){
    return helper;
  }

}
