package com.example.base.infrastructure;

import java.util.Objects;
import java.util.Optional;

import com.example.base.domain.ValueObject.UserId;
import com.example.base.domain.ValueObject.UserName;
import com.example.base.domain.entity.User;
import com.example.base.domain.repository.UserRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class HibernateUserRepository implements UserRepository {
	private final EntityManagerFactory emf;

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

	// BUG 上は行ける、下はだめ（java.lang.IllegalArgumentException:
	// Unable to locate persister:
	private UserDataModel toDataModel(User from) {
		UserDataModel dataModel = new UserDataModel();
		dataModel.setId(from.getId().getValue());
		dataModel.setName(from.getName().getValue());
		return dataModel;
	}

	// private UserDataModel toDataModel(User from) {
	// return new UserDataModel() {
	// {
	// setId(from.getId().getValue());
	// setName(from.getName().getValue());
	// }
	// };
	// }
}
