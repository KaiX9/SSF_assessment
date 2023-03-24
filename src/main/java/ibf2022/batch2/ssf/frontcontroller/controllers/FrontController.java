package ibf2022.batch2.ssf.frontcontroller.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ibf2022.batch2.ssf.frontcontroller.models.Login;
import ibf2022.batch2.ssf.frontcontroller.services.AuthenticationService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
@RequestMapping
public class FrontController {

	@Autowired
	private AuthenticationService authSvc;

	// TODO: Task 2, Task 3, Task 4, Task 6
	@GetMapping(path={"/", "/index.html"})
	public String loginPage(Model m, HttpSession s, @ModelAttribute Login login) {

		login = getLogin(s);

		m.addAttribute("login", login);
		return "view0";
	}

	@PostMapping(path="/login")
	public String postLogin(Model m, HttpSession s
		, @ModelAttribute @Valid Login login
		, BindingResult binding) throws Exception {
		
		int noOfTries = 1;
		login = getLogin(s);
		m.addAttribute("login", login);

		System.out.printf(">>> login: %s\n", login);

		if (binding.hasErrors()) {
			return "view0";
		}
		for (noOfTries = 1; noOfTries < 3; noOfTries++) {
		try {
		authSvc.authenticate(login.getUsername(), login.getPassword());
		} catch (Exception ex) { 
				ex.printStackTrace();
				m.addAttribute("error", ex.getMessage());
				
				System.out.printf(">>>No. of tries = %d".formatted(noOfTries));
				login.generateFirstNum();
				login.generateOperator();
				login.generateSecondNum();
				if (login.generateFirstNum() < login.generateSecondNum()) {
					login.generateFirstNum();
					login.generateSecondNum();
				}
				if (login.generateOperator().contains("+")) {
					login.setAnswer(login.getFirstNum() + login.getSecondNum());
				}
				if (login.generateOperator().contains("-")) {
					login.setAnswer(login.getFirstNum() - login.getSecondNum());
				}
				if (login.generateOperator().contains("*")) {
					login.setAnswer(login.getFirstNum() * login.getSecondNum());
				}
				if (login.generateOperator().contains("/")) {
					login.setAnswer(login.getFirstNum() / login.getSecondNum());
				}

				if (login.answer != (login.getAnswer())) {
					return "view0";
				}
				System.out.printf(">>>Answer = %d".formatted(login.getAnswer()));
				m.addAttribute("answer", login.getAnswer());
				s.setAttribute("count", noOfTries);
				m.addAttribute("count", noOfTries);

				return "view0";
		}
	}
		
		return "view1";
	}

	@PostMapping(path="/disable")
	public String disableUser(Model m, HttpSession s, @ModelAttribute Login login) {
		int noOfTries = (int) s.getAttribute("count");
		m.addAttribute("login", login);
			return "view2";

	}

	private Login getLogin(HttpSession s) {
		Login login = (Login)s.getAttribute("login");
		if (null == login) {
			login = new Login(login.getUsername());
			s.setAttribute("login", login);
		}
		return login;
	}
}
