package com.iterates.simple.redis.server.gateway;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iterates.redis.clone.Store;
import com.iterates.simple.redis.model.exceptions.RedisStoreException;
import com.iterates.simple.redis.model.exceptions.VariableDoesNotExist;

@Service
public class RedisStoreGateway {

	
	private Store store;
	Logger logger = LoggerFactory.getLogger(RedisStoreGateway.class);

	
	@Autowired
	public RedisStoreGateway(Store store) {
		this.store = store;
		logger.info("RedisStoreGateway started");
	}
	
	
	public void set(String varName, Object value) throws RedisStoreException {
		try {
			store.set(varName, value);
		} catch (VariableDoesNotExist e) {

		} catch (Exception e) {
			throw new UnknownError();
		}
	}

	public Object get(String varName) throws RedisStoreException {
		return store.get(varName);
	}

	public void incr(String varName, int count) throws RedisStoreException {
		store.incr(varName, count);
		
	}

	public void decr(String varName, int count) throws RedisStoreException {
		store.decr(varName, count);
	}

	public void rPush(String varName, Object value) throws RedisStoreException {
		store.rPush(varName, value);
	}

	public Object rPop(String varName) throws RedisStoreException {
		return store.rPop(varName);
	}

	public void lPush(String varName, Object value) throws RedisStoreException {
		store.lPush(varName, value);
	}

	public Object lPop(String varName) throws RedisStoreException {
		return store.lPop(varName);
	}

	public Object lIndex(String varName, int index) throws RedisStoreException {
		return store.lIndex(varName, index);
	}

	public void expires(String varName, long seconds) throws RedisStoreException {
		// TODO Auto-generated method stub
		
	}

}
