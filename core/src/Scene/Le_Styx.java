package Scene;

import SceneExtend.SceneExtend;

import SceneExtend.L_Acheron;
import SceneExtend.Le_lac_Stymphale;

public class Le_Styx extends Scene{
	private static String sceneName = "Le Styx";
	private static boolean asBoucle = false;
	private static int mouvement = 0;
	private static boolean asEnd = false;
	private static boolean asClochette = true;
	private static boolean asBoite = true;
	private static SceneExtend[] sceneExtends = {new L_Acheron(), new Le_lac_Stymphale()};
	private static int[] boucleInfo = {0, 0};
	private static int[][] textInfo = {{1110, 65, 90, 157, 19, 60+15}, {1110, 65, 90, 92, 21, 60+51}, {1110, 66, 90, 26, 4, 120+10}};
	private static float[] audioInfo = {2.2F, 120+15.6F};
	
	public Le_Styx () {
		super(sceneName, asBoucle, mouvement, asEnd, boucleInfo, textInfo, audioInfo, asClochette, asBoite, sceneExtends);
	}
}
