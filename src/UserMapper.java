import java.util.ArrayList;
import java.util.List;

public final class UserMapper {
	private UserMapper() {

	}

	public static List<UserWrapper> toUserWrapperList(List<User> userList) {
		List<UserWrapper> userWrapperList = new ArrayList<>();
		for (User user : userList) {
			userWrapperList.add(new UserWrapper(user));
		}
		return userWrapperList;
	}

	public static List<User> toUserList(List<UserWrapper> userWrapperList) {
		List<User> userList = new ArrayList<>();
		for (UserWrapper userWrapper : userWrapperList) {
			userList.add(userWrapper.getUser());
		}
		return userList;
	}
}