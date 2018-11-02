package com.mybatis.demo.utils;

import com.google.json.JsonSanitizer;

@SuppressWarnings("serial")
public class ValidateInput extends RuntimeException {

	public static final Boolean jsonCanBeTrusted(String myJsonLikeString) {
		String wellFormedJson = JsonSanitizer.sanitize(myJsonLikeString);
		if (null == wellFormedJson) {
			return false;
		}
		return true;
	}

}
