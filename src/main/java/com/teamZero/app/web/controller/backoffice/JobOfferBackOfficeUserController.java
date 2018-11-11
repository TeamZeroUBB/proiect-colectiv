package com.teamZero.app.web.controller.backoffice;


import com.teamZero.app.core.facade.JobOfferFacade;
import com.teamZero.app.web.dto.JobOfferDto;
import com.teamZero.app.web.dto.converter.JobOfferConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/job-offer-admin")
public class JobOfferBackOfficeUserController {

    @Autowired
    JobOfferFacade jobOfferFacade;

    @PostMapping("/new")
    public ResponseEntity<String> createNewJobOffer(JobOfferDto jobOfferDto){

        jobOfferFacade.addJobOffer(
                JobOfferConverter.convertFromDto(jobOfferDto)
        );
        return ResponseEntity.ok("Created a new job offer");
    }


    @DeleteMapping("/delete/{jobOfferId}")
    public ResponseEntity<String> deleteJobOffer(@PathVariable Long jobOfferId){

        jobOfferFacade.deleteJobOffer(jobOfferId);
        return ResponseEntity.ok("Deleted job offer");
    }


    @PutMapping("/edit")
    public ResponseEntity<String> editJobOffer(@RequestBody JobOfferDto jobOfferDto){

        jobOfferFacade.updateJobOffer(
                JobOfferConverter.convertFromDto(jobOfferDto)
        );
        return ResponseEntity.ok("Updated job offer");
    }


}
