package Scene;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;

import SceneExtend.SceneExtend;
import la.plume.noire3.LaPlumeNoire3;

public class Voyage_au_bout_de_la_nuit extends Scene{
	private static String sceneName = "Voyage au bout de la nuit";
	private static boolean asBoucle = false;
	private static int mouvement = 2;
	private static boolean asEnd = false;
	private static boolean asClochette = false;
	private static boolean asBoite = false;
	private static SceneExtend[] sceneExtends = {};
	private static int[] boucleInfo = {0, 0};
	private static int[][] textInfo = {{1110, 65, 90, 157, -20, 240+31}, {1110, 65, 90, 92, -21, 240+36}, {1110, 66, 90, 26, -22, 240+41}};
	private static float[] audioInfo = {2.1F, 420+3.1F};
	
	private int switchStat = 0;
	private FileHandle[] introEndFileSwitch = new FileHandle[6];
	private float[] switchScreenTime = {120+33F, 180+20.6F, 240+29.4F, 360+46F, 420+0.1F};
	
	private FileHandle[] boucle;
	private FileHandle[] boucle2;
	
	private static boolean boiteRemoved = false;
	
	public Voyage_au_bout_de_la_nuit () {
		super(sceneName, asBoucle, mouvement, asEnd, boucleInfo, textInfo, audioInfo, asClochette, asBoite, sceneExtends);
		introEndFileSwitch[0] = introEndFile;
		if(Gdx.app.getType().name().equals("Desktop")) {
			introEndFileSwitch[1] = Gdx.files.internal("bin/" + sceneName + "/img1.jpg");
			introEndFileSwitch[2] = Gdx.files.internal("bin/" + sceneName + "/boucle/img-0001.jpg");
			introEndFileSwitch[3] = Gdx.files.internal("bin/" + sceneName + "/boucle2/img-0001.jpg");
			introEndFileSwitch[4] = Gdx.files.internal("bin/" + sceneName + "/img2.jpg");
			introEndFileSwitch[5] = Gdx.files.internal("bin/" + sceneName + "/img3.jpg");
			loadFile("bin/" + sceneName + "/boucle");
			loadFile2("bin/" + sceneName + "/boucle2");
		}else if(Gdx.app.getType().name().equals("Android")) {
			introEndFileSwitch[1] = Gdx.files.absolute(LaPlumeNoire3.data + "/" + sceneName + "/img1.jpg");
			introEndFileSwitch[2] = Gdx.files.absolute(LaPlumeNoire3.data + "/" + sceneName + "/boucle/img-0001.jpg");
			introEndFileSwitch[3] = Gdx.files.absolute(LaPlumeNoire3.data + "/" + sceneName + "/boucle2/img-0001.jpg");
			introEndFileSwitch[4] = Gdx.files.absolute(LaPlumeNoire3.data + "/" + sceneName + "/img2.jpg");
			introEndFileSwitch[5] = Gdx.files.absolute(LaPlumeNoire3.data + "/" + sceneName + "/img3.jpg");
			loadFile(LaPlumeNoire3.data + "/" + sceneName + "/boucle");
			loadFile2(LaPlumeNoire3.data + "/" + sceneName + "/boucle2");
		}
		speMouvement = true;
	}
	
	private void loadFile (String folderPath) {
		FileHandle folder = Gdx.files.absolute(folderPath);
		boucle = new FileHandle[folder.list().length];
		int i=0;
		for (FileHandle file : folder.list()) {
			boucle[i] = file;
			i++;
		}
	}
	
	private void loadFile2 (String folderPath) {
		FileHandle folder = Gdx.files.absolute(folderPath);
		boucle2 = new FileHandle[folder.list().length];
		int i=0;
		for (FileHandle file : folder.list()) {
			boucle2[i] = file;
			i++;
		}
	}
	
	@Override
	public void load () {
		audio = Gdx.audio.newMusic(audioFile);
		introBegin = new Pixmap(introBeginFile);
		textBegin = new Pixmap(textBeginFile);
		introEnd = new Pixmap(introEndFile);
		textEnd = new Pixmap(textEndFile);
		if(!isPause){
			img = new Texture(introBegin);
		}else{
			switchStat = 0;
			Pixmap pixmap = new Pixmap(boucle[0]);
			img = new Texture(pixmap);
			pixmap.dispose();
		}
		imgText = new Texture(textBegin);
		singleDraw = false;
	}
	
	@Override
	public void update () {
		if(isPause){
			unPause();
		}
		
		if(stat == 0){
			if(LaPlumeNoire3.getLanch().isPlaying()){
				if(firstScene){
					if(System.currentTimeMillis() - time >= 20){
						imgApear(true, true);
						time += 20;
					}
				}else{
					if(System.currentTimeMillis() - time >= 10){
						imgApear(false, true);
						time += 10;
					}
				}
			}else{
				introBegin.dispose();
				introEnd.dispose();
				introBegin = new Pixmap(introEndFileSwitch[0]);
				introEnd = new Pixmap(introEndFileSwitch[1]);
				
				stat++;
				LaPlumeNoire3.getLanch().stop();
				preS = new int[55];
				frameText = new int[textInfo.length];
				startTimeText = new float[textInfo.length];
				timeText = System.currentTimeMillis();
				startText = new boolean[textInfo.length];
				endText = new boolean[textInfo.length];
				for(int i=0; i < textInfo.length; i++){
					startText[i] = true;
					endText[i] = false;
				}
				audio.play();
			}
			
		}else if(stat == 1){
			if(switchStat < introEndFileSwitch.length-1){
				if(switchStat == 1){
					if(mouvement != 0 && !mouv){
						mouvementPos = 0;
						mouv = true;
						mouvSt = audio.getPosition();
					}
					if(audio.getPosition() - mouvSt >= 0.05 && mouvementPos >= preMouvementPos){//mouvementPos >= preMouvementPos
						preMouvementPos = mouvementPos;
						mouvementPos = mouvementSave/2 + (int) (mouvementSave * Math.sin((switchScreenTime[0]+2-audio.getPosition())/14-1.55)/2);
						if(mouvementPos < preMouvementPos){
							mouvementPos = preMouvementPos;
							preMouvementPos = mouvementPos+1;
						}
						mouvSt += 0.05;
					}
					
				}else if(switchStat == 2){
					if(System.currentTimeMillis() - time > 33){
						if(frame < boucle.length-1){
							frame++;
						}else{
							frame = 0;
						}
						img.dispose();
						Pixmap pixmap = new Pixmap(boucle[frame]);
						img = new Texture(pixmap);
						pixmap.dispose();
						time += 33;
					}
					
				}else if(switchStat == 3){
					if(System.currentTimeMillis() - time > 33){
						if(frame < boucle2.length-1){
							frame++;
						}else{
							frame = 0;
						}
						img.dispose();
						Pixmap pixmap = new Pixmap(boucle2[frame]);
						img = new Texture(pixmap);
						pixmap.dispose();
						time += 33;
					}
				}else{
					if(!singleDraw){
						img = new Texture(introEndFileSwitch[switchStat]);
						singleDraw = true;
					}
				}
				if(switchScreenTime[switchStat] <= audio.getPosition()){
					singleDraw = false;
					frame = 0;
					stat++;
				}
			}else{
				if(!singleDraw){
					img = new Texture(introEndFileSwitch[switchStat]);
					singleDraw = true;
				}
				
				if(!audio.isPlaying()){
					close(false);
					unload();
					stat = 0;
					switchStat = 0;
					LaPlumeNoire3.ending();
				}
			}
			
			if(System.currentTimeMillis() - timeText >= 40){
				for(int i=0; i < textInfo.length; i++){
					if(isText(i)){
						textApear(i, false);
					}
				}
				timeText += 40;
			}
			
		}else if(stat == 2){
			if(switchScreenTime[switchStat] + 2 > audio.getPosition()){
				if(System.currentTimeMillis() - time >= 20){
					imgEndApear(true);
					time += 20;
				}
			}else{
				switchStat++;
				preS = new int[55];
				img.dispose();
				img = new Texture(introEnd);
				introBegin.dispose();
				introEnd.dispose();
				if(switchStat < introEndFileSwitch.length-1){
					introBegin = new Pixmap(introEndFileSwitch[switchStat]);
					introEnd = new Pixmap(introEndFileSwitch[switchStat+1]);
					if(switchStat == 1){
						mouvementSave = introBegin.getWidth()-742;
					}
				}else{
					introBegin = new Pixmap(introEndFileSwitch[switchStat]);
					introEnd = new Pixmap(introBeginFile);
				}
				stat = 1;
				frame = 0;
				time = System.currentTimeMillis();
			}
			
		}
	}
	
	@Override
	public void imgEndApear (boolean intro) {
		int s = 0;
		if(intro){
			s = 28 - (int)(((2f - (audio.getPosition() - switchScreenTime[switchStat]))/2f) * 28);
		}else{
			s = 29 - (int)(((2f - (audio.getPosition() - switchScreenTime[switchStat] - 2))/2f) * 29);
		}
		if(frame < 6-1){
			frame ++;
		}else{
			frame = 0;
		}
		if(switchStat != 1){
			for(int x=frame; x <= 54; x+=6){
				for(int f=0; f < s-preS[x]; f++){
					if(x%2 == 0){
						for(int y=0; y <= 318/2; y+=8){
							for(int a=y; a < y+8; a++){
								introBegin.drawPixel(((x*14)+s-f) - 14, y+a, introEnd.getPixel(((x*14)+s-f) - 14, y+a));
							}
						}
					}else{
						for(int y=4; y <= 318/2; y+=8){
							for(int a=y; a < y+8; a++){
								introBegin.drawPixel(((x*14)+s-f) - 14, y+a, introEnd.getPixel(((x*14)+s-f) - 14, y+a));
							}
						}
					}
				}
				preS[x] = s;
			}
		}else{
			for(int y=24-frame; y >= 0; y-=6){
				for(int f=0; f < s-preS[y]; f++){
					if(y%2 == 0){
						for(int x=0; x <= 742/2; x+=8){
							for(int a=x; a < x+8; a++){
								introBegin.drawPixel(x+a+mouvementSave, (y*14)-s+f, introEnd.getPixel(x+a, (y*14)-s+f));
							}
						}
					}else{
						for(int x=4; x <= 742/2; x+=8){
							for(int a=x; a < x+8; a++){
								introBegin.drawPixel(x+a+mouvementSave, (y*14)-s+f, introEnd.getPixel(x+a, (y*14)-s+f));
							}
						}
					}
				}
				preS[y] = s;
			}
		}
		img.dispose();
		img = new Texture(introBegin);
	}
	
	@Override
	public boolean isMouvement(){
		if(switchStat == 1){
			return false;
		}else{
			return true;
		}
	}
	
	@Override
	public boolean isXMouvement(){
		return true;
	}
	
	@Override
	public boolean canActiveExtend() {
		return false;
	}
	
	@Override
	public boolean asClochette() {
		return false;
	}

	@Override
	public boolean asBoite() {
		if(!boiteRemoved && audio.getPosition() >= 60+43.4F){
			LaPlumeNoire3.removeBoite();
			boiteRemoved = true;
		}
		if(!boiteRemoved){
			return true;
		}else{
			return false;
		}
	}
}
