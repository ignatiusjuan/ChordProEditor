package de.chordsystem.chordproeditor.model.classes;

import de.chordsystem.chordproeditor.model.abstracts.EnvironmentAbstract;
import de.chordsystem.chordproeditor.model.interfaces.Tab;

import java.util.List;
import java.util.ArrayList;

public class TabImpl extends EnvironmentAbstract implements Tab{
	
	private List<String> tabList;
	
	public TabImpl(String name) {
		this(name,"#000000");
	}
	
	public TabImpl(String name, String colour) {
		super();
		this.type = TAB;
		this.name = name;
		this.colour = colour;
		this.tabList = new ArrayList<String>();
	}
	
	public void addTab(String tab) {
		tabList.add(tab);
	}
	
	public String getTab(int line) {
		return tabList.get(line);
	}
	
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		for (String st : tabList) {
			sb.append(st + "\n");
		}
		return sb.toString();
	}
}
