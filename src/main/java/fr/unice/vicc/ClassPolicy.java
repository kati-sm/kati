
 /**
 * @author Nabil Benyahya & Kaouthar Azzouzi 
 * Master 2 RISM 
 * CERI
 */
package fr.unice.vicc;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.cloudbus.cloudsim.Host;
import org.cloudbus.cloudsim.Vm;
import org.cloudbus.cloudsim.VmAllocationPolicy;


public class ClassPolicy extends VmAllocationPolicy{
    private Map<String, Host> vmTable;
    public ClassPolicy(List<? extends Host> list) {
        super(list);
        vmTable=new HashMap<>();
    }

    @Override
    public boolean allocationPourVm(Vm vm) {
        for(Host host:getHostList()){
            if(host.vmCreate(vm)){
                vmTable.put(vm.getUid(), host);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean allocationPourVm(Vm vm, Host host) {
            if(host.vmCreate(vm)){
                vmTable.put(vm.getUid(), host);
                return true;
            }
            return false;
    }

    @Override
    public List<Map<String, Object>> optimizeAllocation(List<? extends Vm> list) {
        return null;
    }

    @Override
    public void deallocationPourVm(Vm vm) {
        vmTable.get(vm.getUid()).vmDestroy(vm);
    }

    @Override
    public Host getHost(Vm vm) {
        return vmTable.get(vm.getUid());
    }

    @Override
    public Host getHost(int i, int i1) {
        return vmTable.get(Vm.getUid(i, i1));
    }
    
}
