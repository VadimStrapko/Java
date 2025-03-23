package org.example.server;

import db.DbItems;
import db.DbItem;
import jakarta.servlet.jsp.JspException;
import jakarta.servlet.jsp.JspWriter;
import jakarta.servlet.jsp.tagext.TagSupport;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class KDNprintTable extends TagSupport {
    private static DbItems db = new DbItems();
    private String nameTable;
    public void setNameTable(String nameTable) {
        this.nameTable = nameTable;
    }
    @Override
    public int doStartTag() throws JspException {
        try {
            JspWriter out = pageContext.getOut();
            out.write("<h1>" + nameTable + "</h1>");
            out.write("<table border='1' style='border-spacing:0'>");
        } catch (IOException e) {
            throw new JspException(e.getMessage());
        }
        try {
            db.openConnection();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        try {
            ArrayList<DbItem> items = db.getItems();
            for (var item : items) {
                pageContext.getOut().write("<tr>");
                pageContext.getOut().write("<td>");
                pageContext.getOut().write(item.getName());
                pageContext.getOut().write("</td>");
                pageContext.getOut().write("<td>");
                pageContext.getOut().write(item.getDateOfFound().toString());
                pageContext.getOut().write("</td>");
                pageContext.getOut().write("<td>");
                pageContext.getOut().write(item.getFounder());
                pageContext.getOut().write("</td>");
                pageContext.getOut().write("</tr>");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return SKIP_BODY;
    }

    @Override
    public int doEndTag() throws JspException {
        JspWriter out = pageContext.getOut();
        try {
            out.write("</table>");
        } catch (IOException e) {
            throw new JspException(e.getMessage());
        }
        return EVAL_PAGE;
    }
}
