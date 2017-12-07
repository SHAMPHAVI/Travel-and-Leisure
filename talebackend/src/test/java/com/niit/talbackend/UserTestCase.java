package com.niit.talbackend;
import java.util.List;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.talbackend.dao.UserDao;
import com.niit.talbackend.model.User;

public class UserTestCase {
@Autowired
 static AnnotationConfigApplicationContext context;
@Autowired
static User user;
@Autowired
 static UserDao userdao;

@BeforeClass
public static void initial(){
	context=new AnnotationConfigApplicationContext();
	context.scan("com.niit.talbackend");
	context.refresh();
	userdao=(UserDao) context.getBean("userdao");
	user=(User) context.getBean("user");
}
@Test
public void savetest(){
	user.setUser("ramu");
	user.setPass("123456");
	user.setPhone("9486578651");
	user.setAddress("21,north street,ranchi");
	user.setPincode("621851");
	user.setRole("ROLE_USER");
	String status=userdao.saveuserdetails(user);
	Assert.assertEquals("save test",true,status);
	
}
//@Test
public void logintest(){
List <User> user=userdao.adminValidation("admin@admin","123456");
int a=user.size();
	System.out.println(a);
	
}
}
