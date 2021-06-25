package particles;

import lombok.Setter;
import vector.Vector;
import lombok.Getter;

@Getter
@Setter
public class Particle {

    private Vector position;
    private float radius;
    private int[] color;

    public Particle(Vector position, float radius, int[] color) {
        this.position = position;
        this.radius = radius;
        this.color = color;
    }

}
