/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.amnesty.webservice.network;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.ejb.Stateless;
import javax.jws.WebParam;
import nl.amnesty.crm.collection.IdStartdateEnddate;
import nl.amnesty.crm.entity.Network;
import nl.amnesty.crm.persistence.EMNetwork;
import nl.amnesty.crm.persistence.EntityManager;

/**
 *
 * @author ed
 * algemene webservices uitgezet uit beveiligingsoogpunt
 */
@WebService()
@Stateless()
public class NetworkCRUD {

    /**
     * Web service operation
     */
    @WebMethod(operationName = "dummy")
    public String dummy() {
        //TODO write your implementation code here:
        return null;
    }

    /**
     * Web service operation
     *     private long networkid;
    private String type;
    private String description;
    private Collection<IdStartdateEnddate> idlist;

     
    @WebMethod(operationName = "create")
    public long create(@WebParam(name = "Networkid") long networkid,
            @WebParam(name = "Type") String type,
            @WebParam(name = "Description") String description,
            @WebParam(name = "Rolelist") List<IdStartdateEnddate> idlist) {
        try {
            Network network = new Network();
            network.setNetworkid(networkid);
            network.setType(type);
            network.setDescription(description);
            network.setIdlist(idlist);
            EntityManager em = new EMNetwork();
            network = em.persist(network);
            return network.getNetworkid();
        } catch (Exception e) {
            Logger.getLogger(NetworkCRUD.class.getName()).log(Level.SEVERE, null, e);
            return 0;
        }
    }
    */

    /**
     * Web service operation
    
    @WebMethod(operationName = "read")
    public Network read(@WebParam(name = "Networkid") long networkid) {
        Network network = new Network();
        try {
            EntityManager em = new EMNetwork();
            network = em.find(networkid);
        } catch (Exception e) {
            Logger.getLogger(NetworkCRUD.class.getName()).log(Level.SEVERE, null, e);
            return network;
        }
        return network;
    }
    */

    /**
     * Web service operation
     
    @WebMethod(operationName = "update")
    public long update(@WebParam(name = "Networkid") long networkid,
            @WebParam(name = "Type") String type,
            @WebParam(name = "Description") String description,
            @WebParam(name = "Rolelist") List<IdStartdateEnddate> idlist) {
        try {
            EntityManager em = new EMNetwork();
            Network network = em.find(networkid);
            if (network != null) {
                network.setType(type);
                network.setDescription(description);
            network.setDescription(description);
            network.setIdlist(idlist);

                if (em.merge(network)) {
                    return network.getNetworkid();
                } else {
                    return 0;
                }
            } else {
                return 0;
            }
        } catch (Exception e) {
            Logger.getLogger(NetworkCRUD.class.getName()).log(Level.SEVERE, null, e);
            return 0;
        }
    }
    */

    /**
     * Web service operation
     
    @WebMethod(operationName = "delete")
    public boolean delete(@WebParam(name = "Networkid") long networkid) {
        try {
            // Disable the removal of network as the administration department wants to control this themselves.
            return false;
            
            //EntityManager em = new EMNetwork();
            //if (em.remove(networkid)) {
            //    return true;
            //} else {
            //    return false;
            //}
            
        } catch (Exception e) {
            Logger.getLogger(NetworkCRUD.class.getName()).log(Level.SEVERE, null, e);
            return false;
        }
    }
    */
}
