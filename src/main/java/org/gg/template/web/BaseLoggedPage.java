package org.gg.template.web;

import org.apache.wicket.markup.html.link.Link;
import org.gg.template.web.component.AdminLink;


public class BaseLoggedPage extends BasePage {

  private static final long  serialVersionUID = 1L;

  public BaseLoggedPage(){

    add(new Link("buttonDashboard") {
        @Override
        public void onClick(){
          setResponsePage(new HomePage());
        }
      });
  }

}
