package DB;

import java.io.*;
import java.util.Scanner;
import java.util.logging.Logger;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class CFGCreator {

	//file added to git
	public File serviceCFG = null;
	public File propertyFile = null;
	public File desinerFile = null;
	public PrintWriter pw = null;
	public BufferedReader designer = null;
	Logger logger = null;

	public Logger getLogger() {
		return logger;
	}
	public void setLogger(Logger logger) {
		this.logger = logger;
	}
	public static void main(String[] args) throws IOException {
		CFGCreator cfg = new CFGCreator();
		//cfg.fileInitializer();
		cfg.startOps();

	}
	public CFGCreator(File designerFile ,File propertyFile,File serviceCFG)
	{
		this.serviceCFG = serviceCFG;
		this.propertyFile = propertyFile;
		this.desinerFile = designerFile;
	}
	public CFGCreator() {
		
	}
	
	public boolean startOps() throws IOException {
		System.out.println("haan bhai");
		fileInitializer();
		designer = new BufferedReader(new FileReader(desinerFile));
		pw = new PrintWriter(serviceCFG);
		String read = null;
		while ((read = designer.readLine()) != null) {

			String line = read;
			if (line.contains("#")) {
				continue;
			} else {
				String[] s1 = line.split("=");
				String projlibName = s1[1].substring(0, s1[1].length() - 1);				
				if(!searchProjLibsPaths(projlibName, serviceCFG, propertyFile))
				{
					closeConnection();
					return false;
				}

			}
		}
		closeConnection();
         return true;
		
		

	}

	private void closeConnection() throws IOException {

		pw.close();
		designer.close();
	}

	private boolean searchProjLibsPaths(String projlib, File serviceCFG,
			File ProjLibsPaths) throws IOException {
		Scanner scan = new Scanner(ProjLibsPaths);
		boolean hasNext;
		while ((hasNext = scan.hasNextLine()) == true) {
			String line = scan.nextLine();
			if (line.toLowerCase().indexOf(projlib.toLowerCase()) >= 0) {
				writetoCFG(line, serviceCFG);
				break;
			}
			  
			
		}
		if(hasNext == false)
		  {
			  System.out.println("This Projlib " + projlib + " does not exist");
			  String message = "This Projlib " + projlib + " does not exist";
			  logger.info(message);
			  logger.info("Please update  the property file through script");
				JOptionPane.showMessageDialog(new JFrame(), message, "Dialog",
				        JOptionPane.INFORMATION_MESSAGE);
				return false;
		  }
		return true;

	}

	private void writetoCFG(String line, File serviceCFG) throws IOException {

		
		pw.println(line);
		pw.flush();

	}

	public void fileInitializer() throws IOException {
		 if(serviceCFG == null && propertyFile == null && desinerFile == null)
		 {
			serviceCFG = new File("DepositAccount_IN_v1");
			propertyFile = new File("CFGPath.properties");
			desinerFile = new File(".designtimelibs");
		}
		// reqprojLibs.createNewFile();
		if (serviceCFG!=null && serviceCFG.exists()) {
			serviceCFG.delete();
			serviceCFG.createNewFile();
			System.out.println("Dummy CFG file created");
		} else {
			serviceCFG.createNewFile();
			System.out.println("Dummy CFG file created");
		}

	}

}
