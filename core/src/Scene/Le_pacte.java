package Scene;

import SceneExtend.SceneExtend;

import SceneExtend.For_whom_the_bell_tolls;
import SceneExtend.Symphony_of_the_night;

public class Le_pacte extends Scene{
	private static String sceneName = "Le pacte";
	private static boolean asBoucle = false;
	private static int mouvement = 2;
	private static boolean asEnd = false;
	private static boolean asClochette = true;
	private static boolean asBoite = true;
	private static SceneExtend[] sceneExtends = {new For_whom_the_bell_tolls(), new Symphony_of_the_night()};
	private static int[] boucleInfo = {0, 0};
	private static int[][] textInfo = {{1110, 196, 90, 26, 2, 300+49}};
	private static float[] audioInfo = {2.15F, 300+56.6F, 0};
	
	public Le_pacte () {
		super(sceneName, asBoucle, mouvement, asEnd, boucleInfo, textInfo, audioInfo, asClochette, asBoite, sceneExtends);
	}
}
