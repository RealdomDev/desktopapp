package dom.band;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import graphics3D.Graphics3D;

public class Band {
	private static List<Performer> performerList = new ArrayList<Performer>();

	@SuppressWarnings("serial")
	public static void main(String[] args) {
		JFrame frame = new JFrame("marting ban");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		FieldPanel field = new FieldPanel();
		field.setPreferredSize(new Dimension(1000, 600));
		frame.add(field, BorderLayout.CENTER);
		
		JSlider slider = new JSlider(JSlider.HORIZONTAL, 0, 80, 0);
		slider.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				field.setTime(slider.getValue());
				frame.repaint();
			}
		});
		slider.setMajorTickSpacing(8);
		slider.setMinorTickSpacing(1);
		slider.setPaintTicks(true);
		slider.setPaintLabels(true);
		frame.add(slider, BorderLayout.PAGE_END);
		
		JMenu fileMenu = new JMenu("File");
		fileMenu.setMnemonic('f');
		
		JMenuItem openMenuItem = new JMenuItem("Open");
		openMenuItem.setToolTipText("Open a .3da file");
		openMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("lol");
			}
		});
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.add(fileMenu);
		frame.setJMenuBar(menuBar);
		
		frame.pack();
		frame.setVisible(true);
		
		try {
			performerList = loadPerformerData("c:/users/dom/desktop/band/test.3da");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static List<Performer> loadPerformerData(String filePath) throws IOException {
		List<Performer> performerList = new ArrayList<Performer>();
		
		ByteBuffer bb = ByteBuffer.wrap(Files.readAllBytes(Paths.get(filePath)));
		while(!(bb.get() == 'C' && bb.get() == 'A' && bb.get() == 'S' && bb.get() == 'T')) {}
		byte[] ba;

		System.out.printf("??? = %d\n", bb.getInt());
		
		short performerCount = bb.getShort();
		System.out.printf("Number of Performers = %d\n", performerCount);
		for(; performerCount > 0; performerCount --) {
			System.out.printf("Performer #%d:\n", bb.getShort());
			ba = new byte[bb.getShort()];
			bb.get(ba);
			System.out.printf("\tlabel = %s\n", new String(ba));
		}
		
		bb.getInt();	//Skip 4 bytes (PTAB)
		bb.getInt(); bb.getShort();	//Skip 6 bytes (???)
		bb.getInt();	//Skip 4 bytes (PAGE)
		System.out.printf("??? = %d\n", bb.getInt());
		
		short frameCount = bb.getShort();
		System.out.printf("Number of Frames = %d\n", frameCount);
		performerCount = bb.getShort();
		System.out.printf("Number of Performers = %d\n", performerCount);
		for(int i = 0; i < performerCount; i ++) {
			performerList.add(new Performer());
		}
		
		for(int i = 0; i < frameCount; i ++) {
			System.out.printf("Frame #%d:\n", i);
			for(int j = 0; j < performerCount; j ++) {
				System.out.printf("\tPerformer #%d:\n", bb.getShort());
				Point pos = new Point(bb.getInt(), bb.getInt());
				System.out.printf("\t\t x = %d y = %d\n", pos.x, pos.y);
				System.out.printf("\t\t rgb = %d %d %d symbol = %c\n", (bb.get()+256) % 256, (bb.get()+256) % 256, (bb.get()+256) % 256, bb.get());
				performerList.get(j).setPos(i, pos);
			}
			bb.getInt();	//Redundant performer count
		}
		
		return performerList;
	}
	
	private static BufferedImage createFieldImage(int width, int height) {
		BufferedImage fieldImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics2D g = fieldImg.createGraphics();
		
		Color bg = new Color(0, 240, 0);
		Color fg = new Color(240, 240, 240);
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
