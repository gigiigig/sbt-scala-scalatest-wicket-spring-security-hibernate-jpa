package org.gg.template.web.component;

import org.apache.wicket.authorization.Action;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeAction;

@AuthorizeAction(action = Action.RENDER, roles = { "ROLE_ADMIN" })
public abstract class AdminLink<T> extends Link<T>{

  private static final long serialVersionUID = 1L;

  public AdminLink(String id) {
    super(id);
  }

}


