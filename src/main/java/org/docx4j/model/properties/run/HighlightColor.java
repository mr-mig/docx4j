/*
 *  Copyright 2009, Plutext Pty Ltd.
 *   
 *  This file is part of docx4j.

    docx4j is licensed under the Apache License, Version 2.0 (the "License"); 
    you may not use this file except in compliance with the License. 

    You may obtain a copy of the License at 

        http://www.apache.org/licenses/LICENSE-2.0 

    Unless required by applicable law or agreed to in writing, software 
    distributed under the License is distributed on an "AS IS" BASIS, 
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. 
    See the License for the specific language governing permissions and 
    limitations under the License.

 */
package org.docx4j.model.properties.run;

import org.docx4j.jaxb.Context;
import org.docx4j.wml.Highlight;
import org.docx4j.wml.RPr;
import org.w3c.dom.Element;
import org.w3c.dom.css.CSSValue;

import java.util.HashMap;

public class HighlightColor extends AbstractRunProperty {
	
	public final static String CSS_NAME = "background-color"; 
	public final static String FO_NAME  = "background-color";
    private final static HashMap<String,String> COLOR_MAP = new HashMap<String, String>(){{
        this.put("#000000","black");
        this.put("#0000ff","blue");
        this.put("#00ffff","cyan");
        this.put("#008000","green");
        this.put("#ff00ff","magenta");
        this.put("#ff0000","red");
        this.put("#ffff00","yellow");
        this.put("#ffffff","white");
        this.put("#00008b","darkBlue");
        this.put("#008b8b","darkCyan");
        this.put("#006400","darkGreen");
        this.put("#8b008b","darkMagenta");
        this.put("#8b0000","darkRed");
        this.put("#808000","darkYellow");
        this.put("#a9a9a9","darkGray");
        this.put("#d3d3d3","lightGray");
    }};
	
	/**
	 * @since 2.7.2
	 */	
	public String getCssName() {
		return CSS_NAME;
	}
	
	/*
	 * TODO: this class assumes that the
	 * colors specified in org.docx4j.wml.Highlight
	 * are valid CSS and XSL colors, and
	 * vice versa.
	 * 
	 * Someone needs to add handling/mappings for when
	 * this is not the case.
	 */
	
	public HighlightColor(Highlight shading) {
		this.setObject(shading);
	}
	
	public HighlightColor(CSSValue value) {	
		
		Highlight shd = Context.getWmlObjectFactory().createHighlight();
        String shadeColor = value.getCssText();
        if (COLOR_MAP.containsKey(value.getCssText())){
            shadeColor = COLOR_MAP.get(value.getCssText());
        }
		shd.setVal(shadeColor);
		this.setObject( shd  );
	}

	@Override
	public String getCssProperty() {
		
		Highlight shd = ((Highlight)this.getObject());
		
		if (shd.getVal()!=null &&
		!shd.getVal().equals("none")) {
			return composeCss(CSS_NAME, shd.getVal() );
		} else {
			return CSS_NULL;
		}
	}


	@Override
	public void setXslFO(Element foElement) {

		Highlight shd = ((Highlight)this.getObject());
		
		if (shd.getVal()!=null &&
				!shd.getVal().equals("none")) {
			foElement.setAttribute(FO_NAME, shd.getVal() );
		} 
		
	}

	@Override
	public void set(RPr rPr) {
		rPr.setHighlight((Highlight)this.getObject() );		
	}
	
}
