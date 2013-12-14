package com.nextcoin.ticker;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nextcoin.exchange.Exchange;

public class MtGoxProcessor
{
	private static final Logger LOGGER = Logger.getLogger("mtgox");

	public String update( String mtGoxJson )
	{
		ObjectMapper mapper = new ObjectMapper();
		String tickerJson;
		
		try
		{
			Ticker ticker = new Ticker();
			MtGoxTicker mtGoxTicker;

			mtGoxTicker = mapper.readValue( mtGoxJson, MtGoxTicker.class );
			ticker.setLast( (long)(mtGoxTicker.getLast() * Exchange.getInstance().getRate("USD", "KRW") ) / 1000 );
			ticker.setBuy( (long)(mtGoxTicker.getBuy() * Exchange.getInstance().getRate("USD", "KRW") ) / 1000 );
			ticker.setSell( (long)(mtGoxTicker.getSell() * Exchange.getInstance().getRate("USD", "KRW") ) / 1000 );
			ticker.data.now = Long.toString( mtGoxTicker.data.now );
			ticker.result = "success";

			mapper.writeValue( new File("www/ticker.json"), ticker );
			mapper.writeValue( new File("www/ticker_mtgox.json"), mtGoxTicker );
			
			tickerJson = mapper.writeValueAsString( ticker );
		}
		catch (JsonParseException e)
		{
			tickerJson = "{ \"result\":\"parse_error\", \"return\": { \"" + e.getMessage() + "\" } }";
			LOGGER.log( Level.SEVERE, e.toString() );
		}
		catch (JsonMappingException e)
		{
			tickerJson = "{ \"result\":\"mapping_error\", \"return\": { \"" + e.getMessage() + "\" } }";
			LOGGER.log( Level.SEVERE, e.toString() );
		}
		catch (IOException e)
		{
			tickerJson = "{ \"result\":\"io_error\", \"return\": { \"" + e.getMessage() + "\" } }";
			LOGGER.log( Level.SEVERE, e.toString() );
		}
		
		return tickerJson;
	}
}
