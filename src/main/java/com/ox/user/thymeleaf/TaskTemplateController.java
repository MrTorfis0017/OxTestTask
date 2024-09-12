package com.ox.user.thymeleaf;

import com.ox.user.dto.TaskDTO;
import com.ox.user.services.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
public class TaskTemplateController {

    private final TaskService taskService;

    @GetMapping("/tasks")
    public String showTasks(Model model) {
        model.addAttribute("tasks", taskService.findAll());
        return "task/tasks";
    }

    @GetMapping("/task/{id}")
    public String showTaskDetails(@PathVariable("id") Long id, Model model) {
        TaskDTO task = taskService.get(id);
        model.addAttribute("task", task);
        return "task/task-details";
    }

    @GetMapping("/task/create")
    public String showCreateTaskPage() {
        return "task/create-task";
    }


    @GetMapping("/task/update/{id}")
    public String showUpdateTaskForm(@PathVariable("id") Long id, Model model) {
        TaskDTO task = taskService.get(id);
        model.addAttribute("task", task);
        return "task/task-details";
    }
}
