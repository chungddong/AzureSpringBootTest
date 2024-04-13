package com.mnu.siteuser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping(path="/siteuser")
public class SiteuserController {
	
	@Autowired
	private SiteUserRepository userRepository;

	@GetMapping(value= {"","/"})
	public String start(Model model)
	{
		return "start";
	}
	
	
	@GetMapping(path="/signup")
	public String signup(Model model) {
	model.addAttribute("siteuser", new SiteUser());
	return "signup_input";
	}

	@PostMapping(path="/signup")
	public String signup(@ModelAttribute SiteUser user, Model model) {
	userRepository.save(user);
	model.addAttribute("name", user.getName());
	return "signup_done";
	
	}
	
	@PostMapping(path="/find")
	public String findUser(@RequestParam(name="email") String email,
			HttpSession session, Model model,
			RedirectAttributes rd) {
	SiteUser user = userRepository.findByEmail(email);
	if(user != null) {
		model.addAttribute("user", user);
		return "find_done";
		}
		rd.addFlashAttribute("reason","wrong email");
		return "redirect:/error";
	}
	
	@GetMapping(path="/find")
	public String find() {
		return "find_user";
	}
	
	
	@PostMapping(path="/login")
	public String loginUser(@RequestParam(name="email")String email,
			@RequestParam(name="passwd")String passwd,
			HttpSession session,
			RedirectAttributes rd) {
		
		SiteUser user = userRepository.findByEmail(email);
		if(user != null) {
			if(passwd.equals(user.getPasswd())) {
				session.setAttribute("email", email);
				return "login_done";
			}
		}
		rd.addFlashAttribute("reason","wrong password");
		return "redirect:/error";
		
	}
	
	@GetMapping(path="/login")
	public String loginForm() {
		return "login";
	}
	
	
	@GetMapping(path="/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "login";
	}
	
	
	
	

}
