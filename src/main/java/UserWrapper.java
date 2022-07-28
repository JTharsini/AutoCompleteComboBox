
public class UserWrapper {
	private User user;
	String dummy;

	public UserWrapper(User user) {
		super();
		this.user = user;
	}
	
	public UserWrapper(String typedText) {
		this.dummy = typedText;
	}

	public User getUser() {
		return user;
	}

	@Override
	public String toString()
	{
		return user != null ? user.getUserName() : "";
	}
}
