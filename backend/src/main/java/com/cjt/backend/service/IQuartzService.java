package com.cjt.backend.service;

import com.cjt.common.dto.BasePageDto;
import com.cjt.backend.jobs.BaseJob;

import java.util.List;

public interface IQuartzService {

  List<BaseJob> listJobs();

  List<BaseJob> listJobs(BasePageDto dto);

  int countJobs(BasePageDto dto);

  <T extends BaseJob> boolean saveQuartz(T job);

  BaseJob getQuartz(String name);
}
