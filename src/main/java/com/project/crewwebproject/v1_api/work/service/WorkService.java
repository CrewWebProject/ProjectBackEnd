package com.project.crewwebproject.v1_api.work.service;

import com.project.crewwebproject.config.service.AwsS3Service;
import com.project.crewwebproject.v1_api.work.repository.WorkRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class WorkService {

    private final WorkRepository workRepository;

    private final AwsS3Service awsS3Service;


}

