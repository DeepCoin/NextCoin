package com.nextcoin.ticker;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;

public class MtGoxClient implements Runnable
{
	private static final Logger LOGGER = Logger.getLogger("mtgox");

	static
	{
		try
		{
			FileHandler fileHandler = new FileHandler( LOGGER.getName() + ".log", true );
			fileHandler.setFormatter( new SimpleFormatter() );
			LOGGER.addHandler( fileHandler );
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	private final static HostnameVerifier DO_NOT_VERIFY = new HostnameVerifier() {
		@Override
		public boolean verify(String hostname, SSLSession session)
		{
			return true;
		}
	};

	private final URL url;
	private final MtGoxProcessor processor = new MtGoxProcessor();
	
	public MtGoxClient() throws MalformedURLException
	{
		url = new URL("https://data.mtgox.com/api/2/BTCUSD/money/ticker_fast");
	}

	@Override
	public void run()
	{
		try
		{
			while(true)
			{
				HttpsURLConnection connection = (HttpsURLConnection)url.openConnection();
				
				connection.setHostnameVerifier( DO_NOT_VERIFY );
				
				SSLContext context = SSLContext.getInstance("TLS");
				context.init(null, null, new java.security.SecureRandom());
				connection.setSSLSocketFactory(context.getSocketFactory());
				
				connection.connect();
				connection.setInstanceFollowRedirects(true);
				
				InputStream in = connection.getInputStream();
				BufferedReader reader = new BufferedReader(new InputStreamReader(in));
				String line = reader.readLine();
				if( line != null )
					processor.update( line );
				
				reader.close();
				Thread.sleep( 10 * 60 * 1000 );
			}
		}
		catch (Exception e)
		{
			LOGGER.log( Level.SEVERE, e.toString() );
		}
		finally
		{
			for( Handler h : LOGGER.getHandlers() )
				h.close();
		}
	}

}
