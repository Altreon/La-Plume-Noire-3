package Scene;

import SceneExtend.SceneExtend;
import la.plume.noire3.LaPlumeNoire3;
import SceneExtend.Ombre_et_lumiere;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Pixmap;

import SceneExtend.Doppelganger;

public class Le_dormeur_du_Val extends Scene{
	private static String sceneName = "Le dormeur du Val";
	private static boolean asBoucle = false;
	private static int mouvement = 0;
	private static boolean asEnd = false;
	private static boolean asClochette = true;
	private static boolean asBoite = true;
	private static SceneExtend[] sceneExtends = {new Ombre_et_lumiere(), new Doppelganger()};
	private static int[] boucleInfo = {0, 0};
	private static int[][] textInfo = {{1110, 196, 90, 26, 3, 120+44}};
	private static float[] audioInfo = {2.1F, 120+51.9F};
	
	private FileHandle textEndFile2;
	private int[][] textInfo2 = {{1110, 98, 90, 124, 3, 0}, {1110, 98, 90, 26, 4, 0}};
	
	public Le_dormeur_du_Val () {
		super(sceneName, asBoucle, mouvement, asEnd, boucleInfo, textInfo, audioInfo, asClochette, asBoite, sceneExtends);
		if(Gdx.app.getType().name().equals("Desktop")) {
			textEndFile2 = Gdx.files.internal("bin/" + sceneName + "/textEnd2.jpg");
		}else if(Gdx.app.getType().name().equals("Android")) {
			textEndFile2 = Gdx.files.absolute(LaPlumeNoire3.data + "/" + sceneName + "/textEnd2.jpg");
		}
	}
	
	@Override
	public void start() {
		if(LaPlumeNoire3.fontaineAsShow()){
			super.asClochette = true;
			super.asBoite = true;
			super.textInfo = textInfo2;
			super.textEndFile = textEndFile2;
			LaPlumeNoire3.changeButton(textInfo2);
			textEnd.dispose();
			textEnd = new Pixmap(textEndFile2);
		}else{
			super.asClochette = false;
			super.asBoite = false;
			LaPlumeNoire3.changeButton(textInfo);
		}
		time = System.currentTimeMillis();
	}
	
	public void close(boolean back) {
		LaPlumeNoire3.desactiveBrume();
		LaPlumeNoire3.removeBrume();
		
		if(mouvement == 1){
			preMouvementPos = 0;
			mouvementPos = 0;
		}else if(mouvement == 2){
			preMouvementPos = 0;
			mouvementPos = 0;
		}
		if(sceneExtendStat == 0){
			audio.stop();
		}else{
			sceneExtends[sceneExtendStat-1].getAudio().stop();
		}
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
		if(back){
			stat = 0;
		}else{
			stat = 4;
			if(!LaPlumeNoire3.isLastScenes()){
				if(sceneExtendStat == 0){
					passAudio = true;
				}else{
					sceneExtends[sceneExtendStat-1].passAudio();
				}
			}
			if(sceneExtendStat != 0){
				introBegin.dispose();
				introBegin = new Pixmap(sceneExtends[sceneExtendStat-1].getIntroEnd());
				if(sceneExtends[sceneExtendStat-1].isTextDifferent()){
					textBegin.dispose();
					textBegin = new Pixmap(sceneExtends[sceneExtendStat-1].getTextEnd());
				}
			}
		}
		if(asClochette){
			sceneExtends[0].close();
		}
		if(asBoite){
			sceneExtends[1].close();
		}
		boiteShow = false;
		clochetteShow = false;
		sceneExtendStat = 0;
		asClochette = true;
		asBoite = true;
		time = System.currentTimeMillis();
	}
}
