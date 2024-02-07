package com.hbm.handler.jei;

import com.hbm.handler.jei.SILEXRecipeHandler;

import com.hbm.util.I18nUtil;
import mezz.jei.api.IGuiHelper;

public class SILEXUVRecipeHandler extends SILEXRecipeHandler {

	public SILEXUVRecipeHandler(IGuiHelper help){
		super(help);
	}

	@Override
	public String getUid(){
		return JEIConfig.SILEX_UV;
	}

	@Override
	public String getTitle(){
		return I18nUtil.resolveKey("jei.silexuv");
	}
}
