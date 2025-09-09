package Editor;

class CompositeEffects {
	
	public static final int Noise = 4;
	public static final int Contrast = 8;
	public static final int Vignette = 15;
	public static final int Sepia = 84;
	public static final int Blur = 56;
	public static final int Film = 78;
	
	static final int INITIAL_SIZE = 20;
	
	int[] Effects;
	int[] EffectsArguments;
	int indice;
	
	public CompositeEffects(){
		Effects = new int[INITIAL_SIZE];
		EffectsArguments = new int[INITIAL_SIZE];
		indice = 0;
	}
	
	public void add(int n){
		Effects[indice] = n;
		EffectsArguments[indice] = 0;
		indice++;
	}
	
	
	public void add(int n, int v){
		Effects[indice] = n;
		EffectsArguments[indice] = v;
		indice++;
	}
	
	public int[] getSimpleEffects(){
		return Effects;
	}
	
	public int[] getSimpleEffectsArguments(){
		return EffectsArguments;
	}
}