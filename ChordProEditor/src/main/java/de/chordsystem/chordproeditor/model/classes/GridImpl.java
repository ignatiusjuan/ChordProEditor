package de.chordsystem.chordproeditor.model.classes;

import de.chordsystem.chordproeditor.model.abstracts.EnvironmentAbstract;
import de.chordsystem.chordproeditor.model.interfaces.Grid;

import java.util.ArrayList;
import java.util.List;

public class GridImpl extends EnvironmentAbstract implements Grid{

	private List<String> gridList;
	
	public GridImpl(String name) {
		this(name,"#000000");
	}
	
	private GridImpl(String name, String colour) {
		super();
		this.type = GRID;
		this.name = name;
		this.colour = colour;
		this.gridList = new ArrayList<String>();
	}
	
	public void addGrid(String grid) {
		gridList.add(grid);
	}
	
	public String getGrid(int line) {
		return gridList.get(line);
	}
	
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		for (String st : gridList) {
			sb.append(st + "\n");
		}
		return sb.toString();
	}
	
}
