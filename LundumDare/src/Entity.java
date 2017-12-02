
public class Entity {

	double x;
	double y;

	double velx;
	double vely;

	public Entity() {

	}

	public void update() {
		this.x += this.velx;
		this.y += this.vely;

		this.velx *= 0.8;
		this.vely *= 0.8;
	}

}
