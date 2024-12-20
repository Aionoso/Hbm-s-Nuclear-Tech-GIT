package com.hbm.tileentity.machine.oil;

import com.hbm.blocks.BlockDummyable;
import com.hbm.blocks.ModBlocks;
import com.hbm.blocks.machine.MachinePumpjack;
import com.hbm.config.MachineConfig;
import com.hbm.entity.particle.EntityGasFX;
import com.hbm.forgefluid.FFUtils;
import com.hbm.items.ModItems;
import com.hbm.lib.Library;
import com.hbm.lib.ForgeDirection;
import com.hbm.packet.FluidTankPacket;
import com.hbm.packet.AuxElectricityPacket;
import com.hbm.packet.PacketDispatcher;
import com.hbm.packet.TEPumpjackPacket;

import net.minecraft.init.Blocks;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fml.common.network.NetworkRegistry.TargetPoint;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class TileEntityMachinePumpjack extends TileEntityOilDrillBase {

	public boolean isProgressing;
	public float rotation;
	public float prevRotation;

	public TileEntityMachinePumpjack() {
		super();
	}

	public String getInventoryName() {
		return this.hasCustomInventoryName() ? this.getCustomName() : "container.pumpjack";
	}

	@Override
    public long getMaxPower() {
        return 200000L;
    }

	@Override
	public void readFromNBT(NBTTagCompound compound) {
		this.rotation = compound.getFloat("rotation");
		super.readFromNBT(compound);
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		compound.setFloat("rotation", rotation);
		return super.writeToNBT(compound);
	}

	@SuppressWarnings("deprecation")
	@Override
	public void update() {
		int timer = MachineConfig.delayPerOperationPumpjack;
		prevRotation = rotation;
		age++;
		age2++;
		if(age >= timer)
			age -= timer;
		if(age2 >= 20)
			age2 -= 20;
		if(!world.isRemote) {
			this.updateConnections();
			int tank0Amount = tanks[0].getFluidAmount();
			int tank1Amount = tanks[1].getFluidAmount();
			if(age2 == 9 || age2 == 19) {
				fillFluidInit(tanks[0]);
				fillFluidInit(tanks[1]);
			}

			if(FFUtils.fillFluidContainer(inventory, tanks[0], 1, 2))
				needsUpdate = true;
			if(FFUtils.fillFluidContainer(inventory, tanks[1], 3, 4))
				needsUpdate = true;

			if(needsUpdate) {
				needsUpdate = false;
			}
			power = Library.chargeTEFromItems(inventory, 0, power, getMaxPower());

			if(power >= MachineConfig.powerConsumptionPerOperationPumpjack && !(tank0Amount >= tanks[0].getCapacity() || tank1Amount >= tanks[1].getCapacity())) {

				// operation start

				if(age == timer - 1) {
					warning = 0;

					// warning 0, green: derrick is operational
					// warning 1, red: derrick is full, has no power or the
					// drill is jammed
					// warning 2, yellow: drill has reached max depth

					for(int i = pos.getY() - 1; i > pos.getY() - 1 - 250; i--) {

						if(i <= 0) {
							// Code 2: The drilling ended
							warning = 2;
							break;
						}

						Block b = world.getBlockState(new BlockPos(pos.getX(), i, pos.getZ())).getBlock();
						if(b == ModBlocks.oil_pipe)
							continue;

						if((b.isReplaceable(world, new BlockPos(pos.getX(), i, pos.getZ())) || b.getExplosionResistance(null) < 1000) && !(b == ModBlocks.ore_oil || b == ModBlocks.ore_oil_empty || b == ModBlocks.ore_bedrock_oil)) {
							world.setBlockState(new BlockPos(pos.getX(), i, pos.getZ()), ModBlocks.oil_pipe.getDefaultState());

							// Code 2: The drilling ended
							if(i == pos.getY() - 250)
								warning = 2;
							break;

						} else if(this.tanks[0].getFluidAmount() < this.tanks[0].getCapacity() && this.tanks[1].getFluidAmount() < this.tanks[1].getCapacity()) {
							if(succ(pos.getX(), i, pos.getZ()) == 1) {

								int oilCollected = MachineConfig.oilPerDepositBlockMinPumpjack + ((MachineConfig.oilPerDepositBlockMaxExtraPumpjack > 0) ? world.rand.nextInt(MachineConfig.oilPerDepositBlockMaxExtraPumpjack) : 0);
								int gasCollected = MachineConfig.gasPerDepositBlockMinPumpjack + ((MachineConfig.gasPerDepositBlockMaxExtraPumpjack > 0) ? world.rand.nextInt(MachineConfig.gasPerDepositBlockMaxExtraPumpjack) : 0);

								this.tanks[0].fill(new FluidStack(tankTypes[0], oilCollected), true);
								this.tanks[1].fill(new FluidStack(tankTypes[1], gasCollected), true);
								needsUpdate = true;

								break;
							} else {
								warning = 2;
								break;
							}

						} else {
							// Code 1: Drill jammed
							warning = 1;
							break;
						}
					}
				}

				// operation end

				power -= MachineConfig.powerConsumptionPerOperationPumpjack;
			} else {
				warning = 1;
			}

			warning2 = 0;
			if(tanks[1].getFluidAmount() > 0) {
				if(inventory.getStackInSlot(5).getItem() == ModItems.fuse || inventory.getStackInSlot(5).getItem() == ModItems.screwdriver) {
					warning2 = 2;
					tanks[1].drain(50, true);
					needsUpdate = true;
					world.spawnEntity(new EntityGasFX(world, pos.getX() + 0.5F, pos.getY() + 6.5F, pos.getZ() + 0.5F, 0.0, 0.0, 0.0));
				} else {
					warning2 = 1;
				}
			}
			isProgressing = warning == 0;
			rotation += (warning == 0 ? 5 : 0);

			PacketDispatcher.wrapper.sendToAllAround(new TEPumpjackPacket(pos.getX(), pos.getY(), pos.getZ(), rotation, isProgressing), new TargetPoint(world.provider.getDimension(), pos.getX(), pos.getY(), pos.getZ(), 100));
			PacketDispatcher.wrapper.sendToAllAround(new AuxElectricityPacket(pos, power), new TargetPoint(world.provider.getDimension(), pos.getX(), pos.getY(), pos.getZ(), 10));
			PacketDispatcher.wrapper.sendToAllAround(new FluidTankPacket(pos, new FluidTank[] { tanks[0], tanks[1] }), new TargetPoint(world.provider.getDimension(), pos.getX(), pos.getY(), pos.getZ(), 10));
			if(tank0Amount != tanks[0].getFluidAmount() || tank1Amount != tanks[1].getFluidAmount()){
				markDirty();
			}
		}
	}

	protected void updateConnections() {
		ForgeDirection dir = ForgeDirection.getOrientation(world.getBlockState(pos).getValue(MachinePumpjack.FACING).ordinal());
		ForgeDirection rot = dir.getRotation(ForgeDirection.DOWN);
		
		this.trySubscribe(world, pos.getX() + rot.offsetX * 2 + dir.offsetX * 2, pos.getY(), pos.getZ() + rot.offsetZ * 2 + dir.offsetZ * 2, dir);
		this.trySubscribe(world, pos.getX() + rot.offsetX * 2 + dir.offsetX * 2, pos.getY(), pos.getZ() + rot.offsetZ * 4 - dir.offsetZ * 2, dir.getOpposite());
		this.trySubscribe(world, pos.getX() + rot.offsetX * 4 - dir.offsetX * 2, pos.getY(), pos.getZ() + rot.offsetZ * 4 + dir.offsetZ * 2, dir);
		this.trySubscribe(world, pos.getX() + rot.offsetX * 4 - dir.offsetX * 2, pos.getY(), pos.getZ() + rot.offsetZ * 2 - dir.offsetZ * 2, dir.getOpposite());
	}


	@Override
	public AxisAlignedBB getRenderBoundingBox() {
		return TileEntity.INFINITE_EXTENT_AABB;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public double getMaxRenderDistanceSquared()
	{
		return 65536.0D;
	}
	public void fillFluidInit(FluidTank tank) {
		
		EnumFacing e = world.getBlockState(pos).getValue(MachinePumpjack.FACING);
		e = e.rotateY();
		if(e == EnumFacing.EAST) {
			needsUpdate = FFUtils.fillFluid(this, tank, world, pos.add(-2, 0, 2), 2000) || needsUpdate;
			needsUpdate = FFUtils.fillFluid(this, tank, world, pos.add(-2, 0, -2), 2000) || needsUpdate;
			needsUpdate = FFUtils.fillFluid(this, tank, world, pos.add(-4, 0, 2), 2000) || needsUpdate;
			needsUpdate = FFUtils.fillFluid(this, tank, world, pos.add(-4, 0, -2), 2000) || needsUpdate;
		}
		if(e == EnumFacing.SOUTH) {
			needsUpdate = FFUtils.fillFluid(this, tank, world, pos.add(2, 0, -2), 2000) || needsUpdate;
			needsUpdate = FFUtils.fillFluid(this, tank, world, pos.add(-2, 0, -2), 2000) || needsUpdate;
			needsUpdate = FFUtils.fillFluid(this, tank, world, pos.add(2, 0, -4), 2000) || needsUpdate;
			needsUpdate = FFUtils.fillFluid(this, tank, world, pos.add(-2, 0, -4), 2000) || needsUpdate;
		}
		if(e == EnumFacing.WEST) {
			needsUpdate = FFUtils.fillFluid(this, tank, world, pos.add(2, 0, 2), 2000) || needsUpdate;
			needsUpdate = FFUtils.fillFluid(this, tank, world, pos.add(2, 0, -2), 2000) || needsUpdate;
			needsUpdate = FFUtils.fillFluid(this, tank, world, pos.add(4, 0, 2), 2000) || needsUpdate;
			needsUpdate = FFUtils.fillFluid(this, tank, world, pos.add(4, 0, -2), 2000) || needsUpdate;
		}
		if(e == EnumFacing.NORTH) {
			needsUpdate = FFUtils.fillFluid(this, tank, world, pos.add(2, 0, 2), 2000) || needsUpdate;
			needsUpdate = FFUtils.fillFluid(this, tank, world, pos.add(-2, 0, 2), 2000) || needsUpdate;
			needsUpdate = FFUtils.fillFluid(this, tank, world, pos.add(2, 0, 4), 2000) || needsUpdate;
			needsUpdate = FFUtils.fillFluid(this, tank, world, pos.add(-2, 0, 4), 2000) || needsUpdate;
		}
	}

	@Override
	public FluidStack drain(FluidStack resource, boolean doDrain) {
		if(resource == null){
			return null;
		} else if(resource.getFluid() == tankTypes[0]){
			return tanks[0].drain(resource.amount, doDrain);
		} else if(resource.getFluid() == tankTypes[1]){
			return tanks[1].drain(resource.amount, doDrain);
		} else {
			return null;
		}
	}

	@Override
	public FluidStack drain(int maxDrain, boolean doDrain) {
		if(tanks[0].getFluidAmount() > 0){
			return tanks[0].drain(maxDrain, doDrain);
		} else if(tanks[1].getFluidAmount() > 0){
			return tanks[1].drain(maxDrain, doDrain);
		} else {
			return null;
		}
	}
}