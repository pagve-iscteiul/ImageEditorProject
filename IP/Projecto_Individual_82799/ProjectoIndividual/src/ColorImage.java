/**
 * Represents color images.
 * Image data is represented as a matrix:
 * - the number of lines corresponds to the image height (data.length)
 * - the length of the lines corresponds to the image width (data[*].length)
 * - pixel color is encoded as integers (ARGB)
 */
class ColorImage {

	private int[][] data; // @colorimage

	ColorImage(String file) {
		this.data = ImageUtil.readColorImage(file);
	}

	ColorImage(int width, int height) {
		data = new int[height][width];
	}

	int getWidth() {
		return data[0].length;
	}

	int getHeight() {
		return data.length;
	}

	void setColor(int x, int y, Color c) {
		data[y][x] = ImageUtil.encodeRgb(c.getR(), c.getG(), c.getB());
	}

	Color getColor(int x, int y) {
		int[] rgb = ImageUtil.decodeRgb(data[y][x]);
		return new Color(rgb[0], rgb[1], rgb[2]);
	}
	
	// 2. Transformar a imagem, tornando-a mais clara de acordo com um valor dado. Um valor positivo torna a imagem mais clara, um valor negativo torna a imagem mais escura.
	void brighter(int value){
		for(int i = 0; i < getWidth(); i++){
			for(int j = 0; j < getHeight(); j++){
				Color c = getColor(i,j);
				setColor(i,j,c.brighter(value));
			}
		}
	}
	
	void darker(int value){
		for(int i = 0; i < getWidth(); i++){
			for(int j = 0; j < getHeight(); j++){
				Color c = getColor(i,j);
				setColor(i,j,c.darker(value));
			}
		}
	}
		
	// 6. Devolver uma cópia da imagem.
	ColorImage copy(){
		ColorImage img2 = new ColorImage(getWidth(), getHeight());
		for(int i = 0; i < img2.getWidth(); i++){
			for(int j = 0; j < img2.getHeight(); j++){
				Color c = this.getColor(i,j);
				img2.setColor(i,j,c);
			}
		}
		return img2;
	}
	
	// 4. Modificar uma imagem, colando-lhe outra imagem (paste) em determinado ponto.
		void paste(ColorImage img, int a, int b){
			for(int i = a; i < img.getWidth() && i < getWidth() + a ; i++){
				for(int j = b; j < img.getHeight() && j < getHeight() + b; j++){
					Color c = img.getColor(i - a,j - b);
					this.setColor(i,j,c);
				}
			}
		}
}