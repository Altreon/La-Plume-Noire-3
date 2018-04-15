package Scene;

import SceneExtend.SceneExtend;

public class Wolfen extends Scene{
	private static String sceneName = "Wolfen";
	private static boolean asBoucle = true;
	private static int mouvement = 0;
	private static boolean asEnd = false;
	private static boolean asClochette = false;
	private static boolean asBoite = false;
	private static SceneExtend[] sceneExtends = {};
	private static int[] boucleInfo = {0, 90};
	private static int[][] textInfo = {{1110, 196, 90, 26, 4, 180+05}};
	private static float[] audioInfo = {2.1F, 180+24.1F};
	
	public Wolfen () {
		super(sceneName, asBoucle, mouvement, asEnd, boucleInfo, textInfo, audioInfo, asClochette, asBoite, sceneExtends);
	}
}
