package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.Sprites.Enemy;
import com.mygdx.game.Sprites.Tanksbody;
import com.mygdx.game.TankStars;
import com.mygdx.game.Tools.Controlers;
import com.mygdx.game.utils.TiledObjectutil;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;
public class PlayGround implements Screen {
    private final TankStars app;
    Texture texture;
    private OrthographicCamera camera;
    private Viewport viewport;
    private TmxMapLoader maploader;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;
    private World world;
    private Box2DDebugRenderer b2dr;
    private Skin skin;
    private TextButton buttonPause,buttonResume,buttonExit;
    private Enemy enemy;
    private Tanksbody player;
    private Tanksbody tanksbody;
    private SpriteBatch batch;
    private TextureAtlas atlas;
    Controlers controler;

    public PlayGround(final TankStars app){
        atlas=new TextureAtlas("texturepacks/Tanks_and_bullets.pack");
        this.app= app;
        camera=new OrthographicCamera();
        viewport=new FitViewport(TankStars.V_WIDTH,TankStars.V_HEIGHT,camera);

        maploader= new TmxMapLoader();
        map=maploader.load("Ground/Playground.tmx");
        renderer=new OrthogonalTiledMapRenderer(map);
        camera.position.set(900,520,0);

        world = new World(new Vector2(0,-10),true);
        b2dr= new Box2DDebugRenderer();
        player= new Tanksbody(this);
        BodyDef bdef= new BodyDef();
        PolygonShape shape= new PolygonShape();
        FixtureDef fdef=new FixtureDef();
        Body body;
        new TiledObjectutil(this);
        enemy = new Enemy(this);
        controler=new Controlers(app);
    }

    private void initButton() {
        buttonPause=new TextButton("Pause",skin,"default");
        buttonPause.setPosition(450,450);
        buttonPause.setSize(100,50);
        buttonPause.addAction(sequence(alpha(0),parallel(fadeIn(.5f),moveBy(0,-20,.5f,Interpolation.pow5Out))));
        buttonPause.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                app.setScreen(app.options);
            }
        });
    }

    @Override
    public void show() {
        this.skin=new Skin();
        this.skin.addRegions(app.assets.get("ui/uiskin.atlas",TextureAtlas.class));
        this.skin.add("default-font",app.font);
        this.skin.load(Gdx.files.internal("ui/uiskin.json"));
        //app.setScreen(app.options);
        //app.setScreen(app.pause);
        initButton();
    }
    public TextureAtlas getAtlas(){
        return atlas;
    }
    public TiledMap getMap(){
        return map;
    }
    public World getWorld(){
        return world;
    }
    public void handleInput(float dt){

    }
    public void update(float dt){
        handleInput(dt);
        world.step(5f,1,1);
        player.update(dt);
        enemy.update(dt);
        inputUpdate(dt);
        camera.update();
        renderer.setView(camera);
    }

    public void inputUpdate(float dt) {
        int horizontalForce=0;
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT)){
            horizontalForce-=30;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
            horizontalForce+=30;
        }
        player.b2body.setLinearVelocity(horizontalForce*5,player.b2body.getLinearVelocity().y);
    }

    @Override
    public void render(float delta) {
        update(delta);
        Gdx.gl.glClearColor(1,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        renderer.render();
        b2dr.render(world,camera.combined);
        app.batch.setProjectionMatrix(camera.combined);
        app.batch.begin();
        player.draw(app.batch);
        enemy.draw(app.batch);
        app.batch.end();
        controler.draw();

    }

    @Override
    public void resize(int width, int height) {

        viewport.update(width,height);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
map.dispose();
renderer.dispose();
world.dispose();
b2dr.dispose();
    }
}