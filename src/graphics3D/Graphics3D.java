package graphics3D;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.util.ArrayList;
import java.util.List;

import dom.vector.Vector;

public class Graphics3D {
	private Graphics g;
	private Camera cam;
	private List<Surface> surfaceList;
	
	public Graphics3D(Graphics g) {
		this.g = g;
		
		cam = new Camera();
		surfaceList = new ArrayList<Surface>();
	}
	
	public void render(int width, int height, double scale) {
		BufferedImage img = new BufferedImage((int) Math.ceil(width*scale), (int) Math.ceil(height*scale), BufferedImage.TYPE_INT_RGB);
		WritableRaster raster = img.getRaster();
		
		Vector camY = cam.getY().scale((2*Math.tan(cam.fov/2) * cam.fl) / img.getWidth());
		Vector camZ = cam.getZ().scale(camY.mag());
		Vector baseDir = cam.getX().scale(cam.fl).add(camY.mul(-img.getWidth()/2)).add(camZ.mul(img.getHeight()/2));
		
		for(int y = 0; y < img.getHeight(); y ++) {
			for(int x = 0; x < img.getWidth(); x ++) {
				Vector dir = baseDir.add(camY.mul(x).add(camZ.mul(-y)));
				
				Vector minCollision = null;
				double minDis = Double.MAX_VALUE;
				Surface minSurface = null;
				for(Surface surface : surfaceList) {
					Vector collision = surface.getCollision(cam.pos, dir);
					if(collision == null) {continue;}
					double dis = collision.sub(cam.pos).sqrmag();
					if(minCollision == null || dis < minDis) {
						minCollision = collision;
						minDis = dis;
						minSurface = surface;
					}
				}
				
				int[] rgb;
				if(minSurface == null) {
					int gray = 128 + (int) (128*dir.unit().z);
					rgb = new int[] {gray, gray, gray};
				} else {
					rgb = minSurface.getRGB(minCollision);
				}
				raster.setPixel(x, y, rgb);
			}
		}
		
		g.drawImage(img, 0, 0, img.getWidth(), img.getHeight(), 0, 0, width, height, null);
	}
}
