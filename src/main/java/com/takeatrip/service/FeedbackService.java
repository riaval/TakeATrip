package com.takeatrip.service;

import com.takeatrip.domain.Feedback;

public interface FeedbackService {
	Feedback findById(String id);
	public void add(Feedback feedback);
}
