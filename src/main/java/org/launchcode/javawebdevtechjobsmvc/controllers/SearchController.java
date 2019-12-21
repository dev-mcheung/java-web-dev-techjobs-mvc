package org.launchcode.javawebdevtechjobsmvc.controllers;

import org.launchcode.javawebdevtechjobsmvc.models.Job;
import org.launchcode.javawebdevtechjobsmvc.models.JobData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

import static org.launchcode.javawebdevtechjobsmvc.controllers.ListController.columnChoices;

/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping("search")
public class SearchController {

    @RequestMapping(value = "")
    public String search(Model model) {
        model.addAttribute("columns", columnChoices);
        return "search";
    }

    // TODO #3 - Create a handler to process a search request and render the updated search view.
    @PostMapping("results")
    public String displaySearchResults(Model model, @RequestParam String searchType, String searchTerm) {
        ArrayList<Job> jobs;
        if (searchTerm.toLowerCase().equals("")) {
            jobs = JobData.findAll();
            if (searchType.equals("coreCompetency")) {
                searchType = "Skill";
            }
            model.addAttribute("title", "Jobs With " + searchType + ":");
        } else {
            jobs = JobData.findByColumnAndValue(searchType, searchTerm);
            if (searchType.equals("coreCompetency")) {
                searchType = "Skill";
            }
            model.addAttribute("title", "Jobs with " + searchType + ": " + searchTerm);
    }
        model.addAttribute("jobs", jobs);
        model.addAttribute("columns", ListController.columnChoices);

        return "search";
    }
}
