package by.bsuir.vt3.server.controller.impl;

import by.bsuir.vt3.beans.AccountType;
import by.bsuir.vt3.beans.Request;
import by.bsuir.vt3.beans.Response;
import by.bsuir.vt3.beans.StudentFile;
import by.bsuir.vt3.server.controller.ResponseConstructor;
import by.bsuir.vt3.server.controller.ServerController;
import by.bsuir.vt3.server.service.ServerService;
import by.bsuir.vt3.server.service.ServiceFactory;

import java.util.List;
import java.util.Random;

public class ServerControllerImpl implements ServerController {
    private final Random rand = new Random();

    @Override
    public Response action(Request request) {
        ServerService serverService = ServiceFactory.getInstance().getServerService();

        if (request == null || request.getRequestType() == null) {
            return new ResponseConstructor(null).constructFailResponse("Incorrect request or request type.");
        }

        var constructor = new ResponseConstructor(request.getRequestType());

        switch (request.getRequestType()) {
            case LOGIN: {
                String[] params = request.getParams();
                if (params == null || params.length != 2) {
                    return constructor.constructFailResponse("Incorrect request parameters.");
                }

                String newToken = serverService.login(params[0], params[1]);
                if (newToken == null) {
                    return constructor.constructFailResponse("Incorrect login or password.");
                }

                Response response = constructor.constructPassResponse("Logged in.");
                response.setParams(new String[]{newToken});
                return response;
            }
            case LOGOUT: {
                String authToken = request.getAuthToken();
                if (authToken == null) {
                    return constructor.constructFailResponse("Incorrect authentication token.");
                }

                serverService.logout(authToken);
                return constructor.constructPassResponse("Logged out.");
            }
            case GET: {
                String[] params = request.getParams();
                AccountType accountType = serverService.getAccountType(request.getAuthToken());
                if (accountType == null) {
                    return constructor.constructFailResponse("Incorrect authentication token.");
                }

                try {
                    StudentFile student = serverService.get(Integer.parseInt(params[0]));
                    if (student == null) {
                        return constructor.constructFailResponse("Student with given id wasn't found.");
                    }
                    Response response = constructor.constructPassResponse("Student found:");
                    response.setStudents(new StudentFile[]{student});
                    return response;
                } catch (NullPointerException | NumberFormatException e) {
                    System.out.println(e);
                    return constructor.constructFailResponse("Incorrect request parameter.");
                }
            }
            case GET_ALL: {
                AccountType accountType = serverService.getAccountType(request.getAuthToken());
                if (accountType == null) {
                    return constructor.constructFailResponse("Incorrect authentication token.");
                }

                try {
                    List<StudentFile> students = serverService.getAll();
                    Response response = constructor.constructPassResponse("Students:");
                    response.setStudents(students.toArray(new StudentFile[0]));
                    return response;
                } catch (NullPointerException e) {
                    return constructor.constructFailResponse("Data error.");
                }
            }
            case ADD: {
                AccountType accountType = serverService.getAccountType(request.getAuthToken());
                if (accountType == null) {
                    return constructor.constructFailResponse("Incorrect authentication token.");
                } else if (accountType != AccountType.ADMIN) {
                    return constructor.constructFailResponse("Insufficient permissions.");
                }

                StudentFile student = request.getStudent();
                student.setId(rand.nextInt(Integer.MAX_VALUE));
                if (serverService.add(student)) {
                    return constructor.constructPassResponse("Student added.");
                }
                return constructor.constructFailResponse("Failed to add student.");
            }
            case EDIT: {
                AccountType accountType = serverService.getAccountType(request.getAuthToken());
                if (accountType == null) {
                    return constructor.constructFailResponse("Incorrect authentication token.");
                } else if (accountType != AccountType.ADMIN) {
                    return constructor.constructFailResponse("Insufficient permissions.");
                }

                if (serverService.edit(request.getStudent())) {
                    return constructor.constructPassResponse("Student edited.");
                }
                return constructor.constructFailResponse("Failed to edit student.");
            }
            default:
                return constructor.constructFailResponse("Incorrect request or request type.");
        }
    }
}
