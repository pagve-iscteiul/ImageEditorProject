package Editor;
import Editor.Util.*;

class Test {
	static void testImageEditor(){
		ColorImage img = new ColorImage("objc1.png");
		ImageEditor e = new ImageEditor(img); 
		//e.noise(50);
		//e.contrast(50);
		//e.vignette(10);
		//e.sepia();
		//e.blur(2);
		//e.film();
		//e.film(60);
		//e.historyUndo();
		//e.historyRedo();
		//CompositeEffects n = new CompositeEffects();
		//n.add(CompositeEffects.Noise, 50);
		//n.add(CompositeEffects.Contrast, 50);
		//n.add(CompositeEffects.Vignette,10);
		//n.add(CompositeEffects.Sepia);
		//n.add(CompositeEffects.Blur,2);
		//n.add(CompositeEffects.Film);
		//e.composeEffect(n);
	}
}