package particles;

import vector.Vector;
import lombok.Getter;
import lombok.Setter;
import vector.VectorUtil;

@Getter
@Setter
public class MoverParticle extends Particle {

    private Vector velocity;

    public MoverParticle(Vector position, float radius, int[] color) {
        super(position, radius, color);
        velocity = new Vector(0, 0);
    }

    public MoverParticle(Vector position, float radius, int[] color, Vector velocity) {
        super(position, radius, color);
        this.velocity = velocity;
    }

    public void update () {
        setPosition(VectorUtil.add(
                getPosition(),
                getVelocity()
        ));
    }
}
