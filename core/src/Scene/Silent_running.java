package Scene;

import SceneExtend.SceneExtend;

import SceneExtend.Into_the_wild;
import SceneExtend.Harmony_of_dissonance;

public class Silent_running extends Scene{
	private static String sceneName = "Silent running";
	private static boolean asBoucle = false;
	private static int mouvement = 0;
	private static boolean asEnd = false;
	private static boolean asClochette = true;
	private static boolean asBoite = true;
	private static SceneExtend[] sceneExtends = {new Into_the_wild(), new Harmony_of_dissonance()};
	private static int[] boucleInfo = {0, 0};
	private static int[][] textInfo = {{1110, 65, 90, 157, 14, 120+32}, {1110, 65, 90, 92, 12, 120+40}, {1110, 66, 90, 26, 4, 120+45}};
	private static float[] audioInfo = {2.2F, 120+50.3F};
	
	public Silent_running () {
		super(sceneName, asBoucle, mouvement, asEnd, boucleInfo, textInfo, audioInfo, asClochette, asBoite, sceneExtends);
	}
}
