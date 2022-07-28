import java.util.ArrayList;

public class Dummy {
	private ArrayList<User> users = new ArrayList<>();

	Dummy() {
		for (int i = 0; i < 3; i++) {
			users.add(new User("User: " + i));
		}
		for (int i = 0; i < 3; i++) {
			users.add(new User("JayJayUser: " + i));
		}
		for (int i = 0; i < 3; i++) {
			users.add(new User("MathuUser: " + i));
		}
		for (int i = 0; i < 3; i++) {
			users.add(new User("MathuraUser: " + i));
		}
		for (int i = 0; i < 3; i++) {
			users.add(new User("MathumithaUser: " + i));
		}
	}

	public ArrayList<User> getFilteredUsers(String filterString) {
		ArrayList<User> list = new ArrayList<>();
		for (User user : users) {
			if (user.getUserName().startsWith(filterString)) {
				list.add(user);
			}
		}
		return list;
	}
}