package com.nextcoin.ticker;

public class Ticker
{
	public class Data
	{
		public class Price
		{
			public String	value;
			public String	value_int;
			public String	display;
			public String	display_short;
			public String	currency;
		}
		
		public Price	last;
		public Price	buy;
		public Price	sell;
		public String	now;
		
		public Data()
		{
			last	= new Price();
			buy		= new Price();
			sell	= new Price();
		}
	}
	
	public String	result;
	public Data		data;
	
	public Ticker()
	{
		data = new Data();
	}

	public void setLast( long value_int )
	{
		data.last.value			= Double.toString( value_int / 100000.0 );
		data.last.value_int		= Long.toString( value_int );
		data.last.display		= "\u20a9" + value_int / 100000.0;
		data.last.display_short	= "\u20a9" + value_int / 100000;
		data.last.currency		= "KRW";
	}

	public void setBuy( long value_int )
	{
		data.buy.value			= Double.toString( value_int / 100000.0 );
		data.buy.value_int		= Long.toString( value_int );
		data.buy.display		= "\u20a9" + value_int / 100000.0;
		data.buy.display_short	= "\u20a9" + value_int / 100000;
		data.buy.currency		= "KRW";
	}

	public void setSell( long value_int )
	{
		data.sell.value			= Double.toString( value_int / 100000.0 );
		data.sell.value_int		= Long.toString( value_int );
		data.sell.display		= "\u20a9" + value_int / 100000.0;
		data.sell.display_short	= "\u20a9" + value_int / 100000;
		data.sell.currency		= "KRW";
	}
}
