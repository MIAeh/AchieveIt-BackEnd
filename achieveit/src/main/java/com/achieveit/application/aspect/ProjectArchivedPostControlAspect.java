package com.achieveit.application.aspect;

import com.achieveit.application.annotation.PostControl;
import com.achieveit.application.entity.FeatureUpLoad;
import com.achieveit.application.entity.ProjectEntity;
import com.achieveit.application.entity.ProjectSubStatus;
import com.achieveit.application.enums.ErrorCode;
import com.achieveit.application.enums.ProjectStatus;
import com.achieveit.application.exception.AchieveitException;
import com.achieveit.application.mapper.ProjectMapper;
import com.alibaba.fastjson.JSONObject;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ProjectArchivedPostControlAspect {

    private final ProjectMapper projectMapper;

    private static final Logger logger = LoggerFactory.getLogger("com.achieveit.application.aspect.LoggerAspect");

    public ProjectArchivedPostControlAspect(ProjectMapper projectMapper) {
        this.projectMapper = projectMapper;
    }

    @Pointcut(value = "within(com.achieveit.application.controller..*))")
    public void controllerPointcut() {
    }

    @Before(value = "controllerPointcut() && @annotation(postControl)")
    public void logBefore(JoinPoint joinPoint, PostControl postControl) {
        String signature = joinPoint.getSignature().toLongString();
        logger.info("====  Post Control  ====");
        logger.info("Method Signature: " + signature);

        Object arg = joinPoint.getArgs()[postControl.value()];
        String projectID = parseProjectID(arg);
        if (projectID == null || projectID.isEmpty()) {
            throw new AchieveitException(ErrorCode.POST_CONTROL_AOP_ERROR);
        }
        logger.info("Project ID:" + projectID);

        ProjectEntity project = projectMapper.getProjectByID(projectID);
        if (project == null) {
            throw new AchieveitException(ErrorCode.QUERY_ERROR);
        }

        Integer projectStatus = project.getProjectStatus();
        logger.info("Project Status:" + projectStatus);

        if (projectStatus.equals(ProjectStatus.ARCHIVED.getStatus())) {
            logger.info("Project Archived: Post is not allowed");
            throw new AchieveitException(ErrorCode.POST_CONTROL_ERROR);
        }

        logger.info("========================");
        logger.info("");
    }

    private String parseProjectID(Object object) {
        if (object instanceof String) {
            return (String) object;
        }
        else if (object instanceof JSONObject) {
            return ((JSONObject) object).getString("projectID");
        }
        else if (object instanceof FeatureUpLoad) {
            // assert all project id in FeatureUpLoad list is the same
            return ((FeatureUpLoad) object).getData().get(0).getProjectID();
        }
        else {
            throw new AchieveitException(ErrorCode.POST_CONTROL_AOP_ERROR);
        }
    }
}
