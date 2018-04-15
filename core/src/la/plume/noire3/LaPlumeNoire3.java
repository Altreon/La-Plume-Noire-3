package la.plume.noire3;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;
import java.util.StringTokenizer;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Base64Coder;

import End.*;
import Scene.*;

public class LaPlumeNoire3 extends ApplicationAdapter {
private static String versionText = "V1.0.0";
	
	public static String data;
	
	private static float ratio;
	private static float ratio2;
	
	private Rectangle rectangle;
	private Rectangle rectangle2;
	
	private static Batch batch;
	private static Texture cadre;
	private static Texture repeat;
	private static Music lanch;
	private static Sound start;
	private static Sound takeExtend;
	private static Sound loseExtend;
	
	private Texture clochette;
	private Texture boite;
		
	private static Button clochetteButton;
	private static Button boiteButton;
	
	private static Scene[] scene = new Scene[29];
	private static int preScene = -1;
	private static int currentScene = 0;
	private static int nextScene;
	
	private static boolean sceneLoad = false;
	
	private static Stage stage;
	private static Button[] button;
	
	private static Button repeatButton;
		
	private static File save;
	
	//Install
	private static String packageName;
	private static ZipExtract zipExtract;
	private static Thread zipExtractThread;
	private static boolean isInstall;
	private static boolean isDestination;
	
	private static Texture installBar;
	private static Texture installText;
	private static boolean atInstall;
	//fin Install
	
	//Menu
	private static Texture aienkei;
	private static Texture aienkei2;
	private static Texture imgText;
	private static Texture heip;
	private static Texture clp;
	private static Texture introBegin;
	private static Texture[] rs = new Texture[5];
	private static Button[] rsButton = new Button[5];
	private static BitmapFont versionFont;
	private static Label version;
	private static boolean isBegin = true;
	private static boolean quitBegin = false;
	private static boolean isNew = false;
	private static boolean menuEnd = true;
	private static long beginTime;
	private static long creditsTime;
	private static Sprite img;
	private static int beginStat = 0;
	private static boolean isCredits = false;
	private static boolean isEnd = false;
	private static boolean isPreFight = false;
	private static boolean isPreFightToFight = false;
	private static boolean isFight = false;
	private static boolean quitFight = false;
	private static boolean winFight;
	
	private static Button nouveau;
	private static Button continuer;
	private static Button credits;
	private static Button quitter;
	private static Button retour;
	private static Texture nouveauImg;
	private static Texture continuerImg;
	private static Texture creditsImg;
	private static Texture quitterImg;
	private static Texture retourImg;
	private static Texture whiteBack;
	
	private static Music wind;
	//fin Menu
	
	//effet de brume
	private static ParticleEffect brume;
	private static boolean brumeEnable = false;
	private static boolean brumeSens = false;
	private static boolean brumeStop = false;
	
	private static End end;
	private static Fight fight;
	
	private final static int[] fontaineSquare = {334, 256, 477, 350};
	private final int[][] loupSquare = {{184, 173, 508, 495}, {103, 88, 602, 382}, {85, 109, 578, 252}};
	private boolean alphaSquareSens;
	private long alphaSquareTime;
	
	private static boolean haveClochette = false;
	private static boolean haveBoite = false;
	
	private boolean returnM = false;
	
	public LaPlumeNoire3 (String name) {
		packageName = name;
	}
					
	@Override
	public void create () {
		data = Gdx.files.getLocalStoragePath() + "/Scene";
		
		Gdx.input.setCatchBackKey(true);
		
		ratio = Gdx.graphics.getWidth()/1280F;
		ratio2 = (float)((16D/9D)-((float)Gdx.graphics.getWidth()/(float)Gdx.graphics.getHeight()));
		ratio2 = (float)(ratio2*(9D/16D));
		
		batch = new SpriteBatch();
		batch.getProjectionMatrix().setToOrtho2D(0, 720/2-(720+(720*ratio2))/2, 1280, 720+(720*ratio2));
		if(Gdx.app.getType().name().equals("Desktop")) {
			repeat = new Texture(Gdx.files.internal("bin/repeat.png"));
			cadre = new Texture(Gdx.files.internal("bin/cadre.png"));
			versionFont = new BitmapFont(Gdx.files.internal("bin/versionFont/version.fnt"), false);
		
			lanch = Gdx.audio.newMusic(Gdx.files.internal("bin/lanch.ogg"));
			start = Gdx.audio.newSound(Gdx.files.internal("bin/start.ogg"));
			takeExtend = Gdx.audio.newSound(Gdx.files.internal("bin/take.ogg"));
			loseExtend = Gdx.audio.newSound(Gdx.files.internal("bin/lose.ogg"));
			wind = Gdx.audio.newMusic(Gdx.files.internal("bin/menu/wind.ogg"));
			
			clochette = new Texture(Gdx.files.internal("bin/clochette.png"));
			boite = new Texture(Gdx.files.internal("bin/boite.png"));
			
			brume = new ParticleEffect();
			brume.load(Gdx.files.internal("bin/brume/brume.p"), Gdx.files.internal("bin/brume"));
		
			Pixmap pm = new Pixmap(Gdx.files.internal("bin/blackfeather.png"));
			Gdx.graphics.setCursor(Gdx.graphics.newCursor(pm, 0, 0));
			pm.dispose();
		}else if(Gdx.app.getType().name().equals("Android")) {
			repeat = new Texture(Gdx.files.internal("repeat.png"));
			cadre = new Texture(Gdx.files.internal("cadre.png"));
			versionFont = new BitmapFont(Gdx.files.internal("versionFont/version.fnt"), false);
		
			lanch = Gdx.audio.newMusic(Gdx.files.internal("lanch.ogg"));
			start = Gdx.audio.newSound(Gdx.files.internal("start.ogg"));
			takeExtend = Gdx.audio.newSound(Gdx.files.internal("take.ogg"));
			loseExtend = Gdx.audio.newSound(Gdx.files.internal("lose.ogg"));
			wind = Gdx.audio.newMusic(Gdx.files.internal("menu/wind.ogg"));
			
			clochette = new Texture(Gdx.files.internal("clochette.png"));
			boite = new Texture(Gdx.files.internal("boite.png"));
			
			brume = new ParticleEffect();
			brume.load(Gdx.files.internal("brume/brume.p"), Gdx.files.internal("brume"));
		
			Pixmap pm = new Pixmap(Gdx.files.internal("blackfeather.png"));
			Gdx.graphics.setCursor(Gdx.graphics.newCursor(pm, 0, 0));
			pm.dispose();
			
			rectangle = new Rectangle(0, 720+(720/2-(720+(720*ratio2))/2)-(720/2-(720+(720*ratio2))/2), 1280, -(720/2-(720+(720*ratio2))/2), Color.BLACK);
			rectangle2 = new Rectangle(0, 720/2-(720+(720*ratio2))/2, 1280, -(720/2-(720+(720*ratio2))/2), Color.BLACK);
		}
		
		brume.setPosition(1100, 100);
				
		save = new File(Gdx.files.getLocalStoragePath() + "/save.txt");
		
		stage = new Stage();
		
		if(Gdx.app.getType().name().equals("Android")) {
			zipExtract = new ZipExtract(packageName);
			zipExtractThread = new Thread(zipExtract);
			isInstall = zipExtract.isInstall();
			isDestination = zipExtract.obbDestinationExist();
			if(isInstall){
				installBar = new Texture(Gdx.files.internal("install/installBar.png"));
				installText = new Texture(Gdx.files.internal("install/installText.png"));
				zipExtractThread.start();
				atInstall = true;
			}else if(!zipExtract.obbDestinationExist()){
				installText = new Texture(Gdx.files.internal("install/installNotFound.png"));
				Gdx.input.setCatchBackKey(false);
			}else{
				scene[0] = new La_Plume_Noire_3();
				loadBegin();
				atInstall = false;
			}
		}else{
			scene[0] = new La_Plume_Noire_3();
			loadBegin();
			isInstall = false;
			isDestination = true;
			atInstall = false;
		}
	}
	
	@Override
	public void pause () {
		if(Gdx.app.getType().name().equals("Android")) {
			if(isBegin){
				beginStat = 0;
				isCredits = false;
				if(beginStat >=3){
					unloadBegin2();
					deleteButton();
				}
			}else if(isEnd){
				end.pause();
			}else if(isFight || isPreFight){
				fight.pause();
			}else{
				scene[currentScene].pause();
			}
		}
	}
	
	@Override
	public void resume () {
		if(Gdx.app.getType().name().equals("Android")) {
			if(isBegin){
				loadBegin();
				if(beginStat >=3){
					loadBegin2();
				}
				beginTime = System.currentTimeMillis();
			}else if(isEnd){
				end.unPause();
			}else if(isFight || isPreFight){
				fight.unPause();
			}else{
				scene[currentScene].unPause();
			}
		}
	}

	
	private static void loadScene(){
		scene[1] = new La_foret_d_emeraude();
		scene[2] = new Le_dormeur_du_Val();
		scene[3] = new Le_pacte();
		scene[4] = new A_la_claire_fontaine();
		scene[5] = new Clair_obscur();
		scene[6] = new La_cabane_dans_les_bois();
		scene[7] = new Sur_un_arbre_perche();
		scene[8] = new Wolfen();
		scene[9] = new Shadow_of_the_beast();
		scene[10] = new The_hole();
		scene[11] = new Hurlement();
		scene[12] = new House();
		scene[13] = new Silent_running();
		scene[14] = new Golem_de_feuilles();
		scene[15] = new Les_griffes_de_la_nuit();
		scene[16] = new Rear_window();
		scene[17] = new L_aventure_interieure();
		scene[18] = new Le_Styx();
		scene[19] = new La_barque_solaire();
		scene[20] = new Dark_Water();
		scene[21] = new A_bout_de_souffle();
		scene[22] = new Le_cauchemar_aux_yeux_verts();
		scene[23] = new Fleche();
		scene[24] = new Flesh();
		scene[25] = new Stairway_to_heaven();
		scene[26] = new Meet_the_beast();
		scene[27] = new Dead_end();
		scene[28] = new Voyage_au_bout_de_la_nuit();
		end = new End();
		fight = new Fight();
	}
	
	private static void loadBegin(){
		if(Gdx.app.getType().name().equals("Desktop")) {
			aienkei = new Texture(Gdx.files.internal("bin/menu/Aienkei.png"));
		}else if(Gdx.app.getType().name().equals("Android")) {
			aienkei = new Texture(Gdx.files.internal("menu/Aienkei.png"));
		}
				
		beginTime = System.currentTimeMillis();
	}
	
	private static void loadBegin2(){
		if(Gdx.app.getType().name().equals("Desktop")) {
			aienkei2 = new Texture(Gdx.files.internal("bin/menu/Aienkei2.png"));
			heip = new Texture(Gdx.files.internal("bin/menu/heip.png"));
			clp = new Texture(Gdx.files.internal("bin/menu/clp.png"));
			introBegin = new Texture(Gdx.files.internal("bin/introBegin.jpg"));
			rs[0] = new Texture(Gdx.files.internal("bin/menu/patreon.png"));
			rs[1] = new Texture(Gdx.files.internal("bin/menu/youtube.png"));
			rs[2] = new Texture(Gdx.files.internal("bin/menu/twitter.png"));
			rs[3] = new Texture(Gdx.files.internal("bin/menu/facebook.png"));
			rs[4] = new Texture(Gdx.files.internal("bin/menu/google+.png"));
			nouveauImg = new Texture(Gdx.files.internal("bin/menu/Nouveau.png"));
			continuerImg = new Texture(Gdx.files.internal("bin/menu/Continuer.png"));
			creditsImg = new Texture(Gdx.files.internal("bin/menu/Credits.png"));
			quitterImg = new Texture(Gdx.files.internal("bin/menu/Quitter.png"));
			retourImg = new Texture(Gdx.files.internal("bin/menu/Retour.png"));
			imgText = new Texture(Gdx.files.internal("bin/textBegin.jpg"));
		}else if(Gdx.app.getType().name().equals("Android")) {
			aienkei2 = new Texture(Gdx.files.internal("menu/Aienkei2.png"));
			heip = new Texture(Gdx.files.internal("menu/heip.png"));
			clp = new Texture(Gdx.files.internal("menu/clp.png"));
			introBegin = new Texture(Gdx.files.internal("introBegin.jpg"));
			rs[0] = new Texture(Gdx.files.internal("menu/patreon.png"));
			rs[1] = new Texture(Gdx.files.internal("menu/youtube.png"));
			rs[2] = new Texture(Gdx.files.internal("menu/twitter.png"));
			rs[3] = new Texture(Gdx.files.internal("menu/facebook.png"));
			rs[4] = new Texture(Gdx.files.internal("menu/google+.png"));
			nouveauImg = new Texture(Gdx.files.internal("menu/Nouveau.png"));
			continuerImg = new Texture(Gdx.files.internal("menu/Continuer.png"));
			creditsImg = new Texture(Gdx.files.internal("menu/Credits.png"));
			quitterImg = new Texture(Gdx.files.internal("menu/Quitter.png"));
			retourImg = new Texture(Gdx.files.internal("menu/Retour.png"));
			imgText = new Texture(Gdx.files.internal("textBegin.jpg"));
		}
		menuEnd = true;
		System.out.println("The data location : " + data);
		img = new Sprite(new Texture(scene[0].getImgFile()));
		
		LabelStyle versionLabelStyle = new LabelStyle();
		versionLabelStyle.font = versionFont;
		version = new Label(versionText, versionLabelStyle);
		version.setPosition(0, 720-16-1);
		
		rsButton[0] = new Button();
		rsButton[0].setSize(rs[0].getWidth()*ratio, rs[0].getHeight()*ratio);
		rsButton[0].setPosition(Gdx.graphics.getWidth()/2-(rs[0].getWidth()*ratio/2) + (-32*ratio*9) + (100*ratio)*0, Gdx.graphics.getHeight()/2-(rs[0].getHeight()*ratio/2) + 100*ratio);
		rsButton[0].addListener(new ClickListener() {
			@Override
			public void clicked (InputEvent e, float x, float y) {
				Gdx.net.openURI("https://www.patreon.com/Aienkei");
			}
		});
		rsButton[1] = new Button();
		rsButton[1].setSize(rs[1].getWidth()*ratio, rs[1].getHeight()*ratio);
		rsButton[1].setPosition(Gdx.graphics.getWidth()/2-(rs[1].getWidth()*ratio/2) + (-32*ratio*9) + (100*ratio)*1, Gdx.graphics.getHeight()/2-(rs[1].getHeight()*ratio/2) + 100*ratio);
		rsButton[1].addListener(new ClickListener() {
			@Override
			public void clicked (InputEvent e, float x, float y) {
				Gdx.net.openURI("https://www.youtube.com/user/Aienkei");
			}
		});
		rsButton[2] = new Button();
		rsButton[2].setSize(rs[2].getWidth()*ratio, rs[2].getHeight()*ratio);
		rsButton[2].setPosition(Gdx.graphics.getWidth()/2-(rs[2].getWidth()*ratio/2) + (-32*ratio*0.2F) + (80*ratio)*2, Gdx.graphics.getHeight()/2-(rs[2].getHeight()*ratio/2) + 100*ratio);
		rsButton[2].addListener(new ClickListener() {
			@Override
			public void clicked (InputEvent e, float x, float y) {
				Gdx.net.openURI("https://twitter.com/Aienkei");
			}
		});
		rsButton[3] = new Button();
		rsButton[3].setSize(rs[3].getWidth()*ratio, rs[3].getHeight()*ratio);
		rsButton[3].setPosition(Gdx.graphics.getWidth()/2-(rs[3].getWidth()*ratio/2) + (-32*ratio*0.2F) + (80*ratio)*3, Gdx.graphics.getHeight()/2-(rs[3].getHeight()*ratio/2) + 100*ratio);
		rsButton[3].addListener(new ClickListener() {
			@Override
			public void clicked (InputEvent e, float x, float y) {
				Gdx.net.openURI("https://www.facebook.com/Aienkei");
			}
		});
		rsButton[4] = new Button();
		rsButton[4].setSize(rs[4].getWidth()*ratio, rs[4].getHeight()*ratio);
		rsButton[4].setPosition(Gdx.graphics.getWidth()/2-(rs[4].getWidth()*ratio/2) + (-32*ratio*0.2F) + (80*ratio)*4, Gdx.graphics.getHeight()/2-(rs[4].getHeight()*ratio/2) + 100*ratio);
		rsButton[4].addListener(new ClickListener() {
			@Override
			public void clicked (InputEvent e, float x, float y) {
				Gdx.net.openURI("https://plus.google.com/+AienkeiFreedom");
			}
		});
		
		Pixmap pixmap = new Pixmap(742, 318, Format.RGB565);
		pixmap.setColor(1, 1, 1, 1);
		pixmap.fillRectangle(0, 0, 742, 318);
		whiteBack = new Texture(pixmap);
		pixmap.dispose();
		
		wind.play();
		wind.setVolume(0);
		wind.setLooping(true);
				
		beginTime = System.currentTimeMillis();
	}
	
	private static void loadBeginButton(){
		stage.dispose();
		stage = new Stage();
		nouveau = new Button();
		nouveau.setSize(nouveauImg.getWidth()*ratio, nouveauImg.getHeight()*ratio);
		nouveau.setPosition(Gdx.graphics.getWidth()/2-(nouveauImg.getWidth()*ratio/2) + -300*ratio, Gdx.graphics.getHeight()/2-(nouveauImg.getHeight()*ratio/2) + -190*ratio);
		nouveau.addListener(new ClickListener() {
			@Override
			public void clicked (InputEvent e, float x, float y) {
				quitBegin = true;
				deleteButton();
				isNew = true;
				creditsTime = System.currentTimeMillis();
			}
		});
		continuer = new Button();
		continuer.setSize(continuerImg.getWidth()*ratio, continuerImg.getHeight()*ratio);
		continuer.setPosition(Gdx.graphics.getWidth()/2-(continuerImg.getWidth()*ratio/2) + -300*ratio, Gdx.graphics.getHeight()/2-(continuerImg.getHeight()*ratio/2) + -280*ratio);
		continuer.addListener(new ClickListener() {
			@Override
			public void clicked (InputEvent e, float x, float y) {
				quitBegin = true;
				deleteButton();
				isNew = false;
				creditsTime = System.currentTimeMillis();
			}
		});
		credits = new Button();
		credits.setSize(creditsImg.getWidth()*ratio, creditsImg.getHeight()*ratio);
		credits.setPosition(Gdx.graphics.getWidth()/2-(creditsImg.getWidth()*ratio/2) + 300*ratio, Gdx.graphics.getHeight()/2-(creditsImg.getHeight()*ratio/2) + -190*ratio);
		credits.addListener(new ClickListener() {
			@Override
			public void clicked (InputEvent e, float x, float y) {
				batch.setColor(1, 1, 1, 1);
				stage.dispose();
				stage = new Stage();
				for(Button button : rsButton){
					stage.addActor(button);
				}
				stage.addActor(retour);
				Gdx.input.setInputProcessor(stage);
				isCredits = true;
				creditsTime = System.currentTimeMillis();
			}
		});
		quitter = new Button();
		quitter.setSize(quitterImg.getWidth()*ratio, quitterImg.getHeight()*ratio);
		quitter.setPosition(Gdx.graphics.getWidth()/2-(quitterImg.getWidth()*ratio/2) + 300*ratio, Gdx.graphics.getHeight()/2-(quitterImg.getHeight()*ratio/2) + -280*ratio);
		quitter.addListener(new ClickListener() {
			@Override
			public void clicked (InputEvent e, float x, float y) {
				Gdx.app.exit();
			}
		});
		stage.addActor(nouveau);
		stage.addActor(continuer);
		stage.addActor(credits);
		stage.addActor(quitter);
		
		retour = new Button();
		retour.setSize(retourImg.getWidth()*ratio, retourImg.getHeight()*ratio);
		retour.setPosition(Gdx.graphics.getWidth()/2-(retourImg.getWidth()*ratio/2), Gdx.graphics.getHeight()/2-(retourImg.getHeight()*ratio/2) + -240*ratio);
		retour.addListener(new ClickListener() {
			@Override
			public void clicked (InputEvent e, float x, float y) {
				stage.dispose();
				stage = new Stage();
				stage.addActor(nouveau);
				stage.addActor(continuer);
				stage.addActor(credits);
				stage.addActor(quitter);
				Gdx.input.setInputProcessor(stage);
				isCredits = false;
				creditsTime = System.currentTimeMillis();
			}
		});
		
		Gdx.input.setInputProcessor(stage);
	}
	
	private static void deleteButton(){
		stage.dispose();
		stage = new Stage();
		Gdx.input.setInputProcessor(null);
	}
	
	private static void unloadBegin () {
		wind.stop();
		aienkei.dispose();
		aienkei2.dispose();
		heip.dispose();
		clp.dispose();
		rs[0].dispose();
		rs[1].dispose();
		rs[2].dispose();
		rs[3].dispose();
		rs[4].dispose();
		nouveauImg.dispose();
		continuerImg.dispose();
		creditsImg.dispose();
		quitterImg.dispose();
		retourImg.dispose();
		whiteBack.dispose();
		System.gc();
		
		stage.dispose();
		stage = new Stage();
	}
	
	public void unloadBegin2 () {
		introBegin.dispose();
		imgText.dispose();
		img.getTexture().dispose();
	}
	
	public static void endInstall () {
		installBar.dispose();
		installText.dispose();
		isInstall = false;
		isDestination = true;
	}

	@Override
	public void render () {
		if(isInstall || !isDestination){
			Gdx.gl.glClearColor(0, 0, 0, 0);
			Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
			batch.begin();
			batch.draw(installText, 0, 0);
			if(isInstall){
				batch.draw(installBar, 0, 510, zipExtract.getProgress(), installBar.getHeight());
			}
			batch.end();
		}else{
			
		if(Gdx.input.isKeyJustPressed(Keys.ESCAPE) || Gdx.input.isKeyJustPressed(Keys.BACK)){
			returnMenu();
		}
		
		if(returnM){
			float time = 1 - ((System.currentTimeMillis() - beginTime)/1000F);
			batch.setColor(1, 1, 1, time);
			if(scene[currentScene].getAudio().isPlaying()){
				scene[currentScene].getAudio().setVolume(time);
			}else if(lanch.isPlaying()){
				lanch.setVolume(time);
			}
			if(time <= 0){
				batch.setColor(1, 1, 1, 0);
				if(lanch.isPlaying()){
					lanch.stop();
				}
				lanch.setVolume(1);
				scene[currentScene].close(true);
				scene[currentScene].unload();
				loadBegin2();
				wind.play();
				isBegin = true;
				returnM = false;
				currentScene = 0;
				beginTime = System.currentTimeMillis();
			}
		}
		if(isBegin){
			Gdx.gl.glClearColor(0, 0, 0, 0);
			Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
			
			if(atInstall){
				scene[0] = new La_Plume_Noire_3();
				loadBegin();
				atInstall = false;
			}
			
			float time = 0;
			if(beginStat == 0){
				time = (System.currentTimeMillis() - beginTime)/1000F;
				batch.setColor(1, 1, 1, time);
				if(time >= 1){
					start.play();
					batch.setColor(1, 1, 1, 1);
					beginStat ++;
					beginTime = System.currentTimeMillis();
				}
			}
			if(beginStat == 1){
				time = (System.currentTimeMillis() - beginTime)/1000F;
				if(time >= 1){
					beginStat ++;
					beginTime = System.currentTimeMillis();
				}
			}
			if(beginStat == 2){
				time = 1-((System.currentTimeMillis() - beginTime)/1000F);
				batch.setColor(1, 1, 1, time);
				if(time <= 0){
					batch.setColor(1, 1, 1, 0);
					beginStat ++;
					loadBegin2();
					beginTime = System.currentTimeMillis();
				}
			}
			if(beginStat < 3){
				Pixmap pixmap = new Pixmap(1280, 720, Pixmap.Format.RGBA4444);
				pixmap.setColor(1, 1, 1, 1);
				pixmap.fill();
				Texture texture = new Texture(pixmap);
				pixmap.dispose();
				
				batch.begin();
				batch.draw(texture, 0, 0);
				batch.draw(aienkei, (int)(1280/2-(aienkei.getWidth()/2)), (int)(720/2-(aienkei.getHeight()/2)));
				batch.end();
				texture.dispose();
			}else{
				float alpha = 1;
				if(beginStat == 3){
					time = (System.currentTimeMillis() - beginTime)/1000F;
					batch.setColor(1, 1, 1, time);
					alpha = time;
					if(time >= 1){
						batch.setColor(1, 1, 1, 1);
						wind.setVolume(1);
						alpha = 1;
						loadBeginButton();
						beginStat ++;
					}else{
						wind.setVolume(time);
					}
				}else{
					if(isCredits || quitBegin){
						alpha = 1-((System.currentTimeMillis() - creditsTime)/1000F);
						if(alpha <= 0){
							wind.setVolume(0);
							alpha = 0;
							if(quitBegin){
								batch.setColor(1, 1, 1, 1);
								img.setAlpha(alpha);
								if(!sceneLoad){
									loadScene();
									sceneLoad = true;
								}else{
									for(Scene s : scene){
										s.reniPassAudio();
									}
									haveClochette = false;
									haveBoite = false;
								}
								if(isNew){
									nouveau();
								}else{
									continuer();
								}
							}
						}else{
							wind.setVolume(alpha);
						}
					}else{
						alpha = (System.currentTimeMillis() - creditsTime)/1000F;
						if(alpha >= 1){
							wind.setVolume(1);
							alpha = 1;
						}else{
							wind.setVolume(alpha);
						}
					}
				}
				img.setAlpha(alpha);
				
				batch.begin();
				if(beginStat == 4 && !quitBegin){
					batch.draw(whiteBack, 273, 321);
					batch.draw(heip, (int)(1280/2-(heip.getWidth()/2)), (int)(720/2-(heip.getHeight()/2) + 230));
					batch.draw(aienkei2, (int)(1280/2-(aienkei2.getWidth()/2)), (int)(720/2-(aienkei2.getHeight()/2) + 115));
					for(int i=0; i < rs.length; i++){
						if(i < 2){
							batch.draw(rs[i], (int)(1280/2-(rs[i].getWidth()/2) + (-32*9) + 100*i), (int)(720/2-(rs[i].getHeight()/2) + 100));
						}else{
							batch.draw(rs[i], (int)(1280/2-(rs[i].getWidth()/2) + (-32*0.2F) + 80*i), (int)(720/2-(rs[i].getHeight()/2) + 100));
						}
					}
					batch.draw(clp, (int)(1280/2-(clp.getWidth()/2)), (int)(720/2-(clp.getHeight()/2) + -20));
				}else if(quitBegin){
					if(currentScene != 0){
						batch.draw(introBegin, 273, 321);
						batch.draw(cadre, 0, 0);
					}else{
						batch.draw(img.getTexture(), 0, 0);
					}
				}
				img.draw(batch);
				if(isCredits){
					batch.draw(retourImg, (int)(1280/2-(retourImg.getWidth()/2)), (int)(720/2-(retourImg.getHeight()/2) + -230));
				}else if(!quitBegin){
					batch.draw(nouveauImg, (int)(1280/2-(nouveauImg.getWidth()/2) + -300), (int)(720/2-(nouveauImg.getHeight()/2) + -190));
					batch.draw(continuerImg, (int)(1280/2-(continuerImg.getWidth()/2) + -300), (int)(720/2-(continuerImg.getHeight()/2) + -280));
					batch.draw(creditsImg, (int)(1280/2-(creditsImg.getWidth()/2) + 300), (int)(720/2-(creditsImg.getHeight()/2) + -190));
					batch.draw(quitterImg, (int)(1280/2-(quitterImg.getWidth()/2) + 300), (int)(720/2-(quitterImg.getHeight()/2) + -280));
				}
				
				if(beginStat == 4 && !quitBegin){
					version.draw(batch, 1);
				}
				batch.end();
			}
		}else if(isEnd){
			end.update();
			batch.begin();
			batch.draw(end.getImg(), 0, 0);
			batch.end();
		}else if(isPreFight){
			if(!isPreFightToFight){
				if(currentScene == 25 && !quitFight){
					if(fight.updateBoucle()){
						isPreFightToFight = true;
					}
				}else{
					if(fight.updateFull(!quitFight)){
						isPreFightToFight = true;
					}
				}
				batch.begin();
				batch.draw(fight.getImgFull(), 0, 0);
				batch.end();
			}else if(!quitFight){
				scene[currentScene].unload();
				
				stage = new Stage();
				button = new Button[loupSquare.length];
				for(int i=0; i < loupSquare.length; i++){
					button[i] = new Button();
					button[i].setSize(loupSquare[i][0]*ratio, loupSquare[i][1]*ratio);
					button[i].setPosition(loupSquare[i][2]*ratio, loupSquare[i][3]*ratio+(Gdx.graphics.getHeight()-720*ratio)/2);
					final int i2 = i;
					button[i].addListener(new ClickListener() {
						@Override
						public void clicked (InputEvent e, float x, float y) {
							if(fight.canAttack()){
								fight.attack(i2);
							}
						}
					});
					stage.addActor(button[i]);
				}
				Gdx.input.setInputProcessor(stage);
				
				isPreFightToFight = false;
				isFight = true;
				isPreFight = false;
				alphaSquareSens = true;
				alphaSquareTime = System.currentTimeMillis();
			}else{
				isFight = false;
				isPreFight = false;
				if(winFight){
					System.out.println("changeScene : 27");
					changeScene(27);
				}else{
					System.out.println("changeScene : 26");
					changeScene(26);
				}
				fight.unload();
				change();
				preScene = -1;
			}
		}else if(isFight){
			fight.update();
			batch.begin();
			if(fight.canAttack()){
				batch.draw(fight.getImg(), 0, 0);
				for(int[] s : loupSquare){
					float alphaSquare;
					if(alphaSquareSens){
						alphaSquare = ((System.currentTimeMillis() - alphaSquareTime)/10)/250F;
						if(alphaSquare >= 0.25F){
							fight.getSquare().setAlpha(0.25F);
							alphaSquareTime = System.currentTimeMillis();
							alphaSquareSens = false;
						}else{
							fight.getSquare().setAlpha(alphaSquare);
						}
					}else{
						alphaSquare = 0.25F - (((System.currentTimeMillis() - alphaSquareTime)/10)/250F);
						if(alphaSquare <= 0){
							fight.getSquare().setAlpha(0);
							alphaSquareTime = System.currentTimeMillis();
							alphaSquareSens = true;
						}else{
							fight.getSquare().setAlpha(alphaSquare);
						}
					}
					
					fight.getSquare().setSize(s[0], s[1]);
					fight.getSquare().setPosition(s[2], s[3]);
					fight.getSquare().draw(batch);
				}
			}else{
				batch.draw(fight.getImg(), fight.shake()*ratio, 0);
				fight.getImg2().draw(batch);
				alphaSquareSens = true;
				alphaSquareTime = System.currentTimeMillis();
			}
			batch.end();
		}else{
			
			Gdx.gl.glClearColor(0, 0, 0, 0);
			Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
			scene[currentScene].update();
			if(menuEnd){
				unloadBegin2();
				quitBegin = false;
				menuEnd = false;
			}
			if(preScene != -1){
				scene[preScene].unload();
				preScene = -1;
			}
			
			batch.begin();
			if(!scene[currentScene].isMouvement()){
				batch.draw(scene[currentScene].getImg(), 273-(int)(scene[currentScene].getMouvementPos()), 321);
				if(scene[currentScene].isClochetteShow() && !scene[currentScene].extendSceneBoucle()){
					scene[currentScene].getClochetteImg().draw(batch);
				}
				if(scene[currentScene].isBoiteShow() && !scene[currentScene].extendSceneBoucle()){
					scene[currentScene].getBoiteImg().draw(batch);
				}
			}else{
				if(currentScene != 0){
					batch.draw(scene[currentScene].getImg(), 273, 321);
				}else{
					batch.draw(scene[currentScene].getImg(), 0, 0);
				}
				if(scene[currentScene].isClochetteShow() && !scene[currentScene].extendSceneBoucle()){
					scene[currentScene].getClochetteImg().draw(batch);
				}
				if(scene[currentScene].isBoiteShow() && !scene[currentScene].extendSceneBoucle()){
					scene[currentScene].getBoiteImg().draw(batch);
				}
			}
			if(brumeEnable){
				if(!brumeStop){
					if(brumeSens){
						brume.getEmitters().get(0).setPosition(brume.getEmitters().get(0).getX(), brume.getEmitters().get(0).getY()-0.25F);
						if(brume.getEmitters().get(0).getY() <= 300){
							brumeSens = false;
						}
					}else{
						brume.getEmitters().get(0).setPosition(brume.getEmitters().get(0).getX(), brume.getEmitters().get(0).getY()+0.25F);
						if(brume.getEmitters().get(0).getY() >= 500){
							brumeSens = true;
						}
					}
				}else{
					if(brume.getEmitters().get(0).getY() > 100){
						brume.getEmitters().get(0).setPosition(brume.getEmitters().get(0).getX(), brume.getEmitters().get(0).getY()-0.5F);
					}else{
						brume.getEmitters().get(0).setContinuous(false);
						if(brume.getEmitters().get(0).getActiveCount() == 0){
							brumeEnable = false;
							brume.reset();
						}
					}
				}
				if(brumeEnable){
					brume.update(Gdx.graphics.getDeltaTime());
					brume.draw(batch);
				}
			}
			
			batch.draw(scene[currentScene].getImgText(), 90, 26);
			if(currentScene != 0){
				batch.draw(cadre, 0, 0);
			}
			if(scene[currentScene].asClochette() && haveClochette){
				batch.draw(clochette, 30, 274);
			}
			if(scene[currentScene].asBoite() && haveBoite){
				batch.draw(boite, 1144, 272);
			}
			if(!isLastScenes() && preScene == -1 && scene[currentScene].canRepeat()){
				batch.draw(repeat, 1168, 190);
			}
			if(Gdx.app.getType().name().equals("Android")) {
				rectangle.draw(batch, 1);
				rectangle2.draw(batch, 1);
			}
			batch.end();
		}
		}
	}
	
	private void returnMenu() {
		removeBrume();
		if(isBegin){
			if(isCredits){
				stage.dispose();
				stage = new Stage();
				stage.addActor(nouveau);
				stage.addActor(continuer);
				stage.addActor(credits);
				stage.addActor(quitter);
				Gdx.input.setInputProcessor(stage);
				isCredits = false;
				creditsTime = System.currentTimeMillis();
			}
		}else if(!isEnd && !isPreFight && !isFight){
			returnM = true;
			beginStat = 3;
			beginTime = System.currentTimeMillis();
		}
	}

	public static void changeScene (int nextScene) {
		scene[nextScene].load();
		LaPlumeNoire3.nextScene = nextScene;
			
		stage.dispose();
		stage = new Stage();
		button = new Button[scene[nextScene].getTextInfo().length];
		for(int i=0; i < button.length; i++){
			button[i] = new Button();
			button[i].setSize(scene[nextScene].getTextInfo()[i][0]*ratio, scene[nextScene].getTextInfo()[i][1]*ratio);
			button[i].setPosition(scene[nextScene].getTextInfo()[i][2]*ratio, scene[nextScene].getTextInfo()[i][3]*ratio+(Gdx.graphics.getHeight()-720*ratio)/2);
			final int i2 = i;
			final int nextScene2 = nextScene;
			button[i].addListener(new ClickListener() {
				@Override
				public void clicked (InputEvent e, float x, float y) {
					if(scene[currentScene].canClick(i2)){
						if(scene[nextScene2].getTextInfo()[i2][4] == -10){
							scene[currentScene].closeForFight();
							System.out.println("fight");
							fighting();
						}else if(scene[nextScene2].getTextInfo()[i2][4] == -1){
							if(Gdx.app.getType().name().equals("Desktop")) {
								Gdx.net.openURI("http://altreondownloadpage.wixsite.com/laplumenoire");
							}else{
								Gdx.net.openURI("https://play.google.com/store/apps/details?id=la.plume.noire1.android");
							}
						}else if(scene[nextScene2].getTextInfo()[i2][4] == -2){
							if(Gdx.app.getType().name().equals("Desktop")) {
								Gdx.net.openURI("http://altreondownloadpage.wixsite.com/laplumenoire/laplumenoire2");
							}else{
								Gdx.net.openURI("https://play.google.com/store/apps/details?id=la.plume.noire2.android");
							}
						}else if(scene[nextScene2].getTextInfo()[i2][4] == -20){
							Gdx.net.openURI("https://www.patreon.com/Aienkei");
						}else if(scene[nextScene2].getTextInfo()[i2][4] == -21){
							
						}else if(scene[nextScene2].getTextInfo()[i2][4] == -22){
							Gdx.net.openURI("https://www.youtube.com/watch?v=2QWa2aRsIkg");
						}else{
							scene[currentScene].close(false);
							System.out.println("changeScene : " + scene[nextScene2].getTextInfo()[i2][4]);
							changeScene(scene[nextScene2].getTextInfo()[i2][4]);
						}
					}
				}
			});
			stage.addActor(button[i]);
		}
		
		repeatButton = new Button();
		repeatButton.setSize(32*ratio, 32*ratio);
		repeatButton.setPosition(1168*ratio, 190*ratio+(Gdx.graphics.getHeight()-720*ratio)/2);
		repeatButton.addListener(new ClickListener() {
			@Override
			public void clicked (InputEvent e, float x, float y) {
				if(!isLastScenes() && preScene == -1 && scene[currentScene].canRepeat()){
					scene[currentScene].setAudio();
				}
			}
		});

		stage.addActor(repeatButton);
		
		if(scene[nextScene].asClochette()){
			clochetteButton = new Button();
			clochetteButton.setSize(96*ratio, 119*ratio);
			clochetteButton.setPosition(30*ratio, 274*ratio+(Gdx.graphics.getHeight()-720*ratio)/2);
			clochetteButton.addListener(new ClickListener() {
				@Override
				public void clicked (InputEvent e, float x, float y) {
					if(scene[currentScene].canActiveExtend() && haveClochette){
						scene[currentScene].activeClochette();
					}
				}
			});
			stage.addActor(clochetteButton);
		}
		if(scene[nextScene].asBoite() && nextScene != 27){
			boiteButton = new Button();
			boiteButton.setSize(122*ratio, 119*ratio);
			boiteButton.setPosition(1144*ratio, 272*ratio+(Gdx.graphics.getHeight()-720*ratio)/2);
			boiteButton.addListener(new ClickListener() {
				@Override
				public void clicked (InputEvent e, float x, float y) {
					if(scene[currentScene].canActiveExtend() && haveBoite){
						scene[currentScene].activeBoite();
					}
				}
			});
			stage.addActor(boiteButton);
		}else if(scene[nextScene].asBoite() && nextScene == 27){
			//Dead End
			boiteButton = new Button();
			boiteButton.setSize(122*ratio, 119*ratio);
			boiteButton.setPosition(1144*ratio, 272*ratio+(Gdx.graphics.getHeight()-720*ratio)/2);
			boiteButton.addListener(new ClickListener() {
				@Override
				public void clicked (InputEvent e, float x, float y) {
					if(scene[currentScene].canActiveExtend() && haveBoite){
						scene[currentScene].close(false);
						System.out.println("changeScene : 28");
						changeScene(28);
					}
				}
			});
			stage.addActor(boiteButton);
		}
		
		if(nextScene == 4){
			createFontaineSquare();
		}
		
		Gdx.input.setInputProcessor(stage);
		
		LaPlumeNoire3.nextScene = nextScene;
		if(nextScene != 0){
			lanch.play();
		}
	}
	
	public static void changeButton(final int[][] textInfo) {
		stage.dispose();
		stage = new Stage();
		button = new Button[textInfo.length];
		for(int i=0; i < button.length; i++){
			button[i] = new Button();
			button[i].setSize(textInfo[i][0]*ratio, textInfo[i][1]*ratio);
			button[i].setPosition(textInfo[i][2]*ratio, textInfo[i][3]*ratio+(Gdx.graphics.getHeight()-720*ratio)/2);
			final int i2 = i;
			button[i].addListener(new ClickListener() {
				@Override
				public void clicked (InputEvent e, float x, float y) {
					if(scene[currentScene].canClick(i2)){
						scene[currentScene].close(false);
						System.out.println("changeScene : " + textInfo[i2][4]);
						changeScene(textInfo[i2][4]);
					}
				}
			});
			stage.addActor(button[i]);
		}
				
		repeatButton = new Button();
		repeatButton.setSize(32*ratio, 32*ratio);
		repeatButton.setPosition(1168*ratio, 190*ratio+(Gdx.graphics.getHeight()-720*ratio)/2);
		repeatButton.addListener(new ClickListener() {
			@Override
			public void clicked (InputEvent e, float x, float y) {
				if(!isLastScenes() && preScene == -1 && scene[currentScene].canRepeat()){
					scene[currentScene].setAudio();
				}
			}
		});

		stage.addActor(repeatButton);
		
		if(scene[currentScene].asClochette()){
			clochetteButton = new Button();
			clochetteButton.setSize(96*ratio, 119*ratio);
			clochetteButton.setPosition(30*ratio, 274*ratio+(Gdx.graphics.getHeight()-720*ratio)/2);
			clochetteButton.addListener(new ClickListener() {
				@Override
				public void clicked (InputEvent e, float x, float y) {
					if(scene[currentScene].canActiveExtend() && haveClochette){
						scene[currentScene].activeClochette();
					}
				}
			});
			stage.addActor(clochetteButton);
		}
		if(scene[currentScene].asBoite()){
			boiteButton = new Button();
			boiteButton.setSize(122*ratio, 119*ratio);
			boiteButton.setPosition(1144*ratio, 272*ratio+(Gdx.graphics.getHeight()-720*ratio)/2);
			boiteButton.addListener(new ClickListener() {
				@Override
				public void clicked (InputEvent e, float x, float y) {
					if(scene[currentScene].canActiveExtend() && haveBoite){
						scene[currentScene].activeBoite();
					}
				}
			});
			stage.addActor(boiteButton);
		}
		
		if(currentScene == 4){
			createFontaineSquare();
		}
		
		Gdx.input.setInputProcessor(stage);
		
	}
	
	private static void createFontaineSquare(){
		//fontaine square
		Button button = new Button();
		button = new Button();
		button.setSize(fontaineSquare[0]*ratio, fontaineSquare[1]*ratio);
		button.setPosition(fontaineSquare[2]*ratio, fontaineSquare[3]*ratio+(Gdx.graphics.getHeight()-720*ratio)/2);
		button.addListener(new ClickListener() {
			@Override
			public void clicked (InputEvent e, float x, float y) {
				if(scene[4].canClickFontaine()){
					scene[currentScene].close(false);
					System.out.println("changeScene : 23");
					changeScene(23);
				}
			}
		});
		stage.addActor(button);
	}
	
	public static void activeBrume () {
		brumeStop = false;
		brumeSens = false;
		brume.getEmitters().get(0).setContinuous(true);
		brume.start();
		brumeEnable = true;
	}
	
	public static void desactiveBrume () {
		brumeStop = true;
	}
	
	public static void removeBrume () {
		brumeEnable = false;
		brume.reset();
		brume.setPosition(1100, 100);
	}
	
	public static void addExtends() {
		if(!haveClochette || !haveBoite){
			takeExtend.play();
		}
		haveClochette = true;
		haveBoite = true;
	}
	
	public static void removeClochette() {
		if(haveClochette){
			loseExtend.play();
		}
		haveClochette = false;
	}
	
	public static void removeBoite() {
		if(haveBoite){
			loseExtend.play();
		}
		haveBoite = false;
	}
	
	public static boolean haveExtends() {
		if(haveClochette && haveBoite){
			return true;
		}else{
			return false;
		}
	}
	
	public static Music getLanch () {
		return lanch;
	}
	
	public static boolean isLastScenes(){
		if(currentScene == 28){
			return true;
		}else{
			return false;
		}
	}

	public static void change() {
		preScene = currentScene;
		currentScene = nextScene;
		save();
		scene[currentScene].start();
		System.gc();
	}
	
	public static Scene getScene (int i) {
		return scene[i];
	}
	
	public static void nouveau () {
		unloadBegin();
		try {
			PrintWriter writer = new PrintWriter(save, "UTF-8");
			String write = "";
			write += "0:";
			write += "false:false:false:";
			for(int i=0; i < scene.length; i++){
				write += "false:";
			}
			writer.print(Base64Coder.encodeString(write));
			writer.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		scene[currentScene].setFirstScene();
		changeScene(currentScene);
		scene[currentScene].start();
		isBegin = false;
	}
	
	public static void continuer () {
		if(save.exists() && !save.isDirectory()){
			unloadBegin();
			try {
				Scanner sc = new Scanner(save);
				StringTokenizer info = new StringTokenizer(Base64Coder.decodeString(sc.nextLine()), ":");
				currentScene = Integer.parseInt(info.nextToken());
				haveClochette = Boolean.parseBoolean(info.nextToken());
				haveBoite = Boolean.parseBoolean(info.nextToken());
				for(int i=0; i < scene.length; i++){
					StringTokenizer info2 = new StringTokenizer(info.nextToken(), "/");
					if(Boolean.parseBoolean(info2.nextToken())){
						scene[i].passAudio();
					}
					if(i < 23){
						if(Boolean.parseBoolean(info2.nextToken()) && scene[i].asClochette()){
							scene[i].passAudioClochette();
						}
						if(Boolean.parseBoolean(info2.nextToken()) && scene[i].asBoite()){
							scene[i].passAudioBoite();
						}
					}
					
				}
				sc.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		
			scene[currentScene].setFirstScene();
			lanch.play();
			changeScene(currentScene);
			scene[currentScene].start();
			isBegin = false;
		}else{
			nouveau();
		}
	}
	
	//Début des fonctions spéciales
	public static boolean canShowGoFointaineIfClochette() {
		if(scene[3].getPassAudioBoite()){
			return true;
		}else{
			return false;
		}
	}
	
	public static boolean canShowGoFointaineIfBoite() {
		if(scene[3].getPassAudioClochette()){
			return true;
		}else{
			return false;
		}
	}
	
	public static boolean fontaineAsShow() {
		if(scene[4].getPassAudio()){
			return true;
		}else{
			return false;
		}
	}
	
	//Fin des fonctions spéciales
	
	public static void ending () {
		end.load();
		isEnd = true;
		System.gc();
	}
	
	public static void fighting () {
		stage.dispose();
		if(currentScene == 25){
			fight.load(true);
		}else{
			fight.load(false);
		}
		fight.begin();
		quitFight = false;
		isPreFight = true;
		isPreFightToFight = false;
		fight.iniTime();
	}
	
	public static void loseFight(){
		fight.end();
		quitFight = true;
		isPreFight = true;
		isPreFightToFight = false;
		winFight = false;
		fight.iniTime();
		lanch.play();
	}
	
	public static void winFight(){
		fight.end();
		quitFight = true;
		isPreFight = true;
		isPreFightToFight = false;
		winFight = true;
		fight.iniTime();
		lanch.play();
	}
	
	public static void reboot () {
		isEnd = false;
		isBegin = true;
		beginStat = 0;
		loadBegin();
		System.gc();
	}
	
	public static void save () {
		try {
			PrintWriter writer = new PrintWriter(save, "UTF-8");
			String write = "";
			write += currentScene + ":";
			write += haveClochette + ":";
			write += haveBoite + ":";
			for(int i=0; i < scene.length; i++){
				write += scene[i].getPassAudio() + "/";
				if(i < 23){
					if(scene[i].asClochette()){
						write += scene[i].getPassAudioClochette() + "/";
					}else{
						write += false + "/";
					}
					if(scene[i].asBoite()){
						write += scene[i].getPassAudioBoite() + ":";
					}else{
						write += false + ":";
					}
				}else{
					write += false + "/";
					write += false + ":";
				}
			}
			writer.print(Base64Coder.encodeString(write));
			writer.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
