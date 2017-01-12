package com.malcolm.oes.controller;

import java.util.ArrayList;
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
import com.malcolm.oes.model.Pagination;
import com.malcolm.oes.model.Question;
import com.malcolm.oes.service.QuestionService;
import com.malcolm.oes.util.StringUtil;

@Controller
@RequestMapping(Constants.APP_URL_DASHBOARD_PREFIX)
public class DashBoardController extends BaseController {

    private static final String QUESTION_LIST_JSP = "question/question_manage";
    private static final String ERROR_JSP = "error";

    private Logger log = Logger.getLogger(DashBoardController.class);

    private static final String LOG_HEAD = "path : " + Constants.APP_URL_DASHBOARD_PREFIX;

    @Autowired
    private QuestionService questionService;

    @RequestMapping(value = "/myoes", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView dashBoard(
                                  @RequestParam(value = "page", defaultValue = Constants.DEFAULT_PAGE) String page,
                                  @RequestParam(value = "pageSize", defaultValue = Constants.DEFAULT_PAGE_SIZE) String pageSize,
                                  @RequestParam(value = "currentOrder", defaultValue = Constants.DEFAULT_ORDER) String currentOrder,
                                  @RequestParam(value = "keyword", defaultValue = "") String keyword
                                  ) {

        ModelAndView modelAndView = new ModelAndView();
        List<Question> questionList = new ArrayList<Question>();
        try {
            keyword = StringUtil.htmlEncode(keyword);
            Pagination pagination = new Pagination();
            pagination.setNowPage(Integer.parseInt(page));
            pagination.setPageSize(Integer.parseInt(pageSize));
            if (!StringUtil.isEmpty(keyword)) {
                questionList = questionService.findQuestioinListByKeyword(keyword, currentOrder, pagination);
            } else {
                questionList = questionService.findQuestionList(currentOrder, pagination);
            }
            modelAndView.addObject("keyword", keyword);
            modelAndView.addObject("page", pagination.getNowPage());
            modelAndView.addObject("currentOrder", currentOrder);
            modelAndView.addObject("pagination", pagination);
            modelAndView.addObject("questionList", questionList);
            modelAndView.addObject("pageSize", pageSize);
            modelAndView.setViewName(QUESTION_LIST_JSP);
            log.info(LOG_HEAD + "/myoes : Finished the oes action.");
        } catch (ParameterException parameterException) {
            Map<String, String> errorFields = parameterException.getErrorFields();
            modelAndView.addObject(Constants.ERROR_FIELDS, errorFields);
            modelAndView.setViewName(ERROR_JSP);
            log.warn(LOG_HEAD + "/myoes : [ParameterException] : The parameter may be occured exception.");
            return modelAndView;
        } catch (ServiceException serviceException) {
            modelAndView.addObject(Constants.TIP_MESSAGE,
                    serviceException.getMessage() + "[" + serviceException.getCode() + "]");
            modelAndView.setViewName(ERROR_JSP);
            log.error(LOG_HEAD + "/myoes : [ServiceException] : " + serviceException.getMessage() + "[" + serviceException.getCode() + "]");
            return modelAndView;
        } catch (RuntimeException runtimeException) {
            modelAndView.addObject("errorMsg", "The parameter occured a number format exception.");
            modelAndView.setViewName(ERROR_JSP);
            log.error(LOG_HEAD + "/myoes : [RuntimeException] : " + runtimeException.getMessage());
        }
        return modelAndView;
    }
}
