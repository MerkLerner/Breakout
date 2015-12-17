package ballmania;

import java.util.ArrayList;
import processing.core.PApplet;

public class BallMania extends PApplet {

	Ball b;

	Paddle p;

	ArrayList<Brick> bricks = new ArrayList<Brick>();

	public void setup() {

		size(400, 600);

		b = new Ball(200, 300, 15, random(-3, 3), 2, this);
		p = new Paddle();

		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				bricks.add(new Brick(40 * i, 100 + 15 * j, 40, 15, random(0,255), random(0,255),  random(0,255)));
			}
		}

	}

	public void draw() {
		background(0);
		fill(0, 0, 0, 15);
		noStroke();
		rect(0, 0, width, height);

		// update ball
		b.update();
		b.paint();

		p.update();
		p.paint();

		for (int i = 0; i < bricks.size(); i++) {
			bricks.get(i).paint();
		}

		for (int i = 0; i < bricks.size(); i++) {
			Brick tempBrick = bricks.get(i);
			if (b.x > tempBrick.x && b.x < (tempBrick.x + tempBrick.bWidth) && b.y + b.size / 2 < tempBrick.y) {
				b.vy = -b.vy;
				bricks.remove(tempBrick);
			}

		}
		
		
	}

	class Paddle {
		float x, y;

		public Paddle() {
			x = width / 2;
			y = 550;
		}

		public void update() {
			x = mouseX - 30;
		}

		public void paint() {
			fill(160);
			rect(x, y, 60, 10);
		}

	}

	public class Ball {

		private float x;
		private float y;
		private float vx;
		private float vy;
		private float size;
		PApplet q;

		public Ball(float nx, float ny, float nsize, float spdX, float spdY, PApplet p) {
			x = nx;
			y = ny;
			size = nsize;
			q = p;

			vx = spdX;
			vy = spdY;
		}

		public void update() {
			x = x + vx;
			y = y + vy;

			if (x > q.width || x < 0) {
				vx = -vx;
			}

			if (y > q.height || y < 0) {
				vy = -vy;
			}

			if (p.y < y + size / 2 && p.x + 60 > x && p.x < x) {
				vy = -vy;
			}

		}

		public void paint() {
			q.noStroke();
			q.fill(0, 250, 0);
			q.ellipse(x, y, size, size);
		}

	}

	class Brick {
		float x, y, bWidth, bHeight, r, g, b;

		public Brick(float nx, float ny, float nWidth, float nHeight, float nr, float ng, float nb) {
			x = nx;
			y = ny;
			bWidth = nWidth;
			bHeight = nHeight;
			r = nr;
			g = ng;
			b = nb;
		}

		public void paint() {
			fill(r, g, b);
			rect(x, y, bWidth, bHeight);
		}
	}
	
	public void loseGame () {
		if (b.y > height) {
			background(0);
			fill(0,255,0);
			text("Game over.",200,200);
			b.vy = 0;
			b.vx = 0;
		}
	}
}
