package course.contact.dao;

public interface ContactDao {
    public boolean createContact(int readerId, int toReadId);
    public boolean readConnectedUsers(int userReaderId, int userToReadId);
    public boolean deleteContact(int readerId, int toReadId);
}
