package DB;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.*;

@SuppressWarnings("serial")
public class CFGApp extends JFrame implements ActionListener {

	JFileChooser filechooser = null;
	JPanel panel1 = null;
	JPanel panel2 = null;
	JPanel panel3 = null;
	JPanel panel4 = null;
	JPanel mainPanel = null;
	JTextArea log;
	JFileChooser propertyFC;
	JFileChooser designerFC;
	JButton generateCFG = null;
	JButton opendesinerTab = null;
	JButton openPropPath = null;
	JButton saveserviceCFG = null;
	JTextField destabtf;
	JTextField propPathtf;
	JTextField serviceNametf;
	JLabel desinerLB;
	JLabel propertyLB;
	JLabel serviceName;
	JFrame frame = null;
	File designerFile = null;
	File propertyFile = null;
	File serviceNameFile = null;
	BIAN bianCFG = null;
	String reqCFGFile = null;
	public CFGApp(BIAN bianStr) {
		bianCFG = bianStr;
	}

	public CFGApp() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		CFGApp app = new CFGApp();
		app.createlayout();

	}

	public void createlayout() {

		frame = new JFrame("CFG File Cretor");
		createPanel();
		createButton();
		createLabel();
		createTextPlanes();
		AttachComponents();

	}

	private void AttachComponents() {
		panel1.add(desinerLB, BorderLayout.WEST);
		panel1.add(destabtf, BorderLayout.CENTER);
		panel1.add(opendesinerTab, BorderLayout.EAST);
		panel2.add(propertyLB, BorderLayout.WEST);
		panel2.add(propPathtf, BorderLayout.CENTER);
		panel2.add(openPropPath, BorderLayout.EAST);
		panel3.add(serviceName, BorderLayout.WEST);
		panel3.add(serviceNametf, BorderLayout.CENTER);
		panel4.add(generateCFG);
		mainPanel.add(panel1);
		mainPanel.add(panel2);
		mainPanel.add(panel3);
		mainPanel.add(panel4);
		frame.add(mainPanel);
		// frame.pack();
		Dimension d2 = frame.getPreferredSize();
		frame.setSize(new Dimension(d2.width + 70, d2.height + 50));
		frame.setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setLocationRelativeTo(null);

	}

	private void createTextPlanes() {
		destabtf = new JTextField(30);
		propPathtf = new JTextField(30);
		if (bianCFG == null) {
			serviceNametf = new JTextField(10);
		} else {
			serviceNametf = new JTextField(bianCFG.getService(), 10);
			serviceNametf.setEditable(false);

		}
	}

	private void createLabel() {
		desinerLB = new JLabel("DesignerFile", JLabel.CENTER);
		propertyLB = new JLabel("PropertyFile", JLabel.CENTER);
		serviceName = new JLabel("  ServiceName", JLabel.CENTER);
		Dimension d1 = desinerLB.getPreferredSize();
		desinerLB.setPreferredSize(new Dimension(d1.width + 30, d1.height));
		propertyLB.setPreferredSize(new Dimension(d1.width + 30, d1.height));
		serviceName.setPreferredSize(new Dimension(d1.width + 30, d1.height));

	}

	private void createButton() {
		generateCFG = new JButton("Generate");
		opendesinerTab = new JButton("open");
		openPropPath = new JButton("open");
		opendesinerTab.setPreferredSize(new Dimension(30, 23));
		openPropPath.setPreferredSize(new Dimension(30, 23));
		opendesinerTab.addActionListener(this);
		generateCFG.addActionListener(this);
		openPropPath.addActionListener(this);

	}

	private void createPanel() {
		mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		panel1 = new JPanel(new BorderLayout());
		panel2 = new JPanel(new BorderLayout());
		panel3 = new JPanel(new BorderLayout());
		panel4 = new JPanel();

	}

	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == openPropPath) {
			propertyFC = new JFileChooser();

			int returnVal = propertyFC.showOpenDialog(this);

			if (returnVal == JFileChooser.APPROVE_OPTION) {
				propertyFile = propertyFC.getSelectedFile();
				propPathtf.setText(propertyFile.getAbsolutePath());
				generateCFG.setEnabled(true);
			} else {
				System.out.println("Open command cancelled by user.");
			}

		}
		if (e.getSource() == opendesinerTab) {
			designerFC = new JFileChooser();

			int returnVal = designerFC.showSaveDialog(this);
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				designerFile = designerFC.getSelectedFile();
				destabtf.setText(designerFile.getAbsolutePath());
				generateCFG.setEnabled(true);
			} else {
				System.out.println("Save command cancelled by user.");
			}

		}

		if (e.getSource() == generateCFG) {
			if (bianCFG == null) {
				bianCFG = new BIAN();
				bianCFG.setService(serviceNametf.getText().trim());
			}

			if (propertyFile != null && designerFile != null
					&& !bianCFG.getService().trim().isEmpty()) {

				
				if (bianCFG.getBIAN() != null) {
					reqCFGFile = bianCFG.getABS_CFG_PATH() + "\\"
							+ bianCFG.getBIAN() + "\\" + bianCFG.getService()
							+ ".cfg";
					serviceNameFile = new File(reqCFGFile);

				} else {
					String tempCFGPath = bianCFG.getABS_CFG_PATH() + "\\"
							+ "tempCFG";
					File temp = new File(tempCFGPath);
					if (!temp.isDirectory()) {
						boolean success = temp.mkdirs();
						if (success) {
							System.out.println(" temprarory Created path: "
									+ temp.getPath());
						} else {
							System.out.println("Could not create temp path: "
									+ temp.getPath());
						}
					} else {

						System.out.println("temp path exists: "
								+ temp.getPath());
					}

					reqCFGFile = tempCFGPath + "\\" + serviceName + ".cfg";
					serviceNameFile = new File(reqCFGFile);

				}

			}

			CFGCreator cfgcreate = new CFGCreator(designerFile, propertyFile,
					serviceNameFile);
			try {
				Boolean isSuccess = cfgcreate.startOps();
				if (isSuccess) {
					String message = "CFG file generated in " + reqCFGFile;
					 JOptionPane.showMessageDialog(new JFrame(), message, "Dialog",
						        JOptionPane.INFORMATION_MESSAGE);
					System.out.println("File generated"
							+ reqCFGFile);
					

				} else {
					File build = new File(bianCFG.getServiceCodePath()+"\\"+"build.xml");
					String message = "deleteing build.xnl under" + bianCFG.getServiceCodePath() ;
					JOptionPane.showMessageDialog(new JFrame(), message, "Dialog",
					        JOptionPane.INFORMATION_MESSAGE);
					System.out.println("File not generated");
					System.out.println(build.delete());
					//frame.dispose();
					
				}
			} catch (IOException e1) {

				e1.printStackTrace();
			}

		} /*else {
			System.out.println("please select property and designer file");
			generateCFG.setEnabled(false);
		}*/

	}
}
