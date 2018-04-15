package SceneExtend;

public class La_memoire_de_l_eau extends SceneExtend{
	private static String sceneName = "La memoire de l eau";
	private static boolean asBrume = true;
	private static boolean asBoucle = false;
	private static int[] boucleInfo = {0, 0};
	private static boolean textDifferent = true;
	private static int[][] textInfo = {{1110, 65, 90, 157, 18, 120+16}, {1110, 65, 90, 92, 5, 120+33}, {1110, 66, 90, 26, 12, 120+38}};
	//private static int[][] textInfo = {{1110, 65, 90, 157, 18, 0}, {1110, 65, 90, 92, 5, 10}, {1110, 66, 90, 26, 12, 20}};
	private static float[] audioInfo = {2.2F, 120+46.7F};
	
	public La_memoire_de_l_eau () {
		super(sceneName, asBrume, asBoucle, boucleInfo, textDifferent, textInfo, audioInfo);
	}
}
