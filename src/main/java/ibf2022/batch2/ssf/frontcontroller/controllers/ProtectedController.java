package ibf2022.batch2.ssf.frontcontroller.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import ibf2022.batch2.ssf.frontcontroller.models.Login;
import ibf2022.batch2.ssf.frontcontroller.services.AuthenticationService;

@Controller
public class ProtectedController {

	@Autowired
	private AuthenticationService authSvc;

	// TODO Task 5
	// Write a controller to protect resources rooted under /protected
	@GetMapping(path="/protected")
	public String checkAuthentication(Model m, @ModelAttribute Login login) throws Exception {
		if (!authSvc.authenticate(login.getUsername(), login.getPassword()) 
			|| authSvc.isLocked(login.getUsername()))  {
			return "view0";
		} else {
		return "view1";
		}
	}

}
