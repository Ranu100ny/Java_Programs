/******************************************************************************
 *  
 *  Purpose : Determine Behavioral Design Pattern and it is Observer interface.     
 *  @author   RakeshSoni
 *  @version  1.0
 *  @since    05-03-2018 
 *    
 ******************************************************************************/


package com.bridgelab.designpattern.behavioraldesignpatterns.observerpattern;

public interface Observer {
	//method to update the observer, used by subject
		public void update();
		
		//attach with subject to observe
		public void setSubject(Subject sub);
}
