package graphics3D;

import dom.vector.Vector;

public interface Surface {
	public Vector getCollision(Vector pos, Vector dir);
	public int[] getRGB(Vector pos);
}
