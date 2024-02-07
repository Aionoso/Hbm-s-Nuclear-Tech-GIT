package com.hbm.handler.jei;

import com.hbm.handler.jei.SILEXRecipeHandler;

import com.hbm.util.I18nUtil;
import mezz.jei.api.IGuiHelper;

public class SILEXXRayRecipeHandler extends SILEXRecipeHandler {

	public SILEXXRayRecipeHandler(IGuiHelper help){
		super(help);
	}

	@Override
	public String getUid(){
		return JEIConfig.SILEX_XRAY;
	}

	@Override
	public String getTitle(){
		return I18nUtil.resolveKey("jei.silexxray");
	}
}
