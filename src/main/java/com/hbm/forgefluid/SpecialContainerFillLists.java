package com.hbm.forgefluid;

import com.hbm.inventory.fluid.FluidType;
import com.hbm.inventory.fluid.Fluids;
import org.apache.commons.lang3.tuple.MutablePair;
import org.apache.commons.lang3.tuple.Pair;

import com.hbm.lib.RefStrings;

import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraftforge.fluids.Fluid;

public class SpecialContainerFillLists {
	
	//Drillgon200: I don't even know what I'm trying to do here, but hopefully it works.
	public enum EnumCanister {
		EMPTY(null, new ModelResourceLocation(RefStrings.MODID + ":canister_empty", "inventory")),
		DIESEL(Fluids.DIESEL, new ModelResourceLocation(RefStrings.MODID + ":canister_fuel", "inventory")),
		OIL(Fluids.OIL, new ModelResourceLocation(RefStrings.MODID + ":canister_oil", "inventory")),
		PETROIL(Fluids.PETROIL, new ModelResourceLocation(RefStrings.MODID + ":canister_petroil", "inventory")),
		FRACKSOL(Fluids.FRACKSOL, new ModelResourceLocation(RefStrings.MODID + ":canister_fracksol", "inventory")),
		KEROSENE(Fluids.KEROSENE, new ModelResourceLocation(RefStrings.MODID + ":canister_kerosene", "inventory")),
		NITAN(Fluids.NITAN, new ModelResourceLocation(RefStrings.MODID + ":canister_superfuel", "inventory")),
		BIOFUEL(Fluids.BIOFUEL, new ModelResourceLocation(RefStrings.MODID + ":canister_biofuel", "inventory")),
		CANOLA(Fluids.LUBRICANT, new ModelResourceLocation(RefStrings.MODID + ":canister_canola", "inventory")),
		REOIL(Fluids.RECLAIMED, new ModelResourceLocation(RefStrings.MODID + ":canister_reoil", "inventory")),
		HEAVYOIL(Fluids.HEAVYOIL, new ModelResourceLocation(RefStrings.MODID + ":canister_heavyoil", "inventory")),
		BITUMEN(Fluids.BITUMEN, new ModelResourceLocation(RefStrings.MODID + ":canister_bitumen", "inventory")),
		SMEAR(Fluids.SMEAR, new ModelResourceLocation(RefStrings.MODID + ":canister_smear", "inventory")),
		HEATINGOIL(Fluids.HEATINGOIL, new ModelResourceLocation(RefStrings.MODID + ":canister_heatingoil", "inventory")),
		NAPHTHA(Fluids.NAPHTHA, new ModelResourceLocation(RefStrings.MODID + ":canister_naphtha", "inventory")),
		LIGHTOIL(Fluids.LIGHTOIL, new ModelResourceLocation(RefStrings.MODID + ":canister_lightoil", "inventory")),
		GASOLINE(Fluids.GASOLINE, new ModelResourceLocation(RefStrings.MODID + ":canister_gasoline", "inventory"));
		
		private FluidType fluid;
		private Pair<ModelResourceLocation, IBakedModel> renderPair;
		private String translateKey;
		
		private EnumCanister(FluidType f, ModelResourceLocation r){
			this.fluid = f;
			this.renderPair = MutablePair.of(r, null);
			this.translateKey = "item." + r.getResourcePath() + ".name";
		}
		public FluidType getFluid(){
			return fluid;
		}
		public String getTranslateKey(){
			return translateKey;
		}
		public IBakedModel getRenderModel(){
			return renderPair.getRight();
		}
		public void putRenderModel(IBakedModel model){
			renderPair.setValue(model);
		}
		public ModelResourceLocation getResourceLocation(){
			return renderPair.getLeft();
		}
		public static boolean contains(FluidType f){
			if(f == null)
				return false;
			for(EnumCanister e : EnumCanister.values()){
				if(e.getFluid() == f)
					return true;
			}
			return false;
		}
		public static EnumCanister getEnumFromFluid(FluidType f){
			if(f == null || f == Fluids.NONE)
				return EnumCanister.EMPTY;
			for(EnumCanister e : EnumCanister.values()){
				if(e.getFluid() == f){
					return e;
				}
			}
			return null;
		}
		public static FluidType[] getFluids() {
			FluidType[] f = new FluidType[EnumCanister.values().length];
			for(int i = 0; i < EnumCanister.values().length; i ++){
				f[i] = EnumCanister.values()[i].getFluid();
			}
			return f;
		}
	}
	
	public enum EnumCell {
		EMPTY(null, new ModelResourceLocation(RefStrings.MODID + ":cell_empty", "inventory")),
		UF6(Fluids.UF6, new ModelResourceLocation(RefStrings.MODID + ":cell_uf6", "inventory")),
		PUF6(Fluids.PUF6, new ModelResourceLocation(RefStrings.MODID + ":cell_puf6", "inventory")),
		ANTIMATTER(Fluids.AMAT, new ModelResourceLocation(RefStrings.MODID + ":cell_antimatter", "inventory")),
		DEUTERIUM(Fluids.DEUTERIUM, new ModelResourceLocation(RefStrings.MODID + ":cell_deuterium", "inventory")),
		TRITIUM(Fluids.TRITIUM, new ModelResourceLocation(RefStrings.MODID + ":cell_tritium", "inventory")),
		SAS3(Fluids.SAS3, new ModelResourceLocation(RefStrings.MODID + ":cell_sas3", "inventory")),
		ANTISCHRABIDIUM(Fluids.ASCHRAB, new ModelResourceLocation(RefStrings.MODID + ":cell_anti_schrabidium", "inventory"));
		
		private FluidType fluid;
		private Pair<ModelResourceLocation, IBakedModel> renderPair;
		private String translateKey;
		
		private EnumCell(FluidType f, ModelResourceLocation r){
			this.fluid = f;
			this.renderPair = MutablePair.of(r, null);
			this.translateKey = "item." + r.getResourcePath() + ".name";
		}
		public FluidType getFluid(){
			return fluid;
		}
		public String getTranslateKey(){
			return translateKey;
		}
		public IBakedModel getRenderModel(){
			return renderPair.getRight();
		}
		public void putRenderModel(IBakedModel model){
			renderPair.setValue(model);
		}
		public ModelResourceLocation getResourceLocation(){
			return renderPair.getLeft();
		}
		public static boolean contains(FluidType f){
			if(f == null)
				return false;
			for(EnumCell e : EnumCell.values()){
				if(e.getFluid() == f)
					return true;
			}
			return false;
		}
		public static EnumCell getEnumFromFluid(FluidType f){
			if(f == null)
				return EnumCell.EMPTY;
			for(EnumCell e : EnumCell.values()){
				if(e.getFluid() == f){
					return e;
				}
			}
			return null;
		}
		public static FluidType[] getFluids() {
			FluidType[] f = new FluidType[EnumCell.values().length];
			for(int i = 0; i < EnumCell.values().length; i ++){
				f[i] = EnumCell.values()[i].getFluid();
			}
			return f;
		}
	}
	
	public enum EnumGasCanister {
		EMPTY(null, new ModelResourceLocation(RefStrings.MODID + ":gas_empty", "inventory")),
		NATURAL(Fluids.GAS, new ModelResourceLocation(RefStrings.MODID + ":gas_full", "inventory")),
		PETROLEUM(Fluids.PETROLEUM, new ModelResourceLocation(RefStrings.MODID + ":gas_petroleum", "inventory")),
		BIOGAS(Fluids.BIOGAS, new ModelResourceLocation(RefStrings.MODID + ":gas_biogas", "inventory")),
		HYDROGEN(Fluids.HYDROGEN, new ModelResourceLocation(RefStrings.MODID + ":gas_hydrogen", "inventory")),
		DEUTERIUM(Fluids.DEUTERIUM, new ModelResourceLocation(RefStrings.MODID + ":gas_deuterium", "inventory")),
		TRITIUM(Fluids.TRITIUM, new ModelResourceLocation(RefStrings.MODID + ":gas_tritium", "inventory")),
		OXYGEN(Fluids.OXYGEN, new ModelResourceLocation(RefStrings.MODID + ":gas_oxygen", "inventory"));
		
		private FluidType fluid;
		private Pair<ModelResourceLocation, IBakedModel> renderPair;
		private String translateKey;
		
		private EnumGasCanister(FluidType f, ModelResourceLocation r){
			this.fluid = f;
			this.renderPair = MutablePair.of(r, null);
			this.translateKey = "item." + r.getResourcePath() + ".name";
		}
		public FluidType getFluid(){
			return fluid;
		}
		public String getTranslateKey(){
			return translateKey;
		}
		public IBakedModel getRenderModel(){
			return renderPair.getRight();
		}
		public void putRenderModel(IBakedModel model){
			renderPair.setValue(model);
		}
		public ModelResourceLocation getResourceLocation(){
			return renderPair.getLeft();
		}
		public static boolean contains(FluidType f){
			if(f == null)
				return false;
			for(EnumGasCanister e : EnumGasCanister.values()){
				if(e.getFluid() == f)
					return true;
			}
			return false;
		}
		public static EnumGasCanister getEnumFromFluid(FluidType f){
			if(f == null)
				return EnumGasCanister.EMPTY;
			for(EnumGasCanister e : EnumGasCanister.values()){
				if(e.getFluid() == f){
					return e;
				}
			}
			return null;
		}
		public static FluidType[] getFluids() {
			FluidType[] f = new FluidType[EnumGasCanister.values().length];
			for(int i = 0; i < EnumGasCanister.values().length; i ++){
				f[i] = EnumGasCanister.values()[i].getFluid();
			}
			return f;
		}
	}

	
}
