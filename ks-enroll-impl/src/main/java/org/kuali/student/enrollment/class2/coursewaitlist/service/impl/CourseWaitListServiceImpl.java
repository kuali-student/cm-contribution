package org.kuali.student.enrollment.class2.coursewaitlist.service.impl;

import org.kuali.rice.core.api.criteria.QueryByCriteria;
import org.kuali.student.enrollment.coursewaitlist.dto.CourseWaitListEntryInfo;
import org.kuali.student.enrollment.coursewaitlist.dto.CourseWaitListInfo;
import org.kuali.student.enrollment.coursewaitlist.service.CourseWaitListService;
import org.kuali.student.enrollment.class2.coursewaitlist.dao.CourseWaitListDao;
import org.kuali.student.enrollment.class2.coursewaitlist.model.CourseWaitListEntity;
import org.kuali.student.r2.common.dto.ContextInfo;
import org.kuali.student.r2.common.dto.StatusInfo;
import org.kuali.student.r2.common.dto.ValidationResultInfo;
import org.kuali.student.r2.common.exceptions.*;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.jws.WebParam;
import java.util.ArrayList;
import java.util.List;

/**
 * This class implements the CourseWaitList Service
 *
 * @author Kuali Student Team
 */
public class CourseWaitListServiceImpl implements CourseWaitListService {
    @Resource
    private CourseWaitListDao courseWaitListDao;

    public CourseWaitListDao getCourseWaitListDao() {
        return courseWaitListDao;
    }

    public void setCourseWaitListDao(CourseWaitListDao courseWaitListDao) {
        this.courseWaitListDao = courseWaitListDao;
    }

    @Override
    @Transactional(readOnly = true)
    public CourseWaitListInfo getCourseWaitList(String courseWaitListId, ContextInfo contextInfo)
            throws DoesNotExistException, InvalidParameterException, MissingParameterException,
                   OperationFailedException, PermissionDeniedException {
        CourseWaitListEntity courseWaitListEntity = courseWaitListDao.find(courseWaitListId);
        if (null == courseWaitListEntity) {
            throw new DoesNotExistException(courseWaitListId);
        }
        return courseWaitListEntity.toDto();
    }
    
    @Override
    @Transactional(readOnly = false, noRollbackFor = {DoesNotExistException.class}, rollbackFor = {Throwable.class})
    public CourseWaitListInfo createCourseWaitList(String courseWaitListTypeKey,
                                                   CourseWaitListInfo courseWaitListInfo,
                                                   ContextInfo contextInfo)
            throws DataValidationErrorException, DoesNotExistException,
            InvalidParameterException, MissingParameterException,
            OperationFailedException, PermissionDeniedException, ReadOnlyException {

        CourseWaitListEntity courseWaitListEntity = new CourseWaitListEntity(courseWaitListInfo);
        courseWaitListEntity.setEntityCreated(contextInfo);
        courseWaitListEntity.setType(courseWaitListTypeKey);
        courseWaitListDao.persist(courseWaitListEntity);
        return courseWaitListEntity.toDto();
    }

    @Override
    @Transactional(readOnly = false, noRollbackFor = {DoesNotExistException.class}, rollbackFor = {Throwable.class})
    public CourseWaitListInfo updateCourseWaitList(String courseWaitListId,
                                                   CourseWaitListInfo courseWaitListInfo,
                                                   ContextInfo contextInfo)
            throws DataValidationErrorException, DoesNotExistException,
            InvalidParameterException, MissingParameterException,
            OperationFailedException, PermissionDeniedException,
            ReadOnlyException, VersionMismatchException {

        CourseWaitListEntity courseWaitListEntity = courseWaitListDao.find(courseWaitListId);
        if (null != courseWaitListEntity) {
            courseWaitListEntity.fromDto(courseWaitListInfo);
            courseWaitListEntity.setEntityUpdated(contextInfo);
            courseWaitListDao.merge(courseWaitListEntity);
            return courseWaitListEntity.toDto();
        } else {
            throw new DoesNotExistException(courseWaitListId);
        }
    }

    @Override
    @Transactional(readOnly = false, noRollbackFor = {DoesNotExistException.class}, rollbackFor = {Throwable.class})
    public StatusInfo changeCourseWaitListState(String courseWaitListId, String stateKey, ContextInfo contextInfo)
            throws DoesNotExistException, InvalidParameterException, MissingParameterException,
            OperationFailedException, PermissionDeniedException {
        StatusInfo status = new StatusInfo();
        status.setSuccess(Boolean.TRUE);
        CourseWaitListEntity courseWaitListEntity = courseWaitListDao.find(courseWaitListId);
        if (null != courseWaitListEntity) {
            courseWaitListEntity.setState(stateKey);
            courseWaitListEntity.setEntityUpdated(contextInfo);
            courseWaitListDao.merge(courseWaitListEntity);
        } else {
            throw new DoesNotExistException(courseWaitListId);
        }
        return status;
    }

    @Override
    @Transactional(readOnly = false, noRollbackFor = {DoesNotExistException.class}, rollbackFor = {Throwable.class})
    public StatusInfo deleteCourseWaitList(String courseWaitListId, ContextInfo contextInfo)
            throws DoesNotExistException, InvalidParameterException, MissingParameterException,
            OperationFailedException, PermissionDeniedException {
        StatusInfo status = new StatusInfo();
        status.setSuccess(Boolean.TRUE);

        CourseWaitListEntity courseWaitListEntity = courseWaitListDao.find(courseWaitListId);
        if (null != courseWaitListEntity) {
            courseWaitListDao.remove(courseWaitListEntity);
        } else {
            throw new DoesNotExistException(courseWaitListId);
        }
        return status;
    }   

    @Override
    @Transactional(readOnly = true)
    public List<CourseWaitListInfo> getCourseWaitListsByActivityOffering(String activityOfferingId,
                                                                         ContextInfo contextInfo)
            throws InvalidParameterException, MissingParameterException,
            OperationFailedException, PermissionDeniedException {
        List<String> aoIds = new ArrayList<String>();
        aoIds.add(activityOfferingId);
        List<CourseWaitListEntity> entities = courseWaitListDao.getCourseWaitListsByActivityOffering(aoIds);
        List<CourseWaitListInfo> infoList = new ArrayList<CourseWaitListInfo>();
        for (CourseWaitListEntity entity : entities) {
            CourseWaitListInfo courseWaitListInfo = entity.toDto();
            infoList.add(courseWaitListInfo);
        }
        return infoList;
    }

    @Override
    @Transactional(readOnly = true)
    public List<ValidationResultInfo> validateCourseWaitList(@WebParam(name = "validationTypeKey") String validationTypeKey,
                                                             @WebParam(name = "courseWaitListTypeKey") String courseWaitListTypeKey,
                                                             @WebParam(name = "courseWaitListInfo") CourseWaitListInfo courseWaitListInfo,
                                                             @WebParam(name = "contextInfo") ContextInfo contextInfo)
            throws DoesNotExistException,
            InvalidParameterException,
            MissingParameterException,
            OperationFailedException,
            PermissionDeniedException{
        //put implementation in Decorator?
        throw new OperationFailedException("not implemented");
    }

    @Override
    @Transactional(readOnly = true)
    public List<CourseWaitListInfo> getCourseWaitListsByIds(@WebParam(name = "courseWaitListIds") List<String> courseWaitListIds,
                                                            @WebParam(name = "contextInfo") ContextInfo contextInfo)
            throws DoesNotExistException,
            InvalidParameterException,
            MissingParameterException,
            OperationFailedException,
            PermissionDeniedException{
        throw new OperationFailedException("not implemented");
    }

    @Override
    @Transactional(readOnly = true)
    public List<String> getCourseWaitListIdsByType(@WebParam(name = "courseWaitListTypeKey") String courseWaitListTypeKey,
                                                   @WebParam(name = "contextInfo") ContextInfo contextInfo)
            throws InvalidParameterException,
            MissingParameterException,
            OperationFailedException,
            PermissionDeniedException{
        throw new OperationFailedException("not implemented");
    }



    @Override
    @Transactional(readOnly = true)
    public List<CourseWaitListInfo> getCourseWaitListsByFormatOffering(@WebParam(name = "formatOfferingId") String formatOfferingId,
                                                                       @WebParam(name = "contextInfo") ContextInfo contextInfo)
            throws InvalidParameterException,
            MissingParameterException,
            OperationFailedException,
            PermissionDeniedException{
        throw new OperationFailedException("not implemented");
    }

    @Override
    @Transactional(readOnly = true)
    public List<String> searchForCourseWaitListIds(@WebParam(name = "criteria") QueryByCriteria criteria,
                                                   @WebParam(name = "contextInfo") ContextInfo contextInfo)
            throws InvalidParameterException,
            MissingParameterException,
            OperationFailedException,
            PermissionDeniedException{
        throw new OperationFailedException("not implemented");
    }

    @Override
    @Transactional(readOnly = true)
    public List<CourseWaitListInfo> searchForCourseWaitLists(@WebParam(name = "criteria") QueryByCriteria criteria,
                                                             @WebParam(name = "contextInfo") ContextInfo contextInfo)
            throws InvalidParameterException,
            MissingParameterException,
            OperationFailedException,
            PermissionDeniedException{
        throw new OperationFailedException("not implemented");
    }

    @Override
    @Transactional(readOnly = true)
    public CourseWaitListEntryInfo getCourseWaitListEntry(@WebParam(name = "courseWaitListEntryId") String courseWaitListEntryId,
                                                          @WebParam(name = "contextInfo") ContextInfo contextInfo)
            throws DoesNotExistException,
            InvalidParameterException,
            MissingParameterException,
            OperationFailedException,
            PermissionDeniedException{
        throw new OperationFailedException("not implemented");
    }

    @Override
    @Transactional(readOnly = true)
    public List<CourseWaitListEntryInfo> getCourseWaitListEntriesByIds(@WebParam(name = "courseWaitListEntryIds") List<String> courseWaitListEntryIds,
                                                                       @WebParam(name = "contextInfo") ContextInfo contextInfo)
            throws DoesNotExistException,
            InvalidParameterException,
            MissingParameterException,
            OperationFailedException,
            PermissionDeniedException{
        throw new OperationFailedException("not implemented");
    }

    @Override
    @Transactional(readOnly = true)
    public List<String> getCourseWaitListEntryIdsByType(@WebParam(name = "courseWaitListEntryTypeKey") String courseWaitListEntryTypeKey,
                                                        @WebParam(name = "contextInfo") ContextInfo contextInfo)
            throws InvalidParameterException,
            MissingParameterException,
            OperationFailedException,
            PermissionDeniedException{
        throw new OperationFailedException("not implemented");
    }

    @Override
    @Transactional(readOnly = true)
    public List<CourseWaitListEntryInfo> getCourseWaitListEntriesByStudent(@WebParam(name = "studentId") String studentId,
                                                                           @WebParam(name = "contextInfo") ContextInfo contextInfo)
            throws InvalidParameterException,
            MissingParameterException,
            OperationFailedException,
            PermissionDeniedException{
        throw new OperationFailedException("not implemented");
    }

    @Override
    @Transactional(readOnly = true)
    public List<CourseWaitListEntryInfo> getCourseWaitListEntriesByCourseWaitList(@WebParam(name = "courseWaitListId") String courseWaitListId,
                                                                                  @WebParam(name = "contextInfo") ContextInfo contextInfo)
            throws InvalidParameterException,
            MissingParameterException,
            OperationFailedException,
            PermissionDeniedException{
        throw new OperationFailedException("not implemented");
    }

    @Override
    @Transactional(readOnly = true)
    public List<CourseWaitListEntryInfo> getCourseWaitListEntriesByCourseWaitListAndStudent(@WebParam(name = "courseWaitListId") String courseWaitListId,
                                                                                            @WebParam(name = "studentId") String studentId,
                                                                                            @WebParam(name = "contextInfo") ContextInfo contextInfo)
            throws InvalidParameterException,
            MissingParameterException,
            OperationFailedException,
            PermissionDeniedException{
        throw new OperationFailedException("not implemented");
    }


    @Override
    @Transactional(readOnly = true)
    public List<String> searchForCourseWaitListEntryIds(@WebParam(name = "criteria") QueryByCriteria criteria,
                                                        @WebParam(name = "contextInfo") ContextInfo contextInfo)
            throws InvalidParameterException,
            MissingParameterException,
            OperationFailedException,
            PermissionDeniedException{
        throw new OperationFailedException("not implemented");
    }

    @Override
    @Transactional(readOnly = true)
    public List<CourseWaitListEntryInfo> searchForCourseWaitListEntries(@WebParam(name = "criteria") QueryByCriteria criteria,
                                                                        @WebParam(name = "contextInfo") ContextInfo contextInfo)
            throws InvalidParameterException,
            MissingParameterException,
            OperationFailedException,
            PermissionDeniedException{
        throw new OperationFailedException("not implemented");
    }

    @Override
    @Transactional(readOnly = true)
    public List<ValidationResultInfo> validateCourseWaitListEntry(@WebParam(name = "validationTypeKey") String validationTypeKey,
                                                                  @WebParam(name = "courseWaitListId") String courseWaitListId,
                                                                  @WebParam(name = "studentId") String studentId,
                                                                  @WebParam(name = "courseWaitListEntryTypeKey") String courseWaitListEntryTypeKey,
                                                                  @WebParam(name = "courseWaitListEntryInfo") CourseWaitListEntryInfo courseWaitListEntryInfo,
                                                                  @WebParam(name = "contextInfo") ContextInfo contextInfo)
            throws DoesNotExistException,
            InvalidParameterException,
            MissingParameterException,
            OperationFailedException,
            PermissionDeniedException{
        throw new OperationFailedException("not implemented");
    }

    @Override
    @Transactional(readOnly = true)
    public CourseWaitListEntryInfo createCourseWaitListEntry(@WebParam(name = "courseWaitListId") String courseWaitListId,
                                                             @WebParam(name = "studentId") String studentId,
                                                             @WebParam(name = "courseWaitListEntryTypeKey") String courseWaitListEntryTypeKey,
                                                             @WebParam(name = "courseWaitListEntryInfo") CourseWaitListEntryInfo courseWaitListEntryInfo,
                                                             @WebParam(name = "contextInfo") ContextInfo contextInfo)
            throws DataValidationErrorException,
            DoesNotExistException,
            InvalidParameterException,
            MissingParameterException,
            OperationFailedException,
            PermissionDeniedException,
            ReadOnlyException{
        throw new OperationFailedException("not implemented");
    }

    @Override
    @Transactional(readOnly = true)
    public CourseWaitListEntryInfo updateCourseWaitListEntry(@WebParam(name = "courseWaitListEntryId") String courseWaitListEntryId,
                                                             @WebParam(name = "courseWaitListEntryInfo") CourseWaitListEntryInfo courseWaitListEntryInfo,
                                                             @WebParam(name = "contextInfo") ContextInfo contextInfo)
            throws DataValidationErrorException,
            DoesNotExistException,
            InvalidParameterException,
            MissingParameterException,
            OperationFailedException,
            PermissionDeniedException,
            ReadOnlyException,
            VersionMismatchException {
        throw new OperationFailedException("not implemented");
    }


    @Override
    @Transactional(readOnly = true)
    public StatusInfo changeCourseWaitListEntryState(@WebParam(name = "courseWaitListEntryId") String courseWaitListEntryId,
                                                     @WebParam(name = "stateKey") String stateKey,
                                                     @WebParam(name = "contextInfo") ContextInfo contextInfo)
            throws DoesNotExistException,
            InvalidParameterException,
            MissingParameterException,
            OperationFailedException,
            PermissionDeniedException{
        throw new OperationFailedException("not implemented");
    }

    @Override
    @Transactional(readOnly = true)
    public StatusInfo deleteCourseWaitListEntry(@WebParam(name = "courseWaitListEntryId") String courseWaitListEntryId,
                                                @WebParam(name = "contextInfo") ContextInfo contextInfo)
            throws DoesNotExistException,
            InvalidParameterException,
            MissingParameterException,
            OperationFailedException,
            PermissionDeniedException{
        throw new OperationFailedException("not implemented");
    }

    @Override
    @Transactional(readOnly = true)
    public StatusInfo reorderCourseWaitListEntries(@WebParam(name = "courseWaitListId") String courseWaitListId,
                                                   @WebParam(name = "courseWaitListEntryIds") List<String> courseWaitListEntryIds,
                                                   @WebParam(name = "contextInfo") ContextInfo contextInfo)
            throws DoesNotExistException,
            InvalidParameterException,
            MissingParameterException,
            OperationFailedException,
            PermissionDeniedException{
        throw new OperationFailedException("not implemented");
    }

    @Override
    @Transactional(readOnly = true)
    public StatusInfo moveCourseWaitListEntryToPosition(@WebParam(name = "courseWaitListEntryId") String courseWaitListEntryId,
                                                        @WebParam(name = "position") Integer position,
                                                        @WebParam(name = "contextInfo") ContextInfo contextInfo)
            throws DoesNotExistException,
            InvalidParameterException,
            MissingParameterException,
            OperationFailedException,
            PermissionDeniedException{
        throw new OperationFailedException("not implemented");
    }


}