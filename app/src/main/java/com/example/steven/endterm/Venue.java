package com.example.steven.endterm;

/**
 * @(#)Venue.java
 *
 *
 * @author
 * @version 1.00 2016/4/29
 */


public class Venue {
	private String id, type, name, address, description;
	private int[] dayGoing = {0,0,0,0,0,0,0};

    public Venue(String id, String type, String name, String address, String description)
    {
    	this.id = id;
    	this.type = type;
    	this.name = name;
    	this.address = address;
    	this.description = description;
    }

    public void incrementDay(Weekday day)
    {
    	dayGoing[day.ordinal()]++;
    }

    public void resetDayGoing()
    {
    	for (int i=0; i<dayGoing.length; i++)
    		dayGoing[i]=0;
    }

    public void setDayGoing(int[] arr)
    {
    	dayGoing=arr;
    }

    public String getId()
    {
    	return id;
    }

    public String getType()
    {
    	return type;
    }

    public String getName()
    {
    	return name;
    }

    public String getAddress()
    {
    	return address;
    }

    public String getDescription()
    {
    	return description;
    }

    public int[] getArrGoing()
    {
    	return dayGoing;
    }

    public String toString()
    {
    	String out = id+","+type+","+name+","+description;
    	for (int i=0; i<dayGoing.length; i++)
    	{
    		out = out+","+dayGoing[i];
    	}
    	return out;
    }
}