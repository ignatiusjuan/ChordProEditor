package de.chordsystem.chordproeditor.syntaxchecker;
/***
 * @author shineglurak
 * Class to mark found errors in the text.
 */

public class FoundFault {
	
	private int zeile;
	private int startBuchstabe;
	private int endBuchstabe;
	private String verbesserungsVorschlag;
	
	/***
	 * Construktor for found faults
	 * @param zeile
	 * @param start
	 * @param ende
	 * @param vorschlag
	 */
	FoundFault(){
		this.zeile = 0;
		startBuchstabe = 0;
		endBuchstabe = 0;
		verbesserungsVorschlag = null;
	}
	
	FoundFault(int zeile, int start, int ende, String vorschlag){
		setZeile(zeile);
		setStart(start);
		setEnde(ende);
		setVorschlag(vorschlag);
	}
	
	/***
	 * Sets the line which contains the fault.
	 * @param linenumber
	 */
	public void setZeile(int zeile) {
		this.zeile = zeile;
	}
	
	/***
	 * Sets the position of the first letter of the misspelled word.
	 * @param position beginn word
	 */
	public void setStart(int start) {
		this.startBuchstabe = start;
	}
	
	/***
	 * Sets the position of the last letter of the misspelled word.
	 * @param position end word
	 */
	public void setEnde(int ende) {
		this.endBuchstabe = ende;
	}
	
	/***
	 * Sets the proposal for the misspelled word
	 */
	public void setVorschlag(String vorschlag){
		this.verbesserungsVorschlag = vorschlag;
	}
	
	/***
	 * Gives the line in which the fault is
	 * @return line
	 */
	public int getZeile(){
		return this.zeile;
	}
	
	/***
	 * Gives the position of the first letter, of the wrong word
	 * @return position(like array)
	 */
	public int getStart() {
		return startBuchstabe;
	}
	
	/***
	 * Gives the position of the last letter, of the wrong word
	 * @return position(like array)
	 */
	public int getEnde() {
		return endBuchstabe;
	}

	/***
	 * Gives an correction proposal 
	 * @return proposal
	 */
	public String getVorschlag() {
		return verbesserungsVorschlag;
	}
	
	public String toString() {
		String text = "Line: "+this.zeile+"\n";
		text += "Beginn: "+this.startBuchstabe+"\n";
		text += "End: "+this.endBuchstabe+"\n";
		text += "Proposal: "+this.verbesserungsVorschlag+"\n";
		return text;
	}

}
