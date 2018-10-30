package graphics3D;

import dom.vector.Vector;

public class Camera {
	private static final Vector DEFAULT_POS = Vector.ZERO, DEFAULT_DIR = Vector.UNIT_Z;
	private static final double DEFAULT_FOV = Math.PI/2, DEFAULT_FL = 1;
	
	protected Vector pos, dir;
	protected double fov, fl;
	
	public Camera() {
		pos = DEFAULT_POS;
		dir = DEFAULT_DIR;
		fov = DEFAULT_FOV;
		fl = DEFAULT_FL;
	}
	
	public void move(Vector delta) {
		this.pos = this.pos.add(delta);
	}
	
	public void moveTo(Vector pos) {
		this.pos = pos;
	}
	
	public void yaw(double a) {
		double sin = Math.sin(a), cos = Math.cos(a);
		dir = new Vector(cos*dir.x + sin*dir.y, -sin*dir.x + cos*dir.y, dir.z);
	}
	
	public void pitch(double a) {
		double sin = Math.sin(a), cos = Math.cos(a);
		dir = dir.scale(cos).add(getZ().scale(sin));
	}
	
	public Vector getX() {
		return dir;
	}
	
	public Vector getY() {
		return Vector.UNIT_Z.cross(dir);
	}
	
	public Vector getZ() {
		return dir.cross(getY());
	}
}
