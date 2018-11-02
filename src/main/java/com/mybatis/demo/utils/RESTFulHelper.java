package com.mybatis.demo.utils;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class RESTFulHelper {

	private final static ObjectMapper objectMapper = new ObjectMapper();

	/*
	 * public final static Object makeObjectFromJson(Object t){ return
	 * gson.fromJson("{}", t.getClass()); }
	 */

	public final static String getJsonLikeString(Object obj) throws JsonProcessingException {
		return objectMapper.writeValueAsString(obj);
	}

	public static XMLGregorianCalendar getXMLGregorianCalendarDate(Date dateIn) throws RuntimeException {
		GregorianCalendar gregorianCalendar = new GregorianCalendar();
		gregorianCalendar.setTime(dateIn);
		XMLGregorianCalendar xMLGregorianCalendar = null;
		try {
			xMLGregorianCalendar = DatatypeFactory.newInstance().newXMLGregorianCalendar(gregorianCalendar);
		} catch (DatatypeConfigurationException e) {

			throw new RuntimeException(e.getMessage());
		}
		return xMLGregorianCalendar;
	}

	public final static String getISOTimeStamp(Date ts) throws RuntimeException {
		String pattern = "yyyy-mm-dd'T'hh:mm:ss.SSS'Z'";
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		String isoTS = sdf.format(ts);

		return isoTS;
	}

}
