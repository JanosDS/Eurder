package com.eurder.service;

import com.eurder.domain.user.Address;
import com.eurder.dto.user.AddressDTO;
import com.eurder.dto.user.CreateUserDTO;
import com.eurder.exception.MandatoryFieldException;
import com.eurder.mapper.user.AddressMapper;
import com.eurder.mapper.user.UserMapper;
import com.eurder.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {

	private UserService userService;

	private final String validMailAddress = "janosdescheemaeker@hotmail.com";
	private final String invalidMailAddress = "janosdescheemaeker@hotmailom";

	private final Address validAddress = new Address("street", "12", "9000", "Gent", "Belgium");
	private final AddressDTO validAddressDTO = new AddressDTO("street", "12", "9000", "Gent", "Belgium");


	@BeforeEach
	void setup() {
		this.userService = new UserService(new UserMapper(new AddressMapper()), new UserRepository());
	}

	@Nested
	@DisplayName("Mail validation tests")
	class MailValidation {

		@Test
		@DisplayName("Validate a correct mail format")
		void validateMailFormat_returnTrue() {
			assertTrue(userService.isValidEmailFormat(validMailAddress));
		}

		@Test
		@DisplayName("Validate an incorrect mail format")
		void validateMailFormat_returnFalse() {
			assertFalse(userService.isValidEmailFormat(invalidMailAddress));
		}
	}

	@Nested
	@DisplayName("Customer mandatory fields tests")
	class CustomerMandatoryFields {

		@Test
		@DisplayName("Validate all inputs given")
		void validateCustomerMandatoryFields() {
			final CreateUserDTO createUserDTO = new CreateUserDTO("Janos", "Descheemaeker", validMailAddress, validAddressDTO, "0495188557");
			userService.validateMandatoryCustomerFields(createUserDTO);
		}

		@Test
		@DisplayName("Validate email missing")
		void validateCustomerMandatoryFields_emailMissing_throwException() {
			final CreateUserDTO createUserDTO = new CreateUserDTO("Janos", "Descheemaeker", null, validAddressDTO, "0495188557");
			Exception exception = assertThrows(MandatoryFieldException.class, () -> {
				userService.validateMandatoryCustomerFields(createUserDTO);
			});
		}

		@Test
		@DisplayName("Validate firstname missing")
		void validateCustomerMandatoryFields_firstnameMissing_throwException() {
			final CreateUserDTO createUserDTO = new CreateUserDTO(null, "Descheemaeker", validMailAddress, validAddressDTO, "0495188557");
			Exception exception = assertThrows(MandatoryFieldException.class, () -> {
				userService.validateMandatoryCustomerFields(createUserDTO);
			});
		}

		@Test
		@DisplayName("Validate lastname missing")
		void validateCustomerMandatoryFields_lastnameMissing_throwException() {
			final CreateUserDTO createUserDTO = new CreateUserDTO("Janos", null, validMailAddress, validAddressDTO, "0495188557");
			Exception exception = assertThrows(MandatoryFieldException.class, () -> {
				userService.validateMandatoryCustomerFields(createUserDTO);
			});
		}

		@Test
		@DisplayName("Validate address missing")
		void validateCustomerMandatoryFields_addressMissing_throwException() {
			final CreateUserDTO createUserDTO = new CreateUserDTO("Janos", "Descheemaeker", validMailAddress, null, "0495188557");
			Exception exception = assertThrows(MandatoryFieldException.class, () -> {
				userService.validateMandatoryCustomerFields(createUserDTO);
			});
		}

		@Test
		@DisplayName("Validate phonenumber missing")
		void validateCustomerMandatoryFields_phonenumberMissing_throwException() {
			final CreateUserDTO createUserDTO = new CreateUserDTO("Janos", "Descheemaeker", validMailAddress, validAddressDTO, null);
			Exception exception = assertThrows(MandatoryFieldException.class, () -> {
				userService.validateMandatoryCustomerFields(createUserDTO);
			});
		}

	}

	@Nested
	@DisplayName("Validate address tests")
	class validateAddress {

		@Test
		@DisplayName("Validate valid address")
		void validateAddress() {
			userService.validateAddress(validAddressDTO);
		}

		@Test
		@DisplayName("Validate street missing")
		void validateAddress_streetMissing_throwException(){
			final AddressDTO addressDTO = new AddressDTO(null, "12", "9000", "Gent", "Belgium");
			Exception exception = assertThrows(MandatoryFieldException.class, () -> {
				userService.validateAddress(addressDTO);
			});
		}

		@Test
		@DisplayName("Validate number missing")
		void validateAddress_numberMissing_throwException(){
			final AddressDTO addressDTO = new AddressDTO("street", null, "9000", "Gent", "Belgium");
			Exception exception = assertThrows(MandatoryFieldException.class, () -> {
				userService.validateAddress(addressDTO);
			});
		}

		@Test
		@DisplayName("Validate postal code missing")
		void validateAddress_postalCodeMissing_throwException(){
			final AddressDTO addressDTO = new AddressDTO("street", "12", null, "Gent", "Belgium");
			Exception exception = assertThrows(MandatoryFieldException.class, () -> {
				userService.validateAddress(addressDTO);
			});
		}

		@Test
		@DisplayName("Validate city missing")
		void validateAddress_cityMissing_throwException(){
			final AddressDTO addressDTO = new AddressDTO("street", "12", "9000", null, "Belgium");
			Exception exception = assertThrows(MandatoryFieldException.class, () -> {
				userService.validateAddress(addressDTO);
			});
		}

		@Test
		@DisplayName("Validate country missing")
		void validateAddress_countryMissing_throwException(){
			final AddressDTO addressDTO = new AddressDTO("street", "12", "9000", "Gent", null);
			Exception exception = assertThrows(MandatoryFieldException.class, () -> {
				userService.validateAddress(addressDTO);
			});
		}
	}

}