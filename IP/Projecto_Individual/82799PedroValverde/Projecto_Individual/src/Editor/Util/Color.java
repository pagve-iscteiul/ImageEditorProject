package Editor.Util;

/**
 * Represents RGB colors.
 * RGB values are stored in a 3-position array, with values in the interval [0, 255].
 * rgb[0] - Red
 * rgb[1] - Green
 * rgb[2] - Blue
 */
public class Color {
	static final int MAX_RGB = 255;
	private final int[] rgb; // @color

	/**
	 * Creates an RGB color. Provided values have to 
	 * be in the interval [0, 255]
	 */
	public Color(int r, int g, int b) {
		if(!valid(r) || !valid(g) || !valid(b))
			throw new IllegalArgumentException("invalid RGB values: " + r + ", " + g + ", " + b);
		
		this.rgb = new int[] {r, g, b};
	}

	/**
	 * Red value [0, 255]
	 */
	public int getR() {
		return rgb[0];
	}

	/**
	 * Green value [0, 255]
	 */
	public int getG() {
		return rgb[1];
	}

	/**
	 * Blue value [0, 255]
	 */
	public int getB() {
		return rgb[2];
	}

	/**
	 * Obtains the luminance in the interval [0, 255].
	 */
	public int getLuminance() {
		return (int) Math.round(rgb[0]*.21 + rgb[1]*.71 + rgb[2]*.08);
	}

	public Color inverse() {
		return new Color(255-rgb[0],255-rgb[1], 255-rgb[2]); 
	}
	
	
	public static boolean valid(int value) {
		return value >= 0 && value <= 255;
	}
	
	public Color brighter(int value) {
		return new Color(limit_to_interval(getR() + value),
						limit_to_interval(getG() + value),
						limit_to_interval(getB() + value));
	}
	
	public Color darker(int value){
		return new Color(limit_to_interval(getR() - value),
						limit_to_interval(getG() - value),
						limit_to_interval(getB() - value));
	}
						
	public static int limit_to_interval(int v) {
		if(v > MAX_RGB)
			return MAX_RGB;
		else
			if(v < 0)
				return 0;
			else 
				return v;
	}
		
}
