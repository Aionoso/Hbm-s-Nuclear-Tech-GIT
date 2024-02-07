package com.hbm.packet;

import com.hbm.lib.RefStrings;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

public class PacketDispatcher {
	
	public static final SimpleNetworkWrapper wrapper = NetworkRegistry.INSTANCE.newSimpleChannel(RefStrings.MODID);
	
	public static final void registerPackets(){
		int i = 0;
		//PressPacket
		wrapper.registerMessage(TEPressPacket.Handler.class, TEPressPacket.class, i++, Side.CLIENT);
		//Send chunk radiation packet to individual players
		wrapper.registerMessage(SurveyPacket.Handler.class, SurveyPacket.class, i++, Side.CLIENT);
		//Packet for rendering of rubble
		wrapper.registerMessage(ParticleBurstPacket.Handler.class, ParticleBurstPacket.class, i++, Side.CLIENT);
		//Packet for updating assembler progress
		wrapper.registerMessage(TEAssemblerPacket.Handler.class, TEAssemblerPacket.class, i++, Side.CLIENT);
		//Updates electricity data
		wrapper.registerMessage(AuxElectricityPacket.Handler.class, AuxElectricityPacket.class, i++, Side.CLIENT);
		//Sounds packets
		wrapper.registerMessage(LoopedSoundPacket.Handler.class, LoopedSoundPacket.class, i++, Side.CLIENT);
		//Particle packet
		wrapper.registerMessage(AuxParticlePacket.Handler.class, AuxParticlePacket.class, i++, Side.CLIENT);
		//Chemplant packet
		wrapper.registerMessage(TEChemplantPacket.Handler.class, TEChemplantPacket.class, i++, Side.CLIENT);
		//Syncing fluid tanks
		wrapper.registerMessage(FluidTankPacket.Handler.class, FluidTankPacket.class, i++, Side.CLIENT);
		//Assembler Recipe Sync Packet, so clients can see the right recipes
		wrapper.registerMessage(AssemblerRecipeSyncPacket.Handler.class, AssemblerRecipeSyncPacket.class, i++, Side.CLIENT);
		//Universal package for machine gauges and states
		wrapper.registerMessage(AuxGaugePacket.Handler.class, AuxGaugePacket.class, i++, Side.CLIENT);
		//Universal package for machine gauges and states but for longs
		wrapper.registerMessage(AuxLongPacket.Handler.class, AuxLongPacket.class, i++, Side.CLIENT);
		//Universal button packet
		wrapper.registerMessage(AuxButtonPacket.Handler.class, AuxButtonPacket.class, i++, Side.SERVER);
		//For handling fluid tank type updates
		wrapper.registerMessage(FluidTypePacketTest.Handler.class, FluidTypePacketTest.class, i++, Side.CLIENT);
		//Fluid pipe type update for rendering
		wrapper.registerMessage(TEFluidTypePacketTest.Handler.class, TEFluidTypePacketTest.class, i++, Side.CLIENT);
		//Turret basic packet for making the client has the right ammo amounts
		wrapper.registerMessage(TETurretPacket.Handler.class, TETurretPacket.class, i++, Side.CLIENT);
		//CIWS has a really long range, so stuff might not even exist on client, so rotation needs to be sent
		wrapper.registerMessage(TETurretCIWSPacket.Handler.class, TETurretCIWSPacket.class, i++, Side.CLIENT);
		//Packet for updating the bomber flying sound, I think
		wrapper.registerMessage(LoopedEntitySoundPacket.Handler.class, LoopedEntitySoundPacket.class, i++, Side.CLIENT);
		//Missile type for render sync with client
		wrapper.registerMessage(TEMissilePacket.Handler.class, TEMissilePacket.class, i++, Side.CLIENT);
		//Packet for sending designator data to server
		wrapper.registerMessage(ItemDesignatorPacket.Handler.class, ItemDesignatorPacket.class, i++, Side.SERVER);
		//New particle packet
		wrapper.registerMessage(EnumParticlePacket.Handler.class, EnumParticlePacket.class, i++, Side.CLIENT);
		//Gun firing packet
		wrapper.registerMessage(GunButtonPacket.Handler.class, GunButtonPacket.class, i++, Side.SERVER);
		//Packet sent for every connected electricity pole, for wire rendering
		wrapper.registerMessage(TEPylonSenderPacket.Handler.class, TEPylonSenderPacket.class, i++, Side.CLIENT);
		//Resets connection list in client-sided pole rendering
		wrapper.registerMessage(TEPylonDestructorPacket.Handler.class, TEPylonDestructorPacket.class, i++, Side.CLIENT);
		//Sets railgun rotation so it updates on client
		wrapper.registerMessage(RailgunRotationPacket.Handler.class, RailgunRotationPacket.class, i++, Side.CLIENT);
		// <Insert good comment here>
		wrapper.registerMessage(RailgunCallbackPacket.Handler.class, RailgunCallbackPacket.class, i++, Side.CLIENT);
		// Sets last fire time for railgun
		wrapper.registerMessage(RailgunFirePacket.Handler.class, RailgunFirePacket.class, i++, Side.CLIENT);
		//Siren packet for looped sounds
		wrapper.registerMessage(TESirenPacket.Handler.class, TESirenPacket.class, i++, Side.CLIENT);
		//Door packet for animations and stuff
		wrapper.registerMessage(TEVaultPacket.Handler.class, TEVaultPacket.class, i++, Side.CLIENT);
		//Information packet for the reactor control block
		wrapper.registerMessage(TEControlPacket.Handler.class, TEControlPacket.class, i++, Side.CLIENT);
		//Pumpjack rotation packet
		wrapper.registerMessage(TEPumpjackPacket.Handler.class, TEPumpjackPacket.class, i++, Side.CLIENT);
		//Mining drill rotation for rendering
		wrapper.registerMessage(TEDrillPacket.Handler.class, TEDrillPacket.class, i++, Side.CLIENT);
		//Turbofan spin for rendering
		wrapper.registerMessage(TETurbofanPacket.Handler.class, TETurbofanPacket.class, i++, Side.CLIENT);
		//Machine type for marker rendering
		wrapper.registerMessage(TEStructurePacket.Handler.class, TEStructurePacket.class, i++, Side.CLIENT);
		//Mega packet for large reactor so it doesn't use quite as much network traffic
		wrapper.registerMessage(LargeReactorPacket.Handler.class, LargeReactorPacket.class, i++, Side.CLIENT);
		//Packet to send missile multipart information to TEs
		wrapper.registerMessage(TEMissileMultipartPacket.Handler.class, TEMissileMultipartPacket.class, i++, Side.CLIENT);
		//Signals server to consume items and create template
		wrapper.registerMessage(ItemFolderPacket.Handler.class, ItemFolderPacket.class, i++, Side.SERVER);
		//Signals server to buy offer from bobmazon
		wrapper.registerMessage(ItemBobmazonPacket.Handler.class, ItemBobmazonPacket.class, i++, Side.SERVER);
		//Update packet for force field
		wrapper.registerMessage(TEFFPacket.Handler.class, TEFFPacket.class, i++, Side.CLIENT);
		//Siren packet for looped sounds Drillgon200: Wrong descriptions, but eh, whatever.
		wrapper.registerMessage(TERadarPacket.Handler.class, TERadarPacket.class, i++, Side.CLIENT);
		//Siren packet for looped sounds
		//wrapper.registerMessage(TERadarDestructorPacket.Handler.class, TERadarDestructorPacket.class, i++, Side.CLIENT);
		//Packet for causing pipes to rebuild their connections
		wrapper.registerMessage(PipeUpdatePacket.Handler.class, PipeUpdatePacket.class, i++, Side.CLIENT);
		//Packet for updating entities being zapped
		wrapper.registerMessage(TETeslaPacket.Handler.class, TETeslaPacket.class, i++, Side.CLIENT);
		//Aux Particle Packet, New Technology: like the APP but with NBT
		wrapper.registerMessage(AuxParticlePacketNT.Handler.class, AuxParticlePacketNT.class, i++, Side.CLIENT);
		//Packet to send NBT data to tile entities
		wrapper.registerMessage(NBTPacket.Handler.class, NBTPacket.class, i++, Side.CLIENT);
		//Packet to send sat info to players
		wrapper.registerMessage(SatPanelPacket.Handler.class, SatPanelPacket.class, i++, Side.CLIENT);
		//Signals server to do coord based satellite stuff
		wrapper.registerMessage(SatCoordPacket.Handler.class, SatCoordPacket.class, i++, Side.SERVER);
		//Signals server to perform orbital strike, among other things
		wrapper.registerMessage(SatLaserPacket.Handler.class, SatLaserPacket.class, i++, Side.SERVER);
		//Sets the gun animation on server because there is no client side nbt tag
		wrapper.registerMessage(SetGunAnimPacket.Handler.class, SetGunAnimPacket.class, i++, Side.SERVER);
		//Triggers gun animations of the client
		wrapper.registerMessage(GunAnimationPacket.Handler.class, GunAnimationPacket.class, i++, Side.CLIENT);
		//Unhooks the entity when the player jumps
		wrapper.registerMessage(MeathookJumpPacket.Handler.class, MeathookJumpPacket.class, i++, Side.SERVER);
		//Resets any sideways acceleration when the meathook unhooks
		wrapper.registerMessage(MeathookResetStrafePacket.Handler.class, MeathookResetStrafePacket.class, i++, Side.CLIENT);
		//Gernal packet for sending door states
		wrapper.registerMessage(TEDoorAnimationPacket.Handler.class, TEDoorAnimationPacket.class, i++, Side.CLIENT);
		//Packets for syncing the keypad
		wrapper.registerMessage(KeypadServerPacket.Handler.class, KeypadServerPacket.class, i++, Side.SERVER);
		wrapper.registerMessage(KeypadClientPacket.Handler.class, KeypadClientPacket.class, i++, Side.CLIENT);
		//Sends a funi text to display like a music disc announcement
		wrapper.registerMessage(PlayerInformPacket.Handler.class, PlayerInformPacket.class, i++, Side.CLIENT);
		//Activates particle effects or animations without the need for an entity
		wrapper.registerMessage(GunFXPacket.Handler.class, GunFXPacket.class, i++, Side.CLIENT);
		//Handles custom death animations (like the gluon gun disintegration effect)
		wrapper.registerMessage(PacketSpecialDeath.Handler.class, PacketSpecialDeath.class, i++, Side.CLIENT);
		//Universal keybind packet
		wrapper.registerMessage(KeybindPacket.Handler.class, KeybindPacket.class, i++, Side.SERVER);
		//DELETE LATER
		//wrapper.registerMessage(PacketCreatePhysTree.Handler.class, PacketCreatePhysTree.class, i++, Side.CLIENT);
		//To tell the server to cut a mob for the cutting swords
		wrapper.registerMessage(PacketMobSlicer.Handler.class, PacketMobSlicer.class, i++, Side.SERVER);
		//Sync packet for jetpack data
		wrapper.registerMessage(JetpackSyncPacket.Handler.class, JetpackSyncPacket.class, i++, Side.SERVER);
		wrapper.registerMessage(JetpackSyncPacket.Handler.class, JetpackSyncPacket.class, i-1, Side.CLIENT);
		wrapper.registerMessage(ExtPropPacket.Handler.class, ExtPropPacket.class, i++, Side.CLIENT);
		wrapper.registerMessage(NBTControlPacket.Handler.class, NBTControlPacket.class, i++, Side.SERVER);
		wrapper.registerMessage(AnvilCraftPacket.Handler.class, AnvilCraftPacket.class, i++, Side.SERVER);
		wrapper.registerMessage(ControlPanelUpdatePacket.Handler.class, ControlPanelUpdatePacket.class, i++, Side.CLIENT);
	}
	
	public static void sendTo(IMessage message, EntityPlayerMP player){
		if(player != null)
			wrapper.sendTo(message, player);
	}

}
