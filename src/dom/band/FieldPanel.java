package dom.band;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComponent;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class FieldPanel extends JPanel {
	private static final BufferedImage FIELD_IMG = createFieldImage(900, 480, Color.WHITE, Color.GREEN);
	
	private List<Performer> performerList;
	private double time = 0;
	
	private double viewX, viewY, viewScale;
	
	public FieldPanel() {
		performerList = new ArrayList<Performer>();
		
		viewX = 0;
		viewY = 0;
		viewScale = 1;
		
		addMouseMotionListener(new MouseMotionListener() {
			private Point lastPos;
			
			@Override
			public void mouseMoved(MouseEvent e) {
				lastPos = e.getPoint();
			}
			
			@Override
			public void mouseDragged(MouseEvent e) {
				viewX -= (e.getX() - lastPos.x) / viewScale;
				viewY -= (e.getY() - lastPos.y) / viewScale;
				lastPos = e.getPoint();
				repaint();
			}
		});
		
		addMouseWheelListener(new MouseWheelListener() {
			@Override
			public void mouseWheelMoved(MouseWheelEvent e) {
				double delta = Math.pow(0.95, e.getPreciseWheelRotation());
				viewScale *= delta;
				if(viewScale < 0.01) {viewScale = 0.01;}
				if(viewScale > 100) {viewScale = 100;}
				viewX += e.getX() * (delta - 1) / viewScale;
				viewY += e.getY() * (delta - 1) / viewScale;
				repaint();
			}
		});
	}
	
	public void setPerformerList(List<Performer> list) {
		performerList = list;
	}
	
	public void setTime(double time) {
		this.time = time;
	}
	
	public void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		
		g2d.setColor(new Color(0, 240, 0));
		g2d.fillRect(0, 0, getWidth(), getHeight());
		
		g2d.scale(viewScale, viewScale);
		g2d.translate(-viewX, -viewY); 
		
		g2d.drawImage(FIELD_IMG, (getWidth() - FIELD_IMG.getWidth())/2,
				(getHeight() - FIELD_IMG.getHeight())/2, null);
		
		g2d.setFont(g2d.getFont().deriveFont(18f));
		for(Performer performer : performerList) {
			g2d.setColor(performer.getColor());
			g2d.drawString("" + performer.getSymbol(),
					getWidth()/2 + (int) ((FIELD_IMG.getWidth()/100.0)*performer.getPos((int) time).x/1000.0) - g2d.getFontMetrics().stringWidth("" + performer.getSymbol())/2,
					getHeight()/2 + (int) ((FIELD_IMG.getWidth()/100.0)*performer.getPos((int) time).y/1000.0) - g2d.getFontMetrics().getAscent()/2);
		}
	}
	
	private static BufferedImage createFieldImage(int width, int height, Color fg, Color bg) {
		BufferedImage fieldImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics2D g = fieldImg.createGraphics();
		
		int lineWidth = width / 150;
		
		g.setColor(fg);
		g.fillRect(0, 0, width, height);
		g.setColor(bg);
		g.fillRect(lineWidth, lineWidth, width - 2*lineWidth, height - 2*lineWidth);
		
		for(int i = 1; i < 10; i ++) {
			g.setColor(fg);
			g.fillRect((int) ((i/10.0)*width) - (lineWidth/2), 0, lineWidth, height);
		}
		
		return fieldImg;
	}
}
