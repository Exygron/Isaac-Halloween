package ProjetJeu;

import hevs.gdx2d.components.bitmaps.BitmapImage;
import hevs.gdx2d.lib.GdxGraphics;

public class Projectile extends Objet
{
	protected int force;
	protected int vx;
	protected int vy;
	protected int distanceMax;
	
	
	
	//Constructor
	Projectile(int x, int y, int heigth, int width, int vx, int vy, int force, int dMax)
	{
		this.x = x;
		this.y = y;
		this.height = heigth;
		this.width = width;
		this.vx = vx;
		this.vy = vy;
		this.force = force;
		this.distanceMax = dMax;
	}
	public void move() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void draw(GdxGraphics g) {
		// TODO Auto-generated method stub
		
	}
}
