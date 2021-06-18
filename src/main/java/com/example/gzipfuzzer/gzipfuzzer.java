package com.example.gzipfuzzer;

import java.lang.*;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.zip.GZIPInputStream;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

/**
 * Hello world!
 *
 */
public class gzipfuzzer 
{
	public static ObjectMapper mapper = null;
	public static Class payloadClass;

	public static void fuzzerInitialize() {
		mapper = new ObjectMapper();
        mapper.enableDefaultTyping();
        mapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL, JsonTypeInfo.As.PROPERTY);
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
	}

	public static void fuzzerTestOneInput(byte[] input)
    {
		
        try {
            GZIPInputStream gip = new GZIPInputStream(new ByteArrayInputStream(input));
			Object payload = mapper.readValue(gip, payloadClass);
			gip.close();
        } catch (IOException e) {
        } catch (IllegalArgumentException iae) { 
		}

    }
}
