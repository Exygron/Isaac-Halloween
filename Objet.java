package ProjetJeu;

import hevs.gdx2d.lib.interfaces.DrawableObject;
import hevs.gdx2d.lib.physics.AbstractPhysicsObject;

public abstract class Objet implements DrawableObject {

	protected int x;
	protected int y;
	protected int width;
	protected int height;
	public AbstractPhysicsObject box;
	
	public abstract void move();

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

}
