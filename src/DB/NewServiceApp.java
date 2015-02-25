package DB;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.xml.sax.SAXException;

public class NewServiceApp extends JFrame implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final String Country = "Country";
	private static final String BuisnessArea = "Buisness Area";
	private static final String BuisnessDomain = "Buisness Domain";
	private static final String Service = "Service";
	private JDialog dialog = null;
	private JPanel mainPanel = null;
	private JPanel bianPanel = null;
	private JPanel codePanel = null;
	private JPanel buttonPanel = null;
	private JPanel emptyPanel2 = null;
	private JPanel emptyPanel4 = null;
	private JPanel emptyPanel5 = null;
	private JPanel emptyPanel6 = null;
	private JPanel emptyPanel7 = null;
	private JPanel emptyPanel8 = null;
	private JLabel country = null;
	private JLabel buisnessArea = null;
	private JLabel buisnessDomain = null;
	private JLabel service = null;
	private JLabel projectPath = null;
	private JTextField countryTF = null;
	private JTextField buisnessAreaTF = null;
	private JTextField buisnessDomainTF = null;
	private JTextField serviceTF = null;
	private JTextField projectPathTF = null;
	private JTextField destabtf;
	private JTextField propPathtf;
	private JTextField sampleBuildTF = null;
	private JButton createNewService = null;
	public BIAN bian = null;
	public File codePathFile = null;
	
	private JPanel emptyPanel3;
	JTextArea log;
	JScrollPane logScrollPane;
	JButton openProject = null;
	JFileChooser propertyCode;
	Logger writeLog = null;
	private JPanel designPanel = null;
	private JPanel buildPanel = null;
	private JButton opendesinerTab;
	private JButton openPropPath;
	private JButton openSampleBuild;
	JFileChooser propertyFC;
	JFileChooser designerFC;
	JFileChooser buildFC = null;
	private JLabel sampleBuild;
	private JPanel propertyPanel;
	JLabel desinerLB;
	JLabel propertyLB;
	Logger logger = null;
	FileHandler fh;
	public File propertyFile;
	public File designerFile;
	public File sampleBuildFile;
	private File reqBuildFile;
	private File reqCFGFile;
	public File getCodePathFile() {
		return codePathFile;
	}

	public void setCodePathFile(File codePathFile) {
		this.codePathFile = codePathFile;
	}

	public File getPropertyFile() {
		return propertyFile;
	}

	public void setPropertyFile(File propertyFile) {
		this.propertyFile = propertyFile;
	}

	public File getDesignerFile() {
		return designerFile;
	}

	public void setDesignerFile(File designerFile) {
		this.designerFile = designerFile;
	}

	public File getSampleBuildFile() {
		return sampleBuildFile;
	}

	public void setSampleBuildFile(File sampleBuildFile) {
		this.sampleBuildFile = sampleBuildFile;
	}


	public static void main(String[] args) throws SecurityException,
			IOException {

		NewServiceApp app = new NewServiceApp();
		SimpleFormatter formatter = new SimpleFormatter();  
       	app.fh = new FileHandler("D:/MyLogFile.log");
        app.fh.setFormatter(formatter); 
		app.logger = Logger.getLogger("MyLog");
		app.logger.addHandler(app.fh);
		app.createBuildLayout();

	}

	private void createBuildLayout() {
		dialog = new JDialog(this, "New Service Creator");
		createPanel();
		createLabels();
		createTexts();
		createButtons();
		AddComponents();

	}

	private void createButtons() {
		createNewService = new JButton("Create New Service");
		createNewService.addActionListener(this);
		openProject = new JButton("openProject");
		openProject.setPreferredSize(new Dimension(30, 21));
		openProject.addActionListener(this);
		opendesinerTab = new JButton("open");
		openPropPath = new JButton("open");
		openPropPath.setPreferredSize(new Dimension(30, 21));
		opendesinerTab.setPreferredSize(new Dimension(30, 21));
		opendesinerTab.addActionListener(this);
		openPropPath.addActionListener(this);
		openSampleBuild = new JButton("open");
		openSampleBuild.setPreferredSize(new Dimension(30, 21));
		openSampleBuild.addActionListener(this);

	}

	private void createTexts() {
		countryTF = new JTextField(15);
		buisnessAreaTF = new JTextField(15);
		buisnessDomainTF = new JTextField(15);
		serviceTF = new JTextField(15);
		projectPathTF = new JTextField(15);
		sampleBuildTF = new JTextField(15);
		destabtf = new JTextField(30);
		propPathtf = new JTextField(30);

	}

	private void createLabels() {
		country = new JLabel(Country, JLabel.CENTER);
		buisnessArea = new JLabel(BuisnessArea, JLabel.CENTER);
		buisnessDomain = new JLabel(BuisnessDomain, JLabel.CENTER);
		service = new JLabel(Service, JLabel.CENTER);
		Dimension d1 = country.getPreferredSize();
		country.setPreferredSize(new Dimension(d1.width + 10, d1.height));
		buisnessArea.setPreferredSize(new Dimension(d1.width + 45, d1.height));
		buisnessDomain
				.setPreferredSize(new Dimension(d1.width + 60, d1.height));
		service.setPreferredSize(new Dimension(d1.width + 10, d1.height));
		projectPath = new JLabel("Project Path", JLabel.CENTER);
		projectPath.setPreferredSize(new Dimension(d1.width + 36, d1.height));
		sampleBuild = new JLabel("Sample Build", JLabel.CENTER);
		sampleBuild.setPreferredSize(new Dimension(d1.width + 36, d1.height));
		desinerLB = new JLabel("DesignerFile", JLabel.CENTER);
		desinerLB.setPreferredSize(new Dimension(d1.width + 36, d1.height));
		propertyLB = new JLabel("PropertyFile", JLabel.CENTER);
		propertyLB.setPreferredSize(new Dimension(d1.width + 36, d1.height));
	}

	private void AddComponents() {
		bianPanel.add(country);
		bianPanel.add(Box.createGlue());
		bianPanel.add(countryTF);
		bianPanel.add(Box.createGlue());
		bianPanel.add(buisnessArea);
		bianPanel.add(Box.createGlue());
		bianPanel.add(buisnessAreaTF);
		bianPanel.add(Box.createGlue());
		bianPanel.add(buisnessDomain);
		bianPanel.add(Box.createGlue());
		bianPanel.add(buisnessDomainTF);
		bianPanel.add(Box.createGlue());
		bianPanel.add(service);
		bianPanel.add(Box.createGlue());
		bianPanel.add(serviceTF);
		bianPanel.add(Box.createGlue());
		codePanel.add(projectPath);
		codePanel.add(Box.createGlue());
		codePanel.add(projectPathTF);
		codePanel.add(Box.createGlue());
		codePanel.add(openProject);
		codePanel.add(Box.createGlue());
		codePanel.add(emptyPanel4);
		codePanel.add(Box.createGlue());
		buildPanel.add(sampleBuild);
		buildPanel.add(Box.createGlue());
		buildPanel.add(sampleBuildTF);
		buildPanel.add(Box.createGlue());
		buildPanel.add(openSampleBuild);
		buildPanel.add(Box.createGlue());
		buildPanel.add(emptyPanel6);
		buildPanel.add(Box.createGlue());
		designPanel.add(desinerLB);
		designPanel.add(Box.createGlue());
		designPanel.add(destabtf);
		designPanel.add(Box.createGlue());
		designPanel.add(opendesinerTab);
		designPanel.add(Box.createGlue());
		designPanel.add(emptyPanel7);
		designPanel.add(Box.createGlue());
		propertyPanel.add(propertyLB);
		propertyPanel.add(Box.createGlue());
		propertyPanel.add(propPathtf);
		propertyPanel.add(Box.createGlue());
		propertyPanel.add(openPropPath);
		propertyPanel.add(Box.createGlue());
		propertyPanel.add(emptyPanel8);
		propertyPanel.add(Box.createGlue());
		buttonPanel.add(createNewService);
		mainPanel.add(emptyPanel3);
		mainPanel.add(bianPanel);
		mainPanel.add(emptyPanel5);
		mainPanel.add(codePanel);
		mainPanel.add(buildPanel);
		mainPanel.add(designPanel);
		mainPanel.add(propertyPanel);
		mainPanel.add(buttonPanel);
		mainPanel.add(emptyPanel2);
		dialog.add(mainPanel);
		Dimension d2 = dialog.getPreferredSize();
		dialog.setVisible(true);
		dialog.setSize(new Dimension(d2.width + 100, d2.height + 400));
		dialog.pack();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	private void createPanel() {
		mainPanel = new JPanel();
		bianPanel = new JPanel();
		codePanel = new JPanel();
		buttonPanel = new JPanel();
		designPanel = new JPanel();
		buildPanel = new JPanel();
		propertyPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		bianPanel.setLayout(new BoxLayout(bianPanel, BoxLayout.X_AXIS));
		codePanel.setLayout(new BoxLayout(codePanel, BoxLayout.X_AXIS));
		buildPanel.setLayout(new BoxLayout(buildPanel, BoxLayout.X_AXIS));
		designPanel.setLayout(new BoxLayout(designPanel, BoxLayout.X_AXIS));
		propertyPanel.setLayout(new BoxLayout(propertyPanel, BoxLayout.X_AXIS));
		TitledBorder bian;
		bian = BorderFactory.createTitledBorder("BIAN");
		bianPanel.setBorder(bian);
		emptyPanel2 = new JPanel();
		emptyPanel3 = new JPanel();
		emptyPanel2.setPreferredSize(new Dimension(250, 20));
		emptyPanel4 = new JPanel();
		emptyPanel4.setPreferredSize(new Dimension(500, 20));
		emptyPanel5 = new JPanel();
		emptyPanel6 = new JPanel();
		emptyPanel6.setPreferredSize(new Dimension(500, 20));
		emptyPanel7 = new JPanel();
		emptyPanel7.setPreferredSize(new Dimension(500, 20));
		emptyPanel8 = new JPanel();
		emptyPanel8.setPreferredSize(new Dimension(500, 20));

	}

	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == openProject) {
			
			getFilesFromSystem(propertyCode, codePathFile, projectPathTF, "code");
			
		}

		if (e.getSource() == openPropPath) {
		
			getFilesFromSystem(propertyFC, propertyFile, propPathtf, "prop");
			
		}
		if (e.getSource() == opendesinerTab) {
			
			getFilesFromSystem(designerFC, this.designerFile, destabtf, "design");
			
			
		}

		if (e.getSource() == openSampleBuild) {
		
			getFilesFromSystem(buildFC, sampleBuildFile, sampleBuildTF, "sample"); 
			
		}

		if (e.getSource() == createNewService) {
			try {
				//createNewService.setEnabled(false);
				getValuesFromApp();
				createconfPaths();
				if (!createBuildXML()) {
					logger.info("Logging off .....");
					dialog.dispose();
				}
				
				if (!createCFG()) {
					logger.info("Logging off .....");
					dialog.dispose();
				}
				

			} catch (IOException e2) {
				logger.log(Level.SEVERE, e2.getMessage(), e2);
				e2.getMessage();
			} catch (SAXException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (ParserConfigurationException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (TransformerException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			
		}

	}

	private boolean createCFG() throws IOException {
		CFGCreator cfgcreate = new CFGCreator(designerFile, propertyFile,
				reqCFGFile);
		cfgcreate.setLogger(logger);
		boolean isSuccess = cfgcreate.startOps();
		if (isSuccess) {
			String message = "CFG file generated in "
					+ reqCFGFile.getAbsolutePath();
			JOptionPane.showMessageDialog(new JFrame(), message, "Dialog",
					JOptionPane.INFORMATION_MESSAGE);
			logger.info(message);
			System.out.println(message);
			return true;

		} else {
			String message = "There is a problem generating CFG please check the logs .....deleteing build.xml and CFG file under";
			logger.info(message);
			JOptionPane.showMessageDialog(new JFrame(), message, "Dialog",
					JOptionPane.ERROR_MESSAGE);
			System.out.println(message);
			reqBuildFile.delete();
			reqCFGFile.delete();
			return false;

		}

	}

	private Boolean createBuildXML() throws SAXException, IOException,
			ParserConfigurationException, TransformerException {

		BuildXmlCreator BX = new BuildXmlCreator(bian, sampleBuildFile,
				reqBuildFile);
		BX.setLogger(logger);
		boolean isSuccess = BX.startOps();
		if (isSuccess) {
			String message = "Build.xml generated in "
					+ reqBuildFile.getAbsolutePath();
			JOptionPane.showMessageDialog(new JFrame(), message, "Dialog",
					JOptionPane.INFORMATION_MESSAGE);
			System.out.println(message);
			logger.info(message);
			return true;

		} else {
			String message = "There is a problem generating build.xml please check the logs .....deleteing build.xml under"
					+ reqBuildFile.getAbsolutePath();
			JOptionPane.showMessageDialog(new JFrame(), message, "Dialog",
					JOptionPane.ERROR_MESSAGE);
			System.out.println(message);
			logger.info(message);
			reqBuildFile.delete();
			return false;

		}

	}

	private void createconfPaths() {
		NewServiceGenerator sergen = new NewServiceGenerator(bian);
		sergen.setLogger(logger);
		sergen.createNewService();
	}

	private void getValuesFromApp() throws IOException {
		String country = countryTF.getText().trim();
		String BA = buisnessAreaTF.getText().trim();
		String BD = buisnessDomainTF.getText().trim();
		String service = serviceTF.getText().trim();
		bian = new BIAN(country, BA, BD, service);
		

		if (validateMadatoryFeilds() == false) {
			
			logger.info("please enter all valid feilds");
		} else {
			bian.setCodePath(codePathFile.getAbsolutePath().trim());
			bian.createPaths();
			bian.setIs_oneservice(true);
			generateFilePaths();

		}

	}

	private void generateFilePaths() {
		String BUildXMLPath = bian.getServiceCodePath() + "\\" + "build.xml";
		logger.info("Build.xml path is " + BUildXMLPath);
		reqBuildFile = new File(BUildXMLPath);
		String CFGFilePath = bian.getABS_CFG_PATH() + "\\" + bian.getBIAN()
				+ "\\" + bian.getService() + ".cfg";
		reqCFGFile = new File(CFGFilePath);
		logger.info("CFG File path is " + CFGFilePath);

	}

	private Boolean validateMadatoryFeilds() {
		
		if(codePathFile == null)
		{
			System.out.println("1");
		}
		
		if(designerFile == null)
		{
			System.out.println("1");
		}
		if(propertyFile == null)
		{
			System.out.println("1");
		}
		if(sampleBuildFile == null)
		{
			System.out.println("1");
		}
		if (bian.getCountry().isEmpty() == false
				&& bian.getBD().isEmpty() == false
				&& bian.getService().isEmpty() == false
				&& bian.getBA().isEmpty() == false && codePathFile != null
				&& designerFile != null && propertyFile != null
				&& sampleBuildFile != null) {
			return true;
		}
		logger.info("All feilds are mandatory , please enter all feilds");
		JOptionPane.showMessageDialog(new JFrame(),
				"All feilds are mandatory , please enter all feilds", "Dialog",
				JOptionPane.INFORMATION_MESSAGE);
		// createNewService.setEnabled(false);
		return false;
	}

	private void getFilesFromSystem(JFileChooser fileChooser, File filePath,
			JTextField absPath, String fileType) {

		fileChooser = new JFileChooser();
	
		
		if (fileType.equalsIgnoreCase("code")) {
			fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		}

		int returnVal = fileChooser.showOpenDialog(this);

		if (returnVal == JFileChooser.APPROVE_OPTION) {
			filePath = new File(fileChooser.getSelectedFile().getAbsolutePath());
			absPath.setText(filePath.getAbsolutePath());
			 if(fileType.equalsIgnoreCase("code"))
			 {
				 setCodePathFile(filePath);
			 }
			 if(fileType.equalsIgnoreCase("prop"))
			 {
				 setPropertyFile(filePath);
			 }
			 if(fileType.equalsIgnoreCase("design"))
			 {
				 setDesignerFile(filePath);
			 }
			 if(fileType.equalsIgnoreCase("sample"))
			 {
				 setSampleBuildFile(filePath);
			 }
			

		} else {
			logger.info("Open command cancelled by user.");
		}

	}

}
