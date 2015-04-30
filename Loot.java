package ProjetJeu;

import hevs.gdx2d.lib.GdxGraphics;

public class Loot extends Fixe
{
	protected LootType type;
	protected double factor;
	
	// Constructor
	public Loot(LootType type, double factor, int x, int y, int width, int height)
	{
		this.type = type;
		this.factor = factor;
		this.height = height;
		this.width = width;
		this.x = x;
		this.y = y;
	}
	
	
	public void move() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void draw(GdxGraphics g) {
		// TODO Auto-generated method stub
		
	}
}
