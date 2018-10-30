import java.lang.Math;
import java.lang.StringBuilder;

class Dot {
    private static float DEFAULTCTS = 8;
    private float x;
    private float y;
    private float cts;

    public Dot() {
        x = 0;
        y = 0;
        cts = DEFAULTCTS;
    }
    public Dot(float x, float y, float cts) {
        this.x = x;
        this.y = y;
        this.cts = cts;
    }

    public float getX() { return x; }
    public float getY() { return y; }
    public float getCounts() { return cts; }
    public void setX(float x) { this.x = x; }
    public void setY(float y) { this.y = y; }
    public void setCounts(float cts) { this.cts = cts; }

    // non default methods

    public String toStringLtR(float[] yardls, String[] yardnames) {
        int nearidx = nearestYard(yardls);
        float dis = (x - yardls[nearidx]);
        StringBuilder builder = new StringBuilder();

        if (dis < 0) {
            if (x < yardls[yardls.length - 1] / 2) {
                builder.append(Float.toString(Math.abs(dis)));
                builder.append(" steps outside of the ");
            }
            else {
                builder.append(Float.toString(Math.abs(dis)));
                builder.append(" steps inside of the ");
            }
        }
        else if (dis == 0) {
            builder.append("On the ");
        }
        else {
            if (x > yardls[yardls.length - 1] / 2) {
                builder.append(Float.toString(dis));
                builder.append(" steps outside of the ");
            }
            else {
                builder.append(Float.toString(dis));
                builder.append(" steps inside of the ");
            }
        }
        builder.append(yardnames[nearestYard(yardls)]);

        return builder.toString();
    }

    public String toStringFtB(float[] hashes, String[] hashnames) {
        int nearidx = nearestHash(hashes);
        float dis = (y - hashes[nearidx]);
        StringBuilder builder = new StringBuilder();

        if (dis > 0) {
            builder.append(Float.toString(dis));
            builder.append(" steps behind the ");
        }
        else if (dis == 0) {
            builder.append("On the ");
        }
        else {
            builder.append(Float.toString(Math.abs(dis)));
            builder.append(" steps in front of the ");
        }
        builder.append(hashnames[nearestHash(hashes)]);

        return builder.toString();
    }

    public String toString() {
        return null;
    }

    private int nearestYard (float[] yardls) {
        // function takes array of yardline x locations, compares against
        // Dot class x variable, and returns index of closest yardline
        if (x < (yardls[0] + yardls[1]) / 2) {
            // check goalline/0 yardline case
            return 0;
        }
        else {
            int idx = 1;
            while (idx < yardls.length - 1) {
                // checks in between cases
                if ((x < (yardls[idx] + yardls[idx + 1]) / 2) && (x >= (yardls[idx - 1] + yardls[idx]) / 2)) {
                    // this might need to be rewritten depending on whether
                    // where a dot splitting yardlines should default to
                    return idx;
                }
                idx += 1;
            }
            if (x > (yardls[idx] + yardls[idx + 1]) / 2) {
                return idx;
            }
            else {
                return -1;
            }
        }
    }

    private int nearestHash (float[] hashes) {
        // function takes array of hash y location, compares it against
        // Dot class y variable, and returns index of closest hash
        if (y < (hashes[0] + hashes[1]) / 2) {
            // only applies to front sideline cases
            return 0;
        }
        else {
            int idx = 1;
            while (idx < hashes.length - 1) {
                // checks in between cases
                // currently defaults to
                if ((y < (hashes[idx] + hashes [idx + 1]) / 2) && (y >= (hashes[idx - 1] + hashes[idx]) / 2)) {
                    return idx;
                }
                idx += 1;
            }
            if (y > (hashes[idx] + hashes[idx - 1]) / 2) {
                return idx;
            }
            else {
                // this shouldn't happen
                return -1;
            }
        }
    }
}
