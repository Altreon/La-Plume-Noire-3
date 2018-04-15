package SceneExtend;

public class Deus_Ex_Machina extends SceneExtend{
	private static String sceneName = "Deus Ex Machina";
	private static boolean asBrume = true;
	private static boolean asBoucle = false;
	private static int[] boucleInfo = {0, 0};
	private static boolean textDifferent = true;
	private static int[][] textInfo = {{1110, 98, 90, 124, 12, 60+40}, {1110, 98, 90, 26, 4, 60+45}};
	private static float[] audioInfo = {2.1F, 60+50.5F};
	
	public Deus_Ex_Machina () {
		super(sceneName, asBrume, asBoucle, boucleInfo, textDifferent, textInfo, audioInfo);
	}
}
