package Scene;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;

import SceneExtend.SceneExtend;
import la.plume.noire3.LaPlumeNoire3;

public class Fleche extends Scene{
	private static String sceneName = "Fleche";
	private static boolean asBoucle = true;
	private static int mouvement = 0;
	private static boolean asEnd = false;
	private static boolean asClochette = false;
	private static boolean asBoite = false;
	private static SceneExtend[] sceneExtends = {};
	private static int[] boucleInfo = {1020, 1039};
	private static int[][] textInfo = {{1110, 196, 90, 26, 24, 60+33}};
	private static float[] audioInfo = {2.05F, 60+38.3F};
	
	private boolean isBoucle = false;
	
	public Fleche () {
		super(sceneName, asBoucle, mouvement, asEnd, boucleInfo, textInfo, audioInfo, asClochette, asBoite, sceneExtends);
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
				introBegin = new Pixmap(boucle[boucleInfo[1]-1]);
				introEnd.dispose();
				introEnd = new Pixmap(introBeginFile);
				
				stat++;
				LaPlumeNoire3.getLanch().stop();
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
			
			if(!isBoucle){
				if(audio.getPosition() >= 31){
					isBoucle = true;
				}
			}
			
			if(isBoucle){
				if(System.currentTimeMillis() - time > 33){
					if(frame < boucleInfo[1]-1){
						frame++;
					}else{
						frame = boucleInfo[0];
					}
					img.dispose();
					Pixmap pixmap = new Pixmap(boucle[frame]);
					img = new Texture(pixmap);
					pixmap.dispose();
					time += 33;
				}
			}else{
				if(!singleDraw){
					img = new Texture(introEndFile);
					singleDraw = true;
				}
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
		return true;
	}
	
	@Override
	public boolean asBoite() {
		return true;
	}
}
