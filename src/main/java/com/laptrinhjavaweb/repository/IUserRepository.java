package com.laptrinhjavaweb.repository;

import java.util.List;

import com.laptrinhjavaweb.entity.UserEntity;

public interface IUserRepository extends IJPARepository<UserEntity> {

	List<UserEntity> findByRole(String roleCode);
}
