package Scene;

import SceneExtend.SceneExtend;

import SceneExtend.La_lueur_des_tenebres;
import SceneExtend.Le_chat_du_Cheshire;

public class Clair_obscur extends Scene{
	private static String sceneName = "Clair-obscur";
	private static boolean asBoucle = false;
	private static int mouvement = 0;
	private static boolean asEnd = false;
	private static boolean asClochette = true;
	private static boolean asBoite = true;
	private static SceneExtend[] sceneExtends = {new La_lueur_des_tenebres(), new Le_chat_du_Cheshire()};
	private static int[] boucleInfo = {0, 0};
	private static int[][] textInfo = {{1110, 65, 90, 157, 6, 120+18}, {1110, 65, 90, 92, 9, 120+32}, {1110, 66, 90, 26, 4, 120+48}};
	private static float[] audioInfo = {2.05F, 120+53F};
	
	public Clair_obscur () {
		super(sceneName, asBoucle, mouvement, asEnd, boucleInfo, textInfo, audioInfo, asClochette, asBoite, sceneExtends);
	}
}
