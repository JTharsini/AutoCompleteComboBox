import java.awt.BorderLayout;

import javax.swing.JFrame;

public class SearchBoxBindingTest {

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());

		SuggestorPanel suggestorPanel = new SuggestorPanel();
		suggestorPanel.setModel(new SuggestorPanelModel());
		suggestorPanel.initDataBinding();
		frame.add(suggestorPanel, BorderLayout.CENTER);

		/*SuggestorPanelForWrapper suggestorPanelForWrapper = new SuggestorPanelForWrapper();
		suggestorPanelForWrapper.setModel(new SuggestorPanelModelForWrapper());
		suggestorPanelForWrapper.initDataBinding();
		frame.add(suggestorPanelForWrapper, BorderLayout.CENTER);*/

		frame.setSize(200, 70);
		frame.setVisible(true);
	}
}