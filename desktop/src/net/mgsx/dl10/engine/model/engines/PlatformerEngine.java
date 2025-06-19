package net.mgsx.dl10.engine.model.engines;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.IntSet;
import com.badlogic.gdx.utils.ObjectMap;
import com.badlogic.gdx.utils.ObjectMap.Entry;
import com.badlogic.gdx.utils.viewport.Viewport;

import net.mgsx.dl10.GameSettings;
import net.mgsx.dl10.assets.GameAssets;
import net.mgsx.dl10.engine.inputs.InputManager;
import net.mgsx.dl10.engine.model.entities.MenuCameraAnim;
import net.mgsx.dl10.engine.model.factories.PlatformerTilemapFactory;
import net.mgsx.dl10.engine.model.renderer.PlatformerRenderer;
import net.mgsx.dl10.ui.GameHUD;
import net.mgsx.dl10.ui.MenuHUD;

public class PlatformerEngine {

	public PlatformerLevel level;
	
	public final ObjectMap<String, PlatformerLevel> levels = new ObjectMap<String, PlatformerLevel>();

	public final Array<Transition> transitions = new Array<Transition>();

	private Batch batch;

	public int playerLife;
	public int playerContinues;
	
	public final IntSet bigBonus = new IntSet();

	public int smallBonus;
	
	public PlatformerRenderer renderer;

	public InputManager inputManager;

	public boolean ended;
	
	public PlatformerEngine(InputManager inputManager) {
		
		this.inputManager = inputManager;
		batch = new SpriteBatch();
		
	}
	
	public void start(){
		
		playerContinues = GameSettings.playerContinues;
		
		// TODO should be commited at some points...
		
		bigBonus.clear();
		
		smallBonus = 0; 
		
		reset("menu");
		
		ended = false;
	}
	public void reset(String startLevel){
		
		playerLife = GameSettings.playerLifeMax;
		
		PlatformerTilemapFactory f = new PlatformerTilemapFactory(this);
		
		levels.clear();
		
		GameHUD gameHUD = new GameHUD(this);
		
		for(Entry<String, TiledMap> entry : GameAssets.i.maps){
			PlatformerLevel clevel = f.createScene(entry.key);
			clevel.hud = gameHUD;
			levels.put(entry.key, clevel);
		}
		
		// configure
		PlatformerLevel menu = levels.get("menu");
		menu.bgTexture = GameAssets.i.bgRoof;
		menu.hud = new MenuHUD(this);
		menu.cameraAnim = new MenuCameraAnim();
		menu.music = "menu";

		PlatformerLevel roof1 = levels.get("roof1");
		roof1.bgTexture = GameAssets.i.bgRoof;
		roof1.viewOffset = -2;
		roof1.music = "roof";
		
		float houseVolume = .5f;
		
		PlatformerLevel roof2 = levels.get("roof2");
		roof2.music = "cakes";
		roof2.musicLevel = houseVolume;
		
		PlatformerLevel house = levels.get("house");
		house.screenClamp = false;
		house.screenZoom = 4;
		house.viewOffset = 4;
		house.music = "cakes";
		house.musicLevel = houseVolume;
		
		PlatformerLevel cake1 = levels.get("cake1");
		cake1.bgTexture = GameAssets.i.bgcake;
		cake1.music = "jazz1";
		
		PlatformerLevel cake2 = levels.get("cake2");
		cake2.bgTexture = GameAssets.i.bgcake;
		cake2.music = "jazz2";
		
		PlatformerLevel cake3 = levels.get("cake3");
		cake3.bgTexture = GameAssets.i.bgcake;
		cake3.music = "jazz3";
		
		level = levels.get(startLevel);
		
		GameAssets.i.playMusic(level.music, level.musicLevel);
		
		renderer.initialize();
	}
	
	public void update(float delta) {
		if(transitions.size > 0){
			transitions.peek().update(this, delta);
		}else{
			level.update(delta);
		}
	}

	public void render(Viewport viewport) {
		if(transitions.size > 0){
			batch.setProjectionMatrix(viewport.getCamera().combined);
			batch.disableBlending();
			batch.begin();
			transitions.peek().draw(batch, viewport);
			batch.end();
		}
	}
	
}
