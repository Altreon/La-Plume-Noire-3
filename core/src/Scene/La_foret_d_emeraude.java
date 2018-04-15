package Scene;

import SceneExtend.SceneExtend;
import la.plume.noire3.LaPlumeNoire3;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Pixmap;

import SceneExtend.Les_paroles_s_envolent_les_ecrits_restent;
import SceneExtend.Say_my_name;

public class La_foret_d_emeraude extends Scene{
	private static String sceneName = "La foret d emeraude";
	private static boolean asBoucle = false;
	private static int mouvement = 0;
	private static boolean asEnd = false;
	private static boolean asClochette = true;
	private static boolean asBoite = true;
	private static SceneExtend[] sceneExtends = {new Les_paroles_s_envolent_les_ecrits_restent(), new Say_my_name()};
	private static int[] boucleInfo = {0, 0};
	private static int[][] textInfo = {{1110, 98, 90, 124, 3, 120+25}, {1110, 98, 90, 26, 2, 120+36}};
	private static float[] audioInfo = {1.1F, 120+42.4F};
	
	private FileHandle textEndFile2;
	private int[][] textInfo2 = {{1110, 65, 90, 157, 3, 0}, {1110, 65, 90, 92, 2, 0}, {1110, 66, 90, 26, 4, 0}};
	
	public La_foret_d_emeraude () {
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
			super.textInfo = textInfo2;
			super.textEndFile = textEndFile2;
			LaPlumeNoire3.changeButton(textInfo2);
			textEnd.dispose();
			textEnd = new Pixmap(textEndFile2);
		}
		time = System.currentTimeMillis();
	}
}
