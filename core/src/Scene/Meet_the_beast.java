package Scene;

import SceneExtend.SceneExtend;

public class Meet_the_beast extends Scene{
	private static String sceneName = "Meet the beast";
	private static boolean asBoucle = true;
	private static int mouvement = 0;
	private static boolean asEnd = false;
	private static boolean asClochette = false;
	private static boolean asBoite = false;
	private static SceneExtend[] sceneExtends = {};
	private static int[] boucleInfo = {0, 90};
	private static int[][] textInfo = {{1110, 196, 90, 26, -10, 120+9}};
	private static float[] audioInfo = {2.1F, 120+14.8F};
	
	public Meet_the_beast () {
		super(sceneName, asBoucle, mouvement, asEnd, boucleInfo, textInfo, audioInfo, asClochette, asBoite, sceneExtends);
	}
	
	@Override
	public boolean canActiveExtend() {
		return false;
	}
	
	@Override
	public boolean asClochette() {
		return false;
	}

	@Override
	public boolean asBoite() {
		return true;
	}
}
