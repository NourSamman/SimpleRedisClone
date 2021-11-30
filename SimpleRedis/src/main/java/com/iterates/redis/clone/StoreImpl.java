package com.iterates.redis.clone;

import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import com.iterates.redis.clone.model.exceptions.IncrDecrVariableOfTypeNotInteger;
import com.iterates.redis.clone.model.exceptions.InvalidVariableType;
import com.iterates.redis.clone.model.exceptions.RedisStoreException;
import com.iterates.redis.clone.model.exceptions.VariableDoesNotExist;

@Service
@Primary
public class StoreImpl implements Store{
	
	
	private  Map<String,LinkedList<Object>> storeStructure = new Hashtable<String,LinkedList<Object>>();
	
	private static StoreImpl store;
	
	private StoreImpl() {
		
	}
	
	public static Store getInstance() {
		if(store == null)
			return store = new StoreImpl();
		return store;
	}
	

	private boolean isAcceptableType(Object value)
	{
		return value instanceof Number || value instanceof String;
	}
	
	private void validateVariableExists(String varName) throws VariableDoesNotExist {
		if(!storeStructure.containsKey(varName))
			throw new VariableDoesNotExist();
	}
	
	public synchronized void set(String varName, Object value) throws RedisStoreException {

		if (!isAcceptableType(value))
			throw new InvalidVariableType();
		
		//TODO
		//LinkedList<Object> valuesList = (LinkedList<Object>)get(varName);
		
		LinkedList<Object> valuesList = new LinkedList<Object>();
		valuesList.add(value);

		storeStructure.put(varName, valuesList);

	}

	public Object get(String varName) throws RedisStoreException {

		validateVariableExists(varName);

		return storeStructure.get(varName);

	}

	public synchronized void incr(String varName, int count) throws RedisStoreException {

		validateVariableExists(varName);

		List<Object> value = storeStructure.get(varName);

		if (value != null) {
			if (value.size() == 1) {
				Object storedObject = value.get(0);
				if (storedObject instanceof Number) {
					value.add(0, ((AtomicInteger) storedObject).addAndGet(count));
				} else {
					throw new IncrDecrVariableOfTypeNotInteger();
				}
			}

		}

	}

	public synchronized void decr(String varName, int count) throws RedisStoreException {

		validateVariableExists(varName);

		List<Object> value = storeStructure.get(varName);

		synchronized (value) {
			if (value.size() == 1) {
				Object storedObject = value.get(0);
				if (storedObject instanceof AtomicInteger) {
					value.add(0, ((AtomicInteger) storedObject).addAndGet(-count));
				} else {
					throw new IncrDecrVariableOfTypeNotInteger();
				}
			}

		}

	}

	
	public synchronized void rPush(String varName, Object value) throws RedisStoreException {

		validateVariableExists(varName);

		LinkedList<Object> varNameList = storeStructure.get(varName);

		varNameList.addLast(value);

	}

	public synchronized  Object rPop(String varName) throws RedisStoreException {
		
		validateVariableExists(varName); 
		
		LinkedList<Object> varNameList = storeStructure.get(varName);
		
		return varNameList.removeLast();
		
	}

	public synchronized void lPush(String varName, Object value)  throws RedisStoreException  {
		
		validateVariableExists(varName); 
		
		LinkedList<Object> varNameList = storeStructure.get(varName);
					
		varNameList.addFirst(value);
		
	}

	public synchronized Object lPop(String varName) throws RedisStoreException {
		
		validateVariableExists(varName); 
		
		LinkedList<Object> varNameList = storeStructure.get(varName);

		return varNameList.removeLast();
	}

	public synchronized Object lIndex(String varName, int index) throws RedisStoreException {
		
		validateVariableExists(varName); 
		
		List<Object> varNameList = storeStructure.get(varName);
		
		return varNameList.get(index);

	}

	public synchronized void expires(String varName,  long seconds) throws RedisStoreException {
		
//		storeStructure.wait(seconds*1000);
//		storeStructure.remove(varName);
		
	}
	
	

}
