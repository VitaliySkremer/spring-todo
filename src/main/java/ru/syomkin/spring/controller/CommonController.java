package ru.syomkin.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.syomkin.spring.entity.dto.RecordsContainerDto;
import ru.syomkin.spring.services.RecordService;

import java.util.UUID;

@Controller
public class CommonController {

    private final RecordService recordService;

    @Autowired
    public CommonController(RecordService recordService) {
        this.recordService = recordService;
    }

    @RequestMapping("/")
    public String redirectToHomePage() {
        return "redirect:/home";
    }

    @RequestMapping("/home")
    public String getMainPage(
            Model model,
            @RequestParam(value = "filter", required = false, defaultValue = "all") String filter
    ) {
        RecordsContainerDto recordsContainerDto = recordService.findAll(filter);

        model.addAttribute("records", recordsContainerDto.getRecords());
        model.addAttribute("numberOfDoneItems", recordsContainerDto.getNumberOfDoneRecords());
        model.addAttribute("numberOfActiveItems", recordsContainerDto.getNumberOfActiveRecords());

        return "main-page";
    }

    @RequestMapping(value = "/add-record", method = RequestMethod.POST)
    public String addRecord(@RequestParam("title") String title) {
        recordService.addRecord(title);

        return "redirect:/home";
    }

    @RequestMapping(value = "/make-record-done", method = RequestMethod.POST)
    public String makeRecordDone(@RequestParam("id") UUID id,
                                 @RequestParam(value = "filter", required = false) String filter) {
        recordService.makeRecordDone(id);

        return "redirect:/home" + generateFilterParam(filter);
    }

    @RequestMapping(value = "/delete-record", method = RequestMethod.POST)
    public String deleteRecord(@RequestParam("id") UUID id,
                               @RequestParam(value = "filter", required = false) String filter) {
        recordService.deleteRecord(id);

        return "redirect:/home" + generateFilterParam(filter);
    }

    public String generateFilterParam(String filterParam) {
        return filterParam == null || filterParam.isBlank() ? "" : "?filter=" + filterParam;
    }
}
