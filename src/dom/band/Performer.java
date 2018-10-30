package dom.band;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class Performer {
	private static final char DEFAULT_SYMBOL = 'X';
	private static final Color DEFAULT_COLOR = new Color(0, 0, 255);
	
	private List<Point> posList;
	private char symbol;
	private Color color;
	
	public Performer() {
		posList = new ArrayList<Point>();
		for(int i = 0; i < 80; i ++) {
			posList.add(new Point(0, 0));
		}
		symbol = DEFAULT_SYMBOL;
		color = DEFAULT_COLOR;
	}
	
	public Point getPos(int time) {
		return posList.get(time);
	}
	
	public void setPos(int time, Point pos) {
		Point oPos = posList.set(time, pos);
		while(++time < posList.size() && posList.get(time).equals(oPos)) {
			posList.set(time, pos);
		}
	}
	
	public char getSymbol() {
		return symbol;
	}
	
	public Color getColor() {
		return color;
	}
}
