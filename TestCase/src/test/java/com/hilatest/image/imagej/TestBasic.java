
package com.hilatest.image.imagej;

import ij.IJ;
import ij.ImagePlus;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;

import com.hilatest.Constant;

public class TestBasic {

    public final static String gery_path = "src\\main\\resources\\files\\gery.bmp";

    @Test
    public void testBasic() {
        ImagePlus image = IJ.openImage(gery_path);
        // this method will save file with extension defined by format.

        String newFilePath = Constant.OutputPath.getAbsolutePath() + "\\" + RandomStringUtils.randomAlphabetic(10);

        System.out.println("outputfile:" + newFilePath);
        IJ.saveAs(image, "jpeg", newFilePath);
    }
}
