package hangmanGUI;

import hangmanPackage.Preferences;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.border.BevelBorder;
import javax.swing.SwingConstants;
import javax.swing.JCheckBox;

public class PreferencesGUI extends JDialog {

	private static final long serialVersionUID = 1094692549512841333L;
	private final JPanel contentPanel = new JPanel();
	private static  PreferencesGUI dialog;
	private JTextField txtFileName;
	//Create a file chooser
	private  JFileChooser fc;
	private static Config config;
	private JCheckBox cbShowWordAfterResign;
	private JCheckBox cbShowWordAfterLoss;

	/**
	 * Launch the application.
	 */
	public static void launch(Config config) {
		try {
			//PreferencesGUI.config = config;
			dialog = new PreferencesGUI(config);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * Create the dialog.
	 */
	public PreferencesGUI(Config config) {
		this.config = config;
		setTitle("Hangman Preferences");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblWordFile = new JLabel("Word File:");
			lblWordFile.setHorizontalAlignment(SwingConstants.RIGHT);
			lblWordFile.setBounds(8, 14, 65, 14);
			contentPanel.add(lblWordFile);
		}
		{
			txtFileName = new JTextField();
			txtFileName.setBounds(79, 11, 257, 20);
			contentPanel.add(txtFileName);
			txtFileName.setColumns(35);
		}
		{
			JButton btnBrowse = new JButton("Browse...");
			btnBrowse.setBounds(339, 10, 93, 23);
			btnBrowse.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					//In response to a button click:
					fc = new JFileChooser();
					int returnVal = fc.showOpenDialog(PreferencesGUI.this);
					if (returnVal == JFileChooser.APPROVE_OPTION) {
			            File file = fc.getSelectedFile();
			            txtFileName.setText(file.getAbsolutePath());
					}
				}
			});
			contentPanel.add(btnBrowse);
		}
		
		cbShowWordAfterLoss = new JCheckBox("Show word after loss");
		cbShowWordAfterLoss.setBounds(79, 35, 179, 23);
		contentPanel.add(cbShowWordAfterLoss);
		{
			cbShowWordAfterResign = new JCheckBox("Show word after resign");
			cbShowWordAfterResign.setBounds(79, 61, 179, 23);
			contentPanel.add(cbShowWordAfterResign);
		}

		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						exportPreferences();
						PreferencesGUI.this.dispose();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						// This is an interesting syntax and it works...
						PreferencesGUI.this.dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
			importPreferences();
		}
	}
	public void importPreferences(){
		//config.getPreferences().load(config.getConfigFileName());
		txtFileName.setText(config.getPreferences().getWordFileName());
		cbShowWordAfterLoss.setSelected(config.getPreferences().getShowWordAfterLoss());
		cbShowWordAfterResign.setSelected(config.getPreferences().getShowWordAfterResign());
	}
	private void exportPreferences(){
	    config.getPreferences().setWordFileName(txtFileName.getText().trim());
	    config.getPreferences().setShowWordAfterLoss(cbShowWordAfterLoss.isSelected());
	    config.getPreferences().setShowWordAfterResign(cbShowWordAfterResign.isSelected());
	    config.getPreferences().save(config.getConfigFileName());	
	}
}
