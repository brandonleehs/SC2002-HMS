package hms.repository;

import java.util.List;

import hms.entity.user.User;

public interface IUserRepository<E extends User> {
	public E getById(String id);

	public void removeById(String id);

	public List<E> getAll();
}
