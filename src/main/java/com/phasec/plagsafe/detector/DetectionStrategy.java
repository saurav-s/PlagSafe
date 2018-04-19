package com.phasec.plagsafe.detector;

import java.util.List;

import com.phasec.plagsafe.objects.Report;
import com.phasec.plagsafe.objects.SubmissibleRecord;
import com.phasec.plagsafe.system.SystemStatisticsService;

public interface DetectionStrategy {
    public List<Report> compare(SubmissibleRecord submission1, SubmissibleRecord submission2);
    public int compare(Submissible sub1file, Submissible sub2file);
    public void updateRequestCount(SystemStatisticsService stats);
}
