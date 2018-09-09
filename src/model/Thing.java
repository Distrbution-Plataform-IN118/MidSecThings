package model;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.List;

public class Thing implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String UID;
	private LinkedHashMap statusInfo;
	private String editable;
	private String label;
	private LinkedHashMap configuration;
	private LinkedHashMap properties;
	private String thingTypeUID;
	private List channels;
	public String getUID() {
		return UID;
	}
	public void setUID(String uID) {
		UID = uID;
	}
	public LinkedHashMap getStatusInfo() {
		return statusInfo;
	}
	public void setStatusInfo(LinkedHashMap statusInfo) {
		this.statusInfo = statusInfo;
	}
	public String getEditable() {
		return editable;
	}
	public void setEditable(String editable) {
		this.editable = editable;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public LinkedHashMap getConfiguration() {
		return configuration;
	}
	public void setConfiguration(LinkedHashMap configuration) {
		this.configuration = configuration;
	}
	public LinkedHashMap getProperties() {
		return properties;
	}
	public void setProperties(LinkedHashMap properties) {
		this.properties = properties;
	}
	public String getThingTypeUID() {
		return thingTypeUID;
	}
	public void setThingTypeUID(String thingTypeUID) {
		this.thingTypeUID = thingTypeUID;
	}
	public List getChannels() {
		return channels;
	}
	public void setChannels(List channels) {
		this.channels = channels;
	}
	
	
}
