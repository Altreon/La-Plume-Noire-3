package SceneExtend;

public class Le_lac_Stymphale extends SceneExtend{
	private static String sceneName = "Le lac Stymphale";
	private static boolean asBrume = false;
	private static boolean asBoucle = true;
	private static int[] boucleInfo = {0, 210};
	private static boolean textDifferent = false;
	private static int[][] textInfo = {{}};
	private static float[] audioInfo = {2.1F, 120+29.8F};
	
	public Le_lac_Stymphale () {
		super(sceneName, asBrume, asBoucle, boucleInfo, textDifferent, textInfo, audioInfo);
	}
}
