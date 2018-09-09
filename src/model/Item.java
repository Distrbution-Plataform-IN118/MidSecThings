package model;
import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Item implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String link;
	private String state;
	
	private String label;
	private String name;
	private String type;
	
	
	
	
	private LinkedHashMap stateDescription;
	private String category;
	private List tags;
	private List groupNames;
	
	private List members;
	private String groupType;
	private LinkedHashMap function;
	private String transformedState;
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

	public LinkedHashMap getStateDescription() {
		return stateDescription;
	}
	public void setStateDescription(LinkedHashMap stateDescription) {
		this.stateDescription = stateDescription;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List getTags() {
		return tags;
	}
	public void setTags(List tags) {
		this.tags = tags;
	}
	public List getGroupNames() {
		return groupNames;
	}
	public void setGroupNames(List groupNames) {
		this.groupNames = groupNames;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public List getMembers() {
		return members;
	}
	public void setMembers(List members) {
		this.members = members;
	}
	public String getGroupType() {
		return groupType;
	}
	public void setGroupType(String groupType) {
		this.groupType = groupType;
	}
	public LinkedHashMap getFunction() {
		return function;
	}
	public void setFunction(LinkedHashMap function) {
		this.function = function;
	}
	public String getTransformedState() {
		return transformedState;
	}
	public void setTransformedState(String transformedState) {
		this.transformedState = transformedState;
	}
	
	
}
