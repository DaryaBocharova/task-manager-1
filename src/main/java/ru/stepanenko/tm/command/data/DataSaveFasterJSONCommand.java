package ru.stepanenko.tm.command.data;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.jetbrains.annotations.NotNull;
import ru.stepanenko.tm.api.service.IProjectService;
import ru.stepanenko.tm.api.service.ITaskService;
import ru.stepanenko.tm.api.service.IUserService;
import ru.stepanenko.tm.command.AbstractCommand;
import ru.stepanenko.tm.util.Domain;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class DataSaveFasterJSONCommand extends AbstractCommand {

    @Override
    public String getName() {
        return "data-save-faster-json";
    }

    @Override
    public String getDescription() {
        return "Save data to json file provided by fasterXML.";
    }

    @Override
    public void execute(){
        @NotNull final IProjectService projectService = serviceLocator.getProjectService();
        @NotNull final IUserService userService = serviceLocator.getUserService();
        @NotNull final ITaskService taskService = serviceLocator.getTaskService();
        Domain domain = new Domain(new ArrayList<>(projectService.findAll()), new ArrayList<>(taskService.findAll()), new ArrayList<>(userService.findAll()));
        ObjectMapper mapper = new ObjectMapper();
        mapper.setPropertyNamingStrategy(PropertyNamingStrategy.KEBAB_CASE);
        try {
            mapper.writerWithDefaultPrettyPrinter().writeValue(new File("data.json"), domain);
            System.out.println("Success all data was save!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
