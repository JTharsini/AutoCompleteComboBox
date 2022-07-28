import java.beans.Beans;
import java.util.ArrayList;
import java.util.List;

public class SuggestorPanelModel extends AbstractModelObject {
	private String typedText = "";
	private List<User> userMatchedForThreeChars = new ArrayList<>();
	private List<User> userFiltered = new ArrayList<>();
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
		if (typedText.length() == 3) {
			typedCharCountIsThree(typedText);
		} else if (typedText.length() > 3) {
			setUserFiltered(filterUser(typedText));
		} else {
			setUserFiltered(new ArrayList<User>());
		}
	}

	private void typedCharCountIsThree(String typedText) {
		userMatchedForThreeChars = loadDataFromServer(typedText);
		setUserFiltered(userMatchedForThreeChars);
	}

	private List<User> filterUser(String typedText) {
		ArrayList<User> list = new ArrayList<>();
		for (int i = 1; i < userMatchedForThreeChars.size(); i++) {
			if (userMatchedForThreeChars.get(i).getUserName()
					.startsWith(typedText)) {
				list.add(userMatchedForThreeChars.get(i));
			}
		}
		return list;
	}

	private List<User> loadDataFromServer(String typedText) {
		return new Dummy().getFilteredUsers(typedText);
	}

	public List<User> getUserMatchedForThreeChars() {
		return userMatchedForThreeChars;
	}

	public void setUserMatchedForThreeChars(List<User> userMatchedForThreeChars) {
		this.userMatchedForThreeChars = userMatchedForThreeChars;
	}

	public List<User> getUserFiltered() {
		return userFiltered;
	}
	
	private boolean containsName(List<User> userFiltered, String text)
	{
		for(User userWrapper : userFiltered)
		{
			if(userWrapper.getUserName().equalsIgnoreCase(text))
			{
				return true;
			}
		}
		return false;
	}

	public void setUserFiltered(List<User> userFiltered) {
		if(!containsName(userFiltered, typedText))
		{
			userFiltered.add(0, new User(typedText));// problematic
		}
		List<User> oldValue = this.userFiltered;
		this.userFiltered = userFiltered;
		firePropertyChange("userFiltered", oldValue, this.userFiltered);
		if (userFiltered.size() != 1) {
			setShowingUsers(true);
		} else {
			setShowingUsers(false);
		}
	}
}