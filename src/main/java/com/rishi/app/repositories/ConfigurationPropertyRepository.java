package com.rishi.app.repositories;

import java.util.List;

import com.rishi.app.models.ConfigurationProperty;

public interface ConfigurationPropertyRepository {

	ConfigurationProperty create(ConfigurationProperty p);
	ConfigurationProperty update(ConfigurationProperty p);
	ConfigurationProperty delete(Long id);
	List<ConfigurationProperty> findAllConfigurationProperties();
	ConfigurationProperty find(Long id);
	ConfigurationProperty findConfigurationPropertyByName(String name);
}

