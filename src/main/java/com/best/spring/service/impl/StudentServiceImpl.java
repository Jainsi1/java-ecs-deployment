package com.best.spring.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.best.spring.domain.Student;
import com.best.spring.dto.StudentDTO;
import com.best.spring.mapper.StudentMapper;
import com.best.spring.repository.StudentRepository;
import com.best.spring.service.StudentService;

@Service
public class StudentServiceImpl extends IdCheckingService<Student, StudentDTO, Long> implements StudentService{
	
	private final StudentRepository studentRepository;
	private final StudentMapper mapper;
	
	public StudentServiceImpl(StudentRepository studentRepository, StudentMapper mapper) {
		super(studentRepository);
		this.studentRepository = studentRepository;
		this.mapper = mapper;
	}

	@Override
	public List<StudentDTO> getAll() {
		return studentRepository.findAll()
				.stream()
				.map(mapper::toDto)
				.collect(Collectors.toList());
	}

	@Override
	public StudentDTO get(Long id) {
		Student student = getIfExistById(id);
		return mapper.toDto(student);
	}

	@Override
	public StudentDTO add(StudentDTO studentDTO) {
		Student student = mapper.toModel(studentDTO);
		student = studentRepository.save(student);
		return mapper.toDto(student);
	}

	@Override
	public StudentDTO update(StudentDTO studentDTO) {
		getIfExistByDto(studentDTO);
		Student studentUpdated = mapper.toModel(studentDTO);
		studentUpdated = studentRepository.save(studentUpdated);
		return mapper.toDto(studentUpdated);
	}

	@Override
	public void delete(Long id) {
		Student student = getIfExistById(id);
		studentRepository.delete(student);
	}

}