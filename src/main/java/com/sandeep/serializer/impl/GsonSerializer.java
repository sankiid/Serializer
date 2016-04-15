package com.sandeep.serializer.impl;

import javax.annotation.PostConstruct;

import org.apache.commons.lang.SerializationException;
import org.springframework.stereotype.Service;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sandeep.serializer.ISerializer;

@Service("gsonSerializer")
public class GsonSerializer implements ISerializer {
	private Gson gson;

	@PostConstruct
	public void initialize() {
		gson = new GsonBuilder().disableHtmlEscaping().setFieldNamingPolicy(FieldNamingPolicy.IDENTITY).serializeNulls()
				.create();
	}

	@Override
	public byte[] serialize(Class<? extends Object> classType, Object obj) throws SerializationException {
		throw new SerializationException("not supported method");
	}

	@Override
	public Object deserialize(Class<? extends Object> classType, byte[] data) throws SerializationException {
		throw new SerializationException("not supported method");
	}

	@Override
	public String doSerialize(Object obj) throws SerializationException {
		try {
			return gson.toJson(obj);
		} catch (Exception ex) {
			throw new SerializationException("unable to serialize the given object ", ex);
		}
	}

	@Override
	public Object doDeserialize(Class<? extends Object> classType, String data) throws SerializationException {
		try {
			return gson.fromJson(data, classType);
		} catch (Exception ex) {
			throw new SerializationException("unable to deserialize to object ", ex);
		}
	}

	@Override
	public <T> T deserializeObject(Class<T> classType, byte[] data) throws SerializationException {
		try {
			return gson.fromJson(new String(data), classType);
		} catch (Exception ex) {
			throw new SerializationException("unable to deserialize to object ", ex);
		}
	}

}
