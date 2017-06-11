
public class UserWrapper {
	private User user;
	private String dummy;
	
	public UserWrapper(String dummy)
	{
		this.dummy = dummy;
	}

	public UserWrapper(User user) {
		super();
		this.user = user;
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
