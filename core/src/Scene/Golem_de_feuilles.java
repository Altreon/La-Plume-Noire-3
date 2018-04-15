package Scene;

import SceneExtend.SceneExtend;

import SceneExtend.Shining;
import SceneExtend.Overlook;

public class Golem_de_feuilles extends Scene{
	private static String sceneName = "Golem de feuilles";
	private static boolean asBoucle = false;
	private static int mouvement = 0;
	private static boolean asEnd = false;
	private static boolean asClochette = true;
	private static boolean asBoite = true;
	private static SceneExtend[] sceneExtends = {new Shining(), new Overlook()};
	private static int[] boucleInfo = {0, 0};
	private static int[][] textInfo = {{1110, 65, 90, 157, 15, 60+57}, {1110, 65, 90, 92, 12, 120+5}, {1110, 66, 90, 26, 4, 120+10}};
	private static float[] audioInfo = {2.15F, 120+17.5F};
	
	public Golem_de_feuilles () {
		super(sceneName, asBoucle, mouvement, asEnd, boucleInfo, textInfo, audioInfo, asClochette, asBoite, sceneExtends);
	}
}
