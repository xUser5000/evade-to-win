public final class MathUtil {
    private MathUtil () {}

    public static float random (float l, float r) {
        return l + (float) Math.random() * (r-l);
    }

}
