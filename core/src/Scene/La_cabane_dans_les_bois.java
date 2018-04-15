package Scene;

import SceneExtend.SceneExtend;

import SceneExtend.Je_vois_de_la_lumiere_noire;
import SceneExtend.Branche_morte;

public class La_cabane_dans_les_bois extends Scene{
	private static String sceneName = "La cabane dans les bois";
	private static boolean asBoucle = false;
	private static int mouvement = 2;
	private static boolean asEnd = false;
	private static boolean asClochette = true;
	private static boolean asBoite = true;
	private static SceneExtend[] sceneExtends = {new Je_vois_de_la_lumiere_noire(), new Branche_morte()};
	private static int[] boucleInfo = {0, 0};
	private static int[][] textInfo = {{1110, 98, 90, 124, 7, 120+8}, {1110, 98, 90, 26, 5, 120+13}};
	private static float[] audioInfo = {2.1F, 120+20.1F};
	
	public La_cabane_dans_les_bois () {
		super(sceneName, asBoucle, mouvement, asEnd, boucleInfo, textInfo, audioInfo, asClochette, asBoite, sceneExtends);
	}
}
