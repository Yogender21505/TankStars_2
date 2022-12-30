package com.mygdx.game.explosion;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

//@ https://github.com/hollowbit/libgdx-2d-tutorial/blob/master/core/src/net/hollowbit/spacegame/entities/Explosion.java
//@ https://www.youtube.com/watch?v=jzFZ7N-SKfk

public class Explosion {

    public static final float FRAME_LENGTH = 0.2f;
    public static final int OFFSET = 8;
    public static final int SIZE = 64;
    public static final int IMAGE_SIZE = 32;

    private static Animation anim = null;
    float x, y;
    float statetime;
    static int hit;

    public boolean remove = false;

    public Explosion (float x, float y) {
        this.x = x - OFFSET;
        this.y = y - OFFSET;
        statetime = 0;

        if (anim == null)
            anim = new Animation(FRAME_LENGTH, TextureRegion.split(new Texture("explosion.png"), IMAGE_SIZE, IMAGE_SIZE)[0]);
    }

    public void update (float deltatime) {
        statetime += deltatime;
        if (anim.isAnimationFinished(statetime))
            remove = true;
    }

    public static int explosionhappen(int r, int angle, int dist){
        if(hit!=1){
            float rad= (float) (angle/3.14*2);
            float v= rad*dist;

            return 0;
        }
        else{
            return 1;
        }
    }

    public void render (SpriteBatch batch) {
        batch.draw((Texture) anim.getKeyFrame(statetime), x, y, SIZE, SIZE);
    }

}