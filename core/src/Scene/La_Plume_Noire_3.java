package Scene;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;

import SceneExtend.SceneExtend;
import la.plume.noire3.LaPlumeNoire3;

public class La_Plume_Noire_3 extends Scene{
	private static String sceneName = "La Plume Noire 3";
	private static boolean asBoucle = false;
	private static int mouvement = 0;
	private static boolean asEnd = false;
	private static boolean asClochette = false;
	private static boolean asBoite = false;
	private static SceneExtend[] sceneExtends = {null, null};
	private static int[] boucleInfo = {0, 0};
	private static int[][] textInfo = {{1110, 196, 90, 26, 1, 10}};
	private static float[] audioInfo = {0.2F, 24.8F};
		
	public La_Plume_Noire_3 () {
		super(sceneName, asBoucle, mouvement, asEnd, boucleInfo, textInfo, audioInfo, asClochette, asBoite, sceneExtends);
		if(Gdx.app.getType().name().equals("Desktop")) {
			introBeginFile = Gdx.files.internal("bin/" + sceneName + "/introEnd.jpg");
			textBeginFile = Gdx.files.internal("bin/" + sceneName + "/textBeginFull.jpg");
		}else if(Gdx.app.getType().name().equals("Android")) {
			introBeginFile = Gdx.files.absolute(LaPlumeNoire3.data + "/" + sceneName + "/introEnd.jpg");
			textBeginFile = Gdx.files.absolute(LaPlumeNoire3.data + "/" + sceneName + "/textBeginFull.jpg");
		}
	}
	
	@Override
	public void update () {
		if(isPause){
			unPause();
		}
		
		if(stat == 0){
			introBegin.dispose();
			introBegin = new Pixmap(introEndFile);
			introEnd.dispose();
			introEnd = new Pixmap(introBeginFile);
			
			stat++;
			preS = new int[25];
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
			
			if(asText){
				if(System.currentTimeMillis() - timeText >= 40){
					for(int i=0; i < textInfo.length; i++){
						if(isText(i)){
							textApear(i, false);
						}
					}
					timeText += 40;
				}
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
}
