package ProjetJeu;

import com.badlogic.gdx.math.Vector2;

import hevs.gdx2d.components.bitmaps.Spritesheet;
import hevs.gdx2d.components.physics.PhysicsBox;
import hevs.gdx2d.lib.GdxGraphics;
import hevs.gdx2d.lib.physics.AbstractPhysicsObject;
import hevs.gdx2d.lib.utils.Logger;

public class Hero extends Mobile {
	protected double fSpeed = 1;
	protected double fProjectil = 1;

	final int SPRITE_WIDTH = 115;
	final int SPRITE_HEIGHT = 115;
	Spritesheet ss;

	int textureY = 0;

	public int currentFrame = 0;
	final int nFrames = 6;

	// Constructor
	public Hero(int x, int y, int width, int height, int vx, int vy,
			int health, double fSpeed, double fProjectil) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.vx = vx;
		this.vy = vy;
		this.health = health;
		this.fSpeed = fSpeed;
		this.fProjectil = fProjectil;
		Vector2 position = new Vector2(x, y);

		box = new PhysicsBox("Hero", position, width, height);
		box.getBody().setFixedRotation(true);
		box.getBody().setLinearDamping(10f);

		
		ss = new Spritesheet("data/images/SpriteSheetHero115x115.png",
				SPRITE_WIDTH, SPRITE_HEIGHT);
		

	}
	
	public void recieveDamage(int nDamages) {
		this.health -= nDamages;
	}

	public void recieveHealth(int nHealth) {
		this.health += nHealth;
	}

	public void move() {
		// TODO Auto-generated method stub

	}

	public void draw(GdxGraphics g) {
		// TODO Auto-generated method stub
		Vector2 position;
		Vector2 Velocity;
		Vector2 VectorNull = new Vector2(0, 0);

		position = box.getBodyPosition();
		Velocity = box.getBodyLinearVelocity();
		

		if (Velocity.x <1 && Velocity.x >-1 &&Velocity.y <1 && Velocity.y >-1) {
			currentFrame = 6;
		}
		
		g.spriteBatch.draw(ss.sprites[textureY][currentFrame], position.x
				- width / 2-20, position.y - height / 2-4);

	}

}
