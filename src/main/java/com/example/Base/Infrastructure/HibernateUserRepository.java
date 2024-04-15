package com.example.Base.Infrastructure;

import java.util.Objects;
import java.util.Optional;

import com.example.Base.Domain.Entity.User;
import com.example.Base.Domain.Repository.UserRepository;
import com.example.Base.Domain.ValueObject.UserId;
import com.example.Base.Domain.ValueObject.UserName;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

public class HibernateUserRepository implements UserRepository {
	private final EntityManagerFactory emf;

	public HibernateUserRepository(PersistenceFactoryManager pfm) throws Exception {
		emf = pfm.provide();
	}

	@Override
	public User find(UserName userName) throws Exception {
		try (EntityManager em = emf.createEntityManager()) {
			// Queryを生成するBuilder
			CriteriaBuilder cb = em.getCriteriaBuilder();
			// Query自体
			CriteriaQuery<UserDataModel> cq = cb.createQuery(UserDataModel.class);
			// UserDataModelのカラム名を解決してくれるもの？
			Root<UserDataModel> root = cq.from(UserDataModel.class);
			// where users.name = userName.getValueの条件句
			cq.where(cb.equal(root.get("name"), userName.getValue()));
			// 実行
			Optional<UserDataModel> target = em.createQuery(cq).getResultStream().findFirst();
			if (target.isPresent())
				return toModel(target.get());
			else
				return null;
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public void save(User user) throws Exception {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		try {
			UserDataModel found = em.find(UserDataModel.class, user.getId().getValue());
			if (Objects.equals(found, null)) {
				em.persist(toDataModel(user));
			} else {
				found = transfer(user, found);
			}
			em.getTransaction().commit();
		} catch (Exception e) {
			em.getTransaction().rollback();
			throw e;
		} finally {
			em.close();
		}
	}

	private User toModel(UserDataModel from) throws Exception {
		return new User(
				new UserId(from.getId()),
				new UserName(from.getName()));
	}

	private UserDataModel transfer(User from, UserDataModel model) {
		model.setId(from.getId().getValue());
		model.setName(from.getName().getValue());
		return model;
	}

	private UserDataModel toDataModel(User from) {
		return new UserDataModel() {
			{
				setId(from.getId().getValue());
				setName(from.getName().getValue());
			}
		};
	}
}
