package com.cjt.quartz.impl;

import com.cjt.dao.system.IQuartzExecuteDAO;
import com.cjt.quartz.IQuartzExecuteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author caojiantao
 */
@Service
public class QuartzExecuteServiceImpl implements IQuartzExecuteService {

    private final IQuartzExecuteDAO executeDAO;

    @Autowired
    public QuartzExecuteServiceImpl(IQuartzExecuteDAO executeDAO) {
        this.executeDAO = executeDAO;
    }

    @Override
    public boolean saveExecute(String jobClass) {
        try {
            executeDAO.saveExecute(jobClass);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public boolean removeExecuteByJobClass(String jobClass) {
        return executeDAO.removeExecuteByJobClass(jobClass) > 0;
    }
}
