package hms.repository;

import hms.entity.user.User;

public interface IUserRepository<E extends User> {
	public E getById(String id);
}
