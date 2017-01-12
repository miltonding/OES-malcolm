package com.malcolm.oes.controller;

import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.malcolm.oes.Constants;
import com.malcolm.oes.exception.ParameterException;
import com.malcolm.oes.exception.ServiceException;
import com.malcolm.oes.model.Question;
import com.malcolm.oes.model.User;
import com.malcolm.oes.service.QuestionService;

@Controller
@RequestMapping(Constants.APP_URL_QUESTION_PREFIX)
public class QuestionController extends BaseController {

    private static final String CREATE_QUESTION_JSP = "question/question_create";
    private static final String DETAIL_QUESTION_JSP = "question/question_detail";
    private static final String EDIT_QUESTION_JSP = "question/question_edit";

    private static final String CREATE_QUESTION_PAGE = "question/createQuestion";
    private static final String DETAIL_QUESTION_PAGE = "question/detailQuestion";
    private static final String QUESTION_LIST_PAGE = "dashBoard/myoes";

    private static final int FAIL_RESULT_NUM = 0;

    private Logger log = Logger.getLogger(QuestionController.class);

    private static final String LOG_HEAD = "path : " + Constants.APP_URL_DASHBOARD_PREFIX;

    @Autowired
    private QuestionService questionService;

    @RequestMapping(value = "/createQuestion", method = RequestMethod.GET)
    public ModelAndView createQuestion() {
        ModelAndView modelAndView = new ModelAndView();
        try {
            int count = questionService.getAllQuestionCount();
            modelAndView.setViewName(CREATE_QUESTION_JSP);
            modelAndView.addObject("questionId", String.format("Q%04d", (count + 1)));
            log.info(LOG_HEAD + "/createQuestion : Enter the create question jsp.");
            return modelAndView;
        } catch (ParameterException parameterException) {
            Map<String, String> errorFields = parameterException.getErrorFields();
            modelAndView.addObject(Constants.ERROR_FIELDS, errorFields);
            modelAndView.setViewName(Constants.ERROR_JSP);
            log.warn(LOG_HEAD + "/createQuestion : [ParameterException] : The parameter may be occured exception.");
            return modelAndView;
        } catch (ServiceException serviceException) {
            modelAndView.addObject(Constants.TIP_MESSAGE,
                    serviceException.getMessage() + "[" + serviceException.getCode() + "]");
            modelAndView.setViewName(Constants.ERROR_JSP);
            log.error(LOG_HEAD + "/detailQuestion : [ServiceException] : " + serviceException.getMessage() + "[" + serviceException.getCode() + "]");
            return modelAndView;
        }
    }

    @RequestMapping(value = "/detailQuestion/{id}", method = RequestMethod.GET)
    public ModelAndView detailQuestion(@PathVariable int id) {
        ModelAndView modelAndView = new ModelAndView();
        try {
            Question question = questionService.getQuestionById(id);
            modelAndView.addObject("question", question);
            modelAndView.setViewName(DETAIL_QUESTION_JSP);
            log.info(LOG_HEAD + "/detailQuestion : Execute the detail question action by question id.");
        } catch (ParameterException parameterException) {
            Map<String, String> errorFields = parameterException.getErrorFields();
            modelAndView.addObject(Constants.ERROR_FIELDS, errorFields);
            modelAndView.setViewName(Constants.ERROR_JSP);
            log.warn(LOG_HEAD + "/detailQuestion : [ParameterException] : The parameter may be occured exception.");
            return modelAndView;
        } catch (ServiceException serviceException) {
            modelAndView.addObject(Constants.TIP_MESSAGE,
                    serviceException.getMessage() + "[" + serviceException.getCode() + "]");
            modelAndView.setViewName(Constants.ERROR_JSP);
            log.error(LOG_HEAD + "/detailQuestion : [ServiceException] : " + serviceException.getMessage() + "[" + serviceException.getCode() + "]");
            return modelAndView;
        } catch (RuntimeException runtimeException) {
            modelAndView.addObject("errorMsg", "The parameter occured a number format exception.");
            modelAndView.setViewName(Constants.ERROR_JSP);
            log.error(LOG_HEAD + "/detailQuestion : [RuntimeException] : " + runtimeException.getMessage());
        }
        return modelAndView;
    }

    @RequestMapping(value = "saveQuestion", method = RequestMethod.POST)
    public ModelAndView saveQuestion(Question question) {
        ModelAndView modelAndView = new ModelAndView();

        User user = (User) this.getSession(Constants.USER);

        try {
            int result = questionService.saveQuestion(question, user);
            if (result != FAIL_RESULT_NUM) {
                this.addSession(Constants.QUESTION_SUCCESS_FLASH_MSG, "Save question is ok!");
            } else {
                this.addSession(Constants.QUESTION_FAILURE_FLASH_MSG, "Save question is failure!");
            }
            modelAndView.setView(this.getRedirectView(CREATE_QUESTION_PAGE));
            log.info(LOG_HEAD + "/saveQuestion : Execute the save question action.");
        } catch (ParameterException parameterException) {
            Map<String, String> errorFields = parameterException.getErrorFields();
            modelAndView.addObject(Constants.ERROR_FIELDS, errorFields);
            modelAndView.setViewName(Constants.ERROR_JSP);
            log.warn(LOG_HEAD + "/saveQuestion : [ParameterException] : The parameter may be occured exception.");
            return modelAndView;
        } catch (ServiceException serviceException) {
            modelAndView.addObject(Constants.TIP_MESSAGE,
                    serviceException.getMessage() + "[" + serviceException.getCode() + "]");
            modelAndView.setViewName(Constants.ERROR_JSP);
            log.error(LOG_HEAD + "/saveQuestion : [ServiceException] : " + serviceException.getMessage() + "[" + serviceException.getCode() + "]");
            return modelAndView;
        }
        return modelAndView;
    }

    @RequestMapping(value = "/deleteQuestionByIds", method = RequestMethod.POST)
    public ModelAndView deleteQuestionByIds(@RequestParam(value = "questionCheckbox") int questionCheckbox[]) {
        ModelAndView modelAndView = new ModelAndView();
        try {
            int count = questionService.deleteQuestionByIds(questionCheckbox);
            if (count != FAIL_RESULT_NUM) {
                this.addSession(Constants.QUESTION_SUCCESS_FLASH_MSG, "Delete is ok!");
            } else {
                this.addSession(Constants.QUESTION_FAILURE_FLASH_MSG, "Delete is failure!");
            }
            modelAndView.setView(this.getRedirectView(QUESTION_LIST_PAGE));
            log.info(LOG_HEAD + "/deleteQuestionByIds : Execute the delete question by ids action.");
        } catch (ParameterException parameterException) {
            Map<String, String> errorFields = parameterException.getErrorFields();
            modelAndView.addObject(Constants.ERROR_FIELDS, errorFields);
            modelAndView.setViewName(Constants.ERROR_JSP);
            log.warn(LOG_HEAD + "/deleteQuestionByIds : [ParameterException] : The parameter may be occured exception.");
            return modelAndView;
        } catch (ServiceException serviceException) {
            modelAndView.addObject(Constants.TIP_MESSAGE,
                    serviceException.getMessage() + "[" + serviceException.getCode() + "]");
            modelAndView.setViewName(Constants.ERROR_JSP);
            log.error(LOG_HEAD + "/deleteQuestionByIds : [ServiceException] : " + serviceException.getMessage() + "[" + serviceException.getCode() + "]");
            return modelAndView;
        } catch (RuntimeException runtimeException) {
            modelAndView.addObject("errorMsg", "The parameter occured a number format exception.");
            modelAndView.setViewName(Constants.ERROR_JSP);
            log.error(LOG_HEAD + "/deleteQuestionByIds : [RuntimeException] : " + runtimeException.getMessage());
            return modelAndView;
        }
        return modelAndView;
    }

    @RequestMapping(value = "/editQuestion/{id}", method = RequestMethod.GET)
    public ModelAndView editQuestion(@PathVariable int id) {
        ModelAndView modelAndView = new ModelAndView();
        try {
            Question question = questionService.getQuestionById(id);
            modelAndView.addObject("question", question);
            modelAndView.setViewName(EDIT_QUESTION_JSP);
            log.info(LOG_HEAD + "/editQuestion/" + id +" : Execute the edit question by id action.");
        } catch (ParameterException parameterException) {
            Map<String, String> errorFields = parameterException.getErrorFields();
            modelAndView.addObject(Constants.ERROR_FIELDS, errorFields);
            modelAndView.setViewName(Constants.ERROR_JSP);
            log.warn(LOG_HEAD + "/editQuestion/" + id +" : [ParameterException] : The parameter may be occured exception.");
            return modelAndView;
        } catch (ServiceException serviceException) {
            modelAndView.addObject(Constants.TIP_MESSAGE,
                    serviceException.getMessage() + "[" + serviceException.getCode() + "]");
            modelAndView.setViewName(Constants.ERROR_JSP);
            log.error(LOG_HEAD + "/editQuestion" + id +" : [ServiceException] : " + serviceException.getMessage() + "[" + serviceException.getCode() + "]");
            return modelAndView;
        } catch (RuntimeException runtimeException) {
            modelAndView.addObject("errorMsg", "The parameter occured a number format exception.");
            modelAndView.setViewName(Constants.ERROR_JSP);
            log.error(LOG_HEAD + "/editQuestion" + id +" : [RuntimeException] : " + runtimeException.getMessage());
            return modelAndView;
        }
        return modelAndView;
    }

    @RequestMapping(value = "/editQuestionToSave", method = RequestMethod.POST)
    public ModelAndView editQuestionToSave(Question question, @RequestParam(value = "oldId") int oldId) {
        ModelAndView modelAndView = new ModelAndView();

        User user = (User) this.getSession(Constants.USER);
        int result = 0;
        try {
            result = questionService.saveEditQuestion(question, user, oldId);
            if (result != FAIL_RESULT_NUM) {
                this.addSession(Constants.QUESTION_SUCCESS_FLASH_MSG, "Update is ok!");
            } else {
                this.addSession(Constants.QUESTION_FAILURE_FLASH_MSG, "Update is failure!");
            }
            modelAndView.setView(this.getRedirectView(DETAIL_QUESTION_PAGE + "/" + result));
            log.info(LOG_HEAD + "/editQuestionToSave : Execute the save edit question action.");
        } catch (ParameterException parameterException) {
            Map<String, String> errorFields = parameterException.getErrorFields();
            modelAndView.addObject(Constants.ERROR_FIELDS, errorFields);
            modelAndView.addObject("id", result + "");
            modelAndView.setViewName(Constants.ERROR_JSP);
            log.warn(LOG_HEAD + "/editQuestionToSave : [ParameterException] : The parameter may be occured exception.");
            return modelAndView;
        } catch (ServiceException serviceException) {
            modelAndView.addObject(Constants.TIP_MESSAGE,
                    serviceException.getMessage() + "[" + serviceException.getCode() + "]");
            modelAndView.addObject("id", result + "");
            modelAndView.setViewName(Constants.ERROR_JSP);
            log.error(LOG_HEAD + "/editQuestionToSave : [ServiceException] : " + serviceException.getMessage() + "[" + serviceException.getCode() + "]");
            return modelAndView;
        }
        return modelAndView;
    }

    @RequestMapping(value = "/deleteDetailQuestion/{questionRealId}", method = RequestMethod.GET)
    public ModelAndView deleteDetailQuestion(@PathVariable int questionRealId) {
        ModelAndView modelAndView = new ModelAndView();
        try {
            int ids[] = {questionRealId};
            int count = questionService.deleteQuestionByIds(ids);
            if (count != FAIL_RESULT_NUM) {
                this.addSession(Constants.QUESTION_SUCCESS_FLASH_MSG, "Delete is ok!");
            } else {
                this.addSession(Constants.QUESTION_FAILURE_FLASH_MSG, "Delete is failure!");
            }
            modelAndView.setView(this.getRedirectView(QUESTION_LIST_PAGE));
            log.info(LOG_HEAD + "/deleteDetailQuestion/" + questionRealId +" : Execute the delete detail question action.");
        } catch (ParameterException parameterException) {
            Map<String, String> errorFields = parameterException.getErrorFields();
            modelAndView.addObject(Constants.ERROR_FIELDS, errorFields);
            modelAndView.setViewName(Constants.ERROR_JSP);
            log.warn(LOG_HEAD + "/deleteDetailQuestion/" + questionRealId +" : [ParameterException] : The parameter may be occured exception.");
            return modelAndView;
        } catch (ServiceException serviceException) {
            modelAndView.addObject(Constants.TIP_MESSAGE,
                    serviceException.getMessage() + "[" + serviceException.getCode() + "]");
            modelAndView.setViewName(Constants.ERROR_JSP);
            log.error(LOG_HEAD + "/deleteDetailQuestion/" + questionRealId +" : [ServiceException] : " + serviceException.getMessage() + "[" + serviceException.getCode() + "]");
            return modelAndView;
        }
        return modelAndView;
    }

    @RequestMapping(value = "/verifyQuestionIdByCreate", method = RequestMethod.POST)
    @ResponseBody
    public String verifyQuestionIdByCreate(@RequestParam String questionId) throws ParameterException, ServiceException {
        log.info(LOG_HEAD + "/verifyQuestionIdByCreate : Verify the question id is repeat or not.");
        Question question = questionService.verifyQuestionId(questionId);
        if (null == question) {
            return "";
        }
        return "Question id is exist.";
    }

    @RequestMapping(value = "/verifyQuestionIdByEdit", method = RequestMethod.POST)
    @ResponseBody
    public String verifyQuestionIdByEdit(@RequestParam String questionId, @RequestParam String oldQuestionId) throws ParameterException, ServiceException {
        log.info(LOG_HEAD + "/verifyQuestionIdByEdit : Verify the question id is repeat or not except present question id.");
        Question question = questionService.verifyQuestionId(questionId);
        if (null == question) {
            return "";
        } else {
            if (question.getQuestionId().equals(oldQuestionId)) {
                return "";
            }
        }
        return "Question id is exist.";
    }
    @RequestMapping(value="/getQuestionQuantity", method = RequestMethod.POST)
    @ResponseBody
    public int getQuestionQuantity() throws ParameterException, ServiceException {
        log.info(LOG_HEAD + "/verifyQuestionIdByEdit : Verify the question quantity enough or not.");
        return questionService.getQuestionQuantity();
    }
}
