package com.hbm.packet;

import com.hbm.main.MainRegistry;
import io.netty.buffer.ByteBuf;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class PlayerInformPacketLegacy implements IMessage {

    boolean fancy;
    private String dmesg = "";
    private int id;
    private ITextComponent component;
    private int millis = 0;

    public PlayerInformPacketLegacy() { }

    public PlayerInformPacketLegacy(String dmesg, int id) {
        this.fancy = false;
        this.dmesg = dmesg;
        this.id = id;
    }

    public PlayerInformPacketLegacy(ITextComponent component, int id) {
        this.fancy = true;
        this.component = component;
        this.id = id;
    }

    public PlayerInformPacketLegacy(String dmesg, int id, int millis) {
        this.fancy = false;
        this.dmesg = dmesg;
        this.millis = millis;
        this.id = id;
    }

    public PlayerInformPacketLegacy(ITextComponent component, int id, int millis) {
        this.fancy = true;
        this.component = component;
        this.millis = millis;
        this.id = id;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        id = buf.readInt();
        millis = buf.readInt();
        fancy = buf.readBoolean();

        if(!fancy) {
            dmesg = ByteBufUtils.readUTF8String(buf);
        } else {
            component = ITextComponent.Serializer.jsonToComponent(ByteBufUtils.readUTF8String(buf));
        }
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(id);
        buf.writeInt(millis);
        buf.writeBoolean(fancy);
        if(!fancy) {
            ByteBufUtils.writeUTF8String(buf, dmesg);
        } else {
            ByteBufUtils.writeUTF8String(buf, ITextComponent.Serializer.componentToJson(component));
        }
    }

    public static class Handler implements IMessageHandler<PlayerInformPacketLegacy, IMessage> {

        @Override
        @SideOnly(Side.CLIENT)
        public IMessage onMessage(PlayerInformPacketLegacy m, MessageContext ctx) {
            try {

                if(m.millis == 0)
                    MainRegistry.proxy.displayTooltipLegacy(m.fancy ? m.component.getFormattedText() : m.dmesg, m.id);
                else
                    MainRegistry.proxy.displayTooltipLegacy(m.fancy ? m.component.getFormattedText() : m.dmesg, m.millis, m.id);

            } catch (Exception x) { }
            return null;
        }
    }
}
