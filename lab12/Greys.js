function process(imgage) {
	
var height = image.getHeight();
var width = image.getWidth();

    for (var y = 0; y < width; y++) {
        for (var x = 0; x < height; x++) {
            var p =  new java.awt.Color(img.getRGB(x,y));

            var a = (p>>24)&0xff;
            var r = (p>>16)&0xff;
            var g = (p>>8)&0xff;
            var b = p&0xff;

            //calculate average
            var avg = (r+g+b)/3;

            //replace RGB value with avg
            p = (a<<24) | (avg<<16) | (avg<<8) | avg;

            new java.awt.Color( img.setRGB(x, y, p));
        }
    }

    return imgage;
};
