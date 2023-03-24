package ibf2022.batch2.ssf.frontcontroller.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import ibf2022.batch2.ssf.frontcontroller.respositories.AuthenticationRepository;
import jakarta.json.Json;
import jakarta.json.JsonObject;

@Service
public class AuthenticationService {

	@Autowired
	private AuthenticationRepository authRepo;

	private String authenticateUrl = "https://auth.chuklee.com/api/authenticate";

	// TODO: Task 2
	// DO NOT CHANGE THE METHOD'S SIGNATURE
	// Write the authentication method in here
	public boolean authenticate(String username, String password) throws Exception {

		JsonObject o = Json.createObjectBuilder()
							.add("username", username)
							.add("password", password)
							.build();
		RequestEntity req = RequestEntity
							.post(authenticateUrl)
							.accept(MediaType.APPLICATION_JSON)
							.header("Content-Type", "application/json")
							.body(o.toString());

		RestTemplate template = new RestTemplate();
		ResponseEntity<String> resp = template.exchange(req, String.class);
		
		if (!resp.getBody().contains("Authenticated")) {
			return false;
		}
		return true;
	}

	// TODO: Task 3
	// DO NOT CHANGE THE METHOD'S SIGNATURE
	// Write an implementation to disable a user account for 30 mins
	public void disableUser(String username) {
		authRepo.disableUser(username);
	}

	// TODO: Task 5
	// DO NOT CHANGE THE METHOD'S SIGNATURE
	// Write an implementation to check if a given user's login has been disabled
	public boolean isLocked(String username) {
		return false;
	}

}
