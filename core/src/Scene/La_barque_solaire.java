package Scene;

import SceneExtend.SceneExtend;

import SceneExtend.Seeman;
import SceneExtend.Mirai;

public class La_barque_solaire extends Scene{
	private static String sceneName = "La barque solaire";
	private static boolean asBoucle = false;
	private static int mouvement = 0;
	private static boolean asEnd = false;
	private static boolean asClochette = true;
	private static boolean asBoite = true;
	private static SceneExtend[] sceneExtends = {new Seeman(), new Mirai()};
	private static int[] boucleInfo = {0, 0};
	private static int[][] textInfo = {{1110, 65, 90, 157, 20, 50}, {1110, 65, 90, 92, 18, 60+10}, {1110, 66, 90, 26, 4, 60+17}};
	private static float[] audioInfo = {2.1F, 60+28.1F};
	
	public La_barque_solaire () {
		super(sceneName, asBoucle, mouvement, asEnd, boucleInfo, textInfo, audioInfo, asClochette, asBoite, sceneExtends);
	}
}
