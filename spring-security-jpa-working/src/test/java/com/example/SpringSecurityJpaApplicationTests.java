package com.example;
import com.example.controller.UserController;
import com.example.entity.AuthRequest;
import com.example.entity.UserInfo;
import com.example.service.JwtService;
import com.example.service.UserInfoDetails;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import java.util.Arrays;

import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class SpringSecurityJpaApplicationTests {
	private JwtService jwtService;
	@Test
	public void testUserProfile()
	{
		UserController userController=new UserController();
		String result=userController.userProfile();
		assertEquals("Welcome to User Profile",result);
	}
	@Test
	public void testUserProfileNotEmpty()
	{
		UserController userController=new UserController();
		String result=userController.userProfile();
		assertTrue(!result.isEmpty());
	}
	@Test
	public void testUserProfileWelcome()
	{
		UserController userController=new UserController();
		String result=userController.userProfile();
		assertTrue(result.contains("Welcome"));
	}
	@Test
	public void testGetPassword()
	{
		String password="StrongPassword123";
		UserInfo userInfo=new UserInfo();
		userInfo.setPassword(password);
		String result=userInfo.getPassword();
		assertEquals(password,result);
	}
	@Test
	public void testGetAuthorities()
	{
		UserInfo userInfo=new UserInfo();
		userInfo.setRoles("user,admin");
		UserInfoDetails userInfoDetails=new UserInfoDetails(userInfo);
		List<GrantedAuthority> expectedAuthority= Arrays.asList(
				new SimpleGrantedAuthority("user"),
				new SimpleGrantedAuthority("admin"));
		assertEquals(expectedAuthority,userInfoDetails.getAuthorities());
	}
	@Test
	public void testMessage()
	{
		UserController userController=new UserController();
		String message=userController.welcome();
		assertEquals("Welcome this endpoint is not secure",message);
	}
	@Test
	public void testAdminProfile()
	{
		UserController userController=new UserController();
		String result= userController.adminProfile();
		assertEquals("Welcome to Admin Profile",result);
	}
	@Test
	public void testUserProfileDifferentRole()
	{
		UserController userController=new UserController();
		String result= userController.adminProfile();
		assertEquals("Welcome to Admin Profile",result);
	}
	@Test
	public void UserInfoTest()
	{
		UserInfo userInfo=new UserInfo(
				12,"teja","teja@gmail.com","teja","user"
		);
		assertEquals(12,userInfo.getId());
		assertEquals("teja",userInfo.getName());
		assertEquals("teja@gmail.com",userInfo.getEmail());
		assertEquals("teja",userInfo.getPassword());
		assertEquals("user",userInfo.getRoles());
	}
	@Test
	public void newUserInfoTest()
	{
		UserInfo userInfo=new UserInfo();
		userInfo.setId(13);
		userInfo.setName("taha");
		userInfo.setEmail("taha@gmail.com");
		userInfo.setPassword("taha");
		userInfo.setRoles("user");
	}
	@Test
	public void testGetterSetter()
	{
		AuthRequest authRequest=new AuthRequest();
		authRequest.setUsername("testUser");
		authRequest.setPassword("1234");
		assertEquals("testUser",authRequest.getUsername());
		assertEquals("1234",authRequest.getPassword());
	}
	@Test
	public void testConstructorAndGetters()
	{
		AuthRequest authRequest=new AuthRequest("teja","1234");
		assertEquals("teja",authRequest.getUsername());
		assertEquals("1234",authRequest.getPassword());
	}
	@Test
	public void testSetterGetterUser()
	{
		UserInfo userInfo=new UserInfo();
		Integer id=1;
		String name="teja";
		String password="teja";
		String email="teja@gmail.com";
		String role="user";
		userInfo.setId(id);
		userInfo.setName(name);
		userInfo.setEmail(email);
		userInfo.setPassword(password);
		userInfo.setRoles(role);
		assertEquals(id,userInfo.getId());
		assertEquals(name,userInfo.getName());
		assertEquals(password,userInfo.getPassword());
		assertEquals(role,userInfo.getRoles());
		assertEquals(email,userInfo.getEmail());
	}
	@Test
	public void testConstructorAndGettersUser()
	{
		Integer id=1;
		String name="teja";
		String password="teja";
		String email="teja@gmail.com";
		String role="user";
		UserInfo userInfo=new UserInfo(id,name,email,password,role);
		assertEquals(id,userInfo.getId());
		assertEquals(name,userInfo.getName());
		assertEquals(email,userInfo.getEmail());
		assertEquals(password,userInfo.getPassword());
		assertEquals(role,userInfo.getRoles());
	}
	@Test
	public void testEqualsObjectUser()
	{
		UserInfo user=new UserInfo(1,"john","john@gmail.com","john","user");
		assertTrue(user.equals(user));
	}
	@Test
	public void testEqualsObjectAuth()
	{
		AuthRequest authRequest=new AuthRequest("teja","teja");
		assertTrue(authRequest.equals(authRequest));
	}
	@Test
	public void testEqualsAttributesUser()
	{
		UserInfo user=new UserInfo(1,"john","john@gmail.com","john","user");
		UserInfo user1=new UserInfo(1,"john","john@gmail.com","john","user");
		assertTrue(user.equals(user1));
	}
	@Test
	public void authEquals()
	{
		AuthRequest authRequest=new AuthRequest("teja","teja");
		AuthRequest authRequest2=new AuthRequest("teja","teja");
		assertTrue(authRequest.equals(authRequest2));
	}
	@Test
	public void testEqualsAttributes2()
	{
		UserInfo user=new UserInfo(1,"john","john@gmail.com","john","user");
		UserInfo user1=new UserInfo(2,"john","john@gmail.com","john","user");
		assertFalse(user.equals(user1));
	}
	@Test
	public void testEqualsAttributesAuth2()
	{
		AuthRequest authRequest=new AuthRequest("teja","teja");
		AuthRequest authRequest2=new AuthRequest("teja2","teja");
		assertFalse(authRequest2.equals(authRequest));
	}
	@Test
	public void testEqualsNull()
	{
		UserInfo user=new UserInfo(1,"john","john@gmail.com","john","user");
		assertFalse(user.equals(null));
	}
	@Test
	public void testEqualsNullAuth()
	{
		AuthRequest authRequest=new AuthRequest("teja","teja");
		assertFalse(authRequest.equals(null));
	}
	@Test
	public void testEqualsDifferentType()
	{
		UserInfo user=new UserInfo(1,"john","john@gmail.com","john","user");
		String notUser="john";
		assertFalse(user.equals(notUser));
	}
	@Test
	public void testHashCodeAttributes()
	{
		UserInfo user=new UserInfo(1,"john","john@gmail.com","john","user");
		UserInfo user1=new UserInfo(1,"john","john@gmail.com","john","user");
		assertEquals(user.hashCode(),user1.hashCode());
	}
	@Test
	public void testHashCodeAttributeAuth()
	{
		AuthRequest authRequest=new AuthRequest("john","john");
		AuthRequest authRequest1=new AuthRequest("john","john");
		assertEquals(authRequest1.hashCode(),authRequest.hashCode());
	}
	@Test
	public void testHashCodeAttributes2()
	{
		UserInfo user=new UserInfo(1,"john","john@gmail.com","john","user");
		UserInfo user1=new UserInfo(2,"john","john@gmail.com","john","user");
		assertNotEquals(user.hashCode(),user1.hashCode());
	}
	@Test
	public void testHashCodeAttributeAuth2()
	{
		AuthRequest authRequest=new AuthRequest("john","john");
		AuthRequest authRequest1=new AuthRequest("johny","john");
		assertNotEquals(authRequest1.hashCode(),authRequest.hashCode());
	}
	@Test
	public void testHashCodeAttributes3()
	{
		UserInfo user=new UserInfo(1,"john","john@gmail.com","john","user");
		UserInfo user1=new UserInfo(1,"john","john@gmail.com","john","user");
		assertEquals(user.hashCode(),user.hashCode());
		assertEquals(user.hashCode(),user1.hashCode());
	}
}
