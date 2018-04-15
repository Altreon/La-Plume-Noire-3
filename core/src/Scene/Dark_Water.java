package Scene;

import SceneExtend.SceneExtend;

public class Dark_Water extends Scene{
	private static String sceneName = "Dark Water";
	private static boolean asBoucle = true;
	private static int mouvement = 0;
	private static boolean asEnd = false;
	private static boolean asClochette = false;
	private static boolean asBoite = false;
	private static SceneExtend[] sceneExtends = {};
	private static int[] boucleInfo = {0, 90};
	private static int[][] textInfo = {{1110, 98, 90, 124, 18, 120+20}, {1110, 98, 90, 26, 4, 120+25}};
	private static float[] audioInfo = {2.2F, 120+30.1F};
	
	public Dark_Water () {
		super(sceneName, asBoucle, mouvement, asEnd, boucleInfo, textInfo, audioInfo, asClochette, asBoite, sceneExtends);
	}
}
