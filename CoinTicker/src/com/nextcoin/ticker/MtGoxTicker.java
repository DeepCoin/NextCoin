package com.nextcoin.ticker;

public class MtGoxTicker
{
	public static class Data
	{
		public static class Price
		{
			public double	value;
			public long		value_int;
			public String	display;
			public String	display_short;
			public String	currency;
		}
		
		public Price	last_local;
		public Price	last;
		public Price	last_orig;
		public Price	last_all;
		public Price	buy;
		public Price	sell;
		public long		now;
	}
	
	public String	result;
	public Data		data;
	
	public long getLast()	{ return data.last.value_int; }
	public long getBuy()	{ return data.buy.value_int; }
	public long getSell()	{ return data.sell.value_int; }
}
