package dom.vector;

public class Vector {
	public static final Vector
			ZERO = new Vector(0, 0, 0),
			UNIT_X = new Vector(1, 0, 0),
			UNIT_Y = new Vector(0, 1, 0),
			UNIT_Z = new Vector(0, 0, 1);
	
	public final double x, y, z;
	
	public Vector(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public double sqrmag() {
		return x*x + y*y + z*z;
	}
	
	public double mag() {
		return Math.sqrt(sqrmag());
	}
	
	public Vector add(Vector v) {
		return new Vector(x + v.x, y + v.y, z + v.z);
	}
	
	public Vector sub(Vector v) {
		return new Vector(x - v.x, y - v.y, z - v.z);
	}
	
	public double dis(Vector v) {
		return Math.sqrt(Math.pow(x - v.x, 2) + Math.pow(y - v.y, 2) + Math.pow(z - v.z, 2));
	}
	
	public Vector mul(double s) {
		return new Vector(x * s, y * s, z * s);
	}
	
	public Vector mul(Vector v) {
		return new Vector(x * v.x, y * v.y, z * v.z);
	}
	
	public Vector div(double s) {
		return new Vector(x / s, y / s, z / s);
	}
	
	public Vector scale(double s) {
		if(equals(ZERO)) {
			return ZERO;
		}
		return mul(s / mag());
	}
	
	public Vector unit() {
		return div(mag());
	}
	
	public double dot(Vector v) {
		return x*v.x + y*v.y + z*v.z;
	}
	
	public Vector cross(Vector v) {
		return new Vector(y*v.z - z*v.y, z*v.x - x*v.z, x*v.y - y*v.x);
	}
	
	public double ang(Vector v) {
		return Math.acos(dot(v) / (mag() * v.mag()));
	}
	
	public double proj(Vector v) {
		return dot(v.unit());
	}
	
	public Vector onto(Vector v) {
		return v.scale(proj(v));
	}
	
	public boolean equals(Vector v) {
		return x == v.x && y == v.y && z == v.z;
	}
	
	public String toString() {
		return x + " " + y + " " + z;
	}
}
