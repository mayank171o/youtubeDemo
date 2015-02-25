package DB;

import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class BuildXmlCreator {
	private static final String xmlFilePath = "build_original.xml";
	
	BIAN biancre = null;
	File templateBuildFile;
	File serviceBuildXML;
	Logger logger = null;

	

	public Logger getLogger() {
		return logger;
	}

	public void setLogger(Logger logger) {
		this.logger = logger;
	}

	public BuildXmlCreator(BIAN bianbuild, File templateBuild,
			File serviceBuildXML) {
		this.templateBuildFile = templateBuild;
		this.serviceBuildXML = serviceBuildXML;
		biancre = bianbuild;
		
	}

	public void main(String[] args) throws SAXException, IOException,
			ParserConfigurationException, DOMException, TransformerException {

		delfile();
		DocumentBuilderFactory docFactory = DocumentBuilderFactory
				.newInstance();
		DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
		Document doc = docBuilder.parse(xmlFilePath);
		if (args.length == 1) {
			Boolean error = UpdateProperty(doc, args[0]);
			if (error == true) {
				updateFile(doc);
				System.out.println("file updated");
				System.out.println("File created in " + serviceBuildXML.getAbsolutePath() );
				
			} else {
				System.out
						.println("please check the build.xml if contains error");
			}
		} else {
			System.out.println("please provide proper argument service name");
		}

	}

	private static void delfile() {
		File existBuild = new File("build.xml");
		if (existBuild.exists()) {
			System.out.println("baba" + existBuild.delete());
		}

	}

	public boolean updateFile(Document doc)  {
		 try{
		if (serviceBuildXML.exists()) {
			serviceBuildXML.delete();
			serviceBuildXML.getParentFile().mkdirs();
			boolean flagCreated = serviceBuildXML.createNewFile();
			System.out.println("build.xml will be created in " + serviceBuildXML.getAbsolutePath() +"      " + flagCreated );
			logger.info("build.xml will be created in " + serviceBuildXML.getAbsolutePath() +"      " + flagCreated );
		} else {
			serviceBuildXML.getParentFile().mkdirs();
			boolean flagCreated = serviceBuildXML.createNewFile();
			System.out.println("File created in 2" + serviceBuildXML.getAbsolutePath() +"      " + flagCreated );
			logger.info("build.xml will be created in " + serviceBuildXML.getAbsolutePath() +"      " + flagCreated );
		}
		TransformerFactory transformerFactory = TransformerFactory
				.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource source = new DOMSource(doc);
		StreamResult result = new StreamResult(serviceBuildXML);
		transformer.transform(source, result);

		System.out.println("File saved!");
		return true;
		}catch(IOException IOe)
		 {
			IOe.printStackTrace();
			return false;
		 } catch (TransformerException e) {
			
			e.printStackTrace();
			return false;
		}

	}

	private static Boolean UpdateProperty(Document doc, String servicename) {
		NodeList property = doc.getElementsByTagName("property");
		for (int i = 0; i < property.getLength(); i++) {
			Node element = property.item(i);
			Node attrName = element.getAttributes().getNamedItem("name");
			System.out.println(attrName);
			if (attrName != null
					&& attrName.getNodeValue().equalsIgnoreCase("serviceName"))

			{
				element.getAttributes().getNamedItem("value")
						.setNodeValue(servicename);
				System.out.println(element.getAttributes()
						.getNamedItem("value").getNodeValue());
				return true;
			}

		}
		return false;
	}

	// **********************max************/
	public boolean startOps() throws SAXException, IOException,
			ParserConfigurationException, TransformerException {

		DocumentBuilderFactory docFactory = DocumentBuilderFactory
				.newInstance();
		DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
		Document doc = docBuilder.parse(this.templateBuildFile);

		Boolean isUpdated = UpdateProperty(doc);
		if (isUpdated == true) {
			if(updateFile(doc) == true)
			{
				biancre.setIs_buildXMLgenerated(true);
				System.out.println("file updated");
				/*if (biancre != null && biancre.is_oneservice == true) {
					CFGApp cfgApp = new CFGApp(biancre);
					cfgApp.createlayout();
				}*/
				return true;
			} else {
				return false;
			}
		} else {
			System.out
					.println("please check the build.xml if it contains the property with name as servicename");
			System.out.println("");
			return false;
		}

	}

	private Boolean UpdateProperty(Document doc) {

		NodeList property = doc.getElementsByTagName("property");
		boolean isServiceNameUpdated = false;
		boolean isCountryUpdate = false;
		boolean isBAUpdated = false;
		boolean isBDUpdated = false;

		for (int i = 0; i < property.getLength(); i++) {
			Node element = property.item(i);
			Node attrName = element.getAttributes().getNamedItem("name");
			System.out.println(attrName);
			if (attrName != null) {
				if (isServiceNameUpdated == false || isCountryUpdate == false
						|| isBDUpdated == false || isBAUpdated == false) {
					if (attrName.getNodeValue().equalsIgnoreCase("serviceName"))

					{
						element.getAttributes().getNamedItem("value")
								.setNodeValue(biancre.getService());
						System.out.println(element.getAttributes()
								.getNamedItem("value").getNodeValue());
						isServiceNameUpdated = true;

					}

					if (attrName.getNodeValue().equalsIgnoreCase("Country"))

					{
						element.getAttributes().getNamedItem("value")
								.setNodeValue(biancre.getCountry());
						System.out.println(element.getAttributes()
								.getNamedItem("value").getNodeValue());
						isCountryUpdate = true;

					}

					if (attrName.getNodeValue().equalsIgnoreCase("BA"))

					{
						element.getAttributes().getNamedItem("value")
								.setNodeValue(biancre.getBA());
						System.out.println(element.getAttributes()
								.getNamedItem("value").getNodeValue());
						isBAUpdated = true;

					}

					if (attrName.getNodeValue().equalsIgnoreCase("BD"))

					{
						element.getAttributes().getNamedItem("value")
								.setNodeValue(biancre.getBD());
						System.out.println(element.getAttributes()
								.getNamedItem("value").getNodeValue());
						isBDUpdated = true;

					}

					continue;
				} else {
					return true;

				}
			}

		}
		if (isBAUpdated == false) {
			System.out
					.println("template build.xml is corrupt BA attribute does not exist");
			logger.info("template build.xml is corrupt BA attribute does not exist");

		}
		if (isBDUpdated == false) {
			System.out
					.println("template build.xml is corrupt BD attribute does not exist");
			logger.info("template build.xml is corrupt BD attribute does not exist");

		}
		if (isCountryUpdate == false) {
			System.out
					.println("template build.xml is corrupt country attribute does not exist");
			logger.info("template build.xml is corrupt country attribute does not exist");

		}
		if (isServiceNameUpdated == false) {
			System.out
					.println("template build.xml is corrupt service attribute does not exist");
			logger.info("template build.xml is corrupt service attribute does not exist");

		}
		return false;

	}

}
