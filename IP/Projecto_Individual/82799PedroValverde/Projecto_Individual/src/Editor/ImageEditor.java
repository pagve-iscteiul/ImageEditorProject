package Editor;
import Editor.Util.*;

class ImageEditor {
	
	// Atributor que tem uma cópia da imagem que 
	// está a ser editada
	private ColorImage img;
	private ColorImage[] history; 
	private int indice;
	private int numero_de_Undos;
	static final int INITIAL_SIZE = 20;
	SimpleEffects s;
	
	
	
	public ImageEditor(ColorImage img_recebida){	
		history  = new ColorImage[INITIAL_SIZE];
		s = new SimpleEffects();
		img = img_recebida.copy();
		indice  = 0;
		numero_de_Undos = 0;
		addToHistory(img_recebida);
		
	}	
	
	// Noise
	public void noise(int n){
		s.noise(img,n);
		addToHistory(img);
		}
	
	// Contrast	
	public void contrast(int n){
		s.contrast(img,n);
		addToHistory(img);
	}
			
	// Vignette
	public void vignette(int dist){
		s.vignette(img,dist);
		addToHistory(img);
		}
	
	// Sepia
	public void sepia(){
		s.sepia(img);
		addToHistory(img);
		}
	
	// Blur
	public void blur(int r){
		ColorImage a = s.blur(img,r);
		this.img = a;
		addToHistory(img);
	}
	
	// Film
	public void film(){
		s.film(img);
		addToHistory(img);
		}
			
		
	
	public void film(int width){
		s.film(img,width);
		addToHistory(img);
	}

	public void addToHistory(ColorImage c){
		if (history[0]== null){
			history[indice] = c.copy();
		}else{
			indice++;
			history[indice] = c.copy();
		}
	}

	public void historyUndo(){
		if(indice >= 1){
			indice--;
			this.img = history[indice].copy();
			numero_de_Undos++;
		}
	}
	
	public void historyRedo(){
		if(numero_de_Undos > 0){
			indice++;
			this.img = history[indice].copy();	
		}
	}
	
	public void composeEffect(CompositeEffects c){
		int[] e = c.getSimpleEffects();
		int[] a = c.getSimpleEffectsArguments();
		for (int i = 0; e[i] != 0;i++){
			if (e[i] == CompositeEffects.Noise){
				noise(a[i]);
			}
			if (e[i] == CompositeEffects.Contrast){
				contrast(a[i]);
			}
			if (e[i] == CompositeEffects.Vignette){
				vignette(a[i]);
			}
			if (e[i] == CompositeEffects.Sepia){
				sepia();
			}
			if (e[i] == CompositeEffects.Blur){
				blur(a[i]);
			}
			if (e[i] == CompositeEffects.Film){
				if (a[i] == 0){
					film();
				}else{
					film(a[i]);
				}
			}	
		}
	}
}
	
		
		