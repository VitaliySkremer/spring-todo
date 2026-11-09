package ru.syomkin.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.syomkin.spring.entity.Record;
import ru.syomkin.spring.entity.RecordStatus;
import ru.syomkin.spring.services.RecordService;

import java.util.List;
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
    public String getMainPage(Model model) {
        List<Record> records = recordService.findAll();

        int numberOfDoneItems = 0;
        int numberOfActiveItems = 0;

        for (Record record : records) {
            if (record.getStatus().equals(RecordStatus.DONE)) {
                numberOfDoneItems++;
            } else {
                numberOfActiveItems++;
            }
        }

        model.addAttribute("records", records);
        model.addAttribute("numberOfDoneItems", numberOfDoneItems);
        model.addAttribute("numberOfActiveItems", numberOfActiveItems);

        return "main-page";
    }

    @RequestMapping(value = "/add-record", method = RequestMethod.POST)
    public String addRecord(@RequestParam("title") String title) {
        recordService.addRecord(title);

        return "redirect:/home";
    }

    @RequestMapping(value = "/make-record-done", method = RequestMethod.POST)
    public String makeRecordDone(@RequestParam("id") UUID id) {
        recordService.makeRecordDone(id);

        return "redirect:/home";
    }

    @RequestMapping(value = "/delete-record", method = RequestMethod.POST)
    public String deleteRecord(@RequestParam("id") UUID id) {
        recordService.deleteRecord(id);

        return "redirect:/home";
    }
}
