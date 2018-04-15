package Scene;

import SceneExtend.SceneExtend;

import SceneExtend.La_lettre_volee;
import SceneExtend.Dame_blanche;

public class Rear_window extends Scene{
	private static String sceneName = "Rear window";
	private static boolean asBoucle = false;
	private static int mouvement = 0;
	private static boolean asEnd = false;
	private static boolean asClochette = true;
	private static boolean asBoite = true;
	private static SceneExtend[] sceneExtends = {new La_lettre_volee(), new Dame_blanche()};
	private static int[] boucleInfo = {0, 0};
	private static int[][] textInfo = {{1110, 65, 90, 157, 17, 60+34}, {1110, 65, 90, 92, 12, 60+48}, {1110, 66, 90, 26, 4, 60+53}};
	private static float[] audioInfo = {2.2F, 60+58.7F};
	
	public Rear_window () {
		super(sceneName, asBoucle, mouvement, asEnd, boucleInfo, textInfo, audioInfo, asClochette, asBoite, sceneExtends);
	}
}
