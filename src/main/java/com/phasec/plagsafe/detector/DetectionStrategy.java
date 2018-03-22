package com.phasec.plagsafe.detector;

import java.util.List;

import com.phasec.plagsafe.objects.Report;

public interface DetectionStrategy {
    public List<Report> compare(List<Submissible> submission1, List<Submissible> submission2);
}
