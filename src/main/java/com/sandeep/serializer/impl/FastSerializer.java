package com.sandeep.serializer.impl;

import javax.annotation.PostConstruct;

import org.apache.commons.lang.SerializationException;
import org.nustaq.serialization.FSTConfiguration;
import org.springframework.stereotype.Service;

import com.sandeep.serializer.ISerializer;

/**
 * To take advantage of this serialization mechanism make sure that your class
 * implements Serializable marker interface.
 * 
 * @author sandeep
 *
 */
@Service("fastSerializer")
public class FastSerializer implements ISerializer {
	private FSTConfiguration config;

	@PostConstruct
	public void initialize() {
		config = FSTConfiguration.createDefaultConfiguration();
	}

	@Override
	public byte[] serialize(Class<? extends Object> classType, Object obj) throws SerializationException {
		try {
			return config.asByteArray(obj);
		} catch (Exception ex) {
			throw new SerializationException("unable to serialize the given object ", ex);
		}
	}

	@Override
	public Object deserialize(Class<? extends Object> classType, byte[] data) throws SerializationException {
		try {
			return config.asObject(data);
		} catch (Exception ex) {
			throw new SerializationException("unable to deserialize to object ", ex);
		}
	}

	@Override
	public <T> T deserializeObject(Class<T> classType, byte[] data) throws SerializationException {
		try {
			Object object = config.asObject(data);
			return classType.cast(object);
		} catch (Exception ex) {
			throw new SerializationException("unable to deserialize to object ", ex);
		}
	}

	@Override
	public String doSerialize(Object obj) throws SerializationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object doDeserialize(Class<? extends Object> classType, String data) throws SerializationException {
		// TODO Auto-generated method stub
		return null;
	}

}
