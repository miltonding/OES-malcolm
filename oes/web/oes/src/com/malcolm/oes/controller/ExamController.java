package com.malcolm.oes.controller;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.malcolm.oes.Constants;
import com.malcolm.oes.exception.ParameterException;
import com.malcolm.oes.exception.ServiceException;
import com.malcolm.oes.model.Exam;
import com.malcolm.oes.model.ExamVo;
import com.malcolm.oes.model.Pagination;
import com.malcolm.oes.model.PaginationVo;
import com.malcolm.oes.model.User;
import com.malcolm.oes.service.ExamService;
import com.malcolm.oes.util.StringUtil;

@Controller
@RequestMapping(Constants.APP_URL_EXAM_PREFIX)
public class ExamController extends BaseController {

    private static final String MANAGE_EXAM_JSP = "exam/exam_manage";
    private static final String CREATE_EXAM_JSP = "exam/exam_create";

    private static final String MANAGE_EXAM_PAGE = "exam/manageExam";

    private static final int IS_DRAFT_NUM = 1;

    private static final String LOG_HEAD = "path : " + Constants.APP_URL_EXAM_PREFIX;

    private Logger log = Logger.getLogger(ExamController.class);

    @Autowired
    private ExamService examService;

    @RequestMapping(value = "/manageExam", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView manageExam(
                                   @RequestParam(value = "page", defaultValue = Constants.DEFAULT_PAGE) String page,
                                   @RequestParam(value = "pageSize", defaultValue = Constants.DEFAULT_PAGE_SIZE) String pageSize,
                                   @RequestParam(value = "orderParam", defaultValue = Constants.DEFAULT_ORDER_PARAM) String orderParam,
                                   @RequestParam(value = "currentOrder", defaultValue = Constants.DEFAULT_ORDER) String currentOrder,
                                   @RequestParam(value = "keyword", defaultValue = "") String keyword,
                                   @RequestParam(value = "startDate", defaultValue = "") String startDate,
                                   @RequestParam(value = "endDate", defaultValue = "") String endDate
                                   ) {
        ModelAndView modelAndView = new ModelAndView();
        try {
            keyword = StringUtil.htmlEncode(keyword);
            Pagination pagination = new Pagination();
            pagination.setNowPage(Integer.parseInt(page));
            pagination.setPageSize(Integer.parseInt(pageSize));
            PaginationVo paginationVo = new PaginationVo(pagination, keyword, currentOrder, orderParam, startDate, endDate);
            List<ExamVo> examList = examService.findExamList(paginationVo);
            modelAndView.setViewName(MANAGE_EXAM_JSP);
            modelAndView.addObject("examList", examList);
            modelAndView.addObject("keyword", keyword);
            modelAndView.addObject("page", pagination.getNowPage());
            modelAndView.addObject("currentOrder", currentOrder);
            modelAndView.addObject("pagination", pagination);
            modelAndView.addObject("pageSize", pageSize);
            modelAndView.addObject("orderParam", orderParam);
            modelAndView.addObject("startDate", startDate);
            modelAndView.addObject("endDate", endDate);
            log.info(LOG_HEAD + "/examList : Enter the manage exam jsp.");
            return modelAndView;
        } catch (ParameterException parameterException) {
            Map<String, String> errorFields = parameterException.getErrorFields();
            modelAndView.addObject(Constants.ERROR_FIELDS, errorFields);
            modelAndView.setViewName(Constants.ERROR_JSP);
            log.error(LOG_HEAD + "[ParameterException]" + "/examList : " + parameterException.getMessage());
            return modelAndView;
        } catch (ServiceException serviceException) {
            modelAndView.addObject(Constants.TIP_MESSAGE,
                    serviceException.getMessage() + "[" + serviceException.getCode() + "]");
            modelAndView.setViewName(Constants.ERROR_JSP);
            log.error(LOG_HEAD + "[ServiceException]" + "/examList : " + serviceException.getMessage());
            return modelAndView;
        }
    }

    @RequestMapping(value = "/createExam", method = RequestMethod.GET)
    public ModelAndView createExam() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(CREATE_EXAM_JSP);
        log.info(LOG_HEAD + "/createExam : Enter the create exam jsp.");
        return modelAndView;
    }
    @RequestMapping(value = "/createExam", method = RequestMethod.POST)
    public ModelAndView createExam(Exam exam,
                                   @RequestParam(value = "examDate") String examDate,
                                   @RequestParam(value = "examTime") String examtime
                                   ) {
        ModelAndView modelAndView = new ModelAndView();
        exam.setEffectiveTime(StringUtil.revertStringToDate(examDate + " " + examtime));
        try {
            int userId = ((User) this.getSession(Constants.USER)).getId();
            if (exam.getIsDraft() == IS_DRAFT_NUM) {
                int examId = examService.createExamDraft(userId, exam);
                if (examId != exam.getId()) {
                    this.addSession(Constants.EXAM_FAILURE_FLASH_MSG, "Save exam draft is failure.");
                } else {
                    this.addSession(Constants.EXAM_SUCCESS_FLASH_MSG, "Save exam draft is success.");
                }
                modelAndView.setViewName(CREATE_EXAM_JSP);
                log.info(LOG_HEAD + "/createExam : Execute the create exam draft.");
                return modelAndView;
            }
            int examId = examService.createExam(userId, exam);
            if (examId != exam.getId()) {
                this.addSession(Constants.EXAM_FAILURE_FLASH_MSG, "Save exam is failure.");
            } else {
                this.addSession(Constants.EXAM_SUCCESS_FLASH_MSG, "Save exam is success.");
            }
            modelAndView.setViewName(CREATE_EXAM_JSP);
            log.info(LOG_HEAD + "/createExam : Execute the create exam action.");
            return modelAndView;
        } catch (ParameterException parameterException) {
            Map<String, String> errorFields = parameterException.getErrorFields();
            modelAndView.addObject(Constants.ERROR_FIELDS, errorFields);
            modelAndView.setViewName(Constants.ERROR_JSP);
            log.error(LOG_HEAD + "[ParameterException]" + "/createExam : " + parameterException.getMessage());
            return modelAndView;
        } catch (ServiceException serviceException) {
            modelAndView.addObject(Constants.TIP_MESSAGE,
                    serviceException.getMessage() + "[" + serviceException.getCode() + "]");
            modelAndView.setViewName(Constants.ERROR_JSP);
            log.error(LOG_HEAD + "[ServiceException]" + "/createExam : " + serviceException.getMessage());
            return modelAndView;
        }
    }

    @RequestMapping(value = "/deleteExamByIds", method = RequestMethod.POST)
    public ModelAndView deleteExamByIds(@RequestParam(value="examIdCheckBox") int examIds[]) {
        ModelAndView modelAndView = new ModelAndView();
        try {
            int count = examService.deleteExamByIds(examIds);
            if (count != examIds.length) {
                this.addSession(Constants.EXAM_FAILURE_FLASH_MSG, "Delete exam is failure.");
            } else {
                this.addSession(Constants.EXAM_SUCCESS_FLASH_MSG, "Delete exam is success.");
            }
            modelAndView.setView(this.getRedirectView(MANAGE_EXAM_PAGE));
            log.info(LOG_HEAD + "/deleteExamByIds : Execute the delete exam.");
            return modelAndView;
        } catch (ParameterException parameterException) {
            Map<String, String> errorFields = parameterException.getErrorFields();
            modelAndView.addObject(Constants.ERROR_FIELDS, errorFields);
            modelAndView.setViewName(Constants.ERROR_JSP);
            log.error(LOG_HEAD + "[ParameterException]" + "/deleteExamByIds : " + parameterException.getMessage());
            return modelAndView;
        } catch (ServiceException serviceException) {
            modelAndView.addObject(Constants.TIP_MESSAGE,
                    serviceException.getMessage() + "[" + serviceException.getCode() + "]");
            modelAndView.setViewName(Constants.ERROR_JSP);
            log.error(LOG_HEAD + "[ServiceException]" + "/deleteExamByIds : " + serviceException.getMessage());
            return modelAndView;
        } catch (RuntimeException runtimeException) {
            modelAndView.addObject(Constants.TIP_MESSAGE, runtimeException.getMessage());
            modelAndView.setViewName(Constants.ERROR_JSP);
            log.error(LOG_HEAD + "[RuntimeException]" + "/deleteExamByIds : " + runtimeException.getMessage());
            return modelAndView;
        }
    }
}
