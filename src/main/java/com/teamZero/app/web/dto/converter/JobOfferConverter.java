package com.teamZero.app.web.dto.converter;

import com.teamZero.app.core.model.JobOffer;
import com.teamZero.app.web.dto.JobOfferDto;

import java.util.List;
import java.util.stream.Collectors;

public class JobOfferConverter {

    public static JobOfferDto convertToDto(JobOffer jobOffer){

        JobOfferDto dto = new JobOfferDto();

        //TODO implement

        return dto;
    }

    public static JobOffer convertFromDto(JobOfferDto jobOfferDto){

        JobOffer jobOffer = new JobOffer();

        //Todo implement

        return jobOffer;
    }


    public static List<JobOfferDto> convertListToDto(List<JobOffer> jobOfferList){
        return jobOfferList.stream()
                .map(JobOfferConverter::convertToDto)
                .collect(Collectors.toList());
    }

}
