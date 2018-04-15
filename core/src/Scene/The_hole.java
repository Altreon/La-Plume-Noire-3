package Scene;

import SceneExtend.SceneExtend;

import SceneExtend.Dying_light;
import SceneExtend.Items;

public class The_hole extends Scene{
	private static String sceneName = "The hole";
	private static boolean asBoucle = false;
	private static int mouvement = 0;
	private static boolean asEnd = false;
	private static boolean asClochette = true;
	private static boolean asBoite = true;
	private static SceneExtend[] sceneExtends = {new Dying_light(), new Items()};
	private static int[] boucleInfo = {0, 0};
	private static int[][] textInfo = {{1110, 65, 90, 157, 11, 60+42}, {1110, 65, 90, 92, 5, 60+49}, {1110, 66, 90, 26, 4, 60+54}};
	private static float[] audioInfo = {2.15F, 60+59.1F};
	
	public The_hole () {
		super(sceneName, asBoucle, mouvement, asEnd, boucleInfo, textInfo, audioInfo, asClochette, asBoite, sceneExtends);
	}
}
