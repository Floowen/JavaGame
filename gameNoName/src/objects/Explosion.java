package objects;

import static utils.Constants.ObjectConstants.*;

public class Explosion extends GameObject {
	public Explosion(int x, int y) {
		super(x, y, EXPLOSION);
		setAnimation(true);
	}

	public void update() {
		updateAnimationTick();
	}
}
