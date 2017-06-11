import java.util.ArrayList;
import java.util.List;

public class SuggestorPanelModelForWrapper extends AbstractModelObject {
	private String typedText = "";
	private List<UserWrapper> userMatchedForThreeChars = new ArrayList<>();
	private List<UserWrapper> userFiltered = new ArrayList<>();
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
			setUserFiltered(new ArrayList<UserWrapper>());
		}
	}

	private void typedCharCountIsThree(String typedText) {
		userMatchedForThreeChars = loadDataFromServer(typedText);
		setUserFiltered(userMatchedForThreeChars);
	}

	private List<UserWrapper> filterUser(String typedText) {
		ArrayList<UserWrapper> list = new ArrayList<>();
		for (int i = 0; i < userMatchedForThreeChars.size(); i++) {
			if (userMatchedForThreeChars.get(i).getUser() != null && userMatchedForThreeChars.get(i).getUser().getUserName()
					.startsWith(typedText)) {
				list.add(userMatchedForThreeChars.get(i));
			}
		}
		return list;
	}

	private List<UserWrapper> loadDataFromServer(String typedText) {
		return UserMapper.toUserWrapperList(new Dummy()
				.getFilteredUsers(typedText));
	}

	public List<UserWrapper> getUserMatchedForThreeChars() {
		return userMatchedForThreeChars;
	}

	public void setUserMatchedForThreeChars(
			List<UserWrapper> userMatchedForThreeChars) {
		this.userMatchedForThreeChars = userMatchedForThreeChars;
	}

	public List<UserWrapper> getUserFiltered() {
		return userFiltered;
	}

	public void setUserFiltered(List<UserWrapper> userFiltered) {
		//userFiltered.add(0, new UserWrapper(typedText)); problematic
		List<UserWrapper> oldValue = this.userFiltered;
		this.userFiltered = userFiltered;
		firePropertyChange("userFiltered", oldValue, this.userFiltered);
		if (!(userFiltered.isEmpty())) {
			setShowingUsers(true);
		} else {
			setShowingUsers(false);
		}
	}
}