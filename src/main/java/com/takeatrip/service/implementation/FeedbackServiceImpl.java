package com.takeatrip.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.takeatrip.domain.Feedback;
import com.takeatrip.repository.FeedbackRepository;
import com.takeatrip.service.FeedbackService;

@Service
public class FeedbackServiceImpl implements FeedbackService{
	@Autowired
	FeedbackRepository feedbackRepository;
	@Override
	public Feedback findById(String id) {
		return feedbackRepository.findById(id);
	}
	@Override
	public void add(Feedback feedback) {
		feedbackRepository.save(feedback);
	}
}
