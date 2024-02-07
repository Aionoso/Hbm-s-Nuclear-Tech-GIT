package com.hbm.tileentity.machine.rbmk;

import com.hbm.blocks.ModBlocks;
import com.hbm.items.ModItems;
import com.hbm.blocks.BlockDummyable;
import com.hbm.items.machine.ItemForgeFluidIdentifier;
import com.hbm.entity.projectile.EntityRBMKDebris.DebrisType;
import com.hbm.packet.FluidTankPacket;
import com.hbm.packet.PacketDispatcher;
import com.hbm.interfaces.ITankPacketAcceptor;
import com.hbm.inventory.HeatRecipes;
import com.hbm.lib.ForgeDirection;
import com.hbm.lib.Library;
import com.hbm.forgefluid.FFUtils;
import com.hbm.forgefluid.ModForgeFluids;
import com.hbm.tileentity.machine.rbmk.TileEntityRBMKConsole.ColumnType;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.IFluidTankProperties;
import net.minecraftforge.fml.common.network.NetworkRegistry.TargetPoint;

import java.util.ArrayList;
import java.util.List;

public class TileEntityRBMKHeater extends TileEntityRBMKSlottedBase implements IFluidHandler, ITankPacketAcceptor {

	public static final double TU_PER_DEGREE = 3_000D; //based on 1mB of water absorbing 200 TU as well as 0.1°C from an RBMK column
	public FluidTank[] tanks;
	public Fluid[] tankTypes;
	
	public TileEntityRBMKHeater() {
		super(1);
		tanks = new FluidTank[2];
		tankTypes = new Fluid[2];

		tanks[0] = new FluidTank(ModForgeFluids.coolant, 0, 16000);
		tankTypes[0] = ModForgeFluids.coolant;
		tanks[1] = new FluidTank(ModForgeFluids.hotcoolant, 0, 16000);
		tankTypes[1] = ModForgeFluids.hotcoolant;
	}

	@Override
	public String getName() {
		return "container.rbmkHeater";
	}
	
	@Override
	public void update() {
		
		if(!world.isRemote) {
			setFluidType();
            PacketDispatcher.wrapper.sendToAllAround(new FluidTankPacket(pos, new FluidTank[] { tanks[0], tanks[1] }), new TargetPoint(world.provider.getDimension(), pos.getX(), pos.getY(), pos.getZ(), 50));

			if(HeatRecipes.hasBoilRecipe(tankTypes[0])) {
				Fluid hotFluid = HeatRecipes.getBoilFluid(tankTypes[0]);
				double tempRange = this.heat - (hotFluid.getTemperature()-273);
				if(tempRange > 0) {

					int heatReq = HeatRecipes.getRequiredHeat(tankTypes[0]);
					int inputAmount = HeatRecipes.getInputAmountHot(tankTypes[0]);
					int outputAmount = HeatRecipes.getOutputAmountHot(tankTypes[0]);
					
					int inputOps = tanks[0].getFluidAmount() / inputAmount;
					int outputOps = (tanks[1].getCapacity() - tanks[1].getFluidAmount()) / outputAmount;
					int tempOps = (int) Math.floor((tempRange * TU_PER_DEGREE) / heatReq);
					int ops = Math.min(inputOps, Math.min(outputOps, tempOps));

					tanks[0].drain(inputAmount * ops, true);
					tanks[1].fill(new FluidStack(tankTypes[1], outputAmount * ops), true);
					this.heat -= heatReq * ops / TU_PER_DEGREE;
				}
			}

			NBTTagCompound data = new NBTTagCompound();
            data.setTag("tanks", FFUtils.serializeTankArray(tanks));
            data.setString("tankTypes0", tankTypes[0].getName());
			data.setString("tankTypes1", tankTypes[1].getName());
            networkPack(data, 25);

			if(tanks[1].getFluidAmount() > 0){
				fillFluidInit(tanks[1]);
			}
		}
		
		super.update();
	}

	@Override
    public void networkUnpack(NBTTagCompound nbt) {
    	super.networkUnpack(nbt);
        if (nbt.hasKey("tanks")) {
            FFUtils.deserializeTankArray(nbt.getTagList("tanks", 10), tanks);
        }
        if(nbt.hasKey("tankTypes0")) tankTypes[0] = FluidRegistry.getFluid(nbt.getString("tankTypes0"));
		if(nbt.hasKey("tankTypes1")) tankTypes[1] = FluidRegistry.getFluid(nbt.getString("tankTypes1"));
    }

	public void setFluidType(){
		ItemStack inFluid = this.inventory.getStackInSlot(0);
        if(inFluid.getItem() == ModItems.forge_fluid_identifier) {
            setFluidTypes(ItemForgeFluidIdentifier.getType(inFluid));
        }
        if(tankTypes[0] == null) setFluidTypes(ModForgeFluids.coolant);
	}

	public void setFluidTypes(Fluid f){
		if(HeatRecipes.hasBoilRecipe(f) && tankTypes[0] != f) {
            tankTypes[0] = f;
            tankTypes[1] = HeatRecipes.getBoilFluid(f);
            // clear input tank fluid
            tanks[0].setFluid(new FluidStack(f, 0));
            tanks[1].setFluid(new FluidStack(tankTypes[1], 0));
            this.markDirty();
        }
	}

	public void fillFluidInit(FluidTank tank) {

		fillFluid(this.pos.getX(), this.pos.getY() + rbmkHeight + 1, this.pos.getZ(), tank);
		
		if(world.getBlockState(new BlockPos(pos.getX(), pos.getY() - 1, pos.getZ())).getBlock() == ModBlocks.rbmk_loader) {

			fillFluid(this.pos.getX() + 1, this.pos.getY() - 1, this.pos.getZ(), tank);
			fillFluid(this.pos.getX() - 1, this.pos.getY() - 1, this.pos.getZ(), tank);
			fillFluid(this.pos.getX(), this.pos.getY() - 1, this.pos.getZ() + 1, tank);
			fillFluid(this.pos.getX(), this.pos.getY() - 1, this.pos.getZ() - 1, tank);
			fillFluid(this.pos.getX(), this.pos.getY() - 2, this.pos.getZ(), tank);
		}
		
		if(world.getBlockState(new BlockPos(pos.getX(), pos.getY() - 2, pos.getZ())).getBlock() == ModBlocks.rbmk_loader) {

			fillFluid(this.pos.getX() + 1, this.pos.getY() - 2, this.pos.getZ(), tank);
			fillFluid(this.pos.getX() - 1, this.pos.getY() - 2, this.pos.getZ(), tank);
			fillFluid(this.pos.getX(), this.pos.getY() - 2, this.pos.getZ() + 1, tank);
			fillFluid(this.pos.getX(), this.pos.getY() - 2, this.pos.getZ() - 1, tank);
			fillFluid(this.pos.getX(), this.pos.getY() - 1, this.pos.getZ(), tank);
			fillFluid(this.pos.getX(), this.pos.getY() - 3, this.pos.getZ(), tank);
		}
	}

	public void fillFluid(int x, int y, int z, FluidTank tank) {
		FFUtils.fillFluid(this, tank, world, new BlockPos(x, y, z), tank.getCapacity());
	}

	public void getDiagData(NBTTagCompound nbt) {
		this.writeToNBT(nbt);
		nbt.removeTag("jumpheight");
	}
	
	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		
		tanks[0].readFromNBT(nbt.getCompoundTag("tanks0"));
		tanks[1].readFromNBT(nbt.getCompoundTag("tanks1"));
		if(nbt.hasKey("tankTypes0")) tankTypes[0] = FluidRegistry.getFluid(nbt.getString("tankTypes0"));
		if(nbt.hasKey("tankTypes1")) tankTypes[1] = FluidRegistry.getFluid(nbt.getString("tankTypes1"));
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		
		nbt.setTag("tanks0", tanks[0].writeToNBT(new NBTTagCompound()));
		nbt.setTag("tanks1", tanks[1].writeToNBT(new NBTTagCompound()));
		nbt.setString("tankTypes0", tankTypes[0].getName());
		nbt.setString("tankTypes1", tankTypes[1].getName());
		
		return nbt;
	}

	@Override
    public void recievePacket(NBTTagCompound[] tags) {
        if (tags.length != 2) {
            return;
        } else {
            tanks[0].readFromNBT(tags[0]);
            tanks[1].readFromNBT(tags[1]);
        }
    }
	
	@Override
	public void onMelt(int reduce) {
		
		int count = 1 + world.rand.nextInt(2);
		
		for(int i = 0; i < count; i++) {
			spawnDebris(DebrisType.BLANK);
		}
		
		super.onMelt(reduce);
	}

	@Override
	public ColumnType getConsoleType() {
		return ColumnType.HEATEX;
	}

	@Override
	public NBTTagCompound getNBTForConsole() {
		NBTTagCompound data = new NBTTagCompound();
		data.setInteger("inputFluidAmount", this.tanks[0].getFluidAmount());
		data.setInteger("inputFluidMax", this.tanks[0].getCapacity());
		data.setInteger("outputFluidAmount", this.tanks[1].getFluidAmount());
		data.setInteger("outputFluidMax", this.tanks[1].getCapacity());
		data.setString("inputFluid", this.tankTypes[0].getLocalizedName(new FluidStack(this.tankTypes[0], 0)));
		data.setString("outputFluid", this.tankTypes[1].getLocalizedName(new FluidStack(this.tankTypes[1], 0)));
		return data;
	}

	@Override
	public IFluidTankProperties[] getTankProperties(){
		return new IFluidTankProperties[]{tanks[0].getTankProperties()[0], tanks[1].getTankProperties()[0]};
	}

	@Override
	public int fill(FluidStack resource, boolean doFill){
		if(resource != null && resource.amount > 0 && resource.getFluid() == tankTypes[0] && HeatRecipes.hasBoilRecipe(resource.getFluid())){
			return tanks[0].fill(resource, doFill);
		}
		return 0;
	}

	@Override
	public FluidStack drain(FluidStack resource, boolean doDrain){
		if(resource != null && resource.amount > 0 && resource.getFluid() == tankTypes[1]){
			return tanks[1].drain(resource, doDrain);
		}
		return null;
	}

	@Override
	public FluidStack drain(int maxDrain, boolean doDrain){
		if (maxDrain > 0) {
            return tanks[1].drain(maxDrain, doDrain);
        }
        return null;
	}
	
	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing){
		return capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY || super.hasCapability(capability, facing);
	}
	
	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing){
		if(capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY)
			return CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY.cast(this);
		return super.getCapability(capability, facing);
	}
}
