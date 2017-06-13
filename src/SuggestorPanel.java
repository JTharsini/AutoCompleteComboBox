import java.awt.CardLayout;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JPanel;

import org.jdesktop.beansbinding.AutoBinding;
import org.jdesktop.beansbinding.BeanProperty;
import org.jdesktop.beansbinding.Bindings;
import org.jdesktop.swingbinding.JComboBoxBinding;
import org.jdesktop.swingbinding.SwingBindings;

public class SuggestorPanel extends JPanel {
	JComboBox<User> c;
	private SuggestorPanelModel model;

	public SuggestorPanel() {
		this.setLayout(new CardLayout());
		this.add(getComboBox(), "c1");
	}

	void initDataBinding() {
		BeanProperty<SuggestorPanelModel, List<User>> beanProperty = BeanProperty
				.create("userFiltered");
		JComboBoxBinding<User, SuggestorPanelModel, JComboBox> jComboBinding = SwingBindings
				.createJComboBoxBinding(AutoBinding.UpdateStrategy.READ,
						getModel(), beanProperty, getComboBox());
		jComboBinding.bind();

		BeanProperty<JComboBox, String> comboTextProperty = BeanProperty
				.create("editor.editorComponent.text");
		BeanProperty<SuggestorPanelModel, String> typedTextProperty = BeanProperty
				.create("typedText");
		AutoBinding<JComboBox, String, SuggestorPanelModel, String> comboBinding = Bindings
				.createAutoBinding(AutoBinding.UpdateStrategy.READ_WRITE,
						getComboBox(), comboTextProperty, getModel(),
						typedTextProperty);
		comboBinding.bind();

		BeanProperty<SuggestorPanelModel, Boolean> showingUsersProperty = BeanProperty
				.create("showingUsers");
		BeanProperty<JComboBox, Boolean> showingUsersPropertyInPanel = BeanProperty
				.create("popupVisible");
		AutoBinding<SuggestorPanelModel, Boolean, JComboBox, Boolean> showingUsersBinding = Bindings
				.createAutoBinding(AutoBinding.UpdateStrategy.READ_WRITE,
						getModel(), showingUsersProperty, getComboBox(),
						showingUsersPropertyInPanel);
		showingUsersBinding.bind();
	}

	private SuggestorPanelModel getModel() {
		return model;
	}

	private JComboBox<User> getComboBox() {
		if (c == null) {
			c = new JComboBox<User>();
			c.setEditable(true);
		}
		return c;
	}

	public void setModel(SuggestorPanelModel suggestorPanelModel) {
		this.model = suggestorPanelModel;
	}
}
