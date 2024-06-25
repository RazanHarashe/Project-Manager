package com.projectmanager.projectmanager.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.projectmanager.projectmanager.models.LoginUser;
import com.projectmanager.projectmanager.models.Project;
import com.projectmanager.projectmanager.models.User;
import com.projectmanager.projectmanager.services.ProjectService;
import com.projectmanager.projectmanager.services.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;



@org.springframework.stereotype.Controller
public class Controller {

 @Autowired
 private UserService userServ;

 @Autowired
 private ProjectService projectServ;

 @GetMapping("/")
 public String index(Model model) {
     model.addAttribute("newUser", new User());
     model.addAttribute("newLogin", new LoginUser());
     return "index.jsp";
 }

 @PostMapping("/register")
 public String register(@Valid @ModelAttribute("newUser") User newUser, BindingResult result, Model model, HttpSession session) {
     User user = userServ.register(newUser, result);
     if (result.hasErrors()) {
         model.addAttribute("newLogin", new LoginUser());
         return "index.jsp";
     }
     session.setAttribute("userId", user.getId());
     return "redirect:/dashboard";
 }

 @PostMapping("/login")
 public String login(@Valid @ModelAttribute("newLogin") LoginUser newLogin, BindingResult result, Model model, HttpSession session) {
     User user = userServ.login(newLogin, result);
     if (result.hasErrors()) {
         model.addAttribute("newUser", new User());
         return "index.jsp";
     }
     session.setAttribute("userId", user.getId());
     return "redirect:/dashboard";
 }

 @GetMapping("/logout")
 public String logout(HttpSession session) {
     session.setAttribute("userId", null);
     return "redirect:/";
 }

 @GetMapping("/dashboard")
 public String dashboard(HttpSession session, Model model) {
     Long userId = (Long) session.getAttribute("userId");
     if (userId == null) {
         return "redirect:/logout";
     }
     User user = userServ.findById(userId);
     model.addAttribute("user", user);
     model.addAttribute("unassignedProjects", projectServ.unassignedProjects(user));
     model.addAttribute("assignedProjects", projectServ.assignedProjects(user));
     return "dashboard.jsp";
 }

 @GetMapping("/projects/new")
 public String newProject(@ModelAttribute("project") Project project, HttpSession session) {
     if (session.getAttribute("userId") == null) {
         return "redirect:/logout";
     }
     return "new_project.jsp";
 }

 @PostMapping("/projects/new")
 public String createProject(@Valid @ModelAttribute("project") Project project, BindingResult result, HttpSession session) {
     if (session.getAttribute("userId") == null) {
         return "redirect:/logout";
     }
     if (result.hasErrors()) {
         return "new_project.jsp";
     }
     Long userId = (Long) session.getAttribute("userId");
     User user = userServ.findById(userId);
     project.setLead(user);
     projectServ.createProject(project);
     return "redirect:/dashboard";
 }

 @GetMapping("/projects/{id}")
 public String projectDetails(@PathVariable("id") Long id, Model model, HttpSession session) {
     if (session.getAttribute("userId") == null) {
         return "redirect:/logout";
     }
     Project project = projectServ.findProject(id);
     model.addAttribute("project", project);
     return "project_details.jsp";
 }

 @GetMapping("/projects/edit/{id}")
 public String editProject(@PathVariable("id") Long id, Model model, HttpSession session) {
     if (session.getAttribute("userId") == null) {
         return "redirect:/logout";
     }
     Project project = projectServ.findProject(id);
     model.addAttribute("project", project);
     return "edit_project.jsp";
 }

 @PutMapping("/projects/edit/{id}")
 public String updateProject(@Valid @ModelAttribute("project") Project project, BindingResult result, HttpSession session) {
     if (session.getAttribute("userId") == null) {
         return "redirect:/logout";
     }
     if (result.hasErrors()) {
         return "edit_project.jsp";
     }
     projectServ.updateProject(project);
     return "redirect:/dashboard";
 }

 @DeleteMapping("/projects/delete/{id}")
 public String deleteProject(@PathVariable("id") Long id, HttpSession session) {
     if (session.getAttribute("userId") == null) {
         return "redirect:/logout";
     }
     Project project = projectServ.findProject(id);
     projectServ.deleteProject(project);
     return "redirect:/dashboard";
 }

 @RequestMapping("/projects/{id}/join")
 public String joinProject(@PathVariable("id") Long id, HttpSession session) {
     if (session.getAttribute("userId") == null) {
         return "redirect:/logout";
     }
     Long userId = (Long) session.getAttribute("userId");
     User user = userServ.findById(userId);
     Project project = projectServ.findProject(id);
     projectServ.joinTeam(project, user);
     return "redirect:/dashboard";
 }

 @RequestMapping("/projects/{id}/leave")
 public String leaveProject(@PathVariable("id") Long id, HttpSession session) {
     if (session.getAttribute("userId") == null) {
         return "redirect:/logout";
     }
     Long userId = (Long) session.getAttribute("userId");
     User user = userServ.findById(userId);
     Project project = projectServ.findProject(id);
     projectServ.leaveTeam(project, user);
     return "redirect:/dashboard";
 }
}

