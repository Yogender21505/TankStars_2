package com.mygdx.game.Sprites;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.*;
import com.mygdx.game.TankStars;
import com.mygdx.game.screens.PlayGround;

public class Tanksbody extends Sprite {
    public World world;
    public Body b2body;
    private TextureRegion tankstand;
    public Tanksbody(PlayGround screen){

        super(screen.getAtlas().findRegion("yellow-right"));
        World world = screen.getWorld();
        this.world=world;
        defineSprites();
        tankstand = new TextureRegion(getTexture(),0,0,100,56);
        setBounds(0,-23,100,56);
        setRegion(tankstand);

    }
    public void update(float dt){
        setPosition(b2body.getPosition().x-getWidth()/2,b2body.getPosition().y-getHeight()/2);
    }

    public void defineSprites(){
        BodyDef bdef=new BodyDef();
        bdef.position.set(50,340);
        bdef.type=BodyDef.BodyType.DynamicBody;
        b2body= world.createBody(bdef);
        FixtureDef fdef=new FixtureDef();
        CircleShape shape=new CircleShape();
        shape.setRadius(15);

        fdef.shape=shape;
        b2body.createFixture(fdef);
    }
}
