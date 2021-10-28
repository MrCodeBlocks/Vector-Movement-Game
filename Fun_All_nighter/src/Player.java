import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

public class Player extends GameObject{

	// width and height of character.
	private int width, height;

	// Color of the character.
	private Color color;

	private Vec2 PlayerDirection;

	private float speed, rawAngle, angle, sign, yawSpeed, RawMovementInput, MovementInput;

	Texture tex = Game.getInstance();

	public Player(ID id, Vec2 pos, int w, int h, Color col) {
		super(id, pos);
		width = w;
		height = h;
		color = col;
		PlayerDirection = new Vec2(0, 0);
		speed = 8.f;
		angle = 0.f;
		rawAngle = 0.f;
		sign = 0.f;
		yawSpeed = 8.f;
		RawMovementInput = 0.f;
		MovementInput = 0.f;
	}

	@Override
	public void tick() {
		if(angle >360) {
			angle = 0.f;
		}
		if(angle < -360) {
			angle = 0.f;
		}
		if (rr) {
			Rotate(1);
			angle += sign * yawSpeed * Game.DeltaTime;
		}

		if(rl) {
			Rotate(-1);
			angle += sign * yawSpeed * Game.DeltaTime;
		}
		if(up) {
			Move(1);
		}
		if(down) {
			Move(-1);
		}

		PlayerDirection.x = (float)Math.cos(angle * (Math.PI/180));
		PlayerDirection.y = (float)Math.sin(angle * (Math.PI/180));

		// Move the Character!!
		{
			position.x += speed * PlayerDirection.x * Game.DeltaTime * MovementInput;
			position.y += speed * PlayerDirection.y * Game.DeltaTime * MovementInput;
		}

		if(position.x > Game.WIDTH*2) {
			position.x = 0;
		} else if(position.x < 0) {
			position.x = Game.WIDTH * 2;
		}

		if(position.y > Game.HEIGHT*2) {
			position.y = 0;
		} else if(position.y < 0) {
			position.y = Game.HEIGHT*2;
		}

		sanitize();
		System.out.println(PlayerDirection.x + ", " + PlayerDirection.y);
	}

	@Override
	public void render(Graphics2D g) {

		BufferedImage image = tex.player[0];
		g.setColor(color);
		g.rotate(angle * (Math.PI/180), (int)position.x, (int)position.y);
		g.drawImage(image, (int)position.x-(image.getWidth()/2), (int)position.y - (image.getHeight()/2), null);
	}

	@Override
	public Rectangle GetBounds() {
		return new Rectangle((int)position.x, (int)position.y, width, height);
	}

	public void Rotate(float axisValue) {
		rawAngle += axisValue;
	}

	public void Move(float axisValue) {
		RawMovementInput += axisValue;
	}

	public void sanitize() {
		rawAngle = Game.clamp(rawAngle, -1, 1);
		sign = rawAngle;
		rawAngle = 0.f;

		RawMovementInput = Game.clamp(RawMovementInput, -1, 1);
		MovementInput = RawMovementInput;
		RawMovementInput = 0.f;
	}
}
