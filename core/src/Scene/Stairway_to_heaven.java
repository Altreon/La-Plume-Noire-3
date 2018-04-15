package Scene;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;

import SceneExtend.SceneExtend;
import la.plume.noire3.LaPlumeNoire3;

public class Stairway_to_heaven extends Scene{
	private static String sceneName = "Stairway to heaven";
	private static boolean asBoucle = false;
	private static int mouvement = 0;
	private static boolean asEnd = false;
	private static boolean asClochette = false;
	private static boolean asBoite = false;
	private static SceneExtend[] sceneExtends = {};
	private static int[] boucleInfo = {0, 600};
	private static int[][] textInfo = {{1110, 196, 90, 26, -10, 300+15}};
	private static float[] audioInfo = {2.1F, 300+20.3F};
	
	private int switchStat = 0;
	private FileHandle[] introEndFileSwitch = new FileHandle[3];
	private float[] switchScreenTime = {180+3.2F, 240+49F};
	
	private FileHandle[] monsterBoucle = new FileHandle[2];
	private float monsterAudioTime;
	
	public Stairway_to_heaven () {
		super(sceneName, asBoucle, mouvement, asEnd, boucleInfo, textInfo, audioInfo, asClochette, asBoite, sceneExtends);
		introEndFileSwitch[0] = introEndFile;
		if(Gdx.app.getType().name().equals("Desktop")) {
			introEndFileSwitch[1] = Gdx.files.internal("bin/" + sceneName + "/boucle/img-0001.jpg");
			introEndFileSwitch[2] = Gdx.files.internal("bin/" + sceneName + "/boucleMonstre/img1.jpg");
			monsterBoucle[0] = introEndFileSwitch[2];
			monsterBoucle[1] = Gdx.files.internal("bin/" + sceneName + "/boucleMonstre/img2.jpg");
		}else if(Gdx.app.getType().name().equals("Android")) {
			introEndFileSwitch[1] = Gdx.files.absolute(LaPlumeNoire3.data + "/" + sceneName + "/boucle/img-0001.jpg");
			introEndFileSwitch[2] = Gdx.files.absolute(LaPlumeNoire3.data + "/" + sceneName + "/boucleMonstre/img1.jpg");
			monsterBoucle[0] = introEndFileSwitch[2];
			monsterBoucle[1] = Gdx.files.absolute(LaPlumeNoire3.data + "/" + sceneName + "/boucleMonstre/img2.jpg");
		}
	}
	
	@Override
	public void load () {
		audio = Gdx.audio.newMusic(audioFile);
		introBegin = new Pixmap(introBeginFile);
		textBegin = new Pixmap(textBeginFile);
		if(passAudio){
			introEnd = new Pixmap(introEndFileSwitch[1]);
		}else{
			introEnd = new Pixmap(introEndFile);
		}
		textEnd = new Pixmap(textEndFile);
		if(!isPause){
			img = new Texture(introBegin);
		}else{
			if(passAudio){
				img = new Texture(introEndFile);
			}else{
				switchStat = 0;
				Pixmap pixmap = new Pixmap(boucle[0]);
				img = new Texture(pixmap);
				pixmap.dispose();
			}
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
				if(passAudio){
					introBegin = new Pixmap(introEndFileSwitch[2]);
					introEnd = new Pixmap(introBeginFile);
					monsterAudioTime = audio.getPosition();
				}else{
					introBegin = new Pixmap(introEndFileSwitch[0]);
					introEnd = new Pixmap(introEndFileSwitch[1]);
				}
				
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
				if((passAudio || isAudioFinish) && !LaPlumeNoire3.isLastScenes()){
					audio.setPosition(audioInfo[1]);
				}
			}
			
		}else if(stat == 1){
			if(!audio.isPlaying() && !LaPlumeNoire3.isLastScenes()){
				audio.play();
				audio.setPosition(audioInfo[1]);
			}
			
			if(switchStat < introEndFileSwitch.length-1){
				if(switchStat == 1){
					if(frame < boucleInfo[1]-1 && System.currentTimeMillis() - time > 33){
						frame++;
						img.dispose();
						Pixmap pixmap = new Pixmap(boucle[frame]);
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
					stat++;
				}
			}else{
				if(audio.getPosition() - monsterAudioTime >= 0.5F){
					if(frame < monsterBoucle.length-1){
						frame++;
					}else{
						frame = 0;
					}
					img.dispose();
					Pixmap pixmap = new Pixmap(monsterBoucle[frame]);
					img = new Texture(pixmap);
					pixmap.dispose();
					monsterAudioTime = audio.getPosition();
				}
				if(System.currentTimeMillis() - timeText >= 40){
					for(int i=0; i < textInfo.length; i++){
						if(isText(i)){
							textApear(i, false);
						}
					}
					timeText += 40;
				}
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
					if(switchStat == 1){
						introBegin = new Pixmap(boucle[boucleInfo[1]-1]);
					}else{
						introBegin = new Pixmap(introEndFileSwitch[switchStat]);
					}
					introEnd = new Pixmap(introEndFileSwitch[switchStat+1]);
				}else{
					introBegin = new Pixmap(introEndFileSwitch[switchStat]);
					introEnd = new Pixmap(introBeginFile);
					monsterAudioTime = audio.getPosition();
				}
				stat = 1;
				frame = 0;
				time = System.currentTimeMillis();
			}
			
		}
	}
	
	@Override
	public void setAudio() {
		audio.setPosition(audioInfo[0]);
		monsterAudioTime = audio.getPosition();
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
		img.dispose();
		img = new Texture(introBegin);
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
		return true;
	}
}
