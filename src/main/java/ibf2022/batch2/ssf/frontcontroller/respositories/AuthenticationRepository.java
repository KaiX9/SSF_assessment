package ibf2022.batch2.ssf.frontcontroller.respositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import ibf2022.batch2.ssf.frontcontroller.models.Login;

@Repository
public class AuthenticationRepository {

	@Autowired @Qualifier("login")
	private RedisTemplate<String, String> template;

	// TODO Task 5
	// Use this class to implement CRUD operations on Redis
	public Login disableUser(String username) {
		Login login = new Login(username);
		this.template.opsForValue().set(username, username, 1800);
		return login;
	}

}
