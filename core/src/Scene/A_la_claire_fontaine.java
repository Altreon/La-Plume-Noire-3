package Scene;

import SceneExtend.SceneExtend;

import SceneExtend.Au_clair_de_la_lune;
import SceneExtend.La_memoire_de_l_eau;

public class A_la_claire_fontaine extends Scene{
	private static String sceneName = "A la claire fontaine";
	private static boolean asBoucle = false;
	private static int mouvement = 0;
	private static boolean asEnd = false;
	private static boolean asClochette = true;
	private static boolean asBoite = true;
	private static SceneExtend[] sceneExtends = {new Au_clair_de_la_lune(), new La_memoire_de_l_eau()};
	private static int[] boucleInfo = {0, 0};
	private static int[][] textInfo = {{1110, 65, 90, 157, 5, 120+24}, {1110, 65, 90, 92, 12, 120+14}, {1110, 66, 90, 26, 1, 120+34}};
	private static float[] audioInfo = {2F, 120+39.6F, 0};
	
	public A_la_claire_fontaine () {
		super(sceneName, asBoucle, mouvement, asEnd, boucleInfo, textInfo, audioInfo, asClochette, asBoite, sceneExtends);
	}
}
