package com.teamZero.app.dto;

import com.teamZero.app.domain.job.JobOffer;

import java.util.List;

public class JobOfferList {

    private List<JobOffer> list;
    private int totalSize;

    public List<JobOffer> getList() {
        return list;
    }

    public void setList(List<JobOffer> list) {
        this.list = list;
    }

    public int getTotalSize() {
        return totalSize;
    }

    public void setTotalSize(int totalSize) {
        this.totalSize = totalSize;
    }
}
