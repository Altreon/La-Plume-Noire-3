package Scene;

import SceneExtend.SceneExtend;

public class Hurlement extends Scene{
	private static String sceneName = "Hurlement";
	private static boolean asBoucle = true;
	private static int mouvement = 0;
	private static boolean asEnd = false;
	private static boolean asClochette = false;
	private static boolean asBoite = false;
	private static SceneExtend[] sceneExtends = {};
	private static int[] boucleInfo = {0, 90};
	private static int[][] textInfo = {{1110, 98, 90, 124, 9, 180+19}, {1110, 98, 90, 26, 4, 180+24}};
	private static float[] audioInfo = {2.15F, 180+29F};
	
	public Hurlement () {
		super(sceneName, asBoucle, mouvement, asEnd, boucleInfo, textInfo, audioInfo, asClochette, asBoite, sceneExtends);
	}
}
