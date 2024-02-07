package com.hbm.handler.jei;

import com.hbm.handler.jei.SILEXRecipeHandler;

import com.hbm.util.I18nUtil;
import mezz.jei.api.IGuiHelper;

public class SILEXMicroRecipeHandler extends SILEXRecipeHandler {

	public SILEXMicroRecipeHandler(IGuiHelper help){
		super(help);
	}

	@Override
	public String getUid(){
		return JEIConfig.SILEX_MICRO;
	}

	@Override
	public String getTitle(){
		return I18nUtil.resolveKey("jei.silexmicro");
	}
}
