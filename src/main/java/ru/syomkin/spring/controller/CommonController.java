package ru.syomkin.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.syomkin.spring.entity.Record;
import ru.syomkin.spring.entity.RecordStatus;
import ru.syomkin.spring.services.RecordService;

import java.util.List;

@Controller
public class CommonController {

    private final RecordService recordService;

    @Autowired
    public CommonController(RecordService recordService) {
        this.recordService = recordService;
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

        model.addAttribute("numberOfDoneItems", numberOfDoneItems);
        model.addAttribute("numberOfActiveItems", numberOfActiveItems);

        return "main-page";
    }
}
