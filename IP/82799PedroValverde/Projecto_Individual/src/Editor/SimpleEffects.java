package Editor;

import Editor.Util.Color;
import Editor.Util.ColorImage;

class SimpleEffects {
	
	// Noise
	public void noise(ColorImage img, int n){
		for(int i = 0; i < img.getWidth(); i++)
			for(int j = 0; j < img.getHeight(); j++){
				int x = (int) (Math.random()*2); // int para ficar 0 e 1
				if(x == 1){
					int y = (int) (Math.random()*2);
					Color color = img.getColor(i,j); // a cor de cada pixel
					if(y == 1){
						img.setColor(i,j, color.brighter(n));
					}else{
						img.setColor(i,j, color.brighter(-n));
					}
				}
			}
		}
					
	
	// Contrast	
	public void contrast(ColorImage img, int n){
		for(int i = 0; i < img.getWidth(); i++)
			for(int j = 0; j < img.getHeight(); j++){
				Color c = img.getColor(i,j);
				if(c.getR() < 128 && c.getG() < 128 && c.getB() < 128){
					img.setColor(i,j, c.brighter(-n));
				}else{
					img.setColor(i,j, c.brighter(n));
				}
			}
		}
			
	// Vignette
	public void vignette(ColorImage img, int dist){
		int a = img.getWidth()/2;
		int b = img.getHeight()/2;
		for(int i = 0; i < img.getWidth(); i++)
			for(int j = 0; j < img.getHeight(); j++){
				int x = (a - i)*(a - i);
				int y = (b - j)*(b - j);
				int distPixel = (int) (Math.sqrt(x + y));
				Color c = img.getColor(i,j);
				if(distPixel > dist){ // distancia do pixel ao centro
					int d = (int) (distPixel - dist);
					img.setColor(i,j, c.darker(d));
				}
			}
		}
	
	// Sepia
	public void sepia(ColorImage img){
		for(int i = 0; i < img.getWidth(); i++)
			for(int j = 0; j < img.getHeight(); j++){
				Color c = img.getColor(i,j);
				double[][] m = {{0.4,0.77,0.2},{0.35,0.69,0.17},{0.27,0.53,0.13}};
				int r = (int) (Math.round(c.getR()*m[0][0] + c.getG()*m[0][1] + c.getB()*m[0][2]));
				int g = (int) (Math.round(c.getR()*m[1][0] + c.getG()*m[1][1] + c.getB()*m[1][2]));
				int b = (int) (Math.round(c.getR()*m[2][0] + c.getG()*m[2][1] + c.getB()*m[2][2]));
				if(r > 255){
					r = 255;
				}
				if(g > 255){
					g = 255;
				}
				if(b > 255){
					b = 255;
				}
				Color c1 = new Color(r,g,b);
				img.setColor( i, j, c1);
			}
		}
	
	// Blur
	public ColorImage blur(ColorImage img, int r){
		ColorImage a = new ColorImage(img.getWidth(), img.getHeight());
		for(int i = 0; i < a.getWidth(); i++)
			for(int j = 0; j < a.getHeight(); j++){
				int rtotal = 0;
				int gtotal = 0;
				int btotal = 0;
				int neighbors = 0;
				for(int g = (i - r); g <= (i + r) &&  g >= (i - r); g++){
					for(int h = (j - r); h <= (j + r) && h >= (j - r); h++){
						if(g >= 0 && h >= 0 && g < img.getWidth() && h < img.getHeight() && g != i && h != j){
							Color c = img.getColor(g,h);
							neighbors++;
							rtotal = rtotal + c.getR();
							gtotal = gtotal + c.getG();
							btotal = btotal + c.getB();
						}
					}
				}
			int rmedia = rtotal/neighbors;
			int gmedia = gtotal/neighbors;
			int bmedia = btotal/neighbors;
			Color c1 = new Color(rmedia, gmedia, bmedia);
			a.setColor( i, j, c1);
			}
		return a;
	}
	
	// Film
	public void film(ColorImage img){
		for(int i = 0; i < img.getWidth(); i++)
			for(int j = 0; j < img.getHeight(); j++){
				int width = img.getWidth()/4;
				int height = img.getHeight()/9;
				int widthSquare = width/4;
				Color black = new Color(0,0,0);
				Color white = new Color(255,255,255);
				if(i <= width || i >= (img.getWidth() - width)){
					img.setColor(i,j,black);
				}	
				if (i >= widthSquare && i <= width - widthSquare || i >= (img.getWidth() - width + widthSquare) && i <= (img.getWidth() - widthSquare)){
					if(j >= height && j <= height*2){
						img.setColor(i,j,white);
						}
					if(j >= height*3 && j <= height*4){
						img.setColor(i,j,white);
						}
					if(j >= height*5 && j <= height*6){
						img.setColor(i,j,white);
						}
					if(j >= height*7&& j <= (height*8)){
						img.setColor(i,j,white);
						}
				}				
			}
		}
			
	public void film(ColorImage img, int width){
		for(int i = 0; i < img.getWidth(); i++)
			for(int j = 0; j < img.getHeight(); j++){
				Color black = new Color(0,0,0);
				Color white = new Color(255,255,255);
				int width1 = width/2;
				int height = img.getHeight()/9;
				int widthSquare = width1/4;
				if(i <= width || i >= (img.getWidth() - width)){
					img.setColor(i,j,black);
				}	
				if (i >= widthSquare && i <= width - widthSquare || i >= (img.getWidth() - width + widthSquare) && i <= (img.getWidth() - widthSquare)){
					if(j >= height && j <= height*2){
						img.setColor(i,j,white);
						}
					if(j >= height*3 && j <= height*4){
						img.setColor(i,j,white);
						}
					if(j >= height*5 && j <= height*6){
						img.setColor(i,j,white);
						}
					if(j >= height*7&& j <= (height*8)){
						img.setColor(i,j,white);
						}
				}				
			}
		}
	}