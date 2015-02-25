package DB;

import java.io.File;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

/**
 * 
 */

/**
 * @author Mayank Agarwal
 *
 */
public class NewServiceGenerator {

	BIAN bianStr = null;
	String BIAN = null;
	JTextArea log;
	Logger logger = null;
	public Logger getLogger() {
		return logger;
	}

	public void setLogger(Logger logger) {
		this.logger = logger;
	}

	

	public NewServiceGenerator() {

	}

	public NewServiceGenerator(BIAN bian) {
		bianStr = bian;
	}

	public boolean createNewService() {
		
		if(!createCFGPath() || !createXMLPath())
		{
			return false;
		}
		/*BuildXMLApp buildApp = new BuildXMLApp(bianStr);
		System.out.println("Creating Build.xml");
		
		 
		if(buildApp.createBuildLayout())
		{
			System.out.println("Please provide inputs to create build.xml");
			log.append("Please provide inputs to create build.xml" + newline);
			return true;
			
		}else
		{
			System.out.println("Some issue happens in creating build layout");
			log.append("Some issue happens in creating build layout");
			return false;
		}*/
		return true;
		
		
	}

	private Boolean createXMLPath() {
		System.out.println("creating XML Path");
		logger.info("creating XML Path");
		String xmlPath = bianStr.getABS_XML_PATH() + "\\" + bianStr.getBIAN();
		System.out.println("path is" + xmlPath);
		logger.info("Deployment CML File will be generated at this path:  " + xmlPath);
		File xmlF = new File(xmlPath);
		if (!xmlF.isDirectory()) {
			boolean success = xmlF.mkdirs();
			if (success) {
				System.out.println(" 1 Created path: " + xmlF.getPath());
				logger.info("XML Path created successfully at " + xmlF.getPath() );
				return true;
			} else {
				
				String message = "XML Path Cannot be created , Please Check Logs" ;
				JOptionPane.showMessageDialog(new JFrame(), message, "Dialog",
				        JOptionPane.INFORMATION_MESSAGE);
				return false;
			}
		} 
		return true;

	}

	private Boolean createCFGPath() {
		System.out.println("creating CFG Path");
		logger.info("creating CFG Path");
		String cfgPath = bianStr.getABS_CFG_PATH() + "\\" + bianStr.getBIAN();
		System.out.println("path is" + cfgPath);
		logger.info("CFG File will be generated at this path:  " + cfgPath);
		File cfgF = new File(cfgPath);
		if (!cfgF.isDirectory()) {
			boolean success = cfgF.mkdirs();
			if (success) {
				System.out.println(" 1 Created path: " + cfgF.getPath());
				logger.info("CFG Path created successfully at " + cfgF.getPath() );
				return true;
			} else {
				
				String message = "CFG Path Cannot be created , Please Check Logs" ;
				JOptionPane.showMessageDialog(new JFrame(), message, "Dialog",
				        JOptionPane.INFORMATION_MESSAGE);
				return false;
			}
		} 
		
		return true;

	}

}
