/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.amnesty.webservice.network;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import nl.amnesty.crm.config.NetworkDef;
import nl.amnesty.crm.entity.Network;
import nl.amnesty.crm.entity.Role;
import nl.amnesty.crm.persistence.EMNetwork;
import nl.amnesty.crm.persistence.EMRole;
import nl.amnesty.crm.util.IntUtil;
import nl.amnesty.sys.controller.CRMWebformController;
import nl.amnesty.sys.webform.entity.Form;
import nl.amnesty.sys.webform.entity.Property;

/**
 *
 * @author ed
 */
@WebService()
@Stateless()
public class NetworkExternal {

    /**
     * Web service operation
     */
    @WebMethod(operationName = "add")
    public long add(@WebParam(name = "networkname") String networkname,
            @WebParam(name = "sourcecode") String sourcecode,
            @WebParam(name = "roleid") long roleid) {
        try {
            if (IntUtil.isInteger(networkname)) {
                Form form = new Form();
                form.setId(Integer.valueOf(networkname));

                List<Property> propertylist = new ArrayList();
                form.setPropertylist(propertylist);

                if (CRMWebformController.processNetworkAdd(form, roleid)) {
                    return roleid;
                } else {
                    return 0;
                }
            } else {
                return 0;
            }
        } catch (Exception e) {
            Logger.getLogger(NetworkExternal.class.getName()).log(Level.SEVERE, e.getMessage(), e);
            return 0;
        }
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "addviaemail")
    public long addviaemail(@WebParam(name = "networkname") String networkname,
            @WebParam(name = "sourcecode") String sourcecode,
            @WebParam(name = "roleemail") String roleemail) {
        EMRole emrole = new EMRole();
        try {
            if (IntUtil.isInteger(networkname)) {
                Form form = new Form();
                form.setId(Integer.valueOf(networkname));

                // DEBUG
                List<Property> propertylist = new ArrayList();
                form.setPropertylist(propertylist);

                Role rolefound = emrole.roleReadViaEmail(roleemail.replace(";", "").replace("&", "").replace("%", ""));
                if (rolefound == null) {
                    return 0;
                }
                if (CRMWebformController.processNetworkAdd(form, rolefound.getRoleid())) {
                    return rolefound.getRoleid();
                } else {
                    return 0;
                }
            } else {
                return 0;
            }
        } catch (Exception e) {
            Logger.getLogger(NetworkExternal.class.getName()).log(Level.SEVERE, e.getMessage(), e);
            return 0;
        }
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "ispartof")
    public boolean ispartof(@WebParam(name = "networkname") String networkname,
            @WebParam(name = "roleemail") String roleemail) {
        EMRole emrole = new EMRole();
        try {

            //DEBUG
            //Logger.getLogger(NetworkExternal.class.getName()).log(Level.INFO, "Method ispartof: networkname is {0}", networkname);
            //Logger.getLogger(NetworkExternal.class.getName()).log(Level.INFO, "Method ispartof: roleemail is {0}", roleemail);

            // The network name should be non-numeric, three characters long
            if (!IntUtil.isInteger(networkname)) {
                if (networkname.length() == 3) {
                    if (roleemail==null) {
                        return false;
                    }
                    Role rolefound = emrole.roleReadViaEmail(roleemail.replace(";", "").replace("&", "").replace("%", ""));
                    if (rolefound == null) {
                        return false;
                    }

                    EMNetwork emnetwork = new EMNetwork();
                    java.net.URL urlconfignetwork = new java.net.URL(NetworkDef.URLCONFIGNETWORK);
                    return emnetwork.networkPartof(urlconfignetwork, networkname, rolefound.getRoleid());
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } catch (MalformedURLException ex) {
            Logger.getLogger(NetworkExternal.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } catch (Exception e) {
            Logger.getLogger(NetworkExternal.class.getName()).log(Level.SEVERE, e.getMessage(), e);
            return false;
        }
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "removeviaemail")
    public long removeviaemail(@WebParam(name = "networkname") String networkname,
            @WebParam(name = "roleemail") String roleemail) {
        EMRole emrole = new EMRole();
        try {
            if (IntUtil.isInteger(networkname)) {
                Form form = new Form();
                form.setId(Integer.valueOf(networkname));

                List<Property> propertylist = new ArrayList();
                form.setPropertylist(propertylist);

                Role rolefound = emrole.roleReadViaEmail(roleemail.replace(";", "").replace("&", "").replace("%", ""));
                if (rolefound == null) {
                    return 0;
                }
                if (CRMWebformController.processNetworkRemove(form, rolefound.getRoleid())) {
                    return rolefound.getRoleid();
                } else {
                    return 0;
                }
            } else {
                return 0;


            }
        } catch (Exception e) {
            Logger.getLogger(NetworkExternal.class.getName()).log(Level.SEVERE, e.getMessage(), e);

            return 0;
        }
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "blockviaemail")
    public long blockviaemail(@WebParam(name = "roleemail") String roleemail) {
        return 0;
    }
}
