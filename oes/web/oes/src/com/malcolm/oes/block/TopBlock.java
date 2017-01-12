package com.malcolm.oes.block;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.PageContext;

import com.malcolm.oes.Constants;
import com.malcolm.oes.common.BlockAbstract;
import com.malcolm.oes.model.User;

public class TopBlock extends BlockAbstract {

    @Override
    protected void execute(PageContext pageContext) {
        HttpSession session = pageContext.getSession();
        HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
        User user = (User) session.getAttribute(Constants.USER);
        request.setAttribute(Constants.USER_NAME, user.getUserName());
    }

}
