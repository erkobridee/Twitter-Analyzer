package com.erkobridee.twitteranalyzer.util
{
	import mx.formatters.DateFormatter;

	public class Utils
	{
		
		public static function formatDateHour(date:Date):String 
		{
			var df:DateFormatter = new DateFormatter();
			df.formatString = 'DD/MM/YYYY HH:NN:SS';
			
			return df.format(date);
		}
		
	}
}