package com.phasec.plagsafe.detector;

import java.util.List;

import com.phasec.plagsafe.objects.Report;
import com.phasec.plagsafe.objects.SubmissibleRecord;

public interface DetectionStrategy {
    public List<Report> compare(SubmissibleRecord submission1, SubmissibleRecord submission2);
}
