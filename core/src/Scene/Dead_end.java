package Scene;

import SceneExtend.SceneExtend;
import la.plume.noire3.LaPlumeNoire3;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;

public class Dead_end extends Scene{
	private static String sceneName = "Dead end";
	private static boolean asBoucle = true;
	private static int mouvement = 0;
	private static boolean asEnd = false;
	private static boolean asClochette = false;
	private static boolean asBoite = true;
	private static SceneExtend[] sceneExtends = {};
	private static int[] boucleInfo = {0, 90};
	private static int[][] textInfo = {{1110, 65, 90, 157, 26, 60+12}, {1110, 65, 90, 92, 26, 60+17}, {1110, 66, 90, 26, 26, 60+22}};
	private static float[] audioInfo = {2.15F, 60+27.8F};
	
	public Dead_end () {
		super(sceneName, asBoucle, mouvement, asEnd, boucleInfo, textInfo, audioInfo, asClochette, asBoite, sceneExtends);
	}
	
	@Override
	public void load () {
		audio = Gdx.audio.newMusic(audioFile);
		introBegin = new Pixmap(introBeginFile);
		textBegin = new Pixmap(textBeginFile);
		introEnd = new Pixmap(introEndFile);
		mouvementSave = introEnd.getWidth()-742;
		if(asText){
			textEnd = new Pixmap(textEndFile);
		}
		if(!isPause){
			img = new Texture(introBegin);
		}else{
			if(asBoucle){
				Pixmap pixmap = new Pixmap(boucle[0]);
				img = new Texture(pixmap);
				pixmap.dispose();
			}else{
				img = new Texture(introEndFile);
			}
		}
		imgText = new Texture(textBegin);
		singleDraw = false;
	}
	
	@Override
	public void unload () {
		introBegin.dispose();
		if(asText){
			textBegin.dispose();
			textEnd.dispose();
		}
		introEnd.dispose();
		img.dispose();
		imgText.dispose();
		audio.dispose();
		preMouvementPos = 0; 
		mouvementPos = 0;
		mouvementSave = 0;
		mouv = false;
		mouvSt = 0;
	}
	
	@Override
	public void close(boolean back) {
		if(mouvement == 1){
			preMouvementPos = 0;
			mouvementPos = 0;
		}else if(mouvement == 2){
			preMouvementPos = 0;
			mouvementPos = 0;
		}
		audio.stop();
		isAudioFinish = false;
		textBegin.dispose();
		if(!isEnd && asText){
			textBegin = new Pixmap(textEndFile);
		}else if(asText){
			textBegin = new Pixmap(endEndFile);
		}
		if(asText){
			textEnd.dispose();
			textEnd = new Pixmap(textBeginFile);
		}
		/*if(coin != null){
			LaPlumeNoire2.destroyCoin();
			coinStat = 0;
			isCoin = false;
			coin = null;
		}*/
		if(back){
			stat = 0;
		}else{
			stat = 4;
			if(!LaPlumeNoire3.isLastScenes()){
				passAudio = true;
			}
		}
		time = System.currentTimeMillis();
	}
	
	@Override
	public boolean canActiveExtend() {
		if(stat == 1){
			return true;
		}else{
			return false;
		}
	}
	
	@Override
	public void reniPassAudio(){
		passAudio = false;
	}
	
}
