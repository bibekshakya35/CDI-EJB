/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package np.com.drose.parkgarau.ws.resource.v1.impl;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;
import np.com.drose.parkgarau.api.ParkGarauService;
import np.com.drose.parkgarau.api.dto.VehicleEntry;

import np.com.drose.parkgarau.api.request.DeviceRegistrationRequest;
import np.com.drose.parkgarau.api.request.SyncCategoryRequest;
import np.com.drose.parkgarau.api.request.UserLoginRequest;
import np.com.drose.parkgarau.api.request.VehicleInRequest;
import np.com.drose.parkgarau.api.response.SyncCategoryResponse;
import np.com.drose.parkgarau.api.response.UserLoginResponse;
import np.com.drose.parkgarau.modules.AuditInfo;
import np.com.drose.parkgarau.modules.company.Company;
import np.com.drose.parkgarau.modules.company.CompanyBranch;
import np.com.drose.parkgarau.modules.device.Device;
import np.com.drose.parkgarau.modules.parkingmanagement.ParkingCharge;
import np.com.drose.parkgarau.modules.parkingmanagement.ParkingChargeSetup;
import np.com.drose.parkgarau.modules.parkingmanagement.VehicleCategory;
import np.com.drose.parkgarau.modules.parkingmanagement.VehicleSubCategory;
import np.com.drose.parkgarau.modules.token.Token;
import np.com.drose.parkgarau.modules.user.User;
import np.com.drose.parkgarau.service.user.control.UserVerification;
import np.com.drose.parkgarau.utils.JSONUtils;

import np.com.drose.parkgarau.ws.resource.v1.ParkGarauWSAPI;
import np.com.drose.parkgarau.ws.resource.v1.httpstatuscode.HTTPStatusCode;
import np.com.drose.parkgarau.ws.resource.v1.response.Response;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;

/**
 * {Insert class description here}
 *
 * @version $Revision: 1.1.1.1 $
 * @since Build {insert version here} (MM YYYY)
 * @author bibek
 */
@Named
@RequestScoped
public class ParkGarauWSIMPL implements ParkGarauWSAPI {

    @Inject
    ParkGarauService<Device> deviceParkGarauService;
    @Inject
    ParkGarauService<Company> companyParkGarauService;

    @Inject
    ParkGarauService<User> userParkGarauService;

    @Inject
    UserVerification userVerification;

    @Inject
    ParkGarauService<Token> tokenParkGarauService;
    
    @Inject
    ParkGarauService<CompanyBranch> companyBrancParkGarauService;

    @Inject
    ParkGarauService<VehicleCategory> vehicleCategoryParkGarauService;
    @Inject
    ParkGarauService<VehicleSubCategory> vehicleSubParkGarauService;
    @Inject
    ParkGarauService<np.com.drose.parkgarau.modules.parkingmanagement.VehicleEntry> entryParkGarauService;

    @Inject
    ParkGarauService<ParkingCharge> parkingChargeService;
    @Inject
    ParkGarauService<ParkingChargeSetup> parkCSGarauService;
        
    @Override
    public int getVersion() {
        return 1;
    }
    private static final Logger LOG = Logger.getLogger(ParkGarauWSIMPL.class.getName());

    @Override
    public Response registerDevice(DeviceRegistrationRequest deviceRegistrationRequest) {
        LOG.info("inside device registration->>>>>>>->>>>>>>>>>>");
        if (StringUtils.isEmpty(deviceRegistrationRequest.getDeviceId()) && StringUtils.isEmpty(deviceRegistrationRequest.getCompanyCode())) {
            LOG.info("value is empty");
            return new Response(HTTPStatusCode.BADREQUEST.getHttpcode(), "Sorry!! Empty Device ID and Company Code");
        }
        LOG.log(Level.INFO, "HERE IS THE VALUE ->>>>>>{0}: {1}", new Object[]{deviceRegistrationRequest.getCompanyCode(), deviceRegistrationRequest.getDeviceId()});
        if (companyParkGarauService.finder(deviceRegistrationRequest.getCompanyCode()) == null) {
            LOG.info("inside the finder--------->");
            return new Response(HTTPStatusCode.UNAUTHORIZED.getHttpcode(), "Sorry !! invalid company Code");
        }
        if (deviceParkGarauService.finder(deviceRegistrationRequest.getDeviceId()) != null) {
            return new Response(HTTPStatusCode.UNAUTHORIZED.getHttpcode(), "Device Already Register");
        }
        Device device = new Device();
        device.setDeviceCode(deviceRegistrationRequest.getDeviceId());
        device.setDeviceType("TAB");
        device.setModel("HUWAI");
        device.setDescription("cool");
        device.setCompanyCode(deviceRegistrationRequest.getCompanyCode());
        device.setAuditInfo(new AuditInfo(new Date(), "Bibek Shakya", "121211", new Date(), "", new Date()));
        this.deviceParkGarauService.add(device);
        return new Response(HTTPStatusCode.OK.getHttpcode(), "Successful", JSONUtils.objectToJson(new np.com.drose.parkgarau.ws.resource.v1.response.DeviceRegistrationResponse(device.getCompanyCode(), this.companyParkGarauService.finder(device.getCompanyCode()).getProductName())));
    }
    //@Context HttpServletRequest httpServletRequest;
    @Override
    public Response authenticateUser(UserLoginRequest userLoginRequest) {
        UserLoginResponse userLoginResponse = new UserLoginResponse();
        User validateUser = userVerification.validateUser(userLoginRequest.getUsername(), userLoginRequest.getPassword());
        //authenticate user
        if (validateUser == null) {
            return new Response(HTTPStatusCode.UNAUTHORIZED.getHttpcode(), "Sorry, Invalid username and password");

        }
        userLoginResponse.setFullName(validateUser.getUserProfile().getFullname());
        userLoginResponse.setSessionKey(issueToken(validateUser.getUserName()));
        userLoginResponse.setBranchName("dm");
        if (StringUtils.isNotBlank(validateUser.getUserName()) || validateUser.getUserName() != null) {
           // httpServletRequest.getSession(true);
            return new Response(HTTPStatusCode.OK.getHttpcode(), "Successfully Login", JSONUtils.objectToJson(userLoginResponse));
        }
        return new Response(HTTPStatusCode.UNAUTHORIZED.getHttpcode(), "Sorry, Invalid username and password");
    }

    private String issueToken(String userName) {
        Optional<Token> tkn1 = Optional.ofNullable(tokenParkGarauService.findWithAnotherObjectCode(userName));
        if (tkn1.isPresent()) {
            Token tkn = tkn1.get();
            tkn.setActive(false);
            tkn.getAuditInfo().setModifiedOn(new Date());
            tkn.getAuditInfo().setModifiedBy(userName);
            tokenParkGarauService.edit(tkn);
        }
        //String token = UUID.randomUUID().toString();
        Random random = new SecureRandom();
        String token = new BigInteger(130, random).toString(32);
        LOG.log(Level.INFO, "ISSUE TOKEN .....{0}", token);
        this.tokenParkGarauService.add(new Token(userName, token, DateUtils.addHours(new Date(), 12), new AuditInfo()));
        LOG.log(Level.INFO, "successfully inserted token {0}", userName);
        return token;
    }

    @Override
    public Response syncCategory(SyncCategoryRequest syncCategoryRequest) {
        if (this.deviceParkGarauService.finder(syncCategoryRequest.getDeviceId()) != null) {
            if (this.companyParkGarauService.finder(syncCategoryRequest.getCompanyCode()) != null) {
                List<VehicleCategory> vehicleCategorys = vehicleCategoryParkGarauService.getList();

                SyncCategoryResponse syncCategoryResponse = new SyncCategoryResponse();
                List<np.com.drose.parkgarau.api.dto.VehicleCategory> vehicleCategorys1 = new ArrayList<>();

                vehicleCategorys.stream().forEach((vc) -> {

                    np.com.drose.parkgarau.api.dto.VehicleCategory vehicleCategory = new np.com.drose.parkgarau.api.dto.VehicleCategory();
                    vehicleCategory.setCode(vc.getCode());
                    vehicleCategory.setDescription(vc.getDescription());
                    vehicleCategory.setName(vc.getName());
                    List<np.com.drose.parkgarau.api.dto.VehicleSubCategory> vehicleSubCategorys1 = new ArrayList<>();
                    vehicleSubParkGarauService.findList(vc.getCode()).stream().forEach((vsc) -> {
                        np.com.drose.parkgarau.api.dto.VehicleSubCategory vehicleSubCategory = new np.com.drose.parkgarau.api.dto.VehicleSubCategory();
                        vehicleSubCategory.setCode(vsc.getCode());
                        vehicleSubCategory.setDescription(vsc.getDescription());
                        vehicleSubCategory.setName(vsc.getName());
                        vehicleSubCategory.setVehicleCategoryCode(vc.getCode());
                        ParkingChargeSetup parkingChargeSetup = parkCSGarauService.finder(parkingChargeService.finder(vehicleSubCategory.getCode()).getParkingChargeSetupCode());
                        if (parkingChargeSetup!=null) {
                           
                        }
                        vehicleSubCategorys1.add(vehicleSubCategory);
                    });

                    vehicleCategory.setVehicleSubCategory(vehicleSubCategorys1);
                    vehicleCategorys1.add(vehicleCategory);
                });

                syncCategoryResponse.setVehicleCategoryList(vehicleCategorys1);
                return new Response(HTTPStatusCode.OK.getHttpcode(), "List of Sync Category!!!", JSONUtils.objectToJson(syncCategoryResponse));
            } else {
                return new Response(HTTPStatusCode.BADREQUEST.getHttpcode(), "Company is not found");
            }
        } else {
            return new Response(HTTPStatusCode.BADREQUEST.getHttpcode(), "Device is not registered");
        }
    }

    @Override
    public Response vehicleIn(VehicleInRequest vehicleInRequest) {
        VehicleEntry vehicleEntryDTO = vehicleInRequest.getVehicleIn();
        np.com.drose.parkgarau.modules.parkingmanagement.VehicleEntry vehicleEntry = new np.com.drose.parkgarau.modules.parkingmanagement.VehicleEntry(
                vehicleEntryDTO.getId(),
                vehicleEntryDTO.getEntredOn(), vehicleEntryDTO.getVehicleNumber(), vehicleEntryDTO.getVehicleSubCategory(), vehicleEntryDTO.getEnteredBy(), new AuditInfo()
        );
        LOG.log(Level.INFO, "VehicleEntry ready to persist on database{0}", vehicleEntry.toString());
        this.entryParkGarauService.add(vehicleEntry);
        LOG.info("Successfull on adding record");
        return new Response(HTTPStatusCode.OK.getHttpcode(), "Successfully stored in database");
    }

   

}
