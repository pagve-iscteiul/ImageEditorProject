class SimpleEffects {
		
// Noise
	void noise(ColorImage img, int value){
		for(int i = 0; i < img.getWidth(); i++){
			for(int j = 0; j < img.getHeight(); j++){
				Color c = img.getColor(i,j);
				int x = (int)(Math.random()*2);
				if(x == 1){
					int y = (int)(Math.random()*2);
					if(y == 1){
						img.setColor(i,j,c.brighter(value));
					}else{
						img.setColor(i,j,c.brighter(-value));
					}
				}
			}
		}
	}
	
// Contrast
	void contrast(ColorImage img, int n){
		for(int i = 0; i < img.getWidth(); i++){
			for(int j = 0; j < img.getHeight(); j++){
				Color c = img.getColor(i,j);
				if(c.getR() < 128 && c.getG() < 128 && c.getB() < 128){
					img.setColor(i,j,c.brighter(-n));
				}else{ 
					img.setColor(i,j,c.brighter(n));
				}
			}
		}
	}
	
// Vignette
	void vignette(ColorImage img, int distância_min){
		int x = img.getWidth()/2;
		int y = img.getHeight()/2;
		for(int i = 0; i < img.getWidth(); i++){
			for(int j = 0; j < img.getHeight(); j++){
				Color c = img.getColor(i,j);
				int a = (x - i)*(x - i);
				int b = (y - j)*(y - j);
				int distância = (int)(Math.sqrt(a + b));
				if(distância > distância_min){
					int z = (int)(distância - distância_min);
					img.setColor(i,j,c.darker(z));
				}
			}
		}
	}
	
// Sepia
	void sepia(ColorImage img){
		for(int i = 0; i < img.getWidth(); i++){
			for(int j = 0; j < img.getHeight(); j++){
				Color c = img.getColor(i,j);
				int r = c.getR();
				int g = c.getG();
				int b = c.getB();
				double[][] m = { {0.4,0.77,0.2},{0.35,0.69,0.17},{0.27,0.53,0.13} };
				int RED = (Limited((int)(m[0][0]*r + m[0][1]*g + m[0][2]*b)));
				int GREEN = (Limited((int)(m[1][0]*r + m[1][1]*g + m[1][2]*b)));
				int BLUE = (Limited((int)(m[2][0]*r + m[2][1]*g + m[2][2]*b)));
				Color c1 = new Color(RED,GREEN,BLUE);
				img.setColor(i,j,c1);
			}
		}
	}
	
	static int Limited(int n){
		if(n > 255){
			return 255;
		}
		if(n < 0){
			return 0;
		} 
		return n;
	}
	
// Pixelate
	ColorImage pixelate(ColorImage img, int lado){
		ColorImage img1 = new ColorImage(img.getWidth(),img.getHeight());
		for(int i = 0; i < img.getWidth() - lado; i += lado){
		    for(int j = 0; j < img.getHeight() - lado; j += lado){
		    	paint(img1,i,j,lado,Auxiliar(img,i,j,lado));	
		    }
		}
		return img1;
	}
	
	Color Auxiliar(ColorImage img, int x, int y, int lado){
		int rtotal = 0;
    	int gtotal = 0;
    	int btotal = 0;
    	int vizinhos = 0;
    	for(int i = x; i <= x + lado && i < img.getWidth(); i++){
    		for(int j = y; j <= y + lado && j < img.getHeight(); j++){
    			Color c = img.getColor(i,j);
    			rtotal = rtotal + c.getR();
    			gtotal = gtotal + c.getG();
    			btotal = btotal + c.getB();
    			vizinhos++;
    		}
    	}
    	int rmedia = rtotal/vizinhos;
    	int gmedia = gtotal/vizinhos;
    	int bmedia = btotal/vizinhos;
    	Color c1 = new Color(rmedia,gmedia,bmedia);
    	return c1;
    }
	
	void paint(ColorImage img, int x, int y, int lado, Color c){
		for(int i = x; i <= x + lado && i < img.getWidth(); i++){
    		for(int j = y; j <= y + lado && j < img.getHeight(); j++){
    			img.setColor(i,j,c);
    		}
		}
	}
		
// Multiple
	void multiple(ColorImage img){
		ColorImage img2 = reduzir(img);
		for(int i = 0; i < img.getWidth(); i += img.getWidth()/2){
		    for(int j = 0; j < img.getHeight(); j += img.getHeight()/2){
		    	img.paste(img2,i,j);
		    }
		}
	}

	ColorImage reduzir(ColorImage img){
		ColorImage img1 = new ColorImage(img.getWidth(), img.getHeight());
		for(int i = 0; i < img1.getWidth(); i++){
		    for(int j = 0; j < img1.getHeight(); j++){
		    	Color c = img.getColor(i,j);
		    	img1.setColor(i/2,j/2,c);
		    }
		}  
		return img1;
	}
}
		    	