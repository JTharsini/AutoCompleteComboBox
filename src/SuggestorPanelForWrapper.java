import java.awt.CardLayout;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JPanel;

import org.jdesktop.beansbinding.AutoBinding;
import org.jdesktop.beansbinding.BeanProperty;
import org.jdesktop.beansbinding.Bindings;
import org.jdesktop.swingbinding.JComboBoxBinding;
import org.jdesktop.swingbinding.SwingBindings;

public class SuggestorPanelForWrapper extends JPanel {
	JComboBox<UserWrapper> c;
	private SuggestorPanelModelForWrapper model;

	public SuggestorPanelForWrapper() {
		this.setLayout(new CardLayout());
		this.add(getComboBox(), "c1");
	}

	public void setIsShowingUsers(boolean showingUsers) {
		if (showingUsers) {
			getComboBox().setPopupVisible(true);
		} else {
			getComboBox().setPopupVisible(false);
		}
	}

	public boolean getIsShowingUsers() {
		return getComboBox().isPopupVisible();
	}

	void initDataBinding() {
		BeanProperty<SuggestorPanelModelForWrapper, List<UserWrapper>> beanProperty = BeanProperty
				.create("userFiltered");
		JComboBoxBinding<UserWrapper, SuggestorPanelModelForWrapper, JComboBox> jComboBinding = SwingBindings
				.createJComboBoxBinding(AutoBinding.UpdateStrategy.READ,
						getModel(), beanProperty, getComboBox());
		jComboBinding.bind();

		BeanProperty<JComboBox, String> comboTextProperty = BeanProperty
				.create("editor.editorComponent.text");
		BeanProperty<SuggestorPanelModelForWrapper, String> typedTextProperty = BeanProperty
				.create("typedText");
		AutoBinding<JComboBox, String, SuggestorPanelModelForWrapper, String> comboBinding = Bindings
				.createAutoBinding(AutoBinding.UpdateStrategy.READ_WRITE,
						getComboBox(), comboTextProperty, getModel(),
						typedTextProperty);
		comboBinding.bind();

		BeanProperty<SuggestorPanelModelForWrapper, Boolean> showingUsersProperty = BeanProperty
				.create("showingUsers");
		BeanProperty<JPanel, Boolean> showingUsersPropertyInPanel = BeanProperty
				.create("isShowingUsers");
		AutoBinding<SuggestorPanelModelForWrapper, Boolean, JPanel, Boolean> showingUsersBinding = Bindings
				.createAutoBinding(AutoBinding.UpdateStrategy.READ_WRITE,
						getModel(), showingUsersProperty, this,
						showingUsersPropertyInPanel);
		showingUsersBinding.bind();
	}

	private SuggestorPanelModelForWrapper getModel() {
		return model;
	}

	private JComboBox<UserWrapper> getComboBox() {
		if (c == null) {
			c = new JComboBox<UserWrapper>();
			c.setEditable(true);
		}
		return c;
	}

	public void setModel(SuggestorPanelModelForWrapper suggestorPanelModel) {
		this.model = suggestorPanelModel;
	}
}