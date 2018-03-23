package com.phasec.plagsafe.detector;

import com.phasec.plagsafe.objects.Report;
import com.phasec.plagsafe.objects.SubmissibleRecord;

import java.util.List;

public interface SubmissionComparable {
    public List<Report> compare(SubmissibleRecord submissibleRecord, SubmissibleRecord submissibleRecord2);
}