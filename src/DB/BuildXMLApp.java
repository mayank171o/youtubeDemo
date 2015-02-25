package DB;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.swing.*;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.xml.sax.SAXException;


public class BuildXMLApp extends JFrame implements ActionListener {

	/**
	 * 
	 */
	static private final String newline = "\n";
	private static final long serialVersionUID = 1L;
	private static final String Country = "Country";
	private static final String BuisnessArea = "Buisness Area";
	private static final String BuisnessDomain = "Buisness Domain";	
	private static final String Service = "Service";
	private static final String SampleBuildXML = "Sample Build.xml";
	private JFrame frame = null;
	private JPanel mainPanel = null;
	private JPanel countryP1 = null;
	private JPanel buisnessAreaP2 = null;
	private JPanel buisnessDomainP3 = null;
	private JPanel serviceP4 = null;
	private JPanel sampleBuildP5 = null;
	private JPanel generateP6 = null;
	private JPanel emptyP7 = null;
	private JLabel country = null;
	private JLabel buisnessArea = null;
	private JLabel buisnessDomain = null;
	private JLabel service = null;
	private JLabel sampleBuild = null;
	
	JTextField countryTF = null;
	JTextField buisnessAreaTF = null;
	JTextField buisnessDomainTF = null;
	JTextField serviceTF = null;
	JTextField sampleBuildTF = null;
	JButton generateB1 = null;
	JButton openB2 = null;
	JFileChooser jf = null;
	File TemplateBuild = null;
	File serviceBuildXML = null;
	String Tcountry = null;
	String Tba = null;
	String Tbd = null;
	String Tservice = null;
    public BIAN bianbuild = null;
	
	
public BuildXMLApp(BIAN bianStr) {
	 bianbuild =  bianStr;
	}
	
	

	public BuildXMLApp() {
	
}
	public static void main(String[] args) {
		
		BuildXMLApp app = new BuildXMLApp();
		app.createBuildLayout();
	
	}

	public boolean createBuildLayout() {
		frame = new JFrame("BUild XML Creator");
		createPanel();
		createLabels();
		createTexts();
		createButtons();
		AddComponents();
		return true;
		
	}

	private void createLabels() {
		country = new JLabel(Country, JLabel.CENTER);
		buisnessArea = new JLabel(BuisnessArea, JLabel.CENTER);
		buisnessDomain = new JLabel(BuisnessDomain, JLabel.CENTER);
		service = new JLabel(Service, JLabel.CENTER);
		sampleBuild = new JLabel(SampleBuildXML, JLabel.CENTER);
		Dimension d1 = buisnessArea.getPreferredSize();
		country.setPreferredSize(new Dimension(d1.width + 30, d1.height));
		buisnessArea.setPreferredSize(new Dimension(d1.width + 30, d1.height));
		buisnessDomain
				.setPreferredSize(new Dimension(d1.width + 30, d1.height));
		service.setPreferredSize(new Dimension(d1.width + 30, d1.height));
		sampleBuild.setPreferredSize(new Dimension(d1.width + 30, d1.height));

	}

	private void AddComponents() {
		countryP1.add(country,BorderLayout.WEST);
		countryP1.add(countryTF,BorderLayout.CENTER);
		buisnessAreaP2.add(buisnessArea,BorderLayout.WEST);
		buisnessAreaP2.add(buisnessAreaTF,BorderLayout.CENTER);
		buisnessDomainP3.add(buisnessDomain,BorderLayout.WEST);
		buisnessDomainP3.add(buisnessDomainTF,BorderLayout.CENTER);
		serviceP4.add(service,BorderLayout.WEST);
		serviceP4.add(serviceTF,BorderLayout.CENTER);
		sampleBuildP5.add(sampleBuild,BorderLayout.WEST);
		sampleBuildP5.add(sampleBuildTF,BorderLayout.CENTER);
		sampleBuildP5.add(openB2,BorderLayout.EAST);
		generateP6.add(generateB1);
		
		mainPanel.add(countryP1);
		mainPanel.add(buisnessAreaP2);
		mainPanel.add(buisnessDomainP3);
		mainPanel.add(serviceP4);
		mainPanel.add(sampleBuildP5);
		mainPanel.add(generateP6);
		mainPanel.add(emptyP7);
		frame.add(mainPanel);
		Dimension d2 = frame.getPreferredSize();
		//frame.pack();
		frame.setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(new Dimension(d2.width + 70, d2.height + 50));
		setLocationRelativeTo(null);
		
		
	}

	private void createButtons() {
		
		generateB1 = new JButton("Generate");
		openB2 = new JButton("open");
		openB2.setPreferredSize(new Dimension(30, 23));
		generateB1.addActionListener(this);
		openB2.addActionListener(this);
	}

	private void createTexts() {
		if (bianbuild !=null) {
			countryTF = new JTextField(bianbuild.getCountry(), 10);
			countryTF.setEditable(false);
			buisnessAreaTF = new JTextField(bianbuild.getBA(), 10);
			buisnessAreaTF.setEditable(false);
			buisnessDomainTF = new JTextField(bianbuild.getBD(), 10);
			buisnessDomainTF.setEditable(false);
			serviceTF = new JTextField(bianbuild.getService(), 10);
			serviceTF.setEditable(false);
			sampleBuildTF = new JTextField(30);
		} else {
			countryTF = new JTextField(10);
			buisnessAreaTF = new JTextField(10);
			buisnessDomainTF = new JTextField(10);
			serviceTF = new JTextField(10);
			sampleBuildTF = new JTextField(30);
		}

	}

	private void createPanel() {
		mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		countryP1 = new JPanel(new BorderLayout());
		buisnessAreaP2 = new JPanel(new BorderLayout());
		buisnessDomainP3 = new JPanel(new BorderLayout());
		serviceP4 = new JPanel(new BorderLayout());
		sampleBuildP5 = new JPanel(new BorderLayout());
		generateP6 = new JPanel();
		emptyP7 = new JPanel();
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == openB2) {
			jf = new JFileChooser();
			
			int returnVal = jf.showOpenDialog(this);
			 
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				TemplateBuild = jf.getSelectedFile();
				sampleBuildTF.setText(TemplateBuild.getAbsolutePath());
				System.out.println("Opening: " + TemplateBuild.getName() + "." + newline);
				generateB1.setEnabled(true);
			} else {
				
				System.out.println("Open command cancelled by user." + newline);
			}
			

			// Handle save button action.
		}
		if(e.getSource()==generateB1)
 {
			String country = countryTF.getText().trim();
			String BA = buisnessAreaTF.getText().trim();
			String BD = buisnessDomainTF.getText().trim();
			String service = serviceTF.getText().trim();
			String sampleBuild = sampleBuildTF.getText().trim();

			if (country.isEmpty() == false && BD.isEmpty() == false
					&& service.isEmpty() == false && BA.isEmpty() == false
					&& sampleBuild.isEmpty()==false) {
				if (bianbuild == null) {

					try {
						bianbuild = new BIAN(country, BA, BD, service);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}

				String reqBuildFile = bianbuild.getServiceCodePath() + "\\"
						+ "build.xml";
				serviceBuildXML = new File(reqBuildFile);
				BuildXmlCreator BX = new BuildXmlCreator(bianbuild,
						TemplateBuild, serviceBuildXML);
				try {
					boolean isSuccess;
					isSuccess = BX.startOps();
					if (isSuccess) {
						String message = "Build.xml generated in " + serviceBuildXML.getAbsolutePath();
						 JOptionPane.showMessageDialog(new JFrame(), message, "Dialog",
							        JOptionPane.INFORMATION_MESSAGE);
						System.out.println("File generated"
								+ serviceBuildXML.getAbsolutePath());
						

					} else {
						
						String message = "deleteing build.xnl under" + serviceBuildXML.getAbsolutePath();
						JOptionPane.showMessageDialog(new JFrame(), message, "Dialog",
						        JOptionPane.INFORMATION_MESSAGE);
						System.out.println("File not generated");
						serviceBuildXML.delete();
						frame.dispose();
						
					}
				} catch (SAXException e1) {

					e1.printStackTrace();
				} catch (IOException e1) {

					e1.printStackTrace();
				} catch (ParserConfigurationException e1) {

					e1.printStackTrace();
				} catch (TransformerException e1) {

					e1.printStackTrace();
				}
			} else {
				System.out
						.println("please check the enteries somthing is missing");
				generateB1.setEnabled(false);
			}

		}
		
	}
	protected static ImageIcon createImageIcon(String path) {
		java.net.URL imgURL = FileChooserDemo.class.getResource(path);
		if (imgURL != null) {
			return new ImageIcon(imgURL);
		} else {
			System.err.println("Couldn't find file: " + path);
			return null;
		}
	}

}
