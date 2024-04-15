package com.example.Base.Infrastructure;

import java.util.Objects;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class PersistenceFactoryManager {
	private EntityManagerFactory entityManagerFactory;

	private PersistenceFactoryManager() {
	}

	private class SingletonHolder {
		private static final PersistenceFactoryManager INSTANCE = new PersistenceFactoryManager();
	}

	public static PersistenceFactoryManager getInstance() {
		return getInstance(null);
	}

	public static PersistenceFactoryManager getInstance(String persistenceUnitName) {
		SingletonHolder.INSTANCE.create(persistenceUnitName);
		return SingletonHolder.INSTANCE;
	}

	public boolean isAvailable() {
		return !Objects.equals(entityManagerFactory, null)
				&& entityManagerFactory.isOpen();
	}

	public EntityManagerFactory provide() throws Exception {
		if (!isAvailable()) {
			throw new Exception("Persistence Unit is not available.");
		}
		return entityManagerFactory;
	}

	public void create(String persistenceUnitName) {
		String pun = Objects.equals(persistenceUnitName, null) ? "myPU" : persistenceUnitName;
		if (!isAvailable()) {
			entityManagerFactory = Persistence.createEntityManagerFactory(pun);
			return;
		}
	}

	public void close() {
		if (entityManagerFactory.isOpen()) {
			entityManagerFactory.close();
		}
	}

}
