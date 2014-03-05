package course.contact.dao;

import course.user.model.User;

import java.util.List;

public interface ContactDao {
    public boolean createContact(int readerId, int toReadId);
    public boolean readConnectedUsers(int userReaderId, int userToReadId);
    public boolean deleteContact(int readerId, int toReadId);
    public List<User> connectedWith(int userReaderId);
    public List<User> connectedBy(int userToReadId);
}
