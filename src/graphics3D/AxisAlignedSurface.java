package graphics3D;

import java.awt.Color;
import java.awt.image.BufferedImage;

import dom.vector.Vector;

public class AxisAlignedSurface implements Surface{
	public static final int XY_POSITIVE = 0, YZ_POSITIVE = 1, ZX_POSITIVE = 2,
			XY_NEGATIVE = 3, YZ_NEGATIVE = 4, ZX_NEGATIVE = 5;
	
	private int plane;
	private Vector normal;
	private double extent, min1, min2, max1, max2;
	
	private BufferedImage image;
	private int[] rgb;
	
	public AxisAlignedSurface(int plane, double extent, double min1, double min2, double max1, double max2, Color color) {
		this.plane = plane;
		switch(plane) {
			case YZ_POSITIVE: normal = Vector.UNIT_X; break;
			case ZX_POSITIVE: normal = Vector.UNIT_Y; break;
			case XY_NEGATIVE: normal = Vector.UNIT_Z.mul(-1); break;
			case YZ_NEGATIVE: normal = Vector.UNIT_X.mul(-1); break;
			case ZX_NEGATIVE: normal = Vector.UNIT_Y.mul(-1); break;
			case XY_POSITIVE:
			default: plane = XY_POSITIVE; normal = Vector.UNIT_Z; break;
		}
		
		this.extent = extent;
		this.min1 = min1;
		this.min2 = min2;
		this.max1 = max1;
		this.max2 = max2;
		
		this.rgb = new int[] {color.getRed(), color.getGreen(), color.getBlue()};
	}
	
	public AxisAlignedSurface(int plane, double extent, double min1, double min2, double max1, double max2, BufferedImage image) {
		this.plane = plane;
		switch(plane) {
			case YZ_POSITIVE: normal = Vector.UNIT_X; break;
			case ZX_POSITIVE: normal = Vector.UNIT_Y; break;
			case XY_NEGATIVE: normal = Vector.UNIT_Z.mul(-1); break;
			case YZ_NEGATIVE: normal = Vector.UNIT_X.mul(-1); break;
			case ZX_NEGATIVE: normal = Vector.UNIT_Y.mul(-1); break;
			case XY_POSITIVE:
			default: plane = XY_POSITIVE; normal = Vector.UNIT_Z; break;
		}
		
		this.extent = extent;
		this.min1 = min1;
		this.min2 = min2;
		this.max1 = max1;
		this.max2 = max2;
		
		this.image = image;
	}
	
	@Override
	public Vector getCollision(Vector pos, Vector dir) {
		if(normal.dot(dir) >= 0) {
			return null;
		} else {
			switch(plane) {
				case XY_POSITIVE:
				case XY_NEGATIVE:
					if(Math.signum(pos.z - extent) != normal.z) {
						return null;
					} else {
						Vector temp = pos.add(dir.mul((extent - pos.z) / dir.z));
						if(min1 < temp.x && temp.x < max1 && min2 < temp.y && temp.y < max2) {
							return temp;
						} else {
							return null;
						}
					}
				case YZ_POSITIVE:
				case YZ_NEGATIVE:
					if(Math.signum(pos.x - extent) != normal.x) {
						return null;
					} else {
						Vector temp = pos.add(dir.mul((extent - pos.x) / dir.x));
						if(min1 < temp.y && temp.y < max1 && min2 < temp.z && temp.z < max2) {
							return temp;
						} else {
							return null;
						}
					}
				case ZX_POSITIVE:
				case ZX_NEGATIVE:
					if(Math.signum(pos.y - extent) != normal.y) {
						return null;
					} else {
						Vector temp = pos.add(dir.mul((extent - pos.y) / dir.y));
						if(min1 < temp.z && temp.z < max1 && min2 < temp.x && temp.x < max2) {
							return temp;
						} else {
							return null;
						}
					}
				default:
					return null;
			}
		}
	}
	
	@Override
	public int[] getRGB(Vector pos) {
		if(image == null) {
			return rgb;
		} else {
			Color color;
			switch(plane) {
				case XY_POSITIVE:
				case XY_NEGATIVE:
					color = new Color(image.getRGB((int) Math.floor(((pos.x % image.getWidth()) + image.getWidth()) % image.getWidth()),
							(int) Math.floor(((pos.y % image.getHeight()) + image.getHeight()) % image.getHeight())));
					break;
				case YZ_POSITIVE:
				case YZ_NEGATIVE:
					color = new Color(image.getRGB((int) Math.floor(((pos.y % image.getWidth()) + image.getWidth()) % image.getWidth()),
							(int) Math.floor(((pos.z % image.getHeight()) + image.getHeight()) % image.getHeight())));
					break;
				case ZX_POSITIVE:
				case ZX_NEGATIVE:
					color = new Color(image.getRGB((int) Math.floor(((pos.z % image.getWidth()) + image.getWidth()) % image.getWidth()),
							(int) Math.floor(((pos.x % image.getHeight()) + image.getHeight()) % image.getHeight())));
					break;
				default:
					return null;
			}
			return new int[] {color.getRed(), color.getGreen(), color.getBlue()};
		}
	}
}
