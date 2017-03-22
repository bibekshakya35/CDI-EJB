/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package np.com.drose.parkgarau.ws.resource;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import np.com.drose.parkgarau.api.ParkGarauService;
import np.com.drose.parkgarau.api.request.UserLoginRequest;
import np.com.drose.parkgarau.modules.AuditInfo;
import np.com.drose.parkgarau.modules.user.User;
import np.com.drose.parkgarau.modules.user.UserType;
import np.com.drose.parkgarau.modules.userprofile.UserProfile;
import np.com.drose.parkgarau.utils.JSONUtils;
import np.com.drose.parkgarau.ws.dto.UserDTO;
import np.com.drose.parkgarau.ws.resource.v1.ParkGarauWSAPI;
import np.com.drose.parkgarau.ws.resource.v1.response.Response;

/**
 * {Insert class description here}
 *
 * @version $Revision: 1.1.1.1 $
 * @since Build {insert version here} (MM YYYY)
 * @author bibek
 */
@Named
@RequestScoped
@Path("/park")
public class UserResource {

    private static final Logger LOG = Logger.getLogger(UserResource.class.getName());

    @Inject
    ParkGarauWSAPI parkGarauWSAPI;

    @Inject
    ParkGarauService<User> userGarauService;

    @Inject
    Instance<ParkGarauWSAPI> parkGarauWSAPIs;

    private ParkGarauWSAPI getParkGarauWSAPI(int version) {
        for (ParkGarauWSAPI parkGarauWs : parkGarauWSAPIs) {
            if (parkGarauWs.getVersion() == version) {
                return parkGarauWs;
            }
        }
        return null;
    }

    @Path("/login")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response authenticateUser(@HeaderParam("version") String version, UserLoginRequest userLoginRequest) {

        int ver = Integer.parseInt(version);
        parkGarauWSAPI = this.getParkGarauWSAPI(ver);
        LOG.log(Level.INFO, "version is matched......{0}", userLoginRequest.getCompanyCode());
        return this.parkGarauWSAPI.authenticateUser(userLoginRequest);
    }

    @Path("/userlist")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public javax.ws.rs.core.Response getUserList() {
        List<UserDTO> userDTOs = new ArrayList<>();
        userGarauService.getList().stream().forEach((user) -> {
            UserDTO userDTO = new UserDTO();
            userDTO.setUserName(user.getUserName());
            userDTO.setRoleId(user.getRoleId());
            userDTO.setEmailId(user.getUserProfile().getEmailId());
            userDTO.setMobileNumber(user.getUserProfile().getMobileNumber());
            userDTO.setLandLineNumber(user.getUserProfile().getLandLineNumber());
            userDTO.setPicture(user.getUserProfile().getPicture());
            userDTO.setFullName(user.getUserProfile().getFullname());
            userDTOs.add(userDTO);
        });
        return javax.ws.rs.core.Response.ok().entity(userDTOs).build();
    }

    @Path("/user")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response userAddOrDelete(UserDTO userDTO) {
        if (userGarauService.finder(userDTO.getUserName()) == null) {
            User user = new User();
            user.setUserName(userDTO.getUserName());
            user.setUserPassword("");
            user.setRoleId(userDTO.getRoleId());
            user.setAuditInfo(new AuditInfo());
            user.setUserType(UserType.SYSTEM);
            UserProfile userProfile = new UserProfile();
            userProfile.setFullname(userDTO.getFullName());
            userProfile.setLandLineNumber(userDTO.getLandLineNumber());
            userProfile.setPicture(userDTO.getPicture());
            user.setUserProfile(userProfile);
            userGarauService.add(user);
        } else {
            User user = new User();
            user.setUserName(userDTO.getUserName());
            user.setUserPassword("");
            user.setRoleId(userDTO.getRoleId());
            user.setAuditInfo(new AuditInfo());
            user.setUserType(UserType.SYSTEM);
            UserProfile userProfile = new UserProfile();
            userProfile.setFullname(userDTO.getFullName());
            userProfile.setLandLineNumber(userDTO.getLandLineNumber());
            userProfile.setPicture(userDTO.getPicture());
            user.setUserProfile(userProfile);
            userGarauService.add(user);
        }
        return new Response(200, "Successfully");
    }
}
