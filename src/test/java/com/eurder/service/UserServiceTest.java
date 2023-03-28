package com.eurder.service;

import com.eurder.dto.user.AddressDTO;
import com.eurder.dto.user.CreateUserDTO;
import com.eurder.exception.MandatoryFieldException;
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
			final CreateUserDTO createUserDTO = new CreateUserDTO("Janos", "Descheemaeker", validMailAddress, new AddressDTO(), "0495188557");
			userService.validateMandatoryCustomerFields(createUserDTO);
		}

		@Test
		@DisplayName("Validate email missing")
		void validateCustomerMandatoryFields_emailMissing_throwException(){
			final CreateUserDTO createUserDTO = new CreateUserDTO("Janos", "Descheemaeker", null, new AddressDTO(), "0495188557");
			Exception exception = assertThrows(MandatoryFieldException.class, () -> {
				userService.validateMandatoryCustomerFields(createUserDTO);
			});
		}

		@Test
		@DisplayName("Validate firstname missing")
		void validateCustomerMandatoryFields_firstnameMissing_throwException(){
			final CreateUserDTO createUserDTO = new CreateUserDTO(null, "Descheemaeker", validMailAddress, new AddressDTO(), "0495188557");
			Exception exception = assertThrows(MandatoryFieldException.class, () -> {
				userService.validateMandatoryCustomerFields(createUserDTO);
			});
		}

		@Test
		@DisplayName("Validate lastname missing")
		void validateCustomerMandatoryFields_lastnameMissing_throwException(){
			final CreateUserDTO createUserDTO = new CreateUserDTO("Janos", null, validMailAddress, new AddressDTO(), "0495188557");
			Exception exception = assertThrows(MandatoryFieldException.class, () -> {
				userService.validateMandatoryCustomerFields(createUserDTO);
			});
		}

		@Test
		@DisplayName("Validate address missing")
		void validateCustomerMandatoryFields_addressMissing_throwException(){
			final CreateUserDTO createUserDTO = new CreateUserDTO("Janos", "Descheemaeker", validMailAddress, null, "0495188557");
			Exception exception = assertThrows(MandatoryFieldException.class, () -> {
				userService.validateMandatoryCustomerFields(createUserDTO);
			});
		}

		@Test
		@DisplayName("Validate phonenumber missing")
		void validateCustomerMandatoryFields_phonenumberMissing_throwException(){
			final CreateUserDTO createUserDTO = new CreateUserDTO("Janos", "Descheemaeker", validMailAddress, new AddressDTO(), null);
			Exception exception = assertThrows(MandatoryFieldException.class, () -> {
				userService.validateMandatoryCustomerFields(createUserDTO);
			});
		}

	}

}