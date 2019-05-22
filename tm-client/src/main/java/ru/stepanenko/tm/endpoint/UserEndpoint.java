package ru.stepanenko.tm.endpoint;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.Action;
import javax.xml.ws.FaultAction;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 * This class was generated by Apache CXF 3.2.7
 * 2019-05-22T19:06:16.726+03:00
 * Generated source version: 3.2.7
 *
 */
@WebService(targetNamespace = "http://endpoint.tm.stepanenko.ru/", name = "UserEndpoint")
@XmlSeeAlso({ObjectFactory.class})
public interface UserEndpoint {

    @WebMethod
    @Action(input = "http://endpoint.tm.stepanenko.ru/UserEndpoint/removeOneUserRequest", output = "http://endpoint.tm.stepanenko.ru/UserEndpoint/removeOneUserResponse", fault = {@FaultAction(className = AuthenticationSecurityException_Exception.class, value = "http://endpoint.tm.stepanenko.ru/UserEndpoint/removeOneUser/Fault/AuthenticationSecurityException"), @FaultAction(className = DataValidateException_Exception.class, value = "http://endpoint.tm.stepanenko.ru/UserEndpoint/removeOneUser/Fault/DataValidateException")})
    @RequestWrapper(localName = "removeOneUser", targetNamespace = "http://endpoint.tm.stepanenko.ru/", className = "ru.stepanenko.tm.endpoint.RemoveOneUser")
    @ResponseWrapper(localName = "removeOneUserResponse", targetNamespace = "http://endpoint.tm.stepanenko.ru/", className = "ru.stepanenko.tm.endpoint.RemoveOneUserResponse")
    public void removeOneUser(
        @WebParam(name = "session", targetNamespace = "")
        ru.stepanenko.tm.endpoint.SessionDTO session,
        @WebParam(name = "id", targetNamespace = "")
        java.lang.String id
    ) throws AuthenticationSecurityException_Exception, DataValidateException_Exception;

    @WebMethod
    @Action(input = "http://endpoint.tm.stepanenko.ru/UserEndpoint/findUserByLoginRequest", output = "http://endpoint.tm.stepanenko.ru/UserEndpoint/findUserByLoginResponse", fault = {@FaultAction(className = AuthenticationSecurityException_Exception.class, value = "http://endpoint.tm.stepanenko.ru/UserEndpoint/findUserByLogin/Fault/AuthenticationSecurityException"), @FaultAction(className = DataValidateException_Exception.class, value = "http://endpoint.tm.stepanenko.ru/UserEndpoint/findUserByLogin/Fault/DataValidateException")})
    @RequestWrapper(localName = "findUserByLogin", targetNamespace = "http://endpoint.tm.stepanenko.ru/", className = "ru.stepanenko.tm.endpoint.FindUserByLogin")
    @ResponseWrapper(localName = "findUserByLoginResponse", targetNamespace = "http://endpoint.tm.stepanenko.ru/", className = "ru.stepanenko.tm.endpoint.FindUserByLoginResponse")
    @WebResult(name = "return", targetNamespace = "")
    public ru.stepanenko.tm.endpoint.UserDTO findUserByLogin(
        @WebParam(name = "session", targetNamespace = "")
        ru.stepanenko.tm.endpoint.SessionDTO session,
        @WebParam(name = "login", targetNamespace = "")
        java.lang.String login
    ) throws AuthenticationSecurityException_Exception, DataValidateException_Exception;

    @WebMethod
    @Action(input = "http://endpoint.tm.stepanenko.ru/UserEndpoint/editUserProfileRequest", output = "http://endpoint.tm.stepanenko.ru/UserEndpoint/editUserProfileResponse", fault = {@FaultAction(className = AuthenticationSecurityException_Exception.class, value = "http://endpoint.tm.stepanenko.ru/UserEndpoint/editUserProfile/Fault/AuthenticationSecurityException"), @FaultAction(className = DataValidateException_Exception.class, value = "http://endpoint.tm.stepanenko.ru/UserEndpoint/editUserProfile/Fault/DataValidateException")})
    @RequestWrapper(localName = "editUserProfile", targetNamespace = "http://endpoint.tm.stepanenko.ru/", className = "ru.stepanenko.tm.endpoint.EditUserProfile")
    @ResponseWrapper(localName = "editUserProfileResponse", targetNamespace = "http://endpoint.tm.stepanenko.ru/", className = "ru.stepanenko.tm.endpoint.EditUserProfileResponse")
    public void editUserProfile(
        @WebParam(name = "session", targetNamespace = "")
        ru.stepanenko.tm.endpoint.SessionDTO session,
        @WebParam(name = "user", targetNamespace = "")
        ru.stepanenko.tm.endpoint.UserDTO user
    ) throws AuthenticationSecurityException_Exception, DataValidateException_Exception;

    @WebMethod
    @Action(input = "http://endpoint.tm.stepanenko.ru/UserEndpoint/createUserRequest", output = "http://endpoint.tm.stepanenko.ru/UserEndpoint/createUserResponse", fault = {@FaultAction(className = AuthenticationSecurityException_Exception.class, value = "http://endpoint.tm.stepanenko.ru/UserEndpoint/createUser/Fault/AuthenticationSecurityException"), @FaultAction(className = DataValidateException_Exception.class, value = "http://endpoint.tm.stepanenko.ru/UserEndpoint/createUser/Fault/DataValidateException")})
    @RequestWrapper(localName = "createUser", targetNamespace = "http://endpoint.tm.stepanenko.ru/", className = "ru.stepanenko.tm.endpoint.CreateUser")
    @ResponseWrapper(localName = "createUserResponse", targetNamespace = "http://endpoint.tm.stepanenko.ru/", className = "ru.stepanenko.tm.endpoint.CreateUserResponse")
    public void createUser(
        @WebParam(name = "session", targetNamespace = "")
        ru.stepanenko.tm.endpoint.SessionDTO session,
        @WebParam(name = "user", targetNamespace = "")
        ru.stepanenko.tm.endpoint.UserDTO user
    ) throws AuthenticationSecurityException_Exception, DataValidateException_Exception;

    @WebMethod
    @Action(input = "http://endpoint.tm.stepanenko.ru/UserEndpoint/getUserBySessionRequest", output = "http://endpoint.tm.stepanenko.ru/UserEndpoint/getUserBySessionResponse", fault = {@FaultAction(className = AuthenticationSecurityException_Exception.class, value = "http://endpoint.tm.stepanenko.ru/UserEndpoint/getUserBySession/Fault/AuthenticationSecurityException"), @FaultAction(className = DataValidateException_Exception.class, value = "http://endpoint.tm.stepanenko.ru/UserEndpoint/getUserBySession/Fault/DataValidateException")})
    @RequestWrapper(localName = "getUserBySession", targetNamespace = "http://endpoint.tm.stepanenko.ru/", className = "ru.stepanenko.tm.endpoint.GetUserBySession")
    @ResponseWrapper(localName = "getUserBySessionResponse", targetNamespace = "http://endpoint.tm.stepanenko.ru/", className = "ru.stepanenko.tm.endpoint.GetUserBySessionResponse")
    @WebResult(name = "return", targetNamespace = "")
    public ru.stepanenko.tm.endpoint.UserDTO getUserBySession(
        @WebParam(name = "session", targetNamespace = "")
        ru.stepanenko.tm.endpoint.SessionDTO session
    ) throws AuthenticationSecurityException_Exception, DataValidateException_Exception;

    @WebMethod
    @Action(input = "http://endpoint.tm.stepanenko.ru/UserEndpoint/findAllUserRequest", output = "http://endpoint.tm.stepanenko.ru/UserEndpoint/findAllUserResponse", fault = {@FaultAction(className = AuthenticationSecurityException_Exception.class, value = "http://endpoint.tm.stepanenko.ru/UserEndpoint/findAllUser/Fault/AuthenticationSecurityException"), @FaultAction(className = DataValidateException_Exception.class, value = "http://endpoint.tm.stepanenko.ru/UserEndpoint/findAllUser/Fault/DataValidateException")})
    @RequestWrapper(localName = "findAllUser", targetNamespace = "http://endpoint.tm.stepanenko.ru/", className = "ru.stepanenko.tm.endpoint.FindAllUser")
    @ResponseWrapper(localName = "findAllUserResponse", targetNamespace = "http://endpoint.tm.stepanenko.ru/", className = "ru.stepanenko.tm.endpoint.FindAllUserResponse")
    @WebResult(name = "return", targetNamespace = "")
    public java.util.List<ru.stepanenko.tm.endpoint.UserDTO> findAllUser(
        @WebParam(name = "session", targetNamespace = "")
        ru.stepanenko.tm.endpoint.SessionDTO session
    ) throws AuthenticationSecurityException_Exception, DataValidateException_Exception;

    @WebMethod
    @Action(input = "http://endpoint.tm.stepanenko.ru/UserEndpoint/changeUserPasswordRequest", output = "http://endpoint.tm.stepanenko.ru/UserEndpoint/changeUserPasswordResponse", fault = {@FaultAction(className = AuthenticationSecurityException_Exception.class, value = "http://endpoint.tm.stepanenko.ru/UserEndpoint/changeUserPassword/Fault/AuthenticationSecurityException"), @FaultAction(className = DataValidateException_Exception.class, value = "http://endpoint.tm.stepanenko.ru/UserEndpoint/changeUserPassword/Fault/DataValidateException")})
    @RequestWrapper(localName = "changeUserPassword", targetNamespace = "http://endpoint.tm.stepanenko.ru/", className = "ru.stepanenko.tm.endpoint.ChangeUserPassword")
    @ResponseWrapper(localName = "changeUserPasswordResponse", targetNamespace = "http://endpoint.tm.stepanenko.ru/", className = "ru.stepanenko.tm.endpoint.ChangeUserPasswordResponse")
    public void changeUserPassword(
        @WebParam(name = "session", targetNamespace = "")
        ru.stepanenko.tm.endpoint.SessionDTO session,
        @WebParam(name = "user", targetNamespace = "")
        ru.stepanenko.tm.endpoint.UserDTO user
    ) throws AuthenticationSecurityException_Exception, DataValidateException_Exception;
}
