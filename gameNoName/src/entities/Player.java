package entities;

import static utils.Constants.Directions.*;
import static utils.Constants.PlayerConstants.*;
import static utils.HelpMethods.*;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import main.Game;
import utils.LoadSave;

public class Player extends Entity{

	private BufferedImage[][] animations; //stores animation as arrays
	private int animTick, animIndex, animSpeed = 15;
	private int playerAction = IDLE;
	private boolean moving = false, attacking = false;
	private boolean left, up, right, down, jump;
	private float playerSpeed= 1.75f;
	private int[][] lvlData;
	private float xDrawOffset = 21 * Game.SCALE;
	private float yDrawOffset = 4 * Game.SCALE;
	
	//Jumping / Gravity
	private float airSpeed = 0f;
	private float gravity = 0.1f * Game.SCALE;
	private float jumpSpeed = -4.0f * Game.SCALE;
	private float fallSpeedAfterCollision = 0.5f * Game.SCALE;
	private boolean inAir = false;
	
	public Player(float x, float y, int width, int height) {
		super(x, y, width, height);
		loadAnimations();
		initHitbox(x, y, (int)(20 * Game.SCALE), (int)(27 * Game.SCALE));
	}

	public void update() {
		updatePos();
		updateAnimationTick();
		setAnimation();
		
	}
	
	public void render(Graphics g) {
		g.drawImage(animations[playerAction][animIndex], (int)(hitbox.x - xDrawOffset), (int)(hitbox.y - yDrawOffset), width, height, null); //draws image|sprite
//		drawHitbox(g);	
	}
	
	private void updateAnimationTick() { //plays the animation
		animTick++;
		if (animTick >= animSpeed) {
			animTick = 0;
			animIndex++;
			if (animIndex >= getSpriteAmount(playerAction)) {
				animIndex = 0;
				attacking = false;
			}
		}
		
	}
	
	private void setAnimation() {
		int startAnim = playerAction;
		
		if (moving) 
			playerAction = RUNNING;
		else
			playerAction = IDLE;
		
		if (inAir) {
			if (airSpeed < 0)
				playerAction = JUMP;
			else
				playerAction = FALLING;
		}
		
		if (attacking) 
			playerAction = ATTACK_1;
		
		if (startAnim != playerAction) {
			resetAnimTick();
		}
	}
	
	private void resetAnimTick() {
		animTick = 0;
		animIndex = 0;
		
	}

	private void updatePos() {
		moving = false;
		
		if (jump)
			jump();
		if(!left && !right && !inAir)
			return;

		float xSpeed = 0;
		
		if (left) 
			xSpeed -= playerSpeed;
		
		if (right)
			xSpeed += playerSpeed;
		
		if (!inAir) {
			if (!IsEntityOnFloor(hitbox, lvlData)) 
				inAir = true;
		}
		
		if (inAir) {
			if (CanMoveHere(hitbox.x, hitbox.y + airSpeed, hitbox.width, hitbox.height, lvlData)) {
				hitbox.y += airSpeed;
				airSpeed += gravity;
				updateXPos(xSpeed);
			}
			else {
				hitbox.y = GetEntityYPosUnderRoofOrAboveFloor(hitbox, airSpeed);
				if (airSpeed > 0)
					resetInAir();
				else
					airSpeed = fallSpeedAfterCollision;
				updateXPos(xSpeed);
			}
			
		}
		else 
			updateXPos(xSpeed);
		
		moving = true;
	}
	
	private void jump() {
		if (inAir)
			return;
		inAir = true;
		airSpeed = jumpSpeed;
		
	}

	private void resetInAir() {
		inAir = false;
		airSpeed = 0;
	}

	private void updateXPos(float xSpeed) {
		if (CanMoveHere(hitbox.x + xSpeed, hitbox.y, hitbox.width, hitbox.height, lvlData)) {
			hitbox.x += xSpeed;
		}
		else {
			hitbox.x = GetEntityXPosNextToWall(hitbox, xSpeed);
		}
	}

	private void loadAnimations() {
		
			BufferedImage img = LoadSave.getSpriteAtlas(LoadSave.PLAYER_ATLAS);
			
			animations = new BufferedImage[9][6];
			for (int j = 0; j < animations.length; j++) {
				for (int i = 0; i < animations[j].length; i++) {
					animations[j][i] = img.getSubimage(i*64, j*40, 64, 40);
				}
			}
	}

	public void loadlvlData(int[][] lvlData) {
		this.lvlData = lvlData;
		if (!IsEntityOnFloor(hitbox, lvlData))
			inAir = true;
	} 
	
	public void resetDirBooleans() {
		left = false;
		right = false;
		up = false;
		down = false;
	}

	public void setAttacking(boolean attacking) {
		this.attacking = attacking;
	}
	
	public boolean isLeft() {
		return left;
	}

	public void setLeft(boolean left) {
		this.left = left;
	}

	public boolean isUp() {
		return up;
	}

	public void setUp(boolean up) {
		this.up = up;
	}

	public boolean isRight() {
		return right;
	}

	public void setRight(boolean right) {
		this.right = right;
	}

	public boolean isDown() {
		return down;
	}

	public void setDown(boolean down) {
		this.down = down;
	}
	
	public void setJump(boolean jump) {
		this.jump = jump;
	}
	
}
