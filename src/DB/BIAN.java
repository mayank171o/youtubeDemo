package DB;

import java.io.IOException;

public class BIAN {

	private String country = null;
	private String buisnessArea = null;
	private String buisnessDomain = null;
	private String service = null;
	private  String ENV_PATH = null;
	private String  PROJECT_PATH = null;
	private String codePath= null;
	private String BIAN = null;
	private String ABS_XML_PATH = null;
	private String ABS_CFG_PATH = null;
	private String serviceCodePath = null;
	public  boolean  is_oneservice = false;
	public boolean is_buildXMLgenerated = false;
	
	public boolean isIs_buildXMLgenerated() {
		return is_buildXMLgenerated;
	}

	public boolean isIs_CFGgenerated() {
		return is_CFGgenerated;
	}

	public void setIs_CFGgenerated(boolean is_CFGgenerated) {
		this.is_CFGgenerated = is_CFGgenerated;
	}

	public void setIs_buildXMLgenerated(boolean is_buildXMLgenerated) {
		this.is_buildXMLgenerated = is_buildXMLgenerated;
	}

	public boolean is_CFGgenerated = false;

	public boolean isIs_oneservice() {
		return is_oneservice;
	}

	public void setIs_oneservice(boolean is_oneservice) {
		this.is_oneservice = is_oneservice;
	}

	public BIAN() {
		setABS_XML_PATH(ENV_PATH + "\\" + "XMLfiles");
		setABS_CFG_PATH(ENV_PATH + "\\" + "CFGfiles");
	}

	public BIAN(String country, String bA, String bD, String service) throws IOException {
		super();
		this.country = country;
		buisnessArea = bA;
		buisnessDomain = bD;
		this.service = service;
		is_oneservice = true;
	}

	public String getBIAN() {
		return BIAN;
	}

	public void setBIAN(String bIAN) {
		BIAN = bIAN;
	}
	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getBA() {
		return buisnessArea;
	}

	public void setBA(String bA) {
		buisnessArea = bA;
	}

	public String getBD() {
		return buisnessDomain;
	}

	public void setBD(String bD) {
		buisnessDomain = bD;
	}

	public String getService() {
		return service;
	}

	public void setService(String service) {
		this.service = service;
	}

	public String getABS_XML_PATH() {
		return ABS_XML_PATH;
	}

	public String getCodePath() {
		return codePath;
	}

	public void setCodePath(String codePath) {
		this.codePath = codePath;
	}

	public void setABS_XML_PATH(String aBS_XML_PATH) {
		ABS_XML_PATH = aBS_XML_PATH;
	}

	public String getENV_PATH() {
		return ENV_PATH;
	}

	public String getPROJECT_PATH() {
		return PROJECT_PATH;
	}

	public void setPROJECT_PATH(String pROJECT_PATH) {
		PROJECT_PATH = pROJECT_PATH;
	}

	public void setENV_PATH(String eNV_PATH) {
		ENV_PATH = eNV_PATH;
	}

	public String getABS_CFG_PATH() {
		return ABS_CFG_PATH;
	}

	
	public void setABS_CFG_PATH(String aBS_CFG_PATH) {
		ABS_CFG_PATH = aBS_CFG_PATH;
	}

	public String getServiceCodePath() {
		return serviceCodePath;
	}

	public void setServiceCodePath(String serviceCodePath) {
		this.serviceCodePath = serviceCodePath;
	}
	
	public void createPaths()
	{
		this.codePath = getCodePath();
		setENV_PATH(codePath +"\\"+"EnvSettings");
		setPROJECT_PATH(codePath +"\\"+"Code");
		setABS_XML_PATH(getENV_PATH() + "\\" + "XMLfiles");
		setABS_CFG_PATH(getENV_PATH() + "\\" + "CFGfiles");
		setBIAN(country.concat("\\").concat(buisnessArea).concat("\\")
				.concat(buisnessDomain).concat("\\").concat(service));
		setServiceCodePath(getPROJECT_PATH() + "\\" + BIAN);
	}

	
}
