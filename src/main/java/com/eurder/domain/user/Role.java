package com.eurder.domain.user;

import com.eurder.dto.user.CreateUserDTO;

import java.util.ArrayList;
import java.util.List;

public enum Role {
	CUSTOMER(
			new ArrayList<>(){{
				add(Feature.CREATE_ORDER);
			}}
	),
	ADMIN(
			new ArrayList<>(){{
				add(Feature.CREATE_ITEM);
				add(Feature.UPDATE_ITEM);
				add(Feature.CREATE_ADMIN);
			}}
	);

	private final List<Feature> features;

	Role(List<Feature> features) {
		this.features = features;
	}

	public static CreateUserDTO setRoleToCustomer(CreateUserDTO user){
		return user.setRole(CUSTOMER);
	}
	public static CreateUserDTO setRoleToAdmin(CreateUserDTO user){
		return user.setRole(ADMIN);
	}


	public boolean hasAccessTo(Feature feature){
		return features.contains(feature);
	}
}
