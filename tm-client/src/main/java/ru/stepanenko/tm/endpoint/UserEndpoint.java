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
 * 2019-05-13T01:30:25.462+03:00
 * Generated source version: 3.2.7
 *
 */
@WebService(targetNamespace = "http://endpoint.tm.stepanenko.ru/", name = "UserEndpoint")
@XmlSeeAlso({ObjectFactory.class})
public interface UserEndpoint {

    @WebMethod
    @Action(input = "http://endpoint.tm.stepanenko.ru/UserEndpoint/loadUserDataFasterJSONRequest", output = "http://endpoint.tm.stepanenko.ru/UserEndpoint/loadUserDataFasterJSONResponse", fault = {@FaultAction(className = AuthenticationSecurityException_Exception.class, value = "http://endpoint.tm.stepanenko.ru/UserEndpoint/loadUserDataFasterJSON/Fault/AuthenticationSecurityException")})
    @RequestWrapper(localName = "loadUserDataFasterJSON", targetNamespace = "http://endpoint.tm.stepanenko.ru/", className = "ru.stepanenko.tm.endpoint.LoadUserDataFasterJSON")
    @ResponseWrapper(localName = "loadUserDataFasterJSONResponse", targetNamespace = "http://endpoint.tm.stepanenko.ru/", className = "ru.stepanenko.tm.endpoint.LoadUserDataFasterJSONResponse")
    public void loadUserDataFasterJSON(
        @WebParam(name = "session", targetNamespace = "")
        ru.stepanenko.tm.endpoint.Session session
    ) throws AuthenticationSecurityException_Exception;

    @WebMethod
    @Action(input = "http://endpoint.tm.stepanenko.ru/UserEndpoint/editUserProfileRequest", output = "http://endpoint.tm.stepanenko.ru/UserEndpoint/editUserProfileResponse", fault = {@FaultAction(className = AuthenticationSecurityException_Exception.class, value = "http://endpoint.tm.stepanenko.ru/UserEndpoint/editUserProfile/Fault/AuthenticationSecurityException")})
    @RequestWrapper(localName = "editUserProfile", targetNamespace = "http://endpoint.tm.stepanenko.ru/", className = "ru.stepanenko.tm.endpoint.EditUserProfile")
    @ResponseWrapper(localName = "editUserProfileResponse", targetNamespace = "http://endpoint.tm.stepanenko.ru/", className = "ru.stepanenko.tm.endpoint.EditUserProfileResponse")
    @WebResult(name = "return", targetNamespace = "")
    public ru.stepanenko.tm.endpoint.User editUserProfile(
        @WebParam(name = "session", targetNamespace = "")
        ru.stepanenko.tm.endpoint.Session session,
        @WebParam(name = "login", targetNamespace = "")
        java.lang.String login,
        @WebParam(name = "password", targetNamespace = "")
        java.lang.String password
    ) throws AuthenticationSecurityException_Exception;

    @WebMethod
    @Action(input = "http://endpoint.tm.stepanenko.ru/UserEndpoint/loadUserDataJaxbXmlRequest", output = "http://endpoint.tm.stepanenko.ru/UserEndpoint/loadUserDataJaxbXmlResponse", fault = {@FaultAction(className = AuthenticationSecurityException_Exception.class, value = "http://endpoint.tm.stepanenko.ru/UserEndpoint/loadUserDataJaxbXml/Fault/AuthenticationSecurityException")})
    @RequestWrapper(localName = "loadUserDataJaxbXml", targetNamespace = "http://endpoint.tm.stepanenko.ru/", className = "ru.stepanenko.tm.endpoint.LoadUserDataJaxbXml")
    @ResponseWrapper(localName = "loadUserDataJaxbXmlResponse", targetNamespace = "http://endpoint.tm.stepanenko.ru/", className = "ru.stepanenko.tm.endpoint.LoadUserDataJaxbXmlResponse")
    public void loadUserDataJaxbXml(
        @WebParam(name = "session", targetNamespace = "")
        ru.stepanenko.tm.endpoint.Session session
    ) throws AuthenticationSecurityException_Exception;

    @WebMethod
    @Action(input = "http://endpoint.tm.stepanenko.ru/UserEndpoint/createUserRequest", output = "http://endpoint.tm.stepanenko.ru/UserEndpoint/createUserResponse", fault = {@FaultAction(className = AuthenticationSecurityException_Exception.class, value = "http://endpoint.tm.stepanenko.ru/UserEndpoint/createUser/Fault/AuthenticationSecurityException")})
    @RequestWrapper(localName = "createUser", targetNamespace = "http://endpoint.tm.stepanenko.ru/", className = "ru.stepanenko.tm.endpoint.CreateUser")
    @ResponseWrapper(localName = "createUserResponse", targetNamespace = "http://endpoint.tm.stepanenko.ru/", className = "ru.stepanenko.tm.endpoint.CreateUserResponse")
    @WebResult(name = "return", targetNamespace = "")
    public ru.stepanenko.tm.endpoint.User createUser(
        @WebParam(name = "session", targetNamespace = "")
        ru.stepanenko.tm.endpoint.Session session,
        @WebParam(name = "login", targetNamespace = "")
        java.lang.String login,
        @WebParam(name = "password", targetNamespace = "")
        java.lang.String password,
        @WebParam(name = "role", targetNamespace = "")
        java.lang.String role
    ) throws AuthenticationSecurityException_Exception;

    @WebMethod
    @Action(input = "http://endpoint.tm.stepanenko.ru/UserEndpoint/saveUserDataJaxbXmlRequest", output = "http://endpoint.tm.stepanenko.ru/UserEndpoint/saveUserDataJaxbXmlResponse", fault = {@FaultAction(className = AuthenticationSecurityException_Exception.class, value = "http://endpoint.tm.stepanenko.ru/UserEndpoint/saveUserDataJaxbXml/Fault/AuthenticationSecurityException")})
    @RequestWrapper(localName = "saveUserDataJaxbXml", targetNamespace = "http://endpoint.tm.stepanenko.ru/", className = "ru.stepanenko.tm.endpoint.SaveUserDataJaxbXml")
    @ResponseWrapper(localName = "saveUserDataJaxbXmlResponse", targetNamespace = "http://endpoint.tm.stepanenko.ru/", className = "ru.stepanenko.tm.endpoint.SaveUserDataJaxbXmlResponse")
    public void saveUserDataJaxbXml(
        @WebParam(name = "session", targetNamespace = "")
        ru.stepanenko.tm.endpoint.Session session
    ) throws AuthenticationSecurityException_Exception;

    @WebMethod
    @Action(input = "http://endpoint.tm.stepanenko.ru/UserEndpoint/saveUserDataJaxbJSONRequest", output = "http://endpoint.tm.stepanenko.ru/UserEndpoint/saveUserDataJaxbJSONResponse", fault = {@FaultAction(className = AuthenticationSecurityException_Exception.class, value = "http://endpoint.tm.stepanenko.ru/UserEndpoint/saveUserDataJaxbJSON/Fault/AuthenticationSecurityException")})
    @RequestWrapper(localName = "saveUserDataJaxbJSON", targetNamespace = "http://endpoint.tm.stepanenko.ru/", className = "ru.stepanenko.tm.endpoint.SaveUserDataJaxbJSON")
    @ResponseWrapper(localName = "saveUserDataJaxbJSONResponse", targetNamespace = "http://endpoint.tm.stepanenko.ru/", className = "ru.stepanenko.tm.endpoint.SaveUserDataJaxbJSONResponse")
    public void saveUserDataJaxbJSON(
        @WebParam(name = "session", targetNamespace = "")
        ru.stepanenko.tm.endpoint.Session session
    ) throws AuthenticationSecurityException_Exception;

    @WebMethod
    @Action(input = "http://endpoint.tm.stepanenko.ru/UserEndpoint/findUserByLoginRequest", output = "http://endpoint.tm.stepanenko.ru/UserEndpoint/findUserByLoginResponse", fault = {@FaultAction(className = AuthenticationSecurityException_Exception.class, value = "http://endpoint.tm.stepanenko.ru/UserEndpoint/findUserByLogin/Fault/AuthenticationSecurityException")})
    @RequestWrapper(localName = "findUserByLogin", targetNamespace = "http://endpoint.tm.stepanenko.ru/", className = "ru.stepanenko.tm.endpoint.FindUserByLogin")
    @ResponseWrapper(localName = "findUserByLoginResponse", targetNamespace = "http://endpoint.tm.stepanenko.ru/", className = "ru.stepanenko.tm.endpoint.FindUserByLoginResponse")
    @WebResult(name = "return", targetNamespace = "")
    public ru.stepanenko.tm.endpoint.User findUserByLogin(
        @WebParam(name = "session", targetNamespace = "")
        ru.stepanenko.tm.endpoint.Session session,
        @WebParam(name = "login", targetNamespace = "")
        java.lang.String login
    ) throws AuthenticationSecurityException_Exception;

    @WebMethod
    @Action(input = "http://endpoint.tm.stepanenko.ru/UserEndpoint/loadUserDataFasterXmlRequest", output = "http://endpoint.tm.stepanenko.ru/UserEndpoint/loadUserDataFasterXmlResponse", fault = {@FaultAction(className = AuthenticationSecurityException_Exception.class, value = "http://endpoint.tm.stepanenko.ru/UserEndpoint/loadUserDataFasterXml/Fault/AuthenticationSecurityException")})
    @RequestWrapper(localName = "loadUserDataFasterXml", targetNamespace = "http://endpoint.tm.stepanenko.ru/", className = "ru.stepanenko.tm.endpoint.LoadUserDataFasterXml")
    @ResponseWrapper(localName = "loadUserDataFasterXmlResponse", targetNamespace = "http://endpoint.tm.stepanenko.ru/", className = "ru.stepanenko.tm.endpoint.LoadUserDataFasterXmlResponse")
    public void loadUserDataFasterXml(
        @WebParam(name = "session", targetNamespace = "")
        ru.stepanenko.tm.endpoint.Session session
    ) throws AuthenticationSecurityException_Exception;

    @WebMethod
    @Action(input = "http://endpoint.tm.stepanenko.ru/UserEndpoint/saveUserDataRequest", output = "http://endpoint.tm.stepanenko.ru/UserEndpoint/saveUserDataResponse", fault = {@FaultAction(className = AuthenticationSecurityException_Exception.class, value = "http://endpoint.tm.stepanenko.ru/UserEndpoint/saveUserData/Fault/AuthenticationSecurityException")})
    @RequestWrapper(localName = "saveUserData", targetNamespace = "http://endpoint.tm.stepanenko.ru/", className = "ru.stepanenko.tm.endpoint.SaveUserData")
    @ResponseWrapper(localName = "saveUserDataResponse", targetNamespace = "http://endpoint.tm.stepanenko.ru/", className = "ru.stepanenko.tm.endpoint.SaveUserDataResponse")
    public void saveUserData(
        @WebParam(name = "session", targetNamespace = "")
        ru.stepanenko.tm.endpoint.Session session
    ) throws AuthenticationSecurityException_Exception;

    @WebMethod
    @Action(input = "http://endpoint.tm.stepanenko.ru/UserEndpoint/saveUserDataFasterJSONRequest", output = "http://endpoint.tm.stepanenko.ru/UserEndpoint/saveUserDataFasterJSONResponse", fault = {@FaultAction(className = AuthenticationSecurityException_Exception.class, value = "http://endpoint.tm.stepanenko.ru/UserEndpoint/saveUserDataFasterJSON/Fault/AuthenticationSecurityException")})
    @RequestWrapper(localName = "saveUserDataFasterJSON", targetNamespace = "http://endpoint.tm.stepanenko.ru/", className = "ru.stepanenko.tm.endpoint.SaveUserDataFasterJSON")
    @ResponseWrapper(localName = "saveUserDataFasterJSONResponse", targetNamespace = "http://endpoint.tm.stepanenko.ru/", className = "ru.stepanenko.tm.endpoint.SaveUserDataFasterJSONResponse")
    public void saveUserDataFasterJSON(
        @WebParam(name = "session", targetNamespace = "")
        ru.stepanenko.tm.endpoint.Session session
    ) throws AuthenticationSecurityException_Exception;

    @WebMethod
    @Action(input = "http://endpoint.tm.stepanenko.ru/UserEndpoint/loadUserDataJaxbJSONRequest", output = "http://endpoint.tm.stepanenko.ru/UserEndpoint/loadUserDataJaxbJSONResponse", fault = {@FaultAction(className = AuthenticationSecurityException_Exception.class, value = "http://endpoint.tm.stepanenko.ru/UserEndpoint/loadUserDataJaxbJSON/Fault/AuthenticationSecurityException")})
    @RequestWrapper(localName = "loadUserDataJaxbJSON", targetNamespace = "http://endpoint.tm.stepanenko.ru/", className = "ru.stepanenko.tm.endpoint.LoadUserDataJaxbJSON")
    @ResponseWrapper(localName = "loadUserDataJaxbJSONResponse", targetNamespace = "http://endpoint.tm.stepanenko.ru/", className = "ru.stepanenko.tm.endpoint.LoadUserDataJaxbJSONResponse")
    public void loadUserDataJaxbJSON(
        @WebParam(name = "session", targetNamespace = "")
        ru.stepanenko.tm.endpoint.Session session
    ) throws AuthenticationSecurityException_Exception;

    @WebMethod
    @Action(input = "http://endpoint.tm.stepanenko.ru/UserEndpoint/getUserBySessionRequest", output = "http://endpoint.tm.stepanenko.ru/UserEndpoint/getUserBySessionResponse", fault = {@FaultAction(className = AuthenticationSecurityException_Exception.class, value = "http://endpoint.tm.stepanenko.ru/UserEndpoint/getUserBySession/Fault/AuthenticationSecurityException")})
    @RequestWrapper(localName = "getUserBySession", targetNamespace = "http://endpoint.tm.stepanenko.ru/", className = "ru.stepanenko.tm.endpoint.GetUserBySession")
    @ResponseWrapper(localName = "getUserBySessionResponse", targetNamespace = "http://endpoint.tm.stepanenko.ru/", className = "ru.stepanenko.tm.endpoint.GetUserBySessionResponse")
    @WebResult(name = "return", targetNamespace = "")
    public ru.stepanenko.tm.endpoint.User getUserBySession(
        @WebParam(name = "session", targetNamespace = "")
        ru.stepanenko.tm.endpoint.Session session
    ) throws AuthenticationSecurityException_Exception;

    @WebMethod
    @Action(input = "http://endpoint.tm.stepanenko.ru/UserEndpoint/findAllUserRequest", output = "http://endpoint.tm.stepanenko.ru/UserEndpoint/findAllUserResponse", fault = {@FaultAction(className = AuthenticationSecurityException_Exception.class, value = "http://endpoint.tm.stepanenko.ru/UserEndpoint/findAllUser/Fault/AuthenticationSecurityException")})
    @RequestWrapper(localName = "findAllUser", targetNamespace = "http://endpoint.tm.stepanenko.ru/", className = "ru.stepanenko.tm.endpoint.FindAllUser")
    @ResponseWrapper(localName = "findAllUserResponse", targetNamespace = "http://endpoint.tm.stepanenko.ru/", className = "ru.stepanenko.tm.endpoint.FindAllUserResponse")
    @WebResult(name = "return", targetNamespace = "")
    public java.util.List<ru.stepanenko.tm.endpoint.User> findAllUser(
        @WebParam(name = "session", targetNamespace = "")
        ru.stepanenko.tm.endpoint.Session session
    ) throws AuthenticationSecurityException_Exception;

    @WebMethod
    @Action(input = "http://endpoint.tm.stepanenko.ru/UserEndpoint/saveUserDataFasterXmlRequest", output = "http://endpoint.tm.stepanenko.ru/UserEndpoint/saveUserDataFasterXmlResponse", fault = {@FaultAction(className = AuthenticationSecurityException_Exception.class, value = "http://endpoint.tm.stepanenko.ru/UserEndpoint/saveUserDataFasterXml/Fault/AuthenticationSecurityException")})
    @RequestWrapper(localName = "saveUserDataFasterXml", targetNamespace = "http://endpoint.tm.stepanenko.ru/", className = "ru.stepanenko.tm.endpoint.SaveUserDataFasterXml")
    @ResponseWrapper(localName = "saveUserDataFasterXmlResponse", targetNamespace = "http://endpoint.tm.stepanenko.ru/", className = "ru.stepanenko.tm.endpoint.SaveUserDataFasterXmlResponse")
    public void saveUserDataFasterXml(
        @WebParam(name = "session", targetNamespace = "")
        ru.stepanenko.tm.endpoint.Session session
    ) throws AuthenticationSecurityException_Exception;

    @WebMethod
    @Action(input = "http://endpoint.tm.stepanenko.ru/UserEndpoint/changeUserPasswordRequest", output = "http://endpoint.tm.stepanenko.ru/UserEndpoint/changeUserPasswordResponse", fault = {@FaultAction(className = AuthenticationSecurityException_Exception.class, value = "http://endpoint.tm.stepanenko.ru/UserEndpoint/changeUserPassword/Fault/AuthenticationSecurityException")})
    @RequestWrapper(localName = "changeUserPassword", targetNamespace = "http://endpoint.tm.stepanenko.ru/", className = "ru.stepanenko.tm.endpoint.ChangeUserPassword")
    @ResponseWrapper(localName = "changeUserPasswordResponse", targetNamespace = "http://endpoint.tm.stepanenko.ru/", className = "ru.stepanenko.tm.endpoint.ChangeUserPasswordResponse")
    @WebResult(name = "return", targetNamespace = "")
    public ru.stepanenko.tm.endpoint.User changeUserPassword(
        @WebParam(name = "session", targetNamespace = "")
        ru.stepanenko.tm.endpoint.Session session,
        @WebParam(name = "id", targetNamespace = "")
        java.lang.String id,
        @WebParam(name = "login", targetNamespace = "")
        java.lang.String login,
        @WebParam(name = "password", targetNamespace = "")
        java.lang.String password,
        @WebParam(name = "role", targetNamespace = "")
        java.lang.String role
    ) throws AuthenticationSecurityException_Exception;

    @WebMethod
    @Action(input = "http://endpoint.tm.stepanenko.ru/UserEndpoint/loadUserDataRequest", output = "http://endpoint.tm.stepanenko.ru/UserEndpoint/loadUserDataResponse", fault = {@FaultAction(className = AuthenticationSecurityException_Exception.class, value = "http://endpoint.tm.stepanenko.ru/UserEndpoint/loadUserData/Fault/AuthenticationSecurityException")})
    @RequestWrapper(localName = "loadUserData", targetNamespace = "http://endpoint.tm.stepanenko.ru/", className = "ru.stepanenko.tm.endpoint.LoadUserData")
    @ResponseWrapper(localName = "loadUserDataResponse", targetNamespace = "http://endpoint.tm.stepanenko.ru/", className = "ru.stepanenko.tm.endpoint.LoadUserDataResponse")
    public void loadUserData(
        @WebParam(name = "session", targetNamespace = "")
        ru.stepanenko.tm.endpoint.Session session
    ) throws AuthenticationSecurityException_Exception;
}
