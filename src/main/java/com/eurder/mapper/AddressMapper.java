package com.eurder.mapper;

import com.eurder.domain.user.Address;
import com.eurder.dto.user.AddressDTO;
import org.springframework.stereotype.Component;

@Component
public class AddressMapper {

	public Address mapToDomain(AddressDTO addressDTO){
		return new Address();
	}

}
