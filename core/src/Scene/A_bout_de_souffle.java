package Scene;

import SceneExtend.SceneExtend;

import SceneExtend.Sword_of_hope;
import SceneExtend.Le_rieur;

public class A_bout_de_souffle extends Scene{
	private static String sceneName = "A bout de souffle";
	private static boolean asBoucle = true;
	private static int mouvement = 0;
	private static boolean asEnd = false;
	private static boolean asClochette = true;
	private static boolean asBoite = true;
	private static SceneExtend[] sceneExtends = {new Sword_of_hope(), new Le_rieur()};
	private static int[] boucleInfo = {450, 750};
	private static int[][] textInfo = {{1110, 65, 90, 157, 22, 120+28}, {1110, 65, 90, 92, 18, 120+35}, {1110, 66, 90, 26, 4, 120+40}};
	private static float[] audioInfo = {2F, 120+46F};
	
	public A_bout_de_souffle () {
		super(sceneName, asBoucle, mouvement, asEnd, boucleInfo, textInfo, audioInfo, asClochette, asBoite, sceneExtends);
	}
}
