class CompositeEffects {

	public static final int NOISE = 1;
	public static final int CONTRAST = 2;
	public static final int VIGNETTE = 3;
	public static final int SEPIA = 4;
	public static final int PIXELATE = 5;
	public static final int MULTIPLE = 6;
	
	int[] Effects;
	int[] EffectsArguments;
	static final int INITIAL_SIZE = 100;
	int indice;
	
	CompositeEffects(){
		Effects = new int[INITIAL_SIZE];
		EffectsArguments = new int[INITIAL_SIZE];
		indice = 0;
	}
	
	void add(int e, int a){
		Effects[indice] = e;
		EffectsArguments[indice] = a;
		indice++;
	}
			
	void add(int e){
		Effects[indice] = e;
		indice++;
	}
	
	 int[] getSimpleEffects(){
		return Effects;
	 }
	 
	 int[] getSimpleEffectsArguments(){
		 return EffectsArguments;
	 }		
}