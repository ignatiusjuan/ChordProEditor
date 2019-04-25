package de.chordsystem.Prototype;

public class RegEx {
	
	String input;
	
	public RegEx() {
		
	}
	
	public RegEx(String input) {
		this.input = input;
	}
	
	public void start() {
		System.out.println("yes");
	}

	public static void main(String[] args) {
		RegEx regex = new RegEx();
		regex.start();
	}
}