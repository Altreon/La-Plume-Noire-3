package SceneExtend;

public class Le_caveau_de_verre extends SceneExtend{
	private static String sceneName = "Le caveau de verre";
	private static boolean asBrume = false;
	private static boolean asBoucle = false;
	private static int[] boucleInfo = {0, 0};
	private static boolean textDifferent = false;
	private static int[][] textInfo = {{}};
	private static float[] audioInfo = {2.1F, 60+27F};
	
	public Le_caveau_de_verre () {
		super(sceneName, asBrume, asBoucle, boucleInfo, textDifferent, textInfo, audioInfo);
	}
}
