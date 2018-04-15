package Scene;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;

import SceneExtend.SceneExtend;
import la.plume.noire3.LaPlumeNoire3;

public class Flesh extends Scene{
	private static String sceneName = "Flesh";
	private static boolean asBoucle = false;
	private static int mouvement = 0;
	private static boolean asEnd = false;
	private static boolean asClochette = false;
	private static boolean asBoite = false;
	private static SceneExtend[] sceneExtends = {};
	private static int[] boucleInfo = {0, 0};
	private static int[][] textInfo = {{1110, 196, 90, 26, 25, 180+10}};
	private static float[] audioInfo = {2.1F, 180+15.9F, 120+14.7F};
	
	private FileHandle introEndFile2;
	private boolean isSwitch = false;
	
	private static boolean clochetteRemoved = false;
	
	public Flesh () {
		super(sceneName, asBoucle, mouvement, asEnd, boucleInfo, textInfo, audioInfo, asClochette, asBoite, sceneExtends);
		if(Gdx.app.getType().name().equals("Desktop")) {
			introEndFile2 = Gdx.files.internal("bin/" + sceneName + "/introEnd2.jpg");
		}else if(Gdx.app.getType().name().equals("Android")) {
			introEndFile2 = Gdx.files.absolute(LaPlumeNoire3.data + "/" + sceneName + "/introEnd2.jpg");
		}
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
				if(!speBoucle){
					introBegin = new Pixmap(introEndFile);
				}else{
					introBegin = new Pixmap(boucle[boucleInfo[1]-1]);
				}
				introEnd.dispose();
				introEnd = new Pixmap(introEndFile2);
				
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
				if(mouvement != 0){
					mouvSt = audio.getPosition();
				}
			}
			
		}else if(stat == 1){
			if(!audio.isPlaying() && !LaPlumeNoire3.isLastScenes()){
				audio.play();
				audio.setPosition(audioInfo[1]);
			}
			
			//DebugMode :
			/*if(Gdx.input.isKeyJustPressed(Keys.ENTER)){
				if(LaPlumeNoire2.isLastScenes()){
					audio.stop();
				}else if(audioInfo[1] > audio.getPosition()){
					audio.setPosition(audioInfo[1]);
					if(asText){
						textBegin.drawPixmap(textEnd, 0, 0);
						for(int i=0; i < endText.length; i++){
							endText[0] = true;
						}
				
						imgText.dispose();
						imgText = new Texture(textBegin);
					}
					if(!passAudio && asItem && !isItem && itemTime <= audio.getPosition()){
						LaPlumeNoire2.addItem(itemName);
						isItem = true;
					}
				}
			}*/
			
			if(!isSwitch){
				if(audio.getPosition() >= audioInfo[2]){
					stat++;
					isSwitch = true;
				}
			}
			
			if(!singleDraw){
				img = new Texture(introEndFile);
				singleDraw = true;
			}
			
			if(asText){
				if(passAudio){
					if(System.currentTimeMillis() - timeText >= 20){
						for(int i=0; i < textInfo.length; i++){
							if(isText(i)){
								textApear(i, false);
							}
						}
						timeText += 20;
					}
				}else{
					if(System.currentTimeMillis() - timeText >= 40){
						for(int i=0; i < textInfo.length; i++){
							if(isText(i)){
								textApear(i, false);
							}
						}
						timeText += 40;
					}
				}
			}
			
		}else if(stat == 2){
			if(audioInfo[2] + 2 > audio.getPosition()){
				if(System.currentTimeMillis() - time >= 20){
					imgEndApear(true);
					time += 20;
				}
			}else{
				preS = new int[55];
				img.dispose();
				img = new Texture(introEnd);
				introBegin.dispose();
				introBegin = new Pixmap(introEndFile2);
				introEnd.dispose();
				introEnd = new Pixmap(introBeginFile);
				stat = 1;
			}
			
		}else if(stat == 4){
			if(LaPlumeNoire3.getLanch().getPosition() < 1.5F){
				if(System.currentTimeMillis() - time >= 10){
					imgApear(false, false);
					if(asText){
						for(int i=0; i < textInfo.length; i++){
							textApear(i, true);
						}
					}
					time += 10;
				}
			}else{
				if(asText){
					textBegin.drawPixmap(textEnd, 0, 0);
					imgText.dispose();
					imgText = new Texture(textBegin);
				}
				stat = 0;
				preS = new int[25];
				preTextS = new int[85];
				firstScene = false;
				if(!LaPlumeNoire3.isLastScenes()){
					passAudio = true;
				}
				isEnd = false;
				LaPlumeNoire3.change();
			}
			
		}
	}
	
	@Override
	public boolean canActiveExtend() {
		return false;
	}
	
	@Override
	public boolean asClochette() {
		if(!clochetteRemoved && audio.getPosition() >= 60+36.8F){
			LaPlumeNoire3.removeClochette();
			clochetteRemoved = true;
		}
		if(!clochetteRemoved){
			return true;
		}else{
			return false;
		}
	}

	@Override
	public boolean asBoite() {
		return true;
	}
}
