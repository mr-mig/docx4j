package org.docx4j.model.properties;

import org.docx4j.UnitsOfMeasurement;
import org.docx4j.org.xhtmlrenderer.css.parser.FSColor;
import org.docx4j.org.xhtmlrenderer.css.parser.FSRGBColor;
import org.docx4j.org.xhtmlrenderer.css.parser.PropertyValue;
import org.w3c.dom.css.CSSPrimitiveValue;
import org.w3c.dom.css.CSSValue;

/**
 * The class contains static methods for property manipulation
 */
public class PropertyUtils {

    public static String convertCssValueToHEXColor(CSSValue value){
        float fRed;
        float fGreen;
        float fBlue;

        // PrimitiveType 25 -> RGBCOLOR
        short ignored = 1;

        CSSPrimitiveValue cssPrimitiveValue = (CSSPrimitiveValue)value;
        try {
            fRed = cssPrimitiveValue.getRGBColorValue().getRed().getFloatValue(ignored);
            fGreen = cssPrimitiveValue.getRGBColorValue().getGreen().getFloatValue(ignored);
            fBlue = cssPrimitiveValue.getRGBColorValue().getBlue().getFloatValue(ignored);
        } catch (UnsupportedOperationException e) {
            if (!(cssPrimitiveValue instanceof PropertyValue)) throw e;
            final FSColor fsColor = ((PropertyValue) cssPrimitiveValue).getFSColor();
            if (!(fsColor instanceof FSRGBColor)) throw e;
            fRed = ((FSRGBColor) fsColor).getRed();
            fGreen = ((FSRGBColor) fsColor).getGreen();
            fBlue = ((FSRGBColor) fsColor).getBlue();
        }

        return UnitsOfMeasurement.rgbTripleToHex(fRed, fGreen, fBlue);
    }

}
