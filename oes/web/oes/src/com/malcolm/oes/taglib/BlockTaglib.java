package com.malcolm.oes.taglib;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.springframework.context.ApplicationContext;

import com.malcolm.oes.common.BlockAbstract;
import com.malcolm.oes.util.SpringUtil;

public class BlockTaglib extends TagSupport {

    private static final long serialVersionUID = 4721813033016742720L;

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int doEndTag() throws JspException {
        // Tell the server not to handle the body content.
        return SKIP_BODY;
    }

    @Override
    public int doStartTag() throws JspException {
        // There used the spring framework.
        // You should firstly parse the XML file of define the block, if you don't
        // use the spring framework.
        ApplicationContext ac = SpringUtil.getApplicationContext();
        // GET the block object.
        BlockAbstract block = (BlockAbstract) ac.getBean(name);
        // GET the generate HTML footage.
        String content = block.displayBlock(pageContext);
        // Then output in page.
        JspWriter out = pageContext.getOut();
        try {
            out.write(content);

        } catch (Exception e) {
            e.printStackTrace();
        }
        // Go on handle the page.
        return EVAL_PAGE;
    }

    @Override
    public void release() {
        super.release();
    }

}
