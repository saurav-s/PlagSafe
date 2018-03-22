package com.phasec.plagsafe.detector;

import java.util.List;

public interface SubmissionComparable {
    public void compare(List<Submissible> submission1, List<Submissible> submission2);
}
