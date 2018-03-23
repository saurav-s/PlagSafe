package com.phasec.plagsafe.detector;

import com.phasec.plagsafe.objects.Report;

import java.util.List;

public interface SubmissionComparable {
    public List<Report> compare(List<Submissible> submission1, List<Submissible> submission2);
}
