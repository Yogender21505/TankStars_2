package com.mygdx.game.Sprites;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.mygdx.game.screens.PlayGround;

public class Enemy extends Sprite {
    public World world;
    public Body b2body;

    private TextureRegion enemystand;
    public Vector2 velocity;
    public Enemy(PlayGround screen){
        super(screen.getAtlas().findRegion("yellow-left"));
        World world = screen.getWorld();
        this.world=world;
        defineSprites();
         enemystand= new TextureRegion(getTexture(),1,1,100,56);
        setBounds(1,1,100,56);
        setRegion(enemystand);
        velocity = new Vector2(1,0);
    }
    public void update(float dt){
        b2body.setLinearVelocity(velocity);
        setPosition(b2body.getPosition().x-getWidth()/2,b2body.getPosition().y-getHeight()/2);

    }

    public void defineSprites(){
        BodyDef bdef=new BodyDef();
        bdef.position.set(1700,400);
        bdef.type=BodyDef.BodyType.DynamicBody;
        b2body= world.createBody(bdef);
        FixtureDef fdef=new FixtureDef();
        CircleShape shape=new CircleShape();
        shape.setRadius(15);

        fdef.shape=shape;
        b2body.createFixture(fdef);
    }
    public void reverseVelocity(boolean x,boolean y){
        if (x){
            velocity.x= -velocity.x;
        }
        if (y){
            velocity.y=-velocity.y;
        }
    }
}
