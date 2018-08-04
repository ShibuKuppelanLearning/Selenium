package com.qr.test.utility;

import java.io.File;
import java.io.IOException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

public class JSONReaderUtility {

	private static ObjectMapper objectMapper;

	private static JSONReaderUtility jsonReaderUtility;

	static {
		objectMapper = new ObjectMapper();
		jsonReaderUtility = new JSONReaderUtility();
	}

	private JSONReaderUtility() {
		// TODO Auto-generated constructor stub
	}

	@SuppressWarnings("unchecked")
	public <T> T readJson(String path, Class<T> clazz) throws JsonParseException, JsonMappingException, IOException {
		T object = (T) objectMapper.readValue(new File(getClass().getResource(path).getPath()), clazz);
		return object;
	}

	public static JSONReaderUtility getJsonReaderUtility() {
		return jsonReaderUtility;
	}

}
