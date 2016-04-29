/**
 * @(#)Weekdays.java
 *
 *
 * @author
 * @version 1.00 2016/4/29
 */
package com.example.steven.endterm;

public enum Weekday {

    SUNDAY, MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY;

	// toString method to print properly
	public String toString(){
		switch(this){
			case MONDAY: return "Monday";
			case TUESDAY: return "Tuesday";
			case WEDNESDAY: return "Wednesday";
			case THURSDAY: return "Thursday";
			case FRIDAY: return "Friday";
			case SATURDAY: return "Saturday";
			default: return "Sunday";
		}
	}

}