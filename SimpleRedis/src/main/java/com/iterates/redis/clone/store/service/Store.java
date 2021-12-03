package com.iterates.redis.clone.store.service;

import com.iterates.redis.clone.store.model.exceptions.RedisStoreException;

public interface Store {
	
	public void set(String varName, Object value) throws RedisStoreException;
	
	public Object get(String varName) throws RedisStoreException;
	
	public void incr(String varName, int count) throws RedisStoreException;
	
	public void decr(String varName, int count) throws RedisStoreException;
	
	public void rPush(String varName, Object value) throws RedisStoreException;
	
	public Object rPop(String varName) throws RedisStoreException;
	
	public void lPush(String varName, Object value) throws RedisStoreException;
	
	public Object lPop(String varName) throws RedisStoreException;
	
	public Object lIndex(String varName, int index)throws RedisStoreException;
	
	public void expires(String varName, long seconds) throws RedisStoreException;

}
