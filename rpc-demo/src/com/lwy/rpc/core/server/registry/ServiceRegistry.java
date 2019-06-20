package com.lwy.rpc.core.server.registry;

import java.util.HashMap;

public class ServiceRegistry {
	public static final HashMap<String, Class> serviceRegistryMap = new HashMap<String, Class>();

	public static Class get(String key) {
		return serviceRegistryMap.get(key);
	}

	public static void add(String key, Class clazz) {
		serviceRegistryMap.put(key, clazz);
	}

}
