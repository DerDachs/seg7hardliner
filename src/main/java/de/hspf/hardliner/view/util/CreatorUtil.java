package de.hspf.hardliner.view.util;

import de.hspf.hardliner.model.Task;
import de.hspf.hardliner.model.Task.TaskType;
import de.hspf.hardliner.model.Users;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 * This class is a helper which will be removed later on, it only serves to
 * create mock objects.
 *
 * @author thomas.schuster
 */
@Named(value = "creatorBean")
@RequestScoped
public class CreatorUtil {

    private Users user;

    public Collection<Users> createUserList() {
        Collection<Users> users = new ArrayList<>();
        users.add(createUser("timo", "test"));
        users.add(createUser("nick","test"));
        users.add(createUser("caro","test"));
        return users;
    }

    public Users createUser(String name, String password) {
        Users user = new Users();
        user.setUsername(name);
        user.setPassword(password);
        
        Task task1 = createTask("t-sys-01", "Eine einfache Aufgabe", TaskType.Epic, 12);
        Task task2 = createTask("t-sys-02", "Eine schwierige Aufgabe", TaskType.Task, 8);
        Task task3 = createTask("t-sys-03", "Donald etwas Vernunft beibringen", TaskType.SubTask, 2);
        Collection<Task> tasks = new ArrayList<>();
        tasks.add(task1);
        tasks.add(task2);
        tasks.add(task3);

        return user;
    }

    public Task createTask(String id, String name, TaskType type, int offset) {
        Task task = new Task();
        task.setTaskId(id);
        task.setTaskName(name);
        Calendar date = Calendar.getInstance();
        date.add(Calendar.DATE, offset);
        task.setDueDate(date.getTime());
        task.setType(type);

        return task;
    }


}
