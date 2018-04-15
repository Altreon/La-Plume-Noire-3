package Scene;

import SceneExtend.SceneExtend;

public class Le_cauchemar_aux_yeux_verts extends Scene{
	private static String sceneName = "Le cauchemar aux yeux verts";
	private static boolean asBoucle = true;
	private static int mouvement = 0;
	private static boolean asEnd = false;
	private static boolean asClochette = false;
	private static boolean asBoite = false;
	private static SceneExtend[] sceneExtends = {};
	private static int[] boucleInfo = {0, 90};
	private static int[][] textInfo = {{1110, 98, 90, 124, 18, 120+44}, {1110, 98, 90, 26, 4, 120+49}};
	private static float[] audioInfo = {2.1F, 120+54.8F};
	
	public Le_cauchemar_aux_yeux_verts () {
		super(sceneName, asBoucle, mouvement, asEnd, boucleInfo, textInfo, audioInfo, asClochette, asBoite, sceneExtends);
	}
}
