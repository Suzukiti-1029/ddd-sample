package com.example.base.infrastructure;

import com.example.base.domain.ValueObject.UserName;
import com.example.base.domain.entity.User;
import com.example.base.domain.repository.UserRepository;

import redis.clients.jedis.Jedis;

public class RedisUserRepository implements UserRepository {
	private static final String redisHost = "localhost";
	private static final int redisPort = 6379;

	@Override
	public User find(UserName userName) throws Exception {
		// Jedis jedis = new Jedis(redisHost, redisPort);
		// Optional<String> target = Optional.empty();
		// try (jedis) {
		// target = jedis.hgetAll("user").values().stream()
		// .filter(uName -> userName.toString().equals(uName)).findFirst();
		// }
		// if (target.isPresent()) {

		// } else
		return null;
	}

	@Override
	public void save(User user) throws Exception {
		Jedis jedis = new Jedis(redisHost, redisPort);
		try (jedis) {
			jedis.hset("user", user.getId().getValue(),
					user.getName().getValue());
		}
	}
}
