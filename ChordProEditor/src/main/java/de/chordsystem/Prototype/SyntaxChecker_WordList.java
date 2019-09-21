package de.chordsystem.Prototype;

import java.util.ArrayList;

public class SyntaxChecker_WordList {
	
	public static ArrayList<String> forChorus(){
		ArrayList<String> liste = new ArrayList<String>();
		liste.add("chorus");
		liste.add("Xhorus");
		liste.add("Dhorus");
		liste.add("Fhorus");
		liste.add("Vhorus");
		liste.add("xhorus");
		liste.add("dhorus");
		liste.add("fhorus");
		liste.add("vhorus");
		liste.add("Cborus");//Zweiter Buchstabe
		liste.add("Cgorus");
		liste.add("Czorus");
		liste.add("Cuorus");
		liste.add("Cjorus");
		liste.add("Cnorus");
		liste.add("Chirus");//Dritter Buchstabe
		liste.add("Chkrus");
		liste.add("Chlrus");
		liste.add("Chprus");
		liste.add("Choeus");//Vierter Buchstabe
		liste.add("Chodus");
		liste.add("Chofus");
		liste.add("Chotus");
		liste.add("Chorzs");//Fünfter Buchstabe
		liste.add("Chorhs");
		liste.add("Chorjs");
		liste.add("Choris");
		liste.add("Chorua");//Sechster Buchstabe
		liste.add("Choruw");
		liste.add("Chorue");
		liste.add("Chorud");
		liste.add("Chorux");
		liste.add("Choruy");
		return liste;
	}
	
	public static ArrayList<String>forTab(){
		ArrayList<String> liste = new ArrayList<String>();
		liste.add("tab");//Erster Buchstabe
		liste.add("Rab");
		liste.add("rab");
		liste.add("Fab");
		liste.add("fab");
		liste.add("Gab");
		liste.add("gab");
		liste.add("Zab");
		liste.add("zab");
		liste.add("Tqb");//Zweiter Buchstabe
		liste.add("Tsb");
		liste.add("Tyb");
		liste.add("Tav");//Dritter Buchstabe
		liste.add("Tag");
		liste.add("Tah");
		liste.add("Tan");
		return liste;
	}
	
	public static ArrayList<String> forGrid(){
		ArrayList<String> liste = new ArrayList<String>();
		liste.add("grid");//Erster Buchstabe
		liste.add("Frid");
		liste.add("frid");
		liste.add("Trid");
		liste.add("trid");
		liste.add("Zrid");
		liste.add("zrid");
		liste.add("Hrid");
		liste.add("hrid");
		liste.add("Brid");
		liste.add("brid");
		liste.add("Vrid");
		liste.add("vrid");
		liste.add("Geid");//Zweiter Buchstabe
		liste.add("Gdid");
		liste.add("Gfid");
		liste.add("Gtid");
		liste.add("Grud");//Dritter Buchstabe
		liste.add("Grkd");
		liste.add("Grod");
		liste.add("Gris");//Vierter Buchstabe
		liste.add("Grie");
		liste.add("Grir");
		liste.add("Grif");
		liste.add("Gric");
		liste.add("Grix");
		return liste;
	}
	
	public static ArrayList<String> forComment(){
		ArrayList<String> liste = new ArrayList<String>();
		liste.add("/");
		liste.add("\\\\");
		liste.add("\\");
		return liste;
	}

}
