package digitalDotBook;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import digitalDotBook.Measure;

public class MyHandler extends DefaultHandler {
	private List<Measure>  msrList = null;
	private Measure msr = null;
	private StringBuilder data = null;
	
	public List<Measure> getMsrList() {
		return msrList;
	}
	
	boolean bNote = false;
	boolean bAttributes = false;
	boolean bFifths = false;
	boolean bMode = false;
	boolean bBeats = false;
	boolean bBeatType = false;
	boolean bSign = false;
	boolean bLine = false;
	
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		if (qName.equalsIgnoreCase("measure")) {
			String number = attributes.getValue("number");
			msr = new Measure();
			msr.setNumber(Integer.parseInt(number));
			if(msrList == null) {
				msrList = new ArrayList<>();
			}
		}else if (qName.equalsIgnoreCase("attributes")) {
			bAttributes = true;
		}else if (qName.equalsIgnoreCase("note")) {
			bNote = true;
		}else if (qName.equalsIgnoreCase("fifths")) {
			bFifths = true;
		}else if (qName.equalsIgnoreCase("mode")) {
			bMode = true;
		}else if (qName.equalsIgnoreCase("beats")) {
			bBeats = true;
		}else if (qName.equalsIgnoreCase("beat-type")) {
			bBeatType = true;
		}else if (qName.equalsIgnoreCase("sign")) {
			bSign = true;
		}else if (qName.equalsIgnoreCase("line")) {
			bLine = true;
		}
		data = new StringBuilder();
	}
	
	public void endElement(String uri, String localName, String qName) throws SAXException {
		if (bNote) {
			msr.addNote(data.toString());
			bNote = false;
		}else if (bAttributes) {
			msr.setAttributes(true);
			bAttributes = false;
		}else if (bFifths) {
			msr.setFifth(Integer.parseInt(data.toString()));
			bFifths= false;
		}else if (bMode) {
			msr.setMode(data.toString());
			bMode = false;
		}else if (bBeats) {
			msr.setBeats(Integer.parseInt(data.toString()));
			bBeats = false;
		}else if (bBeatType) {
			msr.setBeatType(Integer.parseInt(data.toString()));
			bBeatType = false;
		}else if (bSign) {
			bSign = false;
			msr.setSign(data.toString());
		}else if (bLine) {
			msr.setLine(Integer.parseInt(data.toString()));
			bLine = false;
		}
		if (qName.equalsIgnoreCase("measure")) {
			msrList.add(msr);
		}
	}
	
	public void characters(char ch[], int start, int length) throws SAXException {
		data.append(new String(ch, start, length));
	}
}
