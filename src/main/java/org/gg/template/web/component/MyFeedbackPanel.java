package org.gg.template.web.component;

import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.gg.template.web.ComponentHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyFeedbackPanel extends FeedbackPanel {
  
  Logger logger = LoggerFactory.getLogger(this.getClass());

  private ComponentHelper helper;
  public MyFeedbackPanel(final String id){
    super(id);
    helper = new ComponentHelper(this);
    helper.addClass("alert");
  }

  public void setErrorMessage(){
    helper.addClass("alert-error");
  }

  public boolean isVisible(){
    if(!anyMessage()){
      return false;
    }else{
      return true;
    }
  }

}
