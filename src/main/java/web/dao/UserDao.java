package web.dao;

import web.model.User;

import java.util.List;

public interface UserDao {
    public List<User> getUsers();

    public void saveUser(User user);

    public User getUser(long id);

    public void deleteUser(long id);

    public User findByEmail(String email);

    public User findByUsername(String username);
}
