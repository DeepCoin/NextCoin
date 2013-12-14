package com.nextcoin.exchange;

public class Exchange
{
	private final static Exchange instance = new Exchange();
	
	public static Exchange getInstance()
	{
		return instance;
	}
	
	private Exchange() { }
	
	public double getRate( String from , String to )
	{
		if ( from.equals(to) )
			return 1.0;
		
		return 1053.0;	// 2013.12.13
	}
}
