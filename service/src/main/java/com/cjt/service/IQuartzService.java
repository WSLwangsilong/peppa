package com.cjt.service;

import com.cjt.common.dto.BasePageDto;
import com.cjt.entity.admin.Quartz;

import java.util.List;

public interface IQuartzService {

  List<Quartz> listJobs();

  List<Quartz> listJobs(BasePageDto dto);

  int countJobs(BasePageDto dto);

  <T extends Quartz> boolean saveQuartz(T job);

  Quartz getQuartz(String name);
}
