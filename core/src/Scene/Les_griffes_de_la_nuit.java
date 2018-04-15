package Scene;

import SceneExtend.SceneExtend;

public class Les_griffes_de_la_nuit extends Scene{
	private static String sceneName = "Les griffes de la nuit";
	private static boolean asBoucle = true;
	private static int mouvement = 0;
	private static boolean asEnd = false;
	private static boolean asClochette = false;
	private static boolean asBoite = false;
	private static SceneExtend[] sceneExtends = {};
	private static int[] boucleInfo = {0, 90};
	private static int[][] textInfo = {{1110, 98, 90, 124, 12, 120+23}, {1110, 98, 90, 26, 4, 120+28}};
	private static float[] audioInfo = {2.1F, 120+33F};
	
	public Les_griffes_de_la_nuit () {
		super(sceneName, asBoucle, mouvement, asEnd, boucleInfo, textInfo, audioInfo, asClochette, asBoite, sceneExtends);
	}
}
