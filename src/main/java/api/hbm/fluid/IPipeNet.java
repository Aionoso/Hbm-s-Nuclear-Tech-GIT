package api.hbm.fluid;

import com.hbm.inventory.fluid.FluidType;
import net.minecraftforge.fluids.Fluid;

import java.math.BigInteger;
import java.util.HashSet;
import java.util.List;

public interface IPipeNet {

    public void joinNetworks(IPipeNet network);

    public List<IFluidConductor> getLinks();
    public HashSet<IFluidConnector> getSubscribers();

    public IPipeNet joinLink(IFluidConductor conductor);
    public void leaveLink(IFluidConductor conductor);

    public void subscribe(IFluidConnector connector);
    public void unsubscribe(IFluidConnector connector);
    public boolean isSubscribed(IFluidConnector connector);

    public void destroy();

    public boolean isValid();

    public long transferFluid(long fill, int pressure);
    public FluidType getType();
    public BigInteger getTotalTransfer();
}
