package com.hbm.handler.jei;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.hbm.forgefluid.SpecialContainerFillLists.EnumCell;
import com.hbm.forgefluid.SpecialContainerFillLists.EnumCanister;
import com.hbm.forgefluid.SpecialContainerFillLists.EnumGasCanister;
import com.hbm.inventory.*;
import com.hbm.inventory.AnvilRecipes.AnvilConstructionRecipe;
import com.hbm.inventory.AnvilRecipes.AnvilOutput;
import com.hbm.inventory.AnvilRecipes.OverlayType;
import com.hbm.inventory.BreederRecipes.BreederRecipe;
import com.hbm.inventory.MachineRecipes.GasCentOutput;
import com.hbm.inventory.MagicRecipes.MagicRecipe;
import com.hbm.inventory.RecipesCommon.AStack;
import com.hbm.inventory.RecipesCommon.ComparableStack;
import com.hbm.inventory.RecipesCommon.NbtComparableStack;
import com.hbm.inventory.ChemplantRecipes;
import com.hbm.inventory.fluid.FluidStack;
import com.hbm.inventory.fluid.FluidType;
import com.hbm.inventory.fluid.Fluids;
import com.hbm.inventory.fluid.trait.FT_Heatable;
import com.hbm.items.ModItems;
import com.hbm.items.machine.ItemAssemblyTemplate;
import com.hbm.items.machine.ItemFluidIcon;
import com.hbm.items.machine.ItemFluidTank;
import com.hbm.items.machine.ItemFELCrystal.EnumWavelengths;
import com.hbm.items.special.ItemCell;
import com.hbm.items.tool.ItemFluidCanister;
import com.hbm.items.tool.ItemGasCanister;
import com.hbm.lib.Library;
import com.hbm.util.WeightedRandomObject;
import com.hbm.util.Tuple.Pair;
import com.hbm.util.I18nUtil;

import mezz.jei.api.gui.IDrawableStatic;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.ingredients.VanillaTypes;
import mezz.jei.api.recipe.IIngredientType;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;

public class JeiRecipes {

	private static List<ChemRecipe> chemRecipes = null;
	private static List<MixerRecipe> mixerRecipes = null;
	private static List<CyclotronRecipe> cyclotronRecipes = null;
	private static List<PressRecipe> pressRecipes = null;
	private static List<AlloyFurnaceRecipe> alloyFurnaceRecipes = null;
	private static List<BoilerRecipe> boilerRecipes = null;
	private static List<CMBFurnaceRecipe> cmbRecipes = null;
	private static List<GasCentRecipe> gasCentRecipes = null;
	private static List<ReactorRecipe> reactorRecipes = null;
	private static List<WasteDrumRecipe> wasteDrumRecipes = null;
	private static List<StorageDrumRecipe> storageDrumRecipes = null;
	private static List<RBMKFuelRecipe> rbmkFuelRecipes = null;
	private static List<RefineryRecipe> refineryRecipes = null;
	private static List<CrackingRecipe> crackingRecipes = null;
	private static List<FractioningRecipe> fractioningRecipes = null;
	private static List<FluidRecipe> fluidEquivalences = null;
	private static List<BookRecipe> bookRecipes = null;
	private static List<FusionRecipe> fusionByproducts = null;
	private static List<SAFERecipe> safeRecipes = null;
	private static List<HadronRecipe> hadronRecipes = null;
	private static List<SILEXRecipe> silexRecipes = null;
	private static Map<EnumWavelengths, List<SILEXRecipe>> waveSilexRecipes = new HashMap<EnumWavelengths, List<SILEXRecipe>>();
	private static List<SmithingRecipe> smithingRecipes = null;
	private static List<AnvilRecipe> anvilRecipes = null;
	private static List<TransmutationRecipe> transmutationRecipes = null;
	
	private static List<ItemStack> batteries = null;
	private static Map<Integer, List<ItemStack>> reactorFuelMap = new HashMap<Integer, List<ItemStack>>();
	private static List<ItemStack> blades = null;
	private static List<ItemStack> alloyFuels = null;

	public static final IIngredientType<FluidStack> NTMFLUID = () -> {
		return FluidStack.class;
	};
	
	
	public static class ChemRecipe implements IRecipeWrapper {
		
		private final List<List<ItemStack>> inputs;
		private final List<ItemStack> outputs;
		
		public ChemRecipe(List<AStack> inputs, List<ItemStack> outputs) {
			List<List<ItemStack>> list = new ArrayList<>(inputs.size());
			for(AStack s : inputs)
				list.add(s.getStackList());
			this.inputs = list;
			this.outputs = outputs; 
		}
		
		@Override
		public void getIngredients(IIngredients ingredients) {
			List<List<ItemStack>> in = Library.copyItemStackListList(inputs); // list of inputs and their list of possible items
			ingredients.setInputLists(VanillaTypes.ITEM, in);
			ingredients.setOutputs(VanillaTypes.ITEM, outputs);
		}
	}

	public static class MixerRecipe implements IRecipeWrapper {
		
		private final List<List<ItemStack>> inputs;
		private final ItemStack output;
		
		public MixerRecipe(List<AStack> inputs, ItemStack output) {
			List<List<ItemStack>> list = new ArrayList<>(inputs.size());
			for(AStack s : inputs)
				list.add(s.getStackList());
			this.inputs = list;
			this.output = output; 
		}
		
		@Override
		public void getIngredients(IIngredients ingredients) {
			List<List<ItemStack>> in = Library.copyItemStackListList(inputs); // list of inputs and their list of possible items
			ingredients.setInputLists(VanillaTypes.ITEM, in);
			ingredients.setOutput(VanillaTypes.ITEM, output);
		}

		public int getInputSize(){
			return inputs.size();
		}
	}
	
	public static class CyclotronRecipe implements IRecipeWrapper {
		
		private final List<ItemStack> inputs;
		private final ItemStack output;
		
		public CyclotronRecipe(List<ItemStack> inputs, ItemStack output) {
			this.inputs = inputs;
			this.output = output; 
		}
		
		@Override
		public void getIngredients(IIngredients ingredients) {
			ingredients.setInputs(VanillaTypes.ITEM, inputs);
			ingredients.setOutput(VanillaTypes.ITEM, output);
		}
		
	}
	
	public static class PressRecipe implements IRecipeWrapper {

		private final List<ItemStack> stamps;
		private final List<ItemStack> input;
		private final ItemStack output;
		
		public PressRecipe(List<ItemStack> stamps, List<ItemStack> input, ItemStack output) {
			this.stamps = stamps;
			this.input = input;
			this.output = output; 
		}
		
		public List<ItemStack> getStamps() {
			return stamps;
		}
		
		@Override
		public void getIngredients(IIngredients ingredients) {
			ingredients.setInputs(VanillaTypes.ITEM, input);
			ingredients.setOutput(VanillaTypes.ITEM, output);
		}
		
	}
	
	public static class AlloyFurnaceRecipe implements IRecipeWrapper {
		
		private final List<List<ItemStack>> inputs;
		private final ItemStack output;
		
		public AlloyFurnaceRecipe(AStack input1, AStack input2, ItemStack output) {
			List<List<ItemStack>> list = new ArrayList<>(2);
			list.add(input1.getStackList());
			list.add(input2.getStackList());
			this.inputs = list;
			this.output = output; 
		}
		
		@Override
		public void getIngredients(IIngredients ingredients) {
			List<List<ItemStack>> in = Library.copyItemStackListList(inputs);
			ingredients.setInputLists(VanillaTypes.ITEM, in);
			ingredients.setOutput(VanillaTypes.ITEM, output);
		}
		
	}

	public static class BoilerRecipe implements IRecipeWrapper {
		public final FluidStack input;
		public final FluidStack output;

		public BoilerRecipe(FluidStack input, FluidStack output) {
			this.input = input;
			this.output = output;
		}

		@Override
		public void getIngredients(IIngredients ingredients) {
			ingredients.setInput(NTMFLUID, input);
			ingredients.setOutput(NTMFLUID, output);
		}
	}
	
	public static class CMBFurnaceRecipe implements IRecipeWrapper {
		
		private final List<ItemStack> inputs;
		private final ItemStack output;
		
		public CMBFurnaceRecipe(List<ItemStack> inputs, ItemStack output) {
			this.inputs = inputs;
			this.output = output; 
		}
		
		@Override
		public void getIngredients(IIngredients ingredients) {
			ingredients.setInputs(VanillaTypes.ITEM, inputs);
			ingredients.setOutput(VanillaTypes.ITEM, output);
		}
		
	}
	
	public static class GasCentRecipe implements IRecipeWrapper {
		
		private final ItemStack input;
		private final List<ItemStack> outputs;
		
		public GasCentRecipe(ItemStack input, List<ItemStack> outputs) {
			this.input = input;
			this.outputs = outputs; 
		}
		
		@Override
		public void getIngredients(IIngredients ingredients) {
			ingredients.setInput(VanillaTypes.ITEM, input);
			ingredients.setOutputs(VanillaTypes.ITEM, outputs);
		}
		
	}
	
	public static class ReactorRecipe implements IRecipeWrapper {
		
		public static IDrawableStatic heatTex;
		
		private final ItemStack input;
		private final ItemStack output;
		public final int heat;
		
		public ReactorRecipe(ItemStack input, ItemStack output, int heat) {
			this.input = input;
			this.output = output; 
			this.heat = heat;
		}
		
		@Override
		public void getIngredients(IIngredients ingredients) {
			ingredients.setInput(VanillaTypes.ITEM, input);
			ingredients.setOutput(VanillaTypes.ITEM, output);
		}
		
		@Override
		public void drawInfo(Minecraft minecraft, int recipeWidth, int recipeHeight, int mouseX, int mouseY) {
			heatTex.draw(minecraft, 1, 20, 16-heat*4, 0, 0, 0);
		}
		
	}

	public static class WasteDrumRecipe implements IRecipeWrapper {
		
		private final ItemStack input;
		private final ItemStack output;
		
		public WasteDrumRecipe(ItemStack input, ItemStack output) {
			this.input = input;
			this.output = output; 
		}
		
		@Override
		public void getIngredients(IIngredients ingredients) {
			ingredients.setInput(VanillaTypes.ITEM, input);
			ingredients.setOutput(VanillaTypes.ITEM, output);
		}
	}

	public static class StorageDrumRecipe implements IRecipeWrapper {
		
		private final ItemStack input;
		private final ItemStack output;
		
		public StorageDrumRecipe(ItemStack input, ItemStack output) {
			this.input = input;
			this.output = output; 
		}
		
		@Override
		public void getIngredients(IIngredients ingredients) {
			ingredients.setInput(VanillaTypes.ITEM, input);
			ingredients.setOutput(VanillaTypes.ITEM, output);
		}
	}

	public static class TransmutationRecipe implements IRecipeWrapper {
		
		private final List<List<ItemStack>> inputs;
		private final ItemStack output;
		
		public TransmutationRecipe(List<ItemStack> inputs, ItemStack output) {
			this.inputs = new ArrayList();
			this.inputs.add(inputs);
			this.output = output; 
		}
		
		@Override
		public void getIngredients(IIngredients ingredients) {
			ingredients.setInputLists(VanillaTypes.ITEM, inputs);
			ingredients.setOutput(VanillaTypes.ITEM, output);
		}
	}

	public static class RBMKFuelRecipe implements IRecipeWrapper {
		
		private final ItemStack input;
		private final ItemStack output;
		
		public RBMKFuelRecipe(ItemStack input, ItemStack output) {
			this.input = input;
			this.output = output; 
		}
		
		@Override
		public void getIngredients(IIngredients ingredients) {
			ingredients.setInput(VanillaTypes.ITEM, input);
			ingredients.setOutput(VanillaTypes.ITEM, output);
		}
	}
	
	public static class RefineryRecipe implements IRecipeWrapper {
		
		private final ItemStack input;
		private final List<ItemStack> outputs;
		
		public RefineryRecipe(ItemStack input, List<ItemStack> outputs) {
			this.input = input;
			this.outputs = outputs; 
		}
		
		@Override
		public void getIngredients(IIngredients ingredients) {
			ingredients.setInput(VanillaTypes.ITEM, input);
			ingredients.setOutputs(VanillaTypes.ITEM, outputs);
		}
		
	}

	public static class CrackingRecipe implements IRecipeWrapper {
		
		private final List<ItemStack> inputs;
		public final List<ItemStack> outputs;
		
		public CrackingRecipe(List<ItemStack> inputs, List<ItemStack> outputs) {
			this.inputs = inputs;
			this.outputs = outputs; 
		}
		
		@Override
		public void getIngredients(IIngredients ingredients) {
			ingredients.setInputs(VanillaTypes.ITEM, inputs);
			ingredients.setOutputs(VanillaTypes.ITEM, outputs);
		}
		
	}

	public static class FractioningRecipe implements IRecipeWrapper {
		
		private final ItemStack input;
		private final List<ItemStack> outputs;
		
		public FractioningRecipe(ItemStack input, List<ItemStack> outputs) {
			this.input = input;
			this.outputs = outputs; 
		}
		
		@Override
		public void getIngredients(IIngredients ingredients) {
			ingredients.setInput(VanillaTypes.ITEM, input);
			ingredients.setOutputs(VanillaTypes.ITEM, outputs);
		}
		
	}
	
	public static class FluidRecipe implements IRecipeWrapper {
		
		protected final ItemStack input;
		protected final ItemStack output;
		
		public FluidRecipe(ItemStack input, ItemStack output) {
			this.input = input;
			this.output = output; 
		}
		
		@Override
		public void getIngredients(IIngredients ingredients) {
			ingredients.setInput(VanillaTypes.ITEM, input);
			ingredients.setOutput(VanillaTypes.ITEM, output);
		}
		
	}
	
	public static class FluidRecipeInverse extends FluidRecipe implements IRecipeWrapper {
		
		public FluidRecipeInverse(ItemStack input, ItemStack output) {
			super(input, output);
		}
		
		@Override
		public void getIngredients(IIngredients ingredients) {
			ingredients.setInput(VanillaTypes.ITEM, output);
			ingredients.setOutput(VanillaTypes.ITEM, input);
		}
		
	}
	
	public static class AssemblerRecipeWrapper implements IRecipeWrapper {

		ItemStack output;
		List<List<ItemStack>> inputs;
		int time;
		
		public AssemblerRecipeWrapper(ItemStack output, AStack[] inputs, int time) {
			this.output = output;
			List<List<ItemStack>> list = new ArrayList<>(inputs.length);
			for(AStack s : inputs)
				list.add(s.getStackList());
			this.inputs = list;
			this.time = time;
		}
		
		@Override
		public void getIngredients(IIngredients ingredients) {
			List<List<ItemStack>> in = Library.copyItemStackListList(inputs);
			while(in.size() < 12)
				in.add(Arrays.asList(new ItemStack(ModItems.nothing)));
			int index = -1;
			for(int i = 0; i < AssemblerRecipes.recipeList.size(); i++){ // finding the template item
				if(AssemblerRecipes.recipeList.get(i).isApplicable(output)){
					index = i;
					break;
				}
			}
			if(index >= 0) // adding the template item
				in.add(Arrays.asList(ItemAssemblyTemplate.getTemplate(index)));
			else {
				in.add(Arrays.asList(new ItemStack(ModItems.nothing)));
			}
			ingredients.setInputLists(VanillaTypes.ITEM, in);
			ingredients.setOutput(VanillaTypes.ITEM, output);
		}
		
	}
	
	public static class BookRecipe implements IRecipeWrapper {

		List<ItemStack> inputs;
		ItemStack output;
		
		public BookRecipe(MagicRecipe recipe) {
			inputs = new ArrayList<>(4);
			for(int i = 0; i < recipe.in.size(); i ++)
				inputs.add(recipe.in.get(i).getStack());
			while(inputs.size() < 4)
				inputs.add(new ItemStack(ModItems.nothing));
			output = recipe.getResult();
		}
		
		@Override
		public void getIngredients(IIngredients ingredients) {
			ingredients.setInputs(VanillaTypes.ITEM, inputs);
			ingredients.setOutput(VanillaTypes.ITEM, output);
		}
		
	}
	
	public static class FusionRecipe implements IRecipeWrapper {
		ItemStack input;
		ItemStack output;
		
		public FusionRecipe(FluidStack input, ItemStack output) {
			this.input = ItemFluidIcon.make(input);
			this.output = output;
		}
		
		@Override
		public void getIngredients(IIngredients ingredients) {
			ingredients.setInput(VanillaTypes.ITEM, input);
			ingredients.setOutput(VanillaTypes.ITEM, output);
		}
	}

	public static class SAFERecipe implements IRecipeWrapper {
		ItemStack input;
		ItemStack output;
		
		public SAFERecipe(ItemStack input, ItemStack output) {
			this.input = input;
			this.output = output;
		}
		
		@Override
		public void getIngredients(IIngredients ingredients) {
			ingredients.setInput(VanillaTypes.ITEM, input);
			ingredients.setOutput(VanillaTypes.ITEM, output);
		}
	}
	
	public static class HadronRecipe implements IRecipeWrapper {

		public ItemStack in1, in2, out1, out2;
		public int momentum;
		public boolean analysisOnly;
		
		public HadronRecipe(ItemStack in1, ItemStack in2, ItemStack out1, ItemStack out2, int momentum, boolean analysis) {
			this.in1 = in1;
			this.in2 = in2;
			this.out1 = out1;
			this.out2 = out2;
			this.momentum = momentum;
			this.analysisOnly = analysis;
		}
		
		@Override
		public void getIngredients(IIngredients ingredients) {
			ingredients.setInputs(VanillaTypes.ITEM, Arrays.asList(in1, in2));
			ingredients.setOutputs(VanillaTypes.ITEM, Arrays.asList(out1, out2));
		}
		
		@Override
		public void drawInfo(Minecraft minecraft, int recipeWidth, int recipeHeight, int mouseX, int mouseY) {
			if(analysisOnly)
				HadronRecipeHandler.analysis.draw(minecraft, 117, 17);
			FontRenderer fontRenderer = minecraft.fontRenderer;
	    	
	    	String mom = "" + momentum;
	    	fontRenderer.drawString(mom, -fontRenderer.getStringWidth(mom) / 2 + 19, 36, 0x404040);
	    	GlStateManager.color(1, 1, 1, 1);
		}
		
	}
	
	public static class SILEXRecipe implements IRecipeWrapper {

		List<List<ItemStack>> input;
		List<Double> chances;
		List<ItemStack> outputs;
		double produced;
		EnumWavelengths laserStrength;
		
		public SILEXRecipe(List<ItemStack> inputs, List<Double> chances, List<ItemStack> outputs, double produced, EnumWavelengths laserStrength){
			input = new ArrayList<>(1);
			input.add(inputs);
			this.chances = chances;
			this.outputs = outputs;
			this.produced = produced;
			this.laserStrength = laserStrength;
		}
		
		@Override
		public void getIngredients(IIngredients ingredients){
			ingredients.setInputLists(VanillaTypes.ITEM, input);
			ingredients.setOutputs(VanillaTypes.ITEM, outputs);
		}

		@Override
		public void drawInfo(Minecraft minecraft, int recipeWidth, int recipeHeight, int mouseX, int mouseY){
			FontRenderer fontRenderer = minecraft.fontRenderer;

			int output_size = this.outputs.size();
			int sep = output_size > 6 ? 4 : output_size > 4 ? 3 : 2;
			for(int i = 0; i < output_size; i ++){
				double chance = this.chances.get(i);
				if(i < sep) {
					fontRenderer.drawString(((int)(chance * 100D) / 100D)+"%", 90, 33 + i * 18 - 9 * ((Math.min(output_size, sep) + 1) / 2), 0x404040);
				} else {
					fontRenderer.drawString(((int)(chance * 100D) / 100D)+"%", 138, 33 + (i - sep) * 18 - 9 * ((Math.min(output_size - sep, sep) + 1)/2), 0x404040);
				}
			}
			
			String am = ((int)(this.produced * 10D) / 10D) + "x";
			fontRenderer.drawString(am, 52 - fontRenderer.getStringWidth(am) / 2, 51, 0x404040);

			String wavelength = (this.laserStrength == EnumWavelengths.NULL) ? TextFormatting.WHITE + "N/A" : this.laserStrength.textColor + I18nUtil.resolveKey(this.laserStrength.name);
			fontRenderer.drawString(wavelength, (35 - fontRenderer.getStringWidth(wavelength) / 2), 17, 0x404040);
		}
	}
	
	public static class AnvilRecipe implements IRecipeWrapper {

		List<List<ItemStack>> inputs;
		List<ItemStack> outputs;
		List<Float> chances;
		int tierLower;
		int tierUpper;
		OverlayType overlay;
		
		public AnvilRecipe(List<List<ItemStack>> inp, List<ItemStack> otp, List<Float> chance, int tL, int tU, OverlayType ovl){
			inputs = inp;
			outputs = otp;
			chances = chance;
			tierLower = tL;
			tierUpper = tU;
			overlay = ovl;
		}
		
		@Override
		public void getIngredients(IIngredients ingredients){
			ingredients.setInputLists(VanillaTypes.ITEM, inputs);
			ingredients.setOutputs(VanillaTypes.ITEM, outputs);
		}
		
	}
	
	public static class SmithingRecipe implements IRecipeWrapper {

		List<List<ItemStack>> inputs;
		ItemStack output;
		int tier;
		
		public SmithingRecipe(List<ItemStack> left, List<ItemStack> right, ItemStack out, int tier){
			inputs = new ArrayList<>(2);
			inputs.add(left);
			inputs.add(right);
			output = out;
			this.tier = tier;
		}
		
		@Override
		public void getIngredients(IIngredients ingredients){
			ingredients.setInputLists(VanillaTypes.ITEM, inputs);
			ingredients.setOutput(VanillaTypes.ITEM, output);
		}
		
	}
	
	public static List<ChemRecipe> getChemistryRecipes() {
		if(chemRecipes != null)
			return chemRecipes;
		chemRecipes = new ArrayList<ChemRecipe>();
		
       for(int i: ChemplantRecipes.recipeNames.keySet()){

        	List<AStack> inputs = new ArrayList<AStack>(7);
        	for(int j = 0; j < 7; j ++)
        		inputs.add(j, new ComparableStack(ModItems.nothing));

        	List<ItemStack> outputs = new ArrayList<ItemStack>(6);
        	for(int j = 0; j < 6; j ++)
        		outputs.add(j, new ItemStack(ModItems.nothing));
        	
        	//Adding template item
        	ItemStack template = new ItemStack(ModItems.chemistry_template, 1, i);

		   ChemplantRecipes.ChemRecipe recipe = ChemplantRecipes.indexMapping.get(template.getItemDamage());

        	List<AStack> listIn = ChemplantRecipes.ChemRecipe.getChemInputFromTempate(recipe, template);
        	FluidStack[] fluidIn = ChemplantRecipes.ChemRecipe.getFluidInputFromTempate(recipe, template);
        	ItemStack[] listOut = ChemplantRecipes.ChemRecipe.getChemOutputFromTempate(recipe, template);
        	FluidStack[] fluidOut = ChemplantRecipes.ChemRecipe.getFluidOutputFromTempate(recipe, template);

        	inputs.set(6, new ComparableStack(template));

        	if(listIn != null)
        		for(int j = 0; j < listIn.size(); j++)
        			if(listIn.get(j) != null)
        				inputs.set(j + 2, listIn.get(j).copy());

        	if(fluidIn != null)
	        	for(int j = 0; j < fluidIn.length; j++)
	        		if(fluidIn[j] != null)
	        			inputs.set(j, new NbtComparableStack(ItemFluidIcon.make(fluidIn[j].type, fluidIn[j].fill)));
        	
        	if(listOut != null)
	        	for(int j = 0; j < listOut.length; j++)
	        		if(listOut[j] != null)
	        			outputs.set(j + 2, listOut[j].copy());
        	
        	if(fluidOut != null)
	        	for(int j = 0; j < fluidOut.length; j++)
	        		if(fluidOut[j] != null)
	        			outputs.set(j, ItemFluidIcon.make(fluidOut[j].type, fluidOut[j].fill));
        	
        	chemRecipes.add(new ChemRecipe(inputs, outputs));
        }
		
		return chemRecipes;
	}

	public static List<MixerRecipe> getMixerRecipes() {
		if(mixerRecipes != null)
			return mixerRecipes;
		mixerRecipes = new ArrayList<MixerRecipe>();
		
        for(FluidType f : MixerRecipes.recipes.keySet()){

        	List<AStack> inputs = new ArrayList<AStack>(3);

        	AStack inputItem = MixerRecipes.getInputItem(f);
        	FluidStack[] inputFluids = MixerRecipes.getInputFluidStacks(f);
        	if(inputItem != null)
        		inputs.add(inputItem);
        	if(inputFluids != null){
        		if(inputFluids.length >= 1) inputs.add(new NbtComparableStack(ItemFluidIcon.make(inputFluids[0].type, inputFluids[0].fill)));
        		if(inputFluids.length == 2) inputs.add(new NbtComparableStack(ItemFluidIcon.make(inputFluids[1].type, inputFluids[1].fill)));
        	}

        	ItemStack output = ItemFluidIcon.make(f, MixerRecipes.getFluidOutputAmount(f));
        	
        	mixerRecipes.add(new MixerRecipe(inputs, output));
        }
		
		return mixerRecipes;
	}
	
	public static List<CyclotronRecipe> getCyclotronRecipes() {
		if(cyclotronRecipes != null)
			 return cyclotronRecipes;
		Map<ItemStack[], ItemStack> recipes = CyclotronRecipes.getRecipes();
		cyclotronRecipes = new ArrayList<CyclotronRecipe>(recipes.size());
		for(Entry<ItemStack[], ItemStack> e : recipes.entrySet()){
			cyclotronRecipes.add(new CyclotronRecipe(Arrays.asList(e.getKey()), e.getValue()));
		}
		
		return cyclotronRecipes;
	}
	
	@SuppressWarnings("unchecked")
	public static List<PressRecipe> getPressRecipes() {
		if(pressRecipes != null)
			return pressRecipes;

		pressRecipes = new ArrayList<PressRecipe>();
		
		for(Map.Entry<Pair<PressRecipes.PressType, AStack>, ItemStack> entry : PressRecipes.pressRecipes.entrySet()){

			pressRecipes.add(new PressRecipe(PressRecipes.getStampList(entry.getKey().getKey()), entry.getKey().getValue().getStackList(), entry.getValue()));
		}
		
		return pressRecipes;
	}
	
	
	public static List<AlloyFurnaceRecipe> getAlloyRecipes() {
		if(alloyFurnaceRecipes != null)
			return alloyFurnaceRecipes;
		alloyFurnaceRecipes = new ArrayList<AlloyFurnaceRecipe>();

		for(Map.Entry<Pair<AStack, AStack>, ItemStack> pairEntry : DiFurnaceRecipes.diRecipes.entrySet()){
			alloyFurnaceRecipes.add(new AlloyFurnaceRecipe(pairEntry.getKey().getKey(), pairEntry.getKey().getValue(), pairEntry.getValue()));
		}
		return alloyFurnaceRecipes;
	}

	public static List<RBMKFuelRecipe> getRBMKFuelRecipes() {
		if(rbmkFuelRecipes != null)
			return rbmkFuelRecipes;
		rbmkFuelRecipes = new ArrayList<RBMKFuelRecipe>();

		for(Map.Entry<ItemStack, ItemStack> pairEntry : RBMKFuelRecipes.recipes.entrySet()){
			rbmkFuelRecipes.add(new RBMKFuelRecipe(pairEntry.getKey(), pairEntry.getValue()));
		}
		return rbmkFuelRecipes;
	}
	
	public static List<ItemStack> getAlloyFuels() {
		if(alloyFuels != null)
			return alloyFuels;
		alloyFuels = DiFurnaceRecipes.getAlloyFuels();
		return alloyFuels;
	}

	public static List<BoilerRecipe> getBoilerRecipes() {
		if (boilerRecipes != null) {
			return boilerRecipes;
		}

		boilerRecipes = new ArrayList<>();

		for (FluidType type : Fluids.getInNiceOrder()) {
			if (type.hasTrait(FT_Heatable.class)) {
				FT_Heatable trait = type.getTrait(FT_Heatable.class);

				if (trait.getEfficiency(FT_Heatable.HeatingType.BOILER) > 0) {
					FT_Heatable.HeatingStep step = trait.getFirstStep();
					FluidStack input = new FluidStack(type, step.amountReq);
					FluidStack output = new FluidStack(step.typeProduced, step.amountProduced);

					boilerRecipes.add(new BoilerRecipe(input, output));
				}
			}
		}

		return boilerRecipes;
	}
	
	public static List<ItemStack> getBatteries() {
		if(batteries != null)
			return batteries;
		batteries = new ArrayList<ItemStack>();
		batteries.add(new ItemStack(ModItems.battery_potato));
		batteries.add(new ItemStack(ModItems.battery_potatos));
		batteries.add(new ItemStack(ModItems.battery_su));
		batteries.add(new ItemStack(ModItems.battery_su_l));
		batteries.add(new ItemStack(ModItems.battery_generic));
		batteries.add(new ItemStack(ModItems.battery_red_cell));
		batteries.add(new ItemStack(ModItems.battery_red_cell_6));
		batteries.add(new ItemStack(ModItems.battery_red_cell_24));
		batteries.add(new ItemStack(ModItems.battery_advanced));
		batteries.add(new ItemStack(ModItems.battery_advanced_cell));
		batteries.add(new ItemStack(ModItems.battery_advanced_cell_4));
		batteries.add(new ItemStack(ModItems.battery_advanced_cell_12));
		batteries.add(new ItemStack(ModItems.battery_lithium));
		batteries.add(new ItemStack(ModItems.battery_lithium_cell));
		batteries.add(new ItemStack(ModItems.battery_lithium_cell_3));
		batteries.add(new ItemStack(ModItems.battery_lithium_cell_6));
		batteries.add(new ItemStack(ModItems.battery_schrabidium));
		batteries.add(new ItemStack(ModItems.battery_schrabidium_cell));
		batteries.add(new ItemStack(ModItems.battery_schrabidium_cell_2));
		batteries.add(new ItemStack(ModItems.battery_schrabidium_cell_4));
		batteries.add(new ItemStack(ModItems.battery_spark));
		batteries.add(new ItemStack(ModItems.battery_spark_cell_6));
		batteries.add(new ItemStack(ModItems.battery_spark_cell_25));
		batteries.add(new ItemStack(ModItems.battery_spark_cell_100));
		batteries.add(new ItemStack(ModItems.battery_spark_cell_1000));
		batteries.add(new ItemStack(ModItems.battery_spark_cell_10000));
		batteries.add(new ItemStack(ModItems.battery_spark_cell_power));
		batteries.add(new ItemStack(ModItems.fusion_core));
		batteries.add(new ItemStack(ModItems.energy_core));
		return batteries;
	}
	
	public static List<CMBFurnaceRecipe> getCMBRecipes() {
		if(cmbRecipes != null)
			return cmbRecipes;
		cmbRecipes = new ArrayList<CMBFurnaceRecipe>();
		
		cmbRecipes.add(new CMBFurnaceRecipe(Arrays.asList(new ItemStack(ModItems.ingot_advanced_alloy), new ItemStack(ModItems.ingot_magnetized_tungsten)), new ItemStack(ModItems.ingot_combine_steel, 4)));
		cmbRecipes.add(new CMBFurnaceRecipe(Arrays.asList(new ItemStack(ModItems.powder_advanced_alloy), new ItemStack(ModItems.powder_magnetized_tungsten)), new ItemStack(ModItems.ingot_combine_steel, 4)));
		
		return cmbRecipes;
	}
	
	public static List<GasCentRecipe> getGasCentrifugeRecipes() {
		if(gasCentRecipes != null)
			return gasCentRecipes;
		gasCentRecipes = new ArrayList<GasCentRecipe>();

		for(FluidType f : Fluids.getAll()){
			List<GasCentOutput> outputs = MachineRecipes.getGasCentOutput(f);
			
			if(outputs != null){
				int totalWeight = 0;
				
				for(GasCentOutput o : outputs) {
					totalWeight += o.weight;
				}
				
				ItemStack input = ItemFluidIcon.make(f, MachineRecipes.getFluidConsumedGasCent(f) * totalWeight);
				
				List<ItemStack> result = new ArrayList<ItemStack>(4);
				
				for(GasCentOutput o : outputs){
					ItemStack stack = o.output.copy();
					stack.setCount(stack.getCount() * o.weight);
					result.add(stack);
				}
				
				gasCentRecipes.add(new GasCentRecipe(input, result));
			}
		}
		
		return gasCentRecipes;
	}
	
	public static List<BookRecipe> getBookRecipes(){
		if(bookRecipes != null)
			return bookRecipes;
		bookRecipes = new ArrayList<>();
		for(MagicRecipe m : MagicRecipes.getRecipes()){
			bookRecipes.add(new BookRecipe(m));
		}
		return bookRecipes;
	}
	
	public static List<ReactorRecipe> getReactorRecipes(){
		if(reactorRecipes != null)
			return reactorRecipes;
		reactorRecipes = new ArrayList<ReactorRecipe>();
		
		for(Map.Entry<ItemStack, BreederRecipe> entry : BreederRecipes.getAllRecipes().entrySet()){
			reactorRecipes.add(new ReactorRecipe(entry.getKey(), entry.getValue().output, entry.getValue().heat));
		}
		
		return reactorRecipes;
	}

	public static List<WasteDrumRecipe> getWasteDrumRecipes(){
		if(wasteDrumRecipes != null)
			return wasteDrumRecipes;
		wasteDrumRecipes = new ArrayList<WasteDrumRecipe>();
		
		for(Map.Entry<Item, ItemStack> entry : WasteDrumRecipes.recipes.entrySet()){
			wasteDrumRecipes.add(new WasteDrumRecipe(new ItemStack(entry.getKey()), entry.getValue()));
		}
		
		return wasteDrumRecipes;
	}

	public static List<StorageDrumRecipe> getStorageDrumRecipes(){
		if(storageDrumRecipes != null)
			return storageDrumRecipes;
		storageDrumRecipes = new ArrayList<StorageDrumRecipe>();
		
		for(Map.Entry<ComparableStack, ItemStack> entry : StorageDrumRecipes.recipeOutputs.entrySet()){
			storageDrumRecipes.add(new StorageDrumRecipe(entry.getKey().getStack(), entry.getValue()));
		}
		
		return storageDrumRecipes;
	}

	public static List<TransmutationRecipe> getTransmutationRecipes(){
		if(transmutationRecipes != null)
			return transmutationRecipes;
		transmutationRecipes = new ArrayList<TransmutationRecipe>();
		
		for(Map.Entry<AStack, ItemStack> entry : NuclearTransmutationRecipes.recipesOutput.entrySet()){
			transmutationRecipes.add(new TransmutationRecipe(entry.getKey().getStackList(), entry.getValue()));
		}
		
		return transmutationRecipes;
	}
	
	public static List<ItemStack> getReactorFuels(int heat){
		if(reactorFuelMap.containsKey(heat))
			return reactorFuelMap.get(heat);
		reactorFuelMap.put(heat, BreederRecipes.getAllFuelsFromHEAT(heat));
		return reactorFuelMap.get(heat);
	}
	

	public static List<RefineryRecipe> getRefineryRecipe() {
		if(refineryRecipes != null)
			return refineryRecipes;
		refineryRecipes = new ArrayList<RefineryRecipe>();
		
		for(FluidType fluid : RefineryRecipes.refinery.keySet()){
			FluidStack[] outputFluids = new FluidStack[]{RefineryRecipes.refinery.get(fluid).getX(), RefineryRecipes.refinery.get(fluid).getY(), RefineryRecipes.refinery.get(fluid).getV(), RefineryRecipes.refinery.get(fluid).getW()};
			ItemStack outputItem = RefineryRecipes.refinery.get(fluid).getZ();
			refineryRecipes.add(new RefineryRecipe(
					ItemFluidIcon.make(fluid, 1000),
					Arrays.asList(
						ItemFluidIcon.make(outputFluids[0].type, outputFluids[0].fill * 10),
						ItemFluidIcon.make(outputFluids[1].type, outputFluids[1].fill * 10),
						ItemFluidIcon.make(outputFluids[2].type, outputFluids[2].fill * 10),
						ItemFluidIcon.make(outputFluids[3].type, outputFluids[3].fill * 10),
						outputItem.copy()
					)
				)
			);
		}
		return refineryRecipes;
	}

	public static List<CrackingRecipe> getCrackingRecipe() {
		if(crackingRecipes != null)
			return crackingRecipes;
		crackingRecipes = new ArrayList<CrackingRecipe>();

		for (Entry<FluidType, Pair<com.hbm.inventory.fluid.FluidStack, com.hbm.inventory.fluid.FluidStack>> recipe : CrackRecipes.cracking.entrySet()) {
			List<ItemStack> inputs = Arrays.asList(
					ItemFluidIcon.make(recipe.getKey(), 100),
					ItemFluidIcon.make(Fluids.STEAM, 200)
			);

			List<ItemStack> outputs;
			if (recipe.getValue().getValue().type == Fluids.NONE) {
				outputs = Arrays.asList(
						ItemFluidIcon.make(recipe.getValue().getKey()),
						ItemFluidIcon.make(Fluids.SPENTSTEAM, 2)
				);
			} else {
				outputs = Arrays.asList(
						ItemFluidIcon.make(recipe.getValue().getKey()),
						ItemFluidIcon.make(recipe.getValue().getValue()),
						ItemFluidIcon.make(Fluids.SPENTSTEAM, 2)
				);
			}

			crackingRecipes.add(new CrackingRecipe(inputs, outputs));
		}

		return crackingRecipes;
	}

	public static List<FractioningRecipe> getFractioningRecipe() {
		if(fractioningRecipes != null)
			return fractioningRecipes;
		fractioningRecipes = new ArrayList<FractioningRecipe>();

		for(Entry<FluidType, Pair<FluidStack, FluidStack>> recipe : FractionRecipes.fractions.entrySet()) {
			List<ItemStack> out = new ArrayList<ItemStack>();
			out.add(ItemFluidIcon.make(recipe.getValue().getKey()));
			out.add(ItemFluidIcon.make(recipe.getValue().getValue()));

			fractioningRecipes.add(new FractioningRecipe(ItemFluidIcon.make(recipe.getKey(), 100), out));
		}
		return fractioningRecipes;
	}
	
	public static List<ItemStack> getBlades() {
		if(blades != null)
			return blades;
		
		blades = new ArrayList<ItemStack>();
		blades.add(new ItemStack(ModItems.blades_advanced_alloy));
		blades.add(new ItemStack(ModItems.blades_aluminum));
		blades.add(new ItemStack(ModItems.blades_combine_steel));
		blades.add(new ItemStack(ModItems.blades_gold));
		blades.add(new ItemStack(ModItems.blades_iron));
		blades.add(new ItemStack(ModItems.blades_steel));
		blades.add(new ItemStack(ModItems.blades_titanium));
		blades.add(new ItemStack(ModItems.blades_schrabidium));
		return blades;
	}
	
	public static List<FluidRecipe> getFluidEquivalences(){
		if(fluidEquivalences != null)
			return fluidEquivalences;
		fluidEquivalences = new ArrayList<FluidRecipe>();
		
		for(FluidType f : Fluids.getAll()){
			fluidEquivalences.add(new FluidRecipe(ItemFluidIcon.make(f, 1), ItemFluidTank.getFullTank(f)));
			fluidEquivalences.add(new FluidRecipeInverse(ItemFluidIcon.make(f, 1), ItemFluidTank.getFullTank(f)));

			fluidEquivalences.add(new FluidRecipe(ItemFluidIcon.make(f, 1), ItemFluidTank.getFullBarrel(f)));
			fluidEquivalences.add(new FluidRecipeInverse(ItemFluidIcon.make(f, 1), ItemFluidTank.getFullBarrel(f)));

			if(EnumCanister.contains(f)){
				fluidEquivalences.add(new FluidRecipe(ItemFluidIcon.make(f, 1), new ItemStack(ModItems.canister_generic, 1, f.getID())));
				fluidEquivalences.add(new FluidRecipeInverse(ItemFluidIcon.make(f, 1), new ItemStack(ModItems.canister_generic, 1, f.getID())));
			}
			if(EnumCell.contains(f)){
				fluidEquivalences.add(new FluidRecipe(ItemFluidIcon.make(f, 1), ItemCell.getFullCell(f)));
				fluidEquivalences.add(new FluidRecipeInverse(ItemFluidIcon.make(f, 1), ItemCell.getFullCell(f)));
			}
		}
		
		return fluidEquivalences;
	}
	
	public static List<FusionRecipe> getFusionByproducts(){
		if(fusionByproducts != null)
			return fusionByproducts;
		fusionByproducts = new ArrayList<>();
		fusionByproducts.add(new FusionRecipe(new FluidStack(Fluids.PLASMA_DT, 1), FusionRecipes.getByproduct(Fluids.PLASMA_DT)));
		fusionByproducts.add(new FusionRecipe(new FluidStack(Fluids.PLASMA_HD, 1), FusionRecipes.getByproduct(Fluids.PLASMA_HD)));
		fusionByproducts.add(new FusionRecipe(new FluidStack(Fluids.PLASMA_HT,1), FusionRecipes.getByproduct(Fluids.PLASMA_HT)));
		fusionByproducts.add(new FusionRecipe(new FluidStack(Fluids.PLASMA_XM, 1), FusionRecipes.getByproduct(Fluids.PLASMA_XM)));
		fusionByproducts.add(new FusionRecipe(new FluidStack(Fluids.PLASMA_DH3, 1), FusionRecipes.getByproduct(Fluids.PLASMA_DH3)));
		fusionByproducts.add(new FusionRecipe(new FluidStack(Fluids.PLASMA_BF, 1), FusionRecipes.getByproduct(Fluids.PLASMA_BF)));
		return fusionByproducts;
	}

	public static List<SAFERecipe> getSAFERecipes(){
		if(safeRecipes != null)
			return safeRecipes;
		safeRecipes = new ArrayList<>();
		for(Entry<ItemStack, ItemStack> recipe : com.hbm.inventory.SAFERecipes.getAllRecipes().entrySet()){
			safeRecipes.add(new SAFERecipe(recipe.getKey(), recipe.getValue()));
		}
		return safeRecipes;
	}
	
	public static List<HadronRecipe> getHadronRecipes(){
		if(hadronRecipes != null)
			return hadronRecipes;
		hadronRecipes = new ArrayList<>();
		for(com.hbm.inventory.HadronRecipes.HadronRecipe recipe : com.hbm.inventory.HadronRecipes.getRecipes()){
			hadronRecipes.add(new HadronRecipe(recipe.in1.toStack(), recipe.in2.toStack(), recipe.out1, recipe.out2, recipe.momentum, recipe.analysisOnly));
		}
		return hadronRecipes;
	}
	

	public static List<SILEXRecipe> getSILEXRecipes(EnumWavelengths wavelength){
		if(waveSilexRecipes.containsKey(wavelength))
			return waveSilexRecipes.get(wavelength);
		ArrayList wSilexRecipes = new ArrayList<>();
		for(Entry<List<ItemStack>, com.hbm.inventory.SILEXRecipes.SILEXRecipe> e : com.hbm.inventory.SILEXRecipes.getRecipes().entrySet()){
			com.hbm.inventory.SILEXRecipes.SILEXRecipe out = e.getValue();
			if(out.laserStrength == wavelength){
				double weight = 0;
				for(WeightedRandomObject obj : out.outputs) {
					weight += obj.itemWeight;
				}
				List<Double> chances = new ArrayList<>(out.outputs.size());
				List<ItemStack> outputs = new ArrayList<>(chances.size());
				for(int i = 0; i < out.outputs.size(); i++) {
					WeightedRandomObject obj = out.outputs.get(i);
					outputs.add(obj.asStack());
					chances.add(100 * obj.itemWeight / weight);
				}
				wSilexRecipes.add(new SILEXRecipe(e.getKey(), chances, outputs, (double)out.fluidProduced/out.fluidConsumed, out.laserStrength));
			}
		}
		waveSilexRecipes.put(wavelength, wSilexRecipes);
		return wSilexRecipes;
	}


	public static List<SILEXRecipe> getSILEXRecipes(){
		if(silexRecipes != null)
			return silexRecipes;
		silexRecipes = new ArrayList<>();
		for(Entry<List<ItemStack>, com.hbm.inventory.SILEXRecipes.SILEXRecipe> e : com.hbm.inventory.SILEXRecipes.getRecipes().entrySet()){
			com.hbm.inventory.SILEXRecipes.SILEXRecipe out = e.getValue();
			double weight = 0;
			for(WeightedRandomObject obj : out.outputs) {
				weight += obj.itemWeight;
			}
			List<Double> chances = new ArrayList<>(out.outputs.size());
			List<ItemStack> outputs = new ArrayList<>(chances.size());
			for(int i = 0; i < out.outputs.size(); i++) {
				WeightedRandomObject obj = out.outputs.get(i);
				outputs.add(obj.asStack());
				chances.add(100 * obj.itemWeight / weight);
			}
			silexRecipes.add(new SILEXRecipe(e.getKey(), chances, outputs, (double)out.fluidProduced/out.fluidConsumed, out.laserStrength));
		}
		return silexRecipes;
	}
	
	public static List<SmithingRecipe> getSmithingRecipes(){
		if(smithingRecipes != null)
			return smithingRecipes;
		smithingRecipes = new ArrayList<>();
		for(AnvilSmithingRecipe r : AnvilRecipes.getSmithing()){
			smithingRecipes.add(new SmithingRecipe(r.getLeft(), r.getRight(), r.getSimpleOutput(), r.tier));
		}
		return smithingRecipes;
	}
	
	public static List<AnvilRecipe> getAnvilRecipes(){
		if(anvilRecipes != null)
			return anvilRecipes;
		anvilRecipes = new ArrayList<>();
		for(AnvilConstructionRecipe r : AnvilRecipes.getConstruction()){
			List<List<ItemStack>> inputs = new ArrayList<>(r.input.size());
			List<ItemStack> outputs = new ArrayList<>(r.output.size());
			List<Float> chances = new ArrayList<>(r.output.size());
			for(AStack sta : r.input){
				inputs.add(sta.getStackList());
			}
			for(AnvilOutput sta : r.output){
				outputs.add(sta.stack.copy());
				chances.add(sta.chance);
			}
			anvilRecipes.add(new AnvilRecipe(inputs, outputs, chances, r.tierLower, r.tierUpper, r.getOverlay()));
 		}
		return anvilRecipes;
	}
}
