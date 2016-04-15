package com.sandeep.serializer;

import org.apache.commons.lang.SerializationException;
/**
 * 
 * @author sandeep
 *
 */
public interface ISerializer {


	/**
	 * @param classType
	 * @param obj
	 * @return
	 */
	public byte[] serialize(Class<? extends Object> classType, Object obj) throws SerializationException;

	/**
	 * @param classType
	 * @param data
	 * @return
	 */
	public Object deserialize(Class<? extends Object> classType, byte[] data) throws SerializationException;

	/**
	 * @param obj
	 * @return
	 * @throws SerializationException
	 */
	public String doSerialize(Object obj) throws SerializationException;

	/**
	 * @param classType
	 * @param data
	 * @return
	 * @throws SerializationException
	 */
	public Object doDeserialize(Class<? extends Object> classType, String data) throws SerializationException;

	/**
	 * @param classType
	 * @param data
	 * @return
	 * @throws SerializationException
	 */
	public <T> T deserializeObject(Class<T> classType, byte[] data) throws SerializationException;
}
