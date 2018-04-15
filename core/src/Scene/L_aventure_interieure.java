package Scene;

import SceneExtend.SceneExtend;

import SceneExtend.Shadowgate;
import SceneExtend.Deus_Ex_Machina;

public class L_aventure_interieure extends Scene{
	private static String sceneName = "L aventure interieure";
	private static boolean asBoucle = false;
	private static int mouvement = 0;
	private static boolean asEnd = false;
	private static boolean asClochette = true;
	private static boolean asBoite = true;
	private static SceneExtend[] sceneExtends = {new Shadowgate(), new Deus_Ex_Machina()};
	private static int[] boucleInfo = {0, 0};
	private static int[][] textInfo = {{1110, 65, 90, 157, -1, 120+54}, {1110, 65, 90, 92, -2, 120+59}, {1110, 66, 90, 26, 1, 180+4}};
	private static float[] audioInfo = {2.2F, 180+25.8F};
	
	public L_aventure_interieure () {
		super(sceneName, asBoucle, mouvement, asEnd, boucleInfo, textInfo, audioInfo, asClochette, asBoite, sceneExtends);
	}
}
