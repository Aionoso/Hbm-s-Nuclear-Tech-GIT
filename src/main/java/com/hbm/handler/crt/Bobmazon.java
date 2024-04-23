package com.hbm.handler.crt;

import com.hbm.handler.BobmazonOfferFactory;
import com.hbm.inventory.gui.GUIScreenBobmazon;
import crafttweaker.CraftTweakerAPI;
import crafttweaker.IAction;
import crafttweaker.annotations.ZenRegister;
import crafttweaker.api.item.IItemStack;
import crafttweaker.api.minecraft.CraftTweakerMC;
import net.minecraft.item.ItemStack;
import stanhebben.zenscript.annotations.Optional;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

import javax.annotation.Nullable;

import static net.minecraft.item.ItemStack.areItemStacksEqual;


@ZenRegister
@ZenClass("mods.ntm.Bobmazon")
public class Bobmazon {
	private static class ActionAddOffer implements IAction {

		ItemStack output;
		int cost;
		GUIScreenBobmazon.Requirement requirement;

		int book_type;

		public ActionAddOffer(IItemStack output, int cost, GUIScreenBobmazon.Requirement intToRequirement, int bookLevel) {
			this.output = CraftTweakerMC.getItemStack(output);
			this.cost = cost;
			this.requirement = intToRequirement;
			this.book_type = bookLevel;
		}

		@Override
		public void apply() {
			GUIScreenBobmazon.Offer offer =  new GUIScreenBobmazon.Offer(this.output,  this.requirement, this.cost);
			switch (this.book_type){
				case 0:
					BobmazonOfferFactory.materials.add(offer);
					break;
				case 1:
					BobmazonOfferFactory.machines.add(offer);
					break;
				case 2:
					BobmazonOfferFactory.weapons.add(offer);
					break;
				case 3:
					BobmazonOfferFactory.tools.add(offer);
					break;
				case 4:
					BobmazonOfferFactory.special.add(offer);
					break;
				default:
					CraftTweakerAPI.logError("Invalid book type: " + this.book_type);
					break;
			}

		}

		@Override
		public String describe() {
			return "add bobmazon offer for " + this.output + " with cost " + this.cost + " with requirement " + this.requirement + " with book type " + this.book_type;
		}
	}



	@Nullable
	public static GUIScreenBobmazon.Requirement intToRequirement(int type ){
		if (type >= 0 && type < GUIScreenBobmazon.Requirement.values().length) {
			return GUIScreenBobmazon.Requirement.values()[type];
		} else {
			return null;
		}
	}



	@ZenMethod
	public static void addOffer(IItemStack output, int cost, int requirement, int booktype){

		NTMCraftTweaker.postInitActions.add(new ActionAddOffer(output, cost, intToRequirement(requirement), booktype));

	}

	@ZenMethod
	public static void deleteOffer(IItemStack output){
		NTMCraftTweaker.postInitActions.add(new ActionDeleteOffer(output));
	}

	private static class ActionDeleteOffer implements IAction {

		ItemStack output;
		public ActionDeleteOffer(IItemStack output) {

			this.output = CraftTweakerMC.getItemStack(output);
		}

		@Override
		public void apply() {

			BobmazonOfferFactory.special.removeIf(o -> areItemStacksEqual(output, o.offer));

			BobmazonOfferFactory.tools.removeIf(o -> areItemStacksEqual(output, o.offer));

			BobmazonOfferFactory.weapons.removeIf(o -> areItemStacksEqual(output, o.offer));

			BobmazonOfferFactory.machines.removeIf(o -> areItemStacksEqual(output, o.offer));

			BobmazonOfferFactory.materials.removeIf(o -> areItemStacksEqual(output, o.offer));
		}

		@Override
		public String describe() {
			return "delete bobmazon offer for " + this.output;
		}
	}
}
