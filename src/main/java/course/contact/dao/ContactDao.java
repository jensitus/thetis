package course.contact.dao;

public interface ContactDao {
    public boolean createContact(int readerId, int toReadId);
    public boolean readConnectedUsers(int userReaderId, int userToReadId);
}
