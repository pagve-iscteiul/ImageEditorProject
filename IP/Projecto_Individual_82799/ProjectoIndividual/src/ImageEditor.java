class ImageEditor {

	ColorImage[] history;
	ColorImage img;
	int indice;
	static final int INITIAL_SIZE = 100;
	int Numero_de_Undos;
	SimpleEffects s;
				
	ImageEditor(ColorImage img_recebida){
		img = img_recebida.copy();
		history = new ColorImage[INITIAL_SIZE];
		indice = 0;
		Numero_de_Undos = 0;
		s = new SimpleEffects();
		add(img_recebida);
	}
	
	void noise(int n){
		s.noise(img,n);
		add(img);
	}
	
	void contrast(int n){
		s.contrast(img,n);
		add(img);
	}
	
	void vignette(int n){
		s.vignette(img,n);
		add(img);
	}
	
	void sepia(){
		s.sepia(img);
		add(img);
	}
	
	void pixelate(int n){
		ColorImage img1 = s.pixelate(img,n);
		img = img1.copy();
		add(img);
	}
	
	void multiple(){
		s.multiple(img);
		add(img);
	}
		
	void add(ColorImage img){
		if(history[0] == null){
			history[indice] = img.copy();
		}else{
			indice++;
			history[indice] = img.copy();
		}
	}
	
	void undo(){
		if(indice >= 1){
			indice--;
			img = history[indice].copy();
			Numero_de_Undos++;
		}
	}
	
	void redo(){
		if(Numero_de_Undos > 0){
			indice++;
			img = history[indice].copy();
			Numero_de_Undos--;
		}
	}
	
	void CompositeEffect(CompositeEffects c){
		int[] e = c.getSimpleEffects();
		int[] a = c.getSimpleEffectsArguments();
		for(int i = 0; i < e.length && i < a.length; i++){
			if(e[i] == CompositeEffects.NOISE){
				noise(a[i]);
			}
			if(e[i] == CompositeEffects.CONTRAST){
				contrast(a[i]);
			}
			if(e[i] == CompositeEffects.VIGNETTE){
				vignette(a[i]);
			}
			if(e[i] == CompositeEffects.SEPIA){
				sepia();
			}
			if(e[i] == CompositeEffects.PIXELATE){
				pixelate(a[i]);
			}
			if(e[i] == CompositeEffects.MULTIPLE){
				multiple();
			}
		}
	}
}