package org.gg.template.web;

import java.io.Serializable;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.Component;

import org.apache.wicket.MarkupContainer;
import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.Model;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ComponentHelper implements Serializable {

	private static final long serialVersionUID = 1L;
	Logger logger = LoggerFactory.getLogger(this.getClass());

	MarkupContainer container;

	public ComponentHelper(MarkupContainer container) {
		this.container = container;
	}

	public void addClass(String className) {
		addClass(container, className);
	}

	public void addClass(String component, String className) {
		addClass(getComponent(component), className);
	}

	public void addClass(Component component, String className) {
		component.add(new AttributeAppender("class", new Model<String>(className),
		    " "));
	}

	public void removeClass(String className) {
		removeClass(container, className);
	}

	public void removeClass(String component, String className) {
		removeClass(getComponent(component), className);
	}

	public void removeClass(Component component, String className) {
		component.add(new CssClassRemover("active"));
	}

	public void addLinkToPage(String linkId, final WebPage page) {
		container.add(new Link(linkId) {
			@Override
			public void onClick() {
				setResponsePage(page);
			}
		});
	}

	private Component getComponent(String id) {
		return container.get(id);
	}

	class CssClassRemover extends AttributeModifier {

		private static final long serialVersionUID = 1L;

		public CssClassRemover(String cssClass) {
			super("class", new Model<String>(cssClass));
		}

		@Override
		protected String newValue(String currentValue, String valueToRemove) {
			return currentValue != null ? currentValue.replaceAll(valueToRemove, " ")
			    : "";
		}
	}

}
