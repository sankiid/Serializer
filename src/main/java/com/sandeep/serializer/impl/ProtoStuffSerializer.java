package com.sandeep.serializer.impl;

import org.apache.commons.lang.SerializationException;
import org.springframework.stereotype.Service;

import com.dyuproject.protostuff.LinkedBuffer;
import com.dyuproject.protostuff.ProtostuffIOUtil;
import com.dyuproject.protostuff.Schema;
import com.dyuproject.protostuff.runtime.RuntimeSchema;
import com.sandeep.serializer.ISerializer;

/**
 * @author sandeep
 * 
 *         This serialization won't work if there is no default constructor
 *         provided in the class
 *
 */
@Service("protoStuffSerializer")
public class ProtoStuffSerializer implements ISerializer {
	private static final int BUFFER_ALLOCATION_SIZE = 1024;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public byte[] serialize(java.lang.Class<? extends Object> classType, Object obj) throws SerializationException {
		LinkedBuffer buffer = LinkedBuffer.allocate(BUFFER_ALLOCATION_SIZE);
		byte[] data;
		try {
			Schema schema = RuntimeSchema.getSchema(classType);
			data = ProtostuffIOUtil.toByteArray(obj, schema, buffer);
		} catch (Exception e) {
			throw new SerializationException("Error while serializing object of class: " + classType, e);
		} finally {
			buffer.clear();
		}
		return data;
	}

	@Override
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Object deserialize(Class<? extends Object> classType, byte[] data) throws SerializationException {
		Schema schema = RuntimeSchema.getSchema(classType);
		Object object = null;
		try {
			object = classType.newInstance();
		} catch (InstantiationException e) {
			throw new SerializationException("Unable to de-serialize object of class: " + classType, e);
		} catch (IllegalAccessException e) {
			throw new SerializationException("Unable to de-serialize object of class: " + classType, e);
		}
		ProtostuffIOUtil.mergeFrom(data, object, schema);
		return object;
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

	@Override
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public <T> T deserializeObject(Class<T> classType, byte[] data) throws SerializationException {
		Schema schema = RuntimeSchema.getSchema(classType);
		T object = null;
		try {
			object = classType.newInstance();
		} catch (InstantiationException e) {
			throw new SerializationException("Unable to de-serialize object of class: " + classType, e);
		} catch (IllegalAccessException e) {
			throw new SerializationException("Unable to de-serialize object of class: " + classType, e);
		}
		ProtostuffIOUtil.mergeFrom(data, object, schema);
		return object;
	}

}
