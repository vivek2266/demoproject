package com.example.service;


import com.example.entity.User;
import com.example.repository.UserRepository;
import com.example.config.PdfGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PdfGenerator pdfGenerator;

	@Transactional
	public User saveUser(User user) throws Exception {

		if (user.getName() == null || user.getName().isEmpty()) {
			throw new IllegalArgumentException("Name cannot be empty");
		}

		User savedUser = userRepository.save(user);

		pdfGenerator.generatePdf(savedUser);

		return savedUser;
	}
}