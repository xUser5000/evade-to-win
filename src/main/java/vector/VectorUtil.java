package vector;

public final class VectorUtil {

    private VectorUtil () {}

    public static float magnitude (Vector v) {
        return (int) Math.sqrt(v.getX()*v.getX() + v.getY()*v.getY());
    }

    public static Vector scale (Vector v, float scalar) {
        return new Vector(v.getX() * scalar, v.getY() * scalar);
    }

    public static Vector add (Vector a, Vector b) {
        return new Vector(a.getX() + b.getX(), a.getY() + b.getY());
    }

    public static Vector negative (Vector v) {
        return new Vector(-v.getX(), -v.getY());
    }

    /**
     * Given vectors a and b, returns a - b
     */
    public static Vector subtract (Vector a, Vector b) {
        return add(a, negative(b));
    }

    /**
     * Given a vector, turn it into a unit vector
     * The unit vector of V can be calculated using the following formula:
     * V = V / Magnitude(V)
     */
    public static Vector normalize (Vector v) {
        return scale(v, 1F / magnitude(v));
    }

    public static Vector setMagnitude (Vector v, float m) {
        return scale( normalize(v), m );
    }

    public static float distance (Vector a, Vector b) {
        return (float) Math.sqrt( Math.pow(a.getX()-b.getX(), 2) + Math.pow(a.getY()-b.getY(), 2) );
    }
}
