package com.autentia.tnt.manager.publish;

import com.autentia.tnt.businessobject.Magazine;

public interface IProxy {
	/**
	   * Get magazine by primary key.
	   * @return magazine selected by id.
	   */
	  public Object getById(int id);
}
