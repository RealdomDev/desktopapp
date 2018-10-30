package graphics3D;

import dom.vector.Vector;

public class OrthoSet {
	private Vector x, y, z;
	
	public OrthoSet() {
		x = Vector.UNIT_X;
		y = Vector.UNIT_Y;
		z = Vector.UNIT_Z;
	}
	
	public Vector getX() {return x;}
	public Vector getY() {return y;}
	public Vector getZ() {return z;}
	
	public void rotXY(double s) {
		x = new Vector(Math.cos(s)*x.x + Math.sin(s)*x.y, -Math.sin(s)*x.x + Math.cos(s)*x.y, x.z);
		y = new Vector(Math.cos(s)*y.x + Math.sin(s)*y.y, -Math.sin(s)*y.x + Math.cos(s)*y.y, y.z);
		z = new Vector(Math.cos(s)*z.x + Math.sin(s)*z.y, -Math.sin(s)*z.x + Math.cos(s)*z.y, z.z);
	}
	
	public void rotLocalXY(double s) {
		x = x.scale(Math.cos(s)).add(y.scale(Math.sin(s)));
		y = x.scale(-Math.sin(s)).add(y.scale(Math.cos(s)));
		//z = z;
	}
	
	public void rotYZ(double s) {
		x = new Vector(x.x, Math.cos(s)*x.y, Math.sin(s)*x.z);
		y = new Vector(y.x, Math.cos(s)*y.y, Math.sin(s)*y.z);
		z = new Vector(z.x, Math.cos(s)*z.y, Math.sin(s)*z.z);
	}
	
	public void rotXZ(double s) {
		x = new Vector(Math.cos(s)*x.x, x.y, Math.sin(s)*x.z);
		y = new Vector(Math.cos(s)*y.x, y.y, Math.sin(s)*y.z);
		z = new Vector(Math.cos(s)*z.x, z.y, Math.sin(s)*z.z);
	}
	
	public void rotLocalXZ(double s) {
		x = x.scale(Math.cos(s)).add(z.scale(Math.sin(s)));
		//y = y;
		z = x.scale(-Math.sin(s)).add(z.scale(Math.cos(s)));
	}
}
