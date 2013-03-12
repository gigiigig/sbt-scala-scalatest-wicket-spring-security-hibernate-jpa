package org.gg.template.web.component;

import org.apache.wicket.markup.html.navigation.paging.IPageable;
import org.apache.wicket.markup.html.navigation.paging.PagingNavigator;

public class MyPagingNavigator extends PagingNavigator {

  public MyPagingNavigator(final String id, final IPageable pageable)	{
		super(id, pageable, null);
	}

}
