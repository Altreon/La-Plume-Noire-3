package Scene;

import SceneExtend.SceneExtend;

import SceneExtend.Silencio;
import SceneExtend.Rope;

public class Sur_un_arbre_perche extends Scene{
	private static String sceneName = "Sur un arbre perche";
	private static boolean asBoucle = false;
	private static int mouvement = 0;
	private static boolean asEnd = false;
	private static boolean asClochette = true;
	private static boolean asBoite = true;
	private static SceneExtend[] sceneExtends = {new Silencio(), new Rope()};
	private static int[] boucleInfo = {0, 0};
	private static int[][] textInfo = {{1110, 65, 90, 157, 8, 46}, {1110, 65, 90, 92, 5, 60+36}, {1110, 66, 90, 26, 4, 60+41}};
	private static float[] audioInfo = {2.15F, 60+50.4F};
	
	public Sur_un_arbre_perche () {
		super(sceneName, asBoucle, mouvement, asEnd, boucleInfo, textInfo, audioInfo, asClochette, asBoite, sceneExtends);
	}
}
