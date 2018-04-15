package Scene;

import SceneExtend.SceneExtend;

import SceneExtend.Light_my_fire;
import SceneExtend.You_are_not_alone;

public class House extends Scene{
	private static String sceneName = "House";
	private static boolean asBoucle = false;
	private static int mouvement = 0;
	private static boolean asEnd = false;
	private static boolean asClochette = true;
	private static boolean asBoite = true;
	private static SceneExtend[] sceneExtends = {new Light_my_fire(), new You_are_not_alone()};
	private static int[] boucleInfo = {0, 0};
	private static int[][] textInfo = {{1110, 65, 90, 157, 13, 60+16}, {1110, 65, 90, 92, 16, 60+31}, {1110, 66, 90, 26, 4, 60+36}};
	private static float[] audioInfo = {2.1F, 60+41.8F};
	
	public House () {
		super(sceneName, asBoucle, mouvement, asEnd, boucleInfo, textInfo, audioInfo, asClochette, asBoite, sceneExtends);
	}
}
