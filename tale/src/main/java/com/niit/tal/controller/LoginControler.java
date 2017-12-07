package com.niit.tal.controller;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.niit.talbackend.dao.CartDao;
import com.niit.talbackend.dao.CategoryDao;
import com.niit.talbackend.dao.UserDao;
import com.niit.talbackend.model.Category;
import com.niit.talbackend.model.User;

@Controller

public class LoginControler {
	public static Logger log=LoggerFactory.getLogger(LoginControler.class);
@Autowired
 CartDao cartdao;
@Autowired
CategoryDao categorydao;
@Autowired
UserDao userdao;
	@RequestMapping(value = "/loginjsp")
    public String showloginpage(Model mod) {
		mod.addAttribute("check3","true");		
        return"index";
    }

	@RequestMapping("/logout")
	public String logout(Model mod,HttpServletRequest request,HttpServletResponse response,RedirectAttributes redir){
		
		mod.addAttribute("msg","successfully loged out");
		 Authentication auth=SecurityContextHolder.getContext().getAuthentication();
		 if(auth !=null){
			 new SecurityContextLogoutHandler().logout(request, response, auth);
		 }
		 redir.addFlashAttribute("logout","true");
       return "redirect:/";
	}
	@Autowired
	UserDao userdao1;
	@RequestMapping("/logincheck")
	public String logincheck(Model mod,Principal p,HttpSession session){
		log.debug("login check starts manually");
		String id=p.getName();
		User userpass=userdao.get(id);
		String pwd=userpass.getPass();
   List <User> admin=userdao1.adminValidation(id, pwd);
   int b=admin.size();
   if(b==1){
		session.setAttribute("adminlogcheck", "true");
		mod.addAttribute("adminloginstatus"," admin you logged in successfully");
		mod.addAttribute("userregisterd","true");

	   return "index";
	   
   }else{
   		List<User> user=userdao1.userValidation(id, pwd);
		int a=user.size();
		if(a==1){
			session.setAttribute("userlogcheck", "true");
            session.setAttribute("currentuser",id);
			int count=cartdao.getcartcount(id);
			session.setAttribute("cartcount",count);
		    mod.addAttribute("userregisterd","true");
			mod.addAttribute("userloginstatus",id+", logged in successfully");
			log.debug("login check  ends");
			return "index";
		}else {
			mod.addAttribute("userloginstatus","username or password not coorect");
			return"login";
			}
       
   }
	}
	@RequestMapping("/logine")
	public String loginerror(Model mod){
		log.debug("spring security checks invalid credentials");
		mod.addAttribute("userloginstatus","username or password not coorect");
		mod.addAttribute("check3","true");		
	
		return "index";
	}
	

	 @RequestMapping(value = "/welcome", method = RequestMethod.POST)
	    public String submit( @ModelAttribute("employee")User user, 
	      BindingResult result,Model model) {
		
	        if (result.hasErrors()) {
	            return "register";
	        }
	       
	      String gh=userdao1.saveuserdetails(user);
	        log.debug("new user details binding and saving to database");
		    model.addAttribute("userstatus",gh);
		    model.addAttribute("userregisterd","true");
		   return "index";
	 }	
}
