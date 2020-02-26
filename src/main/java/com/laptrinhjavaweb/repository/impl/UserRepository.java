package com.laptrinhjavaweb.repository.impl;

import java.util.List;

import com.laptrinhjavaweb.entity.UserEntity;
import com.laptrinhjavaweb.repository.IUserRepository;

public class UserRepository extends SimpleJPARepository<UserEntity> implements IUserRepository {

	@Override
	public List<UserEntity> findByRole(String roleCode) {
		StringBuilder sql = new StringBuilder("SELECT * FROM user u");
		sql.append(" INNER JOIN user_role ur ON u.id = ur.userid");
		sql.append(" INNER JOIN role r ON ur.roleid = r.id");
		sql.append(" where r.code = '" + roleCode + "'");
		return this.findAll(sql.toString());
	}

}
