import java.util.ArrayList;

public class SuggestorPanelModel extends AbstractModelObject{
	private String typedText = "";
	private ArrayList<User> userMatchedForThreeChars = new ArrayList<User>();
	private ArrayList<User>userFiltered = new ArrayList<User>();
	private boolean showingUsers = false;

	public boolean isShowingUsers() {
		return showingUsers;
	}
	public void setShowingUsers(boolean showingUsers) {
		boolean oldValue = this.showingUsers;
		this.showingUsers = showingUsers;
	    firePropertyChange("showingUsers", oldValue, this.showingUsers);
	}
	public String getTypedText() {
		return typedText;
	}
	public void setTypedText(String typedText) {
		this.typedText = typedText;
		System.out.println(typedText);
		if(typedText.length() == 3)
		{
			userMatchedForThreeChars = loadDataFromServer(typedText);
			setUserFiltered(userMatchedForThreeChars);
			setShowingUsers(true);
		}
		else if(typedText.length() > 3)
		{
			setUserFiltered(new ArrayList<User>());
			setUserFiltered(filterUser(typedText));
			setShowingUsers(true);
		}
		if(userMatchedForThreeChars.size() == 0 && typedText.length() == 3)
		{
			userMatchedForThreeChars = loadDataFromServer(typedText);
			setUserFiltered(userMatchedForThreeChars);
			setShowingUsers(true);
		}
	}

	private ArrayList<User> filterUser(String typedText) {
		ArrayList<User>list = new ArrayList<>();
		for(User user: userMatchedForThreeChars)
		{
			if(user.getUserName().startsWith(typedText))
			{
				list.add(user);
			}
		}
		return list;
	}
	private ArrayList<User> loadDataFromServer(String typedText) {
		return new Dummy().getFilteredUsers(typedText);
	}
	public ArrayList<User> getUserMatchedForThreeChars() {
		return userMatchedForThreeChars;
	}
	public void setUserMatchedForThreeChars(ArrayList<User> userMatchedForThreeChars) {
		this.userMatchedForThreeChars = userMatchedForThreeChars;
	}
	public ArrayList<User> getUserFiltered() {
		return userFiltered;
	}
	public void setUserFiltered(ArrayList<User> userFiltered) {
		userFiltered.add(0, new User(typedText));
		ArrayList<User> oldValue = this.userFiltered;
		this.userFiltered = userFiltered;
	    firePropertyChange("userFiltered", oldValue, this.userFiltered);
	}
}