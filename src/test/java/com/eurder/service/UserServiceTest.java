package com.eurder.service;

import com.eurder.mapper.user.AddressMapper;
import com.eurder.mapper.user.UserMapper;
import com.eurder.repository.UserRepository;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {

	private UserService userService;

	private final String validMailAddress = "janosdescheemaeker@hotmail.com";
	private final String invalidMailAddress = "janosdescheemaeker@hotmailom";

	@BeforeEach
	void setup(){
		this.userService = new UserService(new UserMapper(new AddressMapper()), new UserRepository());
	}

	@Nested
	@DisplayName("Mail validation tests")
	class MailValidation{

		@Test
		@DisplayName("Validate a correct mail format")
		void validateMailFormat_returnTrue(){
			assertTrue(userService.isValidEmailFormat(validMailAddress));
		}

		@Test
		@DisplayName("Validate an incorrect mail format")
		void validateMailFormat_returnFalse(){
			assertFalse(userService.isValidEmailFormat(invalidMailAddress));
		}
	}

	@Nested
	@DisplayName("Customer mandatory fields tests")
	class CustomerMandatoryFields{

		@Test
		@DisplayName("Validate all inputs given")
		void validateCustomerMandatoryFields(){

		}
	}

}