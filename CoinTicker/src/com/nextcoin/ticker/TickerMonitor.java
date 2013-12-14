package com.nextcoin.ticker;

import java.net.MalformedURLException;

public class TickerMonitor
{
	public static void main(String[] args) throws InterruptedException, MalformedURLException
	{
		Thread mtgox = new Thread( new MtGoxClient() );

		mtgox.start();
		mtgox.join();
	}
}
