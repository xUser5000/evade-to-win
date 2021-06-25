import lombok.Setter;
import vector.Vector;
import lombok.Getter;
import particles.MoverParticle;
import particles.Particle;
import vector.VectorUtil;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Game {

    private int width, height;
    private Particle coin;
    private MoverParticle player;
    private List<MoverParticle> enemies;
    @Setter private Vector mouse;
    private int pointsCount;

    private static final float DEFAULT_RADIUS = 25;
    private static final float DEFAULT_SPEED = 5;
    private static final int[] DEFAULT_COLOR = {52, 152, 219};

    public Game (int width, int height) {
        this.width = width;
        this.height = height;
        init();
    }

    public void init () {
        coin = new Particle(
                new Vector(MathUtil.random(0, width), MathUtil.random(0, height)),
                DEFAULT_RADIUS,
                DEFAULT_COLOR
        );
        player = new MoverParticle(
                new Vector(width/2, height/2),
                DEFAULT_RADIUS,
                DEFAULT_COLOR,
                new Vector(0, 0)
        );
        enemies = new ArrayList<>();
        mouse = new Vector(width/2, height/2);
        pointsCount = 0;
    }

    public void update () {
        Vector diff = VectorUtil.subtract(mouse, player.getPosition());
        if (VectorUtil.magnitude(diff) < 10) player.setVelocity(new Vector(0, 0));
        else {
            player.setVelocity(VectorUtil.scale(
                    VectorUtil.normalize(diff),
                    DEFAULT_SPEED
            ));
        }
        player.update();
    }

}
